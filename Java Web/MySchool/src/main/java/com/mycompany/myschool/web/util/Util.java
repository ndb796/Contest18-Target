package com.mycompany.myschool.web.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.SortedMap;
import java.util.StringTokenizer;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.mycompany.myschool.web.entity.base.BaseEntity;
import com.mycompany.myschool.web.entity.base.UserEntity;
import com.mycompany.myschool.web.entity.student.StudentSub2Entity;
import com.mycompany.myschool.web.entity.vo.BlogVo;

public class Util {
	static ResourceBundle BUNDLE = null;
	
	static {
		BUNDLE = ResourceBundle.getBundle("/conf/config");
	}
	
	public static void DISPLAY_MAP2STR(Map<String, Object> map) {
		Iterator<String> it = map.keySet().iterator();
		System.out.println("============== Map information start ===============");
		while (it.hasNext()) {
			String key = it.next();
			Object value = map.get(key);
			System.out.println(key + "=" + value);
		}
		System.out.println("============== Map information end ===============");
	}
	
	public static void CHECK_SPECIFIC_ORDER(StudentSub2Entity specificEntity) {
		List<Integer> ordLst = Arrays.asList(0, 1, 2, 3, 4, 5);
		List<String> noexistLst = new ArrayList<String>();
		
		for (Integer i : ordLst) {
			System.out.println(i);
		}
		System.out.println("specificEntity.getSchool_ord()=" + specificEntity.getSchool_ord());
		if (ordLst.contains(specificEntity.getSchool_ord())) {
			ordLst.remove(specificEntity.getSchool_ord());
		} else {
			noexistLst.add("school_ord");
		}
		
		if (ordLst.contains(specificEntity.getLocation_ord())) {
			ordLst.remove(specificEntity.getLocation_ord());
		} else {
			noexistLst.add("location_ord");
		}
		
		if (ordLst.contains(specificEntity.getSubject_ord())) {
			ordLst.remove(specificEntity.getSubject_ord());
		} else {
			noexistLst.add("subject_ord");
		}
		
		if (ordLst.contains(specificEntity.getJob_ord())) {
			ordLst.remove(specificEntity.getJob_ord());
		} else {
			noexistLst.add("job_ord");
		}
		
		if (ordLst.contains(specificEntity.getField_ord())) {
			ordLst.remove(specificEntity.getField_ord());
		} else {
			noexistLst.add("field_ord");
		}
		
		for (Integer i : ordLst) {
			if (noexistLst.get(i).equals("school_ord")) {
				specificEntity.setSchool_ord(i);
			} else if (noexistLst.get(i).equals("location_ord")) {
				specificEntity.setLocation_ord(i);
			} else if (noexistLst.get(i).equals("subject_ord")) {
				specificEntity.setSubject_ord(i);
			} else if (noexistLst.get(i).equals("job_ord")) {
				specificEntity.setJob_ord(i);
			} else if (noexistLst.get(i).equals("field_ord")) {
				specificEntity.setField_ord(i);
			}
		}
	}
	
	public static final String LOAD_CONFIG(String name) {
		return BUNDLE.getString(name);
	}
	
	public static final String BYTEARR2STR(byte[] arr) {
		String str = "";
		for (int i=0; i<arr.length; i++) {
			str += arr[i] + "";
		}
		
		return str;
	}
	
	public static final byte[] STR2BYTEARR(String str) {
		byte[] bytes = new byte[str.length()];
		
		for (int i=0; i<str.length(); i++) {
			bytes[i] = Byte.parseByte(Character.toString(str.charAt(i)));
		}
		
		return bytes;
	}
	
	public static SortedMap<String, String> GET_BIRTH_Y_MAP(int startValue, int endValue) {
		SortedMap<String, String> birth_y_map = new TreeMap<String, String>();
		
		for (int i=startValue; i<=endValue; i++) {
			birth_y_map.put(Integer.toString(i), Integer.toString(i));
		}
		
		return birth_y_map;
	}
	
	public static SortedMap<Integer, String> GET_BIRTH_MD_MAP(int endValue) {
		SortedMap<Integer, String> birth_md_map = new TreeMap<Integer, String>();
		
		for (int i=1; i<=endValue; i++) {
			birth_md_map.put(i, (i < 10 ? "0" + i : "" + i));
		}
		
		return birth_md_map;
	}
	
	public static String GET_LOCALE(String clientLocale) {
		Locale objLocale = Locale.getDefault();
		if(clientLocale != null) {
			StringTokenizer st = new StringTokenizer(clientLocale, ",");
			String locale = st.nextToken();
			String[] locales = locale.split("-");
			if(locales != null) {
				if(locales.length == 2) {
					objLocale = new Locale(locales[0], locales[1]);	
				} else {
					objLocale = new Locale(locales[0]);
				}
				
			} 
		}
		
		return objLocale.toString().substring(0, 2);
	}
	
