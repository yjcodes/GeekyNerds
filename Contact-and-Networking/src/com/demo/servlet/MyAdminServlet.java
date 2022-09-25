package com.demo.servlet;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.Node;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.demo.bean.AdminDetails;
import com.demo.service.AdminService;
import com.demo.service.AdminServiceImpl;

public class MyAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		AdminService a=new AdminServiceImpl();
		String xmlString=a.getAdminDetails();
		try(DataOutputStream dos=new DataOutputStream(new FileOutputStream("AdminDetails.xml"));) {
			dos.writeChars(xmlString);
			System.out.println("Written!!");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		
		String aname=request.getParameter("aname");
		String apass=request.getParameter("apass");
		AdminDetails admin= a.authenticateAdmin(aname, apass);
		if(admin!=null) {
		HttpSession session=request.getSession();

	    if(session.isNew()) {
	    	session.setAttribute("admindetails", admin);
	    }			
		RequestDispatcher rd=request.getRequestDispatcher("adminhomepage.jsp");
		rd.forward(request, response);
		
		}else {
			
			out.println("you are not authrized user");
			RequestDispatcher rd=request.getRequestDispatcher("adminindex.html");
			rd.include(request, response);
		}
				
			
		
		
	}

}
