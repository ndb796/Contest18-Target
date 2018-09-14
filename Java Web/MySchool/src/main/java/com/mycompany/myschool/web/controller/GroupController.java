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

import com.mycompany.myschool.web.entity.sns.GroupEntity;
import com.mycompany.myschool.web.service.sns.GroupService;
import com.mycompany.myschool.web.util.Constants;
import com.mycompany.myschool.web.util.IDGenerator;
import com.mycompany.myschool.web.util.ResultCode;

@Controller
public class GroupController {
	@Autowired
	private GroupService groupService;
	
	@RequestMapping("/groupList/{student_id}")
	@ResponseBody
    public void groupList(@ModelAttribute GroupEntity groupEntity,
    									 @PathVariable String student_id, 
    									 HttpServletRequest request, 
    									 HttpServletResponse response,
    									 Model model) {
		String callback = request.getParameter("callback");
		if (callback == null) {
			callback = "callback";
		}
		
		List<GroupEntity> list = groupService.listByStudent(student_id);
		JSONObject obj = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		
		for (GroupEntity group : list) {
			JSONObject groupJson = new JSONObject();
			groupJson.put("idx", group.getIdx());
			groupJson.put("group_id", group.getGroup_id());
			groupJson.put("group_name", group.getGroup_name());
			groupJson.put("student_id", group.getStudent_id());
			
			jsonArray.put(groupJson);
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
		
//		model.addAttribute("data", list);
//		return list;
    }
	
	@RequestMapping("/groupList/jsonpTest")
    public void jsonpTest(@ModelAttribute GroupEntity groupEntity,
    									 HttpServletRequest request,
    									 HttpServletResponse response, 
    									 Model model) {
		String callback = request.getParameter("callback");
		if (callback == null) {
			callback = "callback";
		}
		
		List<GroupEntity> list = groupService.list(groupEntity);
		JSONObject obj = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		
		for (GroupEntity group : list) {
			JSONObject groupJson = new JSONObject();
			groupJson.put("idx", group.getIdx());
			groupJson.put("group_id", group.getGroup_id());
			groupJson.put("group_name", group.getGroup_name());
			groupJson.put("student_id", group.getStudent_id());
			
			jsonArray.put(groupJson);
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
	
	@RequestMapping("/groupList")
    public ModelAndView groupListWeb(@ModelAttribute GroupEntity groupEntity,
    									 HttpServletRequest request, 
    									 Model model) {
		ModelAndView mav = new ModelAndView();
		
		List<GroupEntity> list = groupService.listByStudent(request.getParameter("student_id"));
		int totalCnt = list.size();
		for (GroupEntity entity : list) {
			entity.setNo(totalCnt--);
		}
		
		mav.addObject("student_id", request.getParameter("student_id"));
		mav.addObject("data", list);
		
		return mav;
    }
	
	@RequestMapping("/groupAdd.html")
    public String groupAdd(Model model,
    		 			   HttpServletRequest request) {
		GroupEntity groupEntity = new GroupEntity();
		groupEntity.setStudent_id(request.getParameter("student_id"));
		model.addAttribute("groupEntity", groupEntity);
		
        return "groupAdd";
    }
	
	@RequestMapping("/groupAddConf.html")
    public ModelAndView groupAddConf(@Valid @ModelAttribute GroupEntity groupEntity, 
								   	  BindingResult bindingResult,
								      SessionStatus sessionStatus,
								      Model model) {
		ModelAndView mav = new ModelAndView();
		
		try {
			if (bindingResult.hasErrors()) {
				mav.setViewName("groupAdd");
			} else {
				model.addAttribute("group_entity", groupEntity);
				mav.setViewName("groupAddConf");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
        return mav;
    }
	
	@RequestMapping(value="/groupAddProc.html", method=RequestMethod.POST)
    public ModelAndView groupAddProc(@ModelAttribute GroupEntity groupEntity, 
    								   BindingResult bindingResult,
    								   SessionStatus sessionStatus, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		
		try {
	        /*
	         * Database insert
	         */
			String currentId = groupService.getMaxId();
			groupEntity.setGroup_id(IDGenerator.NEXT_ID(Constants.SRVC_TYPE_GRP, currentId));
			boolean result = groupService.add(groupEntity);
			
			if (result) {
		        sessionStatus.setComplete();
		        
		        mav.addObject("resultCode", ResultCode.GROUP_C_SUCCESS_CODE);
		        mav.addObject("resultMsg", ResultCode.GROUP_C_SUCCESS_MSG);
			} else {
				mav.addObject("resultCode", ResultCode.GROUP_ERR_CREATE_CODE);
				mav.addObject("resultMsg", ResultCode.GROUP_ERR_CREATE_MSG);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		mav.addObject("student_id", groupEntity.getStudent_id());
		mav.setViewName("groupAddOk");
		
        return mav;
    }
	
}
