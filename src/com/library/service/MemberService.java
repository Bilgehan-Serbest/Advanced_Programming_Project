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
 * Session Bean implementation class MemberService
 */
@Stateless
@LocalBean
public class MemberService {

    /**
     * Default constructor. 
     */
    public MemberService() {
        // TODO Auto-generated constructor stub
    }

    @PersistenceContext(unitName="librarymanagement")
    private EntityManager em;
    
    public MEMBER addMember(MEMBER m) {
    	em.persist(m);
    	return m;
    }    
    
    public List<MEMBER> getMembers(){
    	
    	TypedQuery<MEMBER> query = em.createQuery("SELECT m FROM MEMBER m", MEMBER.class);
    	
    	List<MEMBER> mList = query.getResultList();
    	
    	return mList;
    }
    
    public MEMBER getMember(Integer memberId) {
    	// Getting the Member by id
    	CriteriaBuilder builder = em.getCriteriaBuilder();
    	CriteriaQuery<MEMBER> cqMember = builder.createQuery(MEMBER.class);
    	Root<MEMBER> mRoot = cqMember.from(MEMBER.class);
    	cqMember.select(mRoot).where(builder.equal(mRoot.get("ID").as(Integer.class), memberId));
    	TypedQuery<MEMBER> mQuery = em.createQuery(cqMember);
    	MEMBER m = null;
    	try {
    		m = mQuery.getSingleResult();	
    	}catch(NoResultException e) {
    		return null;
    	}
    	return m;    	
    }    
    
    public void lendBookToMember(String bookId, String memberId) {
    	// Getting the member by id
    	CriteriaBuilder builder = em.getCriteriaBuilder();
    	CriteriaQuery<MEMBER> cqMember = builder.createQuery(MEMBER.class);
    	Root<MEMBER> mRoot = cqMember.from(MEMBER.class);
    	cqMember.select(mRoot).where(builder.equal(mRoot.get("ID").as(Integer.class), memberId));
    	TypedQuery<MEMBER> mQuery = em.createQuery(cqMember);
    	MEMBER m = mQuery.getSingleResult();
    	
    	// Getting the book by id
    	builder = em.getCriteriaBuilder();
    	CriteriaQuery<BOOK> cqBook = builder.createQuery(BOOK.class);
    	Root<BOOK> bRoot = cqBook.from(BOOK.class);
    	cqBook.select(bRoot).where(builder.equal(bRoot.get("ID").as(Integer.class), bookId));
    	TypedQuery<BOOK> bQuery = em.createQuery(cqBook);
    	BOOK b = bQuery.getSingleResult();
    	
    	List<BOOK> bList = m.getBOOKS();
    	bList.add(b);
    	m.setBOOKS(bList);
    	b.setMEMBEROFBOOK(m);    	
    }
    
    public void registerMemberToLibrary(String memberId, String libraryId) {
    	
    	TypedQuery<LIBRARY> lryQuery = em.createNamedQuery("Library.findById", LIBRARY.class);
    	
    	lryQuery.setParameter("id", Integer.parseInt(libraryId));
    	
    	LIBRARY lry = lryQuery.getSingleResult();
    	
    	TypedQuery<MEMBER> mQuery = em.createNamedQuery("MEMBER.findById", MEMBER.class);
    	
    	mQuery.setParameter("id", Integer.parseInt(memberId));
    	
    	MEMBER m = mQuery.getSingleResult();
    	
    	List<MEMBER> mList = lry.getMEMBERS();
    	
    	mList.add(m);
    	
    	lry.setMEMBERS(mList);
    	
    	m.getLIBRARIES().add(lry);
    }
    
}
