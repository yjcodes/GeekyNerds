package com.demo.dao;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import com.demo.bean.User;

//data-access layer functions
public class UserDaoImpl implements UserDao {
	static Connection conn;
	static PreparedStatement ugetNameList, ugetCityList, ugetCompanyList, ugetAll, uaddFriendList, uaddToBlockList, ugetBlockList, utoUnblock,pgetuser;
	static {
		// database connection
		conn = DBUtil.getMyConnection();
		try {
			// queries for various functions
			ugetNameList = conn.prepareStatement(
					"select * from userdetails where ufullname=? and uemail not in(select useremail from blockedfriendlist where blockeduseremail=?)");
			ugetCityList = conn.prepareStatement(
					"select * from userdetails where ucity=? and uemail not in(select useremail from blockedfriendlist where blockeduseremail=?)");
			ugetCompanyList = conn.prepareStatement(
					"select * from userdetails where ucompany=? and uemail not in(select useremail from blockedfriendlist where blockeduseremail=?)");
			ugetAll = conn.prepareStatement(
					"select * from userdetails where uemail not in(select useremail from blockedfriendlist where blockeduseremail=?)");
			//ugetById = conn.prepareStatement("select * from userdetails where uid=?");
			uaddFriendList = conn.prepareStatement("insert into friendrequest values(?,?)");
			uaddToBlockList = conn.prepareStatement("insert into blockedfriendlist values(?,?)");
			ugetBlockList = conn.prepareStatement("select * from blockedfriendlist where useremail=?");
			utoUnblock = conn.prepareStatement("delete from blockedfriendlist where blockeduseremail=?");
			pgetuser=conn.prepareStatement("select * from userdetails");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// method to return a list containing user details of those users whose name is
	// same as that passed by the service layer
	@Override
	public List<User> getListByName(String name, String useremail) {
		try {
			ResultSet rs;
			if (name.equals("")) {
				ugetAll.setString(1, useremail);
				rs = ugetAll.executeQuery();
			} else {
				ugetNameList.setString(1, name);
				 ugetNameList.setString(2, useremail); //uemail from session
				rs = ugetNameList.executeQuery();
			}
			List<User> ulist = new ArrayList<>();
			while (rs.next()) {

				 /*Blob blob = rs.getBlob(13); 
				 InputStream in = blob.getBinaryStream();
				 BufferedImage uimage = null; uimage = ImageIO.read(in);*/
				User uObj = new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getDate(7), rs.getString(8), rs.getString(9),
						rs.getString(10), rs.getString(11), rs.getString(12),null,rs.getString(14), rs.getString(15),
						rs.getInt(16),rs.getBoolean(17));
				System.out.println(uObj);
				ulist.add(uObj);
			}
			return ulist;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return null;
	}

	// method to return a list containing user details of those users whose city is
	// same as that passed by the service layer
	@Override
	public List<User> getListByCity(String city, String useremail) {
		try {
			ResultSet rs;
			if (city.equals("")) {
				ugetAll.setString(1, useremail);
				rs = ugetAll.executeQuery();
			} else {
				ugetCityList.setString(1, city);
				ugetCityList.setString(2, useremail); //uemail from session
				rs = ugetCityList.executeQuery();
			}
			List<User> ulist = new ArrayList<>();
			while (rs.next()) {
				
				 Blob blob = rs.getBlob(13); InputStream in = blob.getBinaryStream();
				 BufferedImage uimage = null; try {
					uimage = ImageIO.read(in);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				User uObj = new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getDate(7), rs.getString(8), rs.getString(9),
						rs.getString(10), rs.getString(11), rs.getString(12), uimage,rs.getString(14), rs.getString(15),
						rs.getInt(16),rs.getBoolean(17));
				System.out.println(uObj);
				ulist.add(uObj);
			}
			return ulist;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// method to return a list containing user details of those users whose company
	// is same as that passed by the service layer
	@Override
	public List<User> getListByCompany(String company, String useremail) {
		try {
			ResultSet rs;
			if (company.equals("")) {
				ugetAll.setString(1, useremail);
				rs = ugetAll.executeQuery();
			} else {
				ugetCompanyList.setString(1, company);
			    ugetCompanyList.setString(2, useremail); //uemail from session
				rs = ugetCompanyList.executeQuery();
			}
			List<User> ulist = new ArrayList<>();
			while (rs.next()) {
				
				  Blob blob = rs.getBlob(13); InputStream in = blob.getBinaryStream();
				  BufferedImage uimage = null; uimage = ImageIO.read(in);
				 
				User uObj = new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getDate(7), rs.getString(8), rs.getString(9),
						rs.getString(10), rs.getString(11), rs.getString(12), uimage,rs.getString(14), rs.getString(15),
						rs.getInt(16),rs.getBoolean(17));
				System.out.println(uObj);
				ulist.add(uObj);
			}
			return ulist;
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return null;
	}

	 

	// method to send a friend request to user whose id is passed
	public int addFriendToList(String uemail,String useremail) {
		
		try {
			uaddFriendList.setString(1, useremail);	//from session
			uaddFriendList.setString(2, uemail);
			int n = uaddFriendList.executeUpdate();
			 // instead of udd.getUemail() we will take email from
																// session object
			System.out.println("RETURN Status : " + n);
			return n;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 0;
	}

	// method to block a user whose id is passed
	@Override
	public int addUserToBlockList(String uemail,String useremail) {
		
		try {
			uaddToBlockList.setString(1, useremail);	//from session
			uaddToBlockList.setString(1, uemail);
			int n = uaddToBlockList.executeUpdate();
			System.out.println("RETURN Status : " + n);
			return n;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 0;
	}

	// method that returns a list of users that have been blocked
	@Override
	public List<String> getBlockByUser(String useremail) {
		try {
			ugetBlockList.setString(1, useremail);	// from session
			ResultSet rs = ugetBlockList.executeQuery();
			List<String> ulist = new ArrayList<>();
			while (rs.next()) {
				
				ulist.add(rs.getString(2));
			}
			return ulist;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// method to unblock a user
	@Override
	public int unBlockUser(String uemail) {
		try {
			utoUnblock.setString(1, uemail);
			int n = utoUnblock.executeUpdate();
			System.out.println("RETURN Status : " + n);
			return n;
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return 0;
	}
	
	
	//method to return all the users from userdetails database table
	@Override
	public List<User> getAllUsers() {
		ContactDao contactDao=new ContactDaoImpl();
		try {
			ResultSet rs=pgetuser.executeQuery();
			List<User> ulist=new ArrayList<>();
			User user=null;
			while(rs.next()) {
				Blob aBlob = rs.getBlob(13);
				if(aBlob==null) {
					user=new User(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getDate(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12),null,rs.getString(14),rs.getString(15),rs.getInt(16),rs.getBoolean(17));
				}else {
	            byte[] allBytesInBlob = aBlob.getBytes(1, (int) aBlob.length());
	            ByteArrayInputStream bais = new ByteArrayInputStream(allBytesInBlob);
	            BufferedImage bImage = ImageIO.read(bais);
				user=new User(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getDate(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12),bImage,rs.getString(14),rs.getString(15),rs.getInt(16),rs.getBoolean(17));
				ulist.add(user);
				}
			}
			return ulist;
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	//returns the object of user using email matches with the contact email passed as an argument
	@Override
	public User searchUserByEmail(String cemail) {
		
		List<User> ulist=getAllUsers();
		for(User u:ulist) {
			if(cemail.equals(u.getUemail())) {
				return u;
			}
		}
		return null;
	}
}
