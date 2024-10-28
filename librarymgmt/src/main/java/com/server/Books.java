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

public class Books extends HttpServlet {
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		        PrintWriter out = response.getWriter();
		  
				String book_name = request.getParameter("book_name");
				String author = request.getParameter("author");
				String price = request.getParameter("price");

				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "Mrunalsql.8");

						String sql = "insert into books( book_name, author, price) values(?,?,?);";

						PreparedStatement myStmt = con.prepareStatement(sql);

						myStmt.setString(1, book_name);
						myStmt.setString(2, author);
						myStmt.setString(3, price);

						int i = myStmt.executeUpdate();
						if (i > 0) {
							response.sendRedirect("List");
						}

				} catch (Exception e) {
					System.out.println(e);
				}
	}

}
