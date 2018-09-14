package com.mycompany.myschool.web.controller.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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
import com.mycompany.myschool.web.entity.student.StudentSub1Entity;
import com.mycompany.myschool.web.entity.student.StudentSub2Entity;
import com.mycompany.myschool.web.entity.student.StudentEntity;
import com.mycompany.myschool.web.service.base.UserService;
import com.mycompany.myschool.web.service.student.StudentSub1Service;
import com.mycompany.myschool.web.service.student.RecommendService;
import com.mycompany.myschool.web.service.student.StudentSub2Service;
import com.mycompany.myschool.web.service.student.StudentService;
import com.mycompany.myschool.web.util.ResultCode;
import com.mycompany.myschool.web.util.Util;

@Controller
public class AdminStudentController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private StudentSub1Service curSchoolService;
	
	@Autowired
	private StudentSub2Service specificService;

	@Autowired
	private RecommendService recommendService;
	
	/*
	 * User
	 */
	@RequestMapping("/ds_admin/student")
    public ModelAndView student(Model model, 
    							  HttpServletRequest request,
    							  HttpSession session) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("menu", request.getParameter("menu"));
		
		UserEntity admin_user = (UserEntity)session.getAttribute("admin_user");
		if (admin_user != null) {
			StudentEntity studentEntity = new StudentEntity();
			
			List<StudentEntity> studentList = studentService.list(studentEntity);
			for (StudentEntity entity : studentList) {
				StudentSub1Entity curSchoolEntity = curSchoolService.get(entity.getStudent_id());
				StudentSub2Entity specificEntity = specificService.get(entity.getStudent_id());
				
				int rcmmdCnt = recommendService.getCountByStudentId(entity.getStudent_id());
				
				if (curSchoolEntity != null) {
					entity.setStudent_sub1_entity(curSchoolEntity);
				}
				
				if (specificEntity != null) {
					entity.setStudent_sub2_entity(specificEntity);
				}
				
				entity.setRcmmd_cnt(rcmmdCnt);
			}
			
			mav.addObject("student_list", studentList);
			mav.addObject("locale", Util.GET_LOCALE(request.getHeader("Accept-Language")));
			mav.addObject("context_name", Util.LOAD_CONFIG("context_name"));
			
			mav.setViewName("/ds_admin/student");
		} else {
			mav.setViewName("redirect:/ds_admin/login.do?resultCode=" + ResultCode.USER_ERR_LOGIN_CODE);
		}
		
        return mav;
	}
	
	@RequestMapping("/ds_admin/student/{student_id}")
    public void studentView1(@PathVariable("student_id") String student_id,
    							Model model,
    		 			   		HttpServletRequest request,
    		 			   		HttpServletResponse response) {
		String callback = request.getParameter("jsoncallback");
		
		StudentEntity studentEntity = studentService.getByStudentId(student_id);
		StudentSub1Entity curSchoolEntity = curSchoolService.get(student_id);
		StudentSub2Entity specificEntity = specificService.get(student_id);
		
		JSONObject newsJson = new JSONObject();
		/*
		 * Basic info
		 */
		newsJson.put("idx", studentEntity.getIdx());
		newsJson.put("student_id", studentEntity.getStudent_id());
		newsJson.put("bac_ins_date", (studentEntity.getIns_date_str().equals("0000-00-00 00:00:00") ? "" : studentEntity.getIns_date_str()));
		newsJson.put("bac_upd_date", (studentEntity.getUpd_date_str().equals("0000-00-00 00:00:00") ? "" : studentEntity.getUpd_date_str()));
		
		newsJson.put("fname", studentEntity.getFname());
		newsJson.put("lname", studentEntity.getLname());
		newsJson.put("gender", studentEntity.getGender());
		newsJson.put("rcmmd_type", studentEntity.getRcmmd_type());
		newsJson.put("birth_y", studentEntity.getBirth_y());
		newsJson.put("birth_m", studentEntity.getBirth_m());
		newsJson.put("birth_d", studentEntity.getBirth_d());
		newsJson.put("zipcode", studentEntity.getZipcode());
		newsJson.put("address1", studentEntity.getAddress1());
		newsJson.put("address2", studentEntity.getAddress2());
		newsJson.put("address3", studentEntity.getAddress3());
		newsJson.put("address4", studentEntity.getAddress4());
		
		/*
		 * Currrent school info
		 */
		if (curSchoolEntity != null) {
			newsJson.put("is_curschool_entity", "yes");
			newsJson.put("cur_ins_date", (curSchoolEntity.getIns_date_str().equals("0000-00-00 00:00:00") ? "" : curSchoolEntity.getIns_date_str()));
			newsJson.put("cur_upd_date", (curSchoolEntity.getUpd_date_str().equals("0000-00-00 00:00:00") ? "" : curSchoolEntity.getUpd_date_str()));
			newsJson.put("cur_school_type1", curSchoolEntity.getCur_school_type1());
			newsJson.put("cur_school_name", curSchoolEntity.getCur_school_name());
			newsJson.put("cur_school_grade", curSchoolEntity.getCur_school_grade());
			newsJson.put("lang_score", curSchoolEntity.getLang_score());
			newsJson.put("hist_score", curSchoolEntity.getHist_score());
			newsJson.put("eng_score", curSchoolEntity.getEng_score());
			newsJson.put("civil_score", curSchoolEntity.getCivil_score());
			newsJson.put("mat_score", curSchoolEntity.getMat_score());
			newsJson.put("sci_score", curSchoolEntity.getSci_score());
			newsJson.put("rank", curSchoolEntity.getRank());
		} else {
			newsJson.put("is_curschool_entity", "no");
		}
		
		/*
		 * Specific info
		 */
		if (specificEntity != null) {
			newsJson.put("is_specific_entity", "yes");
			newsJson.put("spc_ins_date", (specificEntity.getIns_date_str().equals("0000-00-00 00:00:00") ? "" : specificEntity.getIns_date_str()));
			newsJson.put("spc_upd_date", (specificEntity.getUpd_date_str().equals("0000-00-00 00:00:00") ? "" : specificEntity.getUpd_date_str()));
			newsJson.put("school1", specificEntity.getSchool1());
			newsJson.put("school2", specificEntity.getSchool2());
			newsJson.put("school3", specificEntity.getSchool3());
			newsJson.put("school_ord", specificEntity.getSchool_ord());
			newsJson.put("location1", specificEntity.getLocation1());
			newsJson.put("location2", specificEntity.getLocation2());
			newsJson.put("location3", specificEntity.getLocation3());
			newsJson.put("location_ord", specificEntity.getLocation_ord());
			newsJson.put("subject1", specificEntity.getSubject1());
			newsJson.put("subject2", specificEntity.getSubject2());
			newsJson.put("subject3", specificEntity.getSubject3());
			newsJson.put("subject_ord", specificEntity.getSubject_ord());
			newsJson.put("job1", specificEntity.getJob1());
			newsJson.put("job2", specificEntity.getJob2());
			newsJson.put("job3", specificEntity.getJob3());
			newsJson.put("job_ord", specificEntity.getJob_ord());
			newsJson.put("field1", specificEntity.getField1());
			newsJson.put("field2", specificEntity.getField2());
			newsJson.put("field3", specificEntity.getField3());
			newsJson.put("field_ord", specificEntity.getField_ord());
		} else {
			newsJson.put("is_specific_entity", "no");
		}
		
		try {
			PrintWriter out = response.getWriter();
			out.write(callback + "(" + newsJson.toString() + ")");
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	@RequestMapping("/ds_admin/student/studentModProc.do")
    public ModelAndView studentModProc(HttpServletRequest request, 
    									 Model model,
    									 HttpSession session,
    									 HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		String idx = request.getParameter("idx");
		String student_id = request.getParameter("student_id");
		
		String isCurUpdated = request.getParameter("isCurUpdated");
		String isSpcUpdated = request.getParameter("isSpcUpdated");
		
		StudentEntity studentEntity = new StudentEntity();
		studentEntity.setStudent_id(student_id);
		studentEntity.setFname((request.getParameter("fname")));
		studentEntity.setLname((request.getParameter("lname")));
		studentEntity.setGender(request.getParameter("gender"));
		String paramRcmmdType = request.getParameter("rcmmd_type");
		int rcmmd_type = 0;
		if (paramRcmmdType != null) {
			rcmmd_type = Integer.parseInt(paramRcmmdType);
		}
		studentEntity.setRcmmd_type(rcmmd_type);
		studentEntity.setBirth_y(request.getParameter("birth_y"));
		studentEntity.setBirth_m(request.getParameter("birth_m"));
		studentEntity.setBirth_d(request.getParameter("birth_d"));
		studentEntity.setZipcode(request.getParameter("zipcode"));
		studentEntity.setAddress1((request.getParameter("address1")));
		studentEntity.setAddress2((request.getParameter("address2")));
		studentEntity.setAddress3((request.getParameter("address3")));
		studentEntity.setAddress4((request.getParameter("address4")));
		studentService.modify(studentEntity);
		
		if (isCurUpdated != null && isCurUpdated.equals("true")) {
			StudentSub1Entity curSchoolEntity = new StudentSub1Entity();
			curSchoolEntity.setStudent_id(student_id);
			String paramSchoolType = request.getParameter("cur_school_type1");
			int school_type = 0;
			if (paramSchoolType != null) {
				school_type = Integer.parseInt(paramSchoolType);
			}
			curSchoolEntity.setCur_school_type1(school_type);
			curSchoolEntity.setCur_school_name((request.getParameter("cur_school_name")));
			String paramSchoolGrade = request.getParameter("cur_school_grade");
			int school_grade = 0;
			if (paramSchoolGrade != null) {
				school_grade = Integer.parseInt(paramSchoolGrade);
			}
			curSchoolEntity.setCur_school_grade(school_grade);
			String paramLangScore = request.getParameter("lang_score");
			float lang_score = 0;
			if (paramLangScore != null) {
				lang_score = Float.parseFloat(paramLangScore);
			}
			curSchoolEntity.setLang_score(lang_score);
			String paramEngScore = request.getParameter("eng_score");
			float eng_score = 0;
			if (paramLangScore != null) {
				eng_score = Float.parseFloat(paramEngScore);
			}
			curSchoolEntity.setEng_score(eng_score);
			String paramHistScore = request.getParameter("hist_score");
			float hist_score = 0;
			if (paramHistScore != null) {
				hist_score = Float.parseFloat(paramHistScore);
			}
			curSchoolEntity.setHist_score(hist_score);
			String paramCivilScore = request.getParameter("civil_score");
			float civil_score = 0;
			if (paramCivilScore != null) {
				civil_score = Float.parseFloat(paramCivilScore);
			}
			curSchoolEntity.setCivil_score(civil_score);
			String paramMatScore = request.getParameter("mat_score");
			float mat_score = 0;
			if (paramMatScore != null) {
				mat_score = Float.parseFloat(paramMatScore);
			}
			curSchoolEntity.setMat_score(mat_score);
			String paramSciScore = request.getParameter("sci_score");
			float sci_score = 0;
			if (paramSciScore != null) {
				sci_score = Float.parseFloat(paramSciScore);
			}
			curSchoolEntity.setSci_score(sci_score);
			curSchoolEntity.setRank(request.getParameter("rank"));
			
			curSchoolService.modify(curSchoolEntity);
		}
		
		if (isSpcUpdated != null && isSpcUpdated.equals("true")) {
			StudentSub2Entity specificEntity = new StudentSub2Entity();
			specificEntity.setStudent_id(student_id);
			specificEntity.setSchool1((request.getParameter("school1")));
			specificEntity.setSchool2((request.getParameter("school2")));
			specificEntity.setSchool3((request.getParameter("school3")));
			String paramSchoolOrd = request.getParameter("school_ord");
			int school_ord = 0;
			if (paramSchoolOrd != null) {
				school_ord = Integer.parseInt(paramSchoolOrd);
			}
			specificEntity.setSchool_ord(school_ord);
			specificEntity.setLocation1((request.getParameter("location1")));
			specificEntity.setLocation2((request.getParameter("location2")));
			specificEntity.setLocation3((request.getParameter("location3")));
			String paramLocationOrd = request.getParameter("location_ord");
			int location_ord = 0;
			if (paramLocationOrd != null) {
				location_ord = Integer.parseInt(paramLocationOrd);
			}
			specificEntity.setLocation_ord(location_ord);
			specificEntity.setSubject1((request.getParameter("subject1")));
			specificEntity.setSubject2((request.getParameter("subject2")));
			specificEntity.setSubject3((request.getParameter("subject3")));
			String paramSubjectOrd = request.getParameter("subject_ord");
			int subject_ord = 0;
			if (paramSubjectOrd != null) {
				subject_ord = Integer.parseInt(paramSubjectOrd);
			}
			specificEntity.setSubject_ord(subject_ord);
			specificEntity.setJob1((request.getParameter("job1")));
			specificEntity.setJob2((request.getParameter("job2")));
			specificEntity.setJob3((request.getParameter("job3")));
			String paramJobOrd = request.getParameter("job_ord");
			int job_ord = 0;
			if (paramJobOrd != null) {
				job_ord = Integer.parseInt(paramJobOrd);
			}
			specificEntity.setJob_ord(job_ord);
			specificEntity.setField1((request.getParameter("field1")));
			specificEntity.setField2((request.getParameter("field2")));
			specificEntity.setField3((request.getParameter("field3")));
			String paramFieldOrd = request.getParameter("field_ord");
			int field_ord = 0;
			if (paramFieldOrd != null) {
				field_ord = Integer.parseInt(paramFieldOrd);
			}
			specificEntity.setField_ord(field_ord);
			
			specificService.modify(specificEntity);
		}
		
		List<StudentEntity> studentList = studentService.list(new StudentEntity());
		for (StudentEntity entity : studentList) {
			StudentSub1Entity curSchoolEntity = curSchoolService.get(entity.getStudent_id());
			StudentSub2Entity specificEntity = specificService.get(entity.getStudent_id());
			
			int rcmmdCnt = recommendService.getCountByStudentId(entity.getStudent_id());
			
			if (curSchoolEntity != null) {
				entity.setStudent_sub1_entity(curSchoolEntity);
			}
			
			if (specificEntity != null) {
				entity.setStudent_sub2_entity(specificEntity);
			}
			
			entity.setRcmmd_cnt(rcmmdCnt);
		}
		
		mav.addObject("student_list", studentList);
		mav.addObject("locale", Util.GET_LOCALE(request.getHeader("Accept-Language")));
		mav.addObject("context_name", Util.LOAD_CONFIG("context_name"));
		
		mav.setViewName("/ds_admin/student");
		
		return mav;
	}
}

