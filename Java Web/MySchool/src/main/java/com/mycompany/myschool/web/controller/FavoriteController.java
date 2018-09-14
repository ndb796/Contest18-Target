package com.mycompany.myschool.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mycompany.myschool.web.entity.base.UserEntity;
import com.mycompany.myschool.web.entity.common.SearchEntity;
import com.mycompany.myschool.web.entity.student.FavoriteEntity;
import com.mycompany.myschool.web.entity.student.StudentEntity;
import com.mycompany.myschool.web.service.base.UserService;
import com.mycompany.myschool.web.service.common.SearchService;
import com.mycompany.myschool.web.service.customer.CustomerBranchService;
import com.mycompany.myschool.web.service.customer.CustomerService;
import com.mycompany.myschool.web.service.student.FavoriteService;
import com.mycompany.myschool.web.service.student.StudentService;
import com.mycompany.myschool.web.util.Constants;
import com.mycompany.myschool.web.util.IDGenerator;
import com.mycompany.myschool.web.util.Token;
import com.mycompany.myschool.web.util.Util;

@Controller
@RequestMapping("/user")
public class FavoriteController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private FavoriteService favoriteService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private CustomerBranchService customerBranchService;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private SearchService searchService;
	
	@RequestMapping("/favorite/favrAdd")
    public ModelAndView favrAdd(HttpServletRequest request, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		UserEntity userEntity = (UserEntity)session.getAttribute("login_user");
				
		if (userEntity != null) {
			if(Token.isValid(request)) {
			    Token.set(request);
			    request.setAttribute("TOKEN_SAVE_CHECK", "TRUE");
			} else {
				request.setAttribute("TOKEN_SAVE_CHECK", "FALSE");
			}
			
			boolean isError = false;
			String sch_id = request.getParameter("sch_id");
			String student_id = request.getParameter("student_id");
			String sb_type = request.getParameter("sb_type");
			
			try {
				if("TRUE".equals(request.getAttribute("TOKEN_SAVE_CHECK"))) {
					FavoriteEntity favoriteEntity = new FavoriteEntity();
					String currentId = favoriteService.getMaxId();
					favoriteEntity.setFavorite_id(IDGenerator.NEXT_ID(Constants.SRVC_TYPE_FAV, currentId));
					favoriteEntity.setSb_id(sch_id);
					favoriteEntity.setStudent_id(student_id);
					
					favoriteService.add(favoriteEntity);
					
					mav.addObject("is_favorite", "yes");
				}
			} catch (Exception e) {
				isError = true;
				e.printStackTrace();
			}
			
			StudentEntity studentEntity = null;
			if (isError) {
				mav.setViewName("/user/school/schoolList");
			} else {
				studentEntity = studentService.getByStudentId(userEntity.getUser_id());
				
				SearchEntity schoolVo = null;
				
				int school_type = 0;
				if (sb_type == null || sb_type.length() == 0) {
					school_type = studentEntity.getRcmmd_type();
				} else {
					school_type = Integer.parseInt(sb_type);
				}
				
				if (sch_id.startsWith("SCH")) {
					schoolVo = searchService.getMemberschoolOne(sch_id);
				} else {
					if (school_type == Constants.STD_RCMMD_CATE_M) {
						schoolVo = searchService.getMiddleschoolOne(sch_id);
					} else if (school_type == Constants.STD_RCMMD_CATE_H) {
						schoolVo = searchService.getHighschoolOne(sch_id);
					} else if (school_type == Constants.STD_RCMMD_CATE_U) {
						schoolVo = searchService.getUniversityOne(sch_id);
					} else if (school_type == Constants.STD_RCMMD_CATE_C) {
						schoolVo = searchService.getVocationalOne(sch_id);
					} 
				}
				
				if (schoolVo != null) {
					mav.addObject("schoolEntity", schoolVo);
					
					FavoriteEntity favoriteEntity = new FavoriteEntity();
					favoriteEntity.setSb_id(sch_id);
					favoriteEntity.setStudent_id(userEntity.getUser_id());
					
					FavoriteEntity dbFavoriteEntity = favoriteService.get(favoriteEntity);
					if (dbFavoriteEntity != null) {
						mav.addObject("is_favorite", "yes");
					}
					
					mav.setViewName("/user/school/schoolDetail");	
				}
			}

			if (studentEntity == null) {
				studentEntity = new StudentEntity();
				studentEntity.setStudent_id(userEntity.getUser_id());
			}
			
			mav.addObject("studentEntity", studentEntity);
			mav.addObject("userEntity", userEntity);
			
			session.removeAttribute("token");
		} else {
			mav.setViewName("redirect:/index.do");
		}
		
		mav.addObject("locale", Util.GET_LOCALE(request.getHeader("Accept-Language")));
		mav.addObject("context_name", Util.LOAD_CONFIG("context_name"));
		
        return mav;
    }
	
	@RequestMapping("/favorite/favrRemove")
    public ModelAndView favrRemove(HttpServletRequest request, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		UserEntity userEntity = (UserEntity)session.getAttribute("login_user");
				
		if (userEntity != null) {
			if(Token.isValid(request)) {
			    Token.set(request);
			    request.setAttribute("TOKEN_SAVE_CHECK", "TRUE");
			} else {
				request.setAttribute("TOKEN_SAVE_CHECK", "FALSE");
			}
			
			boolean isError = false;
			String sch_id = request.getParameter("sch_id");
			String student_id = request.getParameter("student_id");
			String sb_type = request.getParameter("sb_type");
			
			try {
				if("TRUE".equals(request.getAttribute("TOKEN_SAVE_CHECK"))) {
					FavoriteEntity favoriteEntity = new FavoriteEntity();
					favoriteEntity.setSb_id(sch_id);
					favoriteEntity.setStudent_id(student_id);
					
					favoriteService.remove(favoriteEntity);
					
					mav.addObject("is_favorite", "no");
				}
			} catch (Exception e) {
				isError = true;
				e.printStackTrace();
			}
			
			StudentEntity studentEntity = null;
			if (isError) {
				mav.setViewName("/user/school/schoolList");
			} else {
				studentEntity = studentService.getByStudentId(userEntity.getUser_id());
				
				SearchEntity schoolVo = null;
				
				int school_type = 0;
				if (sb_type == null || sb_type.length() == 0) {
					school_type = studentEntity.getRcmmd_type();
				} else {
					school_type = Integer.parseInt(sb_type);
				}
				
				if (sch_id.startsWith("SCH")) {
					schoolVo = searchService.getMemberschoolOne(sch_id);
				} else {
					if (school_type == Constants.STD_RCMMD_CATE_M) {
						schoolVo = searchService.getMiddleschoolOne(sch_id);
					} else if (school_type == Constants.STD_RCMMD_CATE_H) {
						schoolVo = searchService.getHighschoolOne(sch_id);
					} else if (school_type == Constants.STD_RCMMD_CATE_U) {
						schoolVo = searchService.getUniversityOne(sch_id);
					} else if (school_type == Constants.STD_RCMMD_CATE_C) {
						schoolVo = searchService.getVocationalOne(sch_id);
					} 
				}
				
				if (schoolVo != null) {
					mav.addObject("schoolEntity", schoolVo);
					
					FavoriteEntity favoriteEntity = new FavoriteEntity();
					favoriteEntity.setSb_id(sch_id);
					favoriteEntity.setStudent_id(userEntity.getUser_id());
					
					FavoriteEntity dbFavoriteEntity = favoriteService.get(favoriteEntity);
					if (dbFavoriteEntity != null) {
						mav.addObject("is_favorite", "yes");
					}
					
					mav.setViewName("/user/school/schoolDetail");	
				}
			}

			if (studentEntity == null) {
				studentEntity = new StudentEntity();
				studentEntity.setStudent_id(userEntity.getUser_id());
			}
			
			mav.addObject("studentEntity", studentEntity);
			mav.addObject("userEntity", userEntity);
			
			session.removeAttribute("token");
		} else {
			mav.setViewName("redirect:/index.do");
		}
		
		mav.addObject("locale", Util.GET_LOCALE(request.getHeader("Accept-Language")));
		mav.addObject("context_name", Util.LOAD_CONFIG("context_name"));
		
        return mav;
    }
}
