package com.mycompany.myschool.web.persistent.mapper.sns;

import java.util.List;

import com.mycompany.myschool.web.entity.sns.EmotionEntity;

public interface EmotionMapper {
	List<EmotionEntity> select(EmotionEntity emotionEntity);
	
	EmotionEntity selectOne(EmotionEntity emotionEntity);
	
	EmotionEntity selectByBoard(String board_id);
	
	/*String selectMaxId();*/
	
	int insert(EmotionEntity emotionEntity);
	
	int update(EmotionEntity emotionEntity);

	int delete(int idx);
	
	int deleteList(EmotionEntity emotionEntity);
}
