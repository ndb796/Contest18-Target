package com.mycompany.myschool.web.service.impl.student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.myschool.web.entity.student.RecommendEntity;
import com.mycompany.myschool.web.entity.vo.RecommendListVo;
import com.mycompany.myschool.web.persistent.mapper.student.RecommendMapper;
import com.mycompany.myschool.web.service.student.RecommendService;

@Service
public class RecommendServiceImpl implements RecommendService {
	@Autowired
	private RecommendMapper recommendMapper;
	
	@Override
	public List<RecommendEntity> list(RecommendEntity recommendEntity) {
		return recommendMapper.select(recommendEntity);
	}

	@Override
	public RecommendEntity getByRcmmdId(String rcmmd_id) {
		return recommendMapper.selectByRcmmdId(rcmmd_id);
	}
	
	@Override
	public List<RecommendEntity> listByStudentId(String student_id) {
		return recommendMapper.selectByStudentId(student_id);
	}

	@Override
	public List<RecommendEntity> listBySchoolId(String school_id) {
		return recommendMapper.selectBySchoolId(school_id);
	}

	@Override
	public List<RecommendListVo> listRcmmdByStudentId(String student_id) {
		return recommendMapper.selectRcmmdByStudentId(student_id);
	}
	
	@Override
	public List<RecommendListVo> listRcmmdBySchoolId(String school_id) {
		return recommendMapper.selectRcmmdBySchoolId(school_id);
	}
	
	@Override
	public List<RecommendListVo> listRcmmdStudentBySchoolId(String school_id) {
		return recommendMapper.selectRcmmdStudentBySchoolId(school_id);
	}
	
	@Override
	public List<RecommendListVo> listRcmmdSchoolListViewByStudentId(String student_id) {
		return recommendMapper.selectRcmmdSchoolListViewByStudentId(student_id);
	}
	
	@Override
	public String getMaxId() {
		return recommendMapper.selectMaxId();
	}
	
	@Override
	public Integer getCountByStudentId(String student_id) {
		return recommendMapper.selectCountByStudentId(student_id);
	}

	@Override
	public boolean add(RecommendEntity recommendEntity) {
		return recommendMapper.insert(recommendEntity) > 0 ? true : false;
	}

	@Override
	public boolean modify(RecommendEntity recommendEntity) {
		return recommendMapper.update(recommendEntity) > 0 ? true : false;
	}

	@Override
	public boolean remove(String email) {
		return recommendMapper.delete(email) > 0 ? true : false;
	}

	@Override
	public boolean removeOldDataByStudentId(String student_id) {
		return recommendMapper.deleteOldDataByStudentId(student_id) > 0 ? true : false;
	}

	@Override
	public boolean removeList(RecommendEntity recommendEntity) {
		return recommendMapper.deleteList(recommendEntity) > 0 ? true : false;
	}
}
