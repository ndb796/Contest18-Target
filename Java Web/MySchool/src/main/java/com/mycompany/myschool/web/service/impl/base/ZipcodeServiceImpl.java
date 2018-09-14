package com.mycompany.myschool.web.service.impl.base;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.myschool.web.entity.base.ZipcodeEntity;
import com.mycompany.myschool.web.persistent.mapper.base.ZipcodeMapper;
import com.mycompany.myschool.web.service.base.ZipcodeService;

@Service
public class ZipcodeServiceImpl implements ZipcodeService {
	@Autowired
	private ZipcodeMapper zipcodeMapper;
	
	@Override
	public List<ZipcodeEntity> list(ZipcodeEntity zipcodeentity) {
		return zipcodeMapper.select(zipcodeentity);
	}

	@Override
	public ZipcodeEntity findByZipcode(String zipcode) {
		return zipcodeMapper.selectByZipcode(zipcode);
	}
}
