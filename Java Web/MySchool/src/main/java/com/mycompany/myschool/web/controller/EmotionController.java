package com.mycompany.myschool.web.controller;

import java.io.IOException;
import java.io.PrintWriter;

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

import com.mycompany.myschool.web.entity.sns.BoardEntity;
import com.mycompany.myschool.web.entity.sns.EmotionEntity;
import com.mycompany.myschool.web.service.sns.EmotionService;

@Controller
public class EmotionController {
	@Autowired
	private EmotionService emotionService;
	
	@RequestMapping("/emotionList/{board_id}")
	@ResponseBody
    public void emotionList(@ModelAttribute BoardEntity boardEntity,
    									 @PathVariable String board_id,
    									 HttpServletRequest request, 
    									 HttpServletResponse response,
    									 Model model) {
		String callback = request.getParameter("callback");
		if (callback == null) {
			callback = "callback";
		}
		
		EmotionEntity emotionEntity = emotionService.getByBoard(board_id);
		
		JSONObject obj = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		
		JSONObject emotionJson = new JSONObject();
		emotionJson.put("idx", emotionEntity.getIdx());
		emotionJson.put("board_id", emotionEntity.getBoard_id());
		emotionJson.put("emotion_h", emotionEntity.getEmotion_h());
		emotionJson.put("emotion_f", emotionEntity.getEmotion_f());
		emotionJson.put("emotion_k", emotionEntity.getEmotion_k());
		emotionJson.put("emotion_n", emotionEntity.getEmotion_n());
		emotionJson.put("emotion_s", emotionEntity.getEmotion_s());
			
		jsonArray.put(emotionJson);
		
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
	
	@RequestMapping("/emotionList/jsonpTest")
    public void jsonpTest(@ModelAttribute BoardEntity boardEntity,
    									 HttpServletRequest request,
    									 HttpServletResponse response, 
    									 Model model) {
		String callback = request.getParameter("callback");
		if (callback == null) {
			callback = "callback";
		}
		
		EmotionEntity emotionEntity = emotionService.getByBoard(request.getParameter("board_id"));
		
		JSONObject obj = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		
		JSONObject emotionJson = new JSONObject();
		emotionJson.put("idx", emotionEntity.getIdx());
		emotionJson.put("board_id", emotionEntity.getBoard_id());
		emotionJson.put("emotion_h", emotionEntity.getEmotion_h());
		emotionJson.put("emotion_f", emotionEntity.getEmotion_f());
		emotionJson.put("emotion_k", emotionEntity.getEmotion_k());
		emotionJson.put("emotion_n", emotionEntity.getEmotion_n());
		emotionJson.put("emotion_s", emotionEntity.getEmotion_s());
		
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
	
	@RequestMapping("/emotionList")
    public ModelAndView emotionListWeb(HttpServletRequest request, 
    									 Model model) {
		ModelAndView mav = new ModelAndView();
		
		EmotionEntity emotionEntity = emotionService.getByBoard(request.getParameter("board_id"));

		mav.addObject("board_id", request.getParameter("board_id"));
		mav.addObject("student_id", request.getParameter("student_id"));
		mav.addObject("data", emotionEntity);
		
		return mav;
    }
	
	@RequestMapping(value="/emotionModifyProc.html", method=RequestMethod.POST)
    public ModelAndView emotionModifyProc(@ModelAttribute EmotionEntity emotionEntity, 
    								   BindingResult bindingResult,
    								   SessionStatus sessionStatus, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();

		try {
	        /*
	         * Database insert
	         */
			EmotionEntity dbEmotionEntity = emotionService.getByBoard(emotionEntity.getBoard_id());
			if (request.getParameter("emotion") != null) {
				if (request.getParameter("emotion").equals("h")) {
					emotionEntity.setEmotion_h(dbEmotionEntity.getEmotion_h() + 1);
					emotionEntity.setEmotion_f(dbEmotionEntity.getEmotion_f());
					emotionEntity.setEmotion_k(dbEmotionEntity.getEmotion_k());
					emotionEntity.setEmotion_n(dbEmotionEntity.getEmotion_n());
					emotionEntity.setEmotion_s(dbEmotionEntity.getEmotion_s());
				} else if (request.getParameter("emotion").equals("f")) {
					emotionEntity.setEmotion_h(dbEmotionEntity.getEmotion_h());
					emotionEntity.setEmotion_f(dbEmotionEntity.getEmotion_f() + 1);		
					emotionEntity.setEmotion_k(dbEmotionEntity.getEmotion_k());
					emotionEntity.setEmotion_n(dbEmotionEntity.getEmotion_n());
					emotionEntity.setEmotion_s(dbEmotionEntity.getEmotion_s());
				} else if (request.getParameter("emotion").equals("k")) {
					emotionEntity.setEmotion_h(dbEmotionEntity.getEmotion_h());
					emotionEntity.setEmotion_f(dbEmotionEntity.getEmotion_f());
					emotionEntity.setEmotion_k(dbEmotionEntity.getEmotion_k() + 1);
					emotionEntity.setEmotion_n(dbEmotionEntity.getEmotion_n());
					emotionEntity.setEmotion_s(dbEmotionEntity.getEmotion_s());
				} else if (request.getParameter("emotion").equals("n")) {
					emotionEntity.setEmotion_h(dbEmotionEntity.getEmotion_h());
					emotionEntity.setEmotion_f(dbEmotionEntity.getEmotion_f());
					emotionEntity.setEmotion_k(dbEmotionEntity.getEmotion_k());
					emotionEntity.setEmotion_n(dbEmotionEntity.getEmotion_n() + 1);
					emotionEntity.setEmotion_s(dbEmotionEntity.getEmotion_s());
				} else if (request.getParameter("emotion").equals("s")) {
					emotionEntity.setEmotion_h(dbEmotionEntity.getEmotion_h());
					emotionEntity.setEmotion_f(dbEmotionEntity.getEmotion_f());
					emotionEntity.setEmotion_k(dbEmotionEntity.getEmotion_k());
					emotionEntity.setEmotion_n(dbEmotionEntity.getEmotion_n());
					emotionEntity.setEmotion_s(dbEmotionEntity.getEmotion_s() + 1);
				}
			}
			
			boolean result = emotionService.modify(emotionEntity);
			
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
		
		mav.addObject("student_id", request.getParameter("student_id"));
		mav.addObject("group_id", request.getParameter("group_id"));
//		mav.addObject("board_id", emotionEntity.getBoard_id());
		
//		mav.setViewName("boardList");
		mav.setViewName("redirect:/boardList.html");
		
        return mav;
    }
	
}
