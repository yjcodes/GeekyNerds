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

import com.demo.bean.ContactDetails;
import com.demo.bean.User;
import com.demo.service.ContactService;
import com.demo.service.ContactServiceImpl;


/**
 * Servlet implementation class EditProduct
 */
//@WebServlet("/edit")
public class EditContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
				PrintWriter out=response.getWriter();
				HttpSession session=request.getSession();		
				User user = (User) session.getAttribute("userdetails");
				
			
			    String cemail=request.getParameter("cEmail");
			    
				ContactService contactService=new ContactServiceImpl();
				
				
					
				ContactDetails c=contactService.getByContactEmail(cemail);
						
				request.setAttribute("ContactDetails",c);
				System.out.println(c);
				RequestDispatcher rd=request.getRequestDispatcher("editcontact.jsp");
				rd.forward(request, response);
				
	}
}
			
						
			
		
		
			
			
		
		



