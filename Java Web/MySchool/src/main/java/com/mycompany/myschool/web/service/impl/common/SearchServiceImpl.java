package com.mycompany.myschool.web.service.impl.common;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.myschool.web.entity.admin.MainEntity;
import com.mycompany.myschool.web.entity.common.SearchEntity;
import com.mycompany.myschool.web.persistent.mapper.common.SearchMapper;
import com.mycompany.myschool.web.service.common.SearchService;

@Service
public class SearchServiceImpl implements SearchService {
	@Autowired
	private SearchMapper searchMapper;
	
	@Override
	public SearchEntity getOne(String key) {
		return searchMapper.selectOne(key);
	}

	@Override
	public List<SearchEntity> getForSearchMiddleschool(SearchEntity searchEntity) {
		return searchMapper.selectForSearchMiddleschool(searchEntity);
	}

	@Override
	public List<SearchEntity> getAllschoolList(SearchEntity searchEntity) {
		return searchMapper.selectAllschoolList(searchEntity);
	}
	
	@Override
	public List<SearchEntity> getMiddleschoolList(SearchEntity searchEntity) {
		return searchMapper.selectMiddleschoolList(searchEntity);
	}

	@Override
	public List<SearchEntity> getForSearchHighschool(SearchEntity searchEntity) {
		return searchMapper.selectForSearchHighschool(searchEntity);
	}

	@Override
	public List<SearchEntity> getForSearchInUniv(SearchEntity searchEntity) {
		return searchMapper.selectForSearchInUniv(searchEntity);
	}

	@Override
	public List<SearchEntity> getForSearchInVocational(SearchEntity searchEntity) {
		return searchMapper.selectForSearchInVocational(searchEntity);
	}

	@Override
	public List<SearchEntity> getHighschoolList(SearchEntity searchEntity) {
		return searchMapper.selectHighschoolList(searchEntity);
	}

	@Override
	public List<SearchEntity> getUniversityList(SearchEntity searchEntity) {
		return searchMapper.selectUniversityList(searchEntity);
	}

	@Override
	public List<SearchEntity> getVocationalList(SearchEntity searchEntity) {
		return searchMapper.selectVocationalList(searchEntity);
	}

	@Override
	public Integer getCountMiddleschoolList(SearchEntity entity) {
		return searchMapper.selectCountMiddleschoolList(entity);
	}
	
	@Override
	public Integer getCountHighschoolList(SearchEntity entity) {
		return searchMapper.selectCountHighschoolList(entity);
	}

	@Override
	public Integer getCountUniversityList(SearchEntity entity) {
		return searchMapper.selectCountUniversityList(entity);
	}

	@Override
	public Integer getCountVocationalList(SearchEntity entity) {
		return searchMapper.selectCountVocationalList(entity);
	}
	
	@Override
	public Integer getCountAllList(SearchEntity entity) {
		return searchMapper.selectCountAllList(entity);
	}

	@Override
	public SearchEntity getMemberschoolOne(String sch_id) {
		return searchMapper.selectMemberSchoolOne(sch_id);
	}

	@Override
	public SearchEntity getMiddleschoolOne(String sch_id) {
		return searchMapper.selectMiddleschoolOne(sch_id);
	}
	
	@Override
	public SearchEntity getHighschoolOne(String sch_id) {
		return searchMapper.selectHighschoolOne(sch_id);
	}

	@Override
	public SearchEntity getUniversityOne(String sch_id) {
		return searchMapper.selectUniversityOne(sch_id);
	}

	@Override
	public SearchEntity getVocationalOne(String sch_id) {
		return searchMapper.selectVocationalOne(sch_id);
	}

	@Override
	public List<SearchEntity> getForRcmmdWithSchoolKeyword(Map<String, Object> maps) {
		return searchMapper.selectForRcmmdWithSchoolKeyword(maps);
	}	
	
	@Override
	public List<SearchEntity> getForRcmmdWithAddressKeyword(Map<String, Object> maps) {
		return searchMapper.selectForRcmmdWithAddressKeyword(maps);
	}

	@Override
	public List<SearchEntity> getForRcmmdWithAddress1(Map<String, Object> maps) {
		return searchMapper.selectForRcmmdWithAddress1(maps);
	}

	@Override
	public List<SearchEntity> getForRcmmdWithAddress2(Map<String, Object> maps) {
		return searchMapper.selectForRcmmdWithAddress2(maps);
	}

	@Override
	public List<SearchEntity> getForRcmmdWithAddress3(Map<String, Object> maps) {
		return searchMapper.selectForRcmmdWithAddress3(maps);
	}	
	
	@Override
	public int countSchoolThisWeek(Map<String, Object> maps) {
		return searchMapper.countSchoolThisWeek(maps);
	}

	@Override
	public MainEntity getNewSchool() {
		return searchMapper.selectNewSchool();
	}

	@Override
	public List<SearchEntity> getForSearchFromView(SearchEntity searchEntity) {
		return searchMapper.selectForSearchFromView(searchEntity);
	}
	
}