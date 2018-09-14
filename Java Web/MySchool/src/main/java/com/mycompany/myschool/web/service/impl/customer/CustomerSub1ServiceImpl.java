package com.mycompany.myschool.web.service.impl.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.myschool.web.entity.customer.CustomerSub1Entity;
import com.mycompany.myschool.web.persistent.mapper.customer.CustomerSub1Mapper;
import com.mycompany.myschool.web.service.customer.CustomerSub1Service;

@Service
public class CustomerSub1ServiceImpl implements CustomerSub1Service {
	@Autowired
	private CustomerSub1Mapper customerDetailMapper;
	
	@Override
	public List<CustomerSub1Entity> list(CustomerSub1Entity schoolDetailEntity) {
		return customerDetailMapper.select(schoolDetailEntity);
	}

	@Override
	public CustomerSub1Entity get(CustomerSub1Entity schoolDetailEntity) {
		return customerDetailMapper.selectOne(schoolDetailEntity);
	}
	
	@Override
	public CustomerSub1Entity getBySchoolId(String school_id) {
		return customerDetailMapper.selectBySchoolId(school_id);
	}

	@Override
	public boolean add(CustomerSub1Entity schoolDetailEntity) {
		return customerDetailMapper.insert(schoolDetailEntity) > 0 ? true : false;
	}

	@Override
	public boolean modify(CustomerSub1Entity schoolDetailEntity) {
		return customerDetailMapper.update(schoolDetailEntity) > 0 ? true : false;
	}

	@Override
	public boolean remove(String email) {
		return customerDetailMapper.delete(email) > 0 ? true : false;
	}

	@Override
	public boolean removeList(CustomerSub1Entity schoolDetailEntity) {
		return customerDetailMapper.deleteList(schoolDetailEntity) > 0 ? true : false;
	}
}
