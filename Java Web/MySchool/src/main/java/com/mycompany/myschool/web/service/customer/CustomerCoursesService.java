package com.mycompany.myschool.web.service.customer;

import java.util.List;

import com.mycompany.myschool.web.entity.customer.CustomerCoursesEntity;

public interface CustomerCoursesService {

	CustomerCoursesEntity getOne(int idx);

	List<CustomerCoursesEntity> getByBranchId(String sb_id);

	boolean add(CustomerCoursesEntity entity);

	boolean modify(CustomerCoursesEntity entity);

	boolean remove(String sb_id);

}