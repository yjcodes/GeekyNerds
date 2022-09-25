package com.demo.service;

import com.demo.bean.User;
import com.demo.exception.UserNotFoundException;

public interface LoginService {

	User validateUser(String uname, String pass) throws UserNotFoundException;

	int updatePass(String uemail, String pass, String cpass);

	User uemailVerify(String uemail, String uques, String uans);

}
