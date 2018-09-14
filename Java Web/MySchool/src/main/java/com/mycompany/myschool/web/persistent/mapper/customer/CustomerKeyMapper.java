package com.mycompany.myschool.web.persistent.mapper.customer;

import java.util.List;

import com.mycompany.myschool.web.entity.customer.CustomerBranchEntity;
import com.mycompany.myschool.web.entity.customer.CustomerKeyEntity;

public interface CustomerKeyMapper {
	List<CustomerKeyEntity> select();
	
	List<CustomerKeyEntity> selectOne(String sb_id);
	
	List<CustomerKeyEntity> selectByKeycode(String keycode);
	
	int insert(CustomerKeyEntity schoolKeyEntity);
	
	int update(CustomerKeyEntity schoolKeyEntity);

	int delete(String sb_id);
	
	int deleteMulti(CustomerKeyEntity scholleKeyEntity);
}
