package com.ojas;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EditServlet
 */
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    private final static String query="select name,email,mobile,dob,city,gender from usertable where id=?";
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter out=res.getWriter();
		res.setContentType("text/html");
		out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css' rel='stylesheet' integrity='sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi' crossorigin='anonymous'>");

		int id=Integer.parseInt(req.getParameter("id"));
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/usermngt","root","root");
		PreparedStatement ps=con.prepareStatement(query);
		ps.setInt(1, id);
		ResultSet rs=ps.executeQuery();
		rs.next();
		
		out.println("<div style='margin:auto;width:500px;margin-top:100px;'>");
		out.println("<form action='edit?id="+id+"' method='post'>");	
		out.println("<table  class='table table-hover table-striped>");
		out.println("<tr>");
		out.println("<td>Name</td>");
		out.println("<td><input type='text' name='name' value='"+rs.getString(1)+"'</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td>Name</td>");
		out.println("<td><input type='text' name='name' value='"+rs.getString(1)+"'</td>");
		out.println("</tr>");
		
			out.println("<tr>");
			out.println("<td>Email</td>");
			out.println("<td><input type='email' name='email' value='"+rs.getString(2)+"'</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td>Mobile</td>");
			out.println("<td><input type='text' name='mobile' value='"+rs.getString(3)+"'</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td>DOB</td>");
			out.println("<td><input type='date' name='dob' value='"+rs.getString(4)+"'</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td>City</td>");
			out.println("<td><input type='text' name='city' value='"+rs.getString(5)+"'</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td>Gender</td>");
			out.println("<td><input type='text' name='gender' value='"+rs.getString(6)+"'</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td><button type='submit' class='btn btn-outline-success'>Edit</button></td>");
			out.println("<td><button type='reset' class='btn btn-outline-danger'>Cancel</button></td>");
			out.println("</tr>");
			out.println("</table>");
			out.println("</form>");
		

		} catch (SQLException e) {
			out.println("<h2 class='bg-danger text-light text-center'>"+e.getMessage()+"</h2>");
			
			e.printStackTrace();
		}catch (Exception e) { 
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.println("<a href='home.html'><button  class='btn btn-outline-success'>Home</button>");
		out.println("</div>");
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req,res);
	}
}
