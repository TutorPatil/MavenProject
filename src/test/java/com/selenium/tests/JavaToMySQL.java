package com.selenium.tests;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JavaToMySQL {
	
	// JDBC URL, username and password of MySQL server
	private static final String url = "jdbc:mysql://localhost:3306/sys"; 
	private static final String user = "root"; 
	private static final String password = "admin"; 
	
	// JDBC variables for opening and managing connection 
	private static Connection con; 
	private static Statement stmt; 
	private static ResultSet rs; 
	
	public static void main(String args[]) throws SQLException {
	
			jdbcExample3();
		
		}
	
	
	public static void jdbcExample2() throws SQLException
	{
			String query = "select id,ename,sal from emp";
		
			// opening database connection to MySQL server
			con = DriverManager.getConnection(url, user, password);
			
			// getting Statement object to execute query 
			stmt = con.createStatement(); 
			
			// executing SELECT query 
			rs = stmt.executeQuery(query);
			
			while (rs.next())
			{ 
				int id = rs.getInt(1);
				String ename = rs.getString(2); 
				int sal = rs.getInt(3); 
				System.out.printf("id"+id +" ename:"+ ename +" sal " +sal); 
			
			}			
				
						
	}
	
	
	public static void jdbcExample1() throws SQLException
	{
		   String query = "select count(*) from emp";
	
			// opening database connection to MySQL server
			con = DriverManager.getConnection(url, user, password);
			
			// getting Statement object to execute query 
			stmt = con.createStatement(); 
			
			// executing SELECT query 
			rs = stmt.executeQuery(query);
			
			while (rs.next()) 
			{
				int count = rs.getInt(1); 
				System.out.println("Total number of emp in the table : " + count); 
			} 
				
	}
	
	public static void jdbcExample3()
	{
		String query = "INSERT INTO emp (id, ename, sal) \n" + " VALUES (700, 'Blake5', 10000);"; 
		
		try { 
			// opening database connection to MySQL server
			con = DriverManager.getConnection(url, user, password);
			
			// getting Statement object to execute query 
			stmt = con.createStatement(); 
			
			// executing SELECT query 
			 stmt.executeUpdate(query);			
				
				
			} catch (SQLException sqlEx) {
				sqlEx.printStackTrace(); 
				} 
				finally {
					//close connection ,stmt and resultset here 
					try {
						con.close(); 
						stmt.close();						
						} 
							catch(SQLException se) {
								se.printStackTrace();
							}
				}				
	}
	
	
	}


