package com.demo.bean;

import java.awt.image.BufferedImage;
import java.util.Date;
//Variable declaration of Contacts
public class ContactDetails {
	
	
	private String cEmail;
	private String cFullName;   
	private String cMob;
	private String cGender;
	private Date cbDate;
	private String cAddress;
	private String cCity;
	private String cState;
	private String cCountry;
	private String cCompany;
	private BufferedImage cImage;

	//Default Constructor
	public ContactDetails() {
		super();
	}
	
	//Parameterized Constructor
	public ContactDetails(String cemail, String cfullName, String cmob, String cgender, Date cbdate, String caddress,
			String ccity, String cstate, String ccountry, String ccompany, BufferedImage cimage) {
		super();
		
		
		this.cEmail = cemail;
		this.cFullName = cfullName;
		this.cMob = cmob;
		this.cGender = cgender;
		this.cbDate = cbdate;
		this.cAddress = caddress;
		this.cCity = ccity;
		this.cState = cstate;
		this.cCountry = ccountry;
		this.cCompany = ccompany;
		this.cImage = cimage;
	}

	
	
	
	
	public ContactDetails(String cEmail, String cFullName, String cMob, String cCity, BufferedImage cImage) {
		super();
		this.cEmail = cEmail;
		this.cFullName = cFullName;
		this.cMob = cMob;
		this.cCity = cCity;
		this.cImage = cImage;
	}

	
	
	
	public ContactDetails(String cEmail, String cFullName, String cMob, String cCity) {
		super();
		this.cEmail = cEmail;
		this.cFullName = cFullName;
		this.cMob = cMob;
		this.cCity = cCity;
	}

	//Overrided toString method
	@Override
	public String toString() {
		
		return "Contact [cemail=" + cEmail + ", cfullName=" + cFullName + ", cmob=" + cMob + ", cgender=" + cGender
				+ ", cbdate=" + cbDate + ", caddress=" + cAddress + ", ccity=" + cCity + ", cstate=" + cState
				+ ", ccountry=" + cCountry + ", ccompany=" + cCompany + ", cimage=" + cImage + "]";
	}

	//Getters and setters
	public String getCemail() {
		
		return cEmail;
	}
	public void setCemail(String cemail) {
		
		this.cEmail = cemail;
	}
	public String getCfullName() {
		
		return cFullName;
	}
	public void setCfullName(String cfullName) {
		
		this.cFullName = cfullName;
	}
	public String getCmob() {
		
		return cMob;
	}
	public void setCmob(String cmob) {
		
		this.cMob = cmob;
	}
	public String getCgender() {
		
		return cGender;
	}
	public void setCgender(String cgender) {
		
		this.cGender = cgender;
	}
	public Date getCbdate() {
		
		return cbDate;
	}
	public void setCbdate(Date cbdate) {
		
		this.cbDate = cbdate;
	}
	public String getCaddress() {
		
		return cAddress;
	}
	public void setCaddress(String caddress) {
		
		this.cAddress = caddress;
	}
	public String getCcity() {
		
		return cCity;
	}
	public void setCcity(String ccity) {
		
		this.cCity = ccity;
	}
	public String getCstate() {
		
		return cState;
	}
	public void setCstate(String cstate) {
		
		this.cState = cstate;
	}
	public String getCcountry() {
		
		return cCountry;
	}
	public void setCcountry(String ccountry) {
		
		this.cCountry = ccountry;
	}
	public String getCcompany() {
	
		return cCompany;
	}
	public void setCcompany(String ccompany) {
		
		this.cCompany = ccompany;
	}
	public BufferedImage getCimage() {
	
		return cImage;
	}
	public void setCimage(BufferedImage cimage) {
	
		this.cImage = cimage;
	}

}
