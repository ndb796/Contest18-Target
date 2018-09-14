package com.mycompany.myschool.web.service.board;

import java.util.List;

import com.mycompany.myschool.web.entity.board.EvntRegEntity;
import com.mycompany.myschool.web.entity.board.ScheduleEntity;

public interface ScheduleService {
	List<ScheduleEntity> list(ScheduleEntity scheduleEntity);
	
	List<ScheduleEntity> listInRange(ScheduleEntity scheduleEntity);
	
	List<ScheduleEntity> listOnPublic(ScheduleEntity scheduleEntity);
	
	List<ScheduleEntity> listOfRcmmd(ScheduleEntity scheduleEntity);
	
	List<ScheduleEntity> listOneDay(ScheduleEntity scheduleEntity);
	
	int getCountInRange(ScheduleEntity scheduleEntity);
	
	ScheduleEntity get(ScheduleEntity scheduleEntity);
	
	List<ScheduleEntity> listByStudent(String student_id);
	
	List<ScheduleEntity> listByGroup(ScheduleEntity scheduleEntity);
	
	String getMaxId();
	
	boolean add(ScheduleEntity scheduleEntity);
	
	boolean modify(ScheduleEntity scheduleEntity);
	
	boolean remove(String evt_id);
	
	boolean removeList(ScheduleEntity scheduleEntity);
	
	boolean addEvntReg(EvntRegEntity evntRegEntity);
	
	int countEvntReg(EvntRegEntity evntRegEntity);
}
