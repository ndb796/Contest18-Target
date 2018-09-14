package com.mycompany.myschool.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import com.mycompany.myschool.web.entity.customer.CustomerSub2Entity;
import com.mycompany.myschool.web.entity.customer.CustomerEntity;
import com.mycompany.myschool.web.entity.xml.XmlSchoolSub2Data;
import com.mycompany.myschool.web.service.base.UserService;
import com.mycompany.myschool.web.service.customer.CustomerSub2Service;
import com.mycompany.myschool.web.service.customer.CustomerService;
import com.mycompany.myschool.web.util.Constants;
import com.mycompany.myschool.web.util.Token;
import com.mycompany.myschool.web.util.Util;

@Controller
@RequestMapping("/customer")
public class CustomerSub2Controller {
	@Autowired
	private UserService userService;
	
	@Autowired
	private CustomerSub2Service customerChargerService;
	
	@Autowired
	private CustomerService customerService;
	
	@RequestMapping("/myprofile/schChargerAddForm")
    public ModelAndView chargerAddForm(HttpServletRequest request, 
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
			
			CustomerSub2Entity schoolChargerEntity = customerChargerService.getBySchoolId(school_id);
			
			if (schoolChargerEntity != null) {
				mav.setViewName("/customer/myprofile/schView3");
			} else {
				schoolChargerEntity = new CustomerSub2Entity();
				mav.setViewName("/customer/myprofile/schMypage3");
				mav.addObject("mode", "add");
			}
			
			/*
			 * Retrieve branch count
			 */
			int bracnCount = customerService.getBranchCount(school_id);
			mav.addObject("branch_count", bracnCount);
			
			model.addAttribute("schoolChargerEntity", schoolChargerEntity);
		} else {
			mav.setViewName("redirect:/index.do");
		}
		
		mav.addObject("locale", Util.GET_LOCALE(request.getHeader("Accept-Language")));
		mav.addObject("context_name", Util.LOAD_CONFIG("context_name"));
		
		return mav;
    }
	
	@RequestMapping("/myprofile/schChargerProc")
    public ModelAndView chargerProc(@ModelAttribute("schoolChargerEntity") CustomerSub2Entity schoolChargerEntity,
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
//			    System.out.println("################ Token valid=" + request);
			} else {
				request.setAttribute("TOKEN_SAVE_CHECK", "FALSE");
//				System.out.println("################ Token invalid=" + request);
			}
			
			if("TRUE".equals(request.getAttribute("TOKEN_SAVE_CHECK"))) {
				UserEntity dbUserEntity = userService.getByUserId(userEntity.getUser_id());
				byte[] reg_status = Util.STR2BYTEARR(dbUserEntity.getReg_status());
				reg_status[Constants.USER_STATUS_IDX_SPEC] = Constants.USER_STATUS_ON;
				dbUserEntity.setReg_status(Util.BYTEARR2STR(reg_status));
				userService.modifyStatus(dbUserEntity);
				
				schoolChargerEntity.setSchool_id(school_id);
				
				if (request.getParameter("mode") != null && request.getParameter("mode").equals("modify")) {
					customerChargerService.modify(schoolChargerEntity);
				} else {
					customerChargerService.add(schoolChargerEntity);
				}
			}
			
			mav.addObject("mode", "view");
			
			CustomerEntity schoolEntity = new CustomerEntity();
			schoolEntity.setSchool_id(school_id);
			
			mav.addObject("schoolEntity", schoolEntity);
			mav.addObject("schoolChargerEntity", schoolChargerEntity);
			mav.setViewName("/customer/myprofile/schView3");

			session.removeAttribute("token");
		} else {
			mav.setViewName("redirect:/index.do");
		}
		
		mav.addObject("locale", Util.GET_LOCALE(request.getHeader("Accept-Language")));
		mav.addObject("context_name", Util.LOAD_CONFIG("context_name"));
		
        return mav;
    }
	
	@RequestMapping("/myprofile/schChargerView")
    public ModelAndView chargerView(HttpServletRequest request,
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
			
			CustomerSub2Entity schoolChargerEntity = customerChargerService.getBySchoolId(school_id);
			
			if (schoolChargerEntity != null) {
				mav.setViewName("/customer/myprofile/schView3");
				mav.addObject("mode", "view");
			} else {
				schoolChargerEntity = new CustomerSub2Entity();
				mav.setViewName("/customer/myprofile/schMypage3");
				mav.addObject("mode", "add");
				
			}

			/*
			 * Retrieve branch count
			 */
			int bracnCount = customerService.getBranchCount(school_id);
			mav.addObject("branch_count", bracnCount);
			
			mav.addObject("schoolChargerEntity", schoolChargerEntity);
		} else {
			mav.setViewName("redirect:/index.do");
		}
		
		mav.addObject("locale", Util.GET_LOCALE(request.getHeader("Accept-Language")));
		mav.addObject("context_name", Util.LOAD_CONFIG("context_name"));
		
        return mav;
    }
	
	@RequestMapping("/myprofile/schChargerModForm")
    public ModelAndView chargerModForm(HttpServletRequest request, 
    					 HttpServletResponse response, 
			 			 Model model,
			 			@RequestParam("school_id") String school_id,
			 			HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		UserEntity userEntity = (UserEntity)session.getAttribute("login_user");
		
		if (userEntity != null) {
			mav.setViewName("/customer/myprofile/schMypage3");
			
			CustomerSub2Entity schoolChargerEntity = customerChargerService.getBySchoolId(school_id);
			
			if (schoolChargerEntity != null) {
				mav.addObject("mode", "modify");
			} else {
				schoolChargerEntity = new CustomerSub2Entity();
				schoolChargerEntity.setSchool_id(school_id);
				mav.addObject("mode", "add");
			}
			
			CustomerEntity schoolEntity = new CustomerEntity();
			schoolEntity.setSchool_id(school_id);
			
			/*
			 * Retrieve branch count
			 */
			int bracnCount = customerService.getBranchCount(school_id);
			mav.addObject("branch_count", bracnCount);
			
			model.addAttribute("schoolEntity", schoolEntity);
			model.addAttribute("schoolChargerEntity", schoolChargerEntity);
		} else {
			mav.setViewName("redirect:/index.do");
		}
		
		mav.addObject("locale", Util.GET_LOCALE(request.getHeader("Accept-Language")));
		mav.addObject("context_name", Util.LOAD_CONFIG("context_name"));
		
		return mav;
    }
	
	@RequestMapping("/myprofile/chargerList/{datatype}")
    public @ResponseBody Object chargerList(@ModelAttribute CustomerSub2Entity schoolChargerEntity,
    									 @PathVariable String datatype, 
    									 HttpServletRequest request, 
    									 HttpServletResponse response,
    									 Model model) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		List<CustomerSub2Entity> list = customerChargerService.list(schoolChargerEntity);
		if ("xml".equals(datatype)) {
			XmlSchoolSub2Data data = new XmlSchoolSub2Data();
	        data.setItems(list);
	        
	        model.addAttribute("data", data);
	        
	        return data;
		} else {
			model.addAttribute("data", list);
			return list;
		}
    }
}
