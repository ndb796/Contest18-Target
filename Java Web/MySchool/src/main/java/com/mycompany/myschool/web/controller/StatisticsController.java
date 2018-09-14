package com.mycompany.myschool.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mycompany.myschool.web.entity.base.UserEntity;
import com.mycompany.myschool.web.entity.customer.CustomerEntity;
import com.mycompany.myschool.web.entity.student.StudentEntity;
import com.mycompany.myschool.web.entity.vo.StatisticsVo;
import com.mycompany.myschool.web.service.base.UserService;
import com.mycompany.myschool.web.service.common.StatisticsService;
import com.mycompany.myschool.web.util.Constants;
import com.mycompany.myschool.web.util.ResultCode;
import com.mycompany.myschool.web.util.Util;

@Controller
public class StatisticsController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private StatisticsService statisticsService;
	
	@RequestMapping("/user/statistics/testStatistics")
    public ModelAndView testStatistics(HttpServletRequest request,
    								HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("/user/statistics/testStatistics");		
		
		mav.addObject("context_name", Util.LOAD_CONFIG("context_name"));
		mav.addObject("series", "{name: '학생', data: [11, 11, 15, 20, 5, 10, 12]}, {name: '학교',data: [8, 20, 28, 40, 20, 24, 17]}");
		
        return mav;
    }
	
	/*
	 * Student statistics
	 */
	@RequestMapping("/user/statistics/statistics_graph")
    public ModelAndView statistics_graph(HttpServletRequest request,
    									 Model model,
    									 HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		UserEntity userEntity = (UserEntity)session.getAttribute("login_user");
		
		if (userEntity != null) {		// login 페이로부터 이동 
			StudentEntity studentEntity = new StudentEntity();
			studentEntity.setStudent_id(userEntity.getUser_id());
			
			UserEntity dbUserEntity = userService.getByUserId(userEntity.getUser_id());
			model.addAttribute("userEntity", dbUserEntity);
			model.addAttribute("studentEntity", studentEntity);
			
			mav.setViewName("/user/statistics/statistics_graph");		
			
			mav.addObject("context_name", Util.LOAD_CONFIG("context_name"));
			mav.addObject("series", "{name: '학생', data: [11, 11, 15, 20, 5, 10, 12]}, {name: '학교',data: [8, 20, 28, 40, 20, 24, 17]}");
			
		} else {
			mav.setViewName("redirect:/index.do?resultCode=" + ResultCode.USER_ERR_NOTLOGIN_CODE);	
		}
		
		mav.addObject("locale", Util.GET_LOCALE(request.getHeader("Accept-Language")));
		mav.addObject("context_name", Util.LOAD_CONFIG("context_name"));
		
        return mav;
    }
	
	@RequestMapping("/user/statistics/statUser")
    public ModelAndView statUser(HttpServletRequest request,
    									 Model model,
    									 HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		UserEntity userEntity = (UserEntity)session.getAttribute("login_user");
		
		if (userEntity != null) {		// login 페이로부터 이동 
			StudentEntity studentEntity = new StudentEntity();
			studentEntity.setStudent_id(userEntity.getUser_id());
			
			UserEntity dbUserEntity = userService.getByUserId(userEntity.getUser_id());
			model.addAttribute("userEntity", dbUserEntity);
			model.addAttribute("studentEntity", studentEntity);
			
			mav.setViewName("/user/statistics/statUser");		
			
			String dataLabels = "dataLabels: {enabled: true, rotation: -90, color: '#FFFFFF', align: 'right', x: 4, y: 10, " +
											"style: {fontSize: '13px', fontFamily: 'Verdana, sans-serif', textShadow: '0 0 3px black'}}";
			
			List<StatisticsVo> statList = statisticsService.getRegCount();
			String schStr = "";
			String stdStr = "";
			for (StatisticsVo stat : statList) {
				if (Integer.parseInt(stat.getCol()) == Constants.USER_MEM_SCHOOL) {
					schStr = "'학교', " + stat.getCnt();
				} else if (Integer.parseInt(stat.getCol()) == Constants.USER_MEM_STUDENT) {
					schStr = "'학생', " + stat.getCnt();
				}
			}
			
			mav.addObject("series", "{name: 'Registration count', data: [[" + schStr + "], [" + stdStr + "]], " + dataLabels + "}");
		} else {
			mav.setViewName("redirect:/index.do?resultCode=" + ResultCode.USER_ERR_NOTLOGIN_CODE);	
		}
		
		mav.addObject("locale", Util.GET_LOCALE(request.getHeader("Accept-Language")));
		mav.addObject("context_name", Util.LOAD_CONFIG("context_name"));
		
        return mav;
    }
	
	@RequestMapping("/user/statistics/statConsult")
    public ModelAndView statConsult(HttpServletRequest request,
    									 Model model,
    									 HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		UserEntity userEntity = (UserEntity)session.getAttribute("login_user");
		
		if (userEntity != null) {		// login 페이로부터 이동 
			StudentEntity studentEntity = new StudentEntity();
			studentEntity.setStudent_id(userEntity.getUser_id());
			
			UserEntity dbUserEntity = userService.getByUserId(userEntity.getUser_id());
			model.addAttribute("userEntity", dbUserEntity);
			model.addAttribute("studentEntity", studentEntity);
			
			mav.setViewName("/user/statistics/statConsult");		
			
			String dataLabels = "dataLabels: {enabled: true, rotation: -90, color: '#FFFFFF', align: 'right', x: 4, y: 10, " +
											"style: {fontSize: '13px', fontFamily: 'Verdana, sans-serif', textShadow: '0 0 3px black'}}";
			StatisticsVo stat = statisticsService.getConsultCountByUserId(userEntity.getUser_id());
			String schStr = "'전체', " + stat.getCnt();
			String stdStr = "'학생', " + stat.getCnt2();
			
			mav.addObject("series", "{name: 'Registration count', data: [[" + schStr + "], [" + stdStr + "]], " + dataLabels + "}");
		} else {
			mav.setViewName("redirect:/index.do?resultCode=" + ResultCode.USER_ERR_NOTLOGIN_CODE);	
		}
		
		mav.addObject("locale", Util.GET_LOCALE(request.getHeader("Accept-Language")));
		mav.addObject("context_name", Util.LOAD_CONFIG("context_name"));
		
        return mav;
    }
	
	/*
	 * Customer statistics
	 */
	@RequestMapping("/customer/statistics/statAccess")
    public ModelAndView customerStatAccess(HttpServletRequest request,
    									 Model model,
    									 HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		UserEntity userEntity = (UserEntity)session.getAttribute("login_user");
		
		if (userEntity != null) {		// login 페이로부터 이동 
			CustomerEntity schoolEntity = new CustomerEntity();
			schoolEntity.setSchool_id(userEntity.getUser_id());
			
			model.addAttribute("userEntity", userEntity);
			model.addAttribute("schoolEntity", schoolEntity);
			
			mav.setViewName("/customer/statistics/statAccess");	
			mav.addObject("period", Util.GET_7DAYS_FORMAT());
			
			String[] days = Util.GET_7DAYS_UNFORMAT();
			List<StatisticsVo> accessCntList = statisticsService.getAccessCount();
			
			Map<String, Integer> stdMap = new HashMap<String, Integer>();
			Map<String, Integer> schMap = new HashMap<String, Integer>();
			for (StatisticsVo stat : accessCntList) {
				schMap.put(stat.getCnt3(), stat.getCnt());
				stdMap.put(stat.getCnt3(), stat.getCnt2());
			}
			
			StringBuffer stdSB = new StringBuffer("[");
			StringBuffer schSB = new StringBuffer("[");
			StringBuffer allSB = new StringBuffer("[");
			int i = 1;
			for (String day : days) {
				int stdCnt = 0;
				if (stdMap.containsKey(day)) {
					stdCnt = stdMap.get(day);
				} 
				
				int schCnt = 0;
				if (schMap.containsKey(day)) {
					schCnt = schMap.get(day);
				}
				
				stdSB.append(stdCnt);
				schSB.append(schCnt);
				allSB.append(stdCnt + schCnt);
				
				if (i-- < (days.length - 1)) {
					stdSB.append(",");
					schSB.append(",");
					allSB.append(",");
				}
			}
			stdSB.append("]");
			schSB.append("]");
			allSB.append("]");	
			
			mav.addObject("series", "{name: '전체', data: " + allSB.toString() + "}, " + 
									"{name: '학교', data: " + schSB.toString() + "}, " + 
									"{name: '학생', data: " + stdSB.toString() + "}");
		} else {
			mav.setViewName("redirect:/index.do?resultCode=" + ResultCode.USER_ERR_NOTLOGIN_CODE);	
		}
		
		mav.addObject("locale", Util.GET_LOCALE(request.getHeader("Accept-Language")));
		mav.addObject("context_name", Util.LOAD_CONFIG("context_name"));
		
        return mav;
    }
	

	@RequestMapping("/customer/statistics/statUser")
    public ModelAndView customerStatUser(HttpServletRequest request,
    									 Model model,
    									 HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		UserEntity userEntity = (UserEntity)session.getAttribute("login_user");
		
		if (userEntity != null) {		// login 페이로부터 이동 
			StudentEntity studentEntity = new StudentEntity();
			studentEntity.setStudent_id(userEntity.getUser_id());
			
			UserEntity dbUserEntity = userService.getByUserId(userEntity.getUser_id());
			model.addAttribute("userEntity", dbUserEntity);
			model.addAttribute("studentEntity", studentEntity);
			
			mav.setViewName("/customer/statistics/statUser");		
			mav.addObject("period", Util.GET_7DAYS_FORMAT());
			
			String[] days = Util.GET_7DAYS_UNFORMAT();
			List<StatisticsVo> stdList = statisticsService.getByUserCount(Constants.USER_MEM_STUDENT);
			List<StatisticsVo> schList = statisticsService.getByUserCount(Constants.USER_MEM_SCHOOL);
			
			Map<String, Integer> stdMap = new HashMap<String, Integer>();
			for (StatisticsVo stat : stdList) {
				stdMap.put(stat.getCol(), stat.getCnt());
			}
			Map<String, Integer> schMap = new HashMap<String, Integer>();
			for (StatisticsVo stat : schList) {
				schMap.put(stat.getCol(), stat.getCnt());
			}
			
			StringBuffer stdSB = new StringBuffer("[");
			StringBuffer schSB = new StringBuffer("[");
			StringBuffer allSB = new StringBuffer("[");
			int i = 1;
			for (String day : days) {
				int stdCnt = 0;
				if (stdMap.containsKey(day)) {
					stdCnt = stdMap.get(day);
				} 
				
				int schCnt = 0;
				if (schMap.containsKey(day)) {
					schCnt = schMap.get(day);
				}
				
				stdSB.append(stdCnt);
				schSB.append(schCnt);
				allSB.append(stdCnt + schCnt);
				
				if (i-- < (days.length - 1)) {
					stdSB.append(",");
					schSB.append(",");
					allSB.append(",");
				}
			}
			stdSB.append("]");
			schSB.append("]");
			allSB.append("]");
			
			mav.addObject("series", "{name: '전체', data: " + allSB.toString() + "}, " + 
									"{name: '학교', data: " + schSB.toString() + "}, " + 
									"{name: '학생', data: " + stdSB.toString() + "}");
		} else {
			mav.setViewName("redirect:/index.do?resultCode=" + ResultCode.USER_ERR_NOTLOGIN_CODE);	
		}
		
		mav.addObject("locale", Util.GET_LOCALE(request.getHeader("Accept-Language")));
		mav.addObject("context_name", Util.LOAD_CONFIG("context_name"));
		
        return mav;
    }
	
	@RequestMapping("/customer/statistics/statConsult")
    public ModelAndView customerStatConsult(HttpServletRequest request,
    									 Model model,
    									 HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		UserEntity userEntity = (UserEntity)session.getAttribute("login_user");
		
		if (userEntity != null) {		// login 페이로부터 이동 
			StudentEntity studentEntity = new StudentEntity();
			studentEntity.setStudent_id(userEntity.getUser_id());
			
			UserEntity dbUserEntity = userService.getByUserId(userEntity.getUser_id());
			model.addAttribute("userEntity", dbUserEntity);
			model.addAttribute("studentEntity", studentEntity);
			
			mav.setViewName("/customer/statistics/statConsult");		
			mav.addObject("period", Util.GET_7DAYS_FORMAT());
			
			String[] days = Util.GET_7DAYS_UNFORMAT();
			List<StatisticsVo> stdList = statisticsService.getConsultCountAll(Constants.CONST_STATUS_REQ); // student -> school
			List<StatisticsVo> schList = statisticsService.getConsultCountAll(Constants.CONST_STATUS_RES); // school -> student
			
			Map<String, Integer> stdMap = new HashMap<String, Integer>();
			for (StatisticsVo stat : stdList) {
				stdMap.put(stat.getCol(), stat.getCnt());
			}
			Map<String, Integer> schMap = new HashMap<String, Integer>();
			for (StatisticsVo stat : schList) {
				schMap.put(stat.getCol(), stat.getCnt());
			}
			
			StringBuffer stdSB = new StringBuffer("[");
			StringBuffer schSB = new StringBuffer("[");
			StringBuffer allSB = new StringBuffer("[");
			int i = 1;
			for (String day : days) {
				int stdCnt = 0;
				if (stdMap.containsKey(day)) {
					stdCnt = stdMap.get(day);
				} 
				
				int schCnt = 0;
				if (schMap.containsKey(day)) {
					schCnt = schMap.get(day);
				}
				
				stdSB.append(stdCnt);
				schSB.append(schCnt);
				allSB.append(stdCnt + schCnt);
				
				if (i-- < (days.length - 1)) {
					stdSB.append(",");
					schSB.append(",");
					allSB.append(",");
				}
			}
			stdSB.append("]");
			schSB.append("]");
			allSB.append("]");
			
			mav.addObject("series", "{name: '학교',data: " + schSB.toString() + "}, " + 
									"{name: '학생', data: " + stdSB.toString() + "}");
			
		} else {
			mav.setViewName("redirect:/index.do?resultCode=" + ResultCode.USER_ERR_NOTLOGIN_CODE);	
		}
		
		mav.addObject("locale", Util.GET_LOCALE(request.getHeader("Accept-Language")));
		mav.addObject("context_name", Util.LOAD_CONFIG("context_name"));
		
        return mav;
    }
	
	@RequestMapping("/customer/statistics/statEtc")
    public ModelAndView customerStatEtc(HttpServletRequest request,
    									 Model model,
    									 HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		UserEntity userEntity = (UserEntity)session.getAttribute("login_user");
		
		if (userEntity != null) {		// login 페이로부터 이동 
			CustomerEntity schoolEntity = new CustomerEntity();
			schoolEntity.setSchool_id(userEntity.getUser_id());
			
			UserEntity dbUserEntity = userService.getByUserId(userEntity.getUser_id());
			model.addAttribute("userEntity", dbUserEntity);
			model.addAttribute("schoolEntity", schoolEntity);
			
			mav.setViewName("/customer/statistics/statEtc");		
			
			mav.addObject("period", "'15일(금)', '16일(토)', '17일(일)', '18일(월)', '19일(화)', '20일(수)', '21일(목)'");
			mav.addObject("series", "{type: 'column', name: '즐겨찾기', data: [11, 11, 15, 20, 5, 10, 12]}, " + 
									"{type: 'column', name: '추천',data: [8, 20, 28, 40, 20, 24, 17]}, " + 
									"{type: 'column', name: '조회수',data: [50, 60, 70, 40, 20, 51, 33]}, " + 
									"{type: 'spline', name: '평균',  data: [13, 27, 30, 12, 55, 45, 30], " + 
										"marker: { lineWidth: 2, lineColor: Highcharts.getOptions().colors[3], fillColor: 'white' }}");
			
		} else {
			mav.setViewName("redirect:/index.do?resultCode=" + ResultCode.USER_ERR_NOTLOGIN_CODE);	
		}
		
		mav.addObject("locale", Util.GET_LOCALE(request.getHeader("Accept-Language")));
		mav.addObject("context_name", Util.LOAD_CONFIG("context_name"));
		
        return mav;
    }
}
