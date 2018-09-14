package com.mycompany.myschool.web.service.impl.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.myschool.web.entity.customer.CustomerRefEntity;
import com.mycompany.myschool.web.persistent.mapper.customer.CustomerRefMapper;
import com.mycompany.myschool.web.service.customer.CustomerRefService;

@Service
public class CustomerRefServiceImpl implements CustomerRefService {
	@Autowired
	private CustomerRefMapper customerRefMapper;

	@Override
	public CustomerRefEntity getOne(String key) {
		return customerRefMapper.selectOne(key);
	}	
	
	@Override
	public List<CustomerRefEntity> getAll(CustomerRefEntity entity) {
		return customerRefMapper.selectAll(entity);
	}	
	
	@Override
	public boolean add(CustomerRefEntity entity) {
		return customerRefMapper.insert(entity) > 0 ? true : false;
	}	
	
	@Override
	public boolean modify(CustomerRefEntity entity) {
		return customerRefMapper.update(entity) > 0 ? true : false;
	}	
	
	@Override
	public boolean remove(CustomerRefEntity entity) {
		return customerRefMapper.delete(entity) > 0 ? true : false;
	}
}