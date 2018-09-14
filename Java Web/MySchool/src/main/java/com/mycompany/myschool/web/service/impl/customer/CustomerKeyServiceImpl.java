package com.mycompany.myschool.web.service.impl.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.myschool.web.entity.customer.CustomerKeyEntity;
import com.mycompany.myschool.web.persistent.mapper.customer.CustomerKeyMapper;
import com.mycompany.myschool.web.service.customer.CustomerKeyService;

@Service
public class CustomerKeyServiceImpl implements CustomerKeyService {
	@Autowired
	private CustomerKeyMapper customerKeyMapper;
	
	@Override
	public List<CustomerKeyEntity> list() {
		return customerKeyMapper.select();
	}

	@Override
	public List<CustomerKeyEntity> get(String sb_id) {
		return customerKeyMapper.selectOne(sb_id);
	}

	@Override
	public List<CustomerKeyEntity> listByKeycode(String keycode) {
		return customerKeyMapper.selectByKeycode(keycode);
	}

	@Override
	public boolean add(CustomerKeyEntity schoolKeyEntity) {
		return customerKeyMapper.insert(schoolKeyEntity) > 0 ? true : false;
	}

	@Override
	public boolean modify(CustomerKeyEntity schoolKeyEntity) {
		return customerKeyMapper.update(schoolKeyEntity) > 0 ? true : false;
	}

	@Override
	public boolean remove(String sb_id) {
		return customerKeyMapper.delete(sb_id) > 0 ? true : false;
	}

	@Override
	public boolean removeMulti(CustomerKeyEntity schoolKeyEntity) {
		return customerKeyMapper.deleteMulti(schoolKeyEntity) > 0 ? true : false;
	}
}
