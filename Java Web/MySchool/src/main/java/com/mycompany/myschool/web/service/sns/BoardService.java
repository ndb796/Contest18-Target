package com.mycompany.myschool.web.service.sns;

import java.util.List;

import com.mycompany.myschool.web.entity.sns.BoardEntity;

public interface BoardService {
	List<BoardEntity> list(BoardEntity boardEntity);
	
	BoardEntity get(BoardEntity boardEntity);
	
	List<BoardEntity> listByStudent(String student_id);
	
	List<BoardEntity> listByGroup(BoardEntity boardEntity);
	
	String getMaxId();
	
	boolean add(BoardEntity boardEntity);
	
	boolean modify(BoardEntity boardEntity);
	
	boolean remove(String board_id);
	
	boolean removeList(BoardEntity boardEntity);
}
