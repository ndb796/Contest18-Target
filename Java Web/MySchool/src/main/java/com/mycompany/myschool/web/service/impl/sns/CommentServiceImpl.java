package com.mycompany.myschool.web.service.impl.sns;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.myschool.web.entity.sns.CommentEntity;
import com.mycompany.myschool.web.persistent.mapper.sns.CommentMapper;
import com.mycompany.myschool.web.service.sns.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	private CommentMapper commentMapper;
	
	@Override
	public List<CommentEntity> list(CommentEntity commentEntity) {
		return commentMapper.select(commentEntity);
	}

	@Override
	public CommentEntity get(CommentEntity commentEntity) {
		return commentMapper.selectOne(commentEntity);
	}

	@Override
	public List<CommentEntity> listByBoard(String board_id) {
		return commentMapper.selectByBoard(board_id);
	}

	@Override
	public List<CommentEntity> listByStudent(String student_id) {
		return commentMapper.selectByStudent(student_id);
	}

	@Override
	public boolean add(CommentEntity commentEntity) {
		return commentMapper.insert(commentEntity) > 0 ? true : false;
	}

	@Override
	public boolean modify(CommentEntity commentEntity) {
		return commentMapper.update(commentEntity) > 0 ? true : false;
	}

	@Override
	public boolean remove(int idx) {
		return commentMapper.delete(idx) > 0 ? true : false;
	}

	@Override
	public boolean removeList(CommentEntity commentEntity) {
		return commentMapper.deleteList(commentEntity) > 0 ? true : false;
	}
}
