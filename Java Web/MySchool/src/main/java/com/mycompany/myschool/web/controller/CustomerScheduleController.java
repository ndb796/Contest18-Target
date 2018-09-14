package com.mycompany.myschool.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

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
import org.springframework.web.servlet.ModelAndView;

import com.mycompany.myschool.web.entity.base.UserEntity;
import com.mycompany.myschool.web.entity.board.ScheduleEntity;
import com.mycompany.myschool.web.entity.customer.CustomerEntity;
import com.mycompany.myschool.web.entity.student.StudentEntity;
import com.mycompany.myschool.web.service.board.ScheduleService;
import com.mycompany.myschool.web.util.Constants;
import com.mycompany.myschool.web.util.IDGenerator;
import com.mycompany.myschool.web.util.ResultCode;
import com.mycompany.myschool.web.util.Token;
import com.mycompany.myschool.web.util.Util;

@Controller
public class CustomerScheduleController {
	@Autowired
	private ScheduleService scheduleService;
	
	@RequestMapping("/customer/schedule/calendar")
	public ModelAndView calendar(HttpServletRequest request,
										HttpSession session,
										Model model) {
		ModelAndView mav = new ModelAndView();
		UserEntity userEntity = (UserEntity)session.getAttribute("login_user");
		
		mav.addObject("locale", Util.GET_LOCALE(request.getHeader("Accept-Language")));
		mav.addObject("context_name", Util.LOAD_CONFIG("context_name"));
		
		return mav;
	}
	
	@RequestMapping("/customer/schedule/schedule")
	public ModelAndView schedule(HttpServletRequest request,
										HttpSession session,
										Model model) {
		ModelAndView mav = new ModelAndView();
		UserEntity userEntity = (UserEntity)session.getAttribute("login_user");
		
		if (userEntity != null) {
			CustomerEntity schoolEntity = new CustomerEntity();
			schoolEntity.setSchool_id(userEntity.getUser_id());
			
			model.addAttribute("userEntity", userEntity);
			model.addAttribute("schoolEntity", schoolEntity);
		} else {
			mav.setViewName("redirect:/index.do?resultCode=" + ResultCode.USER_ERR_NOTLOGIN_CODE);
		}
		
		mav.addObject("locale", Util.GET_LOCALE(request.getHeader("Accept-Language")));
		mav.addObject("context_name", Util.LOAD_CONFIG("context_name"));
		
		return mav;
	}
	
	@RequestMapping("/customer/schedule/scheduleAll")
	public ModelAndView scheduleAll(HttpServletRequest request,
										HttpSession session,
										Model model) {
		ModelAndView mav = new ModelAndView();
		UserEntity userEntity = (UserEntity)session.getAttribute("login_user");
		
		if (userEntity != null) {
			CustomerEntity schoolEntity = new CustomerEntity();
			schoolEntity.setSchool_id(userEntity.getUser_id());
			
			model.addAttribute("userEntity", userEntity);
			model.addAttribute("schoolEntity", schoolEntity);
		} else {
			mav.setViewName("redirect:/index.do?resultCode=" + ResultCode.USER_ERR_NOTLOGIN_CODE);
		}
		
		mav.addObject("locale", Util.GET_LOCALE(request.getHeader("Accept-Language")));
		mav.addObject("context_name", Util.LOAD_CONFIG("context_name"));
		
		return mav;
	}
	
