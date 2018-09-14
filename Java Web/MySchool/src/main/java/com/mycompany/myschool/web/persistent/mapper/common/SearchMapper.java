package com.mycompany.myschool.web.persistent.mapper.common;

import java.util.List;
import java.util.Map;

import com.mycompany.myschool.web.entity.admin.MainEntity;
import com.mycompany.myschool.web.entity.common.SearchEntity;

public interface SearchMapper {

	SearchEntity selectOne(String key);

	List<SearchEntity> selectForSearchMiddleschool(SearchEntity searchEntity);
	
	List<SearchEntity> selectAllschoolList(SearchEntity searchEntity);
	
	List<SearchEntity> selectMiddleschoolList(SearchEntity searchEntity);
	
	List<SearchEntity> selectForSearchHighschool(SearchEntity searchEntity);
	
	List<SearchEntity> selectHighschoolList(SearchEntity searchEntity);
	
	List<SearchEntity> selectForSearchInUniv(SearchEntity searchEntity);
	
	List<SearchEntity> selectUniversityList(SearchEntity searchEntity);
	
	List<SearchEntity> selectForSearchInVocational(SearchEntity searchEntity);
	
	List<SearchEntity> selectVocationalList(SearchEntity searchEntity);
	
	Integer selectCountMiddleschoolList(SearchEntity entity);
	
	Integer selectCountHighschoolList(SearchEntity entity);
	
	Integer selectCountUniversityList(SearchEntity entity);
	
	Integer selectCountVocationalList(SearchEntity entity);
	
	Integer selectCountAllList(SearchEntity entity);
	
	SearchEntity selectMiddleschoolOne(String sch_id);
	
	SearchEntity selectMemberSchoolOne(String sch_id);
	
	SearchEntity selectHighschoolOne(String sch_id);
	
	SearchEntity selectUniversityOne(String sch_id);
	
	SearchEntity selectVocationalOne(String sch_id);
	
	/*
	 * For mobile
	 */
	List<SearchEntity> selectForSearchFromView(SearchEntity searchEntity);
	
	/*
	 * For recommend
	 */
	List<SearchEntity> selectForRcmmdWithSchoolKeyword(Map<String, Object> maps);
	
	List<SearchEntity> selectForRcmmdWithAddressKeyword(Map<String, Object> maps);
	
	List<SearchEntity> selectForRcmmdWithAddress1(Map<String, Object> maps);
	List<SearchEntity> selectForRcmmdWithAddress2(Map<String, Object> maps);
	List<SearchEntity> selectForRcmmdWithAddress3(Map<String, Object> maps);
	
	/*
	 * For admin dashboard
	 */
	int countSchoolThisWeek(Map<String, Object> maps);
	
	MainEntity selectNewSchool();
}