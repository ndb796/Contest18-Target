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
import com.mycompany.myschool.web.entity.sns.SnsConsultEntity;
import com.mycompany.myschool.web.service.sns.SnsConsultService;
import com.mycompany.myschool.web.util.ResultCode;
import com.mycompany.myschool.web.util.Util;

@Controller
public class AdminConsultController {
	@Autowired
	private SnsConsultService consultService;
	
	/*
	 * Blog
	 */
	@RequestMapping("/ds_admin/consult.do")
    public ModelAndView consult(Model model, 
    							  HttpServletRequest request,
    							  HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		UserEntity admin_user = (UserEntity)session.getAttribute("admin_user");
		if (admin_user != null) {
			mav.addObject("menu", request.getParameter("menu"));
			
			SnsConsultEntity consultEntity = new SnsConsultEntity();
			List<SnsConsultEntity> consultList = consultService.listForAdmin(consultEntity);
			
			mav.addObject("consult_list", consultList);
			mav.addObject("locale", Util.GET_LOCALE(request.getHeader("Accept-Language")));
			mav.addObject("context_name", Util.LOAD_CONFIG("context_name"));
			
			mav.setViewName("/ds_admin/consult");
		} else {
			mav.setViewName("redirect:/ds_admin/login.do?resultCode=" + ResultCode.USER_ERR_LOGIN_CODE);
		}
		
        return mav;
	}
	
	@RequestMapping("/ds_admin/consult/{idx}")
    public void blogView(@PathVariable("idx") Integer idx,
    							Model model,
    		 			   		HttpServletRequest request,
    		 			   		HttpServletResponse response) {
		String callback = request.getParameter("jsoncallback");
		
		Map<String, Object> maps = new HashMap<String, Object>();
		maps.put("idx", idx);
		
		SnsConsultEntity consultEntity = consultService.get(maps);
		
		JSONObject newsJson = new JSONObject();
		newsJson.put("idx", consultEntity.getIdx());
		newsJson.put("student_id", consultEntity.getStudent_id());
		newsJson.put("sb_id", consultEntity.getSb_id());
		newsJson.put("const_status", consultEntity.getConst_status());
		newsJson.put("contents", consultEntity.getContents());
		newsJson.put("ins_date", consultEntity.getIns_date_str());
		newsJson.put("del_date", consultEntity.getDel_date_str());
		newsJson.put("del_flg", consultEntity.getDel_flg());
		
		try {
			PrintWriter out = response.getWriter();
			out.write(callback + "(" + newsJson.toString() + ")");
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
}

