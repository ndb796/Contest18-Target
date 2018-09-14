package com.mycompany.myschool.web.service.sns;

import java.util.List;
import java.util.Map;

import com.mycompany.myschool.web.entity.admin.MainEntity;
import com.mycompany.myschool.web.entity.sns.BlogEntity;
import com.mycompany.myschool.web.entity.vo.BlogVo;

public interface BlogService {
	List<BlogVo> list(BlogEntity blogEntity);
	
	List<BlogVo> listPaging(BlogEntity blogEntity);
	
	List<BlogVo> listFromMain(Map<String, Object> maps);
	
	BlogVo get(Map<String, Object> maps);
	
	int countFromMain(Map<String, Object> maps);
	
	boolean add(BlogEntity blogEntity);
	
	boolean modify(BlogEntity blogEntity);

	boolean remove(BlogEntity blogEntity);
	
	boolean removeOne(int idx);
	
	MainEntity getWeekDate();
	
	int countBlogThisWeek(Map<String, Object> maps);
	
	MainEntity getNewBlog();
}
