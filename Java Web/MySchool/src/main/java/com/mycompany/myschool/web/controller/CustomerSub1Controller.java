package com.mycompany.myschool.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mycompany.myschool.web.entity.base.UserEntity;
import com.mycompany.myschool.web.entity.customer.CustomerSub1Entity;
import com.mycompany.myschool.web.entity.customer.CustomerEntity;
import com.mycompany.myschool.web.entity.xml.XmlSchooSub1Data;
import com.mycompany.myschool.web.service.base.UserService;
import com.mycompany.myschool.web.service.customer.CustomerSub1Service;
import com.mycompany.myschool.web.service.customer.CustomerService;
import com.mycompany.myschool.web.util.Constants;
import com.mycompany.myschool.web.util.Token;
import com.mycompany.myschool.web.util.Util;

@Controller
@RequestMapping("/customer")
public class CustomerSub1Controller {
	@Autowired
	private UserService userService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private CustomerSub1Service customerDetailService;
	
	@RequestMapping("/myprofile/schDetailAddForm")
    public ModelAndView detailAddForm(HttpServletRequest request, 
    					 				HttpServletResponse response, 
    					 				Model model,
    					 				@RequestParam("school_id") String school_id,
    					 				HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		UserEntity userEntity = (UserEntity)session.getAttribute("login_user");
		
		if (userEntity != null) {
			CustomerEntity schoolEntity = customerService.get(school_id);
			if (schoolEntity == null) {
				schoolEntity = new CustomerEntity();
				schoolEntity.setSchool_id(school_id);
			}
			
			mav.addObject("schoolEntity", schoolEntity);
			
			CustomerSub1Entity schoolDetailEntity = customerDetailService.getBySchoolId(school_id);
			
			if (schoolDetailEntity != null) {
				mav.setViewName("/customer/myprofile/schView2");
			} else {
				schoolDetailEntity = new CustomerSub1Entity();
				mav.setViewName("/customer/myprofile/schMypage2");
				mav.addObject("mode", "add");
			}
			
			/*
			 * Retrieve branch count
			 */
			int bracnCount = customerService.getBranchCount(school_id);
			mav.addObject("branch_count", bracnCount);
			
			model.addAttribute("curSchoolEntity", schoolEntity);
		} else {
			mav.setViewName("redirect:/index.do");
		}
		
		mav.addObject("locale", Util.GET_LOCALE(request.getHeader("Accept-Language")));
		mav.addObject("context_name", Util.LOAD_CONFIG("context_name"));
		
		return mav;
    }
	
	@RequestMapping("/myprofile/schDetailProc")
    public ModelAndView detailProc(@ModelAttribute("schoolDetailEntity") CustomerSub1Entity schoolDetailEntity,
    								  @RequestParam("school_id") String school_id,
    								  Model model,
									  HttpServletRequest request,
									  HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		UserEntity userEntity = (UserEntity)session.getAttribute("login_user");
		
		if (userEntity != null) {
			if(Token.isValid(request)) {
			    Token.set(request);
			    request.setAttribute("TOKEN_SAVE_CHECK", "TRUE");
			} else {
				request.setAttribute("TOKEN_SAVE_CHECK", "FALSE");
			}
			
			if("TRUE".equals(request.getAttribute("TOKEN_SAVE_CHECK"))) {
				UserEntity dbUserEntity = userService.getByUserId(userEntity.getUser_id());
				byte[] reg_status = Util.STR2BYTEARR(dbUserEntity.getReg_status());
				reg_status[Constants.USER_STATUS_IDX_EDUC] = Constants.USER_STATUS_ON;
				dbUserEntity.setReg_status(Util.BYTEARR2STR(reg_status));
				userService.modifyStatus(dbUserEntity);
				
				schoolDetailEntity.setSchool_id(school_id);
				
				if (request.getParameter("mode") != null && request.getParameter("mode").equals("modify")) {
					customerDetailService.modify(schoolDetailEntity);
				} else {
					customerDetailService.add(schoolDetailEntity);
				}
			}
			
			mav.addObject("mode", "view");
			mav.addObject("school_id", schoolDetailEntity.getSchool_id());
			
			CustomerEntity schoolEntity = new CustomerEntity();
			schoolEntity.setSchool_id(school_id);
			mav.addObject("schoolEntity", schoolEntity);
			mav.addObject("schoolDetailEntity",schoolDetailEntity);
			mav.setViewName("/customer/myprofile/schView2");

			session.removeAttribute("token");
		} else {
			mav.setViewName("redirect:/index.do");
		}
		
		mav.addObject("locale", Util.GET_LOCALE(request.getHeader("Accept-Language")));
		mav.addObject("context_name", Util.LOAD_CONFIG("context_name"));
		
        return mav;
    }
	
