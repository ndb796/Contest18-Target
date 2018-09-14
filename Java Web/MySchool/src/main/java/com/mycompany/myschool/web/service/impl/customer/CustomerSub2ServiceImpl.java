package com.mycompany.myschool.web.service.impl.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.myschool.web.entity.customer.CustomerSub2Entity;
import com.mycompany.myschool.web.persistent.mapper.customer.CustomerSub2Mapper;
import com.mycompany.myschool.web.service.customer.CustomerSub2Service;

@Service
public class CustomerSub2ServiceImpl implements CustomerSub2Service {
	@Autowired
	private CustomerSub2Mapper customerChargerMapper;
	
	@Override
	public List<CustomerSub2Entity> list(CustomerSub2Entity schoolChargerEntity) {
		return customerChargerMapper.select(schoolChargerEntity);
	}

	@Override
	public CustomerSub2Entity get(CustomerSub2Entity schoolChargerEntity) {
		return customerChargerMapper.selectOne(schoolChargerEntity);
	}

	@Override
	public CustomerSub2Entity getBySchoolId(String school_id) {
		// TODO Auto-generated method stub
		return customerChargerMapper.selectBySchoolId(school_id);
	}

	@Override
	public boolean add(CustomerSub2Entity schoolChargerEntity) {
		return customerChargerMapper.insert(schoolChargerEntity) > 0 ? true : false;
	}

	@Override
	public boolean modify(CustomerSub2Entity schoolChargerEntity) {
		return customerChargerMapper.update(schoolChargerEntity) > 0 ? true : false;
	}

	@Override
	public boolean remove(String school_id) {
		return customerChargerMapper.delete(school_id) > 0 ? true : false;
	}

	@Override
	public boolean removeList(CustomerSub2Entity schoolChargerEntity) {
		return customerChargerMapper.deleteList(schoolChargerEntity) > 0 ? true : false;
	}
}
