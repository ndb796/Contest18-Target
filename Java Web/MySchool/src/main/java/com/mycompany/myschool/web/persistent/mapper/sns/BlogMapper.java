package com.mycompany.myschool.web.persistent.mapper.sns;

import java.util.List;
import java.util.Map;

import com.mycompany.myschool.web.entity.admin.MainEntity;
import com.mycompany.myschool.web.entity.sns.BlogEntity;
import com.mycompany.myschool.web.entity.vo.BlogVo;

public interface BlogMapper {
	List<BlogVo> select(BlogEntity blogEntity);
	
	List<BlogVo> selectPaging(BlogEntity blogEntity);
	
	List<BlogVo> selectForList(Map<String, Object> maps);
	
	BlogVo selectOne(Map<String, Object> maps);
	
	int countForList(Map<String, Object> maps);
	
	int insert(BlogEntity blogEntity);
	
	int update(BlogEntity blogEntity);

	int delete(BlogEntity blogEntity);
	
	int deleteOne(int idx);
	
	MainEntity selectWeekDate();
	
	int countBlogThisWeek(Map<String, Object> maps);
	
	MainEntity selectNewBlog();
}
