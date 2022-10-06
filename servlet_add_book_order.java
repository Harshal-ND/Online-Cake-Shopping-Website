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


@WebServlet("/servlet_add_book_order")
public class servlet_add_book_order extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public servlet_add_book_order() {
        super();
        
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		
		String id=request.getParameter("c_id");
		String n=request.getParameter("c_name");
		String pid=request.getParameter("p_id");
		String qty=request.getParameter("o_qty");
		
		
		
		
		
		try
		{
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/adv_java","root","");
			PreparedStatement ps=con.prepareStatement("insert into order_details (c_id, c_name, p_id, o_qty) values(?,?,?,?)");
			
			ps.setString(1, id);
			ps.setString(2, n);
			ps.setString(3, pid);
			ps.setString(4, qty);
			
			
			int l=ps.executeUpdate();
			
			
			if(l>0)
			{
				pw.print("Successfully added");
				Thread.sleep(500);
				RequestDispatcher rd=request.getRequestDispatcher("cakes.html");
				rd.forward(request, response);
				//response.sendRedirect("login.html");

			}
			else
			{
				pw.print("Errror!!!");
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


