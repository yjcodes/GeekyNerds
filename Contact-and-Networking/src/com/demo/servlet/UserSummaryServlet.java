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

import com.demo.bean.AdminDetails;
import com.demo.bean.User;
import com.demo.service.AdminService;
import com.demo.service.AdminServiceImpl;


/**
 * Servlet implementation class UserSummaryServlet
 */
public class UserSummaryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		HttpSession session=request.getSession();
		AdminDetails admin = (AdminDetails) session.getAttribute("admindetails");
		System.out.println(admin);
		AdminService adminService=new AdminServiceImpl();
		if(admin!=null) {
			int totUsers=adminService.getNoOfUsers();
			int totCities=adminService.getNoOfDiffCities();
		    request.setAttribute("admName", admin.getAname());
			request.setAttribute("admEmail", admin.getAemail());
			request.setAttribute("admMob", admin.getAmob());
			request.setAttribute("totUsers", totUsers);
			request.setAttribute("totCities", totCities);
			RequestDispatcher rd=request.getRequestDispatcher("summary.jsp");
			rd.forward(request, response);
		}else {
	
			System.out.println("Something Wrong");
			RequestDispatcher rd= request.getRequestDispatcher("adminindex.html");
			rd.forward(request, response);
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
}
