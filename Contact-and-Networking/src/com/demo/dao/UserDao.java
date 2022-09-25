package com.demo.dao;

import java.util.List;
import com.demo.bean.User;

//interface to implement Data-access layer functions
public interface UserDao {

	List<User> getListByName(String name, String useremail);

	List<User> getListByCity(String city, String useremail);

	List<User> getListByCompany(String company, String useremail);

	int addFriendToList(String uemail, String useremail);

	List<String> getBlockByUser(String useremail);

	int unBlockUser(String uemail);

	List<User> getAllUsers();

	User searchUserByEmail(String cemail);

	int addUserToBlockList(String uemail, String useremail);
}
