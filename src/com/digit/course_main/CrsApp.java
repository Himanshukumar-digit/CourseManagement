package com.digit.course_main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

import com.digit.course_management.Professor;
import com.digit.course_management.Student;
import com.digit.course_management.Users;
import com.digit.course_managementservices.Admin_service;
import com.digit.course_managementservices.Professor_services;
import com.digit.course_managementservices.Student_services;

public class CrsApp {
	public static Connection con;

	public static void admin() {

		int temp = 0;

		System.out.println("Admin Login");
		

		while (true) {

			temp++;

			boolean b = Users.login();

			if (b == true) {

				System.out.println("Login Successful...\n");

				System.out.println("------------------------------------");

				manage();

				break;

			}

			if (temp == 3) {

				System.out.println("admin not found");

				break;

			}
			else {

				System.err.println("Invalid......\n");

				System.out.println("Enter again");
			}
		}

	}

	public static void manage() {

		System.out.println("Select the Type of User:");

		System.out.println("1. Professor service\n"

				+ "2. Student service\n"

				+ "3. Menu");

		System.out.println("Enter number of your choice");

		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();

		switch (n) {

		case 1: {

			Professor_services.login();

			break;

		}

		case 2: {

			Student_services.login();

			break;

		}

		case 3: {

			Admin_service adsrv = new Admin_service();

			adsrv.menu();

			break;

		}

		}

	}

	public static void main(String[] args) throws Exception {

		Class.forName("com.mysql.cj.jdbc.Driver");
		System.out.println("Driver Loaded");
		String url = "jdbc:mysql://localhost:3306/crs";

		String user = "root";
		String pwd = "root";
		// Step:2
		con = DriverManager.getConnection(url, user, pwd);
		admin();

	}
}