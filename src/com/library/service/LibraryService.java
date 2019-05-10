package com.library.service;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.airline.models.AIRPLANE;
import com.airline.models.FLIGHT;
import com.airline.models.PASSENGER;
import com.airline.models.PILOT;
import com.library.models.Book;
import com.library.models.Librarian;
import com.library.models.Library;
import com.library.models.Member;

/**
 * Session Bean implementation class LibraryService
 */
@Stateless
@LocalBean
public class LibraryService {

    /**
     * Default constructor. 
     */
    public LibraryService() {
        // TODO Auto-generated constructor stub
    }
    
    @PersistenceContext(unitName="librarymanagement")
    EntityManager em;

    public void addLibrary(Library l) {
    	em.persist(l);    	
    }

  public void addLibrarianToLibrary(String librarianId, String libraryId) {
    	
    	TypedQuery<Library> lryQuery = em.createNamedQuery("Library.findById", Library.class);
    	
    	lryQuery.setParameter("id", Integer.parseInt(libraryId));
    	
    	Library lry = lryQuery.getSingleResult();
    	
    	TypedQuery<Librarian> lrnQuery = em.createNamedQuery("Librarian.findById", Librarian.class);
    	
    	lrnQuery.setParameter("id", Integer.parseInt(librarianId));
    	
    	Librarian lrn = lrnQuery.getSingleResult();
    	
    	List<Librarian> lrnList = lry.getLibrarians();
    	
    	lrnList.add(lrn);
    	
    	lry.setLibrarians(lrnList);
    	
    	lrn.setLibraryOfLibrarian(lry);
    	
    }
  
  public void addBookToLibrary(String bookId, String libraryId) {
  	
		TypedQuery<Library> lryQuery = em.createNamedQuery("Library.findById", Library.class);
		
		lryQuery.setParameter("id", Integer.parseInt(libraryId));
		
		Library lry = lryQuery.getSingleResult();
		
		TypedQuery<Book> bQuery = em.createNamedQuery("Book.findById", Book.class);
		
		bQuery.setParameter("id", Integer.parseInt(bookId));
		
		Book b = bQuery.getSingleResult();
		
		List<Book> bList = lry.getBooks();
		
		bList.add(b);
		
		lry.setBooks(bList);
		
		b.setLibraryOfBook(lry);
  	
  }
  
  public void addMemberToLibrary(String memberId, String libraryId) {
  	
	  	// Getting the member by id
	  	CriteriaBuilder builder = em.getCriteriaBuilder();
	  	CriteriaQuery<Member> cqMember = builder.createQuery(Member.class);
	  	Root<Member> mRoot = cqMember.from(Member.class);
	  	cqMember.select(mRoot).where(builder.equal(mRoot.get("id").as(Integer.class), memberId));
	  	TypedQuery<Member> mQuery = em.createQuery(cqMember);
	  	Member m = mQuery.getSingleResult();
	  	
	  	// Getting the library by id
	  	builder = em.getCriteriaBuilder();
	  	CriteriaQuery<Library> cqLibrary = builder.createQuery(Library.class);
	  	Root<Library> lRoot = cqLibrary.from(Library.class);
	  	cqLibrary.select(lRoot).where(builder.equal(lRoot.get("id").as(Integer.class), libraryId));
	  	TypedQuery<Library> lQuery = em.createQuery(cqLibrary);
	  	Library l = lQuery.getSingleResult();
	  	
	  	List<Member> mList = l.getMembers();
	  	mList.add(m);
	  	l.setMembers(mList);
	  	m.getLibraries().add(l);
	  	
  }
  
  public List<Library> getLibraries(){
	  	TypedQuery<Library> query = em.createQuery("SELECT l FROM Library l", Library.class);    	
	  	List<Library> results = query.getResultList();
	  	return results;
  }
  
  public Library getLibrary(Integer libraryId) {
  	
	  	TypedQuery<Library> lQuery = em.createNamedQuery("Library.findById", Library.class);
	  	
	  	lQuery.setParameter("id", libraryId);
	  	
	  	Library l = null;
	  	
	  	try {
	  		l = lQuery.getSingleResult();
	  	} catch(NoResultException e) {
	  		return null;
	  	}
	  	
	  	return l;
  }
  
  
  
    
}
