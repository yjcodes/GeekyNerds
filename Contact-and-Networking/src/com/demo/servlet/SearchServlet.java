package com.demo.servlet;

import java.io.IOException;
import java.io.PrintWriter;
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
 * Servlet implementation class SearchServlet
 */
//@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();		
		User user = (User) session.getAttribute("userdetails");
		String owneremail=user.getUemail();
		
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		String param1 = request.getParameter("prodName");
		ContactService contactService=new ContactServiceImpl();
		List<String> uelist=contactService.getUserEmail(owneremail);
		List<String> celist=contactService.getContactEmail(owneremail);
		System.out.println(owneremail);
		System.out.println(param1);
		
			  if(uelist.contains(param1))
			  {    System.out.println("In user search");
			       User u= contactService.getByUserEmail(param1);
			       request.setAttribute("UserDetails",u);
			  }
			  else
			  {
				  ContactDetails c= contactService.getByContactEmail(param1);
				  request.setAttribute("ContactDetails",c);
   
			  }
		
		request.setAttribute("owneremail",owneremail);
		RequestDispatcher rd=request.getRequestDispatcher("search.jsp");
		rd.forward(request, response);
		
	}

	
}
