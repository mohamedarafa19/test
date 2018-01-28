package com.example.test;

import java.io.IOException;
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
 * Servlet implementation class second
 */
public class second extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public second() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "jdbc:mysql://johnny.heliohost.org/devmoham_register";
        String userNameDB = "devmoham_admin";
        String passwordDB = "AaAa123456";
        Connection con;
        PreparedStatement pst;
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String married = request.getParameter("married");
        String sex = request.getParameter("sex");
        String age = request.getParameter("age");
        String day = request.getParameter("day");
        int child = Integer.parseInt(request.getParameter("childern"));
        String city = request.getParameter("city");
        String address = request.getParameter("address");
        String sql;
        String msg;
        
        try 
        {
            sql = "Select * From client Where Phone = " + phone + " OR Email = '" + email + "'";
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, userNameDB, passwordDB);
            pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            if(rs.next())
            {
                msg = "Email / Phone is used ,please change your value";
                response.getWriter().append("<html>");
                response.getWriter().append("<head>");
                response.getWriter().append("<title>Error Register</title>");
                response.getWriter().append("</head>");
                response.getWriter().append("<body>");
                response.getWriter().append("<h1>" + msg + "</h1>");
                response.getWriter().append("<form action=main.html>");
                response.getWriter().append("<button style=color:white;Background-color:blue; >Back Up</button>");
                response.getWriter().append("</form>");
                response.getWriter().append("</body>");
                response.getWriter().append("</html>");
            }
            else
            {
                sql = "Insert Into client (Name,Email,Phone,Married,Sex,Age,Day,Child,City,Address) Values ('" + name +
                    "','" + email + 
                    "','" + phone + 
                    "','"+ married + 
                    "','" + sex + 
                    "','" + age + 
                    "','" + day + 
                    "','" + child +
                    "','" + city + 
                    "','" + address + "')";

                    pst = con.prepareStatement(sql);
                    pst.execute();                    
                    response.sendRedirect("main.html");
            }
         }catch (ClassNotFoundException ex) {
                    response.getWriter().append("First ========== " + ex.getMessage());
        }
        catch(SQLException ex)
        {
        	response.getWriter().append("Second ========== " + ex.getMessage());
        }
     }
}