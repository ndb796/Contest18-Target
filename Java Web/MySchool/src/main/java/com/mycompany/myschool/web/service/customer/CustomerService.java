package com.mycompany.myschool.web.service.customer;

import java.util.List;

import com.mycompany.myschool.web.entity.customer.CustomerEntity;
import com.mycompany.myschool.web.entity.vo.SchoolInfoVo;
import com.mycompany.myschool.web.entity.vo.SchoolListVo;

public interface CustomerService {
	List<CustomerEntity> list(CustomerEntity schoolEntity);
	
	List<CustomerEntity> listForFile(CustomerEntity schoolEntity);
	
	CustomerEntity get(String school_id);
	
	int getBranchCount(String school_id);
	
	String getMaxId();
	
	boolean add(CustomerEntity schoolEntity);
	
	boolean modify(CustomerEntity schoolEntity);
	
	boolean remove(String school_id);
	
	boolean removeList(CustomerEntity schoolEntity);
}
