package com.mycompany.myschool.web.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import com.mycompany.myschool.web.entity.base.UserEntity;
import com.mycompany.myschool.web.entity.common.SearchEntity;
import com.mycompany.myschool.web.entity.student.RecommendEntity;
import com.mycompany.myschool.web.entity.student.StudentSub2Entity;
import com.mycompany.myschool.web.entity.student.StudentEntity;
import com.mycompany.myschool.web.entity.vo.RecommendListVo;
import com.mycompany.myschool.web.service.common.SearchService;
import com.mycompany.myschool.web.service.student.RecommendService;
import com.mycompany.myschool.web.service.student.StudentSub2Service;
import com.mycompany.myschool.web.service.student.StudentService;
import com.mycompany.myschool.web.util.Constants;
import com.mycompany.myschool.web.util.IDGenerator;
import com.mycompany.myschool.web.util.Util;

public class DRESManager {
	public boolean EXEC_DRES(UserEntity userEntity,
							List<RecommendListVo> rcmmdList,
							StudentService studentService, 
							StudentSub2Service specificService, 
							SearchService searchService, 
							RecommendService rcmmdService) {
		/*
		 * 10개 미만이면 실행
		 * JOB3 - STUDENT테이블에서 추천 받고 싶은 학교의 종류 컬럼 값 검색
		 */
		StudentEntity studentEntity = studentService.getByStudentId(userEntity.getUser_id());
		
		if (studentEntity == null) {
			return false;
		}
		
		int rcmmd_type = studentEntity.getRcmmd_type();
		
		/*
		 * JOB4 - SPECIFIC테이블에서 SCHOOL,LOCATION의 우선 순위에 의해서 각각 BRANCH테이블의 NAME과 ADDRESS컬럼과 비교 -> 1차 추천
		 *        (현재 상태로는 SCHOOL은 7개 LOCATION은 3개 정도의 비율로 추천)  
		 */
		SortedMap<String, List<SearchEntity>> resultMap = new TreeMap<String, List<SearchEntity>>();
		
		StudentSub2Entity specificEntity = specificService.get(userEntity.getUser_id());
		HashMap<String, Object> searchMap = new HashMap<String, Object>();
		int totalSearchCount = 0;
		Map<String, SearchEntity> searchResultMap = new HashMap<String, SearchEntity>();
		
		if (specificEntity != null) {
			int schoolOrd = specificEntity.getSchool_ord();
			int locationOrd = specificEntity.getLocation_ord();
			int readCount[] = new int[2];
			
			if (schoolOrd < locationOrd) {				// schoolOrd:1순위, locationOrd:2순위 이라면 
				readCount[0] = 7;
				readCount[1] = 3;
			} else if (schoolOrd > locationOrd) {		// schoolOrd:2순위, locationOrd:1순위 이라면 
				readCount[0] = 3;
				readCount[1] = 7;
			} else {
				readCount[0] = 5;
				readCount[1] = 5;
			}
			
			/*
			 * For school
			 */
			searchMap.clear();
			searchMap.put("school_type", rcmmd_type); 
//			System.out.println(specificEntity.getSchool1() != null && specificEntity.getSchool1().length() > 0);
			if (specificEntity.getSchool1() != null && specificEntity.getSchool1().length() > 0) {
				searchMap.put("school_name1", specificEntity.getSchool1()); 
			}
			if (specificEntity.getSchool2() != null && specificEntity.getSchool2().length() > 0) {
				searchMap.put("school_name2", specificEntity.getSchool2());
			}
			if (specificEntity.getSchool3() != null && specificEntity.getSchool3().length() > 0) {
				searchMap.put("school_name3", specificEntity.getSchool3()); 
			}
			
			if (searchMap.containsKey("school_name3")) {
				searchMap.put("school_limit3", Math.round(readCount[0] * 0.2));
				searchMap.put("school_limit2", Math.round(readCount[0] * 0.3));
				searchMap.put("school_limit1", Math.round(readCount[0] * 0.5));
			} else if (searchMap.containsKey("school_name2")) {
				searchMap.put("school_limit2", Math.round(readCount[0] * 0.3));
				searchMap.put("school_limit1", Math.round(readCount[0] * 0.7));
			} else {
				searchMap.put("school_limit1", readCount[0]);
			}
//			System.out.println(">>> map info");
			Util.DISPLAY_MAP2STR(searchMap);
			List<SearchEntity> searchListBySchool = searchService.getForRcmmdWithSchoolKeyword(searchMap);
			if (searchListBySchool != null && searchListBySchool.size() > 0) {
				int schoolCount = (searchListBySchool.size() > readCount[0] ? readCount[0] : searchListBySchool.size());
				
				int cnt = 0;
				Map<String, SearchEntity> _schSearchResultMap = new HashMap<String, SearchEntity>();
				for (int i = 0; i < schoolCount; i++) {
					if (!searchResultMap.containsKey(searchListBySchool.get(i).getSch_id())) {
						_schSearchResultMap.put(searchListBySchool.get(i).getSch_id(), searchListBySchool.get(i));
						cnt++;
					}
				}
				
				totalSearchCount += cnt;
				
				List<SearchEntity> searchResultList = new ArrayList<SearchEntity>();
				Iterator<String> searchKey = _schSearchResultMap.keySet().iterator(); 
				while (searchKey.hasNext()) {
					String key = searchKey.next();
					searchResultList.add(_schSearchResultMap.get(key));
				}
				resultMap.put("SCH", searchResultList);
			}
			
			/*
			 * For location
			 */
			searchMap.clear();
			searchMap.put("school_type", rcmmd_type); 
			if (specificEntity.getLocation1() != null && specificEntity.getLocation1().length() > 0) {
				searchMap.put("school_loc1", specificEntity.getLocation1());
			}
			if (specificEntity.getLocation2() != null && specificEntity.getLocation2().length() > 0) {
				searchMap.put("school_loc2", specificEntity.getLocation2());
			}
			if (specificEntity.getLocation3() != null && specificEntity.getLocation3().length() > 0) {
				searchMap.put("school_loc3", specificEntity.getLocation3());
			}
			
			if (searchMap.containsKey("school_loc3")) {
				searchMap.put("school_limit3", Math.round(readCount[1] * 0.2));
				searchMap.put("school_limit2", Math.round(readCount[1] * 0.3));
				searchMap.put("school_limit1", Math.round(readCount[1] * 0.5));
			} else if (searchMap.containsKey("school_loc2")) {
				searchMap.put("school_limit2", Math.round(readCount[1] * 0.3));
				searchMap.put("school_limit1", Math.round(readCount[1] * 0.7));
			} else {
				searchMap.put("school_limit1", readCount[1]);
			}
			
			List<SearchEntity> searchListByLocation = searchService.getForRcmmdWithAddressKeyword(searchMap);
			if (searchListByLocation != null && searchListByLocation.size() > 0) {
				int locationCount = (searchListByLocation.size() > readCount[1] ? readCount[0] : searchListByLocation.size());
	
				int cnt = 0;
				Map<String, SearchEntity> _locSearchResultMap = new HashMap<String, SearchEntity>();
				for (int i = 0; i < locationCount; i++) {
					if (!searchResultMap.containsKey(searchListByLocation.get(i).getSch_id())) {
						_locSearchResultMap.put(searchListByLocation.get(i).getSch_id(), searchListByLocation.get(i));
						cnt++;
					}
				}
				
				totalSearchCount += cnt;
				
				List<SearchEntity> searchResultList = new ArrayList<SearchEntity>();
				Iterator<String> searchKey = _locSearchResultMap.keySet().iterator(); 
				while (searchKey.hasNext()) {
					String key = searchKey.next();
					searchResultList.add(_locSearchResultMap.get(key));
				}
				resultMap.put("LOC", searchResultList);
			}
		} 
		
		/*
		 * JOB5 - (SPECIFIC테이블이 존재하지 않거나 또는) 결과가 아직 10개 미만이면 
		 * 		  STUDENT테이블의 ZIPCODE, ADDRESS컬럼과 BRANCH의 ADDRESS컬럼과 비교  -> 2차 추천
		 */
		if (specificEntity == null || totalSearchCount < Constants.BASE_RCMMD_COUNT) {
			/*
			 * job5-1 ZIPCODE (-> ADDRESS1 + ADDRESS2 + ADDRESS3)
			 * 			STUDENT_TX의 ZIPCODE와 일치하는 데이터를 SEARCH_TX에서 검색
			 */
			searchMap.clear();
			searchMap.put("school_type", rcmmd_type);
			searchMap.put("zipcode", studentEntity.getZipcode());
			
			Map<String, SearchEntity> _bacSearchResultMap = new HashMap<String, SearchEntity>();
			List<SearchEntity> searchListByAddress1 = searchService.getForRcmmdWithAddress1(searchMap);
			if (searchListByAddress1 != null && searchListByAddress1.size()  > 0) {
				int zipcodeCount = (searchListByAddress1.size() > (Constants.BASE_RCMMD_COUNT - totalSearchCount)
										? (Constants.BASE_RCMMD_COUNT - totalSearchCount)
										: searchListByAddress1.size());
				
				int cnt = 0;
				for (int i = 0; i < zipcodeCount; i++) {
					if (!searchResultMap.containsKey(searchListByAddress1.get(i).getSch_id())) {
						_bacSearchResultMap.put(searchListByAddress1.get(i).getSch_id(), searchListByAddress1.get(i));
						cnt++;
					} 
				}
				
				totalSearchCount += cnt;
			}
			
			/*
			 * job5-2. 시군동 (-> ADDRESS1 + ADDRESS2)
			 * 			Zipcode 검색 결과를 포함하여 전체 결과가 Constants.BASE_RCMMD_COUNT(10)개 미만인 경우
			 */
			if (totalSearchCount < Constants.BASE_RCMMD_COUNT) {
				searchMap.clear();
				searchMap.put("school_type", rcmmd_type);
				searchMap.put("address", studentEntity.getAddress1() + studentEntity.getAddress2());
				List<SearchEntity> searchListByAddress2 = searchService.getForRcmmdWithAddress2(searchMap);
				if (searchListByAddress2 != null && searchListByAddress2.size()  > 0) {
					int address2Count = (searchListByAddress2.size() > (Constants.BASE_RCMMD_COUNT - totalSearchCount)
											? (Constants.BASE_RCMMD_COUNT - totalSearchCount)
											: searchListByAddress2.size());
					
					int cnt = 0;
					for (int i = 0; i < address2Count; i++) {
						if (!searchResultMap.containsKey(searchListByAddress2.get(i).getSch_id())) {
							_bacSearchResultMap.put(searchListByAddress2.get(i).getSch_id(), searchListByAddress2.get(i));
							cnt++;
						} 
					}
					
					totalSearchCount += cnt;
				}
				
				/*
				 * job5-3. 도　(-> ADDRESS1)
				 * 			시군동 (-> ADDRESS1 + ADDRESS2)로 검색한 결과를 포함하여 전체 결과가 Constants.BASE_RCMMD_COUNT(10)개 미만인 경우
				 */
				if (totalSearchCount < Constants.BASE_RCMMD_COUNT) {
					searchMap.clear();
					searchMap.put("school_type", rcmmd_type);
					searchMap.put("address", studentEntity.getAddress1());
					
					List<SearchEntity> searchListByAddress3 = searchService.getForRcmmdWithAddress3(searchMap);
					if (searchListByAddress3 != null && searchListByAddress3.size()  > 0) {
						int address2Count = (searchListByAddress3.size() > (Constants.BASE_RCMMD_COUNT - totalSearchCount)
								? (Constants.BASE_RCMMD_COUNT - totalSearchCount)
								: searchListByAddress3.size());
						
						int cnt = 0;
						for (int i = 0; i < address2Count; i++) {
							if (!searchResultMap.containsKey(searchListByAddress3.get(i).getSch_id())) {
								_bacSearchResultMap.put(searchListByAddress3.get(i).getSch_id(), searchListByAddress3.get(i));
								cnt++;
							}
						}
						
						totalSearchCount += cnt;
					}
				}
			}
			
			/*
			 * SPECIFIC_TX가 존재하지 않을 경우에 대한 검색 결과를 추천타입="BAC" 으로 저장
			 */
			List<SearchEntity> searchResultList = new ArrayList<SearchEntity>();
			Iterator<String> searchKey = _bacSearchResultMap.keySet().iterator(); 
			while (searchKey.hasNext()) {
				String key = searchKey.next();
				searchResultList.add(_bacSearchResultMap.get(key));
			}
			resultMap.put("BAC", searchResultList);
		}
		
		/*
		 * JOB6 - 상담 내역이 없는 7일 이전의 데이터 검색하여 데이터 삭제   
		 * 검색 된 결과가 0일 경우에는 기존 추천 학교를 삭제하지 않음
		 */
		if (totalSearchCount > 0) {
			rcmmdService.removeOldDataByStudentId(userEntity.getUser_id());
		}
		
		/*
		 * JOB7 - 추천 된 학교 목록을 RCMMD테이블에 저장
		 */
		Map<String, RecommendListVo> prevRcmmdMap = new HashMap<String, RecommendListVo>();
		for (RecommendListVo rcmmd : rcmmdList) {
			prevRcmmdMap.put(rcmmd.getSb_id(), rcmmd);
		}
		
		
		Map<String, RecommendEntity> newRcmmdMap = new HashMap<String, RecommendEntity>();
//		Iterator<String> key = resultMap.keySet().iterator();
		String[] keys = {"SCH", "LOC", "BAC"};
		for (String name : keys) {
			List<SearchEntity> list = resultMap.get(name); 
			if (list != null) {
				for (SearchEntity entity : list) {
					if (prevRcmmdMap.containsKey(entity.getSch_id()) || newRcmmdMap.containsKey(entity.getSch_id())) {
						continue;
					}
					
					RecommendEntity recommendEntity = new RecommendEntity();
					String curId = rcmmdService.getMaxId();
					String newId =  IDGenerator.NEXT_ID(Constants.SRVC_TYPE_RCM, curId);
					recommendEntity.setRcmmd_id(newId);
					recommendEntity.setStudent_id(userEntity.getUser_id());
					recommendEntity.setSb_id(entity.getSch_id());
					recommendEntity.setRcmmd_type(name);
					rcmmdService.add(recommendEntity);
					
					newRcmmdMap.put(recommendEntity.getSb_id(), recommendEntity);
				}
			}
		}
		
		return true;
	}
}
