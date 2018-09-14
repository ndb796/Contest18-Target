package com.mycompany.myschool.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
import com.mycompany.myschool.web.entity.common.SearchEntity;
import com.mycompany.myschool.web.entity.customer.CustomerBranchEntity;
import com.mycompany.myschool.web.entity.customer.CustomerEntity;
import com.mycompany.myschool.web.entity.customer.ViewCountEntity;
import com.mycompany.myschool.web.entity.student.FavoriteEntity;
import com.mycompany.myschool.web.entity.student.StudentEntity;
import com.mycompany.myschool.web.entity.vo.BranchListVo;
import com.mycompany.myschool.web.entity.vo.FavoriteListVo;
import com.mycompany.myschool.web.entity.vo.RecommendListVo;
import com.mycompany.myschool.web.entity.xml.XmlSchoolData;
import com.mycompany.myschool.web.service.common.SearchService;
import com.mycompany.myschool.web.service.customer.CustomerBranchService;
import com.mycompany.myschool.web.service.customer.CustomerService;
import com.mycompany.myschool.web.service.customer.ViewCountService;
import com.mycompany.myschool.web.service.student.FavoriteService;
import com.mycompany.myschool.web.service.student.RecommendService;
import com.mycompany.myschool.web.service.student.StudentService;
import com.mycompany.myschool.web.util.Constants;
import com.mycompany.myschool.web.util.ResultCode;
import com.mycompany.myschool.web.util.Util;

@Controller
public class SchoolForUserController {
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private RecommendService recommendService;
	
	@Autowired
	private FavoriteService favoriteService;
	
	@Autowired
	private CustomerBranchService customerBranchService;
	
	@Autowired
	private ViewCountService viewCountService;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private SearchService searchService;
	
