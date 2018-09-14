package com.mycompany.myschool.web.service.sns;

import java.util.List;
import java.util.Map;

import com.mycompany.myschool.web.entity.admin.MainEntity;
import com.mycompany.myschool.web.entity.sns.SnsConsultEntity;

public interface SnsConsultService {
	List<SnsConsultEntity> list(SnsConsultEntity snsConsultEntity);
	
	List<SnsConsultEntity> listByStudentId(SnsConsultEntity conditionEntity);
	
	List<SnsConsultEntity> listBySchoolId(SnsConsultEntity conditionEntity);
	
	SnsConsultEntity get(Map<String, Object> maps);
	
	List<SnsConsultEntity> listForAdmin(SnsConsultEntity consultEntity);
	
	boolean add(SnsConsultEntity snsConsultEntity);
	
	boolean remove(int idx);
	
	boolean removeList(SnsConsultEntity snsConsultEntity);
	
	int countConsultThisWeek(Map<String, Object> maps);
	
	MainEntity getNewConsult();
}
