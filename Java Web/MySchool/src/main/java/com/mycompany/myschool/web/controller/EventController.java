package com.mycompany.myschool.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.mycompany.myschool.web.entity.board.ScheduleEntity;
import com.mycompany.myschool.web.service.board.ScheduleService;
import com.mycompany.myschool.web.util.Constants;
import com.mycompany.myschool.web.util.IDGenerator;
import com.mycompany.myschool.web.util.ResultCode;

@Controller
public class EventController {
	@Autowired
	private ScheduleService scheduleService;
	
	@RequestMapping("/eventList/{student_id}")
	@ResponseBody
    public void eventList(@ModelAttribute ScheduleEntity ScheduleEntity,
    									 @PathVariable String student_id,
    									 HttpServletRequest request, 
    									 HttpServletResponse response,
    									 Model model) {
		String callback = request.getParameter("callback");
		if (callback == null) {
			callback = "callback";
		}
		
		List<ScheduleEntity> list = scheduleService.listByStudent(student_id);
		
		JSONObject obj = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		
		for (ScheduleEntity event : list) {
			JSONObject eventJson = new JSONObject();
			eventJson.put("idx", event.getIdx());
			eventJson.put("evt_id", event.getEvt_id());
			eventJson.put("evt_type", event.getEvt_type());
			eventJson.put("group_id", event.getGroup_id());
			eventJson.put("student_id", event.getStudent_id());
			eventJson.put("evt_date_s", event.getEvt_date_s());
			eventJson.put("evt_date_e", event.getEvt_date_e());
			eventJson.put("evt_time_s", event.getEvt_time_s());
			eventJson.put("evt_time_e", event.getEvt_time_e());
			eventJson.put("evt_location", event.getEvt_location());
			eventJson.put("evt_contents", event.getEvt_contents());
			eventJson.put("ins_date", event.getIns_date());
			eventJson.put("upd_date", event.getUpd_date());
			
			jsonArray.put(eventJson);
		}
		
		obj.put("items", jsonArray);
		
		try {
			PrintWriter out = response.getWriter();
			
			out.write(callback + "(" + obj.toString() + ")");
//			System.out.println(callback + "(" + obj.toString() + ")");
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	@RequestMapping("/eventList/jsonpTest")
    public void jsonpTest(@ModelAttribute ScheduleEntity ScheduleEntity,
    									 HttpServletRequest request,
    									 HttpServletResponse response, 
    									 Model model) {
		String callback = request.getParameter("callback");
		if (callback == null) {
			callback = "callback";
		}
		
		List<ScheduleEntity> list = scheduleService.list(ScheduleEntity);
		JSONObject obj = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		
		for (ScheduleEntity event : list) {
			JSONObject eventJson = new JSONObject();
			eventJson.put("idx", event.getIdx());
			eventJson.put("evt_id", event.getEvt_id());
			eventJson.put("evt_type", event.getEvt_type());
			eventJson.put("group_id", event.getGroup_id());
			eventJson.put("student_id", event.getStudent_id());
			eventJson.put("evt_date_s", event.getEvt_date_s());
			eventJson.put("evt_date_e", event.getEvt_date_e());
			eventJson.put("evt_time_s", event.getEvt_time_s());
			eventJson.put("evt_time_e", event.getEvt_time_e());
			eventJson.put("evt_location", event.getEvt_location());
			eventJson.put("evt_contents", event.getEvt_contents());
			eventJson.put("ins_date", event.getIns_date());
			eventJson.put("upd_date", event.getUpd_date());
			
			jsonArray.put(eventJson);
		}
		
		obj.put("items", jsonArray);
		
		try {
			PrintWriter out = response.getWriter();
			
			out.write(callback + "(" + obj.toString() + ")");
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	@RequestMapping("/eventList/calendar")
    public void jsonpTestForCalendar(@ModelAttribute ScheduleEntity ScheduleEntity,
    									 HttpServletRequest request,
    									 HttpServletResponse response, 
    									 Model model) {
		String callback = request.getParameter("callback");
		if (callback == null) {
			callback = "callback";
		}
		
		JSONObject obj = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		
		JSONObject eventJson = new JSONObject();
		eventJson.put("title", "All Day Event");
		eventJson.put("start", "2014-07-01T09:30:00");
		eventJson.put("end", "2014-07-01T11:00:00");
		jsonArray.put(eventJson);
		
		eventJson = new JSONObject();
		eventJson.put("title", "Long Event");
		eventJson.put("start", "2014-05-07");
		eventJson.put("end", "2014-05-10");
		jsonArray.put(eventJson);
		
		obj.put("items", jsonArray);
		
		try {
			PrintWriter out = response.getWriter();
			
			out.write(callback + "(" + obj.toString() + ")");
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	@RequestMapping("/eventDetail/{evt_id}")
    public void jsonpTestDetail(@PathVariable String evt_id,
    									 HttpServletRequest request,
    									 HttpServletResponse response, 
    									 Model model) {
		String callback = request.getParameter("callback");
		if (callback == null) {
			callback = "callback";
		}
		JSONObject eventJson = new JSONObject();
		eventJson.put("title", "All Day Event");
		eventJson.put("start", "2014-05-01T09:30:00");
		eventJson.put("end", "2014-05-01T11:00:00");
		eventJson.put("location", "Tokyo");
		eventJson.put("contents", "Test Contents<br/>1<br/>2");
		
		try {
			PrintWriter out = response.getWriter();
			
			out.write(callback + "(" + eventJson.toString() + ")");
			out.flush();
			out.close();
		} catch (IOException e) {
			
		}
    }
	
	@RequestMapping("/eventList")
    public ModelAndView eventListWeb(@ModelAttribute ScheduleEntity ScheduleEntity,
    									 HttpServletRequest request, 
    									 Model model) {
		ModelAndView mav = new ModelAndView();
		
		ScheduleEntity.setStudent_id(request.getParameter("student_id"));
		ScheduleEntity.setGroup_id(request.getParameter("group_id"));
		
		List<ScheduleEntity> list = scheduleService.listByGroup(ScheduleEntity);

		int totalCnt = list.size();
		for (ScheduleEntity entity : list) {
			entity.setNo(totalCnt--);
		}
		
		mav.addObject("group_id", request.getParameter("group_id"));
		mav.addObject("student_id", request.getParameter("student_id"));
		mav.addObject("data", list);
		
		return mav;
    }
	
	@RequestMapping("/eventAdd.html")
    public String eventAdd(Model model,
    		 			   HttpServletRequest request) {
		model.addAttribute("student_id", request.getParameter("student_id"));
		model.addAttribute("group_id", request.getParameter("group_id"));
		
        return "eventAdd";
    }
	
	@RequestMapping("/eventAddConf.html")
    public ModelAndView eventAddConf(@Valid @ModelAttribute ScheduleEntity ScheduleEntity, 
								   	  BindingResult bindingResult,
								      SessionStatus sessionStatus,
								      Model model) {
		ModelAndView mav = new ModelAndView();
		
		try {
			if (bindingResult.hasErrors()) {
				mav.setViewName("eventAdd");
			} else {
				model.addAttribute("event_entity", ScheduleEntity);
				mav.setViewName("eventAddConf");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
        return mav;
    }
	
	@RequestMapping(value="/eventAddProc.html", method=RequestMethod.POST)
    public ModelAndView eventAddProc(@ModelAttribute ScheduleEntity ScheduleEntity, 
    								   BindingResult bindingResult,
    								   SessionStatus sessionStatus, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		
		try {
	        /*
	         * Database insert
	         */
			String currentId = scheduleService.getMaxId();
			ScheduleEntity.setEvt_id(IDGenerator.NEXT_ID(Constants.SRVC_TYPE_EVT, currentId));
			boolean result = scheduleService.add(ScheduleEntity);
			
			if (result) {
		        sessionStatus.setComplete();
		        
		        mav.addObject("resultCode", ResultCode.EVENT_C_SUCCESS_CODE);
		        mav.addObject("resultMsg", ResultCode.EVENT_C_SUCCESS_MSG);
			} else {
				mav.addObject("resultCode", ResultCode.EVENT_ERR_CREATE_CODE);
				mav.addObject("resultMsg", ResultCode.EVENT_ERR_CREATE_MSG);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		mav.addObject("student_id", ScheduleEntity.getStudent_id());
		mav.addObject("group_id", ScheduleEntity.getGroup_id());
		mav.setViewName("eventAddOk");
		
        return mav;
    }
}
