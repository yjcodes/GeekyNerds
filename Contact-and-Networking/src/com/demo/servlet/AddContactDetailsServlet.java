package com.demo.servlet;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
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


//@WebServlet("/addcontact")
public class AddContactDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static SimpleDateFormat sdf;
	static {
		sdf=new SimpleDateFormat("yyyy-MM-dd");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();		
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		User user = (User) session.getAttribute("userdetails");
		String owneremail=user.getUemail();
		String email=(String) request.getParameter("cemail");
		String name=(String) request.getParameter("cname");
		String number=request.getParameter("num");
		String gender=request.getParameter("gender");
		String bdate=request.getParameter("date");
		String addr=request.getParameter("address");
		String country=request.getParameter("country");
		String state=request.getParameter("state");
		String city=request.getParameter("city");
		String company=request.getParameter("company");
		BufferedImage bImage=null;
		String image=request.getParameter("image");
		if(image!=null) {
			bImage = ImageIO.read(new File(image));
		}
		ContactDetails contact = null;
	
		Date dt=null;
		if(bdate!=null) {
			try {
				dt = sdf.parse(bdate);
				contact=new ContactDetails(email,name,number,gender,dt,addr,city,state,country,company, bImage);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		ContactService contactService=new ContactServiceImpl();
		
		//if contact exists in contactdetails update contactlist only
		if(contactService.checkIfContactExists(email)) {
			contactService.updateContactListForNewContact(owneremail,email);
			out.println("New contact added successfully");
			///change path to home page of user
			RequestDispatcher rd=request.getRequestDispatcher("addnewcontact.html");
			rd.forward(request, response);
		}
		
		//if contact does not exist in contactdetails update both tables
		else {
			int status=contactService.addNewContact(contact);
			contactService.updateContactListForNewContact(owneremail,email);
			out.println("New contact added successfully");
			System.out.println("New contact added successfully");
			///change path to home page of user
			RequestDispatcher rd=request.getRequestDispatcher("addnewcontact.html");
			rd.forward(request, response);
		}
		
	}

}
