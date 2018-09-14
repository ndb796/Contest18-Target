package com.mycompany.myschool.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

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
import com.mycompany.myschool.web.entity.student.StudentSub2Entity;
import com.mycompany.myschool.web.entity.student.StudentEntity;
import com.mycompany.myschool.web.entity.vo.RecommendListVo;
import com.mycompany.myschool.web.entity.xml.XmlStudentSub2Data;
import com.mycompany.myschool.web.service.base.UserService;
import com.mycompany.myschool.web.service.student.RecommendService;
import com.mycompany.myschool.web.service.student.StudentSub2Service;
import com.mycompany.myschool.web.util.Constants;
import com.mycompany.myschool.web.util.Token;
import com.mycompany.myschool.web.util.Util;

@Controller
@RequestMapping("/user")
public class StudentSub2Controller {
	@Autowired
	private UserService userService;
	
	@Autowired
	private StudentSub2Service specificService;
	
	@Autowired
	private RecommendService rcmmdService;
	
	@RequestMapping("/myprofile/stdSpecificAddForm")
    public ModelAndView specificAddForm(HttpServletRequest request, 
    					 HttpServletResponse response, 
			 			 Model model,
			 			@RequestParam("student_id") String student_id,
			 			HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		UserEntity userEntity = (UserEntity)session.getAttribute("login_user");
		
		if (userEntity != null) {
			StudentSub2Entity specificEntity = specificService.get(student_id);
			
			if (specificEntity != null) {
				mav.setViewName("/user/myprofile/stdView3");
			} else {
				specificEntity = new StudentSub2Entity();
				mav.setViewName("/user/myprofile/stdMypage3");
				mav.addObject("mode", "add");
				
				mav.addObject("school_ord_map", getSpecificOrdMap());
				mav.addObject("location_ord_map", getSpecificOrdMap());
				mav.addObject("subject_ord_map", getSpecificOrdMap());
				mav.addObject("job_ord_map", getSpecificOrdMap());
				mav.addObject("field_ord_map", getSpecificOrdMap());
				
				specificEntity.setSchool_ord(1);
				specificEntity.setLocation_ord(2);
				specificEntity.setSubject_ord(3);
				specificEntity.setJob_ord(4);
				specificEntity.setField_ord(5);
			}
			
			StudentEntity studentEntity = new StudentEntity();
			studentEntity.setStudent_id(student_id);
			
			model.addAttribute("userEntity", userEntity);
			model.addAttribute("studentEntity", studentEntity);
			model.addAttribute("specificEntity", specificEntity);
		} else {
			mav.setViewName("redirect:/index.do");
		}
		
		/*
		 * Recommended school list
		 */
		/*List<BranchListVo> branchList = getSchoolBranchList(userEntity.getUser_id());
		mav.addObject("branch_list", branchList);*/
		List<RecommendListVo> list = getRcmmdListForStudent(userEntity.getUser_id());
		mav.addObject("school_list", list);
		
		mav.addObject("locale", Util.GET_LOCALE(request.getHeader("Accept-Language")));
		mav.addObject("context_name", Util.LOAD_CONFIG("context_name"));
		
		return mav;
    }
	
