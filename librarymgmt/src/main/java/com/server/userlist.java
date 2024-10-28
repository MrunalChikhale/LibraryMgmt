package com.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class userlist extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		out.println(
				"<html><head><link rel=\"stylesheet\" href=\"booktable.css\"><link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css\"></head><body>");
		
		out.println("<div class=\"logo\">");
		out.println("<div>");
		out.println("<img src=\"library-logo.png\"\r\n" + "width=\"60px\" height=\"60px\">");
		out.println("</div>");
		out.println("<div class=\"headerdiv\">");
		out.println("<h2>Aksharvaat </h2>");
		out.println("</div>");
		out.println("<div class=book>");
		out.println("<div class=head ><a href=\"List\" class=\"link\">Books</a></div>");
		out.println("<div class=head><a href=\"List1\" class=\"link\">User</a></div>");
		out.println("<div class=head><a href=\"allocationList\" class=\"link\">Allocation</a></div>");
		out.println("<div class=head><a href=\"login.html\" class=\"link\">Logout</a></div>");
		out.println("</div>");
		out.println("</div>");
		
		out.println("<div class=link1>");
		out.println("<a href=\"/librarymgmt/home.html\">HOME  </a>/ USER LIST");
		out.println("<h3 class=heading>USER LIST</h3>");
		out.println(" </div>");
		out.println("<div class=\"form\">");
		out.println("<div class=\"btn\">");
		out.print("<button type=\"button\"><a href=user.html>+Add New</a></button>");
		out.println("</div>");
		out.println("<div class=booklist style=border:1px solid black>");

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?characterEncoding=utf8",
					"root", "Mrunalsql.8");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM library.user;");

			out.println("<table>");
			out.println(
					"<tr><th>User Id</th><th>User Name</th><th>Age</th><th>Phone No</th><th>Address</th><th>Update</th><th>Delete</th></tr>");
			while (rs.next()) {

				Integer user_id = rs.getInt("user_id");
				String name = rs.getString("name");
				Integer age = rs.getInt("age");
				String phone_no = rs.getString("phone_no");
				String address = rs.getString("address");

				out.print("<tr><td>" + user_id + "</td><td>" + name + "</td><td>" + age + "</td><td>" + phone_no
						+ "</td><td>" + address + "</td>" + "<td><a href =UpdateUser?user_id=" + user_id
						+ "><i class=\"fa fa-pencil\"></i></a></td>" 
						+ "<td><a href =DeleteUser?user_id=" + user_id 
						+"><i class=\"fa fa-trash\"></i></a></td></tr>");

			}
			out.println("</table>");
			out.println("</div>");

			out.println("<br>");

			out.println("</div>");

			out.println("</html></body>");

		} catch (Exception e) {
			out.println("Exception :" + e);
		}

	}

}
