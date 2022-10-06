package servlet_package;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/servlet_login")
public class servlet_login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
  

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		String e=request.getParameter("c_email");
		String p=request.getParameter("c_password");
		
		try
		{
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/adv_java","root","");
			PreparedStatement ps=con.prepareStatement("select c_email,c_password from c_register where c_email=? and c_password=? ");
		ps.setString(1, e);
		ps.setString(2, p);
		ResultSet rs=ps.executeQuery();
		if(rs.next())
		{
			
			
			if(e.equals(rs.getString(1)) && p.equals(rs.getString(2)))
			{
				pw.print("Succesfully Login!");
				Thread.sleep(500);
				RequestDispatcher rd=request.getRequestDispatcher("cakes.html");
				rd.forward(request, response);
			}
//			else
//			{
//				pw.print("Invalid email and password");
//			}
		}
		else
		{
			pw.print("Invalid email and password");
		}
			
			
		
		}
		catch(Exception e1)
		{
			System.out.println(e1);
		}
	}

}
