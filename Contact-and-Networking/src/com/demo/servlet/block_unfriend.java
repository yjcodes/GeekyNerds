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
 * Servlet implementation class block_unfriend
 */
//@WebServlet("/block_unfriend")
public class block_unfriend extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		User user = (User) session.getAttribute("userdetails");
		String uemail=user.getUemail();
		FriendService friendService=new FriendServiceImpl();
		String fEmail=request.getParameter("sourceId");
		if(request.getParameter("block") != null)
		{
			
			friendService.blockFriendByEmail(uemail,fEmail);
			//String uemail=request.getParameter("block");
			//userService.BlockFriend();
		}
		else if(request.getParameter("unfriend") != null)
		{
			out.println(request.getParameter("unfriend"));
			friendService.removeFriend(uemail,fEmail);
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("welcome.jsp");
		rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
