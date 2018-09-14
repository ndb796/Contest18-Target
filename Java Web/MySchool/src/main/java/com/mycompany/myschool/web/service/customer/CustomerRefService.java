package com.mycompany.myschool.web.service.customer;

import java.util.List;

import com.mycompany.myschool.web.entity.customer.CustomerRefEntity;

public interface CustomerRefService {

	CustomerRefEntity getOne(String key);

	List<CustomerRefEntity> getAll(CustomerRefEntity entity);

	boolean add(CustomerRefEntity entity);

	boolean modify(CustomerRefEntity entity);

	boolean remove(CustomerRefEntity entity);

}