package com.library.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

@NamedQuery(name="Librarian.findById", query="SELECT l FROM Librarian l WHERE l.id = :id")
@Entity
public class LIBRARIAN implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public LIBRARIAN() {
		super();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer ID;
	
	private String FIRSTNAME;
	
	private String LASTNAME;
	
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




	public LIBRARY getLIBRARYOFLIBRARIAN() {
		return LIBRARYOFLIBRARIAN;
	}




	public void setLIBRARYOFLIBRARIAN(LIBRARY lIBRARYOFLIBRARIAN) {
		LIBRARYOFLIBRARIAN = lIBRARYOFLIBRARIAN;
	}

	@ManyToOne
	@JoinColumn(name="library_fk")
	private LIBRARY LIBRARYOFLIBRARIAN;

	


	@Override
	public String toString() {
		return "Librarian [id=" + ID + ", firstName=" + FIRSTNAME + ", lastName=" + LASTNAME + ", libraryOfLibrarian="
				+ LIBRARYOFLIBRARIAN + "]";
	}
	
	
}
