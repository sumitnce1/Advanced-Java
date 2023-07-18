package org.fi.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Insert{

	public static void main(String[] args) {
		
		Connection connection = null;
		Statement SetInsert = null;
		Scanner scanner = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); 
			scanner = new Scanner(System.in);
			
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaee_0016", "root", "Admin@123");
			
			
			SetInsert = connection.createStatement();
			
			System.out.print("Enter the ID : ");
			String id = scanner.next();
			
			System.out.print("Enter Name : ");
			String name = scanner.next();
			
			System.out.print("Enter the Email :");
			String email = scanner.next();
			
			System.out.print("Enter the Mobile no : ");
			String mobile = scanner.next();
			
			System.out.print("Enter the Age : ");
			String age = scanner.next();
		
			String sqlInsert = "insert into input_0016 values('" + id + "','" + name + "','" + email + "','" + mobile +"','" + age +"')";

			System.out.println("SQL : " + sqlInsert);
			
			int rows = SetInsert.executeUpdate(sqlInsert);
			if (rows>0)
				System.out.println("Record saved");
			else
				System.out.println("Unable to save record");
			
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			try {
				if(scanner!=null)
					scanner.close();
				if(SetInsert!=null)
					SetInsert.close();
				if(connection!=null)
					connection.close();
			} catch(SQLException e){
				e.printStackTrace();
			}
		}
	}
}