	@RequestMapping("/customer/schedule/scheduleJson")
    public void jsonpTest(HttpServletRequest request,
    									 HttpServletResponse response, 
    									 Model model) {
		String callback = request.getParameter("callback");
		if (callback == null) {
			callback = "callback";
		}
		String isAll = request.getParameter("isAll");
		
		Calendar cal = Calendar.getInstance();
		
		List<ScheduleEntity> scheduleList = null;
		ScheduleEntity scheduleEntity = new ScheduleEntity();
		scheduleEntity.setEvt_date_s(cal.get(Calendar.YEAR) + "-01-01");
		scheduleEntity.setEvt_date_e(cal.get(Calendar.YEAR) + "-12-31");
		
		if (isAll != null && isAll.equals("yes")) {
			scheduleEntity.setEvt_type(Constants.SCHE_TYPE_PUBLIC);
			scheduleList = scheduleService.listOnPublic(scheduleEntity);
		} else {
			scheduleEntity.setStudent_id(request.getParameter("school_id"));
			scheduleList = scheduleService.listInRange(scheduleEntity);
		}
		
		JSONObject obj = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		
		for (ScheduleEntity event : scheduleList) {
			JSONObject eventJson = new JSONObject();
			eventJson.put("idx", event.getIdx());
			eventJson.put("evt_id", event.getEvt_id());
			eventJson.put("evt_type", event.getEvt_type());
			eventJson.put("evt_category", event.getEvt_category());
			eventJson.put("group_id", event.getGroup_id());
			eventJson.put("student_id", event.getStudent_id());
			eventJson.put("title", event.getEvt_title());
			eventJson.put("evt_date_s", event.getEvt_date_s());
			eventJson.put("evt_date_e", event.getEvt_date_e());
			eventJson.put("evt_time_s", event.getEvt_time_s());
			eventJson.put("evt_time_e", event.getEvt_time_e());
			eventJson.put("start", event.getEvt_date_s() + "T" + event.getEvt_time_s());
			eventJson.put("end", event.getEvt_date_e() + "T" + event.getEvt_time_e());
			
			jsonArray.put(eventJson);
		}
		
		obj.put("items", jsonArray);
		
		try {
			PrintWriter out = response.getWriter();
			
			out.write(callback + "(" + obj.toString() + ")");
			System.out.println(callback + "(" + obj.toString() + ")");
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	@RequestMapping("/customer/schedule/scheduleMonthly")
	public ModelAndView scheduleMonthly(HttpServletRequest request,
										HttpSession session,
										Model model) {
		ModelAndView mav = new ModelAndView();
		UserEntity userEntity = (UserEntity)session.getAttribute("login_user");
		
		if (userEntity != null) {		
			int[] calendar = getDateInfoFromReq(request);
		
			mav.addObject("weekdays", Constants.WEEKDAYS_ja);
			mav.addObject("current_year", calendar[0]);
			mav.addObject("current_month", calendar[1]);
			mav.addObject("monthly_days", Util.MONTHLY_DAYS(calendar[0], calendar[1]));
			
			CustomerEntity customerEntity = new CustomerEntity();
			customerEntity.setSchool_id(userEntity.getUser_id());
			
			model.addAttribute("userEntity", userEntity);
			model.addAttribute("customerEntity", customerEntity);
			
			ScheduleEntity scheduleEntity = new ScheduleEntity();
			scheduleEntity.setStudent_id(userEntity.getUser_id());
			scheduleEntity.setEvt_date_s(calendar[0] + "-" + 
										((calendar[1] + 1) < 10 ? "0" + (calendar[1] + 1) : "" + (calendar[1] + 1)) + "-" + 1);
			int endOfMonth = Constants.END_OF_MONTH[calendar[1]];
			if (Util.IS_LEAP_YEAR(calendar[0])) {
				endOfMonth++;
			}
			scheduleEntity.setEvt_date_e(calendar[0] + "-" + 
										((calendar[1] + 1) < 10 ? "0" + (calendar[1] + 1) : "" + (calendar[1] + 1)) + "-" + endOfMonth);
			
			List<ScheduleEntity> scheduleList = scheduleService.listInRange(scheduleEntity);
			Map<String, List<ScheduleEntity>> eventMap = new HashMap<String, List<ScheduleEntity>>();
			for (ScheduleEntity sEntity : scheduleList) {
				List<ScheduleEntity> eventOfDay = null;
				
				String key = sEntity.getEvt_date_s();
				String[] startDates = key.split("-");
				int day = Integer.parseInt(startDates[2]);
				
				if (eventMap.containsKey(Integer.toString(day))) {
					eventOfDay = eventMap.get(Integer.toString(day));
				} else {
					eventOfDay = new ArrayList<ScheduleEntity>();
				}
				
				eventOfDay.add(sEntity);
				eventMap.put(Integer.toString(day), eventOfDay);
			}
			
			mav.addObject("event_map", eventMap);
			mav.setViewName("/customer/schedule/scheduleMonthly");
		} else {
			mav.setViewName("redirect:/index.do?resultCode=" + ResultCode.USER_ERR_NOTLOGIN_CODE);
		}
		
		mav.addObject("locale", Util.GET_LOCALE(request.getHeader("Accept-Language")));
		mav.addObject("context_name", Util.LOAD_CONFIG("context_name"));
		
		return mav;
	}
	
	@RequestMapping("/customer/schedule/scheduleWeekly")
	public ModelAndView scheduleWeekly(HttpServletRequest request,
										HttpSession session) {
		ModelAndView mav = new ModelAndView();
		UserEntity userEntity = (UserEntity)session.getAttribute("login_user");
		
		if (userEntity != null) {		// login �럹�씠濡쒕��꽣 �씠�룞 
			int[] calendar = getDateInfoFromReq(request);
			
			mav.addObject("weekdays", Constants.WEEKDAYS_ja);
			mav.addObject("current_year", calendar[0]);
			mav.addObject("current_month", calendar[1]);
			mav.addObject("current_week", calendar[2]);
			mav.addObject("weekly_days", Util.WEEKLY_DAYS(calendar[0], calendar[1], calendar[2]));
			
			mav.setViewName("/customer/schedule/scheduleWeekly");
		} else {
			mav.setViewName("redirect:/index.do?resultCode=" + ResultCode.USER_ERR_NOTLOGIN_CODE);
		}
		
		mav.addObject("locale", Util.GET_LOCALE(request.getHeader("Accept-Language")));
		mav.addObject("context_name", Util.LOAD_CONFIG("context_name"));
		
		return mav;
	}
	
	@RequestMapping("/customer/schedule/scheduleDaily")
	public ModelAndView scheduleDaily(HttpServletRequest request,
										HttpSession session) {
		ModelAndView mav = new ModelAndView();
		UserEntity userEntity = (UserEntity)session.getAttribute("login_user");
		
		if (userEntity != null) {		
			int[] calendar = getDateInfoOfToday(request);
			
			mav.addObject("current_dayOfWeek", Constants.WEEKDAYS_ja[calendar[3] - 1]);
			mav.addObject("current_year", calendar[0]);
			mav.addObject("current_month", calendar[1]);
			mav.addObject("current_day", calendar[2]);
			
			mav.setViewName("/customer/schedule/scheduleDaily");
		} else {
			mav.setViewName("redirect:/index.do?resultCode=" + ResultCode.USER_ERR_NOTLOGIN_CODE);
		}
		
		mav.addObject("locale", Util.GET_LOCALE(request.getHeader("Accept-Language")));
		mav.addObject("context_name", Util.LOAD_CONFIG("context_name"));
		
		return mav;
	}
	
	@RequestMapping("/customer/schedule/scheduleAddProc")
    public void scheduleAddProc(@ModelAttribute("scheduleEntity") ScheduleEntity scheduleEntity,
    								  Model model,
									  HttpServletRequest request,
									  HttpSession session,
									  HttpServletResponse response) {
		String callback = request.getParameter("jsoncallback");

		JSONObject obj = new JSONObject();
		
		UserEntity userEntity = (UserEntity)session.getAttribute("login_user");
		if (userEntity != null) {
			if(Token.isValid(request)) {
			    Token.set(request);
			    request.setAttribute("TOKEN_SAVE_CHECK", "TRUE");
			} else {
				request.setAttribute("TOKEN_SAVE_CHECK", "FALSE");
			}
			
//			if("TRUE".equals(request.getAttribute("TOKEN_SAVE_CHECK"))) {
				String evt_datetime_s = request.getParameter("evt_datetime_s");
				String evt_datetime_e = request.getParameter("evt_datetime_e");
				
				StringTokenizer st = new StringTokenizer(evt_datetime_s, "T");
				scheduleEntity.setEvt_date_s(st.nextToken());
				scheduleEntity.setEvt_time_s(st.nextToken());
				
				st = new StringTokenizer(evt_datetime_e, "T");
				scheduleEntity.setEvt_date_e(st.nextToken());
				scheduleEntity.setEvt_time_e(st.nextToken());
				
				String currentId = scheduleService.getMaxId();
				scheduleEntity.setEvt_id(IDGenerator.NEXT_ID(Constants.SRVC_TYPE_EVT, currentId));
				scheduleEntity.setGroup_id("group_id");
				
				scheduleService.add(scheduleEntity);
				
				obj.put("result_code", ResultCode.EVENT_C_SUCCESS_CODE);
				obj.put("result_msg", ResultCode.EVENT_C_SUCCESS_MSG);
//			} else {
//				obj.put("result_code", ResultCode.EVENT_C_SUCCESS_CODE);
//				obj.put("result_msg", ResultCode.EVENT_C_SUCCESS_MSG);
//			}
			
			StudentEntity studentEntity = new StudentEntity();
			studentEntity.setStudent_id(userEntity.getUser_id());
			CustomerEntity schoolEntity = new CustomerEntity();
			schoolEntity.setSchool_id(userEntity.getUser_id());
			
			model.addAttribute("userEntity", userEntity);
			model.addAttribute("studentEntity", studentEntity);
			model.addAttribute("schoolEntity", schoolEntity);
			
			session.removeAttribute("token");
		} else {
//			mav.setViewName("redirect:/index.do");
		}
		
		try {
			PrintWriter out = response.getWriter();
			
			out.write(callback + "(" + obj.toString() + ")");
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
    }
	
	@RequestMapping("/customer/schedule/{evt_id}")
    public void jsonpTestDetail(@PathVariable String evt_id,
    									 HttpServletRequest request,
    									 HttpServletResponse response, 
    									 Model model) {
		String callback = request.getParameter("jsoncallback");
		
		ScheduleEntity scheduleEntity = new ScheduleEntity();
		scheduleEntity.setEvt_id(evt_id);
		ScheduleEntity dbScheduleEntity = scheduleService.get(scheduleEntity);
		
		JSONObject eventJson = new JSONObject();
		eventJson.put("evt_title", dbScheduleEntity.getEvt_title());
		eventJson.put("evt_type", dbScheduleEntity.getEvt_type());
		eventJson.put("evt_datetime_s", dbScheduleEntity.getEvt_date_s() + "T" + dbScheduleEntity.getEvt_time_s());
		eventJson.put("evt_datetime_e", dbScheduleEntity.getEvt_date_e() + "T" + dbScheduleEntity.getEvt_time_e());
		eventJson.put("evt_location", dbScheduleEntity.getEvt_location());
		eventJson.put("evt_contents", dbScheduleEntity.getEvt_contents());
		
		try {
			PrintWriter out = response.getWriter();
			out.write(callback + "(" + eventJson.toString() + ")");
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	private int[] getDateInfoFromReq(HttpServletRequest request) {
		Calendar cal = Calendar.getInstance();
		int year = 0;
		int month = 0;
		int week = 0;
		
		if (request.getParameter("curYear") == null || request.getParameter("curYear") .length() == 0) {
			year = cal.get(Calendar.YEAR);
		} else {
			year = Integer.parseInt(request.getParameter("curYear"));
		}
		
		if (request.getParameter("curMonth") == null || request.getParameter("curMonth") .length() == 0) {
			month = cal.get(Calendar.MONTH);
		} else {
			month = Integer.parseInt(request.getParameter("curMonth"));
		}
		
		if (month == -1) {
			year = year - 1;
			month = 11;
		}
		
		if (month == 12) {
			year = year + 1;
			month = 0;
		}
		
		if (request.getParameter("curWeek") == null || request.getParameter("curWeek") .length() == 0) {
			week = cal.get(Calendar.WEEK_OF_YEAR);
		} else {
			week = Integer.parseInt(request.getParameter("curWeek"));
		}
		
		int[] calendar = new int[3];
		calendar[0] = year;
		calendar[1] = month;
		calendar[2] = week;
		
		return calendar;
	}
	
	private int[] getDateInfoOfToday(HttpServletRequest request) {
		Calendar cal = Calendar.getInstance();
		int year = 0;
		int month = 0;
		int day = 0;
		
		if (request.getParameter("curYear") == null || request.getParameter("curYear") .length() == 0) {
			year = cal.get(Calendar.YEAR);
		} else {
			year = Integer.parseInt(request.getParameter("curYear"));
		}
		
		if (request.getParameter("curMonth") == null || request.getParameter("curMonth") .length() == 0) {
			month = cal.get(Calendar.MONTH);
		} else {
			month = Integer.parseInt(request.getParameter("curMonth"));
		}
		
		if (request.getParameter("curDay") == null || request.getParameter("curDay") .length() == 0) {
			day = cal.get(Calendar.DATE);
		} else {
			day = Integer.parseInt(request.getParameter("curDay"));
		}
		
		GregorianCalendar gCal = (GregorianCalendar)GregorianCalendar.getInstance();
		int endDayOfBeforeMonth = Constants.END_OF_MONTH[month - 1];
		int endDayOfThisMonth = Constants.END_OF_MONTH[month];
		
		// Leap year
		if (gCal.isLeapYear(year)) {
			endDayOfBeforeMonth++; 
			endDayOfThisMonth++;
		}
		
		if (day == 0) {
			month = month - 1;
			day = endDayOfBeforeMonth;
		}
		if (day > endDayOfThisMonth) {
			month = month + 1;
			day = 1;
		}
		
		if (month == -1) {
			year = year - 1;
			month = 11;
		}
		
		if (month == 12) {
			year = year + 1;
			month = 0;
		}
		
		cal.set(year, month, day);
		int[] calendar = new int[4];
		calendar[0] = year;
		calendar[1] = month;
		calendar[2] = day;
		calendar[3] = cal.get(Calendar.DAY_OF_WEEK);
		
		return calendar;
	}
}
