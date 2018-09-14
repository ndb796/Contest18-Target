package com.mycompany.myschool.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.mycompany.myschool.web.entity.sns.MyGroupEntity;
import com.mycompany.myschool.web.entity.student.StudentEntity;
import com.mycompany.myschool.web.service.sns.GroupService;
import com.mycompany.myschool.web.service.sns.MyGroupService;
import com.mycompany.myschool.web.service.student.StudentService;
import com.mycompany.myschool.web.util.Constants;
import com.mycompany.myschool.web.util.ResultCode;
import com.mycompany.myschool.web.util.Util;

@Controller
public class MyGroupController {
	@Autowired
	private MyGroupService myGroupService;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private GroupService groupService;
	
	@RequestMapping("/mygroupList/{student_id}")
	@ResponseBody
    public void mygroupList(@ModelAttribute MyGroupEntity myGroupEntity,
    									 @PathVariable String student_id, 
    									 HttpServletRequest request, 
    									 HttpServletResponse response,
    									 Model model) {
		String callback = request.getParameter("callback");
		if (callback == null) {
			callback = "callback";
		}
		
		List<MyGroupEntity> list = myGroupService.list(myGroupEntity);
		JSONObject obj = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		
		for (MyGroupEntity myGroup : list) {
			JSONObject groupJson = new JSONObject();
			groupJson.put("idx", myGroup.getIdx());
			groupJson.put("student_id", myGroup.getStudent_id());
			groupJson.put("group_id", myGroup.getGroup_id());
			groupJson.put("mystudent_id", myGroup.getMystudent_id());
			groupJson.put("grp_status", myGroup.getGrp_status());
			
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
		
//		model.addAttribute("data", list);
//		return list;
    }
	
	@RequestMapping("/mygroupList/jsonpTest")
    public void jsonpTest(@ModelAttribute MyGroupEntity myGroupEntity,
    									 HttpServletRequest request,
    									 HttpServletResponse response, 
    									 Model model) {
		String callback = request.getParameter("callback");
		if (callback == null) {
			callback = "callback";
		}
		
		List<MyGroupEntity> list = myGroupService.list(myGroupEntity);
		JSONObject obj = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		
		for (MyGroupEntity myGroup : list) {
			JSONObject groupJson = new JSONObject();
			groupJson.put("idx", myGroup.getIdx());
			groupJson.put("student_id", myGroup.getStudent_id());
			groupJson.put("group_id", myGroup.getGroup_id());
			groupJson.put("mystudent_id", myGroup.getMystudent_id());
			groupJson.put("grp_status", myGroup.getGrp_status());
			
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
	
	@RequestMapping("/mygroupList")
    public ModelAndView mygroupListWeb(HttpServletRequest request, 
    									 Model model) {
		ModelAndView mav = new ModelAndView();
		List<MyGroupEntity> list = myGroupService.listByMyStudentId(request.getParameter("student_id"));
		
		int totalCnt = list.size();
		for (MyGroupEntity entity : list) {
			entity.setNo(totalCnt--);
		}
		
		mav.addObject("student_id", request.getParameter("student_id"));
		mav.addObject("mystudent_list", list);
		
		return mav;
    }
	
	@RequestMapping("/mygroupAdd.html")
    public String mygroupAdd(Model model,
    		 			   HttpServletRequest request) {
		StudentEntity studentEntity = new StudentEntity();
		List<StudentEntity> studentList = studentService.list(studentEntity);
		Map<String, StudentEntity> studentMap = new HashMap<String, StudentEntity>();
		for (StudentEntity student : studentList) {
			studentMap.put(student.getStudent_id(), student);
		}
		
		MyGroupEntity myGroupEntity = new MyGroupEntity();
		myGroupEntity.setStudent_id(request.getParameter("student_id"));
		List<MyGroupEntity> list = myGroupService.list(myGroupEntity);
		List<MyGroupEntity> requestList = myGroupService.listByMyStudentId(myGroupEntity.getStudent_id());
		Map<String, MyGroupEntity> mygroupMap = new HashMap<String, MyGroupEntity>();
		for (MyGroupEntity mygroup : requestList) {
			mygroupMap.put(mygroup.getStudent_id(), mygroup);
		}
		
		List<GroupEntity> groupList = groupService.listByStudent(myGroupEntity.getStudent_id());
		/*
		 * 본인은 student_list에서 제거 
		 */
		studentMap.remove(myGroupEntity.getStudent_id());
		
		for (MyGroupEntity mygroup : list) {
			if (studentMap.containsKey(mygroup.getMystudent_id())) {
				studentMap.remove(mygroup.getMystudent_id());
			}
		}
		
		Iterator<String> studentIt = studentMap.keySet().iterator();
		while (studentIt.hasNext()) {
			String student_id = studentIt.next();
			MyGroupEntity newMyGroup = null;
			if (mygroupMap.containsKey(student_id)) {
				newMyGroup = mygroupMap.get(student_id);
				if (newMyGroup.getGrp_status() == Constants.MYSTUDENT_GRP_STATUS_ACCEPT) {
					newMyGroup.setGrp_status(Constants.MYSTUDENT_GRP_STATUS_GROUP);
				}
			} else {
				StudentEntity student = studentMap.get(student_id);
				newMyGroup = new MyGroupEntity();
				newMyGroup.setStudent_id(request.getParameter("student_id"));
				newMyGroup.setMystudent_id(student.getStudent_id());
				newMyGroup.setGrp_status(Constants.MYSTUDENT_GRP_STATUS_NO_REQ);
			}
			
			list.add(newMyGroup);
		}
		
		int totalCnt = list.size();
		for (MyGroupEntity entity : list) {
			entity.setNo(totalCnt--);
		}
		
		model.addAttribute("student_id", myGroupEntity.getStudent_id());
		model.addAttribute("student_list", list);
		model.addAttribute("group_list", groupList);
		
        return "mygroupAdd";
    }
	
	@RequestMapping("/mygroupAddConf.html")
    public ModelAndView mygroupAddConf(@ModelAttribute MyGroupEntity myGroupEntity, 
								   	  BindingResult bindingResult,
								      SessionStatus sessionStatus,
								      Model model) {
		ModelAndView mav = new ModelAndView();
		myGroupEntity.setGrp_status(Constants.MYSTUDENT_GRP_STATUS_REQUEST);
		try {
			if (bindingResult.hasErrors()) {
				mav.setViewName("mygroupAdd");
			} else {
				model.addAttribute("entity", myGroupEntity);
				mav.setViewName("mygroupAddConf");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
        return mav;
    }
	
	@RequestMapping(value="/mygroupAddProc.html", method=RequestMethod.POST)
    public ModelAndView mygroupAddProc(@ModelAttribute MyGroupEntity myGroupEntity, 
    								   BindingResult bindingResult,
    								   SessionStatus sessionStatus, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		
		try {
	        /*
	         * Database insert
	         */
			boolean result = myGroupService.add(myGroupEntity);
			
			if (result) {
		        sessionStatus.setComplete();
		        
		        mav.addObject("resultCode", ResultCode.MYGROUP_REQ_SUCCESS_CODE);
		        mav.addObject("resultMsg", ResultCode.MYGROUP_REQ_SUCCESS_MSG);
			} else {
				mav.addObject("resultCode", ResultCode.MYGROUP_ERR_REQ_CODE);
				mav.addObject("resultMsg", ResultCode.MYGROUP_ERR_REQ_MSG);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		mav.addObject("student_id", myGroupEntity.getStudent_id());
		mav.setViewName("redirect:/mygroupList.html");
		
        return mav;
    }
	
	@RequestMapping(value="/mygroupModifyProc.html", method=RequestMethod.POST)
    public ModelAndView mygroupModifyProc(@ModelAttribute MyGroupEntity myGroupEntity, 
    								   BindingResult bindingResult,
    								   SessionStatus sessionStatus, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		String cmd = Util.CHK_NULL(request.getParameter("cmd"));
		try {
	        /*
	         * Database update
	         */
			boolean result = false;
			if (cmd.equals(Constants.MYSTUDENT_CMD_STATUS_MODIFY)) {
				//result = myGroupService.modify(myGroupEntity);
			} else if (cmd.equals(Constants.MYSTUDENT_CMD_GROUP_MODIFY)) {
				//result = myGroupService.modifyGroup(myGroupEntity);
			}
			
			if (result) {
		        sessionStatus.setComplete();
		        
		        mav.addObject("resultCode", ResultCode.MYGROUP_SUCCESS_CODE);
		        mav.addObject("resultMsg", ResultCode.MYGROUP_SUCCESS_MSG);
			} else {
				mav.addObject("resultCode", ResultCode.MYGROUP_ERR_CODE);
				mav.addObject("resultMsg", ResultCode.MYGROUP_ERR_MSG);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		mav.addObject("student_id", myGroupEntity.getStudent_id());
		if (cmd.equals(Constants.MYSTUDENT_CMD_GROUP_MODIFY)) {
			mav.setViewName("redirect:/mygroupAdd.html");
		} else {
			mav.setViewName("redirect:/mygroupList.html");
		}
		
        return mav;
    }
	
}
