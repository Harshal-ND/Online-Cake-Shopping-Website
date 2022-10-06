package servlet_package;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




@WebServlet("/servlet_register1")
public class servlet_register1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		
		String n=request.getParameter("c_name");
		String e=request.getParameter("c_email");
		String p=request.getParameter("c_password");
		String a=request.getParameter("c_address");
		String pin=request.getParameter("c_pincode");
		String m=request.getParameter("c_mob_no");
		
		bean b1=new bean();
		
		b1.setC_name(n);
		b1.setC_email(e);
		b1.setC_password(p);
		b1.setC_address(a);
		b1.setC_pincode(pin);
		b1.setC_mob_no(m);
		
		try
		{
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/adv_java","root","");
			PreparedStatement ps=con.prepareStatement("insert into c_register (c_name, c_email, c_password, c_address,c_pincode, c_mob_no) values(?,?,?,?,?,?)");
			
			ps.setString(1, n);
			ps.setString(2, e);
			ps.setString(3, p);
			ps.setString(4, a);
			ps.setString(5, pin);
			ps.setString(6, m);
			
			int k=ps.executeUpdate();
			
			
			if(k>0)
			{
				pw.print("Successfully added");
				Thread.sleep(500);
				RequestDispatcher rd=request.getRequestDispatcher("login.html");
				rd.forward(request, response);
				//response.sendRedirect("login.html");

			}
			else
			{
				pw.print("Errror!!!");
				RequestDispatcher rd=request.getRequestDispatcher("/register.html");
				rd.include(request, response);
			}
		}
		catch(Exception e1)
		{
			System.out.println(e1);
		}
		
	}

}
