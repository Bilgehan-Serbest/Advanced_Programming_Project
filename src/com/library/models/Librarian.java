package com.library.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

import com.airline.models.FLIGHT;

@NamedQuery(name="Librarian.findById", query="SELECT l FROM Librarian l WHERE l.id = :id")
@Entity
public class Librarian implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public Librarian() {
		super();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String firstName;
	
	private String lastName;
	
	@ManyToOne
	@JoinColumn(name="library_fk")
	private Library libraryOfLibrarian;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Library getLibraryOfLibrarian() {
		return libraryOfLibrarian;
	}

	public void setLibraryOfLibrarian(Library libraryOfLibrarian) {
		this.libraryOfLibrarian = libraryOfLibrarian;
	}

	@Override
	public String toString() {
		return "Librarian [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", libraryOfLibrarian="
				+ libraryOfLibrarian + "]";
	}
	
	
}
