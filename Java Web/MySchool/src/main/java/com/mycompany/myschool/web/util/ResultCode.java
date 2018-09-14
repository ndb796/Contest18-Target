package com.mycompany.myschool.web.util;

import java.lang.reflect.Field;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ResultCode {
	private static Map<String, String> MESSAGE_MAP = new HashMap<String, String>();
	
	static {
		Field[] fields = ResultCode.class.getFields();
		Map<String, String> codeMap = new HashMap<String, String>();
		Map<String, String> msgMap = new HashMap<String, String>();
		
		for (Field field : fields) {
			if (!field.getName().equals("MESSAGE_MAP") && !field.getName().equals("MANAGER_MODULE_HEADER")) {
				try {
					if (field.getName().endsWith("_CODE")) {
						codeMap.put(field.getName(), field.get(new String[]{}).toString());
					} else if (field.getName().endsWith("_MSG")) {
						msgMap.put(field.getName().substring(0, field.getName().lastIndexOf("_MSG")) + "_CODE", field.get(new String[]{}).toString());
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		Iterator<String> codeIt = codeMap.keySet().iterator();
		while (codeIt.hasNext()) {
			String key = codeIt.next();
			MESSAGE_MAP.put(codeMap.get(key), msgMap.get(key));
		}
	}
	
	public static Map<String, String> GET_MESSAGE_MAP() {
		return MESSAGE_MAP;
	}
	
	/**
	 * OpenAPI execute [CODE, MESSAGE]
	 */
	public static final String OPENAPI_NOT_EXISTS_CODE 		= 	"E10001";
	public static final String OPENAPI_NOT_EXISTS_MSG 		= 	"Requested openapi is not exists in Openapi system.";
	public static final String OPENAPI_ERR_FAIL_CODE 		=	"E10002";
	public static final String OPENAPI_ERR_FAIL_MSG 		= 	"Openapi request fail.";
	public static final String OPENAPI_ERR_PARAM_CODE 		= 	"E10003";
	public static final String OPENAPI_ERR_PARAM_MSG 		= 	"Wrong parameter entered. Please check parameters.";
	
	public static final String COMMON_ZIPCODE_NOT_FOUND_CODE	= 	"E90001";
	public static final String COMMON_ZIPCODE_NOT_FOUND_MSG		= 	"Zipcode is not exists.";
	
	/**
	 * Administrator console - user
	 */
	public static final String USER_C_SUCCESS_CODE 			= 	"S10001";
	public static final String USER_C_SUCCESS_MSG 			= 	"감사합니다. 회원등록이 완료되었습니다.";
	public static final String USER_U_SUCCESS_CODE 			= 	"S10002";
	public static final String USER_U_SUCCESS_MSG 			= 	"User has been modified successfully.";
	public static final String USER_D_SUCCESS_CODE 			= 	"S10003";
	public static final String USER_D_SUCCESS_MSG 			= 	"User has been removed successfully.";
	public static final String USER_STATUS_APPROVED_CODE 	= 	"S10004";
	public static final String USER_STATUS_APPROVED_MSG 	= 	"User's status has been updated to approve successfully.";
	public static final String USER_STATUS_REJECTED_CODE 	= 	"S10005";
	public static final String USER_STATUS_REJECTED_MSG 	= 	"User's status has been updated to reject successfully.";
	public static final String USER_LOGOUT_CODE 			= 	"S10006";
	public static final String USER_LOGOUT_MSG 				= 	"로그아웃 되었습니다.";
	public static final String USER_NOTAPPROVED_CODE 		= 	"S10007";
	public static final String USER_NOTAPPROVED_MSG 		= 	"Your ID is not approved yet.";
	public static final String USER_ERR_CREATE_CODE 		= 	"E10001";
	public static final String USER_ERR_CREATE_MSG 			= 	"죄송합니다. 시스템장애로 인해 회원등록에 실패하였습니다.";
	public static final String USER_ERR_UPDATE_CODE 		= 	"E10002";
	public static final String USER_ERR_UPDATE_MSG 			= 	"Failed to modify the user. [ERROR CODE={0}]";
	public static final String USER_ERR_DELETE_CODE 		= 	"E10003";
	public static final String USER_ERR_DELETE_MSG 			= 	"Failed to remove the user. [ERROR CODE={0}]";
	public static final String USER_ERR_STATUS_CODE 		= 	"E10004";
	public static final String USER_ERR_STATUS_MSG 			= 	"Failed to update of the user status. [ERROR CODE={0}]";
	public static final String USER_ERR_ALREADY_CODE 		= 	"E10005";
	public static final String USER_ERR_ALREADY_MSG			= 	"Sorry, entered e-mail has been already joined.";
	public static final String USER_ERR_LOGIN_CODE 			= 	"E10006";
	public static final String USER_ERR_LOGIN_MSG 			= 	"입력하신 ID가 존재하지 않습니다.";
	public static final String USER_ERR_PWD_CODE 			= 	"E10007";
	public static final String USER_ERR_PWD_MSG 			= 	"Please, check your password.";
	public static final String USER_ERR_NOTLOGIN_CODE 		= 	"E10008";
	public static final String USER_ERR_NOTLOGIN_MSG 		= 	"Sorry, you have to login for our service.";
	public static final String USER_ERR_DUPLICATE_CODE 		= 	"E10009";
//	public static final String USER_ERR_DUPLICATE_MSG 		= 	"Sorry, your email was joined already. Try to login with your email.";
	public static final String USER_ERR_DUPLICATE_MSG 		= 	"입력한 이메일주소는 이미 등록되어 있습니다. 이메일주소를 확인해 주십시오.";
	
	/**
	 * Administrator console - group
	 */
	public static final String GROUP_C_SUCCESS_CODE 		= 	"S20001";
	public static final String GROUP_C_SUCCESS_MSG 			= 	"Group has been added successfully.";
	public static final String GROUP_U_SUCCESS_CODE 		= 	"S20002";
	public static final String GROUP_U_SUCCESS_MSG 			= 	"Group has been modified successfully.";
	public static final String GROUP_D_SUCCESS_CODE 		= 	"S20003";
	public static final String GROUP_D_SUCCESS_MSG 			= 	"Group has been removed successfully.";
	public static final String MYGROUP_SUCCESS_CODE 		= 	"S20004";
	public static final String MYGROUP_SUCCESS_MSG 			= 	"My Group has been updated successfully.";
	public static final String MYGROUP_REQ_SUCCESS_CODE 	= 	"S20005";
	public static final String MYGROUP_REQ_SUCCESS_MSG 		= 	"My Group has been requested successfully.";
	public static final String GROUP_ERR_CREATE_CODE 		= 	"E20001";
	public static final String GROUP_ERR_CREATE_MSG 		= 	"Failed to add the group. [ERROR CODE={0}]";
	public static final String GROUP_ERR_UPDATE_CODE 		= 	"E20002";
	public static final String GROUP_ERR_UPDATE_MSG 		= 	"Failed to modify the group. [ERROR CODE={0}]";
	public static final String GROUP_ERR_DELETE_CODE 		= 	"E20003";
	public static final String GROUP_ERR_DELETE_MSG 		= 	"Failed to remove the group. [ERROR CODE={0}]";
	public static final String MYGROUP_ERR_CODE 			= 	"E20004";
	public static final String MYGROUP_ERR_MSG 				= 	"Failed to update the group. [ERROR CODE={0}]";
	public static final String MYGROUP_ERR_REQ_CODE 		= 	"E20005";
	public static final String MYGROUP_ERR_REQ_MSG 			= 	"Failed to request selected user. [ERROR CODE={0}]";
	
	/**
	 * School(Customer) information
	 */
	public static final String SCHOOL_C_SUCCESS_CODE 		= 	"S50001";
	public static final String SCHOOL_C_SUCCESS_MSG 		= 	"학교정보가 성공적으로 추가되었습니다.";
	public static final String SCHOOL_U_SUCCESS_CODE 		= 	"S50002";
	public static final String SCHOOL_U_SUCCESS_MSG 		= 	"학교정보가 성공적으로 수정되었습니다.";
	public static final String SCHOOL_D_SUCCESS_CODE 		= 	"S50003";
	public static final String SCHOOL_D_SUCCESS_MSG 		= 	"학교정보가 성공적으로 삭제되었습니다.";
	public static final String CUST_ERR_CODE				=	"E50001";
	public static final String CUST_ERR_MSG					= 	"Sorry, selected item is not exists anymore.";
	public static final String SCHOOL_ERR_CREATE_CODE 		= 	"E50002";
	public static final String SCHOOL_ERR_CREATE_MSG 		= 	"에러가 발생하였습니다. 학교정보가 추가되지 않았습니다.";
	public static final String SCHOOL_ERR_UPDATE_CODE 		= 	"E50003";
	public static final String SCHOOL_ERR_UPDATE_MSG 		= 	"에러가 발생하였습니다. 학교정보가 수정되지 않았습니다.";
	public static final String SCHOOL_ERR_DELETE_CODE 		= 	"E50003";
	public static final String SCHOOL_ERR_DELETE_MSG 		= 	"에러가 발생하였습니다. 학교정보가 삭제되지 않았습니다.";
	
	/**
	 * Administrator console - board
	 */
	public static final String BOARD_C_SUCCESS_CODE 		= 	"S30001";
	public static final String BOARD_C_SUCCESS_MSG 			= 	"Board has been added successfully.";
	public static final String BOARD_U_SUCCESS_CODE 		= 	"S30002";
	public static final String BOARD_U_SUCCESS_MSG 			= 	"Board has been modified successfully.";
	public static final String BOARD_D_SUCCESS_CODE 		= 	"S30003";
	public static final String BOARD_D_SUCCESS_MSG 			= 	"Board has been removed successfully.";
	public static final String COMMENT_SUCCESS_CODE 		= 	"S30004";
	public static final String COMMENT_SUCCESS_MSG 			= 	"Comment has been updated successfully.";
	public static final String EMOTION_SUCCESS_CODE 		= 	"S30005";
	public static final String EMOTION_SUCCESS_MSG 			= 	"Emotion has been updated successfully.";
	public static final String BOARD_ERR_CREATE_CODE 		= 	"E30001";
	public static final String BOARD_ERR_CREATE_MSG 		= 	"Failed to add the board. [ERROR CODE={0}]";
	public static final String BOARD_ERR_UPDATE_CODE 		= 	"E30002";
	public static final String BOARD_ERR_UPDATE_MSG 		= 	"Failed to modify the board. [ERROR CODE={0}]";
	public static final String BOARD_ERR_DELETE_CODE 		= 	"E30003";
	public static final String BOARD_ERR_DELETE_MSG 		= 	"Failed to remove the board. [ERROR CODE={0}]";
	public static final String COMMENT_ERR_CODE 			= 	"E30004";
	public static final String COMMENT_ERR_MSG 				= 	"Failed to update the board. [ERROR CODE={0}]";
	public static final String EMOTION_ERR_CODE 			= 	"E30005";
	public static final String EMOTION_ERR_MSG 				= 	"Failed to update the board. [ERROR CODE={0}]";
	
	/**
	 * Administrator console - event
	 */
	public static final String EVENT_C_SUCCESS_CODE 		= 	"S40001";
	public static final String EVENT_C_SUCCESS_MSG 			= 	"Event has been added successfully.";
	public static final String EVENT_U_SUCCESS_CODE 		= 	"S40002";
	public static final String EVENT_U_SUCCESS_MSG 			= 	"Event has been modified successfully.";
	public static final String EVENT_D_SUCCESS_CODE 		= 	"S40003";
	public static final String EVENT_D_SUCCESS_MSG 			= 	"Event has been removed successfully.";
	public static final String EVENT_E_SUCCESS_CODE 		= 	"S40004";
	public static final String EVENT_E_SUCCESS_MSG 			= 	"Event registration has been added successfully.";
	public static final String EVENT_ERR_CREATE_CODE 		= 	"E40001";
	public static final String EVENT_ERR_CREATE_MSG 		= 	"Failed to add the event. [ERROR CODE={0}]";
	public static final String EVENT_ERR_UPDATE_CODE 		= 	"E40002";
	public static final String EVENT_ERR_UPDATE_MSG 		= 	"Failed to modify the event. [ERROR CODE={0}]";
	public static final String EVENT_ERR_DELETE_CODE 		= 	"E40003";
	public static final String EVENT_ERR_DELETE_MSG 		= 	"Failed to remove the event. [ERROR CODE={0}]";
	public static final String EVENT_ERR_EVENT_CODE 		= 	"E40004";
	public static final String EVENT_ERR_EVENT_MSG 			= 	"You have registed the event already.";
	
	/**
	 * favorite
	 */
	public static final String FAVOR_C_SUCCESS_CODE 		= 	"S60001";
	public static final String FAVOR_C_SUCCESS_MSG 			= 	"즐겨찾기에 추가 되었습니다.";
	public static final String FAVOR_D_SUCCESS_CODE 		= 	"S60003";
	public static final String FAVOR_D_SUCCESS_MSG 			= 	"즐겨찾기에서 삭제 되었습니다.";
	public static final String FAVOR_ERR_CREATE_CODE 		= 	"E60001";
	public static final String FAVOR_ERR_CREATE_MSG 		= 	"Failed to add the favorite. [ERROR CODE={0}]";
	public static final String FAVOR_ERR_DELETE_CODE 		= 	"E60003";
	public static final String FAVOR_ERR_DELETE_MSG 		= 	"Failed to remove the favorite. [ERROR CODE={0}]";
	
	public static String GET_MESSAGE(String code) {
		String result = MESSAGE_MAP.get(code);
		
		return result.indexOf("{0}") > 0 ? MessageFormat.format(result, code) : result;
	}
}
