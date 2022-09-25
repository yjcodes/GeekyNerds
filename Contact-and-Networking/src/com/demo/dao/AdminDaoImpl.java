  package com.demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSSerializer;

import com.demo.bean.User;

public class AdminDaoImpl implements AdminDao{	
	
	static Connection conn;
	static PreparedStatement pgetdisableusers,pgetdisableusers2, pgetnotdisableusers, pgetbyusername, pdeleteuser, pgetuserbyuname, pgetalluser,pupdateuserflag, premovebyname, pgetblockedcount, pgetdisableuser, gettotalusers,getdistinctcities,agetadmin;
	static boolean enableFlag=false;
	static boolean disableFlag=true;
	static Document doc =null;
	static {
		conn=DBUtil.getMyConnection();
		try {
			agetadmin=conn.prepareStatement("select * from admindetails");//to get admin details from database
			pupdateuserflag=conn.prepareStatement("update userdetails set disableflag=? where uname=?");//query to set disable flag
			pgetdisableusers=conn.prepareStatement("select * from userdetails where disableflag=true");//get all details of disabled user
			pgetdisableusers2=conn.prepareStatement("select uname from userdetails where disableflag=true");//get username of disabled user
			pgetbyusername=conn.prepareStatement("select * from userdetails where uname=? ");
			pgetuserbyuname=conn.prepareStatement("select * from userdetails where uname=?");// get user by username
			pgetblockedcount=conn.prepareStatement("select blockcount from userdetails where uname=?"); // block count of a user
			pgetdisableuser=conn.prepareStatement("select * from userdetails where blockcount>=3 AND disableflag=false");    // to get list of users who have been blocked by 3 or more
			pgetalluser=conn.prepareStatement("select * from userdetails");   
			gettotalusers=conn.prepareStatement("select COUNT(*) from userdetails"); 	//to show total no of users
			getdistinctcities=conn.prepareStatement("select COUNT(distinct ucity) from userdetails"); 	// to show from how many different cities users are registered in this app:
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	//gets admindetails from database
		public String getAdminDetails() {
			ResultSet rs;
			try {
				rs = agetadmin.executeQuery();
				doc = toDocument(rs);
				String xmlStr=getStringFromDoc(doc);
				return xmlStr;
			} catch (SQLException | ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
			 
		}
		
		//converts result set to document
		public Document toDocument(ResultSet rs) throws ParserConfigurationException, SQLException {
			   DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			   DocumentBuilder builder        = factory.newDocumentBuilder();
			   Document doc                   = builder.newDocument();
			   Element results = (Element) doc.createElement("AdminDetails");
			   doc.appendChild((Node) results);
			   ResultSetMetaData rsmd = rs.getMetaData();
			   int colCount = rsmd.getColumnCount();
			   while (rs.next())
			   {
			      Element row = (Element) doc.createElement("Admin");
			      ((Node) results).appendChild((Node) row);
			      for (int i = 1; i <= colCount; i++)
			      {
			         String columnName = rsmd.getColumnName(i);
			         Object value = rs.getObject(i);
			         Element node = (Element) doc.createElement(columnName);
			         ((Node) node).appendChild(doc.createTextNode(value.toString()));
			         ((Node) row).appendChild((Node) node);
			      }
			   }
			   return doc;
			}
		
		
		//converts doc to string
		public String getStringFromDoc(org.w3c.dom.Document doc)  {
		    DOMImplementationLS domImplementation = (DOMImplementationLS) doc.getImplementation();
		    LSSerializer lsSerializer = domImplementation.createLSSerializer();
		    return lsSerializer.writeToString(doc);   
		}
	
	

	@Override
	public List<User> getByDisabledUser() {
		List<User> ulist=new ArrayList<>();
		
		ResultSet rs;
		try {
			rs = pgetdisableusers.executeQuery();
			while(rs.next()) {  
				User u=new User(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getDate(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12),null,rs.getString(14),rs.getString(15),rs.getInt(16),rs.getBoolean(17));
			ulist.add(u);
			} 
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ulist;
	}

	public List<String> getDisabledUserName() {
		ResultSet rs;
		List<String> ulist=new ArrayList<>();
		try {
			rs = pgetdisableusers2.executeQuery();
			while(rs.next()) {  
				
				ulist.add(rs.getString(1));				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ulist;
	}


	private User findByUsername(String s) {
		try { 
			pgetbyusername.setString(1, s);
			ResultSet rs=pgetbyusername.executeQuery();
			//List<User> pList=new ArrayList<>();
			User p=null;
			while(rs.next())
			{
			    p=new User(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getDate(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12),null,rs.getString(14),rs.getString(15),rs.getInt(16),rs.getBoolean(17));
				
			}
			return p;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@Override


	public void enableUser(String s) {
		User u=findByUsername(s);
		//u.setDisableflag(enableFlag);
		try {
			pupdateuserflag.setBoolean(1,enableFlag);
			pupdateuserflag.setString(2, u.getUname());
			pupdateuserflag.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void disableUser(String s) {
		User u=findByUsername(s);
		//u.setDisableflag(disableFlag);
		try {
			pupdateuserflag.setBoolean(1,disableFlag);
			pupdateuserflag.setString(2, u.getUname());
			pupdateuserflag.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}



	public User getUserByUname(String uname) {
		try {
			pgetuserbyuname.setString(1, uname);
			ResultSet rs = pgetuserbyuname.executeQuery();
			User p=new User(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getDate(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12),null,rs.getString(14),rs.getString(15),rs.getInt(16),rs.getBoolean(17));
			return p;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;	
	}

	
	


	

	

	@Override
	public List<User> getByUserToDisable() {
		ResultSet rs;
		List<User> ulist=new ArrayList<>();
		try {
			rs = pgetdisableuser.executeQuery();
			while(rs.next()) {  
				
				User u=new User(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getDate(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12),null,rs.getString(14),rs.getString(15),rs.getInt(16),rs.getBoolean(17));
				ulist.add(u);				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ulist;
	}

	public int getNoOfDiffCities() {
		
		
		ResultSet rs;
		int noOfDiffCities=0;
		try {
			rs = getdistinctcities.executeQuery();
			while(rs.next()) {  
			
			noOfDiffCities=rs.getInt(1);
			} }catch (SQLException e) {
			e.printStackTrace();
			}
		
		return noOfDiffCities;
		
	}
	
	
	//function to show from how many different cities users are registered in this app:
	@Override
	public int getNoOfUsers() {
		
		ResultSet rs;
		int noOfUsers=0;
		try {
			rs = gettotalusers.executeQuery();
			while(rs.next()) {  
			
			noOfUsers=rs.getInt(1);
			} }catch (SQLException e) {
			e.printStackTrace();
			}
		
		return noOfUsers;
		
	}


	@Override
	public String getAdmDetails(String s) {
		ResultSet rs;
		String data=null;
		try {
			PreparedStatement getAdminDetails = conn.prepareStatement("select * from admindetails where aname=?");
			
			rs=getAdminDetails.executeQuery();
			while(rs.next())
			{
				data="Name: "+rs.getString(2)+"; Email: "+ rs.getString(1)+ "; Mobile: "+ rs.getString(4);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;

	}




}	