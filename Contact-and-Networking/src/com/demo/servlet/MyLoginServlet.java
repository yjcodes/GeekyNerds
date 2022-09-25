package com.demo.servlet;

import java.io.IOException;


import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.demo.bean.User;
import com.demo.exception.UserNotFoundException;
import com.demo.service.LoginService;
import com.demo.service.LoginServiceImpl;


public class MyLoginServlet extends HttpServlet {

	public void destroy() {
		System.out.println("in destroy");
	}
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
		
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		String uname=request.getParameter("uname");
		String pass=request.getParameter("pass");
		LoginService loginService=new LoginServiceImpl();
		
		try {
		User user=loginService.validateUser(uname,pass);
		if(user!=null){
		
			HttpSession session=request.getSession();
			//session.setAttribute("user details", user);
		    if(session.isNew()) {
		    	session.setAttribute("userdetails", user);
		    }			
			RequestDispatcher rd=request.getRequestDispatcher("welcome.jsp");
			rd.forward(request, response);
		}
		}catch(UserNotFoundException e) {
			System.out.println(e);
			//alert("Please authorize first.");
			out.println("you are not authrized user");
			RequestDispatcher rd=request.getRequestDispatcher("userindex.html");
			rd.include(request, response);
			
		}
		
		
		
	}

}
