package com.mycompany.myschool.web.persistent.mapper.student;

import java.util.List;

import com.mycompany.myschool.web.entity.student.StudentSub2Entity;

public interface StudentSub2Mapper {
	List<StudentSub2Entity> select(StudentSub2Entity specificEntity);
	
	StudentSub2Entity selectOne(String student_id);
	
	int insert(StudentSub2Entity specificEntity);
	
	int update(StudentSub2Entity specificEntity);

	int delete(String student_id);
}
