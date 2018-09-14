package com.mycompany.myschool.web.service.sns;

import java.util.List;

import com.mycompany.myschool.web.entity.sns.EmotionEntity;

public interface EmotionService {
	List<EmotionEntity> list(EmotionEntity emotionEntity);
	
	EmotionEntity get(EmotionEntity emotionEntity);
	
	EmotionEntity getByBoard(String board_id);
	
	boolean add(EmotionEntity emotionEntity);
	
	boolean modify(EmotionEntity emotionEntity);
	
	boolean remove(int idx);
	
	boolean removeList(EmotionEntity emotionEntity);
}
