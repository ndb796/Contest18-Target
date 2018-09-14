package com.mycompany.myschool.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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
import com.mycompany.myschool.web.entity.sns.EmotionEntity;
import com.mycompany.myschool.web.entity.sns.MyGroupEntity;
import com.mycompany.myschool.web.service.sns.BoardService;
import com.mycompany.myschool.web.service.sns.CommentService;
import com.mycompany.myschool.web.service.sns.EmotionService;
import com.mycompany.myschool.web.service.sns.MyGroupService;
import com.mycompany.myschool.web.util.Constants;
import com.mycompany.myschool.web.util.IDGenerator;
import com.mycompany.myschool.web.util.ResultCode;

import org.json.JSONArray;
import org.json.JSONObject;

@Controller
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private EmotionService emotionService;
	
	@Autowired
	private MyGroupService myGroupService;
	
	@RequestMapping("/gridList.html")
    public ModelAndView adapterList(HttpServletRequest request,
    								HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("gridList");		
		
        return mav;
    }
	
	@RequestMapping("/boardList/{student_id}/{group_id}")
	@ResponseBody
    public void boardList(@ModelAttribute BoardEntity boardEntity,
    									 @PathVariable String student_id,
    									 @PathVariable String group_id, 
    									 HttpServletRequest request, 
    									 HttpServletResponse response,
    									 Model model) {
		String callback = request.getParameter("callback");
		if (callback == null) {
			callback = "callback";
		}
		
		List<BoardEntity> list = null;
		/*
		 * student_id의 board
		 */
		if (group_id != null) {
			boardEntity.setStudent_id(student_id);
			boardEntity.setGroup_id(group_id);
			list = boardService.listByGroup(boardEntity);
		} else {
			list = boardService.listByStudent(student_id);
		}
		
		/*
		 * mystudent의 board
		 */
		List<MyGroupEntity> mygroupList = myGroupService.listByMyStudentId(student_id);
		for (MyGroupEntity mygroupEntity : mygroupList) {
			boardEntity.setStudent_id(mygroupEntity.getStudent_id());
			boardEntity.setGroup_id(mygroupEntity.getGroup_id());
			List<BoardEntity> boardOfMystudentList = boardService.listByGroup(boardEntity);
			
			list.addAll(boardOfMystudentList);
		}
		
		/*
		 * sort list
		 */
		Collections.sort(list);
		
		JSONObject obj = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		
		for (BoardEntity board : list) {
			List<CommentEntity> commentList = commentService.listByBoard(board.getBoard_id());
			JSONArray cmtJsonArray = new JSONArray();
			
			for (CommentEntity comment : commentList) {
				JSONObject commentJson = new JSONObject();
				commentJson.put("idx", comment.getIdx());
				commentJson.put("comments", comment.getComments());
				commentJson.put("ins_date", comment.getIns_date());
				commentJson.put("upd_date", comment.getUpd_date());
				
				cmtJsonArray.put(commentJson);
			}
			
			EmotionEntity emotionEntity = emotionService.getByBoard(board.getBoard_id());
			JSONObject emotionJson = new JSONObject();
			emotionJson.put("idx", emotionEntity.getIdx());
			emotionJson.put("emotion_h", emotionEntity.getEmotion_h());
			emotionJson.put("emotion_f", emotionEntity.getEmotion_f());
			emotionJson.put("emotion_k", emotionEntity.getEmotion_k());
			emotionJson.put("emotion_n", emotionEntity.getEmotion_n());
			emotionJson.put("emotion_s", emotionEntity.getEmotion_s());
			
			JSONObject boardJson = new JSONObject();
			boardJson.put("idx", board.getIdx());
			boardJson.put("board_id", board.getBoard_id());
			boardJson.put("board_type", board.getBoard_type());
			boardJson.put("group_id", board.getGroup_id());
			boardJson.put("student_id", board.getStudent_id());
			boardJson.put("contents", board.getContents());
			boardJson.put("emotion", emotionJson);
			boardJson.put("comments", cmtJsonArray);
			
			jsonArray.put(boardJson);
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
	
	@RequestMapping("/boardList/jsonpTest")
    public void jsonpTest(@ModelAttribute BoardEntity boardEntity,
    									 HttpServletRequest request,
    									 HttpServletResponse response, 
    									 Model model) {
		String callback = request.getParameter("callback");
		if (callback == null) {
			callback = "callback";
		}
		
		List<BoardEntity> list = boardService.list(boardEntity);
		JSONObject obj = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		
		for (BoardEntity board : list) {
			List<CommentEntity> commentList = commentService.listByBoard(board.getBoard_id());
			JSONArray cmtJsonArray = new JSONArray();
			
			for (CommentEntity comment : commentList) {
				JSONObject commentJson = new JSONObject();
				commentJson.put("idx", comment.getIdx());
				commentJson.put("comments", comment.getComments());
				commentJson.put("ins_date", comment.getIns_date());
				commentJson.put("upd_date", comment.getUpd_date());
				
				cmtJsonArray.put(commentJson);
			}
			
			EmotionEntity emotionEntity = emotionService.getByBoard(board.getBoard_id());
			JSONObject emotionJson = new JSONObject();
			emotionJson.put("idx", emotionEntity.getIdx());
			emotionJson.put("emotion_h", emotionEntity.getEmotion_h());
			emotionJson.put("emotion_f", emotionEntity.getEmotion_f());
			emotionJson.put("emotion_k", emotionEntity.getEmotion_k());
			emotionJson.put("emotion_n", emotionEntity.getEmotion_n());
			emotionJson.put("emotion_s", emotionEntity.getEmotion_s());
			
			JSONObject boardJson = new JSONObject();
			boardJson.put("idx", board.getIdx());
			boardJson.put("board_id", board.getBoard_id());
			boardJson.put("board_type", board.getBoard_type());
			boardJson.put("group_id", board.getGroup_id());
			boardJson.put("student_id", board.getStudent_id());
			boardJson.put("contents", board.getContents());
			boardJson.put("emotion", emotionJson);
			boardJson.put("comments", cmtJsonArray);
			
			jsonArray.put(boardJson);
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
	
	@RequestMapping("/boardList")
    public ModelAndView boardListWeb(@ModelAttribute BoardEntity boardEntity,
    									 HttpServletRequest request, 
    									 Model model) {
		ModelAndView mav = new ModelAndView();
		
		boardEntity.setStudent_id(request.getParameter("student_id"));
		boardEntity.setGroup_id(request.getParameter("group_id"));
		
		List<BoardEntity> list = null;
		/*
		 * student_id의 board
		 */
		if (request.getParameter("group_id") != null) {
			boardEntity.setStudent_id(request.getParameter("student_id"));
			boardEntity.setGroup_id(request.getParameter("group_id"));
			list = boardService.listByGroup(boardEntity);
		} else {
			list = boardService.listByStudent(request.getParameter("student_id"));
		}

		/*
		 * mystudent의 board
		 */
		List<MyGroupEntity> mygroupList = myGroupService.listByMyStudentId(request.getParameter("student_id"));
		
		for (MyGroupEntity mygroupEntity : mygroupList) {
			boardEntity.setStudent_id(mygroupEntity.getStudent_id());
			boardEntity.setGroup_id(mygroupEntity.getGroup_id());
			List<BoardEntity> boardOfMystudentList = boardService.listByGroup(boardEntity);
			
			list.addAll(boardOfMystudentList);
		}
		
		/*
		 * sort list
		 */
		Collections.sort(list);
		
		int totalCnt = list.size();
		for (BoardEntity entity : list) {
			entity.setNo(totalCnt--);
		}
		
		/*
		 * comments, emotion will be added 
		 */
		for (BoardEntity board : list) {
			EmotionEntity emotionEntity = emotionService.getByBoard(board.getBoard_id());
			board.setEmotionEntity(emotionEntity);
			
			List<CommentEntity> commentList = commentService.listByBoard(board.getBoard_id());
			board.setCommentList(commentList);
		}
		
		mav.addObject("group_id", request.getParameter("group_id"));
		mav.addObject("student_id", request.getParameter("student_id"));
		mav.addObject("data", list);
		
		return mav;
    }
	
	@RequestMapping("/boardAdd.html")
    public String boardAdd(Model model,
    		 			   HttpServletRequest request) {
		BoardEntity boardEntity = new BoardEntity();
		boardEntity.setStudent_id(request.getParameter("student_id"));
		boardEntity.setGroup_id(request.getParameter("group_id"));
		model.addAttribute("boardEntity", boardEntity);
		model.addAttribute("student_id", boardEntity.getStudent_id());
		model.addAttribute("group_id", boardEntity.getGroup_id());
		
        return "boardAdd";
    }
	
	@RequestMapping("/boardAddConf.html")
    public ModelAndView boardAddConf(@Valid @ModelAttribute BoardEntity boardEntity, 
								   	  BindingResult bindingResult,
								      SessionStatus sessionStatus,
								      Model model) {
		ModelAndView mav = new ModelAndView();
		
		try {
			if (bindingResult.hasErrors()) {
				mav.setViewName("boardAdd");
			} else {
				model.addAttribute("board_entity", boardEntity);
				mav.setViewName("boardAddConf");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
        return mav;
    }
	
	@RequestMapping(value="/boardAddProc.html", method=RequestMethod.POST)
    public ModelAndView boardAddProc(@ModelAttribute BoardEntity boardEntity, 
    								   BindingResult bindingResult,
    								   SessionStatus sessionStatus, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		
		try {
	        /*
	         * Database insert
	         */
			String currentId = boardService.getMaxId();
			boardEntity.setBoard_id(IDGenerator.NEXT_ID(Constants.SRVC_TYPE_BRD, currentId));
			boolean result = boardService.add(boardEntity);
			
			if (result) {
				EmotionEntity emotionEntity = new EmotionEntity();
				emotionEntity.setBoard_id(boardEntity.getBoard_id());
				emotionService.add(emotionEntity);
				
		        sessionStatus.setComplete();
		        
		        mav.addObject("resultCode", ResultCode.BOARD_C_SUCCESS_CODE);
		        mav.addObject("resultMsg", ResultCode.BOARD_C_SUCCESS_MSG);
			} else {
				mav.addObject("resultCode", ResultCode.BOARD_ERR_CREATE_CODE);
				mav.addObject("resultMsg", ResultCode.BOARD_ERR_CREATE_MSG);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		mav.addObject("student_id", boardEntity.getStudent_id());
		mav.addObject("group_id", boardEntity.getGroup_id());
		mav.setViewName("boardAddOk");
		
        return mav;
    }
	
}
