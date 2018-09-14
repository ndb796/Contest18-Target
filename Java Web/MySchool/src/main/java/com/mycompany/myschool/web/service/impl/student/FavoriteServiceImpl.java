package com.mycompany.myschool.web.service.impl.student;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.myschool.web.entity.student.FavoriteEntity;
import com.mycompany.myschool.web.entity.vo.FavoriteListVo;
import com.mycompany.myschool.web.persistent.mapper.student.FavoriteMapper;
import com.mycompany.myschool.web.service.student.FavoriteService;

@Service
public class FavoriteServiceImpl implements FavoriteService {
	@Autowired
	private FavoriteMapper favoriteMapper;
	
	@Override
	public List<FavoriteEntity> list(FavoriteEntity favoriteEntity) {
		return favoriteMapper.select(favoriteEntity);
	}

	@Override
	public FavoriteEntity get(FavoriteEntity favoriteEntity) {
		return favoriteMapper.selectOne(favoriteEntity);
	}
	
	@Override
	public FavoriteEntity getByFavoriteId(String favorite_id) {
		return favoriteMapper.selectByFavoriteId(favorite_id);
	}
	
	@Override
	public List<FavoriteListVo> listFavoriteStudentBySchoolId(String school_id) {
		return favoriteMapper.selectFavoriteStudentBySchoolId(school_id);
	}
	
	@Override
	public List<FavoriteListVo> listFavoriteSchoolByStudentId(String student_id) {
		return favoriteMapper.selectFavoriteSchoolByStudentId(student_id);
	}
	
	@Override
	public List<FavoriteListVo> listFavoriteSchoolListViewByStudentId(Map<String, String> maps) {
		return favoriteMapper.selectFavoriteSchoolListViewByStudentId(maps);
	}

	@Override
	public String getMaxId() {
		return favoriteMapper.selectMaxId();
	}
	
	@Override
	public boolean add(FavoriteEntity favoriteEntity) {
		return favoriteMapper.insert(favoriteEntity) > 0 ? true : false;
	}

	@Override
	public boolean modify(FavoriteEntity favoriteEntity) {
		return favoriteMapper.update(favoriteEntity) > 0 ? true : false;
	}

	@Override
	public boolean remove(FavoriteEntity favoriteEntity) {
		return favoriteMapper.delete(favoriteEntity) > 0 ? true : false;
	}

	@Override
	public boolean removeList(FavoriteEntity favoriteEntity) {
		return favoriteMapper.deleteList(favoriteEntity) > 0 ? true : false;
	}
}
