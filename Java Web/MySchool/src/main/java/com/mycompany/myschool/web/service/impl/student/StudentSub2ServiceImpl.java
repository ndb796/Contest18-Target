package com.mycompany.myschool.web.service.impl.student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.myschool.web.entity.student.StudentSub2Entity;
import com.mycompany.myschool.web.persistent.mapper.student.StudentSub2Mapper;
import com.mycompany.myschool.web.service.student.StudentSub2Service;

@Service
public class StudentSub2ServiceImpl implements StudentSub2Service {
	@Autowired
	private StudentSub2Mapper specificMapper;
	
	@Override
	public List<StudentSub2Entity> list(StudentSub2Entity specificEntity) {
		return specificMapper.select(specificEntity);
	}

	@Override
	public StudentSub2Entity get(String student_id) {
		return specificMapper.selectOne(student_id);
	}

	@Override
	public boolean add(StudentSub2Entity specificEntity) {
		return specificMapper.insert(specificEntity) > 0 ? true : false;
	}

	@Override
	public boolean modify(StudentSub2Entity specificEntity) {
		return specificMapper.update(specificEntity) > 0 ? true : false;
	}

	@Override
	public boolean remove(String student_id) {
		return specificMapper.delete(student_id) > 0 ? true : false;
	}
}