	public static String GET_URI(HttpServletRequest request) {
		//http://localhost:8080/Deviewstory
		return "http://127.0.0.1:" + request.getLocalPort();
	}
	
	public static BaseEntity GET_SEARCH_PARAMETAERS(HttpServletRequest request, String className) {
		BaseEntity entity = null;
		if (className.endsWith("UserEntity")) {
			entity = new UserEntity();
		} 
		
		Enumeration<?> em = request.getParameterNames();
		while (em.hasMoreElements()) {
			String name = (String)em.nextElement();
			String value = request.getParameter(name);
			
			if ("page".equals(name)) {
				entity.setPage(value != null && value.length() > 0 ? Integer.parseInt(value) : 1);
			} else if ("rows".equals(name)) {
				entity.setRows(value != null && value.length() > 0 ? Integer.parseInt(value) : 1);
			} else if ("sidx".equals(name)) {
				entity.setSidx(value != null && value.length() > 0 ? value : null);
			} else if ("sord".equals(name)) {
				entity.setSord(value != null && value.length() > 0 ? value : null);
			} else if ("_search".equals(name)) {
				entity.set_search(value != null && value.length() > 0 ? value : null);
			} else if ("searchString".equals(name)) {
				entity.setSearchString(value != null && value.length() > 0 ? value : null);
			} else if ("searchOper".equals(name)) {
				entity.setSearchOper(value != null && value.length() > 0 ? value : null);
			} else if ("searchField".equals(name)) {
				entity.setSearchField(value != null && value.length() > 0 ? value : null);
			}
		}
		
		return entity;
	}
	
	public static String[] WEEKLY_DAYS(int curYear, int curMonth, int curWeek) {
		List<String> daysList = new ArrayList<String>();
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.WEEK_OF_YEAR, curWeek);
		cal.set(Calendar.DAY_OF_WEEK, 1);
		
		int endDayOfBeforeMonth = Constants.END_OF_MONTH[curMonth - 1];
		
		// Leap year
		if (IS_LEAP_YEAR(curYear)) {
			endDayOfBeforeMonth++; 
		}
		
		int startDayOfWeek = cal.get(Calendar.DAY_OF_MONTH);
		if (curMonth != cal.get(Calendar.MONTH)) {
			for (int i=1; i<=7; i++) {
				if (startDayOfWeek > endDayOfBeforeMonth) {
					startDayOfWeek = 1;
				}
				
				daysList.add("" + startDayOfWeek++);
			}
		} else {
			for (int i=0; i<7; i++) {
				daysList.add("" + startDayOfWeek++);
			}
		}
		
		System.out.println(daysList);
		
		String[] result = new String[daysList.size()];
		
