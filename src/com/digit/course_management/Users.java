package com.digit.course_management;

import java.util.Scanner;

import com.digit.course_main.CrsApp;
import java.sql.PreparedStatement;
import java.sql.ResultSet;



public class Users {
	 static String user_name ="Admin";
	 static String pass = "Admin";
	private static PreparedStatement pstmt;
	private static ResultSet res;
	public Users(String user_name, String pass) {
		super();
		this.user_name = user_name;
		this.pass = pass;
	}
	/**
	 * @return the user_name
	 */
	public String getUser_name() {
		return user_name;
	}
	/**
	 * @param user_name the user_name to set
	 */
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	/**
	 * @return the pass
	 */
	public String getPass() {
		return pass;
	}
	/**
	 * @param pass the pass to set
	 */
	public void setPass(String pass) {
		this.pass = pass;
	}
	public static boolean login() {
		try {
			// TODO Auto-generated method stub
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter the user_name:");
			user_name=sc.next();
			System.out.println("Enter the password:");
			pass=sc.next();
			String sql = "select * from users where user_name=? and password=?";
			pstmt = CrsApp.con.prepareStatement(sql);
			pstmt.setString(1, user_name);
			pstmt.setString(2, pass);
			res = pstmt.executeQuery();
			if(user_name.equals("admin") && pass.equals("admin")) {
				return true;
			}
			else {
				return false;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	

}
