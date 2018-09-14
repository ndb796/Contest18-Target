package com.mycompany.myschool.web.persistent.mapper.common;

import java.util.List;

import com.mycompany.myschool.web.entity.common.CodeMstEntity;

public interface CodeMstMapper {
	List<CodeMstEntity> select();
	
	List<CodeMstEntity> selectByType(String type);
	
	CodeMstEntity selectByCode(String code);
	
	List<CodeMstEntity> selectByCodeStr(String code_str);
	
	int insert(CodeMstEntity codeMstEntity);
	
	int update(CodeMstEntity codeMstEntity);

	int delete(String code);
}
