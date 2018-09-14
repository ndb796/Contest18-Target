package com.mycompany.myschool.web.persistent.mapper.customer;

import java.util.List;

import com.mycompany.myschool.web.entity.customer.CustomerEntity;
import com.mycompany.myschool.web.entity.vo.SchoolInfoVo;
import com.mycompany.myschool.web.entity.vo.SchoolListVo;

public interface CustomerMapper {
	List<CustomerEntity> select(CustomerEntity schoolEntity);
	
	List<CustomerEntity> selectForFile(CustomerEntity schoolEntity);
	
	CustomerEntity selectOne(String school_id);

	int selectBranchCount(String school_id);
	
	String selectMaxId();
	
	int insert(CustomerEntity schoolEntity);
	
	int update(CustomerEntity schoolEntity);

	int delete(String school_id);
	
	int deleteList(CustomerEntity schoolEntity);
}
