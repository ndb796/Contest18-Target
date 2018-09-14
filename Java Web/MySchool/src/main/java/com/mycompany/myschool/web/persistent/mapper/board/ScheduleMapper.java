package com.mycompany.myschool.web.persistent.mapper.board;

import java.util.List;

import com.mycompany.myschool.web.entity.board.EvntRegEntity;
import com.mycompany.myschool.web.entity.board.ScheduleEntity;

public interface ScheduleMapper {
	List<ScheduleEntity> select(ScheduleEntity scheduleEntity);
	
	List<ScheduleEntity> selectInRange(ScheduleEntity scheduleEntity);
	
	List<ScheduleEntity> selectOnPublic(ScheduleEntity scheduleEntity);
	
	List<ScheduleEntity> selectOfRcmmd(ScheduleEntity scheduleEntity);
	
	List<ScheduleEntity> selectOneDay(ScheduleEntity scheduleEntity);
	
	int selectCountInRange(ScheduleEntity scheduleEntity);
	
	ScheduleEntity selectOne(ScheduleEntity scheduleEntity);
	
	List<ScheduleEntity> selectByStudent(String student_id);
	
	List<ScheduleEntity> selectByGroup(ScheduleEntity scheduleEntity);
	
	String selectMaxId();
	
	int insert(ScheduleEntity scheduleEntity);
	
	int update(ScheduleEntity scheduleEntity);

	int delete(String evt_id);
	
	int deleteList(ScheduleEntity scheduleEntity);
	
	int insertEvntReg(EvntRegEntity evntRegEntity);
	
	int countEvntReg(EvntRegEntity evntRegEntity);
}
