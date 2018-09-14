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
import com.mycompany.myschool.web.entity.sns.BlogEntity;
import com.mycompany.myschool.web.entity.vo.BlogVo;
import com.mycompany.myschool.web.service.sns.BlogService;
import com.mycompany.myschool.web.util.Constants;
import com.mycompany.myschool.web.util.ResultCode;
import com.mycompany.myschool.web.util.Util;

@Controller
public class AdminBlogController {
	@Autowired
	private BlogService blogService;
	
	/*
	 * Blog
	 */
	@RequestMapping("/ds_admin/blog.do")
    public ModelAndView blog(Model model, 
    							  HttpServletRequest request,
    							  HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		UserEntity admin_user = (UserEntity)session.getAttribute("admin_user");
		if (admin_user != null) {
			mav.addObject("menu", request.getParameter("menu"));
			
			BlogEntity blogEntity = new BlogEntity();
			// blogEntity.setShow_flg(Constants.BLOG_NOTSHOW);
			
			List<BlogVo> blogList = blogService.list(blogEntity);
			Util.CHECK_GENDER_IN_BLOG(blogList);
			
			mav.addObject("blog_list", blogList);
			mav.addObject("locale", Util.GET_LOCALE(request.getHeader("Accept-Language")));
			mav.addObject("context_name", Util.LOAD_CONFIG("context_name"));
			
			mav.setViewName("/ds_admin/blog");
		} else {
			mav.setViewName("redirect:/ds_admin/login.do?resultCode=" + ResultCode.USER_ERR_LOGIN_CODE);
		}
		
        return mav;
	}
	
	@RequestMapping("/ds_admin/blog/{idx}")
    public void blogView(@PathVariable("idx") Integer idx,
    							Model model,
    		 			   		HttpServletRequest request,
    		 			   		HttpServletResponse response) {
		String callback = request.getParameter("jsoncallback");
		
		Map<String, Object> maps = new HashMap<String, Object>();
		maps.put("idx", idx);
		
		BlogVo blogVo = blogService.get(maps);
		
		JSONObject newsJson = new JSONObject();
		newsJson.put("idx", blogVo.getIdx());
		newsJson.put("student_id", blogVo.getStudent_id());
		newsJson.put("title", blogVo.getTitle());
		newsJson.put("contents", blogVo.getContents());
		newsJson.put("name", blogVo.getName());
		newsJson.put("show_flg", blogVo.getShow_flg());
		newsJson.put("upd_date", blogVo.getUpd_date_str());
		try {
			PrintWriter out = response.getWriter();
			out.write(callback + "(" + newsJson.toString() + ")");
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	@RequestMapping("/ds_admin/blog/blogModProc")
    public ModelAndView blogModProc(HttpServletRequest request, 
    									 Model model,
    									 HttpSession session,
    									 HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		String idx = Util.CHK_NULL(request.getParameter("idx"));
		String student_id = Util.CHK_NULL(request.getParameter("student_id"));
		String title = Util.CHK_NULL(request.getParameter("title"));
		String contents = Util.CHK_NULL(request.getParameter("contents"));
		String show_flg = Util.CHK_NULL(request.getParameter("show_flg"));
		
		BlogEntity blogEntity = new BlogEntity();
		blogEntity.setIdx(Integer.parseInt(idx));
		blogEntity.setStudent_id(student_id);
		blogEntity.setTitle(title);
		blogEntity.setContents(contents);
		blogEntity.setShow_flg((show_flg.equals("1") ? 1 : 0));
		
		blogEntity.setUpd_id("admin");
		blogService.modify(blogEntity);
		
		/*
		 * retrieve list
		 */
		List<BlogVo> blogList = blogService.list(new BlogEntity());
		Util.CHECK_GENDER_IN_BLOG(blogList);
		
		mav.addObject("blog_list", blogList);
		mav.addObject("locale", Util.GET_LOCALE(request.getHeader("Accept-Language")));
		mav.addObject("context_name", Util.LOAD_CONFIG("context_name"));
		
		mav.setViewName("/ds_admin/blog");
		
		return mav;
	}
	
	@RequestMapping("/ds_admin/blog/blogAddProc")
    public ModelAndView blogAddProc(HttpServletRequest request, 
    									 Model model,
    									 HttpSession session,
    									 HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		
		UserEntity admin_user = (UserEntity)session.getAttribute("admin_user");
		if (admin_user != null) {
			String title = Util.CHK_NULL(request.getParameter("title"));
			String contents = Util.CHK_NULL(request.getParameter("contents"));
			String show_flg = Util.CHK_NULL(request.getParameter("show_flg"));
			if (show_flg == null) {
				show_flg = "0";
			}
			
			BlogEntity blogEntity = new BlogEntity();
			blogEntity.setStudent_id(admin_user.getUser_id());
			blogEntity.setTitle(title);
			blogEntity.setContents((contents));
			blogEntity.setBlog_type(Constants.BLOG_TYPE_COM);
			blogEntity.setShow_flg(show_flg.equals("1") ? 1 : 0);
			blogEntity.setUpd_id(admin_user.getUser_id());
			
			blogService.add(blogEntity);
			/*
			 * retrieve list
			 */
			List<BlogVo> blogList = blogService.list(new BlogEntity());
			Util.CHECK_GENDER_IN_BLOG(blogList);
			
			mav.addObject("blog_list", blogList);
			mav.addObject("locale", Util.GET_LOCALE(request.getHeader("Accept-Language")));
			mav.addObject("context_name", Util.LOAD_CONFIG("context_name"));
			
			mav.setViewName("/ds_admin/news");
		} else {
			mav.setViewName("redirect:/ds_admin/login.do?resultCode=" + ResultCode.USER_ERR_LOGIN_CODE);
		}
		
		return mav;
	}
	
	@RequestMapping("/ds_admin/blog/blogDelProc")
    public ModelAndView blogDelProc(HttpServletRequest request, 
    									 Model model,
    									 HttpSession session,
    									 HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();

		Map<String, Object> maps = new HashMap<String, Object>();
		maps.put("idx", Util.CHK_NULL(request.getParameter("idx")));
		maps.put("del_id", "admin");
		
		String paramIdx = Util.CHK_NULL(request.getParameter("idx"));
		if (paramIdx != null) {
			blogService.removeOne(Integer.parseInt(paramIdx));
		}
		
		/*
		 * retrieve list
		 */
		List<BlogVo> blogList = blogService.list(new BlogEntity());
		Util.CHECK_GENDER_IN_BLOG(blogList);
		
		mav.addObject("blog_list", blogList);
		mav.addObject("locale", Util.GET_LOCALE(request.getHeader("Accept-Language")));
		mav.addObject("context_name", Util.LOAD_CONFIG("context_name"));
		
		mav.setViewName("/ds_admin/blog");
		
		return mav;
	}
}

