package com.demo.bean;

import java.awt.image.BufferedImage;
import java.util.Date;
//Variable declaration of users
public class User {
	
	private String uemail;
	private String uname;       //User handle
	private String ufullName;   //User full name
	private String upass;
	private String umob;
	private String ugender;
	private Date ubdate;
	private String uaddress;
	private String ucity;
	private String ustate;
	private String ucountry;
	private String ucompany;
	private BufferedImage uimage;
	private String supportQn;
	private String supportAns;
	private int blockCount;
	private boolean disableflag;
	
	//Default Constructor
	public User() {
		super();
	}

	//Parameterized constructor
	public User(String uemail, String uname, String ufullName, String upass, String umob, String ugender, Date ubdate,
			String uaddress, String ucity, String ustate, String ucountry, String ucompany, BufferedImage uimage,
			String supportQn, String supportAns, int blockCount, boolean disableflag) {
		super();
		this.uemail = uemail;
		this.uname = uname;
		this.ufullName = ufullName;
		this.upass = upass;
		this.umob = umob;
		this.ugender = ugender;
		this.ubdate = ubdate;
		this.uaddress = uaddress;
		this.ucity = ucity;
		this.ustate = ustate;
		this.ucountry = ucountry;
		this.ucompany = ucompany;
		this.uimage = uimage;
		this.supportQn = supportQn;
		this.supportAns = supportAns;
		this.blockCount = blockCount;
		this.disableflag = disableflag;
	}
	

	public User(String uemail, String uname, String ufullName, String umob, String ugender, String uaddress,
			String ucity, String ustate, String ucountry, String ucompany) {
		super();
		this.uemail = uemail;
		this.uname = uname;
		this.ufullName = ufullName;
		this.umob = umob;
		this.ugender = ugender;
		this.uaddress = uaddress;
		this.ucity = ucity;
		this.ustate = ustate;
		this.ucountry = ucountry;
		this.ucompany = ucompany;
	}

	//Tostring method
	@Override
	public String toString() {
		return "User [uemail=" + uemail + ", uname=" + uname + ", ufullName=" + ufullName + ", upass=" + upass
				+ ", umob=" + umob + ", ugender=" + ugender + ", ubdate=" + ubdate + ", uaddress=" + uaddress
				+ ", ucity=" + ucity + ", ustate=" + ustate + ", ucountry=" + ucountry + ", ucompany=" + ucompany
				+ ", uimage=" + uimage + ", supportQn=" + supportQn + ", supportAns=" + supportAns + ", blockCount="
				+ blockCount + ", disableflag=" + disableflag + "]";
	}

	//Getters and setters
	public String getUemail() {
		return uemail;
	}

	public void setUemail(String uemail) {
		this.uemail = uemail;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getUfullName() {
		return ufullName;
	}

	public void setUfullName(String ufullName) {
		this.ufullName = ufullName;
	}

	public String getUpass() {
		return upass;
	}

	public void setUpass(String upass) {
		this.upass = upass;
	}

	public String getUmob() {
		return umob;
	}

	public void setUmob(String umob) {
		this.umob = umob;
	}

	public String getUgender() {
		return ugender;
	}

	public void setUgender(String ugender) {
		this.ugender = ugender;
	}

	public Date getUbdate() {
		return ubdate;
	}

	public void setUbdate(Date ubdate) {
		this.ubdate = ubdate;
	}

	public String getUaddress() {
		return uaddress;
	}

	public void setUaddress(String uaddress) {
		this.uaddress = uaddress;
	}

	public String getUcity() {
		return ucity;
	}

	public void setUcity(String ucity) {
		this.ucity = ucity;
	}

	public String getUstate() {
		return ustate;
	}

	public void setUstate(String ustate) {
		this.ustate = ustate;
	}

	public String getUcountry() {
		return ucountry;
	}

	public void setUcountry(String ucountry) {
		this.ucountry = ucountry;
	}

	public String getUcompany() {
		return ucompany;
	}

	public void setUcompany(String ucompany) {
		this.ucompany = ucompany;
	}

	public BufferedImage getUimage() {
		return uimage;
	}

	public void setUimage(BufferedImage uimage) {
		this.uimage = uimage;
	}

	public String getSupportQn() {
		return supportQn;
	}

	public void setSupportQn(String supportQn) {
		this.supportQn = supportQn;
	}

	public String getSupportAns() {
		return supportAns;
	}

	public void setSupportAns(String supportAns) {
		this.supportAns = supportAns;
	}

	public int getBlockCount() {
		return blockCount;
	}

	public void setBlockCount(int blockCount) {
		this.blockCount = blockCount;
	}

	public boolean isDisableflag() {
		return disableflag;
	}

	public void setDisableflag(boolean disableflag) {
		this.disableflag = disableflag;
	}
	
}