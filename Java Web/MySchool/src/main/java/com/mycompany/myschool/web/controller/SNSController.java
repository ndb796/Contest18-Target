package com.mycompany.myschool.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mycompany.myschool.web.entity.base.UserEntity;
import com.mycompany.myschool.web.entity.customer.CustomerEntity;
import com.mycompany.myschool.web.entity.sns.SnsConsultEntity;
import com.mycompany.myschool.web.entity.student.StudentEntity;
import com.mycompany.myschool.web.entity.vo.RecommendListVo;
import com.mycompany.myschool.web.service.customer.CustomerService;
import com.mycompany.myschool.web.service.sns.SnsConsultService;
import com.mycompany.myschool.web.service.student.RecommendService;
import com.mycompany.myschool.web.service.student.StudentService;
import com.mycompany.myschool.web.util.Constants;
import com.mycompany.myschool.web.util.ResultCode;
import com.mycompany.myschool.web.util.Token;
import com.mycompany.myschool.web.util.Util;

@Controller
public class SNSController {
	@Autowired
	private RecommendService rcmmdService;
	
	@Autowired
	private SnsConsultService snsConsultService;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private CustomerService customerService;
	
	/*
	 * Test
	 */
	@RequestMapping("/user/sns/snsMessageOriginal")
    public ModelAndView snsMessageOriginal(	 HttpServletRequest request, 
    									 Model model,
 					 					 HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("locale", Util.GET_LOCALE(request.getHeader("Accept-Language")));
		mav.addObject("context_name", Util.LOAD_CONFIG("context_name"));
		
		return mav;
    }
	
	/* 
	 * Student SNS
	 */
	@RequestMapping("/user/sns/snsMessage")
    public ModelAndView snsMessage(	 HttpServletRequest request, 
    									 Model model,
 					 					 HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		UserEntity userEntity = (UserEntity)session.getAttribute("login_user");
		
		if (userEntity != null) {		// login 페이로부터 이동 
			StudentEntity studentEntity = studentService.getByStudentId(userEntity.getUser_id());
			
			if (studentEntity == null) {
				studentEntity = new StudentEntity();
				studentEntity.setStudent_id(userEntity.getUser_id());
				studentEntity.setIdx(1);
			}
			
			CustomerEntity schoolEntity = new CustomerEntity();
			schoolEntity.setSchool_id(userEntity.getUser_id());
			
			model.addAttribute("userEntity", userEntity);
			model.addAttribute("studentEntity", studentEntity);
			model.addAttribute("schoolEntity", schoolEntity);
			
			/*
			 * Recommend setting
			 */
			List<RecommendListVo> list = getRcmmdListByStudentId(userEntity.getUser_id());
			mav.addObject("rcmmd_list", list);
			
		} else {
			mav.setViewName("redirect:/index.do?resultCode=" + ResultCode.USER_ERR_NOTLOGIN_CODE);
		}
		
		mav.addObject("locale", Util.GET_LOCALE(request.getHeader("Accept-Language")));
		mav.addObject("context_name", Util.LOAD_CONFIG("context_name"));
		
		return mav;
    }
	
	@RequestMapping("/user/sns/snsNewsfeed")
    public ModelAndView snsNewsfeed(	 HttpServletRequest request, 
    									 Model model,
 					 					 HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		UserEntity userEntity = (UserEntity)session.getAttribute("login_user");
		
		if (userEntity != null) {		// login 페이로부터 이동 
			StudentEntity studentEntity = new StudentEntity();
			studentEntity.setStudent_id(userEntity.getUser_id());
			CustomerEntity schoolEntity = new CustomerEntity();
			schoolEntity.setSchool_id(userEntity.getUser_id());
			
			model.addAttribute("userEntity", userEntity);
			model.addAttribute("studentEntity", studentEntity);
			model.addAttribute("schoolEntity", schoolEntity);
			
		} else {
			mav.setViewName("redirect:/index.do?resultCode=" + ResultCode.USER_ERR_NOTLOGIN_CODE);
		}
		
		mav.addObject("locale", Util.GET_LOCALE(request.getHeader("Accept-Language")));
		mav.addObject("context_name", Util.LOAD_CONFIG("context_name"));
		
		return mav;
    }
	
	@RequestMapping("/user/sns/rcmmdList/jsonpTest")
    public void getRecommendList(HttpServletRequest request,
    									 HttpServletResponse response, 
    									 Model model) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		
		String callback = request.getParameter("callback");
		System.out.println("callback=" + callback + "," + request.getRemoteAddr());
		
		String student_id = request.getParameter("student_id");
		
