package com.mycompany.myschool.web.persistent.mapper.customer;

import java.util.List;

import com.mycompany.myschool.web.entity.customer.CustomerSub2Entity;

public interface CustomerSub2Mapper {
	List<CustomerSub2Entity> select(CustomerSub2Entity schoolChargerEntity);
	
	CustomerSub2Entity selectOne(CustomerSub2Entity schoolChargerEntity);
	
	CustomerSub2Entity selectBySchoolId(String school_id);
	
	int insert(CustomerSub2Entity schoolChargerEntity);
	
	int update(CustomerSub2Entity schoolChargerEntity);

	int delete(String school_id);
	
	int deleteList(CustomerSub2Entity schoolChargerEntity);
}
