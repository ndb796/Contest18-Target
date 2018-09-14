package com.mycompany.myschool.web.service.impl.sns;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.myschool.web.entity.admin.MainEntity;
import com.mycompany.myschool.web.entity.sns.SnsConsultEntity;
import com.mycompany.myschool.web.persistent.mapper.sns.SnsConsultMapper;
import com.mycompany.myschool.web.service.sns.SnsConsultService;

@Service
public class SnsConsultServiceImpl implements SnsConsultService {
	@Autowired
	private SnsConsultMapper snsConsultMapper;
	
	@Override
	public List<SnsConsultEntity> list(SnsConsultEntity snsConsultEntity) {
		return snsConsultMapper.select(snsConsultEntity);
	}

	@Override
	public List<SnsConsultEntity> listByStudentId(SnsConsultEntity conditionEntity) {
		return snsConsultMapper.selectByStudentId(conditionEntity);
	}

	@Override
	public List<SnsConsultEntity> listBySchoolId(SnsConsultEntity conditionEntity) {
		return snsConsultMapper.selectBySchoolId(conditionEntity);
	}

	@Override
	public SnsConsultEntity get(Map<String, Object> maps) {
		return snsConsultMapper.selectOne(maps);
	}

	@Override
	public List<SnsConsultEntity> listForAdmin(SnsConsultEntity consultEntity) {
		return snsConsultMapper.selectForAdmin(consultEntity);
	}

	@Override
	public boolean add(SnsConsultEntity snsConsultEntity) {
		return snsConsultMapper.insert(snsConsultEntity) > 0 ? true : false;
	}

	@Override
	public boolean remove(int idx) {
		return snsConsultMapper.delete(idx) > 0 ? true : false;
	}

	@Override
	public boolean removeList(SnsConsultEntity snsConsultEntity) {
		return snsConsultMapper.deleteList(snsConsultEntity) > 0 ? true : false;
	}
	
	@Override
	public int countConsultThisWeek(Map<String, Object> maps) {
		return snsConsultMapper.countConsultThisWeek(maps);
	}

	@Override
	public MainEntity getNewConsult() {
		return snsConsultMapper.selectNewConsult();
	}
}
