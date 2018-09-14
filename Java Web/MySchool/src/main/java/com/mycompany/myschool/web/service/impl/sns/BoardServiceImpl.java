package com.mycompany.myschool.web.service.impl.sns;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.myschool.web.entity.sns.BoardEntity;
import com.mycompany.myschool.web.persistent.mapper.sns.BoardMapper;
import com.mycompany.myschool.web.service.sns.BoardService;

@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	private BoardMapper boardMapper;
	
	@Override
	public List<BoardEntity> list(BoardEntity boardEntity) {
		return boardMapper.select(boardEntity);
	}

	@Override
	public BoardEntity get(BoardEntity boardEntity) {
		return boardMapper.selectOne(boardEntity);
	}

	@Override
	public List<BoardEntity> listByStudent(String student_id) {
		return boardMapper.selectByStudent(student_id);
	}

	@Override
	public List<BoardEntity> listByGroup(BoardEntity boardEntity) {
		return boardMapper.selectByGroup(boardEntity);
	}

	@Override
	public String getMaxId() {
		return boardMapper.selectMaxId();
	}
	
	@Override
	public boolean add(BoardEntity boardEntity) {
		return boardMapper.insert(boardEntity) > 0 ? true : false;
	}

	@Override
	public boolean modify(BoardEntity boardEntity) {
		return boardMapper.update(boardEntity) > 0 ? true : false;
	}

	@Override
	public boolean remove(String board_id) {
		return boardMapper.delete(board_id) > 0 ? true : false;
	}

	@Override
	public boolean removeList(BoardEntity boardEntity) {
		return boardMapper.deleteList(boardEntity) > 0 ? true : false;
	}
	
	
}
