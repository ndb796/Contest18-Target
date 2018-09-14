package com.mycompany.myschool.web.service.sns;

import java.util.List;

import com.mycompany.myschool.web.entity.sns.MediaEntity;

public interface MediaService {
	List<MediaEntity> list(MediaEntity mediaEntity);
	
	MediaEntity get(MediaEntity mediaEntity);
	
	List<MediaEntity> listByBoard(String board_id);
	
	boolean add(MediaEntity mediaEntity);
	
	boolean modify(MediaEntity mediaEntity);
	
	boolean remove(int idx);
	
	boolean removeList(MediaEntity mediaEntity);
}
