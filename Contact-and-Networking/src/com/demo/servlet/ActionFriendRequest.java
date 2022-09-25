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

import com.demo.bean.User;
import com.demo.service.FriendService;
import com.demo.service.FriendServiceImpl;

/**
 * Servlet implementation class ActionFriendRequest
 */
//@WebServlet("/actionfriendrequest")
public class ActionFriendRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
    	response.setContentType("text/html");
  
    	HttpSession session = request.getSession();
    	User user = (User) session.getAttribute("userdetails");
    	FriendService friendService = new FriendServiceImpl();
    	
    	String receiverEmail = user.getUemail();  //get mail from session
    	if(receiverEmail!=null) {
    	String senderEmail = request.getParameter("senderEmail");
		String buttonValue = request.getParameter("actionbutton");
		System.out.println("Sender Umail:" + senderEmail + buttonValue);
		String fullName = friendService.requestAction(receiverEmail, senderEmail, buttonValue);
		if(buttonValue.equals("accept"))
			out.print("You are now friends with " + fullName);
		else if(buttonValue.equals("ignore"))
			out.print("You have ignored " + fullName + "'s friend request");
		else
			out.print("You have blocked " + fullName);
		RequestDispatcher rd = request.getRequestDispatcher("welcome.jsp");
		rd.forward(request, response);
	}
	else {
		out.println("Please authorize first");
		RequestDispatcher rd = request.getRequestDispatcher("HomePage.html");
		rd.include(request, response);
	}
}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
//insert into friendrequest(recieveremail,senderemail) values ('oj_52@yahoo.com','krishna@gmail.com');