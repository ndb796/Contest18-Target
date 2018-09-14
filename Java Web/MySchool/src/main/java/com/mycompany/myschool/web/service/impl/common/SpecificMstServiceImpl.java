package com.mycompany.myschool.web.service.impl.common;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.myschool.web.entity.common.SpecificMstEntity;
import com.mycompany.myschool.web.persistent.mapper.common.SpecificMstMapper;
import com.mycompany.myschool.web.service.common.SpecificMstService;

@Service
public class SpecificMstServiceImpl implements SpecificMstService {
	@Autowired
	private SpecificMstMapper specificMstMapper;
	
	@Override
	public List<SpecificMstEntity> list(SpecificMstEntity specificMstEntity) {
		return specificMstMapper.select(specificMstEntity);
	}

	@Override
	public List<SpecificMstEntity> listByCountry(String country) {
		return specificMstMapper.selectByCountry(country);
	}

	@Override
	public List<SpecificMstEntity> listByCategory(String category) {
		return specificMstMapper.selectByCategory(category);
	}

	@Override
	public SpecificMstEntity getByCode(String code) {
		return specificMstMapper.selectByCode(code);
	}

	@Override
	public List<SpecificMstEntity> listByType1(String type1) {
		return specificMstMapper.selectByType1(type1);
	}

	@Override
	public List<SpecificMstEntity> listByType2(String type2) {
		return specificMstMapper.selectByType1(type2);
	}

	@Override
	public List<SpecificMstEntity> listByType3(String type3) {
		return specificMstMapper.selectByType1(type3);
	}

	@Override
	public boolean add(SpecificMstEntity specificMstEntity) {
		return specificMstMapper.insert(specificMstEntity) > 0 ? true : false;
	}

	@Override
	public boolean modify(SpecificMstEntity specificMstEntity) {
		return specificMstMapper.update(specificMstEntity) > 0 ? true : false;
	}

	@Override
	public boolean remove(String email) {
		return specificMstMapper.delete(email) > 0 ? true : false;
	}

	@Override
	public boolean removeList(SpecificMstEntity specificMstEntity) {
		return specificMstMapper.deleteList(specificMstEntity) > 0 ? true : false;
	}
}
