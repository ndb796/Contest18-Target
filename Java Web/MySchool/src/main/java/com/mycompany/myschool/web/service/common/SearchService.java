package com.mycompany.myschool.web.service.common;

import java.util.List;
import java.util.Map;

import com.mycompany.myschool.web.entity.admin.MainEntity;
import com.mycompany.myschool.web.entity.common.SearchEntity;

public interface SearchService {

	SearchEntity getOne(String key);

	List<SearchEntity> getForSearchMiddleschool(SearchEntity searchEntity);
	
	List<SearchEntity> getAllschoolList(SearchEntity searchEntity);
	
	List<SearchEntity> getMiddleschoolList(SearchEntity searchEntity);
	
	List<SearchEntity> getForSearchHighschool(SearchEntity searchEntity);
	
	List<SearchEntity> getHighschoolList(SearchEntity searchEntity);
	
	List<SearchEntity> getForSearchInUniv(SearchEntity searchEntity);
	
	List<SearchEntity> getUniversityList(SearchEntity searchEntity);
	
	List<SearchEntity> getForSearchInVocational(SearchEntity searchEntity);
	
	List<SearchEntity> getVocationalList(SearchEntity searchEntity);
	
	Integer getCountMiddleschoolList(SearchEntity entity);
	
	Integer getCountHighschoolList(SearchEntity entity);
	
	Integer getCountUniversityList(SearchEntity entity);
	
	Integer getCountVocationalList(SearchEntity entity);
	
	Integer getCountAllList(SearchEntity entity);
	
	SearchEntity getMemberschoolOne(String sch_id);
	
	SearchEntity getMiddleschoolOne(String sch_id);
	
	SearchEntity getHighschoolOne(String sch_id);
	
	SearchEntity getUniversityOne(String sch_id);
	
	SearchEntity getVocationalOne(String sch_id);
	
	List<SearchEntity> getForSearchFromView(SearchEntity searchEntity);
	
	List<SearchEntity> getForRcmmdWithSchoolKeyword(Map<String, Object> maps);
	
	List<SearchEntity> getForRcmmdWithAddressKeyword(Map<String, Object> maps);
	
	List<SearchEntity> getForRcmmdWithAddress1(Map<String, Object> maps);
	List<SearchEntity> getForRcmmdWithAddress2(Map<String, Object> maps);
	List<SearchEntity> getForRcmmdWithAddress3(Map<String, Object> maps);
	
	int countSchoolThisWeek(Map<String, Object> maps);
	
	MainEntity getNewSchool();
}