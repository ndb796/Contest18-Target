package com.mycompany.myschool.web.service.impl.board;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.myschool.web.entity.admin.MainEntity;
import com.mycompany.myschool.web.entity.board.NewsEntity;
import com.mycompany.myschool.web.persistent.mapper.board.NewsMapper;
import com.mycompany.myschool.web.service.board.NewsService;

@Service
public class NewsServiceImpl implements NewsService {
	@Autowired
	private NewsMapper newsMapper;
	
	@Override
	public List<NewsEntity> list(NewsEntity newsEntity) {
		return newsMapper.select(newsEntity);
	}

	@Override
	public NewsEntity get(int idx) {
		return newsMapper.selectOne(idx);
	}

	@Override
	public boolean add(NewsEntity newsEntity) {
		return newsMapper.insert(newsEntity) > 0 ? true : false;
	}

	@Override
	public boolean modify(NewsEntity newsEntity) {
		return newsMapper.update(newsEntity) > 0 ? true : false;
	}

	@Override
	public boolean remove(Map<String, Object> maps) {
		return newsMapper.delete(maps) > 0 ? true : false;
	}
	
	@Override
	public MainEntity getNewNews() {
		return newsMapper.selectNewNews();
	}

	@Override
	public boolean modifyForRemove(NewsEntity newsEntity) {
		return newsMapper.updateForDelete(newsEntity) > 0 ? true : false;
	}	
}
