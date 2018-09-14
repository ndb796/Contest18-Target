package com.mycompany.myschool.web.service.impl.common;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.myschool.web.entity.vo.StatisticsVo;
import com.mycompany.myschool.web.persistent.mapper.common.StatisticsMapper;
import com.mycompany.myschool.web.service.common.StatisticsService;

@Service
public class StatisticsServiceImpl implements StatisticsService {
	@Autowired
	private StatisticsMapper statisticsMapper;
	
	@Override
	public List<StatisticsVo> getByUserCount(int memType) {
		return statisticsMapper.selectByUserCount(memType);
	}

	@Override
	public List<StatisticsVo> getRegCount() {
		return statisticsMapper.selectRegCount();
	}

	@Override
	public StatisticsVo getConsultCountByUserId(String student_id) {
		return statisticsMapper.selectConsultCountByUserId(student_id);
	}

	@Override
	public List<StatisticsVo> getConsultCountAll(int const_status) {
		return statisticsMapper.selectConsultCountAll(const_status);
	}
	
	@Override
	public List<StatisticsVo> getAccessCount() {
		return statisticsMapper.selectAccessCount();
	}
}
