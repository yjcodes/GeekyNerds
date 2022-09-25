package com.demo.dao;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.text.ParseException;
import javax.imageio.ImageIO;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import com.demo.bean.ContactDetails;


import com.demo.bean.User;
//ContactDao class which implements int ContactDao interface
public class ContactDaoImpl implements ContactDao{

	static Connection conn;
	static SimpleDateFormat sdf;
	static PreparedStatement pinsnewcontact,pinsintocontactlist,pgetcontactdetails,cgetuemail,cgetcemail,cgetbyuseremail,cgetbycontactemail,cdeleteuser,cdeletecontact,cupdateuser,cupdatecontact,cbycity,cgetallcity,cdeletecontactdetails,cgetusercity,cgetcontactcity,cgetcontactcitybymail;
	static {
		
		conn=DBUtil.getMyConnection();
		sdf=new SimpleDateFormat("yyyy-MM-dd");
		try {
			//Database queries to perform DML operations as per the requirement
			pinsnewcontact=conn.prepareStatement("insert into contactdetails values(?,?,?,?,?,?,?,?,?,?,?)");
			pinsintocontactlist=conn.prepareStatement("insert into contactlist values(?,?,?)");
			pgetcontactdetails=conn.prepareStatement("select * from contactdetails");
			cgetuemail=conn.prepareStatement("select uemail from contactlist where owneremail=?");
			cgetcemail=conn.prepareStatement("select cemail from contactlist where owneremail=?");
			cgetbyuseremail=conn.prepareStatement("select * from userdetails where uemail=? ");
			cgetbycontactemail=conn.prepareStatement("select * from contactdetails where cemail=? ");
			cdeleteuser=conn.prepareStatement("delete from contactlist where uemail=? ");
			cdeletecontact=conn.prepareStatement("delete from contactlist where cemail=? ");
			cdeletecontactdetails=conn.prepareStatement("delete from contactdetails where cemail=? ");
			
			cupdatecontact=conn.prepareStatement("update contactdetails set cfullname=?,cmob=?,ccity=? where cemail=?");
			cbycity=conn.prepareStatement("select * from contactdetails where ccity=?");
			cgetallcity=conn.prepareStatement("select * from contactdetails where ccity=? ");
			cgetusercity=conn.prepareStatement("select * from userdetails where ucity=? and uemail=?");
			cgetcontactcity=conn.prepareStatement("select * from contactdetails where ccity=? ");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	
	//this funtion is called to check if the Contact exist in the user details
	@Override
	public boolean checkIfExists(String cemail) {
		
		UserDao userDao=new UserDaoImpl();
		User user=userDao.searchUserByEmail(cemail);
		if(user!=null) {
			return true;
		}
		
		return false;
	}
	//This function is used to get all the contacts for view purpose
	@Override
	public List<ContactDetails> getAllContacts() {
		
		try {
			ResultSet rs=pgetcontactdetails.executeQuery();
			List<ContactDetails> clist=new ArrayList<>();
			ContactDetails contact=null;
			while(rs.next()) {
				String email=rs.getString(1);
				String name=rs.getString(2);
				String mobile=rs.getString(3);
				String gender= rs.getString(4);
				String bdate=rs.getString(5);
				String address=rs.getString(6);
				String city=rs.getString(7);
				String state=rs.getString(8);
				String country=rs.getString(9);
				String company=rs.getString(10);
				Blob image=rs.getBlob(11);
				BufferedImage bimage=convertToBufferedImage(image);
				java.util.Date dt;
				try {
					if(bdate!=null) {
						dt = sdf.parse(bdate);
					}
					else {
						dt=null;
					}
					contact=new ContactDetails(email,name, mobile,gender , dt, address, city, state, country, company, bimage);
					clist.add(contact);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return clist;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	//This function is called to add new contact to the contactdetails database
	@Override
	public int addNewContact(ContactDetails contact) {
		Blob blob=convertToBlob(contact.getCimage());
		try {
			pinsnewcontact.setString(1, contact.getCemail());
			pinsnewcontact.setString(2, contact.getCfullName());
			pinsnewcontact.setString(3, contact.getCmob());
			pinsnewcontact.setString(4, contact.getCgender());
			java.util.Date date=contact.getCbdate();
			java.sql.Date dt=convertUtilToSql(date);
			pinsnewcontact.setDate(5,dt);
			pinsnewcontact.setString(6, contact.getCaddress());
			pinsnewcontact.setString(7, contact.getCcity());
			pinsnewcontact.setString(8, contact.getCstate());
			pinsnewcontact.setString(9, contact.getCcountry());
			pinsnewcontact.setString(10, contact.getCcompany());
			pinsnewcontact.setBlob(11, blob);
			return pinsnewcontact.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}

	//method update ContactList table with existing user
	@Override
	public void updateContactListForUser(String uemail, String email) {
		
		try {
			System.out.println("In contact user");
			pinsintocontactlist.setString(1, uemail);
			pinsintocontactlist.setString(2, email);
			pinsintocontactlist.setString(3, null);
			pinsintocontactlist.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	//method update ContactList table with new contact not an user
	@Override
	public void updateContactListForNewContact(String uemail, String email) {
		
		try {
			pinsintocontactlist.setString(1, uemail);
			pinsintocontactlist.setString(2, null);
			pinsintocontactlist.setString(3, email);
			pinsintocontactlist.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//This method is used to check if the contact exists associated with the particular email id from the user
	@Override
	public boolean checkIfContactExists(String email) {
		
		List<ContactDetails> clist=getAllContacts();
		for(ContactDetails contact:clist) {
			if(email.equals(contact.getCemail())) {
				return true;
			}
		}
		return false;
	}
	
	//Image format conversion
	@Override
	public Blob convertToBlob(BufferedImage bImage) {
		
		 ByteArrayOutputStream bos = new ByteArrayOutputStream();
		 Blob blob = null;
	     
			try {
				ImageIO.write(bImage, "jpg", bos );
				byte[] data = bos.toByteArray();
				blob = new SerialBlob(data);
				return blob;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			} catch (SerialException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
	      
		return null;
	}
	
	public BufferedImage convertToBufferedImage(Blob image) {
		InputStream in;
		if(image!=null) {
			try {
				in = image.getBinaryStream();
				BufferedImage bimage = ImageIO.read(in);
				return bimage;
			} catch(SQLException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
		}
		
		return null;
	}
	private static java.sql.Date convertUtilToSql(java.util.Date uDate) {
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        return sDate;
    }
	
	
	
	
	// to get the email of the users in the contactlist associated with the Owner(the person who's account is logged in) 
	@Override
	public List<String> getUserEmail(String owneremail) {
		// TODO Auto-generated method stub
		
		try {
			cgetuemail.setString(1, owneremail);
			ResultSet rs=cgetuemail.executeQuery();
			List<String> cList=new ArrayList<>();
			while(rs.next())
			{
				
				cList.add(rs.getString(1));
			}
			
			return cList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	    return null;
	}
	// Method to get the list of all the contacts associated with the owner(The person who's account is logged in)
	@Override
	public List<String> getContactEmail(String owneremail) {
		// TODO Auto-generated method stub
		try {
			
			cgetcemail.setString(1, owneremail);
			ResultSet rs=cgetcemail.executeQuery();
			List<String> cList=new ArrayList<>();
			while(rs.next())
			{
				
				cList.add(rs.getString(1));
			}
			
			return cList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	    return null;
	}
	//To get the user object using user email
	@Override
	public User getByUserEmail(String s) {
		// TODO Auto-generated method stub
		try { 
			cgetbyuseremail.setString(1, s);
			ResultSet rs=cgetbyuseremail.executeQuery();
			
			User p=null;
			while(rs.next())
			{
				Blob aBlob = rs.getBlob(13);
	            byte[] allBytesInBlob = aBlob.getBytes(1, (int) aBlob.length());
	            ByteArrayInputStream bais = new ByteArrayInputStream(allBytesInBlob);
	            BufferedImage bImage = ImageIO.read(bais);
			      p=new User(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getDate(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12),bImage,rs.getString(14),rs.getString(15),rs.getInt(16),rs.getBoolean(17));
				
			}
			return p;
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	    return null;
	}
	// To return the contact object using contact email
	@Override
	public ContactDetails getByContactEmail(String p) {
		// TODO Auto-generated method stub
		try { 
			
			cgetbycontactemail.setString(1, p);
			ResultSet rs=cgetbycontactemail.executeQuery();
			
			
			ContactDetails c=null;
			
			
			while(rs.next())
			{     				 
				Blob aBlob = rs.getBlob(11);
	            byte[] allBytesInBlob = aBlob.getBytes(1, (int) aBlob.length());
	            ByteArrayInputStream bais = new ByteArrayInputStream(allBytesInBlob);
	            BufferedImage bImage = ImageIO.read(bais);		
	            System.out.println(bImage);
				c=new ContactDetails(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getDate(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),bImage);
				 
			}
			//System.out.println(c);
			return c;
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	    return null;
	}
	//To delete the existing user from the contact list
	@Override
	public void deleteUserList(User u) {
		// TODO Auto-generated method stub
		try {
			//System.out.println(p.getpId());   
			cdeleteuser.setString(1, u.getUemail());
			cdeleteuser.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	//delete the contact details using contact email
	@Override
	public void deleteContact(ContactDetails c) {
		// TODO Auto-generated method stub
		try {
			//System.out.println(p.getpId());   
			cdeletecontact.setString(1, c.getCemail());
			
			cdeletecontact.executeUpdate();
			cdeletecontactdetails.setString(1, c.getCemail());
			
			cdeletecontactdetails.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//Updating the contact details using contact emailid
	@Override
	public void updatecontact(ContactDetails c) {
		// TODO Auto-generated method stub
		try {
			
			cupdatecontact.setString(1, c.getCfullName());
			cupdatecontact.setString(2, c.getCmob());
			cupdatecontact.setString(3, c.getCcity());
			cupdatecontact.setString(4, c.getCemail());
			cupdatecontact.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	//retrieve the contact data using city filter and the people with same city are stored in the filter servlet and not here
	@Override
	public ContactDetails getByCity(String city) {
		// TODO Auto-generated method stub
		try {
			cbycity.setString(1, city);
             ResultSet rs=cbycity.executeQuery();
			
			ContactDetails c=null;
			while(rs.next())
			{
				Blob aBlob = rs.getBlob(11);
	            byte[] allBytesInBlob = aBlob.getBytes(1, (int) aBlob.length());
	            ByteArrayInputStream bais = new ByteArrayInputStream(allBytesInBlob);
	            BufferedImage bImage = ImageIO.read(bais);		 
				c=new ContactDetails(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getDate(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),bImage);
				
			}
			return c;
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	//Return the list of all the contacts with same city( here the contacts are filtered on the basis of city)
	@Override
	public List<ContactDetails> getAllByCity(String city,List<String> celist) {
		// TODO Auto-generated method stub
         try { 
			
			cgetallcity.setString(1, city);
			ResultSet rs=cgetallcity.executeQuery();
			
			
			ContactDetails c=null;
			List<ContactDetails> cList=new ArrayList<>();
			
			while(rs.next())
			{     				 
				Blob aBlob = rs.getBlob(11);
	            byte[] allBytesInBlob = aBlob.getBytes(1, (int) aBlob.length());
	            ByteArrayInputStream bais = new ByteArrayInputStream(allBytesInBlob);
	            BufferedImage bImage = ImageIO.read(bais);		 
				c=new ContactDetails(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getDate(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),bImage);
				cList.add(c);
				 
			}
			//System.out.println(c);
			return cList;
			
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	//This method is used to get the user with city
	@Override
	public List<User> getUserCity(String city,String owneremail) {
		// TODO Auto-generated method stub
       try { 
			
			cgetusercity.setString(1, city);
			cgetusercity.setString(2, owneremail);
			ResultSet rs=cgetusercity.executeQuery();
			User c=null;
			List<User> cList=new ArrayList<>();
			while(rs.next())
			{     
				Blob aBlob = rs.getBlob(13);
	            byte[] allBytesInBlob = aBlob.getBytes(1, (int) aBlob.length());
	            ByteArrayInputStream bais = new ByteArrayInputStream(allBytesInBlob);
	            BufferedImage bImage = ImageIO.read(bais);
				c=new User(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getDate(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12),bImage,rs.getString(14),rs.getString(15),rs.getInt(16),rs.getBoolean(17));
				cList.add(c);
				System.out.println(c);
				 
			}
			System.out.println(cList);
			return cList;
			
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	//This method is used to filter the contact with city and the list is made in filter servlet(contacts with same city)
	@Override
	public List<ContactDetails> getContactrCity(String city) {
		// TODO Auto-generated method stub
       try { 
			
			cgetcontactcity.setString(1, city);
			
			ResultSet rs=cgetcontactcity.executeQuery();
			
			
			ContactDetails c=null;
			List<ContactDetails> cList=new ArrayList<>();
			
			while(rs.next())
			{     		
				Blob aBlob = rs.getBlob(11);
	            byte[] allBytesInBlob = aBlob.getBytes(1, (int) aBlob.length());
	            ByteArrayInputStream bais = new ByteArrayInputStream(allBytesInBlob);
	            BufferedImage bImage = ImageIO.read(bais);		 
				c=new ContactDetails(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getDate(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),bImage);
				cList.add(c);
				 
			}
			//System.out.println(c);
			return cList;
			
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	
	@Override
	public ContactDetails getContactCityByMail(String p) {
		// TODO Auto-generated method stub
      try { 
			
    	  cgetcontactcitybymail.setString(1, p);
			ResultSet rs=cgetcontactcitybymail.executeQuery();
			
			
			ContactDetails c=null;
			
			
			while(rs.next())
			{     				 
				Blob aBlob = rs.getBlob(11);
	            byte[] allBytesInBlob = aBlob.getBytes(1, (int) aBlob.length());
	            ByteArrayInputStream bais = new ByteArrayInputStream(allBytesInBlob);
	            BufferedImage bImage = ImageIO.read(bais);		 
				c=new ContactDetails(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getDate(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),bImage);
				 
			}
			//System.out.println(c);
			return c;
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	    return null;
	}

}
