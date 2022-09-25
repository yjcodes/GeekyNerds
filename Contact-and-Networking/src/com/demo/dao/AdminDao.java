package com.demo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;

import com.demo.bean.User;

public interface AdminDao {


/*
	List<String> getDisabledUserId(); 
	
	void enableUser(String s);
	
	List<User> findByDisableFlag() throws SQLException;

	void deleteUser(User u);

	User getByUsername(String s);

	void deleteSelectedUser(String[] username);

	void disableSelectedUser(String[] username);

	List<User> getByUserToDisable();
*/
	public Document toDocument(ResultSet rs) throws ParserConfigurationException, SQLException ;

	public String getAdminDetails();

	public String getStringFromDoc(org.w3c.dom.Document doc);

	
	List<User> getByDisabledUser();


	List<User> getByUserToDisable();

	void enableUser(String s);

	List<String> getDisabledUserName();

	
	int getNoOfDiffCities();

	int getNoOfUsers();

	String getAdmDetails(String s);

	void disableUser(String s);

}
