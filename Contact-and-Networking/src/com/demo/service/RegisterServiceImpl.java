package com.demo.service;

import com.demo.bean.User;
import com.demo.dao.RegisterDao;
import com.demo.dao.RegisterDaoImpl;

public class RegisterServiceImpl implements RegisterService {
	private RegisterDao registerDao;
	
	public  RegisterServiceImpl() {
		super();
		registerDao=new RegisterDaoImpl();
	}
	 public boolean saveDetails(User userDetails) {
		return registerDao.saveDetails(userDetails);
	}

}
