package com.mycompany.myschool.web.service.impl.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.myschool.web.entity.customer.CustomerCoursesEntity;
import com.mycompany.myschool.web.persistent.mapper.customer.CustomerCoursesMapper;
import com.mycompany.myschool.web.service.customer.CustomerCoursesService;

@Service
public class CustomerCoursesServiceImpl implements CustomerCoursesService {
	@Autowired
	private CustomerCoursesMapper customerCoursesMapper;
	
	@Override
	public CustomerCoursesEntity getOne(int idx) {
		return customerCoursesMapper.selectOne(idx);
	}	
	
	@Override
	public List<CustomerCoursesEntity> getByBranchId(String sb_id) {
		return customerCoursesMapper.selectByBranchId(sb_id);
	}	
	
	@Override
	public boolean add(CustomerCoursesEntity entity) {
		return customerCoursesMapper.insert(entity) > 0 ? true : false;
	}	
	
	@Override
	public boolean modify(CustomerCoursesEntity entity) {
		return customerCoursesMapper.update(entity) > 0 ? true : false;
	}	
	
	@Override
	public boolean remove(String sb_id) {
		return customerCoursesMapper.delete(sb_id) > 0 ? true : false;
	}
}