package com.mycompany.myschool.web.service.student;

import java.util.List;

import com.mycompany.myschool.web.entity.student.StudentSub1Entity;

public interface StudentSub1Service {
	List<StudentSub1Entity> list(StudentSub1Entity curSchoolEntity);
	
	StudentSub1Entity get(String student_id);
	
	boolean add(StudentSub1Entity curSchoolEntity);
	
	boolean modify(StudentSub1Entity curSchoolEntity);
	
	boolean remove(String student_id);
}
