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

public class allocation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter out = response.getWriter();	
		response.setContentType("text/html");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?characterEncoding=utf8",
					"root", "Mrunalsql.8");
			Statement st = con.createStatement();
			Statement st1 = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT user_id,name FROM library.user;");
			ResultSet rs1 = st1.executeQuery("select book_id,book_name from library.books;");
			out.println("<html><head><link rel=\"stylesheet\" href=\"allocation.css\"><link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css\"></head><body>");
			out.println("<div class=\"logo\">");
			out.println("<div >");
			out.println("<img src=\"library-logo.png\" width=\"60px\" height=\"60px\">");
			out.println(" </div>");
			out.println("<div class=\"headerdiv\">");
			out.println("<h2>Aksharvaat</h2>");
			out.println("</div>");
			out.println("<div class=book>");
			out.println("<div class=head ><a href=\"List\" class=\"link\">Books</a></div>");
			out.println("<div class=head><a href=\"List1\" class=\"link\">User</a></div>");
			out.println("<div class=head><a href=\"allocationList\" class=\"link\">Allocation</a></div>");
			out.println("<div class=head><a href=\"login.html\" class=\"link\">Logout</a></div>");
			out.println("</div>");
			out.println(" </div>");
			out.println("<div class=link1>");
			out.println("<a href=\"/librarymgmt/home.html\">HOME</a>/ADD ALLOCATION\r\n"
								+ "	</div>");
			out.println("</div>");
			out.println("<h3 class=\"heading\">ADD ALLOCATION</h3>");
			out.println("<div>");
			out.println("<form action=\"allocationSave\" method=\"post\">");
			out.println("<fieldset>");
			out.println("<legend><h2>Allocation</h2> </legend>");
			out.println("<h3>");
			out.print("<label for=\"User Name\">User Name:</label>");
			out.print("<select class=dropdown name=userId>");
			while (rs.next()) {
				out.print("<option value="+rs.getInt(1)+">"+rs.getString(2)+"</option>");
			}

			out.print("</select>");
			out.print("<br>");
			out.print("<label for=\"Book Name\">Book Name:</label>");

			out.println("<select class=dropdown name=bookId>");
			while(rs1.next()) {
				out.print("<option value="+rs1.getInt(1)+">"+rs1.getString(2)+"</option>");
			}
			out.print("</select>");
			out.print("<br>");
		
		out.println("<label for=\"Issue Date\">Issue Date:\r\n</label>");
		out.println("<input class=\"size4\" type=\"date\" name=\"issue_date\" placeholder=\" Enter Issue Date\" required>");
		out.println("<label for=\"Return Date\">Return Date:\r\n</label>");
		out.println("<input class=\"size4\" type=\"date\" name=\"return_date\" placeholder=\" Enter Return Date\" required>");	
		out.println("</h3>");
		out.println("<hr>");
		out.println("<br>");
		out.println("<pre class=\"btnn1\">");
		out.println("<div class=\"endDiv\"><input type=\"submit\" value=\"submit\"class=\"btn1\"><input type=\"reset\" value=\"reset\" class=\"btn\"></div>");
		out.println("</pre>");
		out.println("</fieldset>");
		out.println("</form>");
		out.println("</div>");
		out.print("</body></html>");
		}
		catch (Exception e) {
			out.println("Exception " + e);
		}
	}
}

