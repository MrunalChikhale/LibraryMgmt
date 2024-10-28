package com.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Update2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	response.setContentType("text/html");
	PrintWriter out = response.getWriter();

	Integer book_id = Integer.parseInt(request.getParameter("book_id"));
	String book_name = request.getParameter("book_name");
	String author = request.getParameter("author");
	String price = request.getParameter("price");

	try {
		Class.forName("com.mysql.cj.jdbc.Driver");

		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?characterEncoding=utf8",
				"root", "Mrunalsql.8");

		PreparedStatement ps = con.prepareStatement("update books set book_name=?, author=?, price=? where book_id=? ");
		ps.setInt(4, book_id);
		ps.setString(1, book_name);
		ps.setString(2, author);
		ps.setString(3, price);

		ps.executeUpdate();
          
		response.sendRedirect("List");
	} catch (Exception e) {
		out.println("Exception " + e);
	}

}

}
