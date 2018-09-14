package com.mycompany.myschool.web.service.common;

import java.util.List;

import com.mycompany.myschool.web.entity.vo.StatisticsVo;

public interface StatisticsService {
	
	List<StatisticsVo> getByUserCount(int memType);
	
	List<StatisticsVo> getRegCount();
	
	StatisticsVo getConsultCountByUserId(String student_id);
	
	List<StatisticsVo> getConsultCountAll(int const_status);
	
	List<StatisticsVo> getAccessCount();
}
