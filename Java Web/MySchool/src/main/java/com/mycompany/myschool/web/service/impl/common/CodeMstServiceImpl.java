package com.mycompany.myschool.web.service.impl.common;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.myschool.web.entity.common.CodeMstEntity;
import com.mycompany.myschool.web.persistent.mapper.common.CodeMstMapper;
import com.mycompany.myschool.web.service.common.CodeMstService;

@Service
public class CodeMstServiceImpl implements CodeMstService {
	@Autowired
	private CodeMstMapper codeMstMapper;

	@Override
	public List<CodeMstEntity> list() {
		return codeMstMapper.select();
	}

	@Override
	public List<CodeMstEntity> listByType(String type) {
		return codeMstMapper.selectByType(type);
	}

	@Override
	public CodeMstEntity getByCode(String code) {
		return codeMstMapper.selectByCode(code);
	}

	@Override
	public List<CodeMstEntity> listByCodeStr(String code_str) {
		return codeMstMapper.selectByCodeStr(code_str);
	}

	@Override
	public boolean add(CodeMstEntity codeMstEntity) {
		return codeMstMapper.insert(codeMstEntity) > 0 ? true : false;
	}

	@Override
	public boolean modify(CodeMstEntity codeMstEntity) {
		return codeMstMapper.update(codeMstEntity) > 0 ? true : false;
	}

	@Override
	public boolean remove(String code) {
		return codeMstMapper.delete(code) > 0 ? true : false;
	}
}
