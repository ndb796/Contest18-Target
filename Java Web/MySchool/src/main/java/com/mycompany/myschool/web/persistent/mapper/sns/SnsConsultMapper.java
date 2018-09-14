package com.mycompany.myschool.web.persistent.mapper.sns;

import java.util.List;
import java.util.Map;

import com.mycompany.myschool.web.entity.admin.MainEntity;
import com.mycompany.myschool.web.entity.sns.SnsConsultEntity;

public interface SnsConsultMapper {
	List<SnsConsultEntity> select(SnsConsultEntity snsConsultEntity);
	
	List<SnsConsultEntity> selectByStudentId(SnsConsultEntity conditionEntity);
	
	List<SnsConsultEntity> selectBySchoolId(SnsConsultEntity conditionEntity);
	
	SnsConsultEntity selectOne(Map<String, Object> maps);
	
	List<SnsConsultEntity> selectForAdmin(SnsConsultEntity consultEntity);
	
	int insert(SnsConsultEntity snsConsultEntity);
	
	int delete(int idx);
	
	int deleteList(SnsConsultEntity snsConsultEntity);
	
	int countConsultThisWeek(Map<String, Object> maps);
	
	MainEntity selectNewConsult();
}
