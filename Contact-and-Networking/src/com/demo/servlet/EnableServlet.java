package com.demo.servlet;

import java.io.IOException;
import java.util.List;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;



import com.demo.bean.User;
import com.demo.service.AdminService;
import com.demo.service.AdminServiceImpl;

/**
 * Servlet implementation class EnableServlet
 */
//@WebServlet("/enable")
public class EnableServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		
		
		String[] username =request.getParameterValues("enable");
		AdminService adminService=new AdminServiceImpl();
		List<String> ulist=adminService.getDisabledUserName();
			
		  for( String s:username) 
		  {     
			  if(ulist.contains(s))
					{ 
			         adminService.enableUser(s);
			         System.out.println("User Enabled");
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
