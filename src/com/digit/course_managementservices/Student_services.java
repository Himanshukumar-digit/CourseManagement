package com.digit.course_managementservices;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Scanner;

import com.digit.course_main.CrsApp;

public class Student_services {
	static int sid;

	private static PreparedStatement pstmt;

	static String check;

	private static ResultSet resultset;

	private static Statement stmt;
	static String user_name;
	static String pass1;

	public static void login() {

		Scanner sc = new Scanner(System.in);

		int temp = 0;

		System.out.println("please Login");

		try {

			while (true) {

				String sql = "select * from srequest";

				stmt = CrsApp.con.createStatement();

				resultset = stmt.executeQuery(sql);

				temp++;
				System.out.println("\nEnter registerd user name");

				user_name = sc.next();

				System.out.println("Enter password");

				pass1 = sc.next();
				while (resultset.next() == true) {

					if (user_name.equals(resultset.getString("user_name"))

							&& pass1.equals(resultset.getString("pass1"))) {

						System.out.println("Login successful..........");

						show_marks();

						break;

					}

				}

				if (temp == 3) {

					System.out.println("User name not found");

					CrsApp.manage();

					break;

				} else {

					System.out.println("Re-enter your login details");

				}

			}

		} catch (Exception e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}

	}

	public Student_services(int sid) {

		this.sid = sid;

	}

	/**
	 * 
	 * @return the sid
	 * 
	 */

	public static int getSid() {

		return sid;
	}

	/**
	 * 
	 * @param sid the sid to set
	 * 
	 */

	public void setSid(int sid) {

		this.sid = sid;

	}

	public static void show_marks() {

		Scanner sc = new Scanner(System.in);

		try {

			System.out.println("Enter student id");

			sid = sc.nextInt();

			Student_services ps = new Student_services(sid);

			String sql1 = "select Sname from student where sid=?";

			pstmt = CrsApp.con.prepareStatement(sql1);

			pstmt.setInt(1, ps.getSid());

			resultset = pstmt.executeQuery();

			while (resultset.next() == true) {

				System.out.println("Name of the student....");

				System.out.println(resultset.getString("sname"));

			}

			String sql2 = "select *from marks where sid=?";

			pstmt = CrsApp.con.prepareStatement(sql2);

			pstmt.setInt(1, ps.getSid());

			resultset = pstmt.executeQuery();

			
			while (resultset.next() == true) {

				System.out.println("Marks of the student....");

				System.out.println(resultset.getInt("marks"));
				System.out.println("grade of the student....");

				System.out.println(resultset.getString("grade"));

			}

		} catch (Exception e) {
			e.printStackTrace();

		}
		Admin_service.menu();
	}

}