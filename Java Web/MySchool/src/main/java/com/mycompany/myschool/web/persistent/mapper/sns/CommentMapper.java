package com.mycompany.myschool.web.persistent.mapper.sns;

import java.util.List;

import com.mycompany.myschool.web.entity.sns.CommentEntity;

public interface CommentMapper {
	List<CommentEntity> select(CommentEntity commentEntity);
	
	CommentEntity selectOne(CommentEntity commentEntity);
	
	List<CommentEntity> selectByBoard(String board_id);
	
	List<CommentEntity> selectByStudent(String student_id);
	
	/*String selectMaxId();*/
	
	int insert(CommentEntity commentEntity);
	
	int update(CommentEntity commentEntity);

	int delete(int idx);
	
	int deleteList(CommentEntity commentEntity);
}
