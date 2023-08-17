package com.digit.course_management;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import com.digit.course_main.CrsApp;

public class Professor {
	int pid;
	String pname;
	int exp;
	static String user_name;
	static String pass1;
	int course_id;
	private static PreparedStatement pstmt;

	
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

				System.out.println("Professor Registered------------ :");

	

			}

		}

		catch (SQLException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}

		String sql1 = "insert into prequest values(?,?)";

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
	 * @param pid
	 * @param pname
	 * @param exp
	 */
	public Professor(int pid,int courseid, String pname, int exp) {
		super();
		this.pid = pid;
		this.pname = pname;
		this.exp = exp;
		this.course_id = courseid;
	}

	/**
	 * @return the pid
	 */
	public int getPid() {
		return pid;
	}

	/**
	 * @param pid the pid to set
	 */
	public void setPid(int pid) {
		this.pid = pid;
	}

	/**
	 * @return the pname
	 */
	public String getPname() {
		return pname;
	}

	/**
	 * @param pname the pname to set
	 */
	public void setPname(String pname) {
		this.pname = pname;
	}

	/**
	 * @return the exp
	 */
	public int getExp() {
		return exp;
	}

	/**
	 * @param exp the exp to set
	 */
	public void setExp(int exp) {
		this.exp = exp;
	}
	
		public static String getUser_name() {
			return user_name;
		}

		public static void setUser_name(String user_name) {
			Professor.user_name = user_name;
		}

	
		public static String getPass1() {
			return pass1;
		}
		public static void setPass1(String pass1) {
			Professor.pass1 = pass1;
		}

}
