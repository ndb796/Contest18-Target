package com.mycompany.myschool.web.controller;

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
import com.mycompany.myschool.web.entity.student.StudentSub1Entity;
import com.mycompany.myschool.web.entity.student.StudentEntity;
import com.mycompany.myschool.web.entity.vo.RecommendListVo;
import com.mycompany.myschool.web.entity.xml.XmlStudentSub1Data;
import com.mycompany.myschool.web.service.base.UserService;
import com.mycompany.myschool.web.service.student.StudentSub1Service;
import com.mycompany.myschool.web.service.student.RecommendService;
import com.mycompany.myschool.web.util.Constants;
import com.mycompany.myschool.web.util.DecCryptoUtil;
import com.mycompany.myschool.web.util.Token;
import com.mycompany.myschool.web.util.Util;

@Controller
@RequestMapping("/user")
public class StudentSub1Controller {
	@Autowired
	private UserService userService;
	
	@Autowired
	private StudentSub1Service curSchoolService;

	@Autowired
	private RecommendService rcmmdService;
	
	@RequestMapping("/myprofile/stdEducationAddForm")
    public ModelAndView educationAddForm(HttpServletRequest request, 
    					 HttpServletResponse response, 
			 			 Model model,
			 			@RequestParam("student_id") String student_id,
			 			HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		UserEntity userEntity = (UserEntity)session.getAttribute("login_user");
		
		if (userEntity != null) {
			StudentSub1Entity curSchoolEntity = curSchoolService.get(student_id);
			
			if (curSchoolEntity != null) {
				mav.setViewName("/user/myprofile/stdView2");
			} else {
				curSchoolEntity = new StudentSub1Entity();
				mav.setViewName("/user/myprofile/stdMypage2");
				mav.addObject("mode", "add");
				
				mav.addObject("cur_school_grade_map", getCurSchoolGradeMap());
			}
			
			StudentEntity studentEntity = new StudentEntity();
			studentEntity.setStudent_id(student_id);
			
			model.addAttribute("userEntity", userEntity);
			model.addAttribute("studentEntity", studentEntity);
			model.addAttribute("curSchoolEntity", curSchoolEntity);
		} else {
			mav.setViewName("redirect:/index.do");
		}
		
		/*
		 * Recommended school list
		 */
		List<RecommendListVo> list = getRcmmdListForStudent(userEntity.getUser_id());
		mav.addObject("school_list", list);
		
		mav.addObject("locale", Util.GET_LOCALE(request.getHeader("Accept-Language")));
		mav.addObject("context_name", Util.LOAD_CONFIG("context_name"));
		
		return mav;
    }
	
