package com.library.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@NamedQuery(name="Member.findById", query="SELECT m FROM Member m WHERE m.id = :id")
@Entity
public class MEMBER implements Serializable{
private static final long serialVersionUID = 1L;
	
	public MEMBER() {
		super();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer ID;
	
	private String FIRSTNAME;
	
	private String LASTNAME;
	
	@Temporal(TemporalType.DATE)
	private Date DOB;
	
	@Enumerated(EnumType.STRING)
	private GENDER GENDER;
	
	@ManyToMany(mappedBy = "members")
	private List<LIBRARY> LIBRARIES;
	
	@OneToMany(mappedBy="memberOfBook", cascade= {CascadeType.REMOVE})
	private List<BOOK> BOOKS;


	public Integer getID() {
		return ID;
	}


	public void setID(Integer iD) {
		ID = iD;
	}


	public String getFIRSTNAME() {
		return FIRSTNAME;
	}


	public void setFIRSTNAME(String fIRSTNAME) {
		FIRSTNAME = fIRSTNAME;
	}


	public String getLASTNAME() {
		return LASTNAME;
	}


	public void setLASTNAME(String lASTNAME) {
		LASTNAME = lASTNAME;
	}


	public Date getDOB() {
		return DOB;
	}


	public void setDOB(Date dOB) {
		this.DOB = dOB;
	}


	public GENDER getGENDER() {
		return GENDER;
	}


	public void setGENDER(GENDER gENDER) {
		GENDER = gENDER;
	}


	public List<LIBRARY> getLIBRARIES() {
		return LIBRARIES;
	}


	public void setLIBRARIES(List<LIBRARY> lIBRARIES) {
		LIBRARIES = lIBRARIES;
	}


	public List<BOOK> getBOOKS() {
		return BOOKS;
	}


	public void setBOOKS(List<BOOK> bOOKS) {
		BOOKS = bOOKS;
	}


	@Override
	public String toString() {
		return "Member [id=" + ID + ", firstName=" + FIRSTNAME + ", lastName=" + LASTNAME + ", dob=" + DOB + ", gender="
				+ GENDER + ", libraries=" + LIBRARIES + ", books=" + BOOKS + "]";
	}
	
	
}
