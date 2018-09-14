package com.mycompany.myschool.web.service.impl.student;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.myschool.web.entity.admin.MainEntity;
import com.mycompany.myschool.web.entity.student.StudentEntity;
import com.mycompany.myschool.web.persistent.mapper.student.StudentMapper;
import com.mycompany.myschool.web.service.student.StudentService;

@Service
public class StudentServiceImpl implements StudentService {
	@Autowired
	private StudentMapper studentMapper;
	
	@Override
	public List<StudentEntity> list(StudentEntity studentEntity) {
		return studentMapper.select(studentEntity);
	}

	@Override
	public StudentEntity get(StudentEntity studentEntity) {
		return studentMapper.selectOne(studentEntity);
	}
	
	@Override
	public StudentEntity getByStudentId(String student_id) {
		return studentMapper.selectByStudentId(student_id);
	}

	@Override
	public String getMaxId() {
		return studentMapper.selectMaxId();
	}
	
	@Override
	public boolean add(StudentEntity studentEntity) {
		return studentMapper.insert(studentEntity) > 0 ? true : false;
	}

	@Override
	public boolean modify(StudentEntity studentEntity) {
		return studentMapper.update(studentEntity) > 0 ? true : false;
	}

	@Override
	public boolean modifyStatus(StudentEntity studentEntity) {
		return studentMapper.updateStatus(studentEntity) > 0 ? true : false;
	}

	@Override
	public boolean remove(String email) {
		return studentMapper.delete(email) > 0 ? true : false;
	}

	@Override
	public boolean removeList(StudentEntity studentEntity) {
		return studentMapper.deleteList(studentEntity) > 0 ? true : false;
	}
	
	@Override
	public int countStudentThisWeek(Map<String, Object> maps) {
		return studentMapper.countStudentThisWeek(maps);
	}

	@Override
	public MainEntity getNewStudent() {
		return studentMapper.selectNewStudent();
	}
}
