package com.mycompany.myschool.web.persistent.mapper.customer;

import java.util.List;

import com.mycompany.myschool.web.entity.customer.CustomerRefEntity;

public interface CustomerRefMapper {

	CustomerRefEntity selectOne(String key);

	List<CustomerRefEntity> selectAll(CustomerRefEntity entity);

	int insert(CustomerRefEntity entity);

	int update(CustomerRefEntity entity);

	int delete(CustomerRefEntity entity);

}