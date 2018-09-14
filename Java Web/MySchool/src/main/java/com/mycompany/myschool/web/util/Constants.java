package com.mycompany.myschool.web.util;

public class Constants {
	/**
	 * Recommend count
	 */
	public static final int BASE_RCMMD_COUNT = 10;
	
   /**
    * Service type
    */
   public static final String SRVC_TYPE_STD = "STD";	// Student
   public static final String SRVC_TYPE_GRP = "GRP";	// Group
   public static final String SRVC_TYPE_BRD = "BRD";	// Board
   public static final String SRVC_TYPE_CMT = "CMT";	// Comment
   public static final String SRVC_TYPE_EMT = "EMT";	// Emotion
   public static final String SRVC_TYPE_MED = "MED";	// Media
   public static final String SRVC_TYPE_EVT = "EVT";	// Event
   public static final String SRVC_TYPE_SCH = "SCH";	// School
   public static final String SRVC_TYPE_BRC = "BRN";	// School Branch
   public static final String SRVC_TYPE_CUS = "CUS";	// Current school
   public static final String SRVC_TYPE_FAV = "FAV";	// Favorite
   public static final String SRVC_TYPE_RCM = "RCM";	// Recommend
   
   /**
    * My Group Status
    */
   public static final int MYSTUDENT_GRP_STATUS_REQUEST = 0;
   public static final int MYSTUDENT_GRP_STATUS_ACCEPT = 1;
   public static final int MYSTUDENT_GRP_STATUS_REJECT = 2;
   public static final int MYSTUDENT_GRP_STATUS_GROUP = 8;
   public static final int MYSTUDENT_GRP_STATUS_NO_REQ = 9;
   
   /**
    * My Student Command
    */
   public static final String MYSTUDENT_CMD_STATUS_MODIFY = "mystudent_cmd_status_modify";
   public static final String MYSTUDENT_CMD_GROUP_MODIFY = "mystudent_cmd_group_modify";
   public static final String MYSTUDENT_CMD_ACCEPT_REQUEST = "mystudent_cmd_accept_request";
   
   /**
    * Code master
    */
   public static final String CODE_KEYWORD = "KEYWORD";
   
   /**
    * User Join Type
    */
   public static final int USER_TYPE_GG = 1;		// Google user
   public static final int USER_TYPE_FB = 2;		// Facebook user
   public static final int USER_TYPE_TW = 3;		// Twitter user
   public static final int USER_TYPE_WEB = 4;		// Web user
   public static final int USER_TYPE_ADMIN = 9;		// Administrator
   
   /**
    * School Paid Type
    */
   public static final int SCHO_PAID_TYPE_FREE = 0;		// Free
   public static final int SCHO_PAID_TYPE_PAID = 1;		// Paid
   public static final int SCHO_PAID_TYPE_SPON = 2;		// Sponsor
   public static final int SCHO_PAID_TYPE_PROM = 3;		// Promotion
   
   /**
    * User Member Type
    */
   public static final int USER_MEM_STUDENT = 1;	// Student type
   public static final int USER_MEM_SCHOOL = 2;		// School type
   public static final int USER_MEM_BRANCH = 3;		// Branch type
   public static final int USER_MEM_ADMIN = 9;		// Administrator type
   public static final int USER_MEM_EMPTY = -1;		// Not exists Member type
   
   public static final String NAV_MENU_1 = "MYPR";
   public static final String NAV_MENU_2 = "SCHL";
   public static final String NAV_MENU_3 = "SCHE";
   public static final String NAV_MENU_4 = "STAT";
   public static final String NAV_MENU_5 = "SNS";
   public static final String NAV_MENU_6 = "NEWS";
   
   /**
    * School recommend type
    */
   public static final int STD_RCMMD_CATE_ALL = -1;		// ALL
   public static final int STD_RCMMD_CATE_E = 1;		// Elementary school
   public static final int STD_RCMMD_CATE_M = 2;		// Middle school
   public static final int STD_RCMMD_CATE_H = 3;		// High school
   public static final int STD_RCMMD_CATE_U = 4;		// University
   public static final int STD_RCMMD_CATE_C = 5;		// College
   public static final int STD_RCMMD_CATE_F = 6;		// Free school
   public static final int STD_RCMMD_CATE_G = 7;		// Graduate
   public static final int STD_RCMMD_CATE_A = 8;		// Abroad
   
