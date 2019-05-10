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

import com.library.models.Book;
import com.library.models.Librarian;
import com.library.models.Library;
import com.library.models.Member;

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
    
    public Member addMember(Member m) {
    	em.persist(m);
    	return m;
    }    
    
    public List<Member> getMembers(){
    	
    	TypedQuery<Member> query = em.createQuery("SELECT m FROM Member m", Member.class);
    	
    	List<Member> mList = query.getResultList();
    	
    	return mList;
    }
    
    public Member getMember(Integer memberId) {
    	// Getting the Member by id
    	CriteriaBuilder builder = em.getCriteriaBuilder();
    	CriteriaQuery<Member> cqMember = builder.createQuery(Member.class);
    	Root<Member> mRoot = cqMember.from(Member.class);
    	cqMember.select(mRoot).where(builder.equal(mRoot.get("id").as(Integer.class), memberId));
    	TypedQuery<Member> mQuery = em.createQuery(cqMember);
    	Member m = null;
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
    	CriteriaQuery<Member> cqMember = builder.createQuery(Member.class);
    	Root<Member> mRoot = cqMember.from(Member.class);
    	cqMember.select(mRoot).where(builder.equal(mRoot.get("id").as(Integer.class), memberId));
    	TypedQuery<Member> mQuery = em.createQuery(cqMember);
    	Member m = mQuery.getSingleResult();
    	
    	// Getting the book by id
    	builder = em.getCriteriaBuilder();
    	CriteriaQuery<Book> cqBook = builder.createQuery(Book.class);
    	Root<Book> bRoot = cqBook.from(Book.class);
    	cqBook.select(bRoot).where(builder.equal(bRoot.get("id").as(Integer.class), bookId));
    	TypedQuery<Book> bQuery = em.createQuery(cqBook);
    	Book b = bQuery.getSingleResult();
    	
    	List<Book> bList = m.getBooks();
    	bList.add(b);
    	m.setBooks(bList);
    	b.setMemberOfBook(m);    	
    }
    
    public void registerMemberToLibrary(String memberId, String libraryId) {
    	
    	TypedQuery<Library> lryQuery = em.createNamedQuery("Library.findById", Library.class);
    	
    	lryQuery.setParameter("id", Integer.parseInt(libraryId));
    	
    	Library lry = lryQuery.getSingleResult();
    	
    	TypedQuery<Member> mQuery = em.createNamedQuery("Member.findById", Member.class);
    	
    	mQuery.setParameter("id", Integer.parseInt(memberId));
    	
    	Member m = mQuery.getSingleResult();
    	
    	List<Member> mList = lry.getMembers();
    	
    	mList.add(m);
    	
    	lry.setMembers(mList);
    	
    	m.getLibraries().add(lry);
    }
    
}