	@RequestMapping("/myprofile/stdSpecificProc")
    public ModelAndView specificProc(@ModelAttribute("specificEntity") StudentSub2Entity specificEntity,
    								  @RequestParam("student_id") String student_id,
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
			
			boolean isError = false;
			
			try {
				if("TRUE".equals(request.getAttribute("TOKEN_SAVE_CHECK"))) {
					UserEntity dbUserEntity = userService.getByUserId(userEntity.getUser_id());
					byte[] reg_status = Util.STR2BYTEARR(dbUserEntity.getReg_status());
					reg_status[Constants.USER_STATUS_IDX_SPEC] = Constants.USER_STATUS_ON;
					dbUserEntity.setReg_status(Util.BYTEARR2STR(reg_status));
					userService.modifyStatus(dbUserEntity);
					
					specificEntity.setStudent_id(student_id);
					
					/*
					 * Check order
					 */
					/*Util.CHECK_SPECIFIC_ORDER(specificEntity);*/
					
					if (request.getParameter("mode") != null && request.getParameter("mode").equals("modify")) {
						specificService.modify(specificEntity);
					} else {
						specificService.add(specificEntity);
					}
				}
			} catch (Exception e) {
				isError = true;
				e.printStackTrace();
			}
			
			if (isError) {
				mav.addObject("mode", "add");
				mav.addObject("specificEntity", specificEntity);
				mav.setViewName("/user/myprofile/stdMypage3");
			} else {
				mav.addObject("mode", "view");
				
				List<String> school_lst = new ArrayList<String>();
				if (specificEntity.getSchool1() != null && specificEntity.getSchool1().length() > 0) school_lst.add(specificEntity.getSchool1());
				if (specificEntity.getSchool2() != null && specificEntity.getSchool2().length() > 0) school_lst.add(specificEntity.getSchool2());
				if (specificEntity.getSchool3() != null && specificEntity.getSchool3().length() > 0) school_lst.add(specificEntity.getSchool3());
				mav.addObject("school_lst",school_lst);
				
				List<String> location_lst = new ArrayList<String>();
				if (specificEntity.getLocation1() != null && specificEntity.getLocation1().length() > 0) location_lst.add(specificEntity.getLocation1());
				if (specificEntity.getLocation2() != null && specificEntity.getLocation2().length() > 0) location_lst.add(specificEntity.getLocation2());
				if (specificEntity.getLocation3() != null && specificEntity.getLocation3().length() > 0) location_lst.add(specificEntity.getLocation3());
				mav.addObject("location_lst",location_lst);
				
				List<String> subject_lst = new ArrayList<String>();
				if (specificEntity.getSubject1() != null && specificEntity.getSubject1().length() > 0) subject_lst.add(specificEntity.getSubject1());
				if (specificEntity.getSubject2() != null && specificEntity.getSubject2().length() > 0) subject_lst.add(specificEntity.getSubject2());
				if (specificEntity.getSubject3() != null && specificEntity.getSubject3().length() > 0) subject_lst.add(specificEntity.getSubject3());
				mav.addObject("subject_lst",subject_lst);
				
				List<String> job_lst = new ArrayList<String>();
				if (specificEntity.getJob1() != null && specificEntity.getJob1().length() > 0) job_lst.add(specificEntity.getJob1());
				if (specificEntity.getJob2() != null && specificEntity.getJob2().length() > 0) job_lst.add(specificEntity.getJob2());
				if (specificEntity.getJob3() != null && specificEntity.getJob3().length() > 0) job_lst.add(specificEntity.getJob3());
				mav.addObject("job_lst",job_lst);
				
				List<String> field_lst = new ArrayList<String>();
				if (specificEntity.getField1() != null && specificEntity.getField1().length() > 0) field_lst.add(specificEntity.getField1());
				if (specificEntity.getField2() != null && specificEntity.getField2().length() > 0) field_lst.add(specificEntity.getField2());
				if (specificEntity.getField3() != null && specificEntity.getField3().length() > 0) field_lst.add(specificEntity.getField3());
				mav.addObject("field_lst",field_lst);
				
				StudentEntity studentEntity = new StudentEntity();
				studentEntity.setStudent_id(student_id);
				
				mav.addObject("userEntity", userEntity);
				mav.addObject("studentEntity", studentEntity);
				mav.addObject("specificEntity", specificEntity);
				mav.setViewName("/user/myprofile/stdView3");
			}

			session.removeAttribute("token");
		} else {
			mav.setViewName("redirect:/index.do");
		}
		
		List<RecommendListVo> list = getRcmmdListForStudent(userEntity.getUser_id());
		mav.addObject("school_list", list);
		
		mav.addObject("locale", Util.GET_LOCALE(request.getHeader("Accept-Language")));
		mav.addObject("context_name", Util.LOAD_CONFIG("context_name"));
		
