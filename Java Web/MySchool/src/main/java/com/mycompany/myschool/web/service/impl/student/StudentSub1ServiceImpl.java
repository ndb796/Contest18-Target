package com.mycompany.myschool.web.service.impl.student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.myschool.web.entity.student.StudentSub1Entity;
import com.mycompany.myschool.web.persistent.mapper.student.StudentSub1Mapper;
import com.mycompany.myschool.web.service.student.StudentSub1Service;

@Service
public class StudentSub1ServiceImpl implements StudentSub1Service {
	@Autowired
	private StudentSub1Mapper curSchoolMapper;
	
	@Override
	public List<StudentSub1Entity> list(StudentSub1Entity curSchoolEntity) {
		return curSchoolMapper.select(curSchoolEntity);
	}

	@Override
	public StudentSub1Entity get(String student_id) {
		return curSchoolMapper.selectOne(student_id);
	}

	@Override
	public boolean add(StudentSub1Entity curSchoolEntity) {
		return curSchoolMapper.insert(curSchoolEntity) > 0 ? true : false;
	}

	@Override
	public boolean modify(StudentSub1Entity curSchoolEntity) {
		return curSchoolMapper.update(curSchoolEntity) > 0 ? true : false;
	}

	@Override
	public boolean remove(String student_id) {
		return curSchoolMapper.delete(student_id) > 0 ? true : false;
	}
}
