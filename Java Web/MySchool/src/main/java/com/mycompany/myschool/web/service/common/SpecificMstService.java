package com.mycompany.myschool.web.service.common;

import java.util.List;

import com.mycompany.myschool.web.entity.common.SpecificMstEntity;

public interface SpecificMstService {
	List<SpecificMstEntity> list(SpecificMstEntity specificMstEntity);
	
	List<SpecificMstEntity> listByCountry(String country);
	
	List<SpecificMstEntity> listByCategory(String category);
	
	SpecificMstEntity getByCode(String code);
	
	List<SpecificMstEntity> listByType1(String type1);
	List<SpecificMstEntity> listByType2(String type2);
	List<SpecificMstEntity> listByType3(String type3);
	
	boolean add(SpecificMstEntity specificMstEntity);
	
	boolean modify(SpecificMstEntity specificMstEntity);
	
	boolean remove(String code);
	
	boolean removeList(SpecificMstEntity specificMstEntity);
}
