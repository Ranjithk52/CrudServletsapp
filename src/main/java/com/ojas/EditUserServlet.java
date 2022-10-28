package com.ojas;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EditUserServlet
 */
public class EditUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    private final static String query="update usertable set name=?,email=?,mobile=?,dob=?,city=?,gender=? where id=?";
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter out=res.getWriter();
		res.setContentType("text/html");
		out.println("<head><link href='https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css' rel='stylesheet' integrity='sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi' crossorigin='anonymous'</head>>");

		int id=Integer.parseInt(req.getParameter("id"));
		String name=req.getParameter("name");
		String email=req.getParameter("email");
		String mobile=req.getParameter("mobile");
		String dob=req.getParameter("dob");
		String city=req.getParameter("city");
		String gender=req.getParameter("gender");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/usermngt","root","root");
		PreparedStatement ps=con.prepareStatement(query);
		ps.setInt(7, id);
		ps.setString(1, name);
		ps.setString(2, email);
		ps.setString(3, mobile);
		ps.setString(4, dob);
		ps.setString(5, city);
		ps.setString(6, gender);
		int count=ps.executeUpdate();
		out.println("<div class='card' style='margin:auto;width:300px;margin-top:100px'>");
		if(count==1){
			out.println("<h2 class='bg-danger text-light text-center'> Record Updated Successfully </h2>");
		}else{
			out.println("<h2 class='bg-danger text-light text-center'> Record Not Updated </h2>");

		}

		} catch (SQLException e) {
			out.println("<h2 class='bg-danger text-light text-center'>"+e.getMessage()+"</h2>");
			
			e.printStackTrace();
		}catch (Exception e) { 
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.println("<a href='home.html'><button  class='btn btn-outline-success'>Home</button>");
		out.println("&nbsp; &nbsp;");
		out.println("<a href='showuser'><button class='btn btn-outline-success'>Show User</button></a>");
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
