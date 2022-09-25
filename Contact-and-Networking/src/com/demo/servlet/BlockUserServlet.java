package com.demo.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.demo.bean.User;
import com.demo.service.UserService;
import com.demo.service.UserServiceImpl;

/**
 * Servlet implementation class BlockUserServlet
 */
@WebServlet("/BlockUserServlet")
public class BlockUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		System.out.println("in init method");
		
	}
	public void destroy() {
		System.out.println("in destroy");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session= request.getSession();
		PrintWriter out = response.getWriter();
		User user = (User) session.getAttribute("userdetails");
		String useremail=user.getUemail();
		String uemail = request.getParameter("uemail");
		//System.out.println("UID in add block : " + uid);
		UserService userService = new UserServiceImpl();
		int result = userService.addToBlockList(uemail,useremail);
		System.out.println("add to block list");	//message display in console
	}

}
