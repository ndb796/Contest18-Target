package com.mycompany.myschool.web.service.student;

import java.util.List;
import java.util.Map;

import com.mycompany.myschool.web.entity.student.FavoriteEntity;
import com.mycompany.myschool.web.entity.vo.FavoriteListVo;

public interface FavoriteService {
	List<FavoriteEntity> list(FavoriteEntity favoriteEntity);
	
	FavoriteEntity get(FavoriteEntity favoriteEntity);
	
	FavoriteEntity getByFavoriteId(String favorite_id);
	
	List<FavoriteListVo> listFavoriteStudentBySchoolId(String school_id);
	
	List<FavoriteListVo> listFavoriteSchoolByStudentId(String student_id);
	
	List<FavoriteListVo> listFavoriteSchoolListViewByStudentId(Map<String, String> maps);
	
	String getMaxId();
	
	boolean add(FavoriteEntity favoriteEntity);
	
	boolean modify(FavoriteEntity favoriteEntity);
	
	boolean remove(FavoriteEntity favoriteEntity);
	
	boolean removeList(FavoriteEntity favoriteEntity);
}
