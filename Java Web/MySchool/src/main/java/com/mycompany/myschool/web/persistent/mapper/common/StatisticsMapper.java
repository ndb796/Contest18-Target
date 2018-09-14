package com.mycompany.myschool.web.persistent.mapper.common;

import java.util.List;

import com.mycompany.myschool.web.entity.vo.StatisticsVo;

public interface StatisticsMapper {
	
	List<StatisticsVo> selectByUserCount(int memType);
	
	List<StatisticsVo> selectRegCount();
	
	StatisticsVo selectConsultCountByUserId(String student_id);
	
	List<StatisticsVo> selectConsultCountAll(int const_status);
	
	List<StatisticsVo> selectAccessCount();
}
