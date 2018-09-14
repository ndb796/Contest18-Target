package com.mycompany.myschool.web.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.mycompany.myschool.web.entity.base.AccessCountEntity;
import com.mycompany.myschool.web.entity.base.UserEntity;
import com.mycompany.myschool.web.entity.board.NewsEntity;
import com.mycompany.myschool.web.entity.sns.BlogEntity;
import com.mycompany.myschool.web.entity.student.StudentEntity;
import com.mycompany.myschool.web.entity.vo.BlogVo;
import com.mycompany.myschool.web.entity.vo.RecommendListVo;
import com.mycompany.myschool.web.manager.DRESManager;
import com.mycompany.myschool.web.service.base.AccessCountService;
import com.mycompany.myschool.web.service.base.UserService;
import com.mycompany.myschool.web.service.board.NewsService;
import com.mycompany.myschool.web.service.common.SearchService;
import com.mycompany.myschool.web.service.sns.BlogService;
import com.mycompany.myschool.web.service.student.RecommendService;
import com.mycompany.myschool.web.service.student.StudentService;
import com.mycompany.myschool.web.service.student.StudentSub2Service;
import com.mycompany.myschool.web.util.Constants;
import com.mycompany.myschool.web.util.IDGenerator;
import com.mycompany.myschool.web.util.MD5Util;
import com.mycompany.myschool.web.util.ResultCode;
import com.mycompany.myschool.web.util.Token;
import com.mycompany.myschool.web.util.Util;

@Controller
@SessionAttributes("loginUser")
public class LoginController {
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	@Autowired
	private UserService userService;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private RecommendService rcmmdService;
	
	@Autowired
	private StudentSub2Service specificService;
	
	@Autowired
	private SearchService searchService;
	
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private NewsService newsService;
	
	@Autowired
	private AccessCountService accessCountService;
	
	@RequestMapping("/loginProc")
    public ModelAndView loginProc(Model model, 
    							  @RequestParam String user_email,
    							  @RequestParam String user_pwd,
    							  HttpServletRequest request,
    							  HttpServletResponse response,
    							  HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		List<UserEntity> userEntitylist = userService.getByLogin(user_email);
		if (userEntitylist != null && userEntitylist.size() > 0) {
			UserEntity userEntity = userEntitylist.get(0);
			
			if (userEntity != null) {
				try {
					if (Util.CHK_NULL(MD5Util.GET_CRYPTO_MD5(user_pwd)).equals(userEntity.getPwd())) {
						model.addAttribute("login_user", userEntity);
						session.setAttribute("login_user", userEntity);
						
						byte[] user_status = Util.STR2BYTEARR(userEntity.getReg_status());
						
						String today = Util.GET_TODAY_STR();
						if (accessCountService.get(Util.GET_TODAY_STR()) == null) {
							AccessCountEntity accessCountEntity = new AccessCountEntity();
							accessCountEntity.setAccess_date(today);
							accessCountService.add(accessCountEntity);
						}
						
						// cookie check for mem_type
						int mem_type = Constants.USER_MEM_EMPTY;
						Cookie[] cookies = request.getCookies();
						for (Cookie cookie : cookies) {
							if (cookie.getName().equals("mem_type")) {
								mem_type = Integer.parseInt(cookie.getValue());
								if (mem_type != userEntity.getMem_type()) {
									mem_type = userEntity.getMem_type();
								}
								
								break;
							}
						}
						
						if (mem_type == Constants.USER_MEM_EMPTY) {
							mem_type = userEntity.getMem_type();
						}
						
						if (mem_type == Constants.USER_MEM_STUDENT) {
							accessCountService.modifyStdCnt(today);
							mav.setViewName("redirect:/common/blog_new/myblog.do");	
						} else {
							accessCountService.modifySchCnt(today);
							
							if (user_status[Constants.USER_STATUS_IDX_BASC] == Constants.USER_STATUS_OFF) {
								mav.setViewName("redirect:/customer/myprofile/schBasicAddForm.do");
							} else {
								mav.setViewName("redirect:/customer/myprofile/schBasicView.do?school_id=" + userEntity.getUser_id());										
							}
						}
						
						// cookie handling
						Cookie c = new Cookie("mem_type", Integer.toString(mem_type));
						c.setMaxAge(60 * 60 * 24 * 365);
						response.addCookie(c);
						
					} else {
						mav.addObject("resultCode", ResultCode.USER_ERR_PWD_CODE);
						mav.setViewName("redirect:/index.do?resultCode=" + ResultCode.USER_ERR_PWD_CODE);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				mav.addObject("resultCode", ResultCode.USER_ERR_LOGIN_CODE);
				mav.setViewName("redirect:/index.do?resultCode=" + ResultCode.USER_ERR_PWD_CODE);
			}
		} else {
			mav.addObject("resultCode", ResultCode.USER_ERR_LOGIN_CODE);
			mav.setViewName("redirect:/index.do?resultCode=" + ResultCode.USER_ERR_LOGIN_CODE);
		}
		
        return mav;
    }
	
	@RequestMapping("/loginProcForApp")
    public void loginProcForApp(@RequestParam("user_email") String user_email,
    							  @RequestParam("user_pwd") String user_pwd,
    							  HttpSession session,
    							  HttpServletRequest request,
    							  HttpServletResponse response) {
		OutputStream out = null;
		try {
			out = response.getOutputStream();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		List<UserEntity> userEntitylist = userService.getByLogin(user_email);
		if (userEntitylist != null && userEntitylist.size() > 0) {
			UserEntity userEntity = userEntitylist.get(0);
			
			if (userEntity != null) {
				if (Util.CHK_NULL(user_pwd).equals(userEntity.getPwd())) {
					session.setAttribute("login_user", userEntity);
					
					byte[] user_status = Util.STR2BYTEARR(userEntity.getReg_status());
					
					String today = Util.GET_TODAY_STR();
					if (accessCountService.get(Util.GET_TODAY_STR()) == null) {
						AccessCountEntity accessCountEntity = new AccessCountEntity();
						accessCountEntity.setAccess_date(today);
						accessCountService.add(accessCountEntity);
					}
					
					session.removeAttribute("auth_token_" + request.getRemoteAddr());
					session.removeAttribute("login_user_app");
					String msg = null;
					if (userEntity.getMem_type() == Constants.USER_MEM_STUDENT) {
						accessCountService.modifyStdCnt(today);
						
						UUID auth_token = UUID.nameUUIDFromBytes((request.getRemoteAddr() + System.currentTimeMillis()).getBytes()) ;
						msg = "{\"auth_token\":\"" + auth_token + "\", \"SC_CODE\":\"200\"}";
						session.setAttribute("auth_token_" + request.getRemoteAddr(), auth_token);
						session.setAttribute(auth_token.toString(), userEntity);
					} else {
						msg = "{\"SC_CODE\":\"500\",\"SC_MSG\"=\"ONLY STUDENT CAN ACCESS\"}";
					}
					
					try {
						out.write(msg.getBytes());
						out.flush();
						out.close();
					} catch (IOException e) {
						e.printStackTrace();
					}	
				} else {
					String msg = "{\"SC_CODE\":\"500\",\"SC_MSG\"=\"WRONG PASSWORD\"}";
					try {
						out.write(msg.getBytes());
						out.flush();
						out.close();
					} catch (IOException e) {
						e.printStackTrace();
					}	
				}
			} else {
				String msg = "{\"SC_CODE\":\"500\",\"SC_MSG\"=\"ID DOES NOT EXISTS\"}";
				try {
					out.write(msg.getBytes());
					out.flush();
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}	
			}
		} else {
			String msg = "{\"SC_CODE\":\"500\",\"SC_MSG\"=\"WRONG EMAIL\"}";
			try {
				out.write(msg.getBytes());
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
    }
	
	@RequestMapping("/myprofile")
    public ModelAndView myprofile(Model model, 
    							  HttpServletRequest request,
    							  HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		UserEntity userEntity = (UserEntity)session.getAttribute("login_user");
		if (userEntity != null) {
			byte[] user_status = Util.STR2BYTEARR(userEntity.getReg_status());
			
			/*
			 * 학생, 학교 어디로 갈지 결정
			 */
			if (userEntity.getMem_type() == Constants.USER_MEM_STUDENT) {
				/*accessCountService.modifyStdCnt(today);*/
				
				if (user_status[Constants.USER_STATUS_IDX_BASC] == Constants.USER_STATUS_OFF) {
					mav.setViewName("redirect:/user/myprofile/stdBasicAddForm.do");
				} else {
					/*
					 * Deviewstory recommend system (DRES) - 실행 시점
					 * 1. 학생이 로그인 하면 실행
					 * 2. 상담 내역이 없는 7일 이전의 데이터 검색하여 데이터 삭제 -> DREMManager로 이동 
					 * 3. 학생의 추천 목록이 10개 미만이면 실행 
					 * 
					 * Deviewstory REcommend System - 실행 방법
					 * 1. 해당 학생이 신규 가입 시 추천 받고 싶은 학교의 종류(초, 중, 고, 대학 등)를 선택한 뒤, 해당 학교를 중심으로 DRES 실행
					 * 2. SPECIFIC_TX의 정보 중 우선순위에 의해 SCHOOL은 SCHOOL VIEW의 NAME과 LOCATION은 SCHOOL VIEW의 ADDRESS와 비교하여 1차 목록 추출 (나머지 우선순위는 미정) 
					 * 3. SPECIFIC_TX가 없거나, 추천 목록이 10개 미만이면, STUDENT_TX의 ZIPCODE, ADDRESS 정보와 SCHOOL VIEW의 ZIPCODE, ADDRESS 컬럼을 비교하여 2차 목록을 추출 
					 * 4. 추천 된 총 10개의 데이터를 RCMMD_TX에 저장
					 */
					
					/*
					 * JOB1 - 학생의 추천 목록이 10개 미만이면 실행
					 */
					List<RecommendListVo> rcmmdList = rcmmdService.listRcmmdSchoolListViewByStudentId(userEntity.getUser_id());
					
					if (rcmmdList == null || rcmmdList.size() < Constants.BASE_RCMMD_COUNT) {
						boolean dresResult = new DRESManager().EXEC_DRES(userEntity, rcmmdList, studentService, specificService, searchService, rcmmdService);
					}
					
					mav.setViewName("redirect:/user/myprofile/stdBasicView.do?student_id=" + userEntity.getUser_id());									
				}
			} else {
				/*accessCountService.modifySchCnt(today);*/
				
				if (user_status[Constants.USER_STATUS_IDX_BASC] == Constants.USER_STATUS_OFF) {
					mav.setViewName("redirect:/customer/myprofile/schBasicAddForm.do");
				} else {
					mav.setViewName("redirect:/customer/myprofile/schBasicView.do?school_id=" + userEntity.getUser_id());										
				}
			}
		} else {
			mav.addObject("resultCode", ResultCode.USER_ERR_LOGIN_CODE);
			mav.setViewName("redirect:/index.do?resultCode=" + ResultCode.USER_ERR_LOGIN_CODE);
		}
		
        return mav;
    }
	
	@RequestMapping("/fbLoginProc")
    public ModelAndView fbLoginProc(Model model, 
    								HttpServletRequest request,
    								HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		String fbUserId = request.getParameter("fbUserId");
		String email = request.getParameter("email");
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String gender = request.getParameter("gender");
		String joinType = request.getParameter("joinType");
		String memberType = request.getParameter("memberType");
		
		List<UserEntity> userEntitylist = userService.getByLogin(email);
		if (userEntitylist != null && userEntitylist.size() > 0) {
			UserEntity userEntity = userEntitylist.get(0);
			
			model.addAttribute("login_user", userEntity);
			session.setAttribute("login_user", userEntity);
			
			byte[] user_status = Util.STR2BYTEARR(userEntity.getReg_status());
			
			String today = Util.GET_TODAY_STR();
			if (accessCountService.get(Util.GET_TODAY_STR()) == null) {
				AccessCountEntity accessCountEntity = new AccessCountEntity();
				accessCountEntity.setAccess_date(today);
				accessCountService.add(accessCountEntity);
			}
			
			accessCountService.modifyStdCnt(today);
			
			if (user_status[Constants.USER_STATUS_IDX_BASC] == Constants.USER_STATUS_OFF) {
				mav.setViewName("redirect:/user/myprofile/stdBasicAddForm.do");
			} else {
				rcmmdService.removeOldDataByStudentId(userEntity.getUser_id());
				List<RecommendListVo> rcmmdList = rcmmdService.listRcmmdSchoolListViewByStudentId(userEntity.getUser_id());
				
				if (rcmmdList == null || rcmmdList.size() < Constants.BASE_RCMMD_COUNT) {
					boolean dresResult = new DRESManager().EXEC_DRES(userEntity, rcmmdList, studentService, specificService, searchService, rcmmdService);
				}
				
				mav.setViewName("redirect:/common/blog/myblog.do");	
			}
		} else {  // join by facebook id
			String currentId = "";
			UserEntity userEntity = new UserEntity();
			Map<String, String> map = new HashMap<String, String>();
			
			map.put("keyword", Constants.SRVC_TYPE_STD);
			currentId = userService.getMaxId(map);
			userEntity.setUser_id(IDGenerator.NEXT_ID(Constants.SRVC_TYPE_STD, currentId));
			
			userEntity.setEmail(email);
			userEntity.setJoin_type(Integer.parseInt(joinType));
			userEntity.setMem_type(Integer.parseInt(memberType));
			userEntity.setReg_status("100000");	// 1:메일정보가입, 00000: 나머지는 등록없
			
			if(Token.isValid(request)) {
			    Token.set(request);
			    request.setAttribute("TOKEN_SAVE_CHECK", "TRUE");
			} else {
				request.setAttribute("TOKEN_SAVE_CHECK", "FALSE");
			}
			
			boolean isError = false;
			
			try {
				if("TRUE".equals(request.getAttribute("TOKEN_SAVE_CHECK"))) {
					if (!userService.add(userEntity)) {
						isError = true;
					} else {
						/*
						 * Add to student table 
						 */
//						StudentEntity studentEntity = new StudentEntity();
//						
//						studentService.add(studentEntity);
					}
				} 
			} catch (Exception e) {
				isError = true;
				e.printStackTrace();
			}
			
			if (isError) {
				mav.setViewName("redirect:/index.do?resultCode=" + ResultCode.USER_ERR_CREATE_CODE);
			} else {
				session.setAttribute("login_user", userEntity);
				mav.addObject("mode", "add");
				
				List<UserEntity>  dbUserEntitylist = userService.getByLogin(email);
				if (dbUserEntitylist != null && dbUserEntitylist.size() > 0) {
					UserEntity dbUserEntity = dbUserEntitylist.get(0);
					mav.addObject("userEntity", dbUserEntity);
					StudentEntity studentEntity = new StudentEntity();
					studentEntity.setStudent_id(dbUserEntity.getUser_id());
					
					model.addAttribute("studentEntity", studentEntity);
					
					/*
					 * Setting date
					 */
					mav.addObject("birth_y_map", Util.GET_BIRTH_Y_MAP(1950, 2007));
					mav.addObject("birth_m_map", Util.GET_BIRTH_MD_MAP(12));
					mav.addObject("birth_d_map", Util.GET_BIRTH_MD_MAP(31));
					
					mav.setViewName("/user/myprofile/stdMypage1");
				} else {
					mav.setViewName("redirect:/index.do");
				}
			}
		}
		
		mav.addObject("locale", Util.GET_LOCALE(request.getHeader("Accept-Language")));
		mav.addObject("context_name", Util.LOAD_CONFIG("context_name"));
		
        return mav;
    }
	
	@RequestMapping("/logoutProc")
    public ModelAndView logoutProc(Model model, 
    							  HttpServletRequest request,
    							  HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		model.addAttribute("login_user", new StudentEntity());
		session.removeAttribute("login_user");
		
		mav.addObject("resultCode", ResultCode.USER_LOGOUT_CODE);
		mav.setViewName("redirect:/index.do?resultCode=" + ResultCode.USER_LOGOUT_CODE);
		
        return mav;
    }
	
	@RequestMapping("/index")
    public ModelAndView index(Model model, 
    							  HttpServletRequest request,
    							  HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		model.addAttribute("login_user", new StudentEntity());
		session.removeAttribute("login_user");
		
		BlogEntity blogEntity = new BlogEntity();
		blogEntity.setShow_flg(Constants.BLOG_SHOW);
		blogEntity.setRows_per_page(Constants.BLOGROWS_OF_INDEX);
		List<BlogVo> blogList = blogService.list(blogEntity);
		
		Util.CHECK_GENDER_IN_BLOG(blogList);
		
		/*
		 * Retrieve to news list 
		 */
		Calendar cal = Calendar.getInstance();
		String today = sdf.format(cal.getTime());
		
		List<NewsEntity> newsList = newsService.list(new NewsEntity());
		List<NewsEntity> indexNewsList = new ArrayList<NewsEntity>();
		if (newsList != null) {
			for (NewsEntity newsEntity : newsList) {
				newsEntity.setUpd_date_str(newsEntity.getUpd_date_str().substring(0, 10));
				newsEntity.setNews_contents(newsEntity.getNews_contents().replaceAll("\n", "<br/>"));
				indexNewsList.add(newsEntity);
				
				if (indexNewsList.size() > 10) {
					break;
				}
			}
		}
		
		/*
		 * Retrieve to Pickup school
		 */
		Map<String, String> pickupMap = readXML("SCH000011");
		mav.addObject("pickup_img", pickupMap.get("image"));
		mav.addObject("pickup_name", pickupMap.get("name"));
		mav.addObject("pickup_desc1", pickupMap.get("desc1"));
		mav.addObject("pickup_desc2", pickupMap.get("desc2"));
		
		mav.addObject("news_list", indexNewsList);
		mav.addObject("blog_list", blogList);
		mav.setViewName("/common/index");
		
        return mav;
    }
	
	public Map<String, String> readXML(String name)  {
		 Map<String, String> map = new HashMap<String, String>();
	     String[] els = {"image", "name", "desc1", "desc2"};
		 try {
		   InputStream is =
				   this.getClass().getClassLoader().getResourceAsStream("conf/pickupschool.xml");
	       DocumentBuilderFactory builderFactory = 
	    		    DocumentBuilderFactory.newInstance();
		   DocumentBuilder builder =  builderFactory.newDocumentBuilder();
		   Document xmlDocument = builder.parse(is);
		   XPath xPath =  XPathFactory.newInstance().newXPath();
		
		   for (String el : els) {
			   String expression = "//*/school/" + el;
			   NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);
			   for (int i = 0; i < nodeList.getLength(); i++) {
				   map.put(nodeList.item(i).getNodeName(), nodeList.item(i).getTextContent());
			   }
		   }
		   
		 } catch (Exception ex) {
			 ex.printStackTrace();
		 }
		 
		 return map;
	}
	
	@RequestMapping("/privacy_policy.do")
    public ModelAndView privacyPolicy(Model model, 
    							  HttpServletRequest request,
    							  HttpSession session) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/common/privacy_policy");
        return mav;
	}
	
	@RequestMapping("/press.do")
    public ModelAndView press(Model model, 
    							  HttpServletRequest request,
    							  HttpSession session) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/common/press");
        return mav;
	}
	
	@RequestMapping("/rules.do")
    public ModelAndView rules(Model model, 
    							  HttpServletRequest request,
    							  HttpSession session) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/common/rules");
        return mav;
	}
}

