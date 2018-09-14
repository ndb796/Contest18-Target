package com.mycompany.myschool.web.persistent.mapper.base;

import java.util.List;

import com.mycompany.myschool.web.entity.base.ZipcodeEntity;

public interface ZipcodeMapper {
	List<ZipcodeEntity> select(ZipcodeEntity zipcodeentity);
	
	ZipcodeEntity selectByZipcode(String zipcode);
	
}
