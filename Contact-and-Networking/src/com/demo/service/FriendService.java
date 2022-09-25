package com.demo.service;

import java.util.List;

import com.demo.bean.User;

public interface FriendService {

	

	List<User> SearchByName(String name,String user);

	List<User> SearchByCity(String uemail,String city );

	void blockFriendByEmail(String uEmail, String fEmail);

	void removeFriend(String uemail, String fEmail);
	
	//Krishna
	List<User> getFriendRequests(String uemail);

	String requestAction(String receiverEmail, String senderEmail, String buttonValue);


}
