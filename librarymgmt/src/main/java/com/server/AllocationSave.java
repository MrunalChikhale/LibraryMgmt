package com.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AllocationSave extends HttpServlet{

	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();	
		response.setContentType("text/html");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?characterEncoding=utf8",
					"root", "Mrunalsql.8");
			
			int bookId = Integer.parseInt(request.getParameter("bookId"));
			int userId = Integer.parseInt(request.getParameter("userId"));
			String issueDate = request.getParameter("issue_date");
			String returnDate = request.getParameter("return_date");
			String query = "insert into allocation(book_id,user_id,issue_date,return_date) values(?,?,?,?);";
			PreparedStatement st = con.prepareStatement(query);
			st.setInt(1,bookId);
			st.setInt(2, userId);
			st.setString(3, issueDate);
			st.setString(4, returnDate);
			int n = st.executeUpdate();
			if(n>0) {
				response.sendRedirect("allocationList");
			}
			else {
				out.print("failed");
			}
			
		}catch(Exception e) {
			System.out.println("exception"+e.getMessage());
		}
		}
} 

