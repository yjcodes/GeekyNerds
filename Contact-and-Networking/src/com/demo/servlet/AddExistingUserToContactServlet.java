package com.demo.servlet;

import java.awt.image.BufferedImage;

import java.io.ByteArrayInputStream;
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
import com.demo.service.UserService;
import com.demo.service.UserServiceImpl;

import sun.misc.BASE64Decoder;

//@WebServlet("/addexistingtocontact")
public class AddExistingUserToContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static SimpleDateFormat sdf;
	static {
		sdf=new SimpleDateFormat("yyyy-MM-dd");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		HttpSession session=request.getSession();		
		User user = (User) session.getAttribute("userdetails");
		String owneremail=user.getUemail();
		
		String email=(String) request.getParameter("email");
		String name=(String) request.getParameter("name");
		String number=request.getParameter("phone");
		String gender=request.getParameter("gender");
		String bdate=request.getParameter("bdate");
		String addr=request.getParameter("address");
		String country=request.getParameter("country");
		String state=request.getParameter("state");
		String city=request.getParameter("city");
		String company=request.getParameter("company");
		String bimage=request.getParameter("bimage");
		BufferedImage bImage =null;
		//BufferedImage bImage =(BufferedImage)bimage;
		System.out.println(bimage);
		//byte[] byteArrray = bimage.getBytes();
		//ByteArrayInputStream bais = new ByteArrayInputStream(byteArrray);
        //BufferedImage bImage = ImageIO.read(bais);
        //System.out.println(bImage);
		//bImage = ImageIO.read(new File(image));
		/*
		 * if(image!=null) { bImage = ImageIO.read(new File(image)); }
		 */
		
		BASE64Decoder decoder = new BASE64Decoder();
        byte[] imgBytes = decoder.decodeBuffer(bimage);   
        bImage = ImageIO.read(new ByteArrayInputStream(imgBytes));
		Date dt;
		try {
			dt = sdf.parse(bdate);
			ContactDetails contact=new ContactDetails(email,name,number,gender,dt,addr,country,state,city,company,bImage);
		
			ContactService contactService=new ContactServiceImpl();
			
			if(contactService.checkIfContactExists(email)) {
				///change 1 to uid
				contactService.updateContactListForUser(owneremail,email);
				///change path to home page of user
				RequestDispatcher rd=request.getRequestDispatcher("addnewcontact.html");
				rd.forward(request, response);
			}
			
			else {
				int status=contactService.addNewContact(contact);
				///change 1 uid
				contactService.updateContactListForNewContact(owneremail, email);
				///change path to home page of user
				RequestDispatcher rd=request.getRequestDispatcher("addnewcontact.html");
				rd.forward(request, response);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
