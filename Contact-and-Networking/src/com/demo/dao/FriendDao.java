package com.demo.dao;

import java.util.List;

import com.demo.bean.User;

public interface FriendDao {

	List<User> searchbyname(String name,String user);

	List<User> searchbycity(String uemail,String city );

	void blockFriendByEmail(String uEmail, String fEmail);

	void removeFriend(String uEmail, String fEmail);
	
	//Krishna
	List<User> getRequests(String uemail);

	String takeRequestAction(String receiverEmail, String senderEmail, String buttonValue);

}
