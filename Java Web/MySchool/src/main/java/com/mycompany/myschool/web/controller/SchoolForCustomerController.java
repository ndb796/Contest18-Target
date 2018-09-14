package com.mycompany.myschool.web.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

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
import com.mycompany.myschool.web.entity.common.CodeMstEntity;
import com.mycompany.myschool.web.entity.customer.CustomerBranchEntity;
import com.mycompany.myschool.web.entity.customer.CustomerCoursesEntity;
import com.mycompany.myschool.web.entity.customer.CustomerEntity;
import com.mycompany.myschool.web.entity.customer.CustomerKeyEntity;
import com.mycompany.myschool.web.entity.student.StudentEntity;
import com.mycompany.myschool.web.entity.vo.BranchInfoVo;
import com.mycompany.myschool.web.entity.vo.BranchListVo;
import com.mycompany.myschool.web.entity.vo.FavoriteListVo;
import com.mycompany.myschool.web.entity.vo.RecommendListVo;
import com.mycompany.myschool.web.service.common.CodeMstService;
import com.mycompany.myschool.web.service.customer.CustomerBranchService;
import com.mycompany.myschool.web.service.customer.CustomerCoursesService;
import com.mycompany.myschool.web.service.customer.CustomerKeyService;
import com.mycompany.myschool.web.service.student.FavoriteService;
import com.mycompany.myschool.web.service.student.RecommendService;
import com.mycompany.myschool.web.util.Constants;
import com.mycompany.myschool.web.util.IDGenerator;
import com.mycompany.myschool.web.util.ResultCode;
import com.mycompany.myschool.web.util.Token;
import com.mycompany.myschool.web.util.Util;

@Controller
public class SchoolForCustomerController {
	@Autowired
	private CustomerCoursesService customerCoursesService;
	
	@Autowired
	private RecommendService recommendService;
	
	@Autowired
	private FavoriteService favoriteService;
	
	@Autowired
	private CustomerBranchService customerBranchService;
	
	@Autowired
	private CodeMstService codeMstService;
	
	@Autowired
	private CustomerKeyService customerKeyService;
	
	/*
	 * Customer part
	 */
	@RequestMapping("/customer/school/myBranchList/jsonpTest")
    public void customerJsonpTest(@ModelAttribute CustomerBranchEntity schoolEntity,
    									 HttpServletRequest request,
    									 HttpServletResponse response, 
    									 Model model) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		
		String callback = request.getParameter("callback");
		schoolEntity.setStartRecord(Integer.parseInt(request.getParameter("start")));
		String orderType = Util.CHK_NULL(request.getParameter("order[0][column]"));
		String orderDir = Util.CHK_NULL(request.getParameter("order[0][dir]"));
		String search = Util.CHK_NULL(request.getParameter("search[value]"));
		if (search != null) {
			try {
				search  =new String(search.getBytes("8859_1"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}

		if (orderType.equals("1")) {
			schoolEntity.setOrderType("SCHOOL_NAME");
		} else {
			schoolEntity.setOrderType("S.IDX");
		}
		schoolEntity.setOrderDir(orderDir);
		schoolEntity.setSearchValue(search);
		
		List<BranchListVo> list = customerBranchService.listVo(schoolEntity);
		int totalCnt = list.size();
		for (BranchListVo entity : list) {
			entity.setNo(totalCnt--);
		}
		Integer count = customerBranchService.countListVo(schoolEntity);
		
		JSONObject obj = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		
		for (BranchListVo vo : list) {
			JSONObject schoolJson = new JSONObject();
			schoolJson.put("idx", vo.getIdx());
			schoolJson.put("sb_id", vo.getSb_id());
			schoolJson.put("sb_name", vo.getSb_name());
			schoolJson.put("sb_type", vo.getSb_type());
			schoolJson.put("favr_cnt", vo.getFavr_cnt());
			schoolJson.put("rcmmd_cnt", vo.getRcmmd_cnt());
			schoolJson.put("ins_date", vo.getIns_date());
			
			jsonArray.put(schoolJson);
		}
		
		obj.put("draw", request.getParameter("draw"));
		obj.put("recordsTotal", count);
		obj.put("recordsFiltered", count);
		obj.put("data", jsonArray);
		
		try {
			PrintWriter out = response.getWriter();
			
			out.write(obj.toString());
			
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	@RequestMapping("/customer/school/schoolList")
    public ModelAndView customerSchoolList(@ModelAttribute CustomerEntity schoolEntity,
    									 HttpServletRequest request, 
    									 Model model,
    									 HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		UserEntity userEntity = (UserEntity)session.getAttribute("login_user");
		
		if (userEntity != null) {
			List<CustomerBranchEntity> list = getSchoolBranchListForCustomer(schoolEntity);
			
			mav.addObject("branch_list", list);
			
			StudentEntity studentEntity = new StudentEntity();
			studentEntity.setStudent_id(userEntity.getUser_id());
			mav.addObject("studentEntity", studentEntity);
			mav.addObject("userEntity", userEntity);
			mav.addObject("schoolEntity", schoolEntity);
			
			mav.setViewName("/customer/school/schoolList");
		} else {
			mav.setViewName("redirect:/index.do");
		}
		
		mav.addObject("locale", Util.GET_LOCALE(request.getHeader("Accept-Language")));
		mav.addObject("current_uri", Util.GET_URI(request));
		mav.addObject("context_name", Util.LOAD_CONFIG("context_name"));
		
		return mav;
    }
	
	@RequestMapping("/customer/school/schoolRcmmdList")
    public ModelAndView customerRcmmdList(@ModelAttribute CustomerEntity schoolEntity,
    									 HttpServletRequest request, 
    									 Model model,
    									 HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		UserEntity userEntity = (UserEntity)session.getAttribute("login_user");
		
		if (userEntity != null) {
			List<RecommendListVo> list = getRcmmdListForCustomer(schoolEntity);
			
			mav.addObject("student_list", list);
			
			StudentEntity studentEntity = new StudentEntity();
			studentEntity.setStudent_id(userEntity.getUser_id());
			mav.addObject("studentEntity", studentEntity);
			mav.addObject("userEntity", userEntity);
			mav.addObject("schoolEntity", schoolEntity);
			
			mav.setViewName("/customer/school/schoolRcmmdList");
		} else {
			mav.setViewName("redirect:/index.do");
		}
		
		mav.addObject("locale", Util.GET_LOCALE(request.getHeader("Accept-Language")));
		mav.addObject("current_uri", Util.GET_URI(request));
		mav.addObject("context_name", Util.LOAD_CONFIG("context_name"));
		
		return mav;
    }
	
	@RequestMapping("/customer/school/schoolFavorList")
    public ModelAndView customerFavorList(@ModelAttribute CustomerEntity schoolEntity,
    									 HttpServletRequest request, 
    									 Model model,
    									 HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		UserEntity userEntity = (UserEntity)session.getAttribute("login_user");
		
		if (userEntity != null) {
			List<FavoriteListVo> list = getFavorListForCustomer(schoolEntity);
			
			mav.addObject("student_list", list);
			
			StudentEntity studentEntity = new StudentEntity();
			studentEntity.setStudent_id(userEntity.getUser_id());
			mav.addObject("studentEntity", studentEntity);
			mav.addObject("userEntity", userEntity);
			mav.addObject("schoolEntity", schoolEntity);
			
			mav.setViewName("/customer/school/schoolFavorList");
		} else {
			mav.setViewName("redirect:/index.do");
		}
		
		mav.addObject("locale", Util.GET_LOCALE(request.getHeader("Accept-Language")));
		mav.addObject("current_uri", Util.GET_URI(request));
		mav.addObject("context_name", Util.LOAD_CONFIG("context_name"));
		
		return mav;
    }
	
	@RequestMapping("/customer/school/branchAddForm")
    public ModelAndView branchAddForm(HttpServletRequest request, 
    					 					HttpServletResponse response, 
    					 					Model model,
    					 					HttpSession session) {
		ModelAndView mav = new ModelAndView();
		UserEntity userEntity = (UserEntity)session.getAttribute("login_user");
		
		if (userEntity != null) {		// login 페이로부터 이동 
			CustomerBranchEntity branchEntity = new CustomerBranchEntity();
			branchEntity.setSchool_id(userEntity.getUser_id());
			model.addAttribute("branchEntity", branchEntity);
			
			CustomerEntity schoolEntity = new CustomerEntity();
			schoolEntity.setSchool_id(userEntity.getUser_id());
			model.addAttribute("schoolEntity", schoolEntity);
			
			/*
			 * Setting mode
			 */
			mav.addObject("mode", "add");
			model.addAttribute("userEntity", userEntity);
			
			/*
			 * Retrieve code master
			 */
			List<CodeMstEntity> codeList = codeMstService.listByType(Constants.CODE_KEYWORD);
			mav.addObject("code_mst", codeList);
			
			/*
			 * School type
			 */
			SortedMap<String, String> school_type_map = new TreeMap<String, String>();
			school_type_map.put("0", "선택해 주세요");
			school_type_map.put("2", "중학교");
			school_type_map.put("3", "고등학교");
			school_type_map.put("4", "대학교");
			school_type_map.put("5", "전문대학교");
			mav.addObject("school_type_map", school_type_map);
			
			mav.setViewName("/customer/school/branchAdd");
		} else {
			mav.setViewName("redirect:/index.do?resultCode=" + ResultCode.USER_ERR_NOTLOGIN_CODE);
		}
		
		mav.addObject("locale", Util.GET_LOCALE(request.getHeader("Accept-Language")));
		mav.addObject("context_name", Util.LOAD_CONFIG("context_name"));
		
		return mav;
    }
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/customer/school/branchAddProc")
    public ModelAndView branchAddProc(@ModelAttribute("schoolEntity") CustomerBranchEntity schoolEntity,
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
					boolean isExistFile = false;
					
					String currentId = null;
					String newSbId = null;
					
					if (mode != null && mode.equals("add")) {
						currentId = customerBranchService.getMaxId();
						newSbId = IDGenerator.NEXT_ID(Constants.SRVC_TYPE_BRC, currentId);
					} else {
						newSbId = request.getParameter("sb_id");
					}
					
					if(!schoolEntity.getPhoto1_file().isEmpty()){
						String tempFilename = schoolEntity.getSchool_id() + "_" + newSbId + "_01_" + System.currentTimeMillis();
						
				        String filename = schoolEntity.getPhoto1_file().getOriginalFilename();
				        String imgExt = filename.substring(filename.lastIndexOf(".")+1, filename.length());
				        tempFilename += "." + imgExt.toLowerCase();
				        
				        if(imgExt.equalsIgnoreCase("JPG") || imgExt.equalsIgnoreCase("JPEG") || imgExt.equalsIgnoreCase("GIF") || imgExt.equalsIgnoreCase("PNG")) {
				            byte[] bytes = schoolEntity.getPhoto1_file().getBytes();
				            try {
				            	File dir = new File(request.getRealPath("/") + "/images/upload/school/" + Util.PATH_FILTER(schoolEntity.getSchool_id()));
				            	if (!dir.exists()) {
				            		dir.mkdirs();
				                }
				            	File outFile = new File(dir, Util.PATH_FILTER(tempFilename));
				            	System.out.println(outFile.getAbsolutePath());
				            	FileOutputStream fos = new FileOutputStream(outFile);
				                fos.write(bytes);
				                 
				                schoolEntity.setPhoto1_name(tempFilename);
				                schoolEntity.setPhoto1_size(bytes.length);
				                schoolEntity.setOrg1_name(filename);
				                 
				                fos.close();
				                 
				                isExistFile = true;
				            } catch(IOException ie) {
				            	ie.printStackTrace();
				            	isError = true;
				                System.err.println("Photo1 file writing error! ");
				            }
				            System.err.println("Photo1 file upload success! ");
				        } else {
				        	isError = true;
				            System.err.println("Photo1 file type error! ");
				        }
				    } 
					
					if(!schoolEntity.getPhoto2_file().isEmpty()){
						String tempFilename = schoolEntity.getSchool_id() + "_" + newSbId + "_02_" + System.currentTimeMillis();
						
				        String filename = schoolEntity.getPhoto2_file().getOriginalFilename();
				        String imgExt = filename.substring(filename.lastIndexOf(".")+1, filename.length());
				        tempFilename += "." + imgExt.toLowerCase();
				        
				        if(imgExt.equalsIgnoreCase("JPG") || imgExt.equalsIgnoreCase("JPEG") || imgExt.equalsIgnoreCase("GIF") || imgExt.equalsIgnoreCase("PNG")) {
				            byte[] bytes = schoolEntity.getPhoto2_file().getBytes();
				            try {
				            	File dir = new File(request.getRealPath("/") + "/images/upload/school/" + Util.PATH_FILTER(schoolEntity.getSchool_id()));
				            	if (!dir.exists()) {
				            		dir.mkdirs();
				                }
				            	File outFile = new File(dir, Util.PATH_FILTER(tempFilename));
				            	FileOutputStream fos = new FileOutputStream(outFile);
				                fos.write(bytes);
				                 
				                schoolEntity.setPhoto2_name(tempFilename);
				                schoolEntity.setPhoto2_size(bytes.length);
				                schoolEntity.setOrg2_name(filename);
				                 
				                fos.close();
				                 
				                isExistFile = true;
				            } catch(IOException ie) {
				            	ie.printStackTrace();
				            	isError = true;
				                System.err.println("Photo2 file writing error! ");
				            }
				            System.err.println("Photo2 file upload success! ");
				        } else {
				        	isError = true;
				            System.err.println("Photo2 file type error! ");
				        }
				    } 
					
					/*
					 * Insert or update branch data
					 */
					String[] keywords = request.getParameterValues("keywords");
					
					if (mode != null && mode.equals("add")) {
						schoolEntity.setSb_id(newSbId);
						customerBranchService.add(schoolEntity);
						
						/*
						 * Insert new keyword info
						 */
						CustomerKeyEntity customerKeyEntity = new CustomerKeyEntity();
						customerKeyEntity.setSb_id(schoolEntity.getSb_id());
						for (String keyword : keywords) {
							customerKeyEntity.setKeycode(keyword);
							
							customerKeyService.add(customerKeyEntity);
						}
						
					} else {
						customerBranchService.modify(schoolEntity);
						
						/*
						 * Delete old keyword info 
						 */
						customerKeyService.remove(schoolEntity.getSb_id());
						
						/*
						 * Insert new keyword info
						 */
						CustomerKeyEntity customerKeyEntity = new CustomerKeyEntity();
						customerKeyEntity.setSb_id(schoolEntity.getSb_id());
						for (String keyword : keywords) {
							customerKeyEntity.setKeycode(keyword);
							
							customerKeyService.add(customerKeyEntity);
						}
					}
					
					/*
					 * Insert subject data
					 */
					SortedMap<String, String> cursTitleMap = new TreeMap<String, String>();  
					SortedMap<String, String> cursContentsMap = new TreeMap<String, String>();  
					Enumeration<String> em = request.getParameterNames();
					while (em.hasMoreElements()) {
						String name = em.nextElement();
						if (name.startsWith("curs_title_")) {
							cursTitleMap.put(name, request.getParameter(name));
						} else if (name.startsWith("curs_contents_")) {
							cursContentsMap.put(name, request.getParameter(name));
						}
					}
					
					/*
					 * 기존 데이터 삭제
					 */
					customerCoursesService.remove(newSbId);
					
					Iterator<String> it = cursTitleMap.keySet().iterator();
					int cursOrd = 1;
					while (it.hasNext()) {
						String key = it.next();
						String surfix = key.substring(key.lastIndexOf("_") + 1);
						CustomerCoursesEntity courseEntity = new CustomerCoursesEntity();
						courseEntity.setSb_id(newSbId);
						courseEntity.setCurs_ord(cursOrd++);
						courseEntity.setCurs_title(cursTitleMap.get(key));
						courseEntity.setCurs_contents1(cursContentsMap.get("curs_contents_" + surfix));
						
						/*
						 * 신규 데이터로 입력
						 */
						customerCoursesService.add(courseEntity);
					}
				}
			} catch (Exception e) {
				isError = true;
				e.printStackTrace();
			}
			
			mav.addObject("school_entity", schoolEntity);
			
			if (isError) {
				mav.addObject("mode", mode);
				mav.setViewName("/customer/school/branchAdd");
				
				if (mode != null && mode.equals("add")) {
					mav.addObject("resultCode", ResultCode.SCHOOL_ERR_CREATE_CODE);
				} else {
					mav.addObject("resultCode", ResultCode.SCHOOL_ERR_UPDATE_CODE);
				}
			} else {
				mav.addObject("mode", "view");
				mav.addObject("sb_id", schoolEntity.getSb_id());
				mav.addObject("school_id", schoolEntity.getSchool_id());
				
				BranchInfoVo schoolVo = customerBranchService.getInfoVo(schoolEntity.getSb_id());
				mav.addObject("branchEntity", schoolVo);
				
				List<CustomerCoursesEntity> courseList = customerCoursesService.getByBranchId(schoolEntity.getSb_id());
				mav.addObject("branchCoursesList", courseList);
				
				CustomerEntity customerEntity = new CustomerEntity();
				customerEntity.setSchool_id(userEntity.getUser_id());
				mav.addObject("schoolEntity", customerEntity);
				mav.addObject("userEntity", userEntity);
				
				if (mode != null && mode.equals("add")) {
					mav.addObject("resultCode", ResultCode.SCHOOL_C_SUCCESS_CODE);
				} else {
					mav.addObject("resultCode", ResultCode.SCHOOL_U_SUCCESS_CODE);
				}

				/*
				 * Retrieve branch keyword 
				 */
				List<CustomerKeyEntity> customerKeyList = customerKeyService.get(schoolEntity.getSb_id());
				mav.addObject("customer_key", customerKeyList);
				
				mav.setViewName("/customer/school/branchDetail");
			}

			session.removeAttribute("token");
		} else {
			mav.setViewName("redirect:/index.do");
		}
		
		mav.addObject("locale", Util.GET_LOCALE(request.getHeader("Accept-Language")));
		mav.addObject("context_name", Util.LOAD_CONFIG("context_name"));
		
        return mav;
    }
	
	@RequestMapping("/customer/school/branchModifyForm")
    public ModelAndView branchModifyForm(HttpServletRequest request, 
    					 					HttpServletResponse response, 
    					 					Model model,
    					 					HttpSession session) {
		ModelAndView mav = new ModelAndView();
		UserEntity userEntity = (UserEntity)session.getAttribute("login_user");
		
		if (userEntity != null) {		// login 페이로부터 이동 
			CustomerBranchEntity paramBranchEntity = new CustomerBranchEntity();
			paramBranchEntity.setSchool_id(userEntity.getUser_id());
			paramBranchEntity.setSb_id(request.getParameter("sb_id"));
			
			CustomerBranchEntity branchEntity = customerBranchService.getOne(paramBranchEntity.getSb_id());
			model.addAttribute("branchEntity", branchEntity);
			
			List<CustomerCoursesEntity> courseList = customerCoursesService.getByBranchId(paramBranchEntity.getSb_id());
			mav.addObject("branchCoursesList", courseList);
			
			/*
			 * Setting mode
			 */
			mav.addObject("mode", "modify");
			
			CustomerEntity customerEntity = new CustomerEntity();
			customerEntity.setSchool_id(userEntity.getUser_id());
			mav.addObject("schoolEntity", customerEntity);
			mav.addObject("userEntity", userEntity);
			
			/*
			 * Retrieve code master
			 */
			List<CodeMstEntity> codeList = codeMstService.listByType(Constants.CODE_KEYWORD);
			mav.addObject("code_mst", codeList);
			
			/*
			 * School type
			 */
			SortedMap<String, String> school_type_map = new TreeMap<String, String>();
			school_type_map.put("0", "선택해 주세요");
			school_type_map.put("2", "중학교");
			school_type_map.put("3", "고등학교");
			school_type_map.put("4", "대학교");
			school_type_map.put("5", "전문학교");
			mav.addObject("school_type_map", school_type_map);
			
			/*
			 * Retrieve branch keyword 
			 */
			List<CustomerKeyEntity> customerKeyList = customerKeyService.get(paramBranchEntity.getSb_id());
			Map<String, CustomerKeyEntity> keyMap = new HashMap<String, CustomerKeyEntity>();
			for (CustomerKeyEntity customerKey : customerKeyList) {
				keyMap.put(customerKey.getKeycode(), customerKey);
			}
			mav.addObject("customer_key", keyMap);
			
			mav.setViewName("/customer/school/branchAdd");
		} else {
			mav.setViewName("redirect:/index.do?resultCode=" + ResultCode.USER_ERR_NOTLOGIN_CODE);
		}
		
		mav.addObject("locale", Util.GET_LOCALE(request.getHeader("Accept-Language")));
		mav.addObject("context_name", Util.LOAD_CONFIG("context_name"));
		
		return mav;
    }
	
	@RequestMapping("/customer/school/branchDetail")
    public ModelAndView branchDetail(HttpServletRequest request, 
    									 Model model,
    									 HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		UserEntity userEntity = (UserEntity)session.getAttribute("login_user");
		if (userEntity != null) {
			String sb_id = request.getParameter("sb_id");
			BranchInfoVo schoolVo = customerBranchService.getInfoVo(sb_id);
			
			if (schoolVo != null) {
				mav.addObject("branchEntity", schoolVo);
				/*
				 * Retrieve course data
				 */
				List<CustomerCoursesEntity> courseList = customerCoursesService.getByBranchId(sb_id);
				mav.addObject("branchCoursesList", courseList);
				
				/*
				 * Retrieve branch keyword 
				 */
				List<CustomerKeyEntity> customerKeyList = customerKeyService.get(sb_id);
			
				mav.addObject("customer_key", customerKeyList);
				
				mav.setViewName("/customer/school/branchDetail");	
			} else {
				String errMsg = ResultCode.GET_MESSAGE(ResultCode.CUST_ERR_CODE);
				mav.addObject("errorMsg", errMsg);
				
				CustomerBranchEntity schoolEntity = new CustomerBranchEntity();
				schoolEntity.setSb_id(sb_id);
				schoolEntity.setSchool_id(userEntity.getUser_id());
				mav.addObject("branchEntity", schoolEntity);
				mav.setViewName("/customer/school/schoolList");
			}
			
			CustomerEntity schoolEntity = new CustomerEntity();
			schoolEntity.setSchool_id(userEntity.getUser_id());
			mav.addObject("schoolEntity", schoolEntity);
			mav.addObject("userEntity", userEntity);
		} else {
			mav.setViewName("redirect:/index.do");
		}
		
		mav.addObject("locale", Util.GET_LOCALE(request.getHeader("Accept-Language")));
		mav.addObject("context_name", Util.LOAD_CONFIG("context_name"));
		
		return mav;
    }
	
	@RequestMapping("/customer/school/branchRemove")
    public ModelAndView customerBranchRemove(HttpServletRequest request, 
    									 Model model,
    									 HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		UserEntity userEntity = (UserEntity)session.getAttribute("login_user");
		
		String[] sbIdArray = Util.CHK_NULL(request.getParameter("sb_id")).split(",");
		
		if (userEntity != null) {
			CustomerBranchEntity customerBranchEntity = new CustomerBranchEntity();
			customerBranchEntity.setSbIdArray(sbIdArray);
			
			if (customerBranchService.modifyForRemove(customerBranchEntity)) {
				mav.addObject("resultCode", ResultCode.SCHOOL_D_SUCCESS_CODE);
			} else {
				mav.addObject("resultCode", ResultCode.SCHOOL_ERR_DELETE_CODE);
			}
			
			CustomerEntity schoolEntity = new CustomerEntity();
			schoolEntity.setSchool_id(request.getParameter("school_id"));
			List<CustomerBranchEntity> list = getSchoolBranchListForCustomer(schoolEntity);
			
			mav.addObject("branch_list", list);
			
			StudentEntity studentEntity = new StudentEntity();
			studentEntity.setStudent_id(userEntity.getUser_id());
			mav.addObject("studentEntity", studentEntity);
			mav.addObject("userEntity", userEntity);
			mav.addObject("schoolEntity", schoolEntity);
			
			/*
			 * Remove branch sub data
			 */
			CustomerKeyEntity customerKeyEntity = new CustomerKeyEntity();
			customerKeyEntity.setSbIdArray(sbIdArray);
			customerKeyService.removeMulti(customerKeyEntity);
		} else {
			mav.setViewName("redirect:/index.do");
		}
		
		mav.setViewName("/customer/school/schoolList");
		mav.addObject("locale", Util.GET_LOCALE(request.getHeader("Accept-Language")));
		mav.addObject("context_name", Util.LOAD_CONFIG("context_name"));
		
		return mav;
	}
	
	/*
	 * Retreieve school list
	 */
	private List<CustomerBranchEntity> getSchoolBranchListForCustomer(CustomerEntity schoolEntity) {
		CustomerBranchEntity customerBranchEntity = new CustomerBranchEntity();
		customerBranchEntity.setSchool_id(schoolEntity.getSchool_id());
		System.out.println(schoolEntity.getSchool_id() + "]");
		List<CustomerBranchEntity> list = customerBranchService.getAllBySchoolId(customerBranchEntity);
		int totalCnt = list.size();
		for (CustomerBranchEntity entity : list) {
			entity.setNo(totalCnt--);
		}
		
		return list;
    }
	
	private List<RecommendListVo> getRcmmdListForCustomer(CustomerEntity schoolEntity) {
		List<RecommendListVo> list = recommendService.listRcmmdStudentBySchoolId(schoolEntity.getSchool_id());
		System.out.println(schoolEntity.getSchool_id() + "/" + list);
		int totalCnt = list.size();
		for (RecommendListVo entity : list) {
			entity.setNo(totalCnt--);
		}
		
		return list;
    }
	
	private List<FavoriteListVo> getFavorListForCustomer(CustomerEntity schoolEntity) {
		List<FavoriteListVo> list = favoriteService.listFavoriteStudentBySchoolId(schoolEntity.getSchool_id());
		int totalCnt = list.size();
		for (FavoriteListVo entity : list) {
			entity.setNo(totalCnt--);
		}
		
		return list;
    }
}
