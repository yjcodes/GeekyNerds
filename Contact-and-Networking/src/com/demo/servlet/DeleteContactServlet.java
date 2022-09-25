package com.demo.servlet;

import java.io.IOException
;
import java.io.PrintWriter;
import java.util.Arrays;
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
 * Servlet implementation class DeleteProduct
 */
//@WebServlet("/delete")
public class DeleteContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
			PrintWriter out=response.getWriter();
			response.setContentType("text/html");
			HttpSession session=request.getSession();		
			User user = (User) session.getAttribute("userdetails");
			String owneremail=user.getUemail();
			String[] email =request.getParameterValues("delete");
			System.out.println(email);
			ContactService contactService=new ContactServiceImpl();
			List<String> uelist=contactService.getUserEmail(owneremail);
			List<String> celist=contactService.getContactEmail(owneremail);
			for( String s:email) { 
				  if(uelist.contains(s))
				  {    System.out.println("In user delete");
					   User c= contactService.getByUserEmail(s);
				        contactService.deleteUserList(c); 
				  }
				  else
				  {
				    ContactDetails c= contactService.getByContactEmail(s);
			        contactService.deleteContact(c);     
				  }
			  }
			
		     System.out.println("Contact deleted");
		     RequestDispatcher rd=request.getRequestDispatcher("viewcontact");
			 rd.forward(request, response);
			
			
		
		
		
		
	}
	

}
