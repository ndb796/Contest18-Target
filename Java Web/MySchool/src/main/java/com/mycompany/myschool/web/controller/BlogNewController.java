package com.mycompany.myschool.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mycompany.myschool.web.entity.base.UserEntity;
import com.mycompany.myschool.web.entity.sns.BlogEntity;
import com.mycompany.myschool.web.entity.student.StudentEntity;
import com.mycompany.myschool.web.entity.vo.BlogVo;
import com.mycompany.myschool.web.service.sns.BlogService;
import com.mycompany.myschool.web.service.student.StudentService;
import com.mycompany.myschool.web.util.Constants;
import com.mycompany.myschool.web.util.ResultCode;
import com.mycompany.myschool.web.util.Token;
import com.mycompany.myschool.web.util.Util;

@Controller
public class BlogNewController {
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private StudentService studentService;
	
	@RequestMapping("/common/blog_new/blog")
    public ModelAndView blog(HttpServletRequest request, 
    									 Model model,
    									 HttpSession session) {
		UserEntity userEntity = (UserEntity)session.getAttribute("login_user");
		ModelAndView mav = new ModelAndView();
		
		if (userEntity != null) {
			mav.addObject("is_member", (request.getParameter("type") != null && request.getParameter("type").equals("1") ? "yes" : "no"));
		} 
		
		String cur_page = request.getParameter("cur_page");
		
		if (cur_page == null || cur_page.length() == 0) {
			cur_page = "1";
		}
		
		BlogEntity blogEntity = new BlogEntity();
		blogEntity.setShow_flg(Constants.BLOG_SHOW);
		blogEntity.setStartPage(Integer.parseInt(cur_page) - 1);
		
		List<BlogVo> blogList = blogService.listPaging(blogEntity);
		List<BlogVo> allBlogList = blogService.list(blogEntity);
		Util.CHECK_GENDER_IN_BLOG(blogList);
		
		mav.addObject("blog_list", blogList);
		
		mav.addObject("disp_page", Util.DISP_PAGES(allBlogList.size(), Integer.parseInt(cur_page)));
		
		mav.addObject("locale", Util.GET_LOCALE(request.getHeader("Accept-Language")));
		mav.addObject("context_name", Util.LOAD_CONFIG("context_name"));
		mav.setViewName("/common/blog_new/blog");
		
		return mav;
    }
	
	@RequestMapping("/common/blog_new/myblog")
    public ModelAndView myblog(HttpServletRequest request, 
    									 Model model,
    									 HttpSession session) {
		UserEntity userEntity = (UserEntity)session.getAttribute("login_user");
		ModelAndView mav = new ModelAndView();
		
		if (userEntity != null) {
			StudentEntity studentEntity = studentService.getByStudentId(userEntity.getUser_id());
			
			if (studentEntity != null) {
				String cur_page = request.getParameter("cur_page");
				int curPage = 0;
				if (cur_page != null && cur_page.length() > 0) {
					curPage = Integer.parseInt(cur_page);
				}
				
				int startPage = curPage * 10;
				
				BlogEntity blogEntity = new BlogEntity();
				blogEntity.setShow_flg(Constants.BLOG_SHOW);
				
				if (userEntity.getMem_type() == Constants.USER_MEM_STUDENT) {
					blogEntity.setStudent_id(userEntity.getUser_id());
				} else {
					blogEntity.setSchool_id(userEntity.getUser_id());
				}
				
				blogEntity.setStartPage(startPage);
				List<BlogVo> blogList = blogService.listPaging(blogEntity);
				List<BlogVo> allBlogList = blogService.list(blogEntity);
				
				int endPage = startPage + 10;
				if (allBlogList.size() < endPage) {
					endPage = allBlogList.size();
				}
				
				StringBuffer dispPage = new StringBuffer();
				if (startPage > 0) {
					dispPage.append("<a href='" +  request.getContextPath() + "/common/blog_new/myblog.do?cur_page=" + (curPage - 1) +"'>이전</a>  ");
				}
				
				
				if (allBlogList.size() == 0) {
					dispPage.append("0건 표시  ");
				} else {
					dispPage.append((startPage + 1) + "건～" + endPage + "건 표시  ");
				}
				if (allBlogList.size() > endPage) {
					dispPage.append("<a href='" +  request.getContextPath() + "/common/blog_new/myblog.do?cur_page=" + (curPage + 1) +"'>다음</a>");
				}
				
				Util.CHECK_GENDER_IN_BLOG(blogList);
				
				mav.addObject("blog_list", blogList);
				mav.addObject("disp_page", dispPage);
				mav.addObject("user_id", userEntity.getUser_id());
				
				studentEntity.setGender_str(Util.CONVERT_GENDER(studentEntity.getGender()));
				studentEntity.setCurrent_grade(Util.CONVERT_CUR_TYPE(studentEntity.getCurrent_type()));
				mav.addObject("student_entity", studentEntity);
				mav.setViewName("/common/blog_new/myblog");
			} else {
				mav.setViewName("redirect:/user/myprofile/stdBasicAddForm.do");
			}
		} else {
			mav.addObject("resultCode", ResultCode.USER_ERR_LOGIN_CODE);
			mav.setViewName("redirect:/index.do?resultCode=" + ResultCode.USER_ERR_LOGIN_CODE);
		}
		
		mav.addObject("locale", Util.GET_LOCALE(request.getHeader("Accept-Language")));
		mav.addObject("context_name", Util.LOAD_CONFIG("context_name"));
		
		return mav;
	}
	
	@RequestMapping("/common/blog_new/myblog_enter")
    public ModelAndView myblogEnter(HttpServletRequest request, 
    									 Model model,
    									 HttpSession session) {
		UserEntity userEntity = (UserEntity)session.getAttribute("login_user");
		ModelAndView mav = new ModelAndView();
		
		if (userEntity != null) {
			StudentEntity studentEntity = studentService.getByStudentId(userEntity.getUser_id());
			
			if (studentEntity != null) {
				if (request.getParameter("title") != null) {
					mav.addObject("title", request.getParameter("title"));
					mav.addObject("title_len", request.getParameter("title").length());
				} else {
					mav.addObject("title_len", "0");
				}
				
				if (request.getParameter("contents") != null) {
					mav.addObject("contents", request.getParameter("contents"));
					mav.addObject("contents_len", request.getParameter("contents").length());
				} else {
					mav.addObject("contents_len", "0");
				}
				
				studentEntity.setGender_str(Util.CONVERT_GENDER(studentEntity.getGender()));
				studentEntity.setCurrent_grade(Util.CONVERT_CUR_TYPE(studentEntity.getCurrent_type()));
				mav.addObject("student_entity", studentEntity);
				mav.setViewName("/common/blog_new/myblog_enter");
			} else {
				mav.setViewName("redirect:/user/myprofile/stdBasicAddForm.do");
			}
		} else {
			mav.addObject("resultCode", ResultCode.USER_ERR_LOGIN_CODE);
			mav.setViewName("redirect:/index.do?resultCode=" + ResultCode.USER_ERR_LOGIN_CODE);
		}
		
		mav.addObject("locale", Util.GET_LOCALE(request.getHeader("Accept-Language")));
		mav.addObject("context_name", Util.LOAD_CONFIG("context_name"));
		
		return mav;
	}
	
	@RequestMapping("/common/blog_new/myblog_confirm")
    public ModelAndView myblogConfirm(HttpServletRequest request, 
    									 Model model,
    									 HttpSession session) {
		UserEntity userEntity = (UserEntity)session.getAttribute("login_user");
		ModelAndView mav = new ModelAndView();
		
		if (userEntity != null) {
			StudentEntity studentEntity = studentService.getByStudentId(userEntity.getUser_id());
			
			if (studentEntity != null) {
				String mode = request.getParameter("mode");
				String idx = request.getParameter("idx");
				String title = request.getParameter("title");
				String contents = request.getParameter("contents");
				
				mav.addObject("mode", Util.CHK_NULL(mode));
				mav.addObject("idx", Util.CHK_NULL(idx));
				mav.addObject("title", Util.CHK_NULL(title));
				mav.addObject("contents", Util.CHK_NULL(contents).replaceAll("\n", "<br/>"));
				
				studentEntity.setGender_str(Util.CONVERT_GENDER(studentEntity.getGender()));
				studentEntity.setCurrent_grade(Util.CONVERT_CUR_TYPE(studentEntity.getCurrent_type()));
				mav.addObject("student_entity", studentEntity);
				mav.setViewName("/common/blog_new/myblog_confirm");
			} else {
				mav.setViewName("redirect:/user/myprofile/stdBasicAddForm.do");
			}
		} else {
			mav.addObject("resultCode", ResultCode.USER_ERR_LOGIN_CODE);
			mav.setViewName("redirect:/index.do?resultCode=" + ResultCode.USER_ERR_LOGIN_CODE);
		}
		
		mav.addObject("locale", Util.GET_LOCALE(request.getHeader("Accept-Language")));
		mav.addObject("context_name", Util.LOAD_CONFIG("context_name"));
		
		return mav;
	}
	
	@RequestMapping("/common/blog_new/myblog_complete")
    public ModelAndView myblogComplete(HttpServletRequest request, 
    									 Model model,
    									 HttpSession session) {
		UserEntity userEntity = (UserEntity)session.getAttribute("login_user");
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("/common/blog_new/myblog_complete");
		
		mav.addObject("locale", Util.GET_LOCALE(request.getHeader("Accept-Language")));
		mav.addObject("context_name", Util.LOAD_CONFIG("context_name"));
		
		return mav;
	}
	
	@RequestMapping("/common/blog_new/myblog_detail")
    public ModelAndView myblogDetail(HttpServletRequest request, 
    									 Model model,
    									 HttpSession session) {
		UserEntity userEntity = (UserEntity)session.getAttribute("login_user");
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("/common/blog_new/myblog_detail");
		
		mav.addObject("locale", Util.GET_LOCALE(request.getHeader("Accept-Language")));
		mav.addObject("context_name", Util.LOAD_CONFIG("context_name"));
		
		return mav;
	}
	
	@RequestMapping("/common/blog_new/myblog_proc")
    public ModelAndView myblogProc(HttpServletRequest request, 
    									 Model model,
    									 HttpSession session) {
		UserEntity userEntity = (UserEntity)session.getAttribute("login_user");
		ModelAndView mav = new ModelAndView();
		
		if (userEntity != null) {
			StudentEntity studentEntity = studentService.getByStudentId(userEntity.getUser_id());
			
			if (studentEntity != null) {
				String mode = request.getParameter("mode");
				String idx = request.getParameter("idx");
				String title = request.getParameter("title");
				String contents = request.getParameter("contents");
				
				BlogEntity blogEntity = new BlogEntity();
				blogEntity.setTitle(Util.CHK_NULL(title));
				blogEntity.setContents(Util.CHK_NULL(contents).replaceAll("<br/>", ""));
				blogEntity.setStudent_id(userEntity.getUser_id());
				blogEntity.setBlog_type(Constants.BLOG_TYPE_STD);
				blogEntity.setShow_flg(Constants.BLOG_SHOW);
				
				if(Token.isValid(request)) {
				    Token.set(request);
				    request.setAttribute("TOKEN_SAVE_CHECK", "TRUE");
				} else {
					request.setAttribute("TOKEN_SAVE_CHECK", "FALSE");
				}
				
				if("TRUE".equals(request.getAttribute("TOKEN_SAVE_CHECK"))) {
					if (mode != null && mode.equals("modify")) {
						blogEntity.setIdx(Integer.parseInt(idx));
						blogEntity.setUpd_id(userEntity.getUser_id());
						blogService.modify(blogEntity);
					} else {
						blogService.add(blogEntity);
						idx = "" + blogEntity.getIdx();
					}
				}
				
				mav.addObject("idx", idx);
				studentEntity.setGender_str(Util.CONVERT_GENDER(studentEntity.getGender()));
				studentEntity.setCurrent_grade(Util.CONVERT_CUR_TYPE(studentEntity.getCurrent_type()));
				mav.addObject("student_entity", studentEntity);
				mav.setViewName("/common/blog_new/myblog_complete");
			} else {
				mav.setViewName("redirect:/user/myprofile/stdBasicAddForm.do");
			}
		} else {
			mav.addObject("resultCode", ResultCode.USER_ERR_LOGIN_CODE);
			mav.setViewName("redirect:/index.do?resultCode=" + ResultCode.USER_ERR_LOGIN_CODE);
		}
		
		mav.addObject("locale", Util.GET_LOCALE(request.getHeader("Accept-Language")));
		mav.addObject("context_name", Util.LOAD_CONFIG("context_name"));
		
		return mav;
	}
	
	@RequestMapping("/common/blog_new/myblog_delete")
    public ModelAndView myblogDelete(HttpServletRequest request, 
    									 Model model,
    									 HttpSession session) {
		UserEntity userEntity = (UserEntity)session.getAttribute("login_user");
		ModelAndView mav = new ModelAndView();
		
		if (userEntity != null) {
			StudentEntity studentEntity = studentService.getByStudentId(userEntity.getUser_id());
			
			if (studentEntity != null) {
				String paramIdxs = request.getParameter("idx");
				
				String[] arrIdxs = Util.CHK_NULL(paramIdxs).split(",");
				int[] idxsArray = new int[arrIdxs.length];
				if (idxsArray != null && idxsArray.length > 0) {
					for (int i=0; i<arrIdxs.length; i++) {
						idxsArray[i] = Integer.parseInt(arrIdxs[i]);
					}
				}
				
				BlogEntity blogEntity = new BlogEntity();
				blogEntity.setIdxsArray(idxsArray);
				blogService.remove(blogEntity);
				
				String cur_page = request.getParameter("cur_page");
				int curPage = 0;
				if (cur_page != null && cur_page.length() > 0) {
					curPage = Integer.parseInt(cur_page);
				}
				
				int startPage = curPage * 10;
				
				blogEntity.setShow_flg(Constants.BLOG_SHOW);
				
				if (userEntity.getMem_type() == Constants.USER_MEM_STUDENT) {
					blogEntity.setStudent_id(userEntity.getUser_id());
				} else {
					blogEntity.setSchool_id(userEntity.getUser_id());
				}
				
				blogEntity.setStartPage(startPage);
				List<BlogVo> blogList = blogService.listPaging(blogEntity);
				List<BlogVo> allBlogList = blogService.list(blogEntity);
				
				int endPage = startPage + 10;
				if (allBlogList.size() < endPage) {
					endPage = allBlogList.size();
				}
				
				StringBuffer dispPage = new StringBuffer();
				if (startPage > 0) {
					dispPage.append("<a href='" +  request.getContextPath() + "/common/blog_new/myblog.do?cur_page=" + (curPage - 1) +"'>이전</a>  ");
				}
				dispPage.append((startPage + 1) + "건～" + endPage + "건 표시  ");
				if (allBlogList.size() > endPage) {
					dispPage.append("<a href='" +  request.getContextPath() + "/common/blog_new/myblog.do?cur_page=" + (curPage + 1) +"'>다음</a>");
				}
				
				Util.CHECK_GENDER_IN_BLOG(blogList);
				
				mav.addObject("blog_list", blogList);
				mav.addObject("disp_page", dispPage);
				mav.addObject("resultMsg", ResultCode.USER_D_SUCCESS_CODE);
				studentEntity.setGender_str(Util.CONVERT_GENDER(studentEntity.getGender()));
				studentEntity.setCurrent_grade(Util.CONVERT_CUR_TYPE(studentEntity.getCurrent_type()));
				mav.addObject("student_entity", studentEntity);
				mav.setViewName("/common/blog_new/myblog");
			} else {
				mav.setViewName("redirect:/user/myprofile/stdBasicAddForm.do");
			}
		} else {
			mav.addObject("resultCode", ResultCode.USER_ERR_LOGIN_CODE);
			mav.setViewName("redirect:/index.do?resultCode=" + ResultCode.USER_ERR_LOGIN_CODE);
		}
		
		mav.addObject("locale", Util.GET_LOCALE(request.getHeader("Accept-Language")));
		mav.addObject("context_name", Util.LOAD_CONFIG("context_name"));
		
		return mav;
	}
	
	@RequestMapping("/common/blog_new/myblog_modify")
    public ModelAndView myblogModify(HttpServletRequest request, 
    									 Model model,
    									 HttpSession session) {
		UserEntity userEntity = (UserEntity)session.getAttribute("login_user");
		ModelAndView mav = new ModelAndView();
		
		if (userEntity != null) {
			StudentEntity studentEntity = studentService.getByStudentId(userEntity.getUser_id());
			
			if (studentEntity != null) {
				Map<String, Object> maps = new HashMap<String, Object>();
				maps.put("idx", request.getParameter("idx"));
				BlogVo blogVo = blogService.get(maps);
				
				if (blogVo != null) {
					mav.addObject("blog_entity", blogVo);
					mav.addObject("title", blogVo.getTitle());
					mav.addObject("title_len", blogVo.getTitle().length());
					mav.addObject("contents", blogVo.getContents());
					mav.addObject("contents_len", blogVo.getContents().length());
					mav.addObject("mode", "modify");
				}
				
				studentEntity.setGender_str(Util.CONVERT_GENDER(studentEntity.getGender()));
				studentEntity.setCurrent_grade(Util.CONVERT_CUR_TYPE(studentEntity.getCurrent_type()));
				mav.addObject("student_entity", studentEntity);
				mav.setViewName("/common/blog_new/myblog_enter");
			} else {
				mav.setViewName("redirect:/user/myprofile/stdBasicAddForm.do");
			}
		} else {
			mav.addObject("resultCode", ResultCode.USER_ERR_LOGIN_CODE);
			mav.setViewName("redirect:/index.do?resultCode=" + ResultCode.USER_ERR_LOGIN_CODE);
		}
		
		mav.addObject("locale", Util.GET_LOCALE(request.getHeader("Accept-Language")));
		mav.addObject("context_name", Util.LOAD_CONFIG("context_name"));
		
		return mav;
	}
}
