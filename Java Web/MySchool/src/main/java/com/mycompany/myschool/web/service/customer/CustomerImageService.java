package com.mycompany.myschool.web.service.customer;

import java.util.List;

import com.mycompany.myschool.web.entity.customer.CustomerImageEntity;

public interface CustomerImageService {
	List<CustomerImageEntity> list(CustomerImageEntity schoolImageEntity);
	
	List<CustomerImageEntity> listBySchoolId(String school_id);
	
	boolean add(CustomerImageEntity schoolImageEntity);
	
	boolean modify(CustomerImageEntity schoolImageEntity);
	
	boolean remove(String school_id);
	
	boolean removeList(CustomerImageEntity schoolImageEntity);
}
