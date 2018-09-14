package com.mycompany.myschool.web.persistent.mapper.customer;

import java.util.List;

import com.mycompany.myschool.web.entity.customer.CustomerImageEntity;

public interface CustomerImageMapper {
	List<CustomerImageEntity> select(CustomerImageEntity schoolImageEntity);
	
	List<CustomerImageEntity> selectBySchoolId(String school_id);
	
	int insert(CustomerImageEntity schoolImageEntity);
	
	int update(CustomerImageEntity schoolImageEntity);

	int delete(String school_id);
	
	int deleteList(CustomerImageEntity schoolImageEntity);
}
