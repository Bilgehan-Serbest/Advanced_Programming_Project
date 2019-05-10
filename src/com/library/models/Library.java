package com.library.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


@NamedQuery(name = "Library.findById", query="SELECT l FROM Library l WHERE l.id =:id")
@Entity
public class LIBRARY implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public LIBRARY() {
		super();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer ID;
	
	private String NAME;
	
	private String ADDRESS;
	
	@OneToMany(mappedBy="libraryOfBook", cascade= {CascadeType.REMOVE})	
	private List<BOOK> BOOKS;
	
	@ManyToMany
	@JoinTable(name="l_m_join", joinColumns=@JoinColumn(name = "library_fk"), inverseJoinColumns=@JoinColumn(name="member_fk"))
	private List<MEMBER> members;
	
	@OneToMany(mappedBy="libraryOfLibrarian", cascade= {CascadeType.REMOVE})
	private List<LIBRARIAN> librarians;

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		this.ID = iD;
	}

	public String getNAME() {
		return NAME;
	}

	public void setNAME(String nAME) {
		this.NAME = nAME;
	}

	public String getADDRESS() {
		return ADDRESS;
	}

	public void setADDRESS(String aDDRESS) {
		this.ADDRESS = aDDRESS;
	}

	public List<MEMBER> getMEMBERS() {
		return MEMBERS;
	}

	public void setMEMBERS(List<MEMBER> mEMBERS) {
		this.MEMBERS = mEMBERS;
	}

	public List<LIBRARIAN> getLIBRARIANS() {
		return LIBRARIANS;
	}

	public void setLIBRARIANS(List<LIBRARIAN> lIBRARIANS) {
		this.LIBRARIANS = lIBRARIANS;
	}

	public List<BOOK> getBOOKS() {
		return BOOKS;
	}

	public void setBooks(List<BOOK> bOOKS) {
		this.BOOKS = bOOKS;
	}

	@Override
	public String toString() {
		return "Library [id=" + id + ", name=" + name + ", address=" + address + ", books=" + books + ", members="
				+ members + ", librarians=" + librarians + "]";
	}
	
}