		return daysList.toArray(result);
	}
	
	public static String[] MONTHLY_DAYS(int curYear, int curMonth) {
		List<String> daysList = new ArrayList<String>();
		Calendar cal = Calendar.getInstance();
		cal.set(curYear, curMonth, 1);
		int endDayOfMonth = Constants.END_OF_MONTH[curMonth];
		
		// Leap year
		if (IS_LEAP_YEAR(curYear)) {
			endDayOfMonth++; 
		}
		
		int startDateOfMonth = cal.get(Calendar.DAY_OF_WEEK);
		// before month
		if (startDateOfMonth > 1) {
			int startDay = Constants.END_OF_MONTH[curMonth] - startDateOfMonth + 1;
			for (int i=0; i<startDateOfMonth - 1; i++) {
				daysList.add("<font color='#cccccc'>" + ++startDay + "</font>");
			}
		}
		
		// cuurent month
		for (int curDayOfMonth=1; curDayOfMonth<=endDayOfMonth; curDayOfMonth++) {
			daysList.add("" + curDayOfMonth);
		}
		
		// next month
		cal.set(curYear,  curMonth, endDayOfMonth);
		int endDateOfMonth = cal.get(Calendar.DAY_OF_WEEK);
		if (endDateOfMonth < 7) {
			for (int endDay=1; endDay<=(7 - endDateOfMonth); endDay++) {
				daysList.add("<font color='#cccccc'>" + endDay + "</font>");
			}
		}
		
		String[] result = new String[daysList.size()];
		
		return daysList.toArray(result);
	}
	
	public static boolean IS_LEAP_YEAR(int year) {
		GregorianCalendar gCal = (GregorianCalendar)GregorianCalendar.getInstance();
		if (gCal.isLeapYear(year)) {
			return true;
		} else {
			return false;
		}
		
	}
	
	public static String[] GET_GOOGLE_MAP(String mapInfo) {
		String[] result = {"35.6205447", "139.74928980000004"};  // -> 회사 
		
		if (mapInfo == null) {
			return result;
		} else {
			String[] maps = mapInfo.split(",");
			if (maps.length != 2) {
				return result;
			} else {
				result[0] = maps[0];
				result[1] = maps[1];
			}
			
			return result;
		}
	}
	
	public static String[] days = {"", "일", "월", "화", "수", "목", "금", "토"};
	
	public static String GET_7DAYS_FORMAT() {
		StringBuffer result = new StringBuffer();
		for (int i=6; i>=0; i--) {
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.DATE, cal.get(Calendar.DATE) - i);
			result.append("'" + (cal.get(Calendar.MONTH) + 1) + "월 ");
			result.append(cal.get(Calendar.DATE) + "일");
			result.append("(" + days[cal.get(Calendar.DAY_OF_WEEK)] + ")'");
			if (i > 0) {
				result.append(",");
			}
		}
		
		return result.toString();
	}
	
	public static String[] GET_7DAYS_UNFORMAT() {
		String[] result = new String[7];
		int idx = 0;
		for (int i=6; i>=0; i--) {
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.DATE, cal.get(Calendar.DATE) - i);
			int month = cal.get(Calendar.MONTH) + 1;
			int date = cal.get(Calendar.DATE);
			result[idx++] = (month < 10 ? "0" + month : "" + month) + (date < 10 ? "0" + date : "" + date);
		}
		
		return result;
	}
	
	public static String GET_TODAY_STR() {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int date = cal.get(Calendar.DATE);
		
		return "" + year + (month < 10 ? "0" + month : month) +  (date < 10 ? "0" + date : date);
	}
	
	public static void CHECK_GENDER_IN_BLOG(List<BlogVo> blogList) {
		for (BlogVo blog : blogList) {
			blog.setGender_str(CONVERT_GENDER(blog.getGender()));
			
			blog.setCurrent_grade(CONVERT_CUR_TYPE(blog.getCurrent_type()));
		}
	}
	
	public static String CONVERT_GENDER(String gender) {
		if (gender.equals("M")) {
			return "boy";
		} else if (gender.equals("F")) {
			return "girl";
		} else if (gender.equals("C")) {
			return"children";
		} else {
			return "";
		}
	}
	
	public static String CONVERT_CUR_TYPE(int current_type) {
		String rtn = null;
		switch (current_type) {
			case 0:
				rtn = "유치원";
				break;
			case 1:
				rtn = "초";
				break;
			case 2:
				rtn = "중";
				break;
			case 3:
				rtn = "고";
				break;
			case 4:
				rtn = "대";
				break;
			default:
				rtn = "-";
				break;
		}
		
		return rtn;
	}
	
	public static String ENCODE(String str) {
		if (str != null) {
			try {
				return new String(str.getBytes("8859_1"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				return str;
			}
		} else {
			return "";
		}
	}
	
	public static String CHK_NULL(String str) {
		if (str == null || str.isEmpty()) {
			return "";
		} else {
			return str;
		}
	}
	
	public static String DISP_PAGES(int totalRows, int cur_page) {
		int pageGroup = 5;
		int rowsPerPage = 10;
		
		int divide = cur_page / pageGroup;
		int mok = cur_page % pageGroup;
		
		int startOfGroup = pageGroup * (divide + (mok == 0 ? -1 : 0)) + 1;
		int totalPages = (totalRows / rowsPerPage) + (totalRows % rowsPerPage > 0 ? 1 : 0);
		
		StringBuffer sb = new StringBuffer();
		if (startOfGroup > pageGroup) {
			sb.append("<<  ");
		}
		if (startOfGroup - 1 > 0) {
			sb.append("< ");
		}
		for (int i=0; i<pageGroup; i++) {
			if (startOfGroup > totalPages) {
				break;
			}
			
			sb.append(startOfGroup++ + " ");
		}
		if (startOfGroup <= totalPages) {
			sb.append(" >");
		}
		if (startOfGroup <= totalPages) {
			sb.append("  >>");
		}
		
		return sb.toString();
	}
	
	public static String PATH_FILTER(String input) {
//		return input.replaceAll("..", "").replaceAll("[/\\\\]","");
		return input;
	}
	
	public static String GET_TAG_VALUE(String sTag, Element eElement) {
	    NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();
        Node nValue = (Node) nlList.item(0);
	    return nValue.getNodeValue();
	}
}
