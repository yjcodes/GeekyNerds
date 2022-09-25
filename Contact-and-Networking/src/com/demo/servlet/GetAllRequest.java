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

import com.demo.bean.User;
import com.demo.service.FriendService;
import com.demo.service.FriendServiceImpl;

/**
 * Servlet implementation class GetAllRequest
 */
//@WebServlet("/getrequest")
public class GetAllRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    	PrintWriter out = response.getWriter();
    	response.setContentType("text/html");
  
    	HttpSession session = request.getSession();
    	User user = (User) session.getAttribute("userdetails");
    	String uemail=user.getUemail();  
    	FriendService friendService = new FriendServiceImpl();
    	if(uemail != null) {   
    		
    		//*******When user clicks on add friend on any page then add its entry in friend request receive and send table both
    		List<User> ulist = friendService.getFriendRequests(uemail);
    		request.setAttribute("ulist", ulist);
    		RequestDispatcher rd = request.getRequestDispatcher("friendrequest.jsp");
			rd.forward(request, response);	
    	}else {
    		//Redirect to profile page
    		
    		out.println("Please authorize first");
    		RequestDispatcher rd = request.getRequestDispatcher("HomePage.html");
			rd.include(request, response);
    	}
    	
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}
