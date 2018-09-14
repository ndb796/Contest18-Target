package com.mycompany.myschool.web.service.base;

import java.util.List;

import com.mycompany.myschool.web.entity.base.ZipcodeEntity;

public interface ZipcodeService {
	List<ZipcodeEntity> list(ZipcodeEntity zipcodeentity);
	
	ZipcodeEntity findByZipcode(String zipcode);
}
