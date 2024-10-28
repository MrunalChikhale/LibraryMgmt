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
import javax.servlet.http.HttpSession;

public class admin extends HttpServlet{
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?characterEncoding=utf8",
					"root", "Mrunalsql.8");
			String sql1= "select * from admin where password=?;";
			PreparedStatement ps1 = con.prepareStatement(sql1);
			
			ps1.setString(1,password);
			ResultSet rs1=ps1.executeQuery();
			if(rs1.next())
			{
				HttpSession session=request.getSession();
				session.getAttribute(password);
				out.println("<html><script>alert('Successfully Login!!')</script></html>");
				RequestDispatcher view=request.getRequestDispatcher("/home.html");
				view.forward(request, response); 
			}
			else
			{	
				out.println("<html><script>alert('Invalid Credentials Please Try Again!!')</script></html>");				
				RequestDispatcher rd=request.getRequestDispatcher("/login.html");
				rd.include(request, response);
			}
			
	} catch (Exception e) {
			System.out.println("Exception " + e);

		}

	}
}
