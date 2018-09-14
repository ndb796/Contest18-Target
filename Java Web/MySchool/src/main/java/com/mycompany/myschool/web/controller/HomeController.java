package com.mycompany.myschool.web.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mysql.jdbc.DatabaseMetaData;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "index";
	}
	
	/**
	 * DB Test 
	 * 	URL: jdbc:mysql://localhost:3306/
	 *  DB Name: myschool
	 *  User Name: root
	 *  USer PWD: mysql
	 * @param ar
	 */
	public static void main(String[] ar) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/myschool?zeroDateTimeBehavior=convertToNull";
			String user = "root";
			String pwd = "mysql";
			Connection conn = DriverManager.getConnection(url, user, pwd);
			DatabaseMetaData dmd = (DatabaseMetaData)conn.getMetaData();
			System.out.println("URL=" + dmd.getURL());
			System.out.println("User Name=" + dmd.getUserName());
			System.out.println("Driver Version=" + dmd.getDriverVersion());
			System.out.println("Database Product Name=" + dmd.getDatabaseProductName());
			System.out.println("Database Product Version=" + dmd.getDatabaseProductVersion());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
