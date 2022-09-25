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
import com.demo.service.LoginService;
import com.demo.service.LoginServiceImpl;
import com.demo.service.UserService;
import com.demo.service.UserServiceImpl;

//@WebServlet("/ConfirmEmail")
public class ConfirmEmail extends HttpServlet {

	//protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		String uemail=request.getParameter("uemail");
		String uques=request.getParameter("uques");
		String uans=request.getParameter("uans");
		LoginService loginService=new LoginServiceImpl();
		User user_forgetpass=loginService.uemailVerify(uemail,uques,uans);
	
		session.setAttribute("user_forgetpass", user_forgetpass);
		RequestDispatcher rd=request.getRequestDispatcher("NewPassword.jsp");
		rd.forward(request, response);
		 
		
	}

}
