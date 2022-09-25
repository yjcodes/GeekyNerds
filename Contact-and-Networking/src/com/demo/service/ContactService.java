package com.demo.service;

import java.util.List;


import com.demo.bean.ContactDetails;
import com.demo.bean.User;

public interface ContactService {

	boolean checkIfExists(String cemail);

	int addNewContact(ContactDetails contact);

	void updateContactListForUser(String uemail, String email);

	void updateContactListForNewContact(String uemail, String email);

	boolean checkIfContactExists(String email);

	
	
	
	
	
	//Rameshwar
	List<String> getUserEmail(String owneremail);

	List<String> getContactEmail(String owneremail);

	User getByUserEmail(String s);

	ContactDetails getByContactEmail(String p);

	void deleteUserList(User u);

	void deleteContact(ContactDetails c);

	/* void updatecontact(UserDetails u); */

	void updatecontact(ContactDetails c);

	ContactDetails searchBycity(String city);

	List<ContactDetails> getAllByCity(String city, List<String> celist);

	List<User> getUserCity(String city, String owneremail);

	

	List<ContactDetails> getContactCity(String city);

	ContactDetails getContactCityByMail(String p);

}
