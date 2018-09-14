package com.mycompany.myschool.web.persistent.mapper.sns;

import java.util.List;

import com.mycompany.myschool.web.entity.sns.BoardEntity;

public interface BoardMapper {
	List<BoardEntity> select(BoardEntity boardEntity);
	
	BoardEntity selectOne(BoardEntity boardEntity);
	
	List<BoardEntity> selectByStudent(String student_id);
	
	List<BoardEntity> selectByGroup(BoardEntity boardEntity);
	
	String selectMaxId();
	
	int insert(BoardEntity boardEntity);
	
	int update(BoardEntity boardEntity);

	int delete(String board_id);
	
	int deleteList(BoardEntity boardEntity);
}
