package com.mycompany.myschool.web.service.impl.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.myschool.web.entity.customer.ViewCountEntity;
import com.mycompany.myschool.web.persistent.mapper.customer.ViewCountMapper;
import com.mycompany.myschool.web.service.customer.ViewCountService;

@Service
public class ViewCountServiceImpl implements ViewCountService {
	@Autowired
	private ViewCountMapper viewCountMapper;
	
	@Override
	public List<ViewCountEntity> list(ViewCountEntity viewCountEntity) {
		return viewCountMapper.select(viewCountEntity);
	}

	@Override
	public ViewCountEntity get(String sb_id) {
		return viewCountMapper.selectOne(sb_id);
	}

	@Override
	public boolean modifyViewCnt(ViewCountEntity viewCountEntity) {
		return viewCountMapper.updateViewCnt(viewCountEntity) > 0 ? true : false;
	}

	@Override
	public boolean modifyRealCnt(ViewCountEntity viewCountEntity) {
		return viewCountMapper.updateRealCnt(viewCountEntity) > 0 ? true : false;
	}

	@Override
	public boolean add(ViewCountEntity viewCountEntity) {
		return viewCountMapper.insert(viewCountEntity) > 0 ? true : false;
	}

	@Override
	public boolean remove(String email) {
		return viewCountMapper.delete(email) > 0 ? true : false;
	}
}
