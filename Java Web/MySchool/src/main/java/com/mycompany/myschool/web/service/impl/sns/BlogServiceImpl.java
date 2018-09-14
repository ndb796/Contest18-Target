package com.mycompany.myschool.web.service.impl.sns;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.myschool.web.entity.admin.MainEntity;
import com.mycompany.myschool.web.entity.sns.BlogEntity;
import com.mycompany.myschool.web.entity.vo.BlogVo;
import com.mycompany.myschool.web.persistent.mapper.sns.BlogMapper;
import com.mycompany.myschool.web.service.sns.BlogService;

@Service
public class BlogServiceImpl implements BlogService {
	@Autowired
	private BlogMapper blogMapper;
	
	@Override
	public List<BlogVo> list(BlogEntity blogEntity) {
		return blogMapper.select(blogEntity);
	}
	
	@Override
	public List<BlogVo> listPaging(BlogEntity blogEntity) {
		return blogMapper.selectPaging(blogEntity);
	}

	@Override
	public List<BlogVo> listFromMain(Map<String, Object> maps) {
		return blogMapper.selectForList(maps);
	}

	@Override
	public BlogVo get(Map<String, Object> maps) {
		return blogMapper.selectOne(maps);
	}

	@Override
	public boolean add(BlogEntity blogEntity) {
		return blogMapper.insert(blogEntity) > 0 ? true : false;
	}

	@Override
	public boolean modify(BlogEntity blogEntity) {
		return blogMapper.update(blogEntity) > 0 ? true : false;
	}

	@Override
	public boolean remove(BlogEntity blogEntity) {
		return blogMapper.delete(blogEntity) > 0 ? true : false;
	}

	@Override
	public boolean removeOne(int idx) {
		return blogMapper.deleteOne(idx) > 0 ? true : false;
	}

	@Override
	public int countFromMain(Map<String, Object> maps) {
		return blogMapper.countForList(maps);
	}

	@Override
	public MainEntity getWeekDate() {
		return blogMapper.selectWeekDate();
	}

	@Override
	public int countBlogThisWeek(Map<String, Object> maps) {
		return blogMapper.countBlogThisWeek(maps);
	}

	@Override
	public MainEntity getNewBlog() {
		return blogMapper.selectNewBlog();
	}
}
