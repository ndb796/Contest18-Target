package com.mycompany.myschool.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mycompany.myschool.web.entity.base.UserEntity;
import com.mycompany.myschool.web.entity.customer.CustomerEntity;
import com.mycompany.myschool.web.entity.student.StudentEntity;
import com.mycompany.myschool.web.service.base.UserService;
import com.mycompany.myschool.web.util.Constants;
import com.mycompany.myschool.web.util.IDGenerator;
import com.mycompany.myschool.web.util.MD5Util;
import com.mycompany.myschool.web.util.ResultCode;
import com.mycompany.myschool.web.util.Token;
import com.mycompany.myschool.web.util.Util;

@Controller
@RequestMapping("/user")
public class JoinController {
	@Autowired
	private UserService userService;
	
	@RequestMapping("/addUser")
    public ModelAndView addUser(Model model, 
    							  HttpServletRequest request,
    							  HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		String user_email = request.getParameter("email");
		String user_pwd = request.getParameter("pwd");
		String joinType = request.getParameter("joinType");
		String memberType = request.getParameter("memberType");
		
		// 1. 기존 가입 보가 있는지 확인
		List<UserEntity> userEntitylist = userService.getByLogin(user_email);
		if (userEntitylist != null && userEntitylist.size() > 0) {
			// 1-1. 기존에 같은 email로 가입된 정보가 있음. 
			mav.setViewName("redirect:/index.do?resultCode=" + ResultCode.USER_ERR_DUPLICATE_CODE);
		} else {
			// 1-2. 기존에 같은 email로 가입된 정보가 없는 경우. 신규가입 진행 
			String currentId = "";
			UserEntity userEntity = new UserEntity();
			Map<String, String> map = new HashMap<String, String>();
			
			if (memberType != null) {
				if (Integer.parseInt(memberType) == Constants.USER_MEM_STUDENT) {
					map.put("keyword", Constants.SRVC_TYPE_STD);
					currentId = userService.getMaxId(map);
					userEntity.setUser_id(IDGenerator.NEXT_ID(Constants.SRVC_TYPE_STD, currentId));
				} else {
					map.put("keyword", Constants.SRVC_TYPE_SCH);
					currentId = userService.getMaxId(map);
					userEntity.setUser_id(IDGenerator.NEXT_ID(Constants.SRVC_TYPE_SCH, currentId));
				}
				
				try {
					userEntity.setEmail(user_email);
					userEntity.setPwd(MD5Util.GET_CRYPTO_MD5(user_pwd));
					userEntity.setJoin_type(Integer.parseInt(joinType));
					userEntity.setMem_type(Integer.parseInt(memberType));
					userEntity.setReg_status("100000");	
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				if(Token.isValid(request)) {
				    Token.set(request);
				    request.setAttribute("TOKEN_SAVE_CHECK", "TRUE");
				} else {
					request.setAttribute("TOKEN_SAVE_CHECK", "FALSE");
				}
			} else {
				return mav;
			}
			
			boolean isError = false;
			
			try {
				if("TRUE".equals(request.getAttribute("TOKEN_SAVE_CHECK"))) {
					if (!userService.add(userEntity)) {
						isError = true;
					} 
				} 
			} catch (Exception e) {
				isError = true;
				e.printStackTrace();
			}
			
			if (isError) {
				mav.setViewName("redirect:/index.do?resultCode=" + ResultCode.USER_ERR_CREATE_CODE);
			} else {
				session.setAttribute("login_user", userEntity);
				mav.addObject("mode", "add");
				
				List<UserEntity>  dbUserEntitylist = userService.getByLogin(user_email);
				UserEntity dbUserEntity = dbUserEntitylist.get(0);
				mav.addObject("userEntity", dbUserEntity);
				if (userEntity.getMem_type() == Constants.USER_MEM_STUDENT) {
					StudentEntity studentEntity = new StudentEntity();
					studentEntity.setStudent_id(dbUserEntity.getUser_id());
					
					model.addAttribute("studentEntity", studentEntity);
					
					/*
					 * Setting date
					 */
					mav.addObject("birth_y_map", Util.GET_BIRTH_Y_MAP(1950, 2007));
					mav.addObject("birth_m_map", Util.GET_BIRTH_MD_MAP(12));
					mav.addObject("birth_d_map", Util.GET_BIRTH_MD_MAP(31));
					
					mav.setViewName("/user/myprofile/stdMypage1");
				} else {
					CustomerEntity schoolEntity = new CustomerEntity();
					schoolEntity.setSchool_id(dbUserEntity.getUser_id());
					
					model.addAttribute("schoolEntity", schoolEntity);
					
					SortedMap<String, String> school_type_map = new TreeMap<String, String>();
					school_type_map.put("1", "중학교");
					school_type_map.put("2", "고등학교");
					school_type_map.put("3", "대학교");
					school_type_map.put("4", "대학원");
					
					mav.addObject("school_type_map", school_type_map);
					
					mav.setViewName("/customer/myprofile/schMypage1");
				}
			}
		}
		
		mav.addObject("locale", Util.GET_LOCALE(request.getHeader("Accept-Language")));
		mav.addObject("context_name", Util.LOAD_CONFIG("context_name"));
		
        return mav;
    }
	
	@RequestMapping("/joinType")
    public ModelAndView joinType(Model model, 
    							  HttpServletRequest request,
    							  HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("locale", Util.GET_LOCALE(request.getHeader("Accept-Language")));
		mav.addObject("context_name", Util.LOAD_CONFIG("context_name"));
		
		mav.setViewName("/user/join_type");
		
		return mav;
	}
	
	@RequestMapping("/emailForm")
    public ModelAndView emailForm(Model model, 
    							  HttpServletRequest request,
    							  HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("locale", Util.GET_LOCALE(request.getHeader("Accept-Language")));
		mav.addObject("context_name", Util.LOAD_CONFIG("context_name"));
		
		mav.setViewName("/user/email_form");
		
		return mav;
	}
}

