package com.demo.servlet;

import java.io.IOException;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.demo.bean.User;
import com.demo.service.ContactService;
import com.demo.service.ContactServiceImpl;
import com.demo.service.UserService;
import com.demo.service.UserServiceImpl;

/**
 * Servlet implementation class EnterDataServlet
 */
//@WebServlet("/entercontactdata")
public class EnterContactDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		String cname=request.getParameter("name");
		String cemail=request.getParameter("email");
		ContactService contactService=new ContactServiceImpl();
		boolean flag=contactService.checkIfExists(cemail);
		if(flag) {
			UserService userService=new UserServiceImpl();
			User user=userService.searchUserByEmail(cemail);
			request.setAttribute("user",user);
			out.println("The contact is a user. Fetching data");
			RequestDispatcher rd=request.getRequestDispatcher("addexistinguser.jsp");
			rd.forward(request, response);
		}
		else {
			request.setAttribute("cname",cname);
			request.setAttribute("cemail",cemail);
			RequestDispatcher rd=request.getRequestDispatcher("addnewcontactdetails.jsp");
			rd.forward(request, response);
		}
	}

}