	@RequestMapping("/myprofile/stdEducationProc")
    public ModelAndView educationProc(@ModelAttribute("curSchoolEntity") StudentSub1Entity curSchoolEntity,
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
					curSchoolEntity.setRank(DecCryptoUtil.ENCRYPT(curSchoolEntity.getRank()));
					
					UserEntity dbUserEntity = userService.getByUserId(userEntity.getUser_id());
					byte[] reg_status = Util.STR2BYTEARR(dbUserEntity.getReg_status());
					reg_status[Constants.USER_STATUS_IDX_EDUC] = Constants.USER_STATUS_ON;
					dbUserEntity.setReg_status(Util.BYTEARR2STR(reg_status));
					userService.modifyStatus(dbUserEntity);
					
					curSchoolEntity.setStudent_id(student_id);
					
					if (request.getParameter("mode") != null && request.getParameter("mode").equals("modify")) {
						curSchoolService.modify(curSchoolEntity);
					} else {
						curSchoolService.add(curSchoolEntity);
					}
				}
			} catch (Exception e) {
				isError = true;
				e.printStackTrace();
			}
			
			if (isError) {
				mav.addObject("mode", "add");
				mav.addObject("curSchoolEntity", curSchoolEntity);
				mav.setViewName("/user/myprofile/stdMypage2");
			} else {
				mav.addObject("mode", "view");
				mav.addObject("student_id", curSchoolEntity.getStudent_id());
				
				StudentEntity studentEntity = new StudentEntity();
				studentEntity.setStudent_id(student_id);
				
				model.addAttribute("userEntity", userEntity);
				mav.addObject("studentEntity", studentEntity);
				
				try {
					curSchoolEntity.setRank(DecCryptoUtil.DECRYPT(curSchoolEntity.getRank()));	
				} catch (Exception e) {
					
				}
				
				mav.addObject("curSchoolEntity",curSchoolEntity);
				
				List<RecommendListVo> list = getRcmmdListForStudent(userEntity.getUser_id());
				mav.addObject("school_list", list);
				
				mav.setViewName("/user/myprofile/stdView2");
			}

			session.removeAttribute("token");
		} else {
			mav.setViewName("redirect:/index.do");
		}
		
		mav.addObject("locale", Util.GET_LOCALE(request.getHeader("Accept-Language")));
		mav.addObject("context_name", Util.LOAD_CONFIG("context_name"));
		
        return mav;
    }
	
	@RequestMapping("/myprofile/stdEducationView")
    public ModelAndView educationView(HttpServletRequest request,
								  @RequestParam("student_id") String student_id,
							 	  HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		UserEntity userEntity = (UserEntity)session.getAttribute("login_user");
		
		if (userEntity != null) {
			StudentSub1Entity curSchoolEntity = curSchoolService.get(student_id);
			
			if (curSchoolEntity != null) {
				mav.setViewName("/user/myprofile/stdView2");
				curSchoolEntity.setRank(DecCryptoUtil.DECRYPT(curSchoolEntity.getRank()));
			} else {
				curSchoolEntity = new StudentSub1Entity();
				mav.setViewName("/user/myprofile/stdMypage2");
				mav.addObject("mode", "add");
				
				mav.addObject("cur_school_grade_map", getCurSchoolGradeMap());
			}

			StudentEntity studentEntity = new StudentEntity();
			studentEntity.setStudent_id(student_id);
			
			mav.addObject("userEntity", userEntity);
			mav.addObject("studentEntity", studentEntity);
			mav.addObject("curSchoolEntity", curSchoolEntity);
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
	
	@RequestMapping("/myprofile/stdEducationModForm")
    public ModelAndView educationModForm(HttpServletRequest request, 
    					 HttpServletResponse response, 
			 			 Model model,
			 			@RequestParam("student_id") String student_id,
			 			HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		UserEntity userEntity = (UserEntity)session.getAttribute("login_user");
		
		if (userEntity != null) {
			StudentSub1Entity curSchoolEntity = curSchoolService.get(student_id);
			
			if (curSchoolEntity != null) {
				mav.addObject("mode", "modify");
				curSchoolEntity.setRank(DecCryptoUtil.DECRYPT(curSchoolEntity.getRank()));
			} else {
				curSchoolEntity = new StudentSub1Entity();
				curSchoolEntity.setStudent_id(student_id);
				mav.addObject("mode", "add");
			}
			
			StudentEntity studentEntity = new StudentEntity();
			studentEntity.setStudent_id(student_id);
			
			mav.addObject("userEntity", userEntity);
			mav.addObject("studentEntity", studentEntity);
			model.addAttribute("curSchoolEntity", curSchoolEntity);
			mav.addObject("cur_school_grade_map", getCurSchoolGradeMap());
			
			mav.setViewName("/user/myprofile/stdMypage2");
		} else {
			mav.setViewName("redirect:/index.do");
		}
		
		/*
		 * Recommended school list
		 */
		List<RecommendListVo> list = getRcmmdListForStudent(userEntity.getUser_id());
		mav.addObject("school_list", list);
		
		mav.addObject("locale", Util.GET_LOCALE(request.getHeader("Accept-Language")));
		mav.addObject("context_name", Util.LOAD_CONFIG("context_name"));
		
		return mav;
    }
	
	private SortedMap<String, String> getCurSchoolGradeMap() {
		SortedMap<String, String> cur_school_grade_map = new TreeMap<String, String>();  
		cur_school_grade_map.put("01", "초등학교1학년");
		cur_school_grade_map.put("02", "초등학교2학년");
		cur_school_grade_map.put("03", "초등학교3학년");
		cur_school_grade_map.put("04", "초등학교4학년");
		cur_school_grade_map.put("05", "초등학교5학년");
		cur_school_grade_map.put("06", "초등학교6학년");
		cur_school_grade_map.put("07", "중학교1학년");
		cur_school_grade_map.put("08", "중학교2학년");
		cur_school_grade_map.put("09", "중학교3학년");
		cur_school_grade_map.put("10", "고등학교1학년");
		cur_school_grade_map.put("11", "고등학교2학년");
		cur_school_grade_map.put("12", "고등학교3학년");
		cur_school_grade_map.put("13", "기타");
		
		return cur_school_grade_map;
	}
	
	@RequestMapping("/myprofile/curSchoolList/{datatype}")
    public @ResponseBody Object curSchoolList(@ModelAttribute StudentSub1Entity curSchoolEntity,
    									 @PathVariable String datatype, 
    									 HttpServletRequest request, 
    									 HttpServletResponse response,
    									 Model model) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		List<StudentSub1Entity> list = curSchoolService.list(curSchoolEntity);
		if ("xml".equals(datatype)) {
			XmlStudentSub1Data data = new XmlStudentSub1Data();
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
