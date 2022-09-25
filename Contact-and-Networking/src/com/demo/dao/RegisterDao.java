package com.demo.dao;

import java.awt.image.BufferedImage;

import java.sql.Blob;

import com.demo.bean.User;
//Register dao interface to implement Data-access layer functions
public interface RegisterDao {

	boolean saveDetails(User userDetails) ;
	
	public Blob convertToBlob(BufferedImage bImage);
	

}