   public static final String[] SCHOOL_TYPE_STR = {"", "초등학교", "중학교", "고등학교", "대학교", "전문학교", "프리스쿨", "대학원", "해외"};		// Abroad
   
   /**
    * Recommend Type
    */
   public static final String RCMMD_TYPE_SCH = "SCH";
   public static final String RCMMD_TYPE_LOC = "LOC";
   public static final String RCMMD_TYPE_SBJ = "SBJ";
   public static final String RCMMD_TYPE_JOB = "JOB";
   public static final String RCMMD_TYPE_FLD = "FLD";
   
   /**
    * Consult Type
    */
   public static final String CONSULT_TYPE_STU = "1";
   public static final String CONSULT_TYPE_SCH = "2";
   
   /**
    * Consult Status 
    */
   public static final int CONST_STATUS_NOT = 0;
   public static final int CONST_STATUS_INIT = 1;	// first request (school -> student, greeting) 
   public static final int CONST_STATUS_REQ = 2;	// student -> school
   public static final int CONST_STATUS_RES = 3;	// school -> student	
   public static final String[] CONST_STATUS_MSG = {"-", "Greeting", "Request", "Response"};
   
   /**
    * User reg status 
    * 1: 메일정보, 2: 기본정보, 3: 학생(성적정보), 학교(학교상세정보),  4: 학생(특수정보), 학교(담당자정보) 5: 지불정보, 6: 기타정보(예약)
    */
   public static final byte[] USER_STATUS = {0,0,0,0,0,0};
   public static final int USER_STATUS_OFF = 0;
   public static final int USER_STATUS_ON = 1;
   public static final int USER_STATUS_IDX_MAIL = 0;
   public static final int USER_STATUS_IDX_BASC = 1;
   public static final int USER_STATUS_IDX_EDUC = 2;		// student: education, school: detail info
   public static final int USER_STATUS_IDX_SPEC = 3;		// student: specific, school: charger info
   public static final int USER_STATUS_IDX_PAID = 4;
   public static final int USER_STATUS_IDX_ETC = 5;
   
   /**
    * Schedule type
    */
   public static final int SCHE_TYPE_PUBLIC = 1;
   public static final int SCHE_TYPE_PRIVACY = 2;
   
   /**
    * News & Events Type
    */
   public static final int NEWS_TYPE_NEWS = 1;
   public static final int NEWS_TYPE_EVNTS = 2;
   
   /**
    * Blog type
    */
   public static final int BLOG_TYPE_STD = 1;
   public static final int BLOG_TYPE_SCH = 2;
   public static final int BLOG_TYPE_COM = 3;
   public static final int BLOG_SHOW = 1;
   public static final int BLOG_NOTSHOW = 0;
   /**
	 * User status
	 */
   public static final String USER_OP_APPROVE = "approve";
   public static final String USER_OP_REJECT = "reject";
   public static final String USER_OP_DELETE = "delete";
   
   public static final String[] USER_STATUS_TITLE = {"Requested", "Approved", "Rejected"};
   public static final int USER_STATUS_REQUESTED = 0;
   public static final int USER_STATUS_APPROVED = 1;
   public static final int USER_STATUS_REJECTED = 2;
   
   public static final int BLOGROWS_OF_INDEX = 9;
   public static final int BLOGROWS_OF_MAIN = 10;
   
   /**
    * Schedule
    */
   public static final int[] END_OF_MONTH = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
   public static final String[] WEEKDAYS_ja = {"日", "月", "火", "水", "木", "金", "土"};
   public static final String[] WEEKDAYS_ko = {"일", "월", "화", "수", "목", "금", "토"};
   public static final String[] WEEKDAYS_en = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
   
   /**
    *　도
    */
   public static final String[] JPN_LOCATION = {"경기도", "강원도", "경상남도", "경상북도", 
											   "전라남도", "전라북도", "충청남도", "충청북도",  
											   "제주도"};
}
