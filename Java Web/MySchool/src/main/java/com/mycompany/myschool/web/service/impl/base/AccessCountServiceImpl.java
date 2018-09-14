package com.mycompany.myschool.web.service.impl.base;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.myschool.web.entity.base.AccessCountEntity;
import com.mycompany.myschool.web.persistent.mapper.base.AccessCountMapper;
import com.mycompany.myschool.web.service.base.AccessCountService;

@Service
public class AccessCountServiceImpl implements AccessCountService {
	@Autowired
	private AccessCountMapper accessCountMapper;
	
	@Override
	public List<AccessCountEntity> list(Map<String, String> searchMap) {
		return accessCountMapper.select(searchMap);
	}

	@Override
	public AccessCountEntity get(String acc_date) {
		return accessCountMapper.selectOne(acc_date);
	}

	@Override
	public boolean add(AccessCountEntity accessCountEntity) {
		return accessCountMapper.insert(accessCountEntity) > 0 ? true : false;
	}

	@Override
	public boolean modifySchCnt(String acc_date) {
		return accessCountMapper.updateSchCnt(acc_date) > 0 ? true : false;
	}

	@Override
	public boolean modifyStdCnt(String acc_date) {
		return accessCountMapper.updateStdCnt(acc_date) > 0 ? true : false;
	}
	
	@Override
	public List<AccessCountEntity> countAccessLast6Months(Map<String, Object> maps) {
		return accessCountMapper.countAccessLast6Months(maps);
	}
}
