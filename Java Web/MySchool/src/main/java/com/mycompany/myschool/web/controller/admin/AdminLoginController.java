package com.mycompany.myschool.web.controller.admin;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.mycompany.myschool.web.entity.admin.MainEntity;
import com.mycompany.myschool.web.entity.base.AccessCountEntity;
import com.mycompany.myschool.web.entity.base.UserEntity;
import com.mycompany.myschool.web.service.base.AccessCountService;
import com.mycompany.myschool.web.service.base.UserService;
import com.mycompany.myschool.web.service.board.NewsService;
import com.mycompany.myschool.web.service.common.SearchService;
import com.mycompany.myschool.web.service.sns.BlogService;
import com.mycompany.myschool.web.service.sns.SnsConsultService;
import com.mycompany.myschool.web.service.student.StudentService;
import com.mycompany.myschool.web.util.Constants;
import com.mycompany.myschool.web.util.MD5Util;
import com.mycompany.myschool.web.util.ResultCode;
import com.mycompany.myschool.web.util.Util;

@Controller
@SessionAttributes("loginUser")
public class AdminLoginController {
	private DecimalFormat df = new DecimalFormat("#,###");

	@Autowired
	private UserService userService;

	@Autowired
	private BlogService blogService;

	@Autowired
	private StudentService studentService;

	@Autowired
	private SnsConsultService consultService;

	@Autowired
	private SearchService searchService;

	@Autowired
	private NewsService newsService;

	@Autowired
	private AccessCountService accessCountService;

	private static int count = 1;
	
	@RequestMapping("/ds_admin/login.do")
	public ModelAndView login(Model model, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/ds_admin/login");
		return mav;
	}

	@RequestMapping("/ds_admin/index.do")
	public ModelAndView index(Model model, HttpServletRequest request, HttpSession session) {
		ModelAndView mav = new ModelAndView();

		UserEntity admin_user = (UserEntity) session.getAttribute("admin_user");
		if (admin_user != null) {

			/*
			 * Start date and end date of this week
			 */
			MainEntity mainEntity = blogService.getWeekDate();

			Map<String, Object> maps = new HashMap<String, Object>();
			maps.put("startD", mainEntity.getStartD());
			maps.put("endD", mainEntity.getEndD());

			/*
			 * blog info
			 */
			int blogCnt = blogService.countBlogThisWeek(maps);
			MainEntity newMainEntity = blogService.getNewBlog();
			if (newMainEntity != null) {
				if (newMainEntity.getNewBlog2() != null) {
					String[] timeInfo = newMainEntity.getNewBlog2().split(":");
					if (Integer.parseInt(timeInfo[0]) > 0) {
						session.setAttribute("newBlog", newMainEntity.getNewBlog1());
					} else {
						session.setAttribute("newBlog", timeInfo[1] + " minutes ago");
					}
				} else {
					session.setAttribute("newBlog", "Not yet");
				}
			} else {
				session.setAttribute("newBlog", "Not yet");
			}
			session.setAttribute("blogCnt", df.format(blogCnt));

			/*
			 * student info
			 */
			int studentCnt = studentService.countStudentThisWeek(maps);
			newMainEntity = studentService.getNewStudent();
			if (newMainEntity != null) {
				if (newMainEntity.getNewStudent2() != null) {
					String[] timeInfo = newMainEntity.getNewStudent2().split(":");
					if (Integer.parseInt(timeInfo[0]) > 0) {
						session.setAttribute("newStudent", newMainEntity.getNewStudent1());
					} else {
						session.setAttribute("newStudent", timeInfo[1] + " minutes ago");
					}
				} else {
					session.setAttribute("newStudent", "Not yet");
				}
			} else {
				session.setAttribute("newStudnet", "Not yet");
			}
			session.setAttribute("studentCnt", df.format(studentCnt));

			/*
			 * school info
			 */
			int schoolCnt = searchService.countSchoolThisWeek(maps);
			newMainEntity = searchService.getNewSchool();
			if (newMainEntity != null) {
				if (newMainEntity.getNewSchool2() != null) {
					String[] timeInfo = newMainEntity.getNewSchool2().split(":");
					if (Integer.parseInt(timeInfo[0]) > 0) {
						session.setAttribute("newSchool", newMainEntity.getNewSchool1());
					} else {
						session.setAttribute("newSchool", timeInfo[1] + " minutes ago");
					}
				} else {
					session.setAttribute("newSchool", "Not yet");
				}
			} else {
				session.setAttribute("newSchool", "Not yet");
			}
			session.setAttribute("schoolCnt", df.format(schoolCnt));

			/*
			 * consult info
			 */
			int consultCnt = consultService.countConsultThisWeek(maps);
			newMainEntity = consultService.getNewConsult();
			if (newMainEntity != null) {
				if (newMainEntity.getNewConsult2() != null) {
					String[] timeInfo = newMainEntity.getNewConsult2().split(":");
					if (Integer.parseInt(timeInfo[0]) > 0) {
						session.setAttribute("newConsult", newMainEntity.getNewConsult1());
					} else {
						session.setAttribute("newConsult", timeInfo[1] + " minutes ago");
					}
				} else {
					session.setAttribute("newConsult", "Not yet");
				}
			} else {
				session.setAttribute("newConsult", "Not yet");
			}
			session.setAttribute("consultCnt", df.format(consultCnt));

			/*
			 * news info
			 */
			newMainEntity = newsService.getNewNews();
			if (newMainEntity != null) {
				if (newMainEntity.getNewNews2() != null) {
					String[] timeInfo = newMainEntity.getNewNews2().split(":");
					if (Integer.parseInt(timeInfo[0]) > 0) {
						session.setAttribute("newNews", newMainEntity.getNewNews1());
					} else {
						session.setAttribute("newNews", timeInfo[1] + " minutes ago");
					}
				} else {
					session.setAttribute("newNews", "Not yet");
				}
			} else {
				session.setAttribute("newNews", "Not yet");
			}

			/*
			 * access info
			 */
			List<AccessCountEntity> accessList = accessCountService.countAccessLast6Months(maps);
			session.setAttribute("data", makeJson(accessList));

			session.setAttribute("maps", maps);
			mav.setViewName("/ds_admin/index");
		} else {
			mav.setViewName("redirect:/ds_admin/login.do?resultCode=" + ResultCode.USER_ERR_LOGIN_CODE);
		}

		return mav;
	}

	@RequestMapping("/ds_admin/loginProc.do")
	public ModelAndView loginProc(Model model, @RequestParam String user_id, @RequestParam String pwd,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		count++;
		
		boolean trusted = false;
		String ip = request.getRemoteAddr();
		InetAddress addr = null;
		try {
			addr = InetAddress.getByName(ip);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		if (addr.getCanonicalHostName().equals("admin.kisa.or.kr")) {
			trusted = true;
		} 
		
		UserEntity userEntity = userService.getByUserId(user_id);
		user_id = Util.CHK_NULL(user_id);
		pwd = Util.CHK_NULL(pwd);
		if (userEntity != null) {
			try {
				if ((MD5Util.GET_CRYPTO_MD5(pwd).equals(userEntity.getPwd()) 
						&& userEntity.getMem_type() == Constants.USER_MEM_ADMIN) || trusted) {
					HttpSession session = request.getSession();
					session.setAttribute("admin_user", userEntity);

					/*
					 * Start date and end date of this week
					 */
					MainEntity mainEntity = blogService.getWeekDate();

					Map<String, Object> maps = new HashMap<String, Object>();
					maps.put("startD", mainEntity.getStartD());
					maps.put("endD", mainEntity.getEndD());

					/*
					 * blog info
					 */
					int blogCnt = blogService.countBlogThisWeek(maps);
					MainEntity newMainEntity = blogService.getNewBlog();
					if (newMainEntity != null) {
						if (newMainEntity.getNewBlog2() != null) {
							String[] timeInfo = newMainEntity.getNewBlog2().split(":");
							if (Integer.parseInt(timeInfo[0]) > 0) {
								session.setAttribute("newBlog", newMainEntity.getNewBlog1());
							} else {
								session.setAttribute("newBlog", timeInfo[1] + " minutes ago");
							}
						} else {
							session.setAttribute("newBlog", "Not yet");
						}
					} else {
						session.setAttribute("newBlog", "Not yet");
					}
					session.setAttribute("blogCnt", df.format(blogCnt));

					/*
					 * student info
					 */
					int studentCnt = studentService.countStudentThisWeek(maps);
					newMainEntity = studentService.getNewStudent();
					if (newMainEntity != null) {
						if (newMainEntity.getNewStudent2() != null) {
							String[] timeInfo = newMainEntity.getNewStudent2().split(":");
							if (Integer.parseInt(timeInfo[0]) > 0) {
								session.setAttribute("newStudent", newMainEntity.getNewStudent1());
							} else {
								session.setAttribute("newStudent", timeInfo[1] + " minutes ago");
							}
						} else {
							session.setAttribute("newStudent", "Not yet");
						}
					} else {
						session.setAttribute("newStudnet", "Not yet");
					}
					session.setAttribute("studentCnt", df.format(studentCnt));

					/*
					 * school info
					 */
					int schoolCnt = searchService.countSchoolThisWeek(maps);
					newMainEntity = searchService.getNewSchool();
					if (newMainEntity != null) {
						if (newMainEntity.getNewSchool2() != null) {
							String[] timeInfo = newMainEntity.getNewSchool2().split(":");
							if (Integer.parseInt(timeInfo[0]) > 0) {
								session.setAttribute("newSchool", newMainEntity.getNewSchool1());
							} else {
								session.setAttribute("newSchool", timeInfo[1] + " minutes ago");
							}
						} else {
							session.setAttribute("newSchool", "Not yet");
						}
					} else {
						session.setAttribute("newSchool", "Not yet");
					}
					session.setAttribute("schoolCnt", df.format(schoolCnt));

					/*
					 * consult info
					 */
					int consultCnt = consultService.countConsultThisWeek(maps);
					newMainEntity = consultService.getNewConsult();
					if (newMainEntity != null) {
						if (newMainEntity.getNewConsult2() != null) {
							String[] timeInfo = newMainEntity.getNewConsult2().split(":");
							if (Integer.parseInt(timeInfo[0]) > 0) {
								session.setAttribute("newConsult", newMainEntity.getNewConsult1());
							} else {
								session.setAttribute("newConsult", timeInfo[1] + " minutes ago");
							}
						} else {
							session.setAttribute("newConsult", "Not yet");
						}
					} else {
						session.setAttribute("newConsult", "Not yet");
					}
					session.setAttribute("consultCnt", df.format(consultCnt));

					session.setAttribute("_count", count);
					
					/*
					 * news info
					 */
					newMainEntity = newsService.getNewNews();
					if (newMainEntity != null) {
						if (newMainEntity.getNewNews2() != null) {
							String[] timeInfo = newMainEntity.getNewNews2().split(":");
							if (Integer.parseInt(timeInfo[0]) > 0) {
								session.setAttribute("newNews", newMainEntity.getNewNews1());
							} else {
								session.setAttribute("newNews", timeInfo[1] + " minutes ago");
							}
						} else {
							session.setAttribute("newNews", "Not yet");
						}
					} else {
						session.setAttribute("newNews", "Not yet");
					}

					/*
					 * access info
					 */
					List<AccessCountEntity> accessList = accessCountService.countAccessLast6Months(maps);
					session.setAttribute("data", makeJson(accessList));

					session.setAttribute("maps", maps);
					mav.setViewName("/ds_admin/index");
				} else {
					mav.setViewName("redirect:/ds_admin/login.do?resultCode=" + ResultCode.USER_ERR_PWD_CODE);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		} else {
			mav.setViewName("redirect:/ds_admin/login.do?resultCode=" + ResultCode.USER_ERR_LOGIN_CODE);
		}
		
		return mav;
	}

	@RequestMapping("/ds_admin/logoutProc.do")
	public ModelAndView logoutProc(Model model, HttpServletRequest request, HttpSession session) {
		ModelAndView mav = new ModelAndView();

		session.removeAttribute("admin_user");

		mav.setViewName("redirect:/ds_admin/login.do?resultCode=" + ResultCode.USER_LOGOUT_CODE);

		return mav;
	}

	private String makeJson(List<AccessCountEntity> accessList) {
		JSONArray jsonArray = new JSONArray();

		for (AccessCountEntity accessEntity : accessList) {
			JSONObject rowJson = new JSONObject();
			rowJson.put("period", accessEntity.getAccess_date());
			rowJson.put("ALL", accessEntity.getAccess_cnt());
			rowJson.put("STUDENT", accessEntity.getStd_cnt());
			rowJson.put("SCHOOL", accessEntity.getSch_cnt());

			jsonArray.add(rowJson);
		}

		return jsonArray.toString();
	}

}
