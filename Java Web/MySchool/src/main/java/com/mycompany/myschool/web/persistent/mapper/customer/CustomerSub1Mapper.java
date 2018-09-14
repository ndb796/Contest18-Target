package com.mycompany.myschool.web.persistent.mapper.customer;

import java.util.List;

import com.mycompany.myschool.web.entity.customer.CustomerSub1Entity;

public interface CustomerSub1Mapper {
	List<CustomerSub1Entity> select(CustomerSub1Entity schoolDetailEntity);
	
	CustomerSub1Entity selectOne(CustomerSub1Entity schoolDetailEntity);
	
	CustomerSub1Entity selectBySchoolId(String school_id);
	
	int insert(CustomerSub1Entity schoolDetailEntity);
	
	int update(CustomerSub1Entity schoolDetailEntity);

	int delete(String school_id);
	
	int deleteList(CustomerSub1Entity schoolDetailEntity);
}
