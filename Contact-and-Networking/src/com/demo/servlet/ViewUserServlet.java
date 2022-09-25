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
import com.demo.service.UserService;
import com.demo.service.UserServiceImpl;
import com.google.gson.Gson;

/**
 * Servlet implementation class ViewUserServlet
 */
@WebServlet("/ViewUserServlet")
public class ViewUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		System.out.println("in init method");
		
	}
	public void destroy() {
		System.out.println("in destroy");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		User user = (User) session.getAttribute("userdetails");
		String useremail=user.getUemail();
		UserService userservice = new UserServiceImpl();
		List<User> ulist = null;
		System.out.println("viewservlet");
		if(request.getParameter("check").equals("name")) {
			String name = request.getParameter("choice");
			ulist = userservice.searchByName(name,useremail);
		}
		else if(request.getParameter("check").equals("city")) {
			String city = request.getParameter("choice");
			System.out.println("city");
			ulist = userservice.searchByCity(city,useremail);
		}
		else if(request.getParameter("check").equals("company")) {
			String company = request.getParameter("choice");
			ulist = userservice.searchByCompany(company,useremail);
		}
		Gson gson = new Gson(); 
		String jsonNames = gson.toJson(ulist);
		out.println(jsonNames);
	}

}
