package com.mycompany.myschool.web.util;


public class IDGenerator {
	public static String NEXT_ID(String type, String currentId) {
		String currentNum = "";
		if (currentId == null || currentId.length() == 0) {
			currentNum = "000000";
		} else {
			if (currentId.startsWith("A")) {
				currentNum = currentId.substring(4);
			} else {
				currentNum = currentId.substring(3);
			}
		}
		int nextNum = Integer.parseInt(currentNum) + 1;
		return type + CONVERT_NUMBER(nextNum);
	}
	
	private static String CONVERT_NUMBER(int num) {
		if (num < 10) {
			return "000000" + num; 
		} else if (num < 100) {
			return "00000" + num;
		} else if (num < 1000) {
			return "0000" + num;
		} else if (num < 10000) {
			return "000" + num;
		} else if (num < 100000) {
			return "00" + num;
		} else if (num < 1000000) {
			return "0" + num;
		} else {
			return "" + num;
		}
	}
}
