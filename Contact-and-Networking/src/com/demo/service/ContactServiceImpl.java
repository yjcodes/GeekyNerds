package com.demo.service;

import java.util.List;


import com.demo.bean.ContactDetails;

import com.demo.bean.User;
import com.demo.dao.ContactDao;
import com.demo.dao.ContactDaoImpl;
//ContactService implementation class which implements ContactService Interface

public class ContactServiceImpl implements ContactService {
	
     private ContactDao contactDao;
	//Constructor
     public ContactServiceImpl(){
 		contactDao=new ContactDaoImpl();
 		}
 	
   //this funtion is called to check if the Contact exist in the user details
 	@Override
 	public boolean checkIfExists(String cemail) {
 		return contactDao.checkIfExists(cemail);
 		
 	}
 	
 	//to add new contact in contact details
 	@Override
 	public int addNewContact(ContactDetails contact) {
 		return contactDao.addNewContact(contact);
 	}

 	//update contactlist table for existing user
 	@Override
 	public void updateContactListForUser(String uemail, String email) {
 		
 		contactDao.updateContactListForUser(uemail,email);
 		
 	}

 	//method update ContactList table with new contact not an user
 	@Override
 	public void updateContactListForNewContact(String uemail, String email) {

 		contactDao.updateContactListForNewContact(uemail,email);
 		
 	}

 	//to check if the contact exist in contactdetails database table
 	@Override
 	public boolean checkIfContactExists(String email) {
 		
 		return contactDao.checkIfContactExists(email);
 	}
	
 	//to get the useremail from contactlist (here owner is the loggedin user)
	@Override
	public List<String> getUserEmail(String owneremail) {
		// TODO Auto-generated method stub
		return contactDao.getUserEmail(owneremail);
	}
	//to get contact email from contactlist
	@Override
	public List<String> getContactEmail(String owneremail) {
		// TODO Auto-generated method stub
		return contactDao.getContactEmail(owneremail);
	}
	
	//get user by useremail from userdetails(to be used for contact purpose)
	@Override
	public User getByUserEmail(String s) {
		// TODO Auto-generated method stub
		return contactDao.getByUserEmail(s);
	}
	
	//get contact by contact email from contact contactdetails database table
	@Override
	public ContactDetails getByContactEmail(String p) {
		// TODO Auto-generated method stub
		return contactDao.getByContactEmail(p);
	}

	//to delete the user from contactlist
	@Override
	public void deleteUserList(User u) {
		// TODO Auto-generated method stub
		  contactDao.deleteUserList(u);
	}
	//to delete contact from contact list and contact details
	@Override
	public void deleteContact(ContactDetails c) {
		// TODO Auto-generated method stub
		contactDao.deleteContact(c);
		
	}

	//to update the contact details (contact name,full name, city ,mobile)
	@Override
	public void updatecontact(ContactDetails c) {
		// TODO Auto-generated method stub
		contactDao.updatecontact(c);
	}
	
	@Override
	public ContactDetails searchBycity(String city) {
		// TODO Auto-generated method stub
		return contactDao.getByCity(city);
	}

	@Override
	public List<ContactDetails> getAllByCity(String city,List<String> celist) {
		// TODO Auto-generated method stub
		return contactDao.getAllByCity(city,celist);
	}
	//to get the user by city as filter
	@Override
	public List<User> getUserCity(String city,String owneremail) {
		// TODO Auto-generated method stub
		return contactDao.getUserCity(city,owneremail);
	}

	//to get the contact by city as filter
	@Override
	public List<ContactDetails> getContactCity(String city) {
		// TODO Auto-generated method stub
		return contactDao.getContactrCity(city);
	}

	@Override
	public ContactDetails getContactCityByMail(String p) {
		// TODO Auto-generated method stub
		return contactDao.getContactCityByMail(p);
	}
	
	

}
