package com.demo.service;

import java.util.List;


import com.demo.bean.User;
import com.demo.dao.UserDao;
import com.demo.dao.UserDaoImpl;

//business layer functions
public class UserServiceImpl implements UserService {
	private UserDao userDao;
	
	//constructor to initialize the value of data-access layer object
	public UserServiceImpl() {
		super();
		userDao = new UserDaoImpl();
	}
	
	//method to access data-access layer to return list of user according to name passed
	@Override
	public List<User> searchByName(String name,String useremail) {
		return userDao.getListByName(name,useremail);
	}
	
	//method to access data-access layer to return list of user according to city passed
	@Override
	public List<User> searchByCity(String city, String useremail) {
		return userDao.getListByCity(city,useremail);
	}
	
	//method to access data-access layer to return list of user according to company passed
	@Override
	public List<User> searchByCompany(String company, String useremail) {
		return userDao.getListByCompany(company,useremail);
	}
	
	//method to access data-access layer to send friend request according to uid passed
	@Override
	public int addToFriend(String uemail,String useremail) {
		return userDao.addFriendToList(uemail,useremail);
	}
	
	//method to access data-access layer to block user according to uid passed
	@Override
	public int addToBlockList(String uemail,String useremail) {
		return userDao.addUserToBlockList(uemail,useremail);
	}
	
	//method to access data-access layer to view entire list of blocked user
	@Override
	public List<String> viewBlockedUserList(String useremail) {
		return userDao.getBlockByUser(useremail);
	}
	
	//method to access data-access layer to unblock a user acccording to email sent
	@Override
	public int removeFromBlockList(String uemail) {
		return userDao.unBlockUser(uemail);
	}
	
	//method to access data-access layer to get a list of all the users from database
	@Override
	public List<User> getAllUsers() {

		return userDao.getAllUsers();
	}

	//method to access data-access layer to search user by contact email
	@Override
	public User searchUserByEmail(String cemail) {
		
		return userDao.searchUserByEmail(cemail);
	}
}
