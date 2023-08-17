 package com.digit.course_managementservices;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import com.digit.course_main.CrsApp;
import com.digit.course_management.Courses;
import com.digit.course_management.Professor;
import com.digit.course_management.Student;

public class Admin_service {
	public static int n;
	private static PreparedStatement pstmt;
	private static Statement stmt;
	private static ResultSet resultset;

	public static void menu() { 
		// TODO Auto-generated method stub
		System.out.println("Select Option:");
		System.out.println("1. Add course\n" + "2. Add Student\n" + "3. Add Professor\n" + "4. Remove Course\n"
				+ "5. Remove Professor\n" + "6. Remove Student\n" + "7. View All Students\n" + "8. View All Courses\n"
				+ "9. View All Professors\n" + "10. View All Users\n" + "11. management\n" + "0. Exit\n");
		System.out.println("Enter your choice");
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		switch (n) {
		case 1: {
			addCourse();
			break;

		}
		case 2: {
			addStudent();
			break;
		}
		case 3: {
			addProfessor();
			break;
		}
		case 4: {
			removeCourse();
			break;
		}
		case 5: {
			removeProfessor();
			break;
		}
		case 6: {
			removeStudent();
			break;
		}
		case 7: {
			viewAllStudent();
			break;
		}
		case 8: {
			viewCourse();
			break;
		}
		case 9: {
			viewProfessor();
			break;
		}
		case 10: {
			viewUsers();
			break;
		}
		case 11: {
			CrsApp.manage();
			break;
		}
		case 0: {
			System.exit(0);
		}
		}
	}

	public static void viewUsers() {
		// TODO Auto-generated method stub
		try {

			String sql = "select * from users";

			stmt = CrsApp.con.createStatement();

			resultset = stmt.executeQuery(sql);

			System.out.println("User Details below........... ");

			while (resultset.next() == true) {
				System.out.println("User's Id");

				System.out.println(resultset.getString(1));
				System.out.println("Users Passwords");

				System.out.println(resultset.getString(2));

				System.out.println("--------");

			}

		} catch (Exception e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}

		menu();

	}

	public static void viewCourse() {
		// TODO Auto-generated method stub
		try {

			String sql = "select * from course";

			stmt = CrsApp.con.createStatement();

			resultset = stmt.executeQuery(sql);

			System.out.println("Course Details below are:-........... ");

			while (resultset.next() == true) {
				System.out.println("Couses id");
				System.out.println(resultset.getInt(1));
				System.out.println("Course name");
				System.out.println(resultset.getString(2));
				System.out.println("Courses fees");

				System.out.println(resultset.getString(3));
				System.out.println("Courses duration");

				System.out.println(resultset.getString(4));

				System.out.println("--------");

			}

		} catch (Exception e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}

		menu();

	}

	public static void viewProfessor() {
		// TODO Auto-generated method stub
		try {

			String sql = "select * from professors";

			stmt = CrsApp.con.createStatement();

			resultset = stmt.executeQuery(sql);

			System.out.println("Professors Details below are:-....... ");

			while (resultset.next() == true) {
				System.out.println("Professors Id");
				System.out.println(resultset.getInt(1));
				System.out.println("Professors name");
				System.out.println(resultset.getString(2));
				System.out.println("Professors Experience");
				System.out.println(resultset.getString(3));

				System.out.println("--------");

			}

		} catch (Exception e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}

		menu();

	}

