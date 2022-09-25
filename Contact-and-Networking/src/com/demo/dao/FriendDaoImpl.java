package com.demo.dao;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import com.demo.bean.User;

public class FriendDaoImpl implements FriendDao{
	static Connection conn;
	static PreparedStatement pgetbyname,pgetbycity,premovefromfriendlist,pinsertinblockedfriendlist,pinsertinblockedbyfriendlist,pgetrequest, paccept, premoverequest, pblockfriend, pgetfullnamebyemail;
	static {
		conn=DBUtil.getMyConnection();
		try {
			
			pgetbyname=conn.prepareStatement("select * from userdetails where uemail in (select friend2email from friendlist where friend1email=? and friend2email=(select uemail from userdetails where uname=?) union select friend1email from friendlist where friend2email=? and friend1email=(select uemail from userdetails where uname=?))");
			pgetbycity=conn.prepareStatement("select * from userdetails where uemail in (select friend2email from friendlist where friend1email=? union select friend1email from friendlist where friend2email=?) and ucity=?");
			
			premovefromfriendlist=conn.prepareStatement("delete from friendlist where friend1email=? and friend2email=? or friend2email=? and friend1email=?");
			pinsertinblockedfriendlist=conn.prepareStatement("insert into blockedfriendlist values (?,?)");
			
			pgetrequest = conn.prepareStatement("select * from userdetails where uemail IN (select senderemail from friendrequest where receiveremail=?)");
			paccept = conn.prepareStatement("insert into friendlist values(?,?)");
			premoverequest = conn.prepareStatement("delete from friendrequest where receiveremail=? and senderemail=?");
			pblockfriend = conn.prepareStatement("insert into blockedfriendlist values(?,?)");	
			pgetfullnamebyemail = conn.prepareStatement("select ufullname from userdetails where uemail=?");
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	static List<User> ulist;
	static {
		ulist=new ArrayList<>();
		
	}
	
	//method to extract the friend list by name
	public List<User> searchbyname(String uemail,String fname) {
		
		try {
			pgetbyname.setString(1, uemail);
			pgetbyname.setString(2, fname);
			pgetbyname.setString(3, uemail);
			pgetbyname.setString(4, fname);
			ResultSet rs=pgetbyname.executeQuery();
			List<User> ulist=new ArrayList<>();
			while(rs.next()) {
				Blob aBlob = rs.getBlob(13);
	            byte[] allBytesInBlob = aBlob.getBytes(1, (int) aBlob.length());
	            ByteArrayInputStream bais = new ByteArrayInputStream(allBytesInBlob);
	            BufferedImage bImage = ImageIO.read(bais);
				User u=new User(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getDate(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12),bImage,rs.getString(14),rs.getString(15),rs.getInt(16),rs.getBoolean(17));
				ulist.add(u);
				System.out.println(u);
			}
			return ulist;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	//Method to search list of friend by city as filter
	@Override
	public List<User> searchbycity(String uemail,String city ) {
		try {
		pgetbycity.setString(1, uemail);
		pgetbycity.setString(2, uemail);
		pgetbycity.setString(3, city);
		ResultSet rs=pgetbycity.executeQuery();
		List<User> ulist=new ArrayList<>();
		while(rs.next()) {
			Blob aBlob = rs.getBlob(13);
            byte[] allBytesInBlob = aBlob.getBytes(1, (int) aBlob.length());
            ByteArrayInputStream bais = new ByteArrayInputStream(allBytesInBlob);
            BufferedImage bImage = ImageIO.read(bais);
			User u=new User(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getDate(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12),bImage,rs.getString(14),rs.getString(15),rs.getInt(16),rs.getBoolean(17));
			ulist.add(u);
			System.out.println(u);
		}
		return ulist;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return null;
	}
	
	//Method to block friends, removing entry from friendlist and entering the data in blockedlist
	@Override
	public void blockFriendByEmail(String uEmail, String fEmail) {
		System.out.println(uEmail);
		System.out.println(fEmail);
		try {
		 pinsertinblockedfriendlist.setString(1, uEmail);
		 pinsertinblockedfriendlist.setString(2, fEmail);
		 pinsertinblockedfriendlist.executeUpdate();
		 removeFriend(uEmail, fEmail);
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//Method to remove friend from friendlist
	@Override
	public void removeFriend(String uEmail, String fEmail) {
		try {
			 premovefromfriendlist.setString(1, uEmail);
			 premovefromfriendlist.setString(2, fEmail);
			 premovefromfriendlist.setString(3, uEmail);
			 premovefromfriendlist.setString(4, fEmail);
			 
			 premovefromfriendlist.executeUpdate();
			
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	@Override
	public List<User> getRequests(String uemail) {
		try {
				pgetrequest.setString(1, uemail);
				ResultSet rs = pgetrequest.executeQuery();
				List<User> ulist1 = new ArrayList<>();
				System.out.println("In dao receiveremail:"+uemail);
				while(rs.next()) {
					/*Date bdate = rs.getDate(7);
					User u = new User(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6), bdate,rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13),rs.getString(14),rs.getInt(15),rs.getBoolean(16));
					User u = new User(rs.getString(1), rs.getString(3));
					System.out.println(u);
					ulist1.add(u);*/
						Blob aBlob = rs.getBlob(13);
			            byte[] allBytesInBlob = aBlob.getBytes(1, (int) aBlob.length());
			            ByteArrayInputStream bais = new ByteArrayInputStream(allBytesInBlob);
			            BufferedImage bImage = ImageIO.read(bais);
						User u=new User(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getDate(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12),bImage,rs.getString(14),rs.getString(15),rs.getInt(16),rs.getBoolean(17));
						ulist1.add(u);
				}
				return ulist1;
			} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
				e.printStackTrace();
			}
		return null;
	}
	
	public void removeFromFriendRequest(String receiverEmail, String senderEmail) {
		try {
			premoverequest.setString(1, receiverEmail);
			premoverequest.setString(2, senderEmail);
			premoverequest.executeUpdate();	
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String takeRequestAction(String receiverEmail, String senderEmail, String buttonValue) {
		if(buttonValue.equals("accept")) {
			try {
				paccept.setString(1, receiverEmail);			
				paccept.setString(2, senderEmail);
				paccept.executeUpdate();											//Add entry in the friend list table
				
				removeFromFriendRequest(receiverEmail, senderEmail);				//Remove entry from friend request table
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else if(buttonValue.equals("ignore")) {
			removeFromFriendRequest(receiverEmail, senderEmail);					//Remove entry from friend request table
		}
		else {			//If receiver blocks sender
			try{
				
				removeFromFriendRequest(receiverEmail, senderEmail);				//Remove entry from friend request table
	
				pblockfriend.setString(1, receiverEmail);
				pblockfriend.setString(2, senderEmail);
				pblockfriend.executeUpdate();										//Add entry in block friend table
				
				
			}catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		String senderFullName = "";
		try {
			pgetfullnamebyemail.setString(1, senderEmail);
			ResultSet rs = pgetfullnamebyemail.executeQuery();
			rs.next();
			senderFullName = rs.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return senderFullName;
		
	}
	
}

