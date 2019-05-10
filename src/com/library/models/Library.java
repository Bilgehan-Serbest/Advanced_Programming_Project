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

import com.airline.models.AIRPLANE;
import com.airline.models.PASSENGER;
import com.airline.models.PILOT;

@NamedQuery(name = "Library.findById", query="SELECT l FROM Library l WHERE l.id =:id")
@Entity
public class Library implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public Library() {
		super();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String name;
	
	private String address;
	
	@OneToMany(mappedBy="libraryOfBook", cascade= {CascadeType.REMOVE})	
	private List<Book> books;
	
	@ManyToMany
	@JoinTable(name="l_m_join", joinColumns=@JoinColumn(name = "library_fk"), inverseJoinColumns=@JoinColumn(name="member_fk"))
	private List<Member> members;
	
	@OneToMany(mappedBy="libraryOfLibrarian", cascade= {CascadeType.REMOVE})
	private List<Librarian> librarians;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<Member> getMembers() {
		return members;
	}

	public void setMembers(List<Member> members) {
		this.members = members;
	}

	public List<Librarian> getLibrarians() {
		return librarians;
	}

	public void setLibrarians(List<Librarian> librarians) {
		this.librarians = librarians;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	@Override
	public String toString() {
		return "Library [id=" + id + ", name=" + name + ", address=" + address + ", books=" + books + ", members="
				+ members + ", librarians=" + librarians + "]";
	}
	
}
