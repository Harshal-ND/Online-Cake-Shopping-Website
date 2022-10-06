package servlet_package;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/servlet_book_order")
public class servlet_book_order extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public servlet_book_order() {
        super();
        
    }

	
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
			PreparedStatement ps=con.prepareStatement("select c_email,c_password,c_id,c_name from c_register where c_email=? and c_password=? ");
		ps.setString(1, e);
		ps.setString(2, p);
		ResultSet rs=ps.executeQuery();
		if(rs.next())
		{
			
			
			if(e.equals(rs.getString(1)) && p.equals(rs.getString(2)))
			{	
//				pw.println("\nCorrect Email and Id");
//				pw.println(" ");
//				pw.println("\nPlease note your Customer ID and Customer Name");
//				pw.println(" ");
				pw.println("\nCustomer Id :" +rs.getInt(3));
				pw.println(" ");
				pw.println("\nCustomer name :" +rs.getString(4));
				pw.println(" ");
				
//				Thread.sleep(1000);
//				RequestDispatcher rd=request.getRequestDispatcher("add_order_details.html");
//				rd.forward(request, response);
				
				pw.print("<form action='add_order_details.html' method='post'> ");
				pw.print(" ");
				pw.print(" ");
				pw.print("<button type='submit'>GO TO ADD ORDER DETAILS</button> ");
				pw.print("</form> ");
			}
			

		}
		
		else
		{
			pw.print("Invalid email and password");
			RequestDispatcher rd=request.getRequestDispatcher("/cakes.html");
			rd.include(request, response);
			
		}
			
			
		
		}
		catch(Exception e1)
		{
			System.out.println(e1);
		}
	}

}
