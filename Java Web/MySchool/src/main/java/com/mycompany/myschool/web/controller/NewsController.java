package com.mycompany.myschool.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mycompany.myschool.web.entity.base.UserEntity;
import com.mycompany.myschool.web.entity.board.NewsEntity;
import com.mycompany.myschool.web.entity.customer.CustomerEntity;
import com.mycompany.myschool.web.entity.student.StudentEntity;
import com.mycompany.myschool.web.service.board.NewsService;
import com.mycompany.myschool.web.util.Constants;
import com.mycompany.myschool.web.util.ResultCode;
import com.mycompany.myschool.web.util.Token;
import com.mycompany.myschool.web.util.Util;

@Controller
public class NewsController {
	@Autowired
	private NewsService newsService;
	
	/*
	 * Student part
	 */
	@RequestMapping("/user/news/newsList/jsonpTest")
    public void jsonpTest(@ModelAttribute NewsEntity newsEntity,
    									 HttpServletRequest request,
    									 HttpServletResponse response, 
    									 Model model) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		
		String callback = request.getParameter("callback");
		
		newsEntity.setNews_type(Constants.NEWS_TYPE_NEWS);
		List<NewsEntity> list = newsService.list(newsEntity);
		JSONObject obj = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		
		for (NewsEntity news : list) {
			JSONObject newsJson = new JSONObject();
			newsJson.put("idx", news.getIdx());
			newsJson.put("news_type", news.getNews_type());
			newsJson.put("news_title", news.getNews_title());
			newsJson.put("news_hit", news.getNews_hit());
			newsJson.put("upd_date", news.getUpd_date().toString());
			
			jsonArray.put(newsJson);
		}
		
		obj.put("items", jsonArray);
		
		try {
			PrintWriter out = response.getWriter();
			
			out.write(callback + "(" + jsonArray.toString() + ")");
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	@RequestMapping("/user/news/newsList")
    public ModelAndView newsListWeb(@ModelAttribute NewsEntity newsEntity,
    									 HttpServletRequest request, 
    									 Model model,
 					 					 HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		UserEntity userEntity = (UserEntity)session.getAttribute("login_user");
		
		if (userEntity != null) {		// login 페이로부터 이동 
			newsEntity.setSchool_id(userEntity.getUser_id());
			newsEntity.setNews_type(Constants.NEWS_TYPE_NEWS);
			List<NewsEntity> list = getNewsList(newsEntity);

			for (NewsEntity news : list) {
				String contents = news.getNews_contents().toLowerCase();
				StringBuffer sb = new StringBuffer();
				String[] tokens = contents.split("href");
				
				for (String token : tokens) {
					if (token.indexOf("<a") != -1) {
						int pos = news.getNews_contents().toLowerCase().indexOf(">");
						token = token.substring(0, pos) + " target=\"_blank\" >"; 
						sb.append(token + "href");
					} else {
						sb.append(token);
					}
				}
			}
			
			StudentEntity studentEntity = new StudentEntity();
			studentEntity.setStudent_id(userEntity.getUser_id());
			CustomerEntity schoolEntity = new CustomerEntity();
			schoolEntity.setSchool_id(userEntity.getUser_id());
			
			model.addAttribute("userEntity", userEntity);
			model.addAttribute("studentEntity", studentEntity);
			model.addAttribute("schoolEntity", schoolEntity);
			
			mav.addObject("news_list", list);
		} else {
			mav.setViewName("redirect:/index.do?resultCode=" + ResultCode.USER_ERR_NOTLOGIN_CODE);
		}
		
		mav.addObject("locale", Util.GET_LOCALE(request.getHeader("Accept-Language")));
		mav.addObject("context_name", Util.LOAD_CONFIG("context_name"));
		
		return mav;
    }
	
	@RequestMapping("/user/news/eventList")
    public ModelAndView eventListWeb(@ModelAttribute NewsEntity newsEntity,
    									 HttpServletRequest request, 
    									 Model model,
 					 					 HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		UserEntity userEntity = (UserEntity)session.getAttribute("login_user");
		
		if (userEntity != null) {		// login 페이로부터 이동 
			newsEntity.setSchool_id(userEntity.getUser_id());
			newsEntity.setNews_type(Constants.NEWS_TYPE_EVNTS);
			List<NewsEntity> list = getNewsList(newsEntity);

			StudentEntity studentEntity = new StudentEntity();
			studentEntity.setStudent_id(userEntity.getUser_id());
			CustomerEntity schoolEntity = new CustomerEntity();
			schoolEntity.setSchool_id(userEntity.getUser_id());
			
			model.addAttribute("userEntity", userEntity);
			model.addAttribute("studentEntity", studentEntity);
			model.addAttribute("schoolEntity", schoolEntity);
			
			mav.addObject("event_list", list);
		} else {
			mav.setViewName("redirect:/index.do?resultCode=" + ResultCode.USER_ERR_NOTLOGIN_CODE);
		}
		
		mav.addObject("locale", Util.GET_LOCALE(request.getHeader("Accept-Language")));
		mav.addObject("context_name", Util.LOAD_CONFIG("context_name"));
		
		return mav;
    }
	
	@RequestMapping("/user/news/newsView")
    public ModelAndView newsView(Model model,
    		 			   		HttpServletRequest request,
    		 			   		@RequestParam("idx") Integer idx,
    		 			   		HttpSession session) {
		ModelAndView mav = new ModelAndView();

		UserEntity userEntity = (UserEntity)session.getAttribute("login_user");
		
		if (userEntity != null) {		// login 페이로부터 이동
			NewsEntity newsEntity = newsService.get(idx);
			
			StudentEntity studentEntity = new StudentEntity();
			studentEntity.setStudent_id(userEntity.getUser_id());
			CustomerEntity schoolEntity = new CustomerEntity();
			schoolEntity.setSchool_id(userEntity.getUser_id());
			
			model.addAttribute("userEntity", userEntity);
			model.addAttribute("studentEntity", studentEntity);
			model.addAttribute("schoolEntity", schoolEntity);
			model.addAttribute("newsEntity", newsEntity);
			
			mav.setViewName("/user/news/newsView");
		} else {
			mav.setViewName("redirect:/index.do?resultCode=" + ResultCode.USER_ERR_NOTLOGIN_CODE);
		}
		
		mav.addObject("locale", Util.GET_LOCALE(request.getHeader("Accept-Language")));
		mav.addObject("context_name", Util.LOAD_CONFIG("context_name"));
        return mav;
    }
	
	/*
	 * Customer part
	 */
	@RequestMapping("/customer/news/newsList/jsonpTest")
    public void customerJsonpTest(@ModelAttribute NewsEntity newsEntity,
    									 HttpServletRequest request,
    									 HttpServletResponse response, 
    									 Model model) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		
		String callback = request.getParameter("callback");
		System.out.println("callback=" + callback + "," + request.getRemoteAddr());
		
		newsEntity.setNews_type(Constants.NEWS_TYPE_NEWS);
		List<NewsEntity> list = newsService.list(newsEntity);
		JSONObject obj = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		
		for (NewsEntity news : list) {
			JSONObject newsJson = new JSONObject();
			newsJson.put("idx", news.getIdx());
			newsJson.put("news_type", news.getNews_type());
			newsJson.put("news_title", news.getNews_title());
			newsJson.put("news_hit", news.getNews_hit());
			newsJson.put("upd_date", news.getUpd_date().toString());
			
			jsonArray.put(newsJson);
		}
		
		obj.put("items", jsonArray);
		
		try {
			PrintWriter out = response.getWriter();
			
			out.write(callback + "(" + jsonArray.toString() + ")");
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	@RequestMapping("/customer/news/newsList")
    public ModelAndView customerNewsListWeb(@ModelAttribute NewsEntity newsEntity,
    									 HttpServletRequest request, 
    									 Model model,
 					 					 HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		UserEntity userEntity = (UserEntity)session.getAttribute("login_user");
		
		if (userEntity != null) {		// login 페이로부터 이동 
			newsEntity.setNews_type(Constants.NEWS_TYPE_NEWS);
			List<NewsEntity> list = getNewsList(newsEntity);
			
			StudentEntity studentEntity = new StudentEntity();
			studentEntity.setStudent_id(userEntity.getUser_id());
			CustomerEntity schoolEntity = new CustomerEntity();
			schoolEntity.setSchool_id(userEntity.getUser_id());
			
			model.addAttribute("userEntity", userEntity);
			model.addAttribute("studentEntity", studentEntity);
			model.addAttribute("schoolEntity", schoolEntity);
			
			mav.addObject("news_list", list);
		} else {
			mav.setViewName("redirect:/index.do?resultCode=" + ResultCode.USER_ERR_NOTLOGIN_CODE);
		}
		
		mav.addObject("locale", Util.GET_LOCALE(request.getHeader("Accept-Language")));
		mav.addObject("context_name", Util.LOAD_CONFIG("context_name"));
		
		return mav;
    }
	
	@RequestMapping("/customer/news/eventList")
    public ModelAndView customerEventListWeb(@ModelAttribute NewsEntity newsEntity,
    									 HttpServletRequest request, 
    									 Model model,
 					 					 HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		UserEntity userEntity = (UserEntity)session.getAttribute("login_user");
		
		if (userEntity != null) {		// login 페이로부터 이동 
			newsEntity.setNews_type(Constants.NEWS_TYPE_EVNTS);
			newsEntity.setSchool_id(userEntity.getUser_id());
			List<NewsEntity> list = getNewsList(newsEntity);

			StudentEntity studentEntity = new StudentEntity();
			studentEntity.setStudent_id(userEntity.getUser_id());
			CustomerEntity schoolEntity = new CustomerEntity();
			schoolEntity.setSchool_id(userEntity.getUser_id());
			
			model.addAttribute("userEntity", userEntity);
			model.addAttribute("studentEntity", studentEntity);
			model.addAttribute("schoolEntity", schoolEntity);
			
			mav.addObject("news_list", list);
		} else {
			mav.setViewName("redirect:/index.do?resultCode=" + ResultCode.USER_ERR_NOTLOGIN_CODE);
		}
		
		mav.addObject("locale", Util.GET_LOCALE(request.getHeader("Accept-Language")));
		mav.addObject("context_name", Util.LOAD_CONFIG("context_name"));
		
		return mav;
    }
	
	@RequestMapping("/customer/news/newsView/{idx}")
    public void customerNewsView(@PathVariable("idx") Integer idx,
    							Model model,
    		 			   		HttpServletRequest request,
    		 			   		HttpServletResponse response) {
		String callback = request.getParameter("jsoncallback");
		
		NewsEntity newsEntity = newsService.get(idx);
		
		JSONObject newsJson = new JSONObject();
		newsJson.put("idx", newsEntity.getIdx());
		newsJson.put("news_type", newsEntity.getNews_type());
		newsJson.put("news_title", newsEntity.getNews_title());
		newsJson.put("news_contents", newsEntity.getNews_contents());
		newsJson.put("news_hit", newsEntity.getNews_hit());
		newsJson.put("upd_date", newsEntity.getUpd_date().toString());
		try {
			PrintWriter out = response.getWriter();
			out.write(callback + "(" + newsJson.toString() + ")");
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	@RequestMapping("/customer/news/newsAddForm")
    public ModelAndView newsAddForm(HttpServletRequest request, 
    					 					HttpServletResponse response, 
    					 					Model model,
    					 					HttpSession session) {
		ModelAndView mav = new ModelAndView();
		UserEntity userEntity = (UserEntity)session.getAttribute("login_user");
		
		if (userEntity != null) {		// login 페이로부터 이동 
			NewsEntity newsEntity = new NewsEntity();
			newsEntity.setSchool_id(userEntity.getUser_id());
			model.addAttribute("newsEntity", newsEntity);
			
			CustomerEntity schoolEntity = new CustomerEntity();
			schoolEntity.setSchool_id(userEntity.getUser_id());
			model.addAttribute("schoolEntity", schoolEntity);
			
			/*
			 * Setting mode
			 */
			mav.addObject("mode", "add");
			SortedMap<String, String> news_type_map = new TreeMap<String, String>();
			news_type_map.put("0", "선택해 주세요");
			news_type_map.put("1", "뉴스");
			news_type_map.put("2", "이벤트");
			mav.addObject("news_type_map", news_type_map);
			
			model.addAttribute("userEntity", userEntity);
			
			mav.setViewName("/customer/news/newsAddForm");
		} else {
			mav.setViewName("redirect:/index.do?resultCode=" + ResultCode.USER_ERR_NOTLOGIN_CODE);
		}
		
		mav.addObject("locale", Util.GET_LOCALE(request.getHeader("Accept-Language")));
		mav.addObject("context_name", Util.LOAD_CONFIG("context_name"));
		
		return mav;
    }
	
	@RequestMapping("/customer/news/eventAddForm")
    public ModelAndView eventAddForm(HttpServletRequest request, 
    					 					HttpServletResponse response, 
    					 					Model model,
    					 					HttpSession session) {
		ModelAndView mav = new ModelAndView();
		UserEntity userEntity = (UserEntity)session.getAttribute("login_user");
		
		if (userEntity != null) {		// login 페이로부터 이동 
			NewsEntity newsEntity = new NewsEntity();
			newsEntity.setSchool_id(userEntity.getUser_id());
			model.addAttribute("newsEntity", newsEntity);
			
			CustomerEntity schoolEntity = new CustomerEntity();
			schoolEntity.setSchool_id(userEntity.getUser_id());
			model.addAttribute("schoolEntity", schoolEntity);
			
			/*
			 * Setting mode
			 */
			mav.addObject("mode", "add");
			SortedMap<String, String> news_type_map = new TreeMap<String, String>();
			news_type_map.put("0", "선택해 주세요");
			news_type_map.put("1", "뉴스");
			news_type_map.put("2", "이벤트");
			mav.addObject("news_type_map", news_type_map);
			
			model.addAttribute("userEntity", userEntity);
			
			mav.setViewName("/customer/news/eventAddForm");
		} else {
			mav.setViewName("redirect:/index.do?resultCode=" + ResultCode.USER_ERR_NOTLOGIN_CODE);
		}
		
		mav.addObject("locale", Util.GET_LOCALE(request.getHeader("Accept-Language")));
		mav.addObject("context_name", Util.LOAD_CONFIG("context_name"));
		
		return mav;
    }
	
	@RequestMapping("/customer/news/newsAddProc")
    public ModelAndView newsAddProc(@ModelAttribute NewsEntity newsEntity, 
									HttpServletRequest request,
									Model model,
									HttpSession session) {
		ModelAndView mav = new ModelAndView();
		String mode = request.getParameter("mode");
		String news_type = request.getParameter("news_type");
		UserEntity userEntity = (UserEntity)session.getAttribute("login_user");
				
		if (userEntity != null) {
			if(Token.isValid(request)) {
			    Token.set(request);
			    request.setAttribute("TOKEN_SAVE_CHECK", "TRUE");
			} else {
				request.setAttribute("TOKEN_SAVE_CHECK", "FALSE");
			}
			
			boolean isError = false;
			
			try {
				if("TRUE".equals(request.getAttribute("TOKEN_SAVE_CHECK"))) {
					if (mode != null && mode.equals("add")) {
						newsService.add(newsEntity);
					} else {
						newsService.modify(newsEntity);
					}
				}
			} catch (Exception e) {
				isError = true;
				e.printStackTrace();
			}
			
			if (isError) {
				mav.addObject("mode", mode);
				
				if (mode != null && mode.equals("add")) {
					mav.addObject("resultCode", ResultCode.SCHOOL_ERR_CREATE_CODE);
				} else {
					mav.addObject("resultCode", ResultCode.SCHOOL_ERR_UPDATE_CODE);
				}
			} else {
				if (Integer.parseInt(news_type) == Constants.NEWS_TYPE_NEWS) {
					newsEntity.setNews_type(Constants.NEWS_TYPE_NEWS);
					mav.setViewName("/customer/news/newsList");
				} else if (Integer.parseInt(news_type) == Constants.NEWS_TYPE_EVNTS) {
					newsEntity.setNews_type(Constants.NEWS_TYPE_EVNTS);
					mav.setViewName("/customer/news/eventList");
				}
				
				newsEntity.setSchool_id(userEntity.getUser_id());
				List<NewsEntity> list = getNewsList(newsEntity);
				StudentEntity studentEntity = new StudentEntity();
				studentEntity.setStudent_id(userEntity.getUser_id());
				CustomerEntity schoolEntity = new CustomerEntity();
				schoolEntity.setSchool_id(userEntity.getUser_id());
				
				model.addAttribute("userEntity", userEntity);
				model.addAttribute("studentEntity", studentEntity);
				model.addAttribute("schoolEntity", schoolEntity);
				
				mav.addObject("news_list", list);
			}

			session.removeAttribute("token");
		} else {
			mav.setViewName("redirect:/index.do");
		}
		
		mav.addObject("locale", Util.GET_LOCALE(request.getHeader("Accept-Language")));
		mav.addObject("context_name", Util.LOAD_CONFIG("context_name"));
		
        return mav;
    }
	
	@RequestMapping("/customer/news/newsModForm")
    public ModelAndView newsModForm(HttpServletRequest request, 
    					 					HttpServletResponse response, 
    					 					Model model,
    					 					HttpSession session,
    					 					@RequestParam("news_id") String news_id) {
		ModelAndView mav = new ModelAndView();
		UserEntity userEntity = (UserEntity)session.getAttribute("login_user");
		
		if (userEntity != null) {		// login 페이로부터 이동 
			CustomerEntity schoolEntity = new CustomerEntity();
			schoolEntity.setSchool_id(userEntity.getUser_id());
			model.addAttribute("schoolEntity", schoolEntity);
			
			/*
			 * Setting mode
			 */
			int idx = 0;
			news_id = Util.CHK_NULL(news_id);
			if (news_id.indexOf(",") != -1) {
				idx = Integer.parseInt(news_id.substring(0, news_id.length() - 1));
			} else {
				idx = Integer.parseInt(news_id);
			}
			
			NewsEntity newsEntity = newsService.get(idx);
			
			model.addAttribute("newsEntity", newsEntity);
			
			mav.addObject("mode", "modify");
			model.addAttribute("userEntity", userEntity);
			
			mav.setViewName("/customer/news/newsAddForm");
		} else {
			mav.setViewName("redirect:/index.do?resultCode=" + ResultCode.USER_ERR_NOTLOGIN_CODE);
		}
		
		mav.addObject("locale", Util.GET_LOCALE(request.getHeader("Accept-Language")));
		mav.addObject("context_name", Util.LOAD_CONFIG("context_name"));
		
		return mav;
    }
	
	@RequestMapping("/customer/news/eventModForm")
    public ModelAndView eventModForm(HttpServletRequest request, 
    					 					HttpServletResponse response, 
    					 					Model model,
    					 					HttpSession session,
    					 					@RequestParam("news_id") String news_id) {
		ModelAndView mav = new ModelAndView();
		UserEntity userEntity = (UserEntity)session.getAttribute("login_user");
		
		if (userEntity != null) {		// login 페이로부터 이동 
			CustomerEntity schoolEntity = new CustomerEntity();
			schoolEntity.setSchool_id(userEntity.getUser_id());
			model.addAttribute("schoolEntity", schoolEntity);
			
			/*
			 * Setting mode
			 */
			int idx = 0;
			news_id = Util.CHK_NULL(news_id);
			
			if (news_id.indexOf(",") != -1) {
				idx = Integer.parseInt(news_id.substring(0, news_id.length() - 1));
			} else {
				idx = Integer.parseInt(news_id);
			}
			
			NewsEntity newsEntity = newsService.get(idx);
			
			model.addAttribute("newsEntity", newsEntity);
			
			mav.addObject("mode", "modify");
			model.addAttribute("userEntity", userEntity);
			
			mav.setViewName("/customer/news/eventAddForm");
		} else {
			mav.setViewName("redirect:/index.do?resultCode=" + ResultCode.USER_ERR_NOTLOGIN_CODE);
		}
		
		mav.addObject("locale", Util.GET_LOCALE(request.getHeader("Accept-Language")));
		mav.addObject("context_name", Util.LOAD_CONFIG("context_name"));
		
		return mav;
    }
	
	@RequestMapping("/customer/news/newsRemove")
    public ModelAndView newsRemove(HttpServletRequest request, 
    									 Model model,
    									 HttpSession session) {
		ModelAndView mav = new ModelAndView();
		String news_type = Util.CHK_NULL(request.getParameter("news_type"));
		UserEntity userEntity = (UserEntity)session.getAttribute("login_user");
		
		String[] idArray = Util.CHK_NULL(request.getParameter("news_id")).split(",");
		
		if (userEntity != null) {
			NewsEntity newsEntity = new NewsEntity();
			newsEntity.setIdArray(idArray);
			
			if (newsService.modifyForRemove(newsEntity)) {
				mav.addObject("resultCode", ResultCode.SCHOOL_D_SUCCESS_CODE);
			} else {
				mav.addObject("resultCode", ResultCode.SCHOOL_ERR_DELETE_CODE);
			}
			
			if (Integer.parseInt(news_type) == Constants.NEWS_TYPE_NEWS) {
				newsEntity.setNews_type(Constants.NEWS_TYPE_NEWS);
				mav.setViewName("/customer/news/newsList");
			} else if (Integer.parseInt(news_type) == Constants.NEWS_TYPE_EVNTS) {
				newsEntity.setNews_type(Constants.NEWS_TYPE_EVNTS);
				mav.setViewName("/customer/news/eventList");
			}
			
			newsEntity.setSchool_id(userEntity.getUser_id());
			List<NewsEntity> list = getNewsList(newsEntity);
			StudentEntity studentEntity = new StudentEntity();
			studentEntity.setStudent_id(userEntity.getUser_id());
			CustomerEntity schoolEntity = new CustomerEntity();
			schoolEntity.setSchool_id(userEntity.getUser_id());
			
			model.addAttribute("userEntity", userEntity);
			model.addAttribute("studentEntity", studentEntity);
			model.addAttribute("schoolEntity", schoolEntity);
			
			mav.addObject("news_list", list);
			
		} else {
			mav.setViewName("redirect:/index.do");
		}
		
		mav.addObject("locale", Util.GET_LOCALE(request.getHeader("Accept-Language")));
		mav.addObject("context_name", Util.LOAD_CONFIG("context_name"));
		
		return mav;
	}
	
	private List<NewsEntity> getNewsList(NewsEntity newsEntity) {
		List<NewsEntity> list = newsService.list(newsEntity);
		int totalCnt = list.size();
		for (NewsEntity entity : list) {
			entity.setNo(totalCnt--);
		}
		
		return list;
    }
}
