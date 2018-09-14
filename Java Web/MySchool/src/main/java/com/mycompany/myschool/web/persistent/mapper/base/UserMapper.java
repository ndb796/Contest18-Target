package com.mycompany.myschool.web.persistent.mapper.base;

import java.util.List;
import java.util.Map;

import com.mycompany.myschool.web.entity.base.UserEntity;

public interface UserMapper {
	List<UserEntity> select(UserEntity userEntity);
	
	UserEntity selectByUserId(String user_id);
	
	List<UserEntity> selectByLogin(String email);
	
	String selectMaxId(Map<String, String> map);
	
	int insert(UserEntity userEntity);
	
	int update(UserEntity userEntity);
	
	int updateByUserId(UserEntity userEntity);

	int updateStatus(UserEntity userEntity);
	
	int delete(Map<String, Object> maps);
}