	/*
	 * Student part
	 */
	@RequestMapping("/user/school/schoolList/{datatype}")
    public @ResponseBody Object studentList(@ModelAttribute CustomerEntity schoolEntity,
    									 @PathVariable String datatype, 
    									 HttpServletRequest request, 
    									 HttpServletResponse response,
    									 Model model) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		List<CustomerEntity> list = customerService.list(schoolEntity);
		if ("xml".equals(datatype)) {
			XmlSchoolData data = new XmlSchoolData();
	        data.setItems(list);
	        
	        model.addAttribute("data", data);
	        
	        return data;
		} else {
			model.addAttribute("data", list);
			return list;
		}
    }
	
	@RequestMapping("/user/school/schoolList/jsonpTest")
    public void jsonpTest(@ModelAttribute CustomerBranchEntity schoolEntity,
    									 HttpServletRequest request,
    									 HttpServletResponse response, 
    									 Model model) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		
		String callback = Util.CHK_NULL(request.getParameter("callback"));
		schoolEntity.setStartRecord(Integer.parseInt(request.getParameter("start")));
		String orderType = Util.CHK_NULL(request.getParameter("order[0][column]"));
		String orderDir = Util.CHK_NULL(request.getParameter("order[0][dir]"));
		String search = Util.CHK_NULL(request.getParameter("search[value]"));
		if (search != null) {
			try {
				search  =new String(search.getBytes("8859_1"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				
			}
		}

		if (orderType.equals("1")) {
			schoolEntity.setOrderType("SB_NAME");
		} else {
			schoolEntity.setOrderType("B.IDX");
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
			schoolJson.put("sb_name", "<a href=\"/user/school/schoolDetail.do?sb_id=" + vo.getSb_id() + "\">" + vo.getSb_name() + "</a>");
			schoolJson.put("sb_type", Constants.SCHOOL_TYPE_STR[Integer.parseInt(vo.getSb_type())]);
			/*schoolJson.put("school_category", vo.getSchool_category());*/
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
	
	@RequestMapping("/user/school/schoolList/schoolListJson")
    public void schoolListJson(@ModelAttribute SearchEntity searchEntity,
    									 HttpServletRequest request,
    									 HttpServletResponse response, 
    									 HttpSession session,
    									 Model model) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		
		String callback = Util.CHK_NULL(request.getParameter("callback"));
		searchEntity.setStartRecord(Integer.parseInt(request.getParameter("start")));
		String orderType = Util.CHK_NULL(request.getParameter("order[0][column]"));
		String orderDir = Util.CHK_NULL(request.getParameter("order[0][dir]"));
		String search = Util.CHK_NULL(request.getParameter("search[value]"));
		
		String extra_search = request.getParameter("school_type");
		
		if (search != null) {
			try {
				search  =new String(search.getBytes("8859_1"), "UTF-8");
				
				if (search.equals(extra_search)) {
					search = null;
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		//System.out.println("search=[" + search + "],[" + extra_search + "]");
		
		if (orderType.equals("1")) {
			searchEntity.setOrderType("NAME");
		} else {
			searchEntity.setOrderType("IDX");
		}
		searchEntity.setOrderDir(orderDir);
		searchEntity.setSearchValue(search);
		
		UserEntity userEntity = (UserEntity)session.getAttribute("login_user");
		StudentEntity studentEntity = studentService.getByStudentId(userEntity.getUser_id());
		
		List<SearchEntity> list = null;
		Integer count = 0;
		int school_type = 0;
		
		JSONObject obj = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		
		if (extra_search != null && extra_search.length() != 0) {
			school_type = Integer.parseInt(extra_search);
		} else {
			school_type = Constants.STD_RCMMD_CATE_ALL;
		}
			
			//System.out.println("SearchValue=[" + searchEntity.getSearchValue() + "]");
			list = getSchoolListForStudent(searchEntity, school_type);
			count = getSchoolListCountForStudent(searchEntity, school_type);
			
			if (list != null && list.size() > 0) {
				for (SearchEntity entity : list) {
					JSONObject schoolJson = new JSONObject();
					schoolJson.put("idx", entity.getIdx());
					schoolJson.put("sch_id", entity.getSch_id());
					schoolJson.put("name", "<a href=\"/user/school/schoolDetail.do?school_type=" + entity.getSb_type() + "&sch_id=" + entity.getSch_id() + "\">" + entity.getName() + "</a>");
	//				schoolJson.put("sb_type", Constants.SCHOOL_TYPE_STR[(studentEntity != null ? studentEntity.getRcmmd_type() : Constants.STD_RCMMD_CATE_H)]);
					schoolJson.put("sb_type", Constants.SCHOOL_TYPE_STR[entity.getSb_type()]);
					schoolJson.put("favr_cnt", entity.getFavr_cnt());
					schoolJson.put("rcmmd_cnt", entity.getRcmmd_cnt());
					schoolJson.put("ins_date", entity.getIns_date());
					
					jsonArray.put(schoolJson);
				}
			}
//		}
		
		obj.put("draw", request.getParameter("draw"));
		obj.put("recordsTotal", count);
		obj.put("recordsFiltered", count);
		obj.put("data", jsonArray);
		
		try {
			PrintWriter out = response.getWriter();
			
			out.write(obj.toString());
			System.out.println(callback + "(" + obj.toString() + ")");
			
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	@RequestMapping("/user/school/schoolList/schoolListJsonForApp")
    public void schoolListJsonForApp(@ModelAttribute SearchEntity searchEntity,
    									 HttpServletRequest request,
    									 HttpServletResponse response, 
    									 HttpSession session,
    									 Model model) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		
		String callback = request.getParameter("callback");
		searchEntity.setStartRecord(Integer.parseInt(request.getParameter("start")));
		String orderType = request.getParameter("order_column");
		String orderDir = request.getParameter("orde_dir");
		String search = request.getParameter("search");
		
		String extra_search = request.getParameter("school_type");
		
		if (search != null) {
			try {
				search  =new String(search.getBytes("8859_1"), "UTF-8");
				
				if (search.equals(extra_search)) {
					search = null;
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		
		if (orderType != null && orderType.equals("1")) {
			searchEntity.setOrderType("NAME");
		} else {
			searchEntity.setOrderType("IDX");
		}
		searchEntity.setOrderDir(orderDir == null ? "desc" : orderDir);
		
		searchEntity.setSearchValue(search);
		
		String param_auth_token = request.getParameter("auth_token");
		UserEntity userEntity = null;
		String msg = null;
		boolean isError = false;
		if (param_auth_token != null) {
			UUID session_auth_token = (UUID)session.getAttribute("auth_token_" + request.getRemoteAddr());
			if (session_auth_token != null && session_auth_token.compareTo(UUID.fromString(param_auth_token)) == 0) {
				userEntity = (UserEntity)session.getAttribute(session_auth_token.toString());
				isError = false;
			} else {
				msg = "{\"SC_CODE\":\"500\",\"SC_MSG\"=\"YOUR AUTH_TOKEN IS INVALIDED\"}";
				isError = true;
			}
		} else {
			msg = "{\"SC_CODE\":\"500\",\"SC_MSG\"=\"YOU MUST BE LOGGED\"}";
			isError = true;
		}
		
		if (isError) {
			try {
				PrintWriter out = response.getWriter();
				out.write(msg);
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}
		
		StudentEntity studentEntity = studentService.getByStudentId(userEntity.getUser_id());
		
		List<SearchEntity> list = null;
		Integer count = 0;
		int school_type = 0;
		
		JSONObject obj = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		
		if (extra_search != null && extra_search.length() != 0) {
			school_type = Integer.parseInt(extra_search);
		} else {
			school_type = Constants.STD_RCMMD_CATE_ALL;
		}
			
			//System.out.println("SearchValue=[" + searchEntity.getSearchValue() + "]");
			list = getSchoolListForStudent(searchEntity, school_type);
			count = getSchoolListCountForStudent(searchEntity, school_type);
			
			if (list != null && list.size() > 0) {
				for (SearchEntity entity : list) {
					JSONObject schoolJson = new JSONObject();
					schoolJson.put("idx", entity.getIdx());
					schoolJson.put("sch_id", entity.getSch_id());
					schoolJson.put("name", "<a href=\"/user/school/schoolDetail.do?school_type=" + entity.getSb_type() + "&sch_id=" + entity.getSch_id() + "\">" + entity.getName() + "</a>");
	//				schoolJson.put("sb_type", Constants.SCHOOL_TYPE_STR[(studentEntity != null ? studentEntity.getRcmmd_type() : Constants.STD_RCMMD_CATE_H)]);
					schoolJson.put("sb_type", Constants.SCHOOL_TYPE_STR[entity.getSb_type()]);
					schoolJson.put("favr_cnt", entity.getFavr_cnt());
					schoolJson.put("rcmmd_cnt", entity.getRcmmd_cnt());
					schoolJson.put("ins_date", entity.getIns_date());
					
					jsonArray.put(schoolJson);
				}
			}
//		}
		
		obj.put("draw", request.getParameter("draw"));
		obj.put("recordsTotal", count);
		obj.put("recordsFiltered", count);
		obj.put("data", jsonArray);
		
		try {
			PrintWriter out = response.getWriter();
			
			out.write(obj.toString());
			System.out.println(callback + "(" + obj.toString() + ")");
			
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	@RequestMapping("/user/school/schoolList")
    public ModelAndView studentListWeb(@ModelAttribute SearchEntity searchEntity,
    									 HttpServletRequest request, 
    									 Model model,
    									 HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		UserEntity userEntity = (UserEntity)session.getAttribute("login_user");
		if (userEntity != null) {
			StudentEntity studentEntity = studentService.getByStudentId(userEntity.getUser_id());
			
			mav.addObject("studentEntity", studentEntity);
			mav.addObject("userEntity", userEntity);
			mav.addObject("searchEntity", searchEntity);
			
			mav.setViewName("/user/school/schoolList");
		} else {
			mav.setViewName("redirect:/index.do");
		}
		
		mav.addObject("locale", Util.GET_LOCALE(request.getHeader("Accept-Language")));
		mav.addObject("current_uri", Util.GET_URI(request));
		mav.addObject("context_name", Util.LOAD_CONFIG("context_name"));
		
		return mav;
    }
	
	@RequestMapping("/user/school/schoolRcmmdList")
    public ModelAndView rcmmdListWeb(@ModelAttribute CustomerEntity schoolEntity,
    									 HttpServletRequest request, 
    									 Model model,
    									 HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		UserEntity userEntity = (UserEntity)session.getAttribute("login_user");
		
		if (userEntity != null) {
			List<RecommendListVo> list = getRcmmdListForStudent(userEntity.getUser_id());
			
			mav.addObject("school_list", list);
			
			StudentEntity studentEntity = new StudentEntity();
			studentEntity.setStudent_id(userEntity.getUser_id());
			mav.addObject("studentEntity", studentEntity);
			mav.addObject("userEntity", userEntity);
			mav.addObject("schoolEntity", schoolEntity);
			
			mav.setViewName("/user/school/schoolRcmmdList");
		} else {
			mav.setViewName("redirect:/index.do");
		}
		
		mav.addObject("locale", Util.GET_LOCALE(request.getHeader("Accept-Language")));
		mav.addObject("current_uri", Util.GET_URI(request));
		mav.addObject("context_name", Util.LOAD_CONFIG("context_name"));
		
		return mav;
    }
	
	@RequestMapping("/user/school/schoolFavorList")
    public ModelAndView favorListWeb(@ModelAttribute CustomerEntity schoolEntity,
    									 HttpServletRequest request, 
    									 Model model,
    									 HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		UserEntity userEntity = (UserEntity)session.getAttribute("login_user");
		
		if (userEntity != null) {
			Map<String, String> maps = new HashMap<String, String>();
			maps.put("student_id", userEntity.getUser_id());
			
			List<FavoriteListVo> favorList = favoriteService.listFavoriteSchoolListViewByStudentId(maps);
			mav.addObject("school_list", favorList);
			
			StudentEntity studentEntity = new StudentEntity();
			studentEntity.setStudent_id(userEntity.getUser_id());
			mav.addObject("studentEntity", studentEntity);
			mav.addObject("userEntity", userEntity);
			mav.addObject("schoolEntity", schoolEntity);
			
			mav.setViewName("/user/school/schoolFavorList");
		} else {
			mav.setViewName("redirect:/index.do");
		}
		
		mav.addObject("locale", Util.GET_LOCALE(request.getHeader("Accept-Language")));
		mav.addObject("current_uri", Util.GET_URI(request));
		mav.addObject("context_name", Util.LOAD_CONFIG("context_name"));
		
		return mav;
    }
	
	@RequestMapping("/user/school/schoolDetail")
    public ModelAndView schoolDetail(HttpServletRequest request, 
    									 Model model,
    									 HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		UserEntity userEntity = (UserEntity)session.getAttribute("login_user");
		if (userEntity != null) {
			String sch_id = request.getParameter("sch_id");
			String sb_type = request.getParameter("school_type");
			
//			BranchInfoVo schoolVo = customerBranchService.getInfoVo(sb_id);
			SearchEntity schoolVo = null;
			
			StudentEntity studentEntity = studentService.getByStudentId(userEntity.getUser_id());
			
			int school_type = 0;
			if (sb_type == null || sb_type.length() == 0) {
				school_type = studentEntity.getRcmmd_type();
			} else {
				school_type = Integer.parseInt(sb_type);
			}
			
			if (sch_id.startsWith("SCH")) {
				schoolVo = searchService.getMemberschoolOne(sch_id);
			} else {
				if (school_type == Constants.STD_RCMMD_CATE_M) {
					schoolVo = searchService.getMiddleschoolOne(sch_id);
				} else if (school_type == Constants.STD_RCMMD_CATE_H) {
					schoolVo = searchService.getHighschoolOne(sch_id);
				} else if (school_type == Constants.STD_RCMMD_CATE_U) {
					schoolVo = searchService.getUniversityOne(sch_id);
				} else if (school_type == Constants.STD_RCMMD_CATE_C) {
					schoolVo = searchService.getVocationalOne(sch_id);
				} 
			}
			
			if (schoolVo != null) {
				if (schoolVo.getSchool_copy() != null) {
					schoolVo.setSchool_copy(schoolVo.getSchool_copy().replaceAll("\n", "<br/>"));
				}
				if (schoolVo.getHistory() != null) {
					schoolVo.setHistory(schoolVo.getHistory().replaceAll("\n", "<br/>"));
				}
				if (schoolVo.getStation() != null) {
					schoolVo.setStation(schoolVo.getStation().replaceAll("\n", "<br/>"));
				}
				
				mav.addObject("schoolEntity", schoolVo);
				
				FavoriteEntity favoriteEntity = new FavoriteEntity();
				favoriteEntity.setSb_id(sch_id);
				favoriteEntity.setStudent_id(userEntity.getUser_id());
				
				FavoriteEntity dbFavoriteEntity = favoriteService.get(favoriteEntity);
				if (dbFavoriteEntity != null) {
					mav.addObject("is_favorite", "yes");
				}
				
				/*
				 * update view count 
				 */
				ViewCountEntity viewCountEntity = new ViewCountEntity();
				viewCountEntity.setSb_id(sch_id);
				if (viewCountService.get(sch_id) != null) {
					viewCountService.modifyViewCnt(viewCountEntity);
				} else {
					viewCountService.add(viewCountEntity);
				}
				
				mav.setViewName("/user/school/schoolDetail");	
			} else {
				String errMsg = ResultCode.GET_MESSAGE(ResultCode.CUST_ERR_CODE);
				mav.addObject("errorMsg", errMsg);
					
				CustomerEntity schoolEntity = new CustomerEntity();
				schoolEntity.setSchool_id(sch_id);
				mav.addObject("schoolEntity", schoolEntity);
				mav.setViewName("/user/school/schoolList");
			}
			
			mav.addObject("studentEntity", studentEntity);
			mav.addObject("userEntity", userEntity);
		} else {
			mav.setViewName("redirect:/index.do");
		}
		
		mav.addObject("locale", Util.GET_LOCALE(request.getHeader("Accept-Language")));
		mav.addObject("context_name", Util.LOAD_CONFIG("context_name"));
		
		return mav;
    }
	
	/**
	 * Retrieve to branch list 
	 * @param schoolEntity
	 * @return
	 */
	private List<BranchListVo> getSchoolBranchListForStudent(CustomerBranchEntity schoolEntity) {
		List<BranchListVo> list = customerBranchService.listVo(schoolEntity);
		int totalCnt = list.size();
		for (BranchListVo entity : list) {
			entity.setNo(totalCnt--);
		}
		
		return list;
    }
	
	/**
	 * 
	 * @param schoolEntity
	 * @return
	 */
	private List<SearchEntity> getSchoolListForStudent(SearchEntity searchEntity, int rcmmd_type) {
		List<SearchEntity> list = null;
		if (rcmmd_type == Constants.STD_RCMMD_CATE_M) {
			list = searchService.getMiddleschoolList(searchEntity);
		} else if (rcmmd_type == Constants.STD_RCMMD_CATE_H) {
			list = searchService.getHighschoolList(searchEntity);
		} else if (rcmmd_type == Constants.STD_RCMMD_CATE_U) {
			list = searchService.getUniversityList(searchEntity);
		} else if (rcmmd_type == Constants.STD_RCMMD_CATE_C) {
			list = searchService.getVocationalList(searchEntity);
		} else {
			list = searchService.getAllschoolList(searchEntity); //searchService.getHighschoolList(searchEntity);		// rcmme_type이 1(STD_RCMMD_CATE_E)인 경우에도 현재는 highschool 정보를 보여 주도록
		}
		
		return list;
    }
	
	/**
	 * 
	 * @param schoolEntity
	 * @return
	 */
	private Integer getSchoolListCountForStudent(SearchEntity searchEntity, int rcmmd_type) {
		if (rcmmd_type == Constants.STD_RCMMD_CATE_M) {
			return searchService.getCountMiddleschoolList(searchEntity);
		} else if (rcmmd_type == Constants.STD_RCMMD_CATE_H) {
			return searchService.getCountHighschoolList(searchEntity);
		} else if (rcmmd_type == Constants.STD_RCMMD_CATE_U) {
			return searchService.getCountUniversityList(searchEntity);
		} else if (rcmmd_type == Constants.STD_RCMMD_CATE_C) {
			return searchService.getCountVocationalList(searchEntity);
		} else {
			return searchService.getCountAllList(searchEntity);
		}
    }
	
	private List<RecommendListVo> getRcmmdListForStudent(String student_id) {
		List<RecommendListVo> list = recommendService.listRcmmdSchoolListViewByStudentId(student_id);
		
		int totalCnt = list.size();
		for (RecommendListVo entity : list) {
			entity.setNo(totalCnt--);
		}
		
		return list;
    }
	
	private List<FavoriteListVo> getFavorListForStudent(String student_id) {
		List<FavoriteListVo> list = favoriteService.listFavoriteSchoolByStudentId(student_id);
		
		int totalCnt = list.size();
		for (FavoriteListVo entity : list) {
			entity.setNo(totalCnt--);
		}
		
		return list;
    }
}
