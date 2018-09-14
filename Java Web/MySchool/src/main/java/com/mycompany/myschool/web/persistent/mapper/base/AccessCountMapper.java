package com.mycompany.myschool.web.persistent.mapper.base;

import java.util.List;
import java.util.Map;

import com.mycompany.myschool.web.entity.base.AccessCountEntity;

public interface AccessCountMapper {
	List<AccessCountEntity> select(Map<String, String> searchMap);
	
	AccessCountEntity selectOne(String acc_date);
	
	int insert(AccessCountEntity accessCountEntity);
	
	int updateSchCnt(String acc_date);
	
	int updateStdCnt(String acc_date);
	
	List<AccessCountEntity> countAccessLast6Months(Map<String, Object> maps);
}
