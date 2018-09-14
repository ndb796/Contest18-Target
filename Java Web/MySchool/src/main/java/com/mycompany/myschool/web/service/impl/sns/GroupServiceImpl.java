package com.mycompany.myschool.web.service.impl.sns;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.myschool.web.entity.sns.GroupEntity;
import com.mycompany.myschool.web.persistent.mapper.sns.GroupMapper;
import com.mycompany.myschool.web.service.sns.GroupService;

@Service
public class GroupServiceImpl implements GroupService {
	@Autowired
	private GroupMapper groupMapper;
	
	@Override
	public List<GroupEntity> list(GroupEntity groupEntity) {
		return groupMapper.select(groupEntity);
	}

	@Override
	public GroupEntity get(GroupEntity groupEntity) {
		return groupMapper.selectOne(groupEntity);
	}

	@Override
	public List<GroupEntity> listByStudent(String student_id) {
		return groupMapper.selectByStudent(student_id);
	}

	@Override
	public String getMaxId() {
		return groupMapper.selectMaxId();
	}
	
	@Override
	public boolean add(GroupEntity groupEntity) {
		return groupMapper.insert(groupEntity) > 0 ? true : false;
	}

	@Override
	public boolean modify(GroupEntity groupEntity) {
		return groupMapper.update(groupEntity) > 0 ? true : false;
	}

	@Override
	public boolean remove(String group_id) {
		return groupMapper.delete(group_id) > 0 ? true : false;
	}

	@Override
	public boolean removeList(GroupEntity groupEntity) {
		return groupMapper.deleteList(groupEntity) > 0 ? true : false;
	}
}
