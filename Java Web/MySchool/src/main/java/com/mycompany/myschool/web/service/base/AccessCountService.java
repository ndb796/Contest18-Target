package com.mycompany.myschool.web.service.base;

import java.util.List;
import java.util.Map;

import com.mycompany.myschool.web.entity.base.AccessCountEntity;

public interface AccessCountService {
	List<AccessCountEntity> list(Map<String, String> searchMap);
	
	AccessCountEntity get(String acc_date);
	
	boolean add(AccessCountEntity accessCountEntity);
	
	boolean modifySchCnt(String acc_date);
	
	boolean modifyStdCnt(String acc_date);

	List<AccessCountEntity> countAccessLast6Months(Map<String, Object> maps);
}
