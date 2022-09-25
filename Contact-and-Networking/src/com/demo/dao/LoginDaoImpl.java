package com.demo.dao;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.imageio.ImageIO;

import com.demo.bean.User;
import com.demo.exception.UserNotFoundException;

public class LoginDaoImpl implements LoginDao{
	static Connection conn;
	static PreparedStatement ugetbyname,pemailverify,pupdatepass;
	static {
		conn=DBUtil.getMyConnection();
		try {
			ugetbyname=conn.prepareStatement("select * from userdetails where uname=? and upass=? and disableflag=false");
			pemailverify=conn.prepareStatement("select * from userdetails where uemail=? AND squestion=? AND sanswer=?");
			pupdatepass=conn.prepareStatement("update userdetails set upass=? where uemail=?");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public User authenticateUser(String uname, String pass) throws UserNotFoundException  {
		try {
			ugetbyname.setString(1, uname);
			ugetbyname.setString(2, pass);
			ResultSet rs=ugetbyname.executeQuery();
			if(rs.next()) {
				Blob aBlob = rs.getBlob(13);
	            byte[] allBytesInBlob = aBlob.getBytes(1, (int) aBlob.length());
	            ByteArrayInputStream bais = new ByteArrayInputStream(allBytesInBlob);
	            BufferedImage bImage = ImageIO.read(bais);
				User u=new User(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getDate(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12),bImage,rs.getString(14),rs.getString(15),rs.getInt(16),rs.getBoolean(17));
				//System.out.println(u.getUname());
				if(u.getUname().equals(uname) && u.getUpass().equals(pass)) {
					return u;
			    }
				
			}
			else {
				throw new UserNotFoundException("Please authorize first");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public User uemailVerify(String uemail, String uques, String uans) {
		
		try {
			pemailverify.setString(1, uemail);
			pemailverify.setString(2, uques);
			pemailverify.setString(3, uans);
			
			ResultSet rs=pemailverify.executeQuery();
			
			if(rs.next()) {
				User u=new User(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(8),rs.getString(9));
				//System.out.println("IN DAOOOO"+u);
				return u;
				
			}
		
		} catch (SQLException | NullPointerException e) {
			// TODO Auto-generated catch block
			System.out.println("Not a valid user");
		}
		
		return null;
	}
	@Override
	public int updatePass(String uemail, String pass, String cpass) {
		try {
			pupdatepass.setString(1, cpass);
			pupdatepass.setString(2, uemail);
			
			int n=pupdatepass.executeUpdate();
			if(n>0)
				return 1;
			else
				return 0;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return 0;
		}
	}
}