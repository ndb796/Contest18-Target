package com.mycompany.myschool.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/*import com.mycompany.myschool.sns.service.SchoolImageService;
import com.mycompany.myschool.sns.service.SchoolKeyService;
import com.mycompany.myschool.sns.service.SchoolRefService;*/
import com.mycompany.myschool.web.entity.base.UserEntity;
import com.mycompany.myschool.web.entity.customer.CustomerEntity;
import com.mycompany.myschool.web.entity.customer.CustomerImageEntity;
import com.mycompany.myschool.web.service.base.UserService;
import com.mycompany.myschool.web.service.customer.CustomerImageService;
import com.mycompany.myschool.web.service.customer.CustomerService;
import com.mycompany.myschool.web.util.Constants;
import com.mycompany.myschool.web.util.ResultCode;
import com.mycompany.myschool.web.util.Token;
import com.mycompany.myschool.web.util.Util;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private CustomerImageService customerImageService;
	
	private File realDir;
	
	public CustomerController() {
		realDir = new File(System.getProperty("user.home") + File.separator + ".myschool" + File.separator + "upload" + File.separator + "school");
		realDir.mkdirs();
	}
	
	// Saved file directory: 
	/*		Development environment -> C:\\Users\\dowon\\Documents\\workspace-sts-3.9.4.RELEASE\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp2\\wtpwebapps\\MySchool\\images\\upload\\school\\[SCHOOLID]*/
	/* 		Release environment -> [Tomcat Home]/[Context Root]/images/upload/school/[SCHOOL ID] */
	@RequestMapping("/myprofile/gallery")
    public ModelAndView gallery(	 HttpServletRequest request, 
    									 Model model,
 					 					 HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("locale", Util.GET_LOCALE(request.getHeader("Accept-Language")));
		mav.addObject("context_name", Util.LOAD_CONFIG("context_name"));
		
		return mav;
    }
	
	@RequestMapping("/myprofile/schBasicAddForm")
    public ModelAndView schoolRegister1(HttpServletRequest request, 
    					 					HttpServletResponse response, 
    					 					Model model,
    					 					HttpSession session) {
		ModelAndView mav = new ModelAndView();
		UserEntity userEntity = (UserEntity)session.getAttribute("login_user");
		
		if (userEntity != null) {		// login 페이로부터 이동 
			mav.addObject("mode", "add");
			CustomerEntity schoolEntity = new CustomerEntity();
			schoolEntity.setSchool_id(userEntity.getUser_id());
			
			UserEntity dbUserEntity = userService.getByUserId(userEntity.getUser_id());
			model.addAttribute("userEntity", dbUserEntity);
			model.addAttribute("schoolEntity", schoolEntity);
			
			/*
			 * Retrieve branch count
			 */
			int bracnCount = customerService.getBranchCount(userEntity.getUser_id());
			mav.addObject("branch_count", bracnCount);
			
			mav.setViewName("/customer/myprofile/schMypage1");
		} else {
			mav.setViewName("redirect:/index.do?resultCode=" + ResultCode.USER_ERR_NOTLOGIN_CODE);
		}

		mav.addObject("locale", Util.GET_LOCALE(request.getHeader("Accept-Language")));
		mav.addObject("context_name", Util.LOAD_CONFIG("context_name"));
		
		return mav;
    }

	@RequestMapping("/myprofile/schBasicProc")
    public ModelAndView schoolRegister2(@ModelAttribute("schoolEntity") CustomerEntity schoolEntity,
    								  Model model,
									  HttpServletRequest request,
									  HttpSession session) {
		ModelAndView mav = new ModelAndView();
		String mode = request.getParameter("mode");
		UserEntity userEntity = (UserEntity)session.getAttribute("login_user");
		
		if (schoolEntity == null || userEntity == null) {
			mav.setViewName("/login.do");
			return mav;
		}
		
		File dir = new File(session.getServletContext().getRealPath("/") + "/images/upload/school/" + Util.PATH_FILTER(schoolEntity.getSchool_id()) + "/");
		File galleryDir = new File(session.getServletContext().getRealPath("/") + "/images/upload/school/" + Util.PATH_FILTER(schoolEntity.getSchool_id()) + "/gallery/");
    	if (!dir.exists()) {
    		dir.mkdirs();
        }
    	if (!galleryDir.exists()) {
    		galleryDir.mkdirs();
        }
    	
		String gallery_files = Util.CHK_NULL(request.getParameter("gallery_files"));
		String gallery_origin_files = Util.CHK_NULL(request.getParameter("gallery_origin_files"));
		String gallery_filesizes = Util.CHK_NULL(request.getParameter("gallery_filesizes"));
		File tempGalleryDir = new File(session.getServletContext().getRealPath("/") + "/images/upload/school/temp/");
		
		if (userEntity != null) {
			if(Token.isValid(request)) {
			    Token.set(request);
			    request.setAttribute("TOKEN_SAVE_CHECK", "TRUE");
			} else {
				request.setAttribute("TOKEN_SAVE_CHECK", "FALSE");
			}
			
			boolean isError = false;
			
			try {
//				if("TRUE".equals(request.getAttribute("TOKEN_SAVE_CHECK"))) {
					String tempFilename = schoolEntity.getSchool_id() + "_" + userEntity.getEmail() + "_LOGO_" + System.currentTimeMillis();
					boolean isExistFile = false;
					if(!schoolEntity.getPhoto_file().isEmpty()){
				        String filename = schoolEntity.getPhoto_file().getOriginalFilename();
				        String imgExt = filename.substring(filename.lastIndexOf(".")+1, filename.length());
				        tempFilename += "." + imgExt.toLowerCase();
				        
				        if(imgExt.equalsIgnoreCase("JPG") || imgExt.equalsIgnoreCase("JPEG") || imgExt.equalsIgnoreCase("GIF") || imgExt.equalsIgnoreCase("PNG")) {
				            byte[] bytes = schoolEntity.getPhoto_file().getBytes();
				            try {
				            	File outFile = new File(dir, Util.PATH_FILTER(tempFilename));
				            	FileOutputStream fos = new FileOutputStream(outFile);
				                fos.write(bytes);
				                 
				                schoolEntity.setLogoname(tempFilename);
				                schoolEntity.setLogosize(bytes.length);
				                schoolEntity.setOrigin_name(filename);
				                fos.close();
				                if (schoolEntity.getPhoto_file().getSize() > 20000000) 
				                	fos.flush();
				                isExistFile = true;
				            } catch(IOException ie) {
				            	ie.printStackTrace();
				            	isError = true;
				                System.err.println("File writing error! ");
				            }
				            System.err.println("File upload success! ");
				        } else {
				        	isError = true;
				            System.err.println("File type error! ");
				        }
				    } else {
				    	isExistFile = false;
				    }
					
					UserEntity dbUserEntity = userService.getByUserId(userEntity.getUser_id());
					byte[] reg_status = Util.STR2BYTEARR(dbUserEntity.getReg_status());
					reg_status[Constants.USER_STATUS_IDX_BASC] = Constants.USER_STATUS_ON;
					dbUserEntity.setReg_status(Util.BYTEARR2STR(reg_status));
					userService.modifyStatus(dbUserEntity);
					
					mav.addObject("userEntity",dbUserEntity);
					
					/*
					 * Add or Modify
					 */
					if (mode != null && mode.equals("add")) {
						customerService.add(schoolEntity);
					} else {
						customerService.modify(schoolEntity);
					}
					
					/*
					 * Gallery image 
					 */
					if (gallery_files != null && gallery_files.length() > 0) {
						//customerImageService.remove(schoolEntity.getSchool_id());
						String[] galleryNames = gallery_files.split(",");
						String[] galleryOriginNames = gallery_origin_files.split(",");
						String[] galleryFilesizes = gallery_filesizes.split(",");
						for (int i=0; i<galleryNames.length; i++) {
							System.out.println(galleryNames[i] + "/" + galleryOriginNames[i] + "/" + galleryFilesizes[i]);
							File gFile = new File(tempGalleryDir, Util.PATH_FILTER(galleryNames[i]));
							File newFile = new File(galleryDir, Util.PATH_FILTER(galleryNames[i])); 
							gFile.renameTo(newFile);
							
							CustomerImageEntity imageEntity = new CustomerImageEntity();
							imageEntity.setImg_ord(i + 1);
							imageEntity.setSchool_id(schoolEntity.getSchool_id());
							imageEntity.setFilename(galleryNames[i]);
							int sidx = galleryFilesizes[i] != null ? galleryFilesizes[i].indexOf("Kb") : 0;
							imageEntity.setFilesize(Long.parseLong(galleryFilesizes[i].substring(0, sidx).trim()));
							imageEntity.setOrigin_name(galleryOriginNames[i]);
							
							customerImageService.add(imageEntity);
						}
					}
					
					// Some type of file processing...
				   /* System.out.println("-------------------------------------------");
				    System.out.println("Test upload: " + schoolEntity.getLogoname());
				    System.out.println("Test upload: " + schoolEntity.getPhoto_file().getOriginalFilename());
				    System.out.println("-------------------------------------------");*/
//				}
			} catch (Exception e) {
				isError = true;
				e.printStackTrace();
			}
			
			if (isError) {
				mav.addObject("mode", mode);
				mav.addObject("school_entity", schoolEntity);
				mav.setViewName("/customer/myprofile/schMypage1");
			} else {
				UserEntity dbUserEntity = userService.getByUserId(schoolEntity.getSchool_id());
				CustomerEntity dbSchoolEntity = customerService.get(schoolEntity.getSchool_id());
//				String prefix = userEntity.getPwd().substring(0, 2);
//				String surfix = userEntity.getPwd().substring(2);
//				StringBuffer astrix = new StringBuffer();
//				for (int i=0; i<surfix.length(); i++) {
//					astrix.append("*"); 
//				}
//				dbUserEntity.setPwd(prefix + astrix.toString());
				
				mav.addObject("mode", "view");
				mav.addObject("school_id", schoolEntity.getSchool_id());
				mav.addObject("userEntity", dbUserEntity);
				mav.addObject("schoolEntity", dbSchoolEntity);
				
				List<CustomerImageEntity> imageList = customerImageService.listBySchoolId(userEntity.getUser_id());
				if (imageList != null && imageList.size() > 4) {
					for (int i=4; i>imageList.size(); i++) {
						imageList.remove(i);
					}
				}
				mav.addObject("gallery_list", imageList);
				
				mav.setViewName("/customer/myprofile/schView1");
			}
			
			session.removeAttribute("token");
		} else {
			mav.setViewName("redirect:/index.do");
		}
		
		mav.addObject("locale", Util.GET_LOCALE(request.getHeader("Accept-Language")));
		mav.addObject("context_name", Util.LOAD_CONFIG("context_name"));
		
        return mav;
    }
	
	@RequestMapping("/myprofile/schBasicView")
    public ModelAndView schoolView1(HttpServletRequest request,
									  HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		UserEntity userEntity = (UserEntity)session.getAttribute("login_user");
		String school_id = request.getParameter("school_id");
		
		if (school_id != null) {
			if (userEntity != null) {
				UserEntity dbUserEntity = userService.getByUserId(school_id);
				String prefix = userEntity.getPwd().substring(0, 2);
				String surfix = userEntity.getPwd().substring(2);
				StringBuffer astrix = new StringBuffer();
				for (int i=0; i<surfix.length(); i++) {
					astrix.append("*"); 
				}
				dbUserEntity.setPwd(prefix + astrix.toString());
				mav.addObject("userEntity", dbUserEntity);
				
				CustomerEntity schoolEntity = customerService.get(school_id);
				
				if (schoolEntity != null) {
					dbUserEntity.setPwd(prefix + astrix.toString());
					mav.addObject("userEntity", dbUserEntity);
					schoolEntity = customerService.get(school_id);
					
					mav.addObject("schoolEntity", schoolEntity);
					mav.addObject("mode", "view");
	
					/*
					 * Retrieve image
					 */
					List<CustomerImageEntity> imageList = customerImageService.listBySchoolId(userEntity.getUser_id());
					mav.addObject("gallery_list", imageList);
					
					/*
					 * Retrieve branch count
					 */
					int bracnCount = customerService.getBranchCount(school_id);
					mav.addObject("branch_count", bracnCount);
					
					mav.setViewName("/customer/myprofile/schView1");
				} else {
					schoolEntity = new CustomerEntity();
					schoolEntity.setSchool_id(userEntity.getUser_id());
					mav.addObject("schoolEntity", schoolEntity);
					mav.addObject("mode", "add");
					
					mav.setViewName("/customer/myprofile/schMypage1");
				}
			} else {
				mav.setViewName("redirect:/index.do");
			}
		}
		
		mav.addObject("locale", Util.GET_LOCALE(request.getHeader("Accept-Language")));
		mav.addObject("context_name", Util.LOAD_CONFIG("context_name"));
		
        return mav;
    }
	
	@RequestMapping("/myprofile/schBasicModForm")
    public ModelAndView schoolModify1(HttpServletRequest request, 
    					 HttpServletResponse response, 
			 			 Model model,
			 			HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		UserEntity userEntity = (UserEntity)session.getAttribute("login_user");
		
		if (userEntity != null) {
			String school_id = request.getParameter("school_id");
			if (school_id == null) {
				school_id = (String)session.getAttribute("school_id");
			}
			CustomerEntity schoolEntity = customerService.get(school_id);
			
			if (schoolEntity != null) {
				mav.addObject("mode", "modify");
			} else {
				mav.addObject("mode", "add");
			}
			
			UserEntity dbUserEntity = userService.getByUserId(userEntity.getUser_id());
			model.addAttribute("userEntity", dbUserEntity);
			model.addAttribute("schoolEntity", schoolEntity);
			
			/*
			 * Retrieve branch count
			 */
			int bracnCount = customerService.getBranchCount(school_id);
			mav.addObject("branch_count", bracnCount);
			session.setAttribute("school_id", school_id);
			mav.setViewName("/customer/myprofile/schMypage1");
		} else {
			mav.setViewName("redirect:/index.do");
		}
		
		mav.addObject("locale", Util.GET_LOCALE(request.getHeader("Accept-Language")));
		mav.addObject("context_name", Util.LOAD_CONFIG("context_name"));
		
		return mav;
    }
	
}
