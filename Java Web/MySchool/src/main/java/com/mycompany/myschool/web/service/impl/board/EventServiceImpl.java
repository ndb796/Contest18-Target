package com.mycompany.myschool.web.service.impl.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.myschool.web.entity.board.EventEntity;
import com.mycompany.myschool.web.persistent.mapper.board.EventMapper;
import com.mycompany.myschool.web.service.board.EventService;

@Service
public class EventServiceImpl implements EventService {
	@Autowired
	private EventMapper eventMapper;
	
	@Override
	public List<EventEntity> list(EventEntity eventEntity) {
		return eventMapper.select(eventEntity);
	}

	@Override
	public EventEntity get(EventEntity eventEntity) {
		return eventMapper.selectOne(eventEntity);
	}

	@Override
	public List<EventEntity> listByStudent(String student_id) {
		return eventMapper.selectByStudent(student_id);
	}

	@Override
	public List<EventEntity> listByGroup(EventEntity eventEntity) {
		return eventMapper.selectByGroup(eventEntity);
	}

	@Override
	public String getMaxId() {
		return eventMapper.selectMaxId();
	}
	
	@Override
	public boolean add(EventEntity eventEntity) {
		return eventMapper.insert(eventEntity) > 0 ? true : false;
	}

	@Override
	public boolean modify(EventEntity eventEntity) {
		return eventMapper.update(eventEntity) > 0 ? true : false;
	}

	@Override
	public boolean remove(String evt_id) {
		return eventMapper.delete(evt_id) > 0 ? true : false;
	}

	@Override
	public boolean removeList(EventEntity eventEntity) {
		return eventMapper.deleteList(eventEntity) > 0 ? true : false;
	}
}