	public static void addCourse() {
		try {
			int x;
			Scanner sc = new Scanner(System.in);
			System.out.println("add course");

			System.out.println("How many course do you want");
			n = sc.nextInt();
			for (int i = 0; i < n; i++) {
				System.out.println("course" + (i + 1));
				System.out.println("Enter the course id");
				int cid = sc.nextInt();
				System.out.println("Enter the course name");
				String cname = sc.next();
				System.out.println("Enter the fees");
				int fees = sc.nextInt();
				System.out.println("Enter the course duration");
				int dur_months = sc.nextInt();
				Courses c = new Courses(cid, cname, fees, dur_months);
				String sql = "insert into course values(?,?,?,?)";
				pstmt = CrsApp.con.prepareStatement(sql);
				pstmt.setInt(1, c.getCid());
				pstmt.setString(2, c.getCname());
				pstmt.setInt(3, c.getFees());
				pstmt.setInt(4, c.getDur_months());

				x = pstmt.executeUpdate();
				if (x > 0) {
					System.out.println("Course Added is :" + c.getCname());
					System.out.println("----*----");

				}
			}
			System.out.println("Course Added sucessfully");
			System.out.println("----*-----");
			menu();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void removeCourse() {

		try {

			String sql = "delete from student where cid=?";

			pstmt = CrsApp.con.prepareStatement(sql);
			Scanner sc = new Scanner(System.in);

			System.out.println("How many course do you want to remove");

			n = sc.nextInt();

			for (int i = 0; i < n; i++) {

				System.out.println("enter course id to be removed");

				pstmt.setInt(1, sc.nextInt());

				int x = pstmt.executeUpdate();

				if (x > 0) {

					System.out.println((i + 1) + " Student removed");

				}

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		if (n == 0) {
			System.out.println((n) + " Course removed");
			menu();

		} else {

			System.out.println("\n\t\tCourse removed Successfully...");

			System.out.println("\n****************************************\n");

			menu();

		}

	}

	public static void addStudent() {
		try {
			int x;
			Scanner sc = new Scanner(System.in);
			System.out.println("add student");
			System.out.println("How many student do you want");
			n = sc.nextInt();
			for (int i = 0; i < n; i++) {
				// registering student
				System.out.println("Register Student No" + (i + 1));
				Student.Register();

				System.out.println("\nEnter the student id");
				int sid = sc.nextInt();
				System.out.println("Enter the Course id to which the student want to Enroll :");
				int cid = sc.nextInt();
				System.out.println("Enter the student name");
				String sname = sc.next();
				System.out.println("Enter the email");
				String email = sc.next();

				Student s = new Student(sid, cid, sname, email);

				String sql = "insert into student values(?,?,?,?)";
				pstmt = CrsApp.con.prepareStatement(sql);
				pstmt.setInt(1, s.getSid());
				pstmt.setInt(2, cid);
				pstmt.setString(3, s.getSname());
				pstmt.setString(4, s.getEmail());

				x = pstmt.executeUpdate();
				if (x > 0) {
					System.out.println("Student Added is :" + s.getSname());
					System.out.println("----*----");

				}
			}
			System.out.println("Student added successfully");
			System.out.println("----*-----");
			menu();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void removeStudent() {
		// TODO Auto-generated method stub
		try {

			String sql = "delete from student where sid=?";
			Scanner sc = new Scanner(System.in);

			pstmt = CrsApp.con.prepareStatement(sql);

			System.out.println("How many student do you want to remove");

			n = sc.nextInt();

			for (int i = 0; i < n; i++) {

				System.out.println("enter student id to be removed");

				pstmt.setInt(1, sc.nextInt());

				int x = pstmt.executeUpdate();

				if (x > 0) {

					System.out.println((i + 1) + " Student removed");

				}

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		if (n == 0) {
			System.out.println((n) + " student removed");
			menu();

		} else {
			System.out.println("\n\t\tStudent removed Successfully...");
			System.out.println("\n****************************************\n");
			menu();

		}

	}

	public static void addProfessor() {
		try {
			int x;

			Scanner sc = new Scanner(System.in);
			System.out.println("add Professor");
			System.out.println("How many professor do you want");
			n = sc.nextInt();
			for (int i = 0; i < n; i++) {
				// registering professor
				System.out.println("Registering Professor No" + (i + 1));
				Professor.Register();

				System.out.println("\nEnter the professor id");
				int pid = sc.nextInt();
				System.out.println("Enter the Course Id to which The Professor will teach : ");
				int cid = sc.nextInt();
				System.out.println("Enter the professor name");
				String pname = sc.next();
				System.out.println("Enter the experience of professor");
				int exp = sc.nextInt();
				Professor p = new Professor(pid, cid, pname, exp);

				String sql = "insert into professors values(?,?,?,?)";
				pstmt = CrsApp.con.prepareStatement(sql);
				pstmt.setInt(1, p.getPid());
				pstmt.setInt(2, cid);
				pstmt.setString(3, p.getPname());
				pstmt.setInt(4, p.getExp());

				x = pstmt.executeUpdate();
				if (x > 0) {
					System.out.println("Professor Added is :" + p.getPname());
					System.out.println("----*----");

				}
			}
			System.out.println("Professor added successfully");
			System.out.println("----*-----");
			menu();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void removeProfessor() {
		// TODO Auto-generated method stub
		try {

			String sql = "delete from professors where pid=?";
			pstmt = CrsApp.con.prepareStatement(sql);
			Scanner sc = new Scanner(System.in);
			System.out.println("How many professor do you want to remove");
			n = sc.nextInt();
			for (int i = 0; i < n; i++) {
				System.out.println("enter professor id to be removed");
				pstmt.setInt(1, sc.nextInt());
				int x = pstmt.executeUpdate();
				if (x > 0) {

					System.out.println((i + 1) + " Professor removed");

				}

			}

		} catch (Exception e) {
// TODO Auto-generated catch block
			e.printStackTrace();

		}

		if (n == 0) {
			System.out.println((n) + "professor removed");
			menu();

		} else {
			System.out.println("\n\t\tProfessor removed Successfully...");
			System.out.println("\n**********************************\n");
			menu();

		}

	}

	public static void viewAllStudent() {
		// TODO Auto-generated method stub
		try {

			String sql = "select * from student";

			stmt = CrsApp.con.createStatement();

			resultset = stmt.executeQuery(sql);

			System.out.println("Student Details below are:-........... ");

			while (resultset.next() == true) {
				System.out.println("Student's Id");
				System.out.println(resultset.getInt(1));
				System.out.println("Students's Name");

				System.out.println(resultset.getString(2));
				System.out.println("Students's Email");
				System.out.println(resultset.getString(3));

				System.out.println("--------");

			}

		} catch (Exception e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}

		menu();

	}
}
