package com.mycompany.myschool.web.persistent.mapper.common;

import java.util.List;

import com.mycompany.myschool.web.entity.common.SpecificMstEntity;

public interface SpecificMstMapper {
	List<SpecificMstEntity> select(SpecificMstEntity specificMstEntity);
	
	List<SpecificMstEntity> selectByCountry(String country);
	
	List<SpecificMstEntity> selectByCategory(String category);
	
	SpecificMstEntity selectByCode(String code);
	
	List<SpecificMstEntity> selectByType1(String type1);
	List<SpecificMstEntity> selectByType2(String type2);
	List<SpecificMstEntity> selectByType3(String type3);
	
	int insert(SpecificMstEntity specificMstEntity);
	
	int update(SpecificMstEntity specificMstEntity);

	int delete(String code);
	
	int deleteList(SpecificMstEntity specificMstEntity);
}
