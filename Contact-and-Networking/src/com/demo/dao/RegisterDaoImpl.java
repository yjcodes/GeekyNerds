package com.demo.dao;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import javax.sql.rowset.serial.SerialBlob;

import com.demo.bean.User;

public class RegisterDaoImpl implements RegisterDao {

	static Connection conn;
	static PreparedStatement uregister;
	static {
		conn=DBUtil.getMyConnection();
		try {
			uregister=conn.prepareStatement("insert into userdetails values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		}catch(SQLException e) {
			
		}
		
}
	//Convert the image to suitable format
	public Blob convertToBlob(BufferedImage bImage) {
		
		 ByteArrayOutputStream bos = new ByteArrayOutputStream();
		 Blob blob = null;
	      try {
			ImageIO.write(bImage, "jpg", bos );
			byte[] data = bos.toByteArray();
			blob = new SerialBlob(data);
			return blob;
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
	      
		return null;
	}
		
	//To add new user to the database(Userdetails database table)
	public boolean saveDetails(User userDetails) {
		
		Blob blob=convertToBlob(userDetails.getUimage());
		try {
			uregister.setString(1, userDetails.getUemail());
			uregister.setString(2, userDetails.getUname());
			uregister.setString(3, userDetails.getUfullName());
			uregister.setString(4, userDetails.getUpass());
			uregister.setString(5, userDetails.getUmob());
			uregister.setString(6, userDetails.getUgender());
			uregister.setDate(7,(java.sql.Date) userDetails.getUbdate());
			uregister.setString(8, userDetails.getUaddress());
			uregister.setString(9, userDetails.getUcity());
			uregister.setString(10, userDetails.getUstate());
			uregister.setString(11, userDetails.getUcountry());
			uregister.setString(12, userDetails.getUcompany());
			uregister.setBlob(13, blob);
			uregister.setString(14, userDetails.getSupportQn());
			uregister.setString(15, userDetails.getSupportAns());
			uregister.setInt(16, userDetails.getBlockCount());
			uregister.setBoolean(17, userDetails.isDisableflag());
			
			int rs=uregister.executeUpdate();
			if(rs!=0) {
				return true;
			}else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
		
	}
}