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
import javax.servlet.http.HttpSession;

import com.demo.bean.ContactDetails;

import com.demo.bean.User;
import com.demo.service.ContactService;
import com.demo.service.ContactServiceImpl;

/**
 * Servlet implementation class DisplayContactServlet
 */
//@WebServlet("/viewcontact")
public class DisplayContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		PrintWriter out=response.getWriter();
		User user= (User) session.getAttribute("userdetails");
		String owneremail=user.getUemail();
		/*
		 * if(owneremail==null) {
		 * session.setAttribute("owneremail",request.getParameter("owneremail"));
		 * 
		 * }
		 */
		
		//owneremail=(String)session.getAttribute("owneremail"); 	
		
	
		ContactService contactService=new ContactServiceImpl();
		List<String> uelist=contactService.getUserEmail(owneremail);
		List<String> celist=contactService.getContactEmail(owneremail);
		
	    System.out.println(owneremail);
		System.out.println(uelist);
		System.out.println(celist);
		List<User> ulist=new ArrayList<>();
		List<ContactDetails> clist=new ArrayList<>();
		for(String s:uelist)
		{   if(s!=null)
	     	{
			     ulist.add(contactService.getByUserEmail(s));
			     System.out.println(contactService.getByUserEmail(s));
			  
		    }

		   
		}   
		
		for(String p:celist)	
		{
			if(p!=null)
			{
				clist.add(contactService.getByContactEmail(p));
			    System.out.println(contactService.getByContactEmail(p));
			   
			}
			
		}    
		request.setAttribute("ulist",ulist);
		request.setAttribute("clist",clist);
		RequestDispatcher rd=request.getRequestDispatcher("displaycontacts.jsp");
		rd.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 doGet(request,response);
	}


}
