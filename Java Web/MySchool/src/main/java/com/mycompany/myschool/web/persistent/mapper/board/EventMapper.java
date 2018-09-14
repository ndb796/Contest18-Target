package com.mycompany.myschool.web.persistent.mapper.board;

import java.util.List;

import com.mycompany.myschool.web.entity.board.EventEntity;

public interface EventMapper {
	List<EventEntity> select(EventEntity eventEntity);
	
	List<EventEntity> selectInRange(EventEntity eventEntity);
	
	int selectCountInRange(EventEntity eventEntity);
	
	EventEntity selectOne(EventEntity eventEntity);
	
	List<EventEntity> selectByStudent(String student_id);
	
	List<EventEntity> selectByGroup(EventEntity eventEntity);
	
	String selectMaxId();
	
	int insert(EventEntity eventEntity);
	
	int update(EventEntity eventEntity);

	int delete(String evt_id);
	
	int deleteList(EventEntity eventEntity);
}
