package com.mycompany.myschool.web.persistent.mapper.board;

import java.util.List;
import java.util.Map;

import com.mycompany.myschool.web.entity.admin.MainEntity;
import com.mycompany.myschool.web.entity.board.NewsEntity;

public interface NewsMapper {
	List<NewsEntity> select(NewsEntity newsEntity);
	
	NewsEntity selectOne(int idx);
	
	int insert(NewsEntity newsEntity);
	
	int update(NewsEntity newsEntity);

	int updateForDelete(NewsEntity newsEntity);
	
	int delete(Map<String, Object> maps);
	
	MainEntity selectNewNews();
}
