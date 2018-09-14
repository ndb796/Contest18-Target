package com.mycompany.myschool.web.service.impl.sns;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.myschool.web.entity.sns.MyGroupEntity;
import com.mycompany.myschool.web.persistent.mapper.sns.MyGroupMapper;
import com.mycompany.myschool.web.service.sns.MyGroupService;

@Service
public class MyGroupServiceImpl implements MyGroupService {
	@Autowired
	private MyGroupMapper myGroupMapper;
	
	@Override
	public List<MyGroupEntity> list(MyGroupEntity myGroupEntity) {
		return myGroupMapper.select(myGroupEntity);
	}

	@Override
	public MyGroupEntity get(MyGroupEntity myGroupEntity) {
		return myGroupMapper.selectOne(myGroupEntity);
	}

	@Override
	public List<MyGroupEntity> listByGroup(MyGroupEntity myGroupEntity) {
		return myGroupMapper.selectByGroup(myGroupEntity);
	}
	
	@Override
	public List<MyGroupEntity> listByMyStudentId(String mystudent_id) {
		return myGroupMapper.selectByMyStudentId(mystudent_id);
	}
	
	@Override
	public boolean add(MyGroupEntity myGroupEntity) {
		return myGroupMapper.insert(myGroupEntity) > 0 ? true : false;
	}

	@Override
	public boolean remove(int idx) {
		return myGroupMapper.delete(idx) > 0 ? true : false;
	}

	@Override
	public boolean removeList(MyGroupEntity myGroupEntity) {
		return myGroupMapper.deleteList(myGroupEntity) > 0 ? true : false;
	}
}
