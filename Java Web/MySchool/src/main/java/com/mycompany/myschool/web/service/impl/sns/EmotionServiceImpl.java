package com.mycompany.myschool.web.service.impl.sns;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.myschool.web.entity.sns.EmotionEntity;
import com.mycompany.myschool.web.persistent.mapper.sns.EmotionMapper;
import com.mycompany.myschool.web.service.sns.EmotionService;

@Service
public class EmotionServiceImpl implements EmotionService {
	@Autowired
	private EmotionMapper emotionMapper;
	
	@Override
	public List<EmotionEntity> list(EmotionEntity emotionEntity) {
		return emotionMapper.select(emotionEntity);
	}

	@Override
	public EmotionEntity get(EmotionEntity emotionEntity) {
		return emotionMapper.selectOne(emotionEntity);
	}

	@Override
	public EmotionEntity getByBoard(String board_id) {
		return emotionMapper.selectByBoard(board_id);
	}

	@Override
	public boolean add(EmotionEntity emotionEntity) {
		return emotionMapper.insert(emotionEntity) > 0 ? true : false;
	}

	@Override
	public boolean modify(EmotionEntity emotionEntity) {
		return emotionMapper.update(emotionEntity) > 0 ? true : false;
	}

	@Override
	public boolean remove(int idx) {
		return emotionMapper.delete(idx) > 0 ? true : false;
	}

	@Override
	public boolean removeList(EmotionEntity emotionEntity) {
		return emotionMapper.deleteList(emotionEntity) > 0 ? true : false;
	}
}
