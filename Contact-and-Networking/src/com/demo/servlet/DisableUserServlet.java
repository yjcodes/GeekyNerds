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
import com.demo.service.AdminService;
import com.demo.service.AdminServiceImpl;

/**
 * Servlet implementation class DisableUser
 */
//@WebServlet("/disableuser")
public class DisableUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		PrintWriter out=response.getWriter();
	

		AdminService adminService=new AdminServiceImpl();
		List<User> ulist=adminService.getByUserToDisable();
		request.setAttribute("ulist",ulist);
		RequestDispatcher rd=request.getRequestDispatcher("usertodisable.jsp");
		rd.forward(request, response);	
		
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