	@RequestMapping("/myprofile/schDetailView")
    public ModelAndView educationView(HttpServletRequest request,
								  		@RequestParam("school_id") String school_id,
								  		HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		UserEntity userEntity = (UserEntity)session.getAttribute("login_user");
		
		if (userEntity != null) {
			CustomerEntity schoolEntity = customerService.get(school_id);
			if (schoolEntity == null) {
				schoolEntity = new CustomerEntity();
				schoolEntity.setSchool_id(school_id);
			}
			
			mav.addObject("schoolEntity", schoolEntity);
			
			CustomerSub1Entity schoolDetailEntity = customerDetailService.getBySchoolId(school_id);
			
			if (schoolDetailEntity != null) {
				mav.setViewName("/customer/myprofile/schView2");
			} else {
				schoolDetailEntity = new CustomerSub1Entity();
				mav.setViewName("/customer/myprofile/schMypage2");
				mav.addObject("mode", "add");
			}

			String[] maps = Util.GET_GOOGLE_MAP(schoolDetailEntity.getMap());
			mav.addObject("lat", maps[0]);
			mav.addObject("lng", maps[1]);
			
			/*
			 * Retrieve branch count
			 */
			int bracnCount = customerService.getBranchCount(school_id);
			mav.addObject("branch_count", bracnCount);
			
			mav.addObject("userEntity", userEntity);
			mav.addObject("schoolDetailEntity", schoolDetailEntity);
		} else {
			mav.setViewName("redirect:/index.do");
		}
		
		mav.addObject("locale", Util.GET_LOCALE(request.getHeader("Accept-Language")));
		mav.addObject("context_name", Util.LOAD_CONFIG("context_name"));
		
        return mav;
    }
	
	@RequestMapping("/myprofile/schDetailModForm")
    public ModelAndView educationModForm(HttpServletRequest request, 
					    					HttpServletResponse response, 
								 			Model model,
								 			@RequestParam("school_id") String school_id,
								 			HttpSession session) {		
		ModelAndView mav = new ModelAndView();
		
		UserEntity userEntity = (UserEntity)session.getAttribute("login_user");
		
		if (userEntity != null) {
			CustomerSub1Entity schoolDetailEntity = customerDetailService.getBySchoolId(school_id);
			
			if (schoolDetailEntity != null) {
				mav.addObject("mode", "modify");
			} else {
				schoolDetailEntity = new CustomerSub1Entity();
				schoolDetailEntity.setSchool_id(school_id);
				mav.addObject("mode", "add");
			}
			
			String[] maps = Util.GET_GOOGLE_MAP(schoolDetailEntity.getMap());
			mav.addObject("lat", maps[0]);
			mav.addObject("lng", maps[1]);
			
			CustomerEntity schoolEntity = new CustomerEntity();
			schoolEntity.setSchool_id(school_id);
			
			/*
			 * Retrieve branch count
			 */
			int bracnCount = customerService.getBranchCount(school_id);
			mav.addObject("branch_count", bracnCount);
			
			mav.addObject("schoolEntity", schoolEntity);
			model.addAttribute("schoolDetailEntity", schoolDetailEntity);
			
			mav.setViewName("/customer/myprofile/schMypage2");
		} else {
			mav.setViewName("redirect:/index.do");
		}
		
		mav.addObject("locale", Util.GET_LOCALE(request.getHeader("Accept-Language")));
		mav.addObject("context_name", Util.LOAD_CONFIG("context_name"));
		
		return mav;
    }
	
	@RequestMapping("/myprofile/detailList/{datatype}")
    public @ResponseBody Object studentList(@ModelAttribute CustomerSub1Entity schoolDetailEntity,
    									 @PathVariable String datatype, 
    									 HttpServletRequest request, 
    									 HttpServletResponse response,
    									 Model model) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		List<CustomerSub1Entity> list = customerDetailService.list(schoolDetailEntity);
		if ("xml".equals(datatype)) {
			XmlSchooSub1Data data = new XmlSchooSub1Data();
	        data.setItems(list);
	        
	        model.addAttribute("data", data);
	        
	        return data;
		} else {
			model.addAttribute("data", list);
			return list;
		}
    }
	
	@RequestMapping("/myprofile/detailList/jsonpTest")
    public void jsonpTest(@ModelAttribute CustomerSub1Entity schoolDetailEntity,
							 HttpServletRequest request,
							 HttpServletResponse response, 
							 Model model) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		
		String callback = request.getParameter("callback");
		System.out.println("callback=" + callback + "," + request.getRemoteAddr());
		
		List<CustomerSub1Entity> list = customerDetailService.list(schoolDetailEntity);
		JSONObject obj = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		
		for (CustomerSub1Entity student : list) {
			JSONObject schooDetaillJson = new JSONObject();
			schooDetaillJson.put("idx", student.getIdx());
			schooDetaillJson.put("school_id", student.getSchool_id());
			schooDetaillJson.put("features", student.getFeatures());
			schooDetaillJson.put("courses", student.getCourses());
			schooDetaillJson.put("fee", student.getFee());
			schooDetaillJson.put("financial", student.getFinancial());
			schooDetaillJson.put("selection", student.getSelection());
			schooDetaillJson.put("map", student.getMap());
			schooDetaillJson.put("ins_date", student.getIns_date());
			
			jsonArray.put(schooDetaillJson);
		}
		
		obj.put("items", jsonArray);
		
		try {
			PrintWriter out = response.getWriter();
			
			out.write(callback + "(" + jsonArray.toString() + ")");
//			System.out.println(callback + "(" + jsonArray.toString() + ")");
			
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}

