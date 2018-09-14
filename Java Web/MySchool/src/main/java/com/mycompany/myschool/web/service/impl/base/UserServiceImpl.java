package com.mycompany.myschool.web.service.impl.base;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.myschool.web.entity.base.UserEntity;
import com.mycompany.myschool.web.persistent.mapper.base.UserMapper;
import com.mycompany.myschool.web.service.base.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public List<UserEntity> list(UserEntity userEntity) {
		return userMapper.select(userEntity);
	}

	@Override
	public UserEntity getByUserId(String user_id) {
		return userMapper.selectByUserId(user_id);
	}

	@Override
	public List<UserEntity> getByLogin(String email) {
		return userMapper.selectByLogin(email);
	}

	@Override
	public String getMaxId(Map<String, String> map) {
		return userMapper.selectMaxId(map);
	}
	
	@Override
	public boolean add(UserEntity userEntity) {
		return userMapper.insert(userEntity) > 0 ? true : false;
	}

	@Override
	public boolean modify(UserEntity userEntity) {
		return userMapper.update(userEntity) > 0 ? true : false;
	}
	
	@Override
	public boolean modifyByUserId(UserEntity userEntity) {
		return userMapper.updateByUserId(userEntity) > 0 ? true : false;
	}

	@Override
	public boolean modifyStatus(UserEntity userEntity) {
		return userMapper.updateStatus(userEntity) > 0 ? true : false;
	}

	@Override
	public boolean remove(Map<String, Object> maps) {
		return userMapper.delete(maps) > 0 ? true : false;
	}
}
