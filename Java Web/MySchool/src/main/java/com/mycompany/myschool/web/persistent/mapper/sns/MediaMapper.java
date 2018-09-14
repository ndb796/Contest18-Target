package com.mycompany.myschool.web.persistent.mapper.sns;

import java.util.List;

import com.mycompany.myschool.web.entity.sns.MediaEntity;

public interface MediaMapper {
	List<MediaEntity> select(MediaEntity mediaEntity);
	
	MediaEntity selectOne(MediaEntity mediaEntity);
	
	List<MediaEntity> selectByBoard(String board_id);
	
	/*String selectMaxId();*/
	
	int insert(MediaEntity mediaEntity);
	
	int update(MediaEntity mediaEntity);

	int delete(int idx);
	
	int deleteList(MediaEntity mediaEntity);
}
