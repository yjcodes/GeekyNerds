package com.demo.dao;

import com.demo.bean.User;
import com.demo.exception.UserNotFoundException;

public interface LoginDao {

	User authenticateUser(String uname, String pass) throws UserNotFoundException;

	User uemailVerify(String uemail, String uques, String uans);

	int updatePass(String uemail, String pass, String cpass);

}
