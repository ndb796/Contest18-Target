package com.mycompany.myschool.web.service.impl.customer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.myschool.web.entity.customer.CustomerBranchEntity;
import com.mycompany.myschool.web.entity.vo.BranchInfoVo;
import com.mycompany.myschool.web.entity.vo.BranchListVo;
import com.mycompany.myschool.web.persistent.mapper.customer.CustomerBranchMapper;
import com.mycompany.myschool.web.service.customer.CustomerBranchService;

@Service
public class CustomerBranchServiceImpl implements CustomerBranchService {
	@Autowired
	private CustomerBranchMapper customerBranchMapper;
	
	@Override
	public CustomerBranchEntity getOne(String key) {
		return customerBranchMapper.selectOne(key);
	}	
	
	@Override
	public List<CustomerBranchEntity> getAllForMain(Map<String, Integer> map) {
		return customerBranchMapper.selectAllForMain(map);
	}

	@Override
	public List<CustomerBranchEntity> getAllBySchoolId(CustomerBranchEntity entity) {
		return customerBranchMapper.selectAllBySchoolId(entity);
	}	
	
	@Override
	public List<BranchListVo> listVo(CustomerBranchEntity entity) {
		return customerBranchMapper.selectListVo(entity);
	}

	@Override
	public Integer countListVo(CustomerBranchEntity entity) {
		return customerBranchMapper.selectCountListVo(entity);
	}

	@Override
	public List<BranchListVo> listBranchListForStudentVo(String student_id) {
		return customerBranchMapper.selectBranchListForStudentVo(student_id);
	}
	
	@Override
	public List<BranchInfoVo> getForRcmmdWithSchoolKeyword(HashMap<String, Object> searchMap) {
		return customerBranchMapper.selectForRcmmdWithSchoolKeyword(searchMap);
	}

	@Override
	public List<BranchInfoVo> getForRcmmdWithAddressKeyword(HashMap<String, Object> searchMap) {
		return customerBranchMapper.selectForRcmmdWithAddressKeyword(searchMap);
	}

	@Override
	public List<BranchInfoVo> getForRcmmdWithAddress(HashMap<String, Object> searchMap) {
		return customerBranchMapper.selectForRcmmdWithAddress(searchMap);
	}

	@Override
	public BranchInfoVo getInfoVo(String sb_id) {
		return customerBranchMapper.selectInfoVo(sb_id);
	}
	
	@Override
	public String getMaxId() {
		return customerBranchMapper.selectMaxId();
	}
	
	@Override
	public boolean add(CustomerBranchEntity entity) {
		return customerBranchMapper.insert(entity) > 0 ? true : false;
	}	
	
	@Override
	public boolean modify(CustomerBranchEntity entity) {
		return customerBranchMapper.update(entity) > 0 ? true : false;
	}	
	
	@Override
	public boolean modifyForRemove(CustomerBranchEntity entity) {
		return customerBranchMapper.updateForDelete(entity) > 0 ? true : false;
	}
	
	@Override
	public boolean remove(CustomerBranchEntity entity) {
		return customerBranchMapper.delete(entity) > 0 ? true : false;
	}
}