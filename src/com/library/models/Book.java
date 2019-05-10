package com.library.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

@NamedQuery(name="BOOK.findById", query="SELECT b FROM BOOK b WHERE b.ID = :ID")
@Entity
public class BOOK implements Serializable{
private static final long serialVersionUID = 1L;
	
	public BOOK() {
		super();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer ID;
	
	private String TITLE;
	
	private String AUTHOR;
	
	private String PUBLISHER;
		
	@ManyToOne
	@JoinColumn(name="library_fk")
	private LIBRARY LIBRARYOFBOOK;
	
	@ManyToOne
	@JoinColumn(name="member_fk")
	private MEMBER MEMBEROFBOOK;

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public String getTITLE() {
		return TITLE;
	}

	public void setTITLE(String tITLE) {
		TITLE = tITLE;
	}

	public String getAUTHOR() {
		return AUTHOR;
	}

	public void setAUTHOR(String aUTHOR) {
		AUTHOR = aUTHOR;
	}

	public String getPUBLISHER() {
		return PUBLISHER;
	}

	public void setPUBLISHER(String pUBLISHER) {
		PUBLISHER = pUBLISHER;
	}

	public LIBRARY getLIBRARYOFBOOK() {
		return LIBRARYOFBOOK;
	}

	public void setLIBRARYOFBOOK(LIBRARY lIBRARYOFBOOK) {
		LIBRARYOFBOOK = lIBRARYOFBOOK;
	}

	public MEMBER getMEMBEROFBOOK() {
		return MEMBEROFBOOK;
	}

	public void setMEMBEROFBOOK(MEMBER mEMBEROFBOOK) {
		MEMBEROFBOOK = mEMBEROFBOOK;
	}

//	@Override
//	public String toString() {
//		return "BOOK [ID=" + ID + ", TITLE=" + TITLE + ", AUTHOR=" + AUTHOR + ", PUBLISHER=" + PUBLISHER
//				+ ", LIBRARYOFBOOK=" + LIBRARYOFBOOK + ", MEMBEROFBOOK=" + MEMBEROFBOOK + "]";
//	}

	

}
