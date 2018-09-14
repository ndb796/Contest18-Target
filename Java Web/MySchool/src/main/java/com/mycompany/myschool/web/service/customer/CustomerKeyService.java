package com.mycompany.myschool.web.service.customer;

import java.util.List;

import com.mycompany.myschool.web.entity.customer.CustomerKeyEntity;

public interface CustomerKeyService {
	List<CustomerKeyEntity> list();
	
	List<CustomerKeyEntity> get(String sb_id);
	
	List<CustomerKeyEntity> listByKeycode(String keycode);
	
	boolean add(CustomerKeyEntity schoolKeyEntity);
	
	boolean modify(CustomerKeyEntity schoolKeyEntity);
	
	boolean remove(String sb_id);
	
	boolean removeMulti(CustomerKeyEntity schoolKeyEntity);
}
