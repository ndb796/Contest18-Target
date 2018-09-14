package com.mycompany.myschool.web.service.common;

import java.util.List;

import com.mycompany.myschool.web.entity.common.CodeMstEntity;

public interface CodeMstService {
	List<CodeMstEntity> list();
	
	List<CodeMstEntity> listByType(String type);
	
	CodeMstEntity getByCode(String code);
	
	List<CodeMstEntity> listByCodeStr(String code_str);
	
	boolean add(CodeMstEntity codeMstEntity);
	
	boolean modify(CodeMstEntity codeMstEntity);
	
	boolean remove(String code);
}
