package com.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateUser2 extends HttpServlet {

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		Integer user_id = Integer.parseInt(request.getParameter("user_id"));
		String name = request.getParameter("name");
		Integer age = Integer.parseInt(request.getParameter("age"));
		String phone_no = request.getParameter("phone_no");
		String address = request.getParameter("address");

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?characterEncoding=utf8",
					"root", "Mrunalsql.8");

			PreparedStatement ps = con.prepareStatement("update user set name=?, age=?, phone_no=?, address=? where user_id=?");
			ps.setInt(5, user_id);
			ps.setString(1, name);
			ps.setInt(2, age);
			ps.setString(3, phone_no);
			ps.setString(4, address);

			ps.executeUpdate();
			response.sendRedirect("List1");


		} catch (Exception e) {
			out.println("Exception " + e);
		}
	}


}
