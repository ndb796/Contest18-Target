package com.mycompany.myschool.web.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mycompany.myschool.web.entity.base.UserEntity;
import com.mycompany.myschool.web.entity.student.StudentSub1Entity;
import com.mycompany.myschool.web.entity.student.StudentEntity;
import com.mycompany.myschool.web.entity.vo.BranchListVo;
import com.mycompany.myschool.web.entity.vo.RecommendListVo;
import com.mycompany.myschool.web.entity.xml.XmlStudentData;
import com.mycompany.myschool.web.service.base.UserService;
import com.mycompany.myschool.web.service.customer.CustomerBranchService;
import com.mycompany.myschool.web.service.student.StudentSub1Service;
import com.mycompany.myschool.web.service.student.RecommendService;
import com.mycompany.myschool.web.service.student.StudentService;
import com.mycompany.myschool.web.util.Constants;
import com.mycompany.myschool.web.util.MD5Util;
import com.mycompany.myschool.web.util.ResultCode;
import com.mycompany.myschool.web.util.Token;
import com.mycompany.myschool.web.util.Util;

@Controller
@RequestMapping("/user")
public class StudentController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private StudentSub1Service curSchoolService;
	
	@Autowired
	private RecommendService rcmmdService;
	
	@Autowired
	private CustomerBranchService customerBranchService;
	
	@RequestMapping("/myprofile/myPath")
    public ModelAndView myPath(HttpServletRequest request, 
    							   HttpServletResponse response, 
			 					   Model model) {
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("student_id", request.getParameter("student_id"));
		mav.addObject("context_name", Util.LOAD_CONFIG("context_name"));
		
		return mav;
    }
	
	@RequestMapping("/myprofile/myPathJson")
    public void myPathJosn(HttpServletRequest request,
    									 HttpServletResponse response, 
    									 HttpSession session,
    									 Model model) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		String callback = request.getParameter("callback");
		JSONObject obj = new JSONObject();
		
		JSONObject preJson = new JSONObject();
		JSONObject meJson = new JSONObject();
		JSONObject nodeJson = new JSONObject();
		JSONObject nextJson = new JSONObject();
		
		JSONObject prevRelation = new JSONObject();
		JSONObject nextRelation = new JSONObject();
		
		JSONObject meRelation = new JSONObject();
		JSONObject edgesJson = new JSONObject();
		JSONObject parentJson = new JSONObject();
		
		preJson.put("borders", "1");
		preJson.put("length", "1");
		preJson.put("region", "Deviewstory2");
		
		meJson.put("borders", "2");
		meJson.put("length", "2");
		meJson.put("region", "Deviewstory3");
		UserEntity userEntity = (UserEntity)session.getAttribute("login_user");
		StudentSub1Entity curSchoolEntity = curSchoolService.get(userEntity.getUser_id());
		StudentEntity studentEntity = studentService.getByStudentId(userEntity.getUser_id());
		
		int currrentSchoolType = 0;
		
		if (studentEntity != null) {
			int nextSchoolType = studentEntity.getRcmmd_type();
			
			List<RecommendListVo> rcmmdList = getRcmmdListForStudent(userEntity.getUser_id());
			
			/*if (curSchoolEntity != null) {
				currrentSchoolType = curSchoolEntity.getCur_school_type1();
				
				nodeJson.put(curSchoolEntity.getCur_school_name(), preJson);
				//meRelation.put(curSchoolEntity.getCur_school_name(), "");
				//prevRelation.put(curSchoolEntity.getCur_school_name(), "");
			} else {*/
				if (rcmmdList == null || rcmmdList.size() == 0) {
					nodeJson.put("추천된 학교가 없습니다.", preJson);
					meRelation.put("추천된 학교가 없습니다.", "");
				}
			/*}*/
			
			nextJson.put("borders", "3");
			nextJson.put("length", "3");
			nextJson.put("region", "Deviewstory1");
			
			for (RecommendListVo rcmmdVo : rcmmdList) {
				nextJson.put("link", Util.LOAD_CONFIG("context_name") + "/user/school/schoolDetail.do?sb_id=" + rcmmdVo.getSb_id());
				nodeJson.put(rcmmdVo.getSchool_name(), nextJson);
				nextRelation.put(rcmmdVo.getSchool_name(), "");
			}
			
			meRelation.put(Constants.SCHOOL_TYPE_STR[nextSchoolType], nextRelation);
			
			nodeJson.put("학생", meJson);
			nodeJson.put(Constants.SCHOOL_TYPE_STR[nextSchoolType], nextJson);
			edgesJson.put("학생", meRelation);
			edgesJson.put(Constants.SCHOOL_TYPE_STR[nextSchoolType], nextRelation);
			
			parentJson.put("nodes", nodeJson);
			parentJson.put("edges", edgesJson);
			
			obj.put("items", parentJson);
		}
		
		try {
			PrintWriter out = response.getWriter();
			
			out.write(callback + "(" + parentJson.toString() + ")");
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	@RequestMapping("/hello_json")
    public void helloWorld(HttpServletRequest request, 
    							   HttpServletResponse response, 
			 					   Model model) {
		String callback = request.getParameter("jsoncallback");
		String email = request.getParameter("email");
		
		JSONObject obj = new JSONObject();
		obj.put("email", email);
		
		try {
			PrintWriter out = response.getWriter();
			
			out.write(callback + "(" + obj.toString() + ")");
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	@RequestMapping("/hello")
    public ModelAndView hello() {
		String message = "Hello World, Spring 3.0";
		
		return new ModelAndView("/user/hello", "message", message);
    }
	
	@RequestMapping("/myprofile/stdBasicAddForm")
    public ModelAndView studentRegister1(HttpServletRequest request, 
    					 					HttpServletResponse response, 
    					 					Model model,
    					 					HttpSession session) {
		ModelAndView mav = new ModelAndView();
		UserEntity userEntity = (UserEntity)session.getAttribute("login_user");
		
		if (userEntity != null) {		// login 페이로부터 이동 
			/*
			 * Setting mode
			 */
			mav.addObject("mode", "add");
			StudentEntity studentEntity = new StudentEntity();
			studentEntity.setStudent_id(userEntity.getUser_id());
			
			UserEntity dbUserEntity = userService.getByUserId(userEntity.getUser_id());
			model.addAttribute("userEntity", dbUserEntity);
			model.addAttribute("studentEntity", studentEntity);
			
			/*
			 * Recommended school list
			 */
			List<BranchListVo> branchList = getSchoolBranchList(userEntity.getUser_id());
			mav.addObject("branch_list", branchList);
			
			/*
			 * Setting date
			 */
			mav.addObject("birth_y_map", Util.GET_BIRTH_Y_MAP(1950, 2007));
			mav.addObject("birth_m_map", Util.GET_BIRTH_MD_MAP(12));
			mav.addObject("birth_d_map", Util.GET_BIRTH_MD_MAP(31));
			
			mav.addObject("context_name", Util.LOAD_CONFIG("context_name"));
			
			mav.setViewName("/user/myprofile/stdMypage1");
		} else {
			mav.setViewName("redirect:/index.do?resultCode=" + ResultCode.USER_ERR_NOTLOGIN_CODE);
		}
		
		mav.addObject("locale", Util.GET_LOCALE(request.getHeader("Accept-Language")));
		mav.addObject("context_name", Util.LOAD_CONFIG("context_name"));
		
		return mav;
    }

	@RequestMapping("/myprofile/stdBasicProc")
    public ModelAndView studentRegister2(@ModelAttribute("studentEntity") StudentEntity studentEntity,
    								  Model model,
									  HttpServletRequest request,
									  HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		String mode = request.getParameter("mode");
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
					String tempFilename = studentEntity.getStudent_id() + "_" + studentEntity.getEmail() + "_" + System.currentTimeMillis();
					boolean isExistFile = false;
					System.out.println(studentEntity.getPhoto_file() == null ? "NULL" : studentEntity.getPhoto_file());
					if(studentEntity.getPhoto_file() != null && !studentEntity.getPhoto_file().isEmpty()){
				        String filename = studentEntity.getPhoto_file().getOriginalFilename();
				        String imgExt = filename.substring(filename.lastIndexOf(".")+1, filename.length());
				        tempFilename += "." + imgExt.toLowerCase();
				        
			            byte[] bytes = studentEntity.getPhoto_file().getBytes();
			            try {
				            	File dir = new File(request.getRealPath("/") + "/images/upload/student/");
				            	if (!dir.exists()) {
				            		dir.mkdirs();
				                }
				            	File outFile = new File(dir, Util.PATH_FILTER(tempFilename));
				            	FileOutputStream fos = new FileOutputStream(outFile);
				                fos.write(bytes);
				                outFile.setExecutable(true, false);
				                
				                studentEntity.setFilename(tempFilename);
				                studentEntity.setFilesize(bytes.length);
				                 
				                fos.close();
				                 
				                isExistFile = true;
				            } catch(IOException ie) {
				            	ie.printStackTrace();
				            	isError = true;
				                System.err.println("File writing error! ");
				            }
				            System.err.println("File upload success! ");

				    } else {
				    	isExistFile = false;
				    }
					
					UserEntity dbUserEntity = userService.getByUserId(userEntity.getUser_id());
					byte[] reg_status = Util.STR2BYTEARR(dbUserEntity.getReg_status());
					reg_status[Constants.USER_STATUS_IDX_BASC] = Constants.USER_STATUS_ON;
					dbUserEntity.setReg_status(Util.BYTEARR2STR(reg_status));
					userService.modifyStatus(dbUserEntity);
					
					mav.addObject("userEntity",dbUserEntity);
					
					if (studentEntity.getZipcode() != null && studentEntity.getZipcode().length() == 7) {
						studentEntity.setZipcode(studentEntity.getZipcode().substring(0, 3) + "-" + studentEntity.getZipcode().substring(3));
					} 
					
					if (mode != null && mode.equals("add")) {
//						studentEntity.setPwd(MD5Util.GET_CRYPTO_MD5(studentEntity.getPwd()));
						studentService.add(studentEntity);
					} else {
						studentService.modify(studentEntity);
					}
				}
			} catch (Exception e) {
				isError = true;
				e.printStackTrace();
			}
			
			if (isError) {
				mav.addObject("mode", mode);
				mav.addObject("student_entity", studentEntity);
				mav.setViewName("/user/myprofile/stdMypage1");
			} else {
				StudentEntity dbStudentEntity = studentService.getByStudentId(studentEntity.getStudent_id());
//				String prefix = studentEntity.getPwd().substring(0, 2);
//				String surfix = studentEntity.getPwd().substring(2);
//				StringBuffer astrix = new StringBuffer();
//				for (int i=0; i<surfix.length(); i++) {
//					astrix.append("*"); 
//				}
//				dbStudentEntity.setPwd(prefix + astrix.toString());
				
				mav.addObject("mode", "view");
				mav.addObject("student_id", studentEntity.getStudent_id());
				mav.addObject("studentEntity", dbStudentEntity);
				
				List<RecommendListVo> list = getRcmmdListForStudent(userEntity.getUser_id());
				mav.addObject("school_list", list);
				
				mav.setViewName("/user/myprofile/stdView1");
			}

			session.removeAttribute("token");
		} else {
			mav.setViewName("redirect:/index.do");
		}
		
		mav.addObject("locale", Util.GET_LOCALE(request.getHeader("Accept-Language")));
		mav.addObject("context_name", Util.LOAD_CONFIG("context_name"));
		
        return mav;
    }
	
	@RequestMapping("/myprofile/stdBasicView")
    public ModelAndView studentView1(HttpServletRequest request,
									  HttpSession session) {
		ModelAndView mav = new ModelAndView();
		UserEntity userEntity = (UserEntity)session.getAttribute("login_user");
		String student_id = request.getParameter("student_id");
		
		if (userEntity != null || (student_id != null && !student_id.isEmpty())) {
			String prefix = userEntity.getPwd().substring(0, 2);
			String surfix = userEntity.getPwd().substring(2);
			StringBuffer astrix = new StringBuffer();
			for (int i=0; i<surfix.length(); i++) {
				astrix.append("*"); 
			}
			
			UserEntity dbUserEntity = userService.getByUserId(student_id);
			dbUserEntity.setPwd(prefix + astrix.toString());
			mav.addObject("userEntity", dbUserEntity);
			
			StudentEntity studentEntity = studentService.getByStudentId(student_id);
			if (studentEntity != null) {
				mav.addObject("mode", "view");
				
				System.out.println("filename=" + studentEntity.getFilename());
				
				List<RecommendListVo> list = getRcmmdListForStudent(userEntity.getUser_id());
				mav.addObject("school_list", list);
				
				mav.setViewName("/user/myprofile/stdView1");
			} else {
				mav.addObject("mode", "add");
				
				studentEntity = new StudentEntity();
				studentEntity.setStudent_id(student_id);
				
				/*
				 * Setting date
				 */
				mav.addObject("birth_y_map", Util.GET_BIRTH_Y_MAP(Integer.parseInt(Util.LOAD_CONFIG("birth_start_year")), Integer.parseInt(Util.LOAD_CONFIG("birth_end_year"))));
				mav.addObject("birth_m_map", Util.GET_BIRTH_MD_MAP(12));
				mav.addObject("birth_d_map", Util.GET_BIRTH_MD_MAP(31));
				
				mav.setViewName("/user/myprofile/stdMypage1");
			}
			
			/*
			 * Recommended school list
			 */
			List<RecommendListVo> rcmmdList = null;
			if (studentEntity != null) {
				int nextSchoolType = studentEntity.getRcmmd_type();
				
				rcmmdList = getRcmmdListForStudent(userEntity.getUser_id());
				
				if (rcmmdList == null || rcmmdList.size() == 0) {
					rcmmdList = new ArrayList<RecommendListVo>();
					RecommendListVo vo = new RecommendListVo();
					vo.setSchool_name("추천된 학교가 없습니다.");
					rcmmdList.add(vo);
				} else {
					for (RecommendListVo rcmmdVo : rcmmdList) {
						rcmmdVo.setSchool_link("/user/school/schoolDetail.do?sch_id=" + rcmmdVo.getSb_id());
					}
				}
				
				mav.addObject("rcmmd_list", rcmmdList);
			} 
			
			mav.addObject("studentEntity", studentEntity);
		} else {
			mav.setViewName("redirect:/index.do");
		}
		
		mav.addObject("locale", Util.GET_LOCALE(request.getHeader("Accept-Language")));
		mav.addObject("context_name", Util.LOAD_CONFIG("context_name"));
		
        return mav;
    }
	
	@RequestMapping("/myprofile/stdBasicModForm")
    public ModelAndView studentModify1(HttpServletRequest request, 
    					 HttpServletResponse response, 
			 			 Model model,
			 			HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		UserEntity userEntity = (UserEntity)session.getAttribute("login_user");
		
		if (userEntity != null) {
			String student_id = request.getParameter("student_id");
			StudentEntity studentEntity = studentService.getByStudentId(student_id);
			
			if (studentEntity != null) {
				if (studentEntity.getZipcode() != null && studentEntity.getZipcode().length() == 8) {
					studentEntity.setZipcode(studentEntity.getZipcode().substring(0, 3) + studentEntity.getZipcode().substring(4));
				} 

				mav.addObject("mode", "modify");
			} else {
				mav.addObject("mode", "add");
			}
			
			UserEntity dbUserEntity = userService.getByUserId(userEntity.getUser_id());
			model.addAttribute("userEntity", dbUserEntity);
			model.addAttribute("studentEntity", studentEntity);
			
			/*
			 * Recommended school list
			 */
			List<RecommendListVo> list = getRcmmdListForStudent(userEntity.getUser_id());
			mav.addObject("school_list", list);
			
			/*
			 * Setting date
			 */
			mav.addObject("birth_y_map", Util.GET_BIRTH_Y_MAP(1950, 2007));
			mav.addObject("birth_m_map", Util.GET_BIRTH_MD_MAP(12));
			mav.addObject("birth_d_map", Util.GET_BIRTH_MD_MAP(31));
			
			mav.setViewName("/user/myprofile/stdMypage1");
		} else {
			mav.setViewName("redirect:/index.do");
		}
		
		mav.addObject("locale", Util.GET_LOCALE(request.getHeader("Accept-Language")));
		mav.addObject("context_name", Util.LOAD_CONFIG("context_name"));
		
		return mav;
    }
	
	@RequestMapping("/myprofile/studentList/{datatype}")
    public @ResponseBody Object studentList(@ModelAttribute StudentEntity studentEntity,
    									 @PathVariable String datatype, 
    									 HttpServletRequest request, 
    									 HttpServletResponse response,
    									 Model model) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		List<StudentEntity> list = studentService.list(studentEntity);
		if ("xml".equals(datatype)) {
			XmlStudentData data = new XmlStudentData();
	        data.setItems(list);
	        
	        model.addAttribute("data", data);
	        
	        return data;
		} else {
			model.addAttribute("data", list);
			return list;
		}
    }
	
	@RequestMapping("/myprofile/studentList/jsonpTest")
    public void jsonpTest(@ModelAttribute StudentEntity studentEntity,
    									 HttpServletRequest request,
    									 HttpServletResponse response, 
    									 Model model) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		
		String callback = request.getParameter("callback");
		System.out.println("callback=" + callback + "," + request.getRemoteAddr());
		
		List<StudentEntity> list = studentService.list(studentEntity);
		JSONObject obj = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		
		for (StudentEntity student : list) {
			JSONObject studentJson = new JSONObject();
			studentJson.put("idx", student.getIdx());
			studentJson.put("fname", student.getFname());
			studentJson.put("email", student.getEmail());
			
			jsonArray.put(studentJson);
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
	
    private List<BranchListVo> getSchoolBranchList(String student_id) {
    	List<BranchListVo> list = customerBranchService.listBranchListForStudentVo(student_id);
		
		int totalCnt = list.size();
		for (BranchListVo entity : list) {
			entity.setNo(totalCnt--);
		}
		
		return list;
    }
	
    private List<RecommendListVo> getRcmmdListForStudent(String student_id) {
		List<RecommendListVo> list = rcmmdService.listRcmmdSchoolListViewByStudentId(student_id);
		
		return list;
    }
    
}
