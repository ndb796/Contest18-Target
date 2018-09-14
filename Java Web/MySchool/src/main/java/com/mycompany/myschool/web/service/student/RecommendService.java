package com.mycompany.myschool.web.service.student;

import java.util.List;

import com.mycompany.myschool.web.entity.student.RecommendEntity;
import com.mycompany.myschool.web.entity.vo.RecommendListVo;

public interface RecommendService {
	List<RecommendEntity> list(RecommendEntity recommendEntity);
	
	RecommendEntity getByRcmmdId(String rcmmd_id);
	
	List<RecommendEntity> listByStudentId(String student_id);
	
	List<RecommendEntity> listBySchoolId(String school_id);
	
	List<RecommendListVo> listRcmmdByStudentId(String student_id);
	
	List<RecommendListVo> listRcmmdBySchoolId(String school_id);
	
	List<RecommendListVo> listRcmmdStudentBySchoolId(String school_id);
	
	List<RecommendListVo> listRcmmdSchoolListViewByStudentId(String student_id);
	
	String getMaxId();
	
	Integer getCountByStudentId(String student_id);
	
	boolean add(RecommendEntity recommendEntity);
	
	boolean modify(RecommendEntity recommendEntity);
	
	boolean remove(String rcmmd_id);
	
	boolean removeOldDataByStudentId(String student_id);
	
	boolean removeList(RecommendEntity recommendEntity);
}