		List<RecommendListVo> list = rcmmdService.listRcmmdSchoolListViewByStudentId(student_id);
		
		int totalCnt = list.size();
		for (RecommendListVo entity : list) {
			entity.setNo(totalCnt--);
		}
		
		JSONObject obj = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		
		for (RecommendListVo vo : list) {
			JSONObject rcmmdJson = new JSONObject();
			rcmmdJson.put("no", vo.getNo());
			rcmmdJson.put("sb_id", vo.getSb_id());
			rcmmdJson.put("sb_name", vo.getSb_name());
			rcmmdJson.put("school_type", vo.getSchool_type());
			rcmmdJson.put("rcmmd_type", vo.getRcmmd_type());
			rcmmdJson.put("const_status", vo.getConst_status());
			rcmmdJson.put("const_status_msg", Constants.CONST_STATUS_MSG[vo.getConst_status()]);
			rcmmdJson.put("ins_date", vo.getIns_date());
			
			jsonArray.put(rcmmdJson);
		}
		
		obj.put("items", jsonArray);
		
		try {
			PrintWriter out = response.getWriter();
			
			out.write(callback + "(" + jsonArray.toString() + ")");
			
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	@RequestMapping("/user/sns/snsMessageDialog")
    public ModelAndView snsMessageDialog(HttpServletRequest request, 
								 Model model,
								 HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		UserEntity userEntity = (UserEntity)session.getAttribute("login_user");
		
		if (userEntity != null) {		// login 페이로부터 이동 
			StudentEntity studentEntity = new StudentEntity();
			studentEntity.setStudent_id(userEntity.getUser_id());
			CustomerEntity schoolEntity = new CustomerEntity();
			schoolEntity.setSchool_id(userEntity.getUser_id());
			
			model.addAttribute("userEntity", userEntity);
			model.addAttribute("studentEntity", studentEntity);
			model.addAttribute("schoolEntity", schoolEntity);
			
			// read sns consult data
			SnsConsultEntity conditionEntity = new SnsConsultEntity();
			conditionEntity.setSb_id(request.getParameter("sb_id"));
			conditionEntity.setStudent_id(userEntity.getUser_id());
			List<SnsConsultEntity> constList = snsConsultService.listByStudentId(conditionEntity);
			model.addAttribute("constList", constList);
			
			String sb_name = request.getParameter("sb_name");
			if (sb_name == null || sb_name.length() == 0) {
				if (constList != null && constList.size() > 0) {
					sb_name = constList.get(0).getSb_id() + "와의 상담내용";
				} else {
					sb_name = "상담내용이 없습니다.";
				}
			} else {
//				try {
//					sb_name = new String(sb_name.getBytes("8859_1"), "UTF-8") + "와의 상담내용";
					sb_name = sb_name + "와의 상담내용";
//				} catch (UnsupportedEncodingException e) {
//					e.printStackTrace();
//				}
			}
			
			model.addAttribute("aite", sb_name);
		} else {
			mav.setViewName("redirect:/index.do?resultCode=" + ResultCode.USER_ERR_NOTLOGIN_CODE);
		}
		
		mav.addObject("locale", Util.GET_LOCALE(request.getHeader("Accept-Language")));
		mav.addObject("context_name", Util.LOAD_CONFIG("context_name"));
		
		return mav;
    }
	
	@RequestMapping("/user/sns/snsConsultProc")
    public ModelAndView snsConsultProc(@ModelAttribute("snsConsultEntity") SnsConsultEntity snsConsultEntity,
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
			
			//if("TRUE".equals(request.getAttribute("TOKEN_SAVE_CHECK"))) {
				// sns consult save
				snsConsultEntity.setConst_status(Constants.CONST_STATUS_REQ);
				snsConsultService.add(snsConsultEntity);
			//}
			
			StudentEntity studentEntity = new StudentEntity();
			studentEntity.setStudent_id(userEntity.getUser_id());
			CustomerEntity schoolEntity = new CustomerEntity();
			schoolEntity.setSchool_id(userEntity.getUser_id());
			
			model.addAttribute("userEntity", userEntity);
			model.addAttribute("studentEntity", studentEntity);
			model.addAttribute("schoolEntity", schoolEntity);
			
			// read sns consult data
			SnsConsultEntity conditionEntity = new SnsConsultEntity();
			conditionEntity.setSb_id(request.getParameter("sb_id"));
			conditionEntity.setStudent_id(userEntity.getUser_id());
			List<SnsConsultEntity> constList = snsConsultService.listByStudentId(conditionEntity);
			model.addAttribute("constList", constList);
						
			mav.setViewName("/user/sns/snsMessageDialog");

			session.removeAttribute("token");
		} else {
			mav.setViewName("redirect:/index.do");
		}
		
		mav.addObject("locale", Util.GET_LOCALE(request.getHeader("Accept-Language")));
		mav.addObject("context_name", Util.LOAD_CONFIG("context_name"));
		
        return mav;
    }
	
