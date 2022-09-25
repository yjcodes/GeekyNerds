package com.demo.service;

import java.util.List;

import com.demo.bean.AdminDetails;
import com.demo.bean.User;

public interface AdminService {

//	
/*
	List<User> getByDisabledUser();

	void enableUser(String s);

	List<String> getDisabledUserId();

	User getByUsername(String s);

	void deleteUser(String s);

	void deleteSelectedUser(String[] username);

	List<User> getByUserToDisable();

	void disableSelectedUser(String[] username);
*/

	
	List<User> getByDisabledUser();


	List<User> getByUserToDisable();

	List<String> getDisabledUserName();

	void enableUser(String s);
	
	int getNoOfDiffCities();

	int getNoOfUsers();

	String getAdmDetails(String s);

	void disableUser(String s);

	String getAdminDetails();
	
	List<AdminDetails> getDetailsFromXml();
	
	AdminDetails authenticateAdmin(String aname,String apass);

}
