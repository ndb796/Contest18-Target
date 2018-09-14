package com.mycompany.myschool.web.persistent.mapper.student;

import java.util.List;

import com.mycompany.myschool.web.entity.student.StudentSub1Entity;

public interface StudentSub1Mapper {
	List<StudentSub1Entity> select(StudentSub1Entity curSchoolEntity);
	
	StudentSub1Entity selectOne(String student_id);
	
	int insert(StudentSub1Entity curSchoolEntity);
	
	int update(StudentSub1Entity curSchoolEntity);

	int delete(String student_id);
}
