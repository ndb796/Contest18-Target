package com.mycompany.myschool.web.service.impl.sns;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.myschool.web.entity.sns.MediaEntity;
import com.mycompany.myschool.web.persistent.mapper.sns.MediaMapper;
import com.mycompany.myschool.web.service.sns.MediaService;

@Service
public class MediaServiceImpl implements MediaService {
	@Autowired
	private MediaMapper mediaMapper;
	
	@Override
	public List<MediaEntity> list(MediaEntity mediaEntity) {
		return mediaMapper.select(mediaEntity);
	}

	@Override
	public MediaEntity get(MediaEntity mediaEntity) {
		return mediaMapper.selectOne(mediaEntity);
	}

	@Override
	public List<MediaEntity> listByBoard(String board_id) {
		return mediaMapper.selectByBoard(board_id);
	}

	@Override
	public boolean add(MediaEntity mediaEntity) {
		return mediaMapper.insert(mediaEntity) > 0 ? true : false;
	}

	@Override
	public boolean modify(MediaEntity mediaEntity) {
		return mediaMapper.update(mediaEntity) > 0 ? true : false;
	}

	@Override
	public boolean remove(int idx) {
		return mediaMapper.delete(idx) > 0 ? true : false;
	}

	@Override
	public boolean removeList(MediaEntity mediaEntity) {
		return mediaMapper.deleteList(mediaEntity) > 0 ? true : false;
	}
}
