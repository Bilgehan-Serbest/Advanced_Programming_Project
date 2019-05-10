package com.library.service;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

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
    
    public List<BOOK> getBooks(){
	  	TypedQuery<BOOK> query = em.createQuery("SELECT b FROM BOOK b", BOOK.class);    	
	  	List<BOOK> results = query.getResultList();
	  	return results;
}
}
