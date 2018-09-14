package com.mycompany.myschool.web.service.base;

import java.util.List;
import java.util.Map;

import com.mycompany.myschool.web.entity.base.UserEntity;

public interface UserService {
	List<UserEntity> list(UserEntity userEntity);
	
	UserEntity getByUserId(String user_id);
	
	List<UserEntity> getByLogin(String email);
	
	String getMaxId(Map<String, String> map);
	
	boolean add(UserEntity userEntity);
	
	boolean modify(UserEntity userEntity);
	
	boolean modifyByUserId(UserEntity userEntity);
	
	boolean modifyStatus(UserEntity userEntity);
	
	boolean remove(Map<String, Object> maps);
}
