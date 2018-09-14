package com.mycompany.myschool.web.persistent.mapper.customer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mycompany.myschool.web.entity.customer.CustomerBranchEntity;
import com.mycompany.myschool.web.entity.vo.BranchInfoVo;
import com.mycompany.myschool.web.entity.vo.BranchListVo;

public interface CustomerBranchMapper {

	CustomerBranchEntity selectOne(String key);

	List<CustomerBranchEntity> selectAllForMain(Map<String, Integer> map);
	
	List<CustomerBranchEntity> selectAllBySchoolId(CustomerBranchEntity entity);
	
	List<BranchListVo> selectListVo(CustomerBranchEntity entity);
	
	Integer selectCountListVo(CustomerBranchEntity entity);
	
	List<BranchListVo> selectBranchListForStudentVo(String student_id);
	
	BranchInfoVo selectInfoVo(String sb_id);
	
	List<BranchListVo> selectForRcmmd1(String student_id);
	
	List<BranchInfoVo> selectForRcmmdWithSchoolKeyword(HashMap<String, Object> searchMap);
	
	List<BranchInfoVo> selectForRcmmdWithAddressKeyword(HashMap<String, Object> searchMap);
	
	List<BranchInfoVo> selectForRcmmdWithAddress(HashMap<String, Object> searchMap);
	
	String selectMaxId();
	
	int insert(CustomerBranchEntity entity);

	int update(CustomerBranchEntity entity);
	
	int updateForDelete(CustomerBranchEntity entity);

	int delete(CustomerBranchEntity entity);

}