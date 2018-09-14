package com.mycompany.myschool.web.service.customer;

import java.util.List;

import com.mycompany.myschool.web.entity.customer.CustomerSub2Entity;

public interface CustomerSub2Service {
	List<CustomerSub2Entity> list(CustomerSub2Entity schoolChargerEntity);
	
	CustomerSub2Entity get(CustomerSub2Entity schoolChargerEntity);
	
	CustomerSub2Entity getBySchoolId(String school_id);
	
	boolean add(CustomerSub2Entity schoolChargerEntity);
	
	boolean modify(CustomerSub2Entity schoolChargerEntity);
	
	boolean remove(String school_id);
	
	boolean removeList(CustomerSub2Entity schoolChargerEntity);
}
