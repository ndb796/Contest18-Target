package com.mycompany.myschool.web.service.impl.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.myschool.web.entity.board.EvntRegEntity;
import com.mycompany.myschool.web.entity.board.ScheduleEntity;
import com.mycompany.myschool.web.persistent.mapper.board.ScheduleMapper;
import com.mycompany.myschool.web.service.board.ScheduleService;

@Service
public class ScheduleServiceImpl implements ScheduleService {
	@Autowired
	private ScheduleMapper scheduleMapper;
	
	@Override
	public List<ScheduleEntity> list(ScheduleEntity scheduleEntity) {
		return scheduleMapper.select(scheduleEntity);
	}

	@Override
	public List<ScheduleEntity> listInRange(ScheduleEntity scheduleEntity) {
		return scheduleMapper.selectInRange(scheduleEntity);
	}
	
	@Override
	public List<ScheduleEntity> listOnPublic(ScheduleEntity scheduleEntity) {
		return scheduleMapper.selectOnPublic(scheduleEntity);
	}
	
	@Override
	public List<ScheduleEntity> listOfRcmmd(ScheduleEntity scheduleEntity) {
		return scheduleMapper.selectOfRcmmd(scheduleEntity);
	}
	
	@Override
	public List<ScheduleEntity> listOneDay(ScheduleEntity scheduleEntity) {
		return scheduleMapper.selectOneDay(scheduleEntity);
	}

	@Override
	public int getCountInRange(ScheduleEntity scheduleEntity) {
		return scheduleMapper.selectCountInRange(scheduleEntity);
	}

	@Override
	public ScheduleEntity get(ScheduleEntity scheduleEntity) {
		return scheduleMapper.selectOne(scheduleEntity);
	}

	@Override
	public List<ScheduleEntity> listByStudent(String student_id) {
		return scheduleMapper.selectByStudent(student_id);
	}

	@Override
	public List<ScheduleEntity> listByGroup(ScheduleEntity scheduleEntity) {
		return scheduleMapper.selectByGroup(scheduleEntity);
	}

	@Override
	public String getMaxId() {
		return scheduleMapper.selectMaxId();
	}
	
	@Override
	public boolean add(ScheduleEntity scheduleEntity) {
		return scheduleMapper.insert(scheduleEntity) > 0 ? true : false;
	}

	@Override
	public boolean modify(ScheduleEntity scheduleEntity) {
		return scheduleMapper.update(scheduleEntity) > 0 ? true : false;
	}

	@Override
	public boolean remove(String evt_id) {
		return scheduleMapper.delete(evt_id) > 0 ? true : false;
	}

	@Override
	public boolean removeList(ScheduleEntity scheduleEntity) {
		return scheduleMapper.deleteList(scheduleEntity) > 0 ? true : false;
	}
	
	@Override
	public boolean addEvntReg(EvntRegEntity evntRegEntity) {
		return scheduleMapper.insertEvntReg(evntRegEntity) > 0 ? true : false;
	}
	
	@Override
	public int countEvntReg(EvntRegEntity evntRegEntity) {
		return scheduleMapper.countEvntReg(evntRegEntity);
	}
}
