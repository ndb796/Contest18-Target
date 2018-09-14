package com.mycompany.myschool.web.service.sns;

import java.util.List;

import com.mycompany.myschool.web.entity.sns.CommentEntity;

public interface CommentService {
	List<CommentEntity> list(CommentEntity commentEntity);
	
	CommentEntity get(CommentEntity commentEntity);
	
	List<CommentEntity> listByBoard(String board_id);
	
	List<CommentEntity> listByStudent(String student_id);
	
	boolean add(CommentEntity commentEntity);
	
	boolean modify(CommentEntity commentEntity);
	
	boolean remove(int idx);
	
	boolean removeList(CommentEntity commentEntity);
}
