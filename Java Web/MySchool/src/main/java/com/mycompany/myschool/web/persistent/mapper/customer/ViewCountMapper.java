package com.mycompany.myschool.web.persistent.mapper.customer;

import java.util.List;

import com.mycompany.myschool.web.entity.customer.ViewCountEntity;

public interface ViewCountMapper {
	List<ViewCountEntity> select(ViewCountEntity viewCountEntity);
	
	ViewCountEntity selectOne(String sb_id);
	
	int insert(ViewCountEntity viewCountEntity);
	
	int updateViewCnt(ViewCountEntity viewCountEntity);
	
	int updateRealCnt(ViewCountEntity viewCountEntity);

	int delete(String school_id);
}
