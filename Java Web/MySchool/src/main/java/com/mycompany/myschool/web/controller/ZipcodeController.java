package com.mycompany.myschool.web.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mycompany.myschool.web.entity.base.ZipcodeEntity;
import com.mycompany.myschool.web.service.base.ZipcodeService;
import com.mycompany.myschool.web.util.ResultCode;
import com.mycompany.myschool.web.util.Util;

@Controller
@RequestMapping("/common")
public class ZipcodeController {
	@Autowired
	private ZipcodeService zipcodeService;
	
	@RequestMapping("/list")
    public ModelAndView list(Model model, 
    							  HttpServletRequest request,
    							  HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("locale", Util.GET_LOCALE(request.getHeader("Accept-Language")));
		mav.addObject("context_name", Util.LOAD_CONFIG("context_name"));
		
        return mav;
    }
	
	@RequestMapping("/findByZipcode")
    public void findByZipcode(HttpServletRequest request,
    							HttpServletResponse response,
    							HttpSession session) {
		try {
			 response.setContentType("text/html;charset=UTF-8");
	         response.setHeader("Cache-Control", "no-cache");
	        
			 PrintWriter out = response.getWriter();
		     String zipcode = request.getParameter("zipcode");
		     
		     ZipcodeEntity zipcodeEntity = zipcodeService.findByZipcode(zipcode);
		     
		     if (zipcodeEntity != null) {
		    	 out.println(zipcodeEntity.getPrefecture() + "|" + zipcodeEntity.getCity() + "|" + zipcodeEntity.getTown());
		     } else {
		    	 out.println("ERRCODE|" + ResultCode.COMMON_ZIPCODE_NOT_FOUND_CODE);
		     }
		     out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

