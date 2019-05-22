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
import com.library.models.LIBRARIAN;
import com.library.models.LIBRARY;
import com.library.models.MEMBER;


/**
 * Session Bean implementation class LibraryService
 */
@Stateless
@LocalBean
public class LibrarianService {
	 public LibrarianService() {
	        // TODO Auto-generated constructor stub
	    }
	    
	    @PersistenceContext(unitName="librarymanagement")
	    EntityManager em;

	    public LIBRARIAN addLibrarian(LIBRARIAN lrn) {
	    	em.persist(lrn);    	
	    	return lrn;
	    }	         

	  
	  public List<LIBRARIAN> getLibrarians(){
		  	TypedQuery<LIBRARIAN> query = em.createQuery("SELECT l FROM LIBRARIAN l", LIBRARIAN.class);    	
		  	List<LIBRARIAN> results = query.getResultList();
		  	return results;
	  }
	  
	  public LIBRARIAN getLibrarian(Integer librarianId) {
		  	
		  	TypedQuery<LIBRARIAN> lrnQuery = em.createNamedQuery("LIBRARIAN.findById", LIBRARIAN.class);
		  	
		  	lrnQuery.setParameter("ID", librarianId);
		  	
		  	LIBRARIAN lrn = null;
		  	
		  	try {
		  		lrn = lrnQuery.getSingleResult();
		  	} catch(NoResultException e) {
		  		return null;
		  	}
		  	
		  	return lrn;
	  }
	  
	  
	  public LIBRARIAN updateLibrarian(Integer librarianId, LIBRARIAN lrnUpdated) {
			
		  LIBRARIAN lrn = em.find(LIBRARIAN.class, librarianId);
	    	
	    	if(lrn==null) {
	    		return null;
	    	}
	    	if(lrnUpdated.getFIRSTNAME() != null) {
	    		lrn.setFIRSTNAME(lrnUpdated.getFIRSTNAME());
	    	}
	    	if(lrnUpdated.getLASTNAME() != null) {
	    		lrn.setLASTNAME(lrnUpdated.getLASTNAME());
	    	}	    	
	    	return lrn;	    	
	    }
	  
} 
 
