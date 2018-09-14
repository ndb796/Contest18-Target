package com.mycompany.myschool.web.persistent.mapper.sns;

import java.util.List;

import com.mycompany.myschool.web.entity.sns.MyGroupEntity;

public interface MyGroupMapper {
	List<MyGroupEntity> select(MyGroupEntity myGroupEntity);
	
	MyGroupEntity selectOne(MyGroupEntity myGroupEntity);
	
	List<MyGroupEntity> selectByGroup(MyGroupEntity myGroupEntity);
	
	List<MyGroupEntity> selectByStudent(MyGroupEntity myGroupEntity);
	
	List<MyGroupEntity> selectByMyStudentId(String mystudent_id);
	
	int insert(MyGroupEntity myGroupEntity);
	
	int updateStatus(MyGroupEntity myGroupEntity);
	
	int updateGroup(MyGroupEntity myGroupEntity);
	
	int delete(int idx);
	
	int deleteList(MyGroupEntity myGroupEntity);
}
