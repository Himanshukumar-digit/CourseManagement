package com.digit.course_management;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import com.digit.course_main.CrsApp;

public class Student {
	private static PreparedStatement pstmt;
	int sid;
	int courseid;
	String sname;
	String email;
	static String user_name;
	static String pass1;

	public static void Register() {

		Scanner sc = new Scanner(System.in);

		System.out.println("Kindly register yourself");

		System.out.println("\nEnter user name");

		user_name = sc.next();

		System.out.println("Set password");

		pass1 = sc.next();

		String sql = "insert into users values(?,?)";

		try {

			pstmt = CrsApp.con.prepareStatement(sql);

			pstmt.setString(1, getUser_name());

			pstmt.setString(2, getPass1());

			int x = pstmt.executeUpdate();

			if (x > 0) {

				System.out.println("Student Registered------------ :");


				

			}

		}

		catch (SQLException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}
		String sql1 = "insert into srequest values(?,?)";

		try {

			pstmt = CrsApp.con.prepareStatement(sql1);

			pstmt.setString(1, getUser_name());

			pstmt.setString(2, getPass1());

			int x = pstmt.executeUpdate();

			if (x > 0) {

				System.out.println("Request login activated:");
				

			}

		}

		catch (SQLException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}

	}

	/**
	 * @param sid
	 * @param sname
	 * @param email
	 * @param user_name
	 * @param pass1
	 */
	public Student(int sid,int course_id, String sname, String email) {
		super();
		this.sid = sid;
		this.courseid = course_id;
		this.sname = sname;
		this.email = email;

	}

	/**
	 * @return the sid
	 */
	public int getSid() {
		return sid;
	}

	/**
	 * @param sid the sid to set
	 */
	public void setSid(int sid) {
		this.sid = sid;
	}

	/**
	 * @return the sname
	 */
	public String getSname() {
		return sname;
	}

	/**
	 * @param sname the sname to set
	 */
	public void setSname(String sname) {
		this.sname = sname;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the user_name
	 */
	public static String getUser_name() {
		return user_name;
	}

	/**
	 * @param user_name the user_name to set
	 */
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	/**
	 * @return the pass1
	 */
	public static String getPass1() {
		return pass1;
	}

	/**
	 * @param pass1 the pass1 to set
	 */
	public void setPass1(String pass1) {
		this.pass1 = pass1;
	}

}
