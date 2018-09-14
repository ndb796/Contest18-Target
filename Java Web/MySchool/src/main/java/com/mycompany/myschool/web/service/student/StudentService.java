package com.mycompany.myschool.web.service.student;

import java.util.List;
import java.util.Map;

import com.mycompany.myschool.web.entity.admin.MainEntity;
import com.mycompany.myschool.web.entity.student.StudentEntity;

public interface StudentService {
	List<StudentEntity> list(StudentEntity studentEntity);
	
	StudentEntity get(StudentEntity studentEntity);
	
	StudentEntity getByStudentId(String student_id);
	
	String getMaxId();
	
	boolean add(StudentEntity studentEntity);
	
	boolean modify(StudentEntity studentEntity);
	
	boolean modifyStatus(StudentEntity studentEntity);
	
	boolean remove(String email);
	
	boolean removeList(StudentEntity studentEntity);
	
	int countStudentThisWeek(Map<String, Object> maps);
	
	MainEntity getNewStudent();
}
