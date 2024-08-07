package com.digit.javaTraining.helpers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.digit.javaTraining.CRSApp.Admin;
import com.digit.javaTraining.CRSApp.DatabaseConnection;
import com.digit.javaTraining.CRSApp.Launch;
import com.digit.javaTraining.CRSApp.Student;

public class StudentHelper {
	static Connection con = DatabaseConnection.con;

	public static void addStudent() {
		try {
			Admin ad = new Admin();
			Scanner sc = new Scanner(System.in);
			System.out.println("\n---ADD STUDENT---");
			System.out.println("Enter the name of the Student");
			String name = sc.nextLine();
			System.out.println("Enter the username:");
			String userName = sc.next();
			System.out.println("Enter the Password");
			String pass = sc.next();
			System.out.println("Enter the Age:");
			int age = sc.nextInt();
			String sql = "insert into student (s_username, s_name, s_password, s_age) values(?,?,?,?)";

			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userName);
			pstmt.setString(2, name);
			pstmt.setString(3, pass);
			pstmt.setInt(4, age);
			int x = pstmt.executeUpdate();

			System.out.println("\033[32m\033[1mStudent Added Successfully...\033[0m\033[0m");

			asignCourse(userName);

			System.out.println("Do you want to add more Students (yes/no)");
			String tryAgain = sc.next();

			if (tryAgain.equalsIgnoreCase("Yes")) {
				addStudent();
			} else {
				Admin.adminMenu();
			}

		} catch (InputMismatchException ime) {
			System.out.println("\033[1m\033[31mInvalid Input!...\033[0m\033[0m");
			System.out.println("\033[1mPlease try again...\033[0m");
			addStudent();
		} catch (SQLException e) {

			System.out.println("\033[1m\033[31mSomething went wrong in Database!...\033[0m\033[0m");
			System.out.println("\033[1mPlease try again...\033[0m");
			addStudent();
		} catch (Exception e) {
			
			System.out.println("\033[1m\033[31mSomething went wrong in Database!...\033[0m\033[0m");
			Admin.adminMenu();
		}
	}

	static public Student login() {
		Scanner sc = new Scanner(System.in);
		System.out.println("\n---Student Login---");
		System.out.println("Enter Your Username");
		String username = sc.next();
		System.out.println("Enter Your Password");
		String pass = sc.next();
		try {
			String sql = "select * from student where s_username = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, username);
			ResultSet result = pstmt.executeQuery();
			if(!result.next()) {
				return null;
			}
			if (pass.equals(result.getString("s_password"))) {
				Student stud = new Student(result.getString("s_username"), result.getString("s_name"),
						result.getString("s_password"), result.getInt("s_age"));

				return stud;

			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	static public void asignCourse(String student_id) {
		Scanner sc = new Scanner(System.in);
		try {
			System.out.println("Select a course to Read");
			ArrayList<String> arrList = CourseHelper.showAllCourses();
			if (arrList.size() <= 0) {
				System.out.println("\033[1m\033[31mNo course is created yet.\033[0m\033[0m");
				Admin.adminMenu();
			}
			System.out.println("Select a option:");
			int inp = sc.nextInt();
			
			String course_id = arrList.get(inp - 1);
			
			String sql = "update student set course_id = ? where s_username = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, course_id);
			pstmt.setString(2, student_id);
			pstmt.executeUpdate();
			System.out.println("\033[32m\033[1mCourse Assigned Sucessfully...\033[0m\033[0m");

		} catch (InputMismatchException ime) {
			System.out.println("\033[1m\033[31mInvalid Input!...\033[0m\033[0m");
			System.out.println("\033[1mPlease try again...\033[0m");
			asignCourse(student_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	static public void allStudentInCourse(String course_id) {
		System.out.println("---All Student inside course--");

		try {
			String sql = "select s_name from student where course_id = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, course_id);
			ResultSet result = pstmt.executeQuery();
			int count = 1;
			while (result.next()) {
				System.out.println(count++ + ") " + result.getString("s_name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	static void printStudentSection() {
		System.out.println("-----------------------------");
		System.out.println("       | STUDENT MENU |      ");
		System.out.println("-----------------------------");
		System.out.println("1. Check score");
		System.out.println("2. Get Report");
		System.out.println("3. Goto Main Menu");
		System.out.println("4. Exit");
	}

	static public void showAllStudent() {
		System.out.println("---List of Students--");
		String sql = "select s_username, s_name, s_age from student";
		try {
			Statement stmt = con.createStatement();
			ResultSet result = stmt.executeQuery(sql);
			int count = 1;
			while (result.next()) {
				System.out.println(count++ + ") " + result.getString("s_name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	static void getUserInput(String studentId) {
		
		try {
			String sql = "select * from student where S_Username = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, studentId);
			ResultSet studentDetail = pstmt.executeQuery();
			studentDetail.next();

			Scanner sc = new Scanner(System.in);
			printStudentSection();
			System.out.println("Select an option:");
			int userInput = sc.nextInt();
			if (userInput == 1) {
				System.out.println("Your marks is: " + studentDetail.getString("s_marks"));
				getUserInput(studentId);
			} else if (userInput == 2) {
				printScoreCard(studentId);
				getUserInput(studentId);
				mainLogin();
			} else if (userInput == 3) {
				Launch.mainMenu();
				return;
			} else if (userInput == 4) {
				System.out.println("Exiting the Application.");
				System.out.println("Thanks for visiting us.");
				System.exit(0);
			} else {
				System.out.println("Wrong Input");
				System.exit(0);
			}
		} catch (Exception e) {
			System.out.println("\033[1m\033[31mWrong Input!...\033[0m\033[0m");
			System.out.println("Select correct option");
			getUserInput(studentId);
		}
	}

	public static void mainLogin() {
		Scanner sc = new Scanner(System.in);
		Student stud = login();

		if (stud != null) {
			System.out.println("\033[32m\033[1mAuthenticated...\033[0m\033[0m");
			getUserInput(stud.getUsername());
		} else {
			System.out.println("\033[1m\033[31mInvalid Credentials!\033[0m\033[0m");
			System.out.println();
			System.out.println("Do you want to try again? Yes/No");
			String tryAgain = sc.next();
			if (tryAgain.equalsIgnoreCase("Yes")) {
				mainLogin();
			} else {
				Launch.mainMenu();
			}
		}

	}

	static void printScoreCard(String studentId) {
//		Course c = ad.courses.get(enrolledCourseId);
		try {
			String sql = "select * from student where s_username = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, studentId);
			ResultSet studentDetail = pstmt.executeQuery();
			studentDetail.next();

			String sql2 = "select * from course where c_id = ?";
			PreparedStatement pstmt2 = con.prepareStatement(sql2);
			pstmt2.setString(1, studentDetail.getString("course_id"));
			ResultSet courseDetail = pstmt2.executeQuery();
			courseDetail.next();

			String sql3 = "select * from professor where course_id = ?";
			PreparedStatement pstmt3 = con.prepareStatement(sql3);
			pstmt3.setString(1, studentDetail.getString("course_id"));
			ResultSet professorDetail = pstmt3.executeQuery();
			professorDetail.next();

			String sname;

			sname = String.format("%s", studentDetail.getString("s_name"));
			String sdurationAndCName;
			System.out.print("*");
			sdurationAndCName = String.format("%s of %s course", courseDetail.getString("Duration"),
					courseDetail.getString("c_name"));
			String sdescription;
			System.out.print("*");
			sdescription = String.format("containing %s", courseDetail.getString("Description"));
			
			String sgrade;
			sgrade = String.format("with a grade of %s", studentDetail.getString("s_marks") + "%");
			
			String pName;
			pName = String.format("%s", professorDetail.getString("p_name"));

			System.out.println("****************************************************");
			System.out.println("*                                                    *");
			System.out.println("*         Certificate of Course Completion           *");
			System.out.println("*                                                    *");
			System.out.println("*              This is to certify that               *");
			System.out.println("*                                                    *");
			System.out.print(  "*");        centerAlign(sname);  System.out.println("*");
			System.out.println("*                                                    *");
			System.out.println("*            has successfully completed              *");
			System.out.println("*                                                    *");
			System.out.print( "*");centerAlign(sdurationAndCName);System.out.println("*");
			System.out.println("*                                                    *");
			System.out.print("*");   centerAlign(sdescription);  System.out.println("*");
			System.out.println("*                                                    *");
			System.out.print( "*");      centerAlign(sgrade);    System.out.println("*");
			System.out.println("*                                                    *");
			System.out.println("*                under the guidance of               *");
			System.out.println("*                                                    *");
			System.out.print( "*");      centerAlign(pName);     System.out.println("*");
			System.out.println("*                                                    *");
			System.out.println("*                                                    *");
			System.out.println("******************************************************");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// func for center alignment
	private static void centerAlign(String text) {
		int totalSpaces = 52 - text.length();
		int leftSpaces = totalSpaces / 2;
		int rightSpaces = totalSpaces - leftSpaces;

		// non-negative totalSpaces value
		if (totalSpaces >= 0) {
			String alignedText = " ".repeat(leftSpaces) + text + " ".repeat(rightSpaces);
			System.out.print(alignedText);
		} else {
			// if text is already longer than 50 characters, print as it is
			System.out.println(text);
		}
	}

}
