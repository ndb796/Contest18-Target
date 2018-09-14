package com.mycompany.myschool.web.controller.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mycompany.myschool.web.entity.base.UserEntity;
import com.mycompany.myschool.web.service.base.UserService;
import com.mycompany.myschool.web.util.ResultCode;
import com.mycompany.myschool.web.util.Util;

@Controller
public class AdminUserController {
	@Autowired
	private UserService userService;
	
	/*
	 * User
	 */
	@RequestMapping("/ds_admin/user")
    public ModelAndView user(Model model, 
    							  HttpServletRequest request,
    							  HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		UserEntity admin_user = (UserEntity)session.getAttribute("admin_user");
		if (admin_user != null) {
			mav.addObject("menu", request.getParameter("menu"));
			
			UserEntity userEntity = new UserEntity();
			
			List<UserEntity> userList = userService.list(userEntity);
			
			mav.addObject("user_list", userList);
			mav.addObject("locale", Util.GET_LOCALE(request.getHeader("Accept-Language")));
			mav.addObject("context_name", Util.LOAD_CONFIG("context_name"));
			
			mav.setViewName("/ds_admin/user");
		} else {
			mav.setViewName("redirect:/ds_admin/login.do?resultCode=" + ResultCode.USER_ERR_LOGIN_CODE);
		}
		
        return mav;
	}
	
	@RequestMapping("/ds_admin/user/{user_id}")
    public void userView(@PathVariable("user_id") String user_id,
    							Model model,
    		 			   		HttpServletRequest request,
    		 			   		HttpServletResponse response) {
		String callback = request.getParameter("jsoncallback");
		
		UserEntity userEntity = userService.getByUserId(user_id);
		
		JSONObject newsJson = new JSONObject();
		newsJson.put("idx", userEntity.getIdx());
		newsJson.put("user_id", userEntity.getUser_id());
		newsJson.put("pwd", userEntity.getPwd());
		newsJson.put("email", userEntity.getEmail());
		newsJson.put("join_type", userEntity.getJoin_type());
		newsJson.put("mem_type", userEntity.getMem_type());
		newsJson.put("upd_date", userEntity.getUpd_date_str());
		
		try {
			PrintWriter out = response.getWriter();
			out.write(callback + "(" + newsJson.toString() + ")");
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	@RequestMapping("/ds_admin/user/userModProc")
    public ModelAndView userModProc(HttpServletRequest request, 
    									 Model model,
    									 HttpSession session,
    									 HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		String paramJoinType = request.getParameter("join_type");
		String paramMemType = request.getParameter("mem_type");
		
		UserEntity userEntity = new UserEntity();
		userEntity.setUser_id(request.getParameter("user_id"));
		userEntity.setEmail(request.getParameter("email"));
		userEntity.setJoin_type(paramJoinType != null ? Integer.parseInt(paramJoinType) : 0);
		userEntity.setMem_type(paramMemType != null ? Integer.parseInt(paramMemType) : 0);
		userEntity.setUpd_id("admin");
		
		userService.modifyByUserId(userEntity);
		
		/*
		 * retrieve list
		 */
		List<UserEntity> userList = userService.list(new UserEntity());
			
		mav.addObject("user_list", userList);
		mav.addObject("locale", Util.GET_LOCALE(request.getHeader("Accept-Language")));
		mav.addObject("context_name", Util.LOAD_CONFIG("context_name"));
		
		mav.setViewName("/ds_admin/user");
		
		return mav;
	}
	
	@RequestMapping("/ds_admin/user/userDelProc")
    public ModelAndView userDelProc(HttpServletRequest request, 
    									 Model model,
    									 HttpSession session,
    									 HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();

		Map<String, Object> maps = new HashMap<String, Object>();
		maps.put("user_id", request.getParameter("user_id"));
		maps.put("upd_id", "admin");
		
		userService.remove(maps);
		
		/*
		 * retrieve list
		 */
		List<UserEntity> userList = userService.list(new UserEntity());
			
		mav.addObject("user_list", userList);
		mav.addObject("locale", Util.GET_LOCALE(request.getHeader("Accept-Language")));
		mav.addObject("context_name", Util.LOAD_CONFIG("context_name"));
		
		mav.setViewName("/ds_admin/user");
		
		return mav;
	}
}

