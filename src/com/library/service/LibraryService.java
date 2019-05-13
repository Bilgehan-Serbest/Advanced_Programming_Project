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
public class LibraryService {

    /**
     * Default constructor. 
     */
    public LibraryService() {
        // TODO Auto-generated constructor stub
    }
    
    @PersistenceContext(unitName="librarymanagement")
    EntityManager em;

    public LIBRARY addLibrary(LIBRARY l) {
    	em.persist(l);    	
    	return l;
    }

  public void addLibrarianToLibrary(LIBRARIAN lrn, String libraryId) {
    	
    	TypedQuery<LIBRARY> lryQuery = em.createNamedQuery("LIBRARY.findById", LIBRARY.class);
    	
    	lryQuery.setParameter("ID", Integer.parseInt(libraryId));
    	
    	LIBRARY lry = lryQuery.getSingleResult();
    	
    	em.persist(lrn);
    	
    	List<LIBRARIAN> lrnList = lry.getLIBRARIANS();
    	
    	lrnList.add(lrn);
    	
    	lry.setLIBRARIANS(lrnList);
    	
    	lrn.setLIBRARYOFLIBRARIAN(lry);
    	
    }
  
  public void addBookToLibrary(BOOK b, String libraryId) {
  	
		TypedQuery<LIBRARY> lryQuery = em.createNamedQuery("LIBRARY.findById", LIBRARY.class);
		
		lryQuery.setParameter("ID", Integer.parseInt(libraryId));
		
		LIBRARY lry = lryQuery.getSingleResult();
			
		em.persist(b);
		
		List<BOOK> bList = lry.getBOOKS();
		
		bList.add(b);
		
		lry.setBOOKS(bList);
		
		b.setLIBRARYOFBOOK(lry);
  	
  }
  
  public void addMemberToLibrary(String memberId, String libraryId) {
  	
	  	// Getting the member by id
	  	CriteriaBuilder builder = em.getCriteriaBuilder();
	  	CriteriaQuery<MEMBER> cqMember = builder.createQuery(MEMBER.class);
	  	Root<MEMBER> mRoot = cqMember.from(MEMBER.class);
	  	cqMember.select(mRoot).where(builder.equal(mRoot.get("ID").as(Integer.class), memberId));
	  	TypedQuery<MEMBER> mQuery = em.createQuery(cqMember);
	  	MEMBER m = mQuery.getSingleResult();
	  	
	  	// Getting the library by id
	  	builder = em.getCriteriaBuilder();
	  	CriteriaQuery<LIBRARY> cqLibrary = builder.createQuery(LIBRARY.class);
	  	Root<LIBRARY> lRoot = cqLibrary.from(LIBRARY.class);
	  	cqLibrary.select(lRoot).where(builder.equal(lRoot.get("ID").as(Integer.class), libraryId));
	  	TypedQuery<LIBRARY> lQuery = em.createQuery(cqLibrary);
	  	LIBRARY l = lQuery.getSingleResult();
	  	
	  	List<MEMBER> mList = l.getMEMBERS();
	  	mList.add(m);
	  	l.setMEMBERS(mList);
	  	m.getLIBRARIES().add(l);
	  	
  }
  
  public List<LIBRARY> getLibraries(){
	  	TypedQuery<LIBRARY> query = em.createQuery("SELECT l FROM LIBRARY l", LIBRARY.class);    	
	  	List<LIBRARY> results = query.getResultList();
	  	return results;
  }
  
  public LIBRARY getLibrary(Integer libraryId) {
  	
	  	TypedQuery<LIBRARY> lQuery = em.createNamedQuery("LIBRARY.findById", LIBRARY.class);
	  	
	  	lQuery.setParameter("ID", libraryId);
	  	
	  	LIBRARY l = null;
	  	
	  	try {
	  		l = lQuery.getSingleResult();
	  	} catch(NoResultException e) {
	  		return null;
	  	}
	  	
	  	return l;
  }
  
  
  
    
}
