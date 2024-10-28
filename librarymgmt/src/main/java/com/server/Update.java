package com.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		response.setContentType("text/html");

		Integer book_id = Integer.parseInt(request.getParameter("book_id"));

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?characterEncoding=utf8",
					"root", "Mrunalsql.8");

			PreparedStatement ps = con.prepareStatement("select * from books where book_id=?");
			ps.setInt(1, book_id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				out.println("<html><head><link rel=\"stylesheet\" href=\"UpdateBook.css\"><link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css\"></head><body>");
				out.println("<div class=\"logo\">");
				out.println("<div >");
				out.println("<img src=\"library-logo.png\" width=\"60px\" height=\"60px\">");
				out.println("</div>");
				out.println("<div class=\"headerdiv\">");
				out.println("<h2>Aksharvaat </h2>");
				out.println("</div>");
				out.println("<div class=book>");
				out.println("<div class=head ><a href=\"List\" class=\"link\">Books</a>");
				out.println("</div>");
				out.println("<div class=head><a href=\"List1\" class=\"link\">User</a></div>");
				out.println("<div class=head><a href=\"allocationList\" class=\"link\">Allocation</a></div>");
				out.println("<div class=head><a href=\"login.html\" class=\"link\">Logout</a></div>");
				out.println("</div>	");
				out.println(" </div>");
				out.println("<div class=link1>");
				out.println("<a href=\"/librarymgmt/home.html\">HOME  </a>/ UPDATE BOOK\r\n"
						+ "	</div>");
				out.println("<h3 class=\"heading\">UPDATE BOOK</h3>");
				out.println("<div>");
				out.println("<form action=\"Update2\" method=\"post\">");
				out.println("<fieldset>");
				out.println("<legend><h3>Update Book</h3> </legend>");
				out.println("<h3 style=font-size: large;>");
				out.println("Book Id : <input class=\"size1\"type=\"Number\"name=\"book_id\" readonly value="+rs.getInt(1)+"><br> <br>");
				out.println("Book Name : <input class=\"size2\" type=\"text\" name=\"book_name\" required value="+rs.getString(2)+"> <br> <br>");
				out.println("Author : <input class=\"size3\" type=\"text\" name=\"author\"required value="+rs.getString(3)+"> <br> <br> ");
				out.println("Price : <input  class=\"size4\" type=\"text\" name=\"price\" required value="+rs.getString(4)+"> <br> <br>");
				out.println("</h3>");
				out.println("<hr>");
				out.println("<br>");
				out.println("<pre class=\"btnn1\">");
				out.println("<input type=\"submit\" value=\"UPDATE\"class=\"btn\"");
				out.println(" </pre>");
				out.println("</fieldset>");
				out.println("</form>");
				out.println("</div>");
			}

		} catch (Exception e) {
			out.println("Exception " + e);
		}
	}
}
