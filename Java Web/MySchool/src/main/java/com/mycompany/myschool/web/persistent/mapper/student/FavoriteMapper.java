package com.mycompany.myschool.web.persistent.mapper.student;

import java.util.List;
import java.util.Map;

import com.mycompany.myschool.web.entity.student.FavoriteEntity;
import com.mycompany.myschool.web.entity.vo.FavoriteListVo;

public interface FavoriteMapper {
	List<FavoriteEntity> select(FavoriteEntity favoriteEntity);
	
	FavoriteEntity selectOne(FavoriteEntity favoriteEntity);
	
	FavoriteEntity selectByFavoriteId(String favorite_id);
	
	List<FavoriteListVo> selectFavoriteStudentBySchoolId(String school_id);
	
	List<FavoriteListVo> selectFavoriteSchoolByStudentId(String student_id);
	
	List<FavoriteListVo> selectFavoriteSchoolListViewByStudentId(Map<String, String> maps);
	
	String selectMaxId();
	
	int insert(FavoriteEntity favoriteEntity);
	
	int update(FavoriteEntity favoriteEntity);

	int delete(FavoriteEntity favoriteEntity);
	
	int deleteList(FavoriteEntity favoriteEntity);
}
