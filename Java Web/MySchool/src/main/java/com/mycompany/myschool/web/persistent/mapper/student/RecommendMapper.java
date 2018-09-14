package com.mycompany.myschool.web.persistent.mapper.student;

import java.util.List;

import com.mycompany.myschool.web.entity.student.RecommendEntity;
import com.mycompany.myschool.web.entity.vo.RecommendListVo;

public interface RecommendMapper {
	List<RecommendEntity> select(RecommendEntity recommendEntity);
	
	RecommendEntity selectByRcmmdId(String rcmmd_id);
	
	List<RecommendEntity> selectByStudentId(String student_id);
	
	List<RecommendEntity> selectBySchoolId(String school_id);
	
	List<RecommendListVo> selectRcmmdByStudentId(String student_id);
	
	List<RecommendListVo> selectRcmmdBySchoolId(String school_id);
	
	List<RecommendListVo> selectRcmmdStudentBySchoolId(String school_id);
	
	List<RecommendListVo> selectRcmmdSchoolListViewByStudentId(String student_id);
	
	String selectMaxId();
	
	Integer selectCountByStudentId(String student_id);
	
	int insert(RecommendEntity recommendEntity);
	
	int update(RecommendEntity recommendEntity);

	int delete(String rcmmd_id);
	
	int deleteOldDataByStudentId(String student_id);
	
	int deleteList(RecommendEntity recommendEntity);
}
