package com.demo.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.demo.bean.User;
import com.demo.service.FriendService;
import com.demo.service.FriendServiceImpl;



public class ViewFriends1 extends HttpServlet {
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		System.out.println("here in init method");
		 
		 
		
	}
	public void destroy() {
		System.out.println("in destroy");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		User user = (User) session.getAttribute("userdetails");
		FriendService friendService=new FriendServiceImpl();
		// here user is the user name that will be passed through the login
		//String user=(String) session.getAttribute("user");
		String uemail= user.getUemail();
		List<User> ulist=null;
		if(request.getParameter("name").equals("name"))
		{
			String fname=request.getParameter("uname");
			ulist=friendService.SearchByName(uemail,fname);
		}
		else if(request.getParameter("name").equals("city")){
			String city=request.getParameter("uname");
			ulist=friendService.SearchByCity(uemail,city);
		}
		
		request.setAttribute("ulist",ulist);
	    RequestDispatcher rd=request.getRequestDispatcher("viewfriends.jsp");
	    rd.forward(request, response);
	}

	
	

}
