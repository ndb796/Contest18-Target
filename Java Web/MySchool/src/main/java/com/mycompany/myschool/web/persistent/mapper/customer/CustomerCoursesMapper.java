package com.mycompany.myschool.web.persistent.mapper.customer;

import java.util.List;

import com.mycompany.myschool.web.entity.customer.CustomerCoursesEntity;

public interface CustomerCoursesMapper {

	CustomerCoursesEntity selectOne(int idx);

	List<CustomerCoursesEntity> selectByBranchId(String sb_id);

	int insert(CustomerCoursesEntity entity);

	int update(CustomerCoursesEntity entity);

	int delete(String sb_id);

}