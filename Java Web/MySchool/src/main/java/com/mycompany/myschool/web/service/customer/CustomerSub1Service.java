package com.mycompany.myschool.web.service.customer;

import java.util.List;

import com.mycompany.myschool.web.entity.customer.CustomerSub1Entity;

public interface CustomerSub1Service {
	List<CustomerSub1Entity> list(CustomerSub1Entity schoolDetailEntity);
	
	CustomerSub1Entity get(CustomerSub1Entity schoolDetailEntity);
	
	CustomerSub1Entity getBySchoolId(String school_id);
	
	boolean add(CustomerSub1Entity schoolDetailEntity);
	
	boolean modify(CustomerSub1Entity schoolDetailEntity);
	
	boolean remove(String school_id);
	
	boolean removeList(CustomerSub1Entity schoolDetailEntity);
}
