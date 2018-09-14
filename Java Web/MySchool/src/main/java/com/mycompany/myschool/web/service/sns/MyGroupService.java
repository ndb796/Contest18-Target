package com.mycompany.myschool.web.service.sns;

import java.util.List;

import com.mycompany.myschool.web.entity.sns.MyGroupEntity;

public interface MyGroupService {
	List<MyGroupEntity> list(MyGroupEntity myGroupEntity);
	
	MyGroupEntity get(MyGroupEntity myGroupEntity);
	
	List<MyGroupEntity> listByGroup(MyGroupEntity myGroupEntity);
	
	List<MyGroupEntity> listByMyStudentId(String mystudent_id);
	
	boolean add(MyGroupEntity myGroupEntity);
	
	boolean remove(int idx);
	
	boolean removeList(MyGroupEntity myGroupEntity);
}
