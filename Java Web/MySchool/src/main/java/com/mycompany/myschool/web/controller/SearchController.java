package com.mycompany.myschool.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mycompany.myschool.web.entity.common.SearchEntity;
import com.mycompany.myschool.web.entity.customer.CustomerBranchEntity;
import com.mycompany.myschool.web.service.common.SearchService;
import com.mycompany.myschool.web.service.customer.CustomerBranchService;
import com.mycompany.myschool.web.util.Constants;
import com.mycompany.myschool.web.util.Util;

@Controller
public class SearchController {
	@Autowired
	private CustomerBranchService customerBranchService;
	
	@Autowired
	private SearchService searchService;

	@RequestMapping("/common/search")
    public ModelAndView search(Model model, 
    							  HttpServletRequest request,
    							  HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		String srch_school = request.getParameter("srch_school");
		String srch_loc = request.getParameter("srch_loc");
		String srch_founder = request.getParameter("srch_founder");
		String srch_keywords = request.getParameter("srch_keywords");
		
		SearchEntity searchEntity = new SearchEntity();
		if (srch_school != null && srch_school.length() > 0 && !srch_school.equals("0")) {
			mav.addObject("srch_school", srch_school);
			searchEntity.setSb_type(Integer.parseInt(srch_school));
		}
		
		if (srch_loc != null && srch_loc.length() > 0) {
			mav.addObject("srch_loc", srch_loc);
			
			searchEntity.setPrefecture_id(srch_loc);		
		}
		
		if (srch_founder != null && srch_founder.length() > 0) {
			mav.addObject("srch_founder", srch_founder);
			
			if (srch_founder.equals("3")) {
				searchEntity.setSch_founder_prv(srch_founder);
			} else if (srch_founder.equals("1")) {
				searchEntity.setSch_founder_pub(srch_founder);
			} 
		}
		
		if (srch_keywords != null && srch_keywords.length() > 0) {
			String[] keywords = srch_keywords.split(",");
			Map<String, String> map = new HashMap<String, String>();
			for (String keyword : keywords) {
				map.put(keyword, keyword);
			}
			
			searchEntity.setKeywords(srch_keywords);
			searchEntity.setKeywordsArray(keywords);
			mav.addObject("keywords", map);
		}

		List<SearchEntity> resultList = null;
		if (searchEntity.getSb_type() == Constants.STD_RCMMD_CATE_U) {
			resultList = searchService.getForSearchInUniv(searchEntity);
		} else if (searchEntity.getSb_type() == Constants.STD_RCMMD_CATE_H) {
			resultList = searchService.getForSearchHighschool(searchEntity);
		} else if (searchEntity.getSb_type() == Constants.STD_RCMMD_CATE_C) {
			resultList = searchService.getForSearchInVocational(searchEntity);
		} else if (searchEntity.getSb_type() == Constants.STD_RCMMD_CATE_M) {
			resultList = searchService.getForSearchMiddleschool(searchEntity);
		} else {
			/* ETC */
		}
		
		mav.addObject("resultList", resultList);
		
        return mav;
    }
	
	@RequestMapping("/common/schoolPath")
    public ModelAndView myPath(HttpServletRequest request, 
    							   HttpServletResponse response, 
			 					   Model model) {
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("student_id", request.getParameter("student_id"));
		mav.addObject("context_name", Util.LOAD_CONFIG("context_name"));
		
		return mav;
    }
	
	@RequestMapping("/common/schoolPathJson")
    public void myPathJosn(HttpServletRequest request,
    									 HttpServletResponse response, 
    									 HttpSession session,
    									 Model model) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		String callback = request.getParameter("callback");
		JSONObject obj = new JSONObject();
		
		JSONObject preJson = new JSONObject();
		JSONObject meJson = new JSONObject();
		JSONObject nodeJson = new JSONObject();
		JSONObject nextJson = new JSONObject();
		JSONObject meRelation = new JSONObject();
		JSONObject edgesJson = new JSONObject();
		JSONObject parentJson = new JSONObject();
		
		preJson.put("borders", "1");
		preJson.put("length", "1");
		
		meJson.put("borders", "1");
		meJson.put("length", "1");
		meJson.put("region", "Deviewstory");
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("pos", 10);
		List<CustomerBranchEntity> customerBranchList = customerBranchService.getAllForMain(map);
		
		for (CustomerBranchEntity entity : customerBranchList) {
		
			nodeJson.put(entity.getSb_name(), preJson);
			meRelation.put(entity.getSb_name(), "");
			
		}
		
		nextJson.put("borders", "1");
		nextJson.put("length", "2");
		nextJson.put("region", "Deviewstory");
		
		nodeJson.put("Me", meJson);
		edgesJson.put("Me", meRelation);
		
		parentJson.put("nodes", nodeJson);
		parentJson.put("edges", edgesJson);
		
		obj.put("items", parentJson);
		
		try {
			PrintWriter out = response.getWriter();
			
			out.write(callback + "(" + parentJson.toString() + ")");
			
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}

