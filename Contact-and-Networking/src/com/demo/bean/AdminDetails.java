package com.demo.bean;
//variable declaration of Admin 
public class AdminDetails {

	private String aEmail;
	private String aName;
	private String aPass;
	private String aMob;
	
	//Default Constructor
	public AdminDetails() {
		super();
	}
	
	//Parameterized Constructor
	public AdminDetails(String aemail, String aname, String apass, String amob) {
		super();
		this.aEmail = aemail;
		this.aName = aname;
		this.aPass = apass;
		this.aMob = amob;
	}
	
	//Overrided toString method
	@Override
	public String toString() {
		return "Admin [aemail=" + aEmail + ", aname=" + aName + ", apass=" + aPass + ", amob=" + aMob + "]";
	}
	
	//Getters and setters
	public String getAemail() {
		return aEmail;
	}
	public void setAemail(String aemail) {
		this.aEmail = aemail;
	}
	public String getAname() {
		return aName;
	}
	public void setAname(String aname) {
		this.aName = aname;
	}
	public String getApass() {
		return aPass;
	}
	public void setApass(String apass) {
		this.aPass = apass;
	}
	public String getAmob() {
		return aMob;
	}
	public void setAmob(String amob) {
		this.aMob = amob;
	}
}
	