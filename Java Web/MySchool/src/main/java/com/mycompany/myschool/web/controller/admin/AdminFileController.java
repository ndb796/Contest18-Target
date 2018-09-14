package com.mycompany.myschool.web.controller.admin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

import javax.servlet.http.Cookie;
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
import com.mycompany.myschool.web.entity.customer.CustomerEntity;
import com.mycompany.myschool.web.service.customer.CustomerImageService;
import com.mycompany.myschool.web.service.customer.CustomerService;
import com.mycompany.myschool.web.util.Constants;
import com.mycompany.myschool.web.util.ResultCode;
import com.mycompany.myschool.web.util.Util;

@Controller
public class AdminFileController {
	@Autowired
	private CustomerService customerService;
	
	/*
	 * File
	 */
	@RequestMapping("/ds_admin/file.do")
    public ModelAndView file(Model model, 
    							  HttpServletRequest request,
    							  HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		UserEntity admin_user = (UserEntity)session.getAttribute("admin_user");
		if (admin_user != null) {
			mav.addObject("menu", request.getParameter("menu"));
			
			CustomerEntity customerEntity = new CustomerEntity();
			
			List<CustomerEntity> customerList = customerService.listForFile(customerEntity);
			
			mav.addObject("school_list", customerList);
			mav.addObject("locale", Util.GET_LOCALE(request.getHeader("Accept-Language")));
			mav.addObject("context_name", Util.LOAD_CONFIG("context_name"));
			
			String dir_type = "";
			Cookie[] cookies = request.getCookies();
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("dir_type")) {
					dir_type = cookie.getValue();
					
					break;
				}
			}
			
			mav.addObject("dir_type", dir_type);
			mav.setViewName("/ds_admin/file");
		} else {
			mav.setViewName("redirect:/ds_admin/login.do?resultCode=" + ResultCode.USER_ERR_LOGIN_CODE);
		}
		
        return mav;
	}
	
	@RequestMapping("/ds_admin/file/{school_id}")
    public void fileList(@PathVariable("school_id") String school_id,
    							Model model,
    		 			   		HttpServletRequest request,
    		 			   		HttpServletResponse response) {
		String callback = request.getParameter("jsoncallback");
		
		String dir_type = request.getParameter("dir_type");
		if (dir_type == null || dir_type.isEmpty()) {
			dir_type = "gallery";
		} else {
			if (dir_type.equals("-")) {
				// add cookie the passed dir type
				Cookie[] cookies = request.getCookies();
				for (Cookie cookie : cookies) {
					if (cookie.getName().equals("dir_type")) {
						dir_type = cookie.getValue();
						
						break;
					}
				}
			}
		}
		
		Cookie c = new Cookie("dir_type", dir_type);
		c.setMaxAge(60 * 60 * 24 * 365);
		response.addCookie(c);
		
		Process process;
		String osName = System.getProperty("os.name");
		String[] cmd;
		String path = request.getRealPath("/") + File.separator + "images" + File.separator 
						+ "upload" + File.separator + "school" + File.separator 
						+ school_id;
		if (dir_type.equals("gallery")) {
			path += File.separator + dir_type;
		} else if (dir_type.equals("logo")) {
			path += File.separator;
		} 
		
		if(osName.toLowerCase().startsWith("window")) {
		    cmd = new String[] { "cmd.exe", "/c", "dir", path };
		} else {
		    cmd = new String[] { "/bin/sh", "/c", "ls -al", path };
		}

		StringBuffer buffer=new StringBuffer();	
		try {
			process = new ProcessBuilder(cmd).start();
			InputStream in = process.getInputStream(); 
			Scanner s = new Scanner(in,"EUC-KR");
			while (s.hasNextLine() == true) {
			    buffer.append(s.nextLine()+"\n");
			}
			s.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			buffer.append("실행오류발생");
			e.printStackTrace();
		} 
		
		JSONObject newsJson = new JSONObject();
		newsJson.put("school_id", school_id);
		newsJson.put("file_list", buffer.toString());
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