	/* 
	 * Customer SNS
	 */
	@RequestMapping("/customer/sns/snsMessage")
    public ModelAndView customerSnsMessage(	 HttpServletRequest request, 
    									 Model model,
 					 					 HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		UserEntity userEntity = (UserEntity)session.getAttribute("login_user");
		
		if (userEntity != null) {		// login 페이로부터 이동 
			CustomerEntity schoolEntity = customerService.get(userEntity.getUser_id());
			
			if (schoolEntity == null) {
				schoolEntity = new CustomerEntity();
				schoolEntity.setSchool_id(userEntity.getUser_id());
				schoolEntity.setIdx(1);
			}
			
			StudentEntity studentEntity = new StudentEntity();
			studentEntity.setStudent_id(userEntity.getUser_id());
			
			model.addAttribute("userEntity", userEntity);
			model.addAttribute("studentEntity", studentEntity);
			model.addAttribute("schoolEntity", schoolEntity);
			
			/*
			 * Recommend setting
			 */
			List<RecommendListVo> list = getRcmmdListBySchoolId(userEntity.getUser_id());
			mav.addObject("rcmmd_list", list);
			
		} else {
			mav.setViewName("redirect:/index.do?resultCode=" + ResultCode.USER_ERR_NOTLOGIN_CODE);
		}
		
		mav.addObject("locale", Util.GET_LOCALE(request.getHeader("Accept-Language")));
		mav.addObject("context_name", Util.LOAD_CONFIG("context_name"));
		
		return mav;
    }
	
	@RequestMapping("/customer/sns/snsNewsfeed")
    public ModelAndView customerSnsNewsfeed(	 HttpServletRequest request, 
    									 Model model,
 					 					 HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		UserEntity userEntity = (UserEntity)session.getAttribute("login_user");
		
		if (userEntity != null) {		// login 페이로부터 이동 
			StudentEntity studentEntity = new StudentEntity();
			studentEntity.setStudent_id(userEntity.getUser_id());
			CustomerEntity schoolEntity = new CustomerEntity();
			schoolEntity.setSchool_id(userEntity.getUser_id());
			
			model.addAttribute("userEntity", userEntity);
			model.addAttribute("studentEntity", studentEntity);
			model.addAttribute("schoolEntity", schoolEntity);
			
		} else {
			mav.setViewName("redirect:/index.do?resultCode=" + ResultCode.USER_ERR_NOTLOGIN_CODE);
		}
		
		mav.addObject("locale", Util.GET_LOCALE(request.getHeader("Accept-Language")));
		mav.addObject("context_name", Util.LOAD_CONFIG("context_name"));
		
		return mav;
    }
	
	@RequestMapping("/customer/sns/rcmmdList/jsonpTest")
    public void customerGetRecommendList(HttpServletRequest request,
    									 HttpServletResponse response, 
    									 Model model) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		
		String callback = request.getParameter("callback");
		System.out.println("callback=" + callback + "," + request.getRemoteAddr());
		
		String school_id = request.getParameter("school_id");
		
		List<RecommendListVo> list = rcmmdService.listRcmmdBySchoolId(school_id);
		
		int totalCnt = list.size();
		for (RecommendListVo entity : list) {
			entity.setNo(totalCnt--);
		}
		
		JSONObject obj = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		
		for (RecommendListVo vo : list) {
			JSONObject rcmmdJson = new JSONObject();
			rcmmdJson.put("no", vo.getNo());
			rcmmdJson.put("student_id", vo.getStudent_id());
			rcmmdJson.put("student_name", vo.getStudent_name());
			rcmmdJson.put("rcmmd_type", vo.getRcmmd_type());
			rcmmdJson.put("const_status", vo.getConst_status());
			rcmmdJson.put("const_status_msg", Constants.CONST_STATUS_MSG[vo.getConst_status()]);
			rcmmdJson.put("ins_date", vo.getIns_date());
			
			jsonArray.put(rcmmdJson);
		}
		
		obj.put("items", jsonArray);
		
