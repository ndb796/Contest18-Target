package com.mycompany.myschool.web.service.student;

import java.util.List;

import com.mycompany.myschool.web.entity.student.StudentSub2Entity;

public interface StudentSub2Service {
	List<StudentSub2Entity> list(StudentSub2Entity specificEntity);
	
	StudentSub2Entity get(String student_id);
	
	boolean add(StudentSub2Entity specificEntity);
	
	boolean modify(StudentSub2Entity specificEntity);
	
	boolean remove(String student_id);
}
