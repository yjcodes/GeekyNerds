package com.demo.service;

import java.util.List;

import com.demo.bean.User;

//interface to implement all functions of business logic layer
public interface UserService {

	List<User> searchByName(String name, String useremail);

	List<User> searchByCity(String city, String useremail);

	List<User> searchByCompany(String company, String useremail);

	int addToFriend(String uemail, String useremail);

	int addToBlockList(String uemail,String useremail);

	List<String> viewBlockedUserList(String useremail);

	int removeFromBlockList(String uemail);
	
	List<User> getAllUsers();

	User searchUserByEmail(String cemail);

}
