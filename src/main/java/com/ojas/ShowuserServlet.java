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
 * Servlet implementation class ShowuserServlet
 */
public class ShowuserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowuserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    private final static String query="select id,name,email,mobile,dob,city,gender from usertable";
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter out=res.getWriter();
		res.setContentType("text/html");
		out.println("<head><link href='https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css' rel='stylesheet' integrity='sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi' crossorigin='anonymous'</head>>");

		out.println("<marquee ><h2 class='text-primary'>Register Form</h2></marquee>");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/usermngt","root","root");
		PreparedStatement ps=con.prepareStatement(query);
		
		ResultSet rs=ps.executeQuery();
		out.println("<div style='margin:auto;width:900px;margin-top:100px;'>");
		out.println("<table class='table table-hover table-striped'>");
		
		
		out.println("<tr>");
		out.println("<th>ID</th>");
		out.println("<th>Name</th>");
		out.println("<th>Email</th>");
		out.println("<th>Mobile No</th>");
		out.println("<th>DOB</th>");
		out.println("<th>City</th>");
		out.println("<th>Gender</th>");
		out.println("<th>Edit</th>");
		out.println("<th>Delete</th>");
		out.println("</tr>");
		while(rs.next()) {
			out.println("<tr>");
			out.println("<td>"+rs.getInt(1)+"</td>");
			out.println("<td>"+rs.getString(2)+"</td>");
			out.println("<td>"+rs.getString(3)+"</td>");
			out.println("<td>"+rs.getString(4)+"</td>");
			out.println("<td>"+rs.getString(5)+"</td>");
			out.println("<td>"+rs.getString(6)+"</td>");
			out.println("<td>"+rs.getString(7)+"</td>");
			out.println("<td><a href='editurl?id="+rs.getInt(1)+"'>Edit</a></td>");
			out.println("<td><a href='deleteurl?id="+rs.getInt(1)+"'>Delete</a></td>");
			out.println("</tr>");
		}
		out.println("</table>");
		

		} catch (SQLException e) {
			out.println("<h2 class='bg-danger text-light text-center'>"+e.getMessage()+"</h2>");
			
			e.printStackTrace();
		}catch (Exception e) { 
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.println("<button class='btn btn-outline-success d-block'><a href='home.html'>Home</a></button>");

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
