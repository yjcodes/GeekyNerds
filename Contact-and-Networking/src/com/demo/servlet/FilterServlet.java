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
 * Servlet implementation class FilterServlet
 */
//@WebServlet("/filter")
public class FilterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();		
		User user = (User) session.getAttribute("userdetails");
		String owneremail=user.getUemail();
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
	    String city=request.getParameter("filter1");
	    ContactService contactService=new ContactServiceImpl();
	    List<String> uelist=contactService.getUserEmail(owneremail);
		List<String> celist=contactService.getContactEmail(owneremail);
		List<ContactDetails> citylist=contactService.getAllByCity(city,celist); 
	    List<User> ucity=contactService.getUserCity(city,owneremail);
	    List<ContactDetails> ccity=contactService.getContactCity(city);
	    System.out.println(ucity);
		request.setAttribute("CityDetails",citylist);
	    request.setAttribute("UserDetailsCity",ucity);
	    request.setAttribute("ContactDetailsCity",ccity);
	    request.setAttribute("owneremail",owneremail);
		RequestDispatcher rd=request.getRequestDispatcher("filter.jsp");
		rd.forward(request, response);
	}

	

}
