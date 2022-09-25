package com.demo.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.demo.bean.User;
import com.demo.service.AdminService;
import com.demo.service.AdminServiceImpl;

/**
 * Servlet implementation class DisableServlet
 */
public class DisableServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		
		
		String[] username =request.getParameterValues("disable");
		System.out.println(username);
		AdminService adminService=new AdminServiceImpl();
		//adminService.disableSelectedUser(username);
		List<User> ulist=adminService.getByUserToDisable();
		for(User u:ulist)
		{
		  for( String s:username) 
		  {
			  if(u.getUname().equals(s))
					{ 
				     adminService.disableUser(s);
			         System.out.println("User Disable");
			       }
		 }
	}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
