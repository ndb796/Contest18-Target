package com.mycompany.myschool.web.service.sns;

import java.util.List;

import com.mycompany.myschool.web.entity.sns.GroupEntity;

public interface GroupService {
	List<GroupEntity> list(GroupEntity groupEntity);
	
	GroupEntity get(GroupEntity groupEntity);
	
	List<GroupEntity> listByStudent(String student_id);
	
	String getMaxId();
	
	boolean add(GroupEntity groupEntity);
	
	boolean modify(GroupEntity groupEntity);
	
	boolean remove(String group_id);
	
	boolean removeList(GroupEntity groupEntity);
}
