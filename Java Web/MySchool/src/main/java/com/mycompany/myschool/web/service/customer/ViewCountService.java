package com.mycompany.myschool.web.service.customer;

import java.util.List;

import com.mycompany.myschool.web.entity.customer.ViewCountEntity;

public interface ViewCountService {
	List<ViewCountEntity> list(ViewCountEntity viewCountEntity);
	
	ViewCountEntity get(String sb_id);
	
	boolean add(ViewCountEntity viewCountEntity);
	
	boolean modifyViewCnt(ViewCountEntity viewCountEntity);
	
	boolean modifyRealCnt(ViewCountEntity viewCountEntity);
	
	boolean remove(String school_id);
}
