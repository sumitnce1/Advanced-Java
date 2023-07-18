package org.fi.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Program1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection connection = null;
		Statement setSelect = null;
		ResultSet result = null;
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver"); 
			
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaee_0016", "root", "Admin@123");
			setSelect = connection.createStatement();
			
			result = setSelect.executeQuery("select * from user_0016");
				while (result.next())
				{
				System.out.println(result.getString(1));
				System.out.println(result.getString("name"));
				System.out.println(result.getString(3));
				System.out.println(result.getString("mobile"));
				System.out.println("---------------------");
				}
				
			} catch(ClassNotFoundException e){
				e.printStackTrace();
			} catch(SQLException e) {
				e.printStackTrace();
			}

			finally
			{
				try {
					if(result!=null)
						result.close();
					if(setSelect!=null)
						setSelect.close();
					if(connection!=null)
						connection.close();
				} catch (SQLException e){
					e.printStackTrace();
				}
				
			}
	}

}