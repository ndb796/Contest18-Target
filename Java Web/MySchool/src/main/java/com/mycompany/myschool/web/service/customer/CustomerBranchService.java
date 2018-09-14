package com.mycompany.myschool.web.service.customer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mycompany.myschool.web.entity.customer.CustomerBranchEntity;
import com.mycompany.myschool.web.entity.vo.BranchInfoVo;
import com.mycompany.myschool.web.entity.vo.BranchListVo;

public interface CustomerBranchService {

	CustomerBranchEntity getOne(String key);

	List<CustomerBranchEntity> getAllForMain(Map<String, Integer> map);
	
	List<CustomerBranchEntity> getAllBySchoolId(CustomerBranchEntity entity);

	List<BranchListVo> listVo(CustomerBranchEntity entity);
	
	Integer countListVo(CustomerBranchEntity entity);
	
	List<BranchListVo> listBranchListForStudentVo(String student_id);
	
	List<BranchInfoVo> getForRcmmdWithSchoolKeyword(HashMap<String, Object> searchMap);
	
	List<BranchInfoVo> getForRcmmdWithAddressKeyword(HashMap<String, Object> searchMap);
	
	List<BranchInfoVo> getForRcmmdWithAddress(HashMap<String, Object> searchMap);
	
	BranchInfoVo getInfoVo(String sb_id);
	
	String getMaxId();
	
	boolean add(CustomerBranchEntity entity);

	boolean modify(CustomerBranchEntity entity);
	
	boolean modifyForRemove(CustomerBranchEntity entity);

	boolean remove(CustomerBranchEntity entity);

}