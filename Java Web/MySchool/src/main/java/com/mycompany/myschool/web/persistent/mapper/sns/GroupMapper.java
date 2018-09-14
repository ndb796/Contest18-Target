package com.mycompany.myschool.web.persistent.mapper.sns;

import java.util.List;

import com.mycompany.myschool.web.entity.sns.GroupEntity;

public interface GroupMapper {
	List<GroupEntity> select(GroupEntity groupEntity);
	
	GroupEntity selectOne(GroupEntity groupEntity);
	
	List<GroupEntity> selectByStudent(String student_id);
	
	String selectMaxId();
	
	int insert(GroupEntity groupEntity);
	
	int update(GroupEntity groupEntity);

	int delete(String group_id);
	
	int deleteList(GroupEntity groupEntity);
}
