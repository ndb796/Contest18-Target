package com.mycompany.myschool.web.service.board;

import java.util.List;
import java.util.Map;

import com.mycompany.myschool.web.entity.admin.MainEntity;
import com.mycompany.myschool.web.entity.board.NewsEntity;

public interface NewsService {
	List<NewsEntity> list(NewsEntity newsEntity);
	
	NewsEntity get(int idx);
	
	boolean add(NewsEntity newsEntity);
	
	boolean modify(NewsEntity newsEntity);
	
	boolean modifyForRemove(NewsEntity newsEntity);
	
	boolean remove(Map<String, Object> maps);
	
	MainEntity getNewNews();
}
