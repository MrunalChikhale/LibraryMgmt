package com.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AllocationList extends HttpServlet{

protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		out.println("<html><head><link rel=\"stylesheet\" href=\"allocationtable.css\"><link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css\"></head><body>");
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
        out.println("<a href=\"/librarymgmt/home.html\">HOME  </a>/ ALLOCATION LIST");
        out.println( "<h3 class=heading>ALLOCATION LIST</h3>");
        out.println(" </div>");
		
		out.println("<div class=\"form\">");
    	     out.println("<div class=\"btn\">");
    	 	 out.print("<button type=\"button\"><a href=allocation>+Add New</a></button>");
    	     out.println("</div>");
    	     out.println("<div class=booklist style=border:1px solid black>");
        try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?characterEncoding=utf8",
					"root", "Mrunalsql.8");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM library.allocation;");
		
			out.println("<table>");
			out.println("<tr><th>Id</th><th>Book Name</th><th>User Name</th><th>Issue Date</th><th>Return Date</th</tr>");
			while (rs.next()) {
				
				Statement st1 = con.createStatement();
				Statement st2 = con.createStatement();
				ResultSet rs1 = st2.executeQuery("SELECT name FROM library.user where user_id = "+rs.getInt(3)+";");
				ResultSet rs2 = st1.executeQuery("select book_name from library.books where book_id ="+rs.getInt(2)+"");
				
				String id = rs.getString("id");
				rs2.next();
				rs1.next();
				String book_id = rs2.getString(1);
				String user_id = rs1.getString(1);
				String issueDate = rs.getDate("issue_date").toString();
				String returnDate = rs.getDate("return_date").toString();
				
				out.print("<tr><td>" + id + "</td><td>" + book_id + "</td><td>" + user_id + "</td><td>" + issueDate + "</td><td>"+returnDate+"</td></tr>");

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
