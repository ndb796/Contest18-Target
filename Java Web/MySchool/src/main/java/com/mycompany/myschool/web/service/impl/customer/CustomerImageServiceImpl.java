package com.mycompany.myschool.web.service.impl.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.myschool.web.entity.customer.CustomerImageEntity;
import com.mycompany.myschool.web.persistent.mapper.customer.CustomerImageMapper;
import com.mycompany.myschool.web.service.customer.CustomerImageService;

@Service
public class CustomerImageServiceImpl implements CustomerImageService {
	@Autowired
	private CustomerImageMapper customerImageMapper;
	
	@Override
	public List<CustomerImageEntity> list(CustomerImageEntity schoolImageEntity) {
		return customerImageMapper.select(schoolImageEntity);
	}

	@Override
	public List<CustomerImageEntity> listBySchoolId(String school_id) {
		return customerImageMapper.selectBySchoolId(school_id);
	}

	@Override
	public boolean add(CustomerImageEntity schoolImageEntity) {
		return customerImageMapper.insert(schoolImageEntity) > 0 ? true : false;
	}

	@Override
	public boolean modify(CustomerImageEntity schoolImageEntity) {
		return customerImageMapper.update(schoolImageEntity) > 0 ? true : false;
	}

	@Override
	public boolean remove(String email) {
		return customerImageMapper.delete(email) > 0 ? true : false;
	}

	@Override
	public boolean removeList(CustomerImageEntity schoolImageEntity) {
		return customerImageMapper.deleteList(schoolImageEntity) > 0 ? true : false;
	}
}
