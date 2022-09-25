package com.demo.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.demo.bean.AdminDetails;
import com.demo.bean.User;
import com.demo.dao.AdminDao;
import com.demo.dao.AdminDaoImpl;

public class AdminServiceImpl implements AdminService{
	AdminDao adminDao=new AdminDaoImpl();
	

	@Override
	public List<User> getByDisabledUser() {
		
		return adminDao.getByDisabledUser();
	}

	

	@Override
	public List<User> getByUserToDisable() {
		return adminDao.getByUserToDisable();

	}

	@Override
	public List<String> getDisabledUserName() {
		return adminDao.getDisabledUserName();

	}

	@Override
	public void enableUser(String s) {
		adminDao.enableUser(s);
		
	}
	
	public int getNoOfDiffCities() {
		//use getNoOfUsers & getNoOfDiffCities functions
		return adminDao.getNoOfDiffCities();
		}
	@Override
	public int getNoOfUsers() {
		//use getNoOfUsers & getNoOfDiffCities functions
		return adminDao.getNoOfUsers();
		}
	public String getAdmDetails(String s) {
		//use getNoOfUsers & getNoOfDiffCities functions
		return adminDao.getAdmDetails(s);
	}	


	@Override
	public void disableUser(String s) {
		// TODO Auto-generated method stub
		adminDao.disableUser(s);
		
	}
	
	
	//Onkar
	@Override
	public String getAdminDetails() {
		return adminDao.getAdminDetails();
	}

	//retrieve admin login data from xml
	public List<AdminDetails> getDetailsFromXml() {
		List<AdminDetails> alist=new ArrayList<>();
		File file = new File("AdminDetails.xml");  
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();  
		DocumentBuilder db;
		try {
			db = dbf.newDocumentBuilder();
			Document doc = db.parse(file); 
			doc.getDocumentElement().normalize();  
			System.out.println("Root element: " + doc.getDocumentElement().getNodeName());  
			NodeList nodeList = doc.getElementsByTagName("Admin");  
			// nodeList is not iterable, so we are using for loop  
			for (int itr = 0; itr < nodeList.getLength(); itr++)   
			{  
				Node node = (Node) nodeList.item(itr);   
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) node;  
					String email=eElement.getElementsByTagName("AEMAIL").item(0).getTextContent();  
					String name=eElement.getElementsByTagName("ANAME").item(0).getTextContent(); 
					String pass=eElement.getElementsByTagName("APASS").item(0).getTextContent(); 
					String mob=eElement.getElementsByTagName("AMOB").item(0).getTextContent(); 
					AdminDetails ad=new AdminDetails(email,name,pass,mob);
					alist.add(ad);
				}
			}
		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return alist;  	
	}


	//authenticate admin details
	public AdminDetails authenticateAdmin(String aname,String apass) {
		List<AdminDetails> alist=getDetailsFromXml();
		System.out.println(alist);
		for(AdminDetails ad:alist) {
			if(ad.getAname().equals(aname) && ad.getApass().equals(apass)) {
				System.out.println(ad);
				return ad;
		}
	}
		return null;

}
}
