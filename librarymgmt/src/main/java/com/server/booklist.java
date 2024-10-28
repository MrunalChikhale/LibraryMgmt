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

public class booklist extends HttpServlet {
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		out.println("<html><head><link rel=\"stylesheet\" href=\"booktable.css\"><link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css\"></head><body>");
		out.println("<div class=\"logo\">");
        out.println("<div>");
        out.println("<img src=\"library-logo.png\"\r\n"+ "width=\"60px\" height=\"60px\">");
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
        out.println("<a href=\"/librarymgmt/home.html\">HOME  </a>/ BOOK LIST");
        out.println( "<h3 class=heading>BOOK LIST</h3>");
        out.println(" </div>");
		
		out.println("<div class=\"form\">");
    	     out.println("<div class=\"btn\">");
    	 	 out.print("<button type=\"button\"><a href=books.html>+Add New</a></button>");
    	     out.println("</div>");
    	     out.println("<div class=booklist style=border:1px solid black>");
    	     
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?characterEncoding=utf8",
					"root", "Mrunalsql.8");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM library.books;");
		
			out.println("<table>");
			out.println("<tr><th>Book Id</th><th>Book Name</th><th>Author</th><th>Price</th><th>Update</th></tr>");
			while (rs.next()) {

				Integer book_id = rs.getInt("book_id");
				String book_name = rs.getString("book_name");
				String author = rs.getString("author");
				String price = rs.getString("price");
				
				out.print("<tr><td>" + book_id + "</td><td>" + book_name + "</td><td>" + author + "</td><td>" + price + "</td><td>"
						+ "<a href ='Update?book_id=" + book_id + "'><i class=\"fa fa-pencil\"></i></a>");
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

