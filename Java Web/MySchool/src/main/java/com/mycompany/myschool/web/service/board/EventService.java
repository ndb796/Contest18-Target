package com.mycompany.myschool.web.service.board;

import java.util.List;

import com.mycompany.myschool.web.entity.board.EventEntity;

public interface EventService {
	List<EventEntity> list(EventEntity eventEntity);
	
	EventEntity get(EventEntity eventEntity);
	
	List<EventEntity> listByStudent(String student_id);
	
	List<EventEntity> listByGroup(EventEntity eventEntity);
	
	String getMaxId();
	
	boolean add(EventEntity eventEntity);
	
	boolean modify(EventEntity eventEntity);
	
	boolean remove(String evt_id);
	
	boolean removeList(EventEntity eventEntity);
}
