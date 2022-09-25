package com.demo.service;

import com.demo.bean.User;
import com.demo.dao.LoginDao;
import com.demo.dao.LoginDaoImpl;
import com.demo.exception.UserNotFoundException;

public class LoginServiceImpl implements LoginService{
	private 
      LoginDao loginDao;
	
	public LoginServiceImpl() {
		super();
		loginDao=new LoginDaoImpl();
	}

	@Override
	public User validateUser(String uname, String pass) throws UserNotFoundException {
		User user=loginDao.authenticateUser(uname,pass);
		if(user!=null) {
	
	    if(user.getUname().equals(uname) && user.getUpass().equals(pass)) {
	    	return user;
	    }
		}
		return null;
	}
	
	//yukti
	@Override
	public User uemailVerify(String uemail, String uques, String uans) {
		return loginDao.uemailVerify(uemail,uques,uans);
		
	}

	@Override
	public int updatePass(String uemail, String pass, String cpass) {
		return loginDao.updatePass(uemail,pass,cpass);
	}

}
