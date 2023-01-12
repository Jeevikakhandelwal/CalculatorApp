package com.jdbc.jeevika.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class PersonAgeCalculator {
private static final String SQL_SELECT_QUERY="SELECT DATEDIFF(NOW(),DOB)/365.25 FROM PERSON WHERE PID=?";
	public static void main(String[] args) {
				//JDBC related parameters
				Connection connection=null;
				PreparedStatement pstmt=null;
				ResultSet resultSet=null;
				
				//To connect with DBE using url,username,password
				String url="jdbc:mysql:///PersonInfo";
				String userName="root";
				String password="root@123";
				Scanner scan=new Scanner(System.in);
				int pid=0;
				
				java.text.SimpleDateFormat sdf=null;
				java.sql.Date sqlDob=null,sqlDom=null,sqlDoj=null;
				
				try {
					if(scan!=null) {
						System.out.print("Enter the Id of the person:: ");
						pid=scan.nextInt();
									
					}
					//Step1 => Establishing the Connection by providing url, username,password
					connection=DriverManager.getConnection(url,userName,password);
					
					if(connection!=null) 
						pstmt=connection.prepareStatement(SQL_SELECT_QUERY);
					
					if(pstmt!=null) 
						
						//Setting the input value to Pre-Compiled Query
						pstmt.setInt(1, pid);	
					
					if(pstmt!=null)
						//Execute the Query
						resultSet =pstmt.executeQuery();
					
					//Process the resultSet and display the output
					if(resultSet.next()) {
						int age=resultSet.getInt(1);
						System.out.println("The age of the person is:: "+age);
						
					}else {
						System.out.println("Record is not available for given record "+pid);
					}
				}catch(SQLException ex) {
					
					//step4 => Handling Code
					ex.printStackTrace();
				}catch(Exception e) {
					e.printStackTrace();
				}finally {
					
					//Step5 => Closing all the resources
					if(scan!=null) {
						try {
							scan.close();
						}catch(Exception e) {
							e.printStackTrace();
						}
					}
					if(resultSet!=null) {
						try {
							resultSet.close();
						}catch(Exception e) {
							e.printStackTrace();
						}
					}
					if(pstmt!=null) {
						try {
							pstmt.close();
						}catch(SQLException e) {
							e.printStackTrace();
						}
					}
					if(connection!=null) {
						try {
							connection.close();
						}catch(SQLException e) {
							e.printStackTrace();
						}
					}
				}
	}

}
