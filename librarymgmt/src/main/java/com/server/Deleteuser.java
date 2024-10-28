package com.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Deleteuser extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		int user_id = Integer.parseInt(request.getParameter("user_id"));

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?characterEncoding=utf8","root", "Mrunalsql.8");
			String sql2= "delete from user where user_id = ?";			
			PreparedStatement ps = con.prepareStatement(sql2);
			ps.setInt(1,user_id);
			ps.executeUpdate();
			 response.sendRedirect("List1");	
		   
		   }
	 catch (Exception e) 
	 {
	    out.println("Exception "+e);
	 }			
	}
}
