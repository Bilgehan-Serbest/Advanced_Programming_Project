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

import com.airline.models.FLIGHT;

@NamedQuery(name="Book.findById", query="SELECT b FROM Book b WHERE b.id = :id")
@Entity
public class Book implements Serializable{
private static final long serialVersionUID = 1L;
	
	public Book() {
		super();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String title;
	
	private String author;
	
	private String publisher;
		
	@ManyToOne
	@JoinColumn(name="library_fk")
	private Library libraryOfBook;
	
	@ManyToOne
	@JoinColumn(name="member_fk")
	private Member memberOfBook;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}


	public Member getMemberOfBook() {
		return memberOfBook;
	}

	public void setMemberOfBook(Member memberOfBook) {
		this.memberOfBook = memberOfBook;
	}

	public Library getLibraryOfBook() {
		return libraryOfBook;
	}

	public void setLibraryOfBook(Library libraryOfBook) {
		this.libraryOfBook = libraryOfBook;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", author=" + author + ", publisher=" + publisher
				+ ", libraryOfBook=" + libraryOfBook + ", memberOfBook=" + memberOfBook + "]";
	}


	
	

}
