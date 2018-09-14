package com.mycompany.myschool.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import com.mycompany.myschool.web.entity.sns.BoardEntity;
import com.mycompany.myschool.web.entity.sns.CommentEntity;
import com.mycompany.myschool.web.service.sns.CommentService;

import org.json.JSONArray;
import org.json.JSONObject;

@Controller
public class CommentController {
	@Autowired
	private CommentService commentService;
	
	@RequestMapping("/commentList/{board_id}")
	@ResponseBody
    public void commentList(@ModelAttribute BoardEntity boardEntity,
    									 @PathVariable String board_id,
    									 HttpServletRequest request, 
    									 HttpServletResponse response,
    									 Model model) {
		String callback = request.getParameter("callback");
		if (callback == null) {
			callback = "callback";
		}
		
		List<CommentEntity> list = commentService.listByBoard(board_id);
		
		JSONObject obj = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		
		for (CommentEntity comment : list) {
			JSONObject commentJson = new JSONObject();
			commentJson.put("idx", comment.getIdx());
			commentJson.put("board_id", comment.getBoard_id());
			commentJson.put("student_id", comment.getStudent_id());
			commentJson.put("comments", comment.getComments());
			commentJson.put("ins_date", comment.getIns_date());
			commentJson.put("upd_date", comment.getUpd_date());
			
			jsonArray.put(commentJson);
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
	
	@RequestMapping("/commentList/jsonpTest")
    public void jsonpTest(@ModelAttribute BoardEntity boardEntity,
    									 HttpServletRequest request,
    									 HttpServletResponse response, 
    									 Model model) {
		String callback = request.getParameter("callback");
		if (callback == null) {
			callback = "callback";
		}
		
		List<CommentEntity> list = commentService.listByBoard(request.getParameter("board_id"));
		
		JSONObject obj = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		
		for (CommentEntity comment : list) {
			JSONObject commentJson = new JSONObject();
			commentJson.put("idx", comment.getIdx());
			commentJson.put("board_id", comment.getBoard_id());
			commentJson.put("student_id", comment.getStudent_id());
			commentJson.put("comments", comment.getComments());
			commentJson.put("ins_date", comment.getIns_date());
			commentJson.put("upd_date", comment.getUpd_date());
			
			jsonArray.put(commentJson);
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
	
	@RequestMapping("/commentList")
    public ModelAndView commentListWeb(@ModelAttribute CommentEntity commentEntity,
    									 HttpServletRequest request, 
    									 Model model) {
		ModelAndView mav = new ModelAndView();
		
		commentEntity.setBoard_id(request.getParameter("board_id"));
		List<CommentEntity> list = commentService.listByBoard(request.getParameter("board_id"));

		int totalCnt = list.size();
		for (CommentEntity entity : list) {
			entity.setNo(totalCnt--);
		}
		
		mav.addObject("board_id", request.getParameter("board_id"));
		mav.addObject("student_id", request.getParameter("student_id"));
		mav.addObject("data", list);
		
		return mav;
    }
	
	@RequestMapping(value="/commentAddProc.html", method=RequestMethod.POST)
    public ModelAndView commentAddProc(@ModelAttribute CommentEntity commentEntity, 
    								   BindingResult bindingResult,
    								   SessionStatus sessionStatus, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		try {
	        /*
	         * Database insert
	         */
			boolean result = commentService.add(commentEntity);
			
			if (result) {
		        sessionStatus.setComplete();

		        /*mav.addObject("resultCode", ResultCode.COMMENT_C_SUCCESS_CODE);
		        mav.addObject("resultMsg", ResultCode.COMMENT_C_SUCCESS_MSG);*/
			} else {
				/*mav.addObject("resultCode", ResultCode.COMMENT_ERR_CREATE_CODE);
				mav.addObject("resultMsg", ResultCode.COMMENT_ERR_CREATE_MSG);*/
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		mav.addObject("student_id", commentEntity.getStudent_id());
		mav.addObject("group_id", request.getParameter("group_id"));
		mav.setViewName("redirect:/boardList.html");
		
        return mav;
    }
	
}
