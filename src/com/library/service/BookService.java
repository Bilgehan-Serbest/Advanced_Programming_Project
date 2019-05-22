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

import com.library.models.BOOK;

/**
 * Session Bean implementation class BookService
 */
@Stateless
@LocalBean
public class BookService {

    /**
     * Default constructor. 
     */
    public BookService() {
        // TODO Auto-generated constructor stub
    }
    @PersistenceContext(unitName="librarymanagement")
    EntityManager em;
    
    public BOOK addBook(BOOK b) {
    	em.persist(b);
    	return b;
    } 
    
    public List<BOOK> getBooks(){
	  	TypedQuery<BOOK> query = em.createQuery("SELECT b FROM BOOK b", BOOK.class);    	
	  	List<BOOK> results = query.getResultList();
	  	return results;
    }
    
    public BOOK getBook(Integer bookId) {
    	// Getting the book by id
    	CriteriaBuilder builder = em.getCriteriaBuilder();
    	CriteriaQuery<BOOK> cqbook = builder.createQuery(BOOK.class);
    	Root<BOOK> bRoot = cqbook.from(BOOK.class);
    	cqbook.select(bRoot).where(builder.equal(bRoot.get("ID").as(Integer.class), bookId));
    	TypedQuery<BOOK> mQuery = em.createQuery(cqbook);
    	BOOK b = null;
    	try {
    		b= mQuery.getSingleResult();	
    	}catch(NoResultException e) {
    		return null;
    	}
    	return b;    	
    }    
    
    public BOOK updateBook(Integer bookId, BOOK bUpdated) {
		
    	BOOK b = em.find(BOOK.class, bookId);
    	
    	if(b==null) {
    		return null;
    	}
    	if(bUpdated.getTITLE() != null) {
    		b.setTITLE(bUpdated.getTITLE());
    	}
    	if(bUpdated.getAUTHOR() != null) {
    		b.setAUTHOR(bUpdated.getAUTHOR());
    	}
    	if(bUpdated.getPUBLISHER() != null) {
    		b.setPUBLISHER(bUpdated.getPUBLISHER());
    	}    	
    	
    	return b;
    	
    }
    
}