		try {
			PrintWriter out = response.getWriter();
			
			out.write(callback + "(" + jsonArray.toString() + ")");
			System.out.println(callback + "(" + jsonArray.toString() + ")");
			
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	@RequestMapping("/customer/sns/snsMessageDialog")
    public ModelAndView customerSnsMessageDialog(HttpServletRequest request, 
								 Model model,
								 HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		UserEntity userEntity = (UserEntity)session.getAttribute("login_user");
		
		if (userEntity != null) {		// login 페이로부터 이동 
			StudentEntity studentEntity = new StudentEntity();
			studentEntity.setStudent_id(userEntity.getUser_id());
			CustomerEntity schoolEntity = new CustomerEntity();
			schoolEntity.setSchool_id(userEntity.getUser_id());
			
			model.addAttribute("userEntity", userEntity);
			model.addAttribute("studentEntity", studentEntity);
			model.addAttribute("schoolEntity", schoolEntity);
			
			// read sns consult data
			SnsConsultEntity conditionEntity = new SnsConsultEntity();
			conditionEntity.setStudent_id(request.getParameter("student_id"));
			conditionEntity.setSb_id(request.getParameter("sb_id") == null || request.getParameter("sb_id") .isEmpty() ? request.getParameter("school_id") : request.getParameter("sb_id"));
			List<SnsConsultEntity> constList = snsConsultService.listBySchoolId(conditionEntity);
			model.addAttribute("constList", constList);
			
			String student_name = request.getParameter("student_name");
			if (student_name == null || student_name.length() == 0) {
				if (constList != null && constList.size() > 0) {
					student_name = constList.get(0).getSb_id() + "와 상담내용";
				} else {
					student_name = "상담내용이 없습니다.";
				}
			} else {
				try {
					student_name = new String(student_name.getBytes("8859_1"), "UTF-8") + "와 상담내용";
				} catch (UnsupportedEncodingException e) {
					
				}
			}
			
			model.addAttribute("aite", student_name);
		} else {
			mav.setViewName("redirect:/index.do?resultCode=" + ResultCode.USER_ERR_NOTLOGIN_CODE);
		}
		
		mav.addObject("locale", Util.GET_LOCALE(request.getHeader("Accept-Language")));
		mav.addObject("context_name", Util.LOAD_CONFIG("context_name"));
		
		return mav;
    }
	
	@RequestMapping("/customer/sns/snsConsultProc")
    public ModelAndView customerSnsConsultProc(@ModelAttribute("snsConsultEntity") SnsConsultEntity snsConsultEntity,
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
				// sns consult save
				snsConsultEntity.setConst_status(Constants.CONST_STATUS_RES);
				snsConsultService.add(snsConsultEntity);
			}
			
			StudentEntity studentEntity = new StudentEntity();
			studentEntity.setStudent_id(userEntity.getUser_id());
			CustomerEntity schoolEntity = new CustomerEntity();
			schoolEntity.setSchool_id(userEntity.getUser_id());
			
			model.addAttribute("userEntity", userEntity);
			model.addAttribute("studentEntity", studentEntity);
			model.addAttribute("schoolEntity", schoolEntity);
			
			// read sns consult data
			SnsConsultEntity conditionEntity = new SnsConsultEntity();
			conditionEntity.setStudent_id(request.getParameter("student_id"));
			conditionEntity.setSb_id(request.getParameter("sb_id"));
			List<SnsConsultEntity> constList = snsConsultService.listBySchoolId(conditionEntity);
			model.addAttribute("constList", constList);
						
			mav.setViewName("/customer/sns/snsMessageDialog");

			session.removeAttribute("token");
		} else {
			mav.setViewName("redirect:/index.do");
		}
		
		mav.addObject("locale", Util.GET_LOCALE(request.getHeader("Accept-Language")));
		mav.addObject("context_name", Util.LOAD_CONFIG("context_name"));
		
        return mav;
    }
	
	private List<RecommendListVo> getRcmmdListByStudentId(String student_id) {
		List<RecommendListVo> list = rcmmdService.listRcmmdSchoolListViewByStudentId(student_id);
		
		int totalCnt = list.size();
		for (RecommendListVo entity : list) {
			entity.setNo(totalCnt--);
			entity.setConst_status_msg(Constants.CONST_STATUS_MSG[entity.getConst_status()]);
		}
		
		return list;
	}
	
	private List<RecommendListVo> getRcmmdListBySchoolId(String school_id) {
		List<RecommendListVo> list = rcmmdService.listRcmmdBySchoolId(school_id);
		
		int totalCnt = list.size();
		for (RecommendListVo entity : list) {
			entity.setNo(totalCnt--);
			entity.setConst_status_msg(Constants.CONST_STATUS_MSG[entity.getConst_status()]);
		}
		
		return list;
	}
}
