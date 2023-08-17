package com.digit.course_managementservices;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Scanner;

import com.digit.course_main.CrsApp;
import com.digit.course_management.Courses;

public class Professor_services {
	static int sid;
	static int marks;
	static String grade;
	static String user_name;
	static String pass1;

	private static PreparedStatement pstmt;

	static String check;

	private static ResultSet resultset;

	private static Statement stmt;
	private static ResultSet resultset1;
	private static Statement stmt2;

	public static void login() {

		Scanner sc = new Scanner(System.in);

		int temp = 0;

		System.out.println("please Login");

		try {

			while (true) {

				String sql = "select * from prequest";

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

						grademarks();

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
			e.printStackTrace();
		}
	}

	public static void grademarks() {

		try {
			// creating id-->course
			HashMap<Integer, String> map = new HashMap<>();
			HashMap<Integer, String> map2 = new HashMap<>();

			String sql1 = "select cid,cname from course";
			String sqlpr = "select c_id,pname from professors";

			stmt = CrsApp.con.createStatement();
			stmt2 = CrsApp.con.createStatement();

			resultset = stmt.executeQuery(sql1);

			resultset1 = stmt2.executeQuery(sqlpr);

			while (resultset1.next() == true) {
				int cid = resultset1.getInt("c_id");
				String pname = resultset1.getString("pname");
				map2.put(cid, pname);
			}

			while (resultset.next() == true) {
				int cid = resultset.getInt("cid");
				String cname = resultset.getString("cname");
				map.put(cid, cname);

			}

// finished

			Scanner sc = new Scanner(System.in);
			System.out.println("add student marks");

			System.out.println("Enter student id");

			sid = sc.nextInt();
			// start

			// end

			System.out.println("Enter student marks");

			marks = sc.nextInt();
			System.out.println("student grade");
			grade = getGrade();
			System.out.println(grade);

			Professor_services ps = new Professor_services(sid, marks, grade);

			String sql2 = "insert into marks values(?,?,?)";

			pstmt = CrsApp.con.prepareStatement(sql2);

			pstmt.setInt(1, ps.getSid());

			pstmt.setInt(2, ps.getMarks());
			pstmt.setString(3, ps.getGrade());

			int x = pstmt.executeUpdate();

			if (x > 0) {

				String sql3 = "select Sname,c_id from student where sid=?";

				pstmt = CrsApp.con.prepareStatement(sql3);

				pstmt.setInt(1, ps.getSid());

				resultset = pstmt.executeQuery();

				while (resultset.next() == true) {
					System.out.println("Professor " + map2.get(resultset.getInt("c_id")) + " is Grading.....\n");
					System.out.println(resultset.getString("sname") + " student marks Added For Course "
							+ map.get(resultset.getInt("c_id")) + "------------ :");

				}

			}

			System.out.println("\n\t\tstudent marks Added Successfully...");

			System.out.println("\n**********************\n");

			System.out.println("Do you want to grade more student : y/n");

			check = sc.next();

			if (check.equals("y")) {

				grademarks();

			}

			else if (check.equals("n")) {

				System.out.println("Do you want to go the management : y/n");

				check = sc.next();

				if (check.equals("y")) {

					CrsApp.manage();

				}

				else if (check.equals("n")) {
					System.out.println("Look into the menu");

					Admin_service.menu();

				}

			}

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	/**
	 * @param sid
	 * @param marks
	 */
	public Professor_services(int sid, int marks, String grade) {
		super();
		this.sid = sid;
		this.marks = marks;
		this.grade = grade;
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
	 * @return the marks
	 */
	public int getMarks() {
		return marks;
	}

	/**
	 * @param marks the marks to set
	 */
	public void setMarks(int marks) {
		this.marks = marks;
	}

	/**
	 * @return the grade
	 */

	/**
	 * @param grade
	 */

	public static String getGrade() {

		if (marks >= 90) {

			return "O";

		}

		else if (marks >= 70 && marks < 90) {

			return "E";

		}

		else if (marks >= 50 && marks < 70) {

			return "A";

		}

		return "F";

	}

	/**
	 * @param grade the grade to set
	 */
	public void setGrade(String grade) {
		this.grade = grade;
	}

}
