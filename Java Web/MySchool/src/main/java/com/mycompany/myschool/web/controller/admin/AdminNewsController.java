package com.mycompany.myschool.web.controller.admin;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mycompany.myschool.web.entity.base.UserEntity;
import com.mycompany.myschool.web.entity.board.NewsEntity;
import com.mycompany.myschool.web.service.board.NewsService;
import com.mycompany.myschool.web.util.Constants;
import com.mycompany.myschool.web.util.ResultCode;
import com.mycompany.myschool.web.util.Util;

@Controller
public class AdminNewsController {
	@Autowired
	private NewsService newsService;
	
	/*
	 * News
	 */
	@RequestMapping("/ds_admin/news.do")
    public ModelAndView news(Model model, 
    							  HttpServletRequest request,
    							  HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		UserEntity admin_user = (UserEntity)session.getAttribute("admin_user");
		if (admin_user != null) {
			mav.addObject("menu", request.getParameter("menu"));
			
			NewsEntity newsEntity = new NewsEntity();
			// newsEntity.setShow_flg(Constants.BLOG_NOTSHOW);
			
			List<NewsEntity> newsList = newsService.list(newsEntity);
			
			mav.addObject("news_list", newsList);
			mav.addObject("locale", Util.GET_LOCALE(request.getHeader("Accept-Language")));
			mav.addObject("context_name", Util.LOAD_CONFIG("context_name"));
			
			mav.setViewName("/ds_admin/news");
		} else {
			mav.setViewName("redirect:/ds_admin/login.do?resultCode=" + ResultCode.USER_ERR_LOGIN_CODE);
		}
		
        return mav;
	}
	
	@RequestMapping("/ds_admin/news/{idx}")
    public void newsView(@PathVariable("idx") Integer idx,
    							Model model,
    		 			   		HttpServletRequest request,
    		 			   		HttpServletResponse response) {
		String callback = request.getParameter("jsoncallback");
		
		NewsEntity newsVo = newsService.get(idx);
		
		JSONObject newsJson = new JSONObject();
		newsJson.put("idx", newsVo.getIdx());
		newsJson.put("news_type", newsVo.getNews_type());
		newsJson.put("news_title", newsVo.getNews_title());
		newsJson.put("news_contents", newsVo.getNews_contents());
		newsJson.put("writer", newsVo.getUpd_id());
		newsJson.put("upd_date", newsVo.getUpd_date_str());
		newsJson.put("del_flg", newsVo.getDel_flg());
		
		try {
			PrintWriter out = response.getWriter();
			out.write(callback + "(" + newsJson.toString() + ")");
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	@RequestMapping("/ds_admin/news/newsModProc")
    public ModelAndView newsModProc(HttpServletRequest request, 
    									 Model model,
    									 HttpSession session,
    									 HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		
		UserEntity admin_user = (UserEntity)session.getAttribute("admin_user");
		if (admin_user != null) {
			String idx = request.getParameter("idx");
			String news_type = request.getParameter("news_type");
			String news_title = request.getParameter("news_title");
			String news_contents = request.getParameter("news_contents");
			String del_flg = request.getParameter("del_flg");
			
			NewsEntity newsEntity = new NewsEntity();
			newsEntity.setSchool_id("ADMIN");
			newsEntity.setIdx(Integer.parseInt(idx));
			newsEntity.setNews_type((news_type.equals("1") ? Constants.NEWS_TYPE_NEWS : Constants.NEWS_TYPE_EVNTS));
			newsEntity.setNews_title((news_title));
			newsEntity.setNews_contents((news_contents));
			newsEntity.setDel_flg((del_flg != null && del_flg.equals("1") ? 1 : 0));
			newsEntity.setDel_id(admin_user.getUser_id());
			
			newsEntity.setUpd_id("admin");
			newsService.modify(newsEntity);
			
			/*
			 * retrieve list
			 */
			List<NewsEntity> newsList = newsService.list(new NewsEntity());
			
			mav.addObject("news_list", newsList);
			mav.addObject("locale", Util.GET_LOCALE(request.getHeader("Accept-Language")));
			mav.addObject("context_name", Util.LOAD_CONFIG("context_name"));
			
			mav.setViewName("/ds_admin/news");
		} else {
			mav.setViewName("redirect:/ds_admin/login.do?resultCode=" + ResultCode.USER_ERR_LOGIN_CODE);
		}
		
		return mav;
	}
	
	@RequestMapping("/ds_admin/news/newsAddProc")
    public ModelAndView newsAddProc(HttpServletRequest request, 
    									 Model model,
    									 HttpSession session,
    									 HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		
		UserEntity admin_user = (UserEntity)session.getAttribute("admin_user");
		if (admin_user != null) {
			String news_type = Util.CHK_NULL(request.getParameter("news_type"));
			String news_title = Util.CHK_NULL(request.getParameter("news_title"));
			String news_contents = Util.CHK_NULL(request.getParameter("news_contents"));
			String show_flg = Util.CHK_NULL(request.getParameter("del_flg"));
			if (show_flg == null) {
				show_flg = "1";
			}
			
			NewsEntity newsEntity = new NewsEntity();
			newsEntity.setSchool_id("ADMIN");
			newsEntity.setNews_type((news_type.equals("1") ? Constants.NEWS_TYPE_NEWS : Constants.NEWS_TYPE_EVNTS));
			newsEntity.setNews_title((news_title));
			newsEntity.setNews_contents((news_contents));
			newsEntity.setDel_flg(show_flg.equals("1") ? 1 : 0);
			newsEntity.setUpd_id(admin_user.getUser_id());
			
			newsEntity.setUpd_id("admin");
			newsService.add(newsEntity);
			/*
			 * retrieve list
			 */
			List<NewsEntity> newsList = newsService.list(new NewsEntity());
			
			mav.addObject("news_list", newsList);
			mav.addObject("locale", Util.GET_LOCALE(request.getHeader("Accept-Language")));
			mav.addObject("context_name", Util.LOAD_CONFIG("context_name"));
			
			mav.setViewName("/ds_admin/news");
		} else {
			mav.setViewName("redirect:/ds_admin/login.do?resultCode=" + ResultCode.USER_ERR_LOGIN_CODE);
		}
		
		return mav;
	}
	
	@RequestMapping("/ds_admin/news/newsDelProc")
    public ModelAndView newsDelProc(HttpServletRequest request, 
    									 Model model,
    									 HttpSession session,
    									 HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();

		Map<String, Object> maps = new HashMap<String, Object>();
		maps.put("idx", Util.CHK_NULL(request.getParameter("idx")));
		maps.put("del_id", "admin");
		
		newsService.remove(maps);
		
		/*
		 * retrieve list
		 */
		List<NewsEntity> newsList = newsService.list(new NewsEntity());
		
		mav.addObject("news_list", newsList);
		mav.addObject("locale", Util.GET_LOCALE(request.getHeader("Accept-Language")));
		mav.addObject("context_name", Util.LOAD_CONFIG("context_name"));
		
		mav.setViewName("/ds_admin/news");
		
		return mav;
	}
}

