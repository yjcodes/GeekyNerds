package com.demo.servlet;

import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.demo.bean.ContactDetails;

import com.demo.bean.User;
import com.demo.service.ContactService;
import com.demo.service.ContactServiceImpl;


/**
 * Servlet implementation class UpdateProduct
 */
//@WebServlet("/update")
public class UpdateContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();		
		User user = (User) session.getAttribute("userdetails");
		String owneremail=user.getUemail();

		
		String cemail=request.getParameter("cemail");
		String cname=request.getParameter("cname");
		String cmob=request.getParameter("cmob");
		String ccity=request.getParameter("ccity");
			
			
		ContactDetails c=new ContactDetails(cemail,cname,cmob,ccity);
			
		ContactService pService=new ContactServiceImpl();
		System.out.println(c.getCfullName());
			
		pService.updatecontact(c);
		System.out.println("product updated");
			
			
			
		RequestDispatcher rd=request.getRequestDispatcher("viewcontact");
		rd.forward(request, response);
		
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 doPost(request,response);
	}
   
   }


