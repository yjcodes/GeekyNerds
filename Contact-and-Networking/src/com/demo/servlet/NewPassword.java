package com.demo.servlet;

import java.io.IOException;
import java.io.PrintWriter;

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

/**
 * Servlet implementation class NewPassword
 */
//@WebServlet("/NewPassword")
public class NewPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		User user_forgetpass=(User) session.getAttribute("user_forgetpass");
		System.out.println(user_forgetpass);
		String pass=request.getParameter("pass");
		String cpass=request.getParameter("cpass");
		if(pass.equals(cpass))
		{
			LoginService loginService=new LoginServiceImpl();
			String user_email=user_forgetpass.getUemail();
			System.out.println("here");
			System.out.println(user_email);
			
			int success=loginService.updatePass(user_email,pass,cpass);
			if (success==1)
			{
		;
				out.println("updated successfully");

			}
			else
			{
				out.println("error while updating");
			}
		}
		else {

			out.println("Password and Confirm Password do not match");
		}
	
					
	}

}
