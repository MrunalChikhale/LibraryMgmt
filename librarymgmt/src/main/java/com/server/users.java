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

public class users extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 PrintWriter out = response.getWriter();
		  
			String name = request.getParameter("name");
			Integer age = Integer.parseInt(request.getParameter("age"));
			String phone_no = request.getParameter("phone_no");
            String address = request.getParameter("address");
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "Mrunalsql.8");

					String sql = "insert into user( name, age, phone_no, address ) values(?,?,?,?);";

					PreparedStatement myStmt = con.prepareStatement(sql);

					myStmt.setString(1, name);
					myStmt.setInt(2, age);
					myStmt.setString(3, phone_no);
					myStmt.setString(4, address);

					int i = myStmt.executeUpdate();
					if (i > 0) {
						out.print("<html><body><script>alert('Saved Successfully!');</script></body></html><br>");
						response.sendRedirect("List1");
					}

			} catch (Exception e) {
				System.out.println(e);
			}
}

	}


