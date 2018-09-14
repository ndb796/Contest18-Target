package com.mycompany.myschool.web.persistent.mapper.student;

import java.util.List;
import java.util.Map;

import com.mycompany.myschool.web.entity.admin.MainEntity;
import com.mycompany.myschool.web.entity.student.StudentEntity;

public interface StudentMapper {
	List<StudentEntity> select(StudentEntity studentEntity);
	
	StudentEntity selectOne(StudentEntity studentEntity);
	
	StudentEntity selectByStudentId(String student_id);
	
	String selectMaxId();
	
	int insert(StudentEntity studentEntity);
	
	int update(StudentEntity studentEntity);

	int updateStatus(StudentEntity studentEntity);
	
	int delete(String email);
	
	int deleteList(StudentEntity studentEntity);
	
	int countStudentThisWeek(Map<String, Object> maps);
	
	MainEntity selectNewStudent();
}
