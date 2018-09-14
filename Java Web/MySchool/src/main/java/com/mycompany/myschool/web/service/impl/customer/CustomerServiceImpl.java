package com.mycompany.myschool.web.service.impl.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.myschool.web.entity.customer.CustomerEntity;
import com.mycompany.myschool.web.persistent.mapper.customer.CustomerMapper;
import com.mycompany.myschool.web.service.customer.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private CustomerMapper customerMapper;
	
	@Override
	public List<CustomerEntity> list(CustomerEntity schoolEntity) {
		return customerMapper.select(schoolEntity);
	}
	
	@Override
	public List<CustomerEntity> listForFile(CustomerEntity schoolEntity) {
		return customerMapper.selectForFile(schoolEntity);
	}

	@Override
	public CustomerEntity get(String school_id) {
		return customerMapper.selectOne(school_id);
	}

	@Override
	public int getBranchCount(String school_id) {
		return customerMapper.selectBranchCount(school_id);
	}
	
	@Override
	public String getMaxId() {
		return customerMapper.selectMaxId();
	}
	
	@Override
	public boolean add(CustomerEntity schoolEntity) {
		return customerMapper.insert(schoolEntity) > 0 ? true : false;
	}

	@Override
	public boolean modify(CustomerEntity schoolEntity) {
		return customerMapper.update(schoolEntity) > 0 ? true : false;
	}

	@Override
	public boolean remove(String email) {
		return customerMapper.delete(email) > 0 ? true : false;
	}

	@Override
	public boolean removeList(CustomerEntity schoolEntity) {
		return customerMapper.deleteList(schoolEntity) > 0 ? true : false;
	}
}
