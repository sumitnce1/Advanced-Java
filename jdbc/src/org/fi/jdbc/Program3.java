package org.fi.jdbc;

import java.sql.DriverManager;
import java.sql.Statement;

import com.mysql.cj.protocol.Resultset;

import java.sql.Connection;

public class Program3 {

	public static void main(String[] args) {
		try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaee_0016", "root", "Admin@123");
			Statement stSelect = connection.createStatement();
			Resultset result= stSelect.executeQuery("select * from user_0016"));
				
		}
	

	}

}
