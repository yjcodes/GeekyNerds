package com.demo.service;

import java.util.List;
import com.demo.bean.User;
import com.demo.dao.FriendDao;
import com.demo.dao.FriendDaoImpl;

public class FriendServiceImpl implements FriendService {
	public FriendDao friendDao;
	

	public FriendServiceImpl() {
		super();
		friendDao=new FriendDaoImpl();
	}

	@Override
	public List<User> SearchByName(String uemail,String fname) {
		
		return friendDao.searchbyname(uemail,fname);
	}

	@Override
	public List<User> SearchByCity(String uemail,String city ) {
		
		return friendDao.searchbycity(uemail,city);
	}

	@Override
	public void blockFriendByEmail(String uEmail, String fEmail) {
		friendDao.blockFriendByEmail(uEmail, fEmail);
		
	}

	@Override
	public void removeFriend(String uEmail, String fEmail) {
		friendDao.removeFriend(uEmail,fEmail);
		
	}
	
	//Krishna
	@Override
	public List<User> getFriendRequests(String uemail) {
		return friendDao.getRequests(uemail);	
	}

	@Override
	public String requestAction(String receiverEmail, String senderEmail, String buttonValue) {
		return friendDao.takeRequestAction(receiverEmail, senderEmail, buttonValue);
	}

}