        return mav;
    }
	
	@RequestMapping("/myprofile/stdSpecificView")
    public ModelAndView specificView(HttpServletRequest request,
						    		@RequestParam("student_id") String student_id,
								 	HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		UserEntity userEntity = (UserEntity)session.getAttribute("login_user");
		
		if (userEntity != null) {
			StudentSub2Entity specificEntity = specificService.get(student_id);
			
			if (specificEntity != null) {
				mav.addObject("mode", "view");
				
				List<String> school_lst = new ArrayList<String>();
				if (specificEntity.getSchool1() != null && specificEntity.getSchool1().length() > 0) school_lst.add(specificEntity.getSchool1());
				if (specificEntity.getSchool2() != null && specificEntity.getSchool2().length() > 0) school_lst.add(specificEntity.getSchool2());
				if (specificEntity.getSchool3() != null && specificEntity.getSchool3().length() > 0) school_lst.add(specificEntity.getSchool3());
				mav.addObject("school_lst",school_lst);
				
				List<String> location_lst = new ArrayList<String>();
				if (specificEntity.getLocation1() != null && specificEntity.getLocation1().length() > 0) location_lst.add(specificEntity.getLocation1());
				if (specificEntity.getLocation2() != null && specificEntity.getLocation2().length() > 0) location_lst.add(specificEntity.getLocation2());
				if (specificEntity.getLocation3() != null && specificEntity.getLocation3().length() > 0) location_lst.add(specificEntity.getLocation3());
				mav.addObject("location_lst",location_lst);
				
				List<String> subject_lst = new ArrayList<String>();
				if (specificEntity.getSubject1() != null && specificEntity.getSubject1().length() > 0) subject_lst.add(specificEntity.getSubject1());
				if (specificEntity.getSubject2() != null && specificEntity.getSubject2().length() > 0) subject_lst.add(specificEntity.getSubject2());
				if (specificEntity.getSubject3() != null && specificEntity.getSubject3().length() > 0) subject_lst.add(specificEntity.getSubject3());
				mav.addObject("subject_lst",subject_lst);
				
				List<String> job_lst = new ArrayList<String>();
				if (specificEntity.getJob1() != null && specificEntity.getJob1().length() > 0) job_lst.add(specificEntity.getJob1());
				if (specificEntity.getJob2() != null && specificEntity.getJob2().length() > 0) job_lst.add(specificEntity.getJob2());
				if (specificEntity.getJob3() != null && specificEntity.getJob3().length() > 0) job_lst.add(specificEntity.getJob3());
				mav.addObject("job_lst",job_lst);
				
				List<String> field_lst = new ArrayList<String>();
				if (specificEntity.getField1() != null && specificEntity.getField1().length() > 0) field_lst.add(specificEntity.getField1());
				if (specificEntity.getField2() != null && specificEntity.getField2().length() > 0) field_lst.add(specificEntity.getField2());
				if (specificEntity.getField3() != null && specificEntity.getField3().length() > 0) field_lst.add(specificEntity.getField3());
				mav.addObject("field_lst",field_lst);
				
				mav.setViewName("/user/myprofile/stdView3");
			} else {
				specificEntity = new StudentSub2Entity();
				mav.addObject("mode", "add");
				
				mav.addObject("school_ord_map", getSpecificOrdMap());
				mav.addObject("location_ord_map", getSpecificOrdMap());
				mav.addObject("subject_ord_map", getSpecificOrdMap());
				mav.addObject("job_ord_map", getSpecificOrdMap());
				mav.addObject("field_ord_map", getSpecificOrdMap());
				
				specificEntity.setSchool_ord(1);
				specificEntity.setLocation_ord(2);
				specificEntity.setSubject_ord(3);
				specificEntity.setJob_ord(4);
				specificEntity.setField_ord(5);
				
				mav.setViewName("/user/myprofile/stdMypage3");
			}

			StudentEntity studentEntity = new StudentEntity();
			studentEntity.setStudent_id(student_id);
			
			mav.addObject("userEntity", userEntity);
			mav.addObject("studentEntity", studentEntity);
			mav.addObject("specificEntity", specificEntity);
		} else {
			mav.setViewName("redirect:/index.do");
		}
		
		/*
		 * Recommended school list
		 */
		/*List<BranchListVo> branchList = getSchoolBranchList(userEntity.getUser_id());
		mav.addObject("branch_list", branchList);*/
		List<RecommendListVo> list = getRcmmdListForStudent(userEntity.getUser_id());
		mav.addObject("school_list", list);
		
		mav.addObject("locale", Util.GET_LOCALE(request.getHeader("Accept-Language")));
		mav.addObject("context_name", Util.LOAD_CONFIG("context_name"));
		
        return mav;
    }
	
	@RequestMapping("/myprofile/stdSpecificModForm")
    public ModelAndView specificModForm(HttpServletRequest request, 
    					 HttpServletResponse response, 
			 			 Model model,
			 			@RequestParam("student_id") String student_id,
			 			HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		UserEntity userEntity = (UserEntity)session.getAttribute("login_user");
		
		if (userEntity != null) {
			mav.setViewName("/user/myprofile/stdMypage3");
			
			/*
			 * DB에서 Specific 정보 취득 with student_id
			 */
			StudentSub2Entity specificEntity = specificService.get(student_id);
			
			if (specificEntity != null) {
				mav.addObject("mode", "modify");
			} else {
				specificEntity = new StudentSub2Entity();
				specificEntity.setStudent_id(student_id);
				mav.addObject("mode", "add");
			}
			
			mav.addObject("school_ord_map", getSpecificOrdMap());
			mav.addObject("location_ord_map", getSpecificOrdMap());
			mav.addObject("subject_ord_map", getSpecificOrdMap());
			mav.addObject("job_ord_map", getSpecificOrdMap());
			mav.addObject("field_ord_map", getSpecificOrdMap());
			
			StudentEntity studentEntity = new StudentEntity();
			studentEntity.setStudent_id(student_id);
			
			model.addAttribute("userEntity", userEntity);
			model.addAttribute("studentEntity", studentEntity);
			model.addAttribute("specificEntity", specificEntity);
		} else {
			mav.setViewName("redirect:/index.do");
		}
		
		/*
		 * Recommended school list
		 */
		/*List<BranchListVo> branchList = getSchoolBranchList(userEntity.getUser_id());
		mav.addObject("branch_list", branchList);*/
		List<RecommendListVo> list = getRcmmdListForStudent(userEntity.getUser_id());
		mav.addObject("school_list", list);
		
		mav.addObject("locale", Util.GET_LOCALE(request.getHeader("Accept-Language")));
		mav.addObject("context_name", Util.LOAD_CONFIG("context_name"));
		
		return mav;
    }
	
	private SortedMap<String, String> getSpecificOrdMap() {
		SortedMap<String, String> specific_ord_map = new TreeMap<String, String>();  
		specific_ord_map.put("1", "1");
		specific_ord_map.put("2", "2");
		specific_ord_map.put("3", "3");
		specific_ord_map.put("4", "4");
		specific_ord_map.put("5", "5");
		
		return specific_ord_map;
	}
	
	@RequestMapping("/myprofile/specificList/{datatype}")
    public @ResponseBody Object specificList(@ModelAttribute StudentSub2Entity specificEntity,
    									 @PathVariable String datatype, 
    									 HttpServletRequest request, 
    									 HttpServletResponse response,
    									 Model model) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		List<StudentSub2Entity> list = specificService.list(specificEntity);
		if ("xml".equals(datatype)) {
			XmlStudentSub2Data data = new XmlStudentSub2Data();
	        data.setItems(list);
	        
	        model.addAttribute("data", data);
	        
	        return data;
		} else {
			model.addAttribute("data", list);
			return list;
		}
    }
	
	private List<RecommendListVo> getRcmmdListForStudent(String student_id) {
		List<RecommendListVo> list = rcmmdService.listRcmmdSchoolListViewByStudentId(student_id);
		
		int totalCnt = list.size();
		for (RecommendListVo entity : list) {
			entity.setNo(totalCnt--);
		}
		
		return list;
    }
}
