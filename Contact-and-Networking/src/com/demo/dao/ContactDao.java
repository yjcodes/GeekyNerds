package com.demo.dao;

import java.awt.image.BufferedImage;

import java.sql.Blob;
import java.util.List;

import com.demo.bean.ContactDetails;

import com.demo.bean.User;
//ContactDao interface
public interface ContactDao {

	
	boolean checkIfExists(String cemail);

	int addNewContact(ContactDetails contact);

	void updateContactListForUser(String uemail, String email);

	void updateContactListForNewContact(String uemail, String email);

	boolean checkIfContactExists(String email);

	List<ContactDetails> getAllContacts();
	
	public Blob convertToBlob(BufferedImage bImage);
	
	public BufferedImage convertToBufferedImage(Blob image);

	
	
	
	
	
	
	
	
	
	
	//Rameshwar
	List<String> getUserEmail(String owneremail);

	List<String> getContactEmail(String owneremail);

	User getByUserEmail(String s);

	ContactDetails getByContactEmail(String p);

	void deleteUserList(User u);

	void deleteContact(ContactDetails c);


	void updatecontact(ContactDetails c);

	ContactDetails getByCity(String city);

	List<ContactDetails> getAllByCity(String city, List<String> celist);

	List<User> getUserCity(String city, String owneremail);

	List<ContactDetails> getContactrCity(String city);

	ContactDetails getContactCityByMail(String p);

}
