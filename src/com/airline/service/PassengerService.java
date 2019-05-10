package com.airline.service;

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

import com.airline.models.FLIGHT;
import com.airline.models.PASSENGER;

/**
 * Session Bean implementation class PassengerService
 */
@Stateless
@LocalBean
public class PassengerService {

    /**
     * Default constructor. 
     */
    public PassengerService() {
        // TODO Auto-generated constructor stub
    }

    @PersistenceContext(unitName="airline")
    private EntityManager em;
    
    public PASSENGER addPassenger(PASSENGER p) {
    	em.persist(p);
    	return p;
    }
    
    public void addFlightTicketToPassenger(String flightId, String passengerId) {
    	// Getting the passenger by id
    	CriteriaBuilder builder = em.getCriteriaBuilder();
    	CriteriaQuery<PASSENGER> cqPassenger = builder.createQuery(PASSENGER.class);
    	Root<PASSENGER> pRoot = cqPassenger.from(PASSENGER.class);
    	cqPassenger.select(pRoot).where(builder.equal(pRoot.get("ID").as(Integer.class), passengerId));
    	TypedQuery<PASSENGER> pQuery = em.createQuery(cqPassenger);
    	PASSENGER p = pQuery.getSingleResult();
    	
    	// Getting the flight by id
    	builder = em.getCriteriaBuilder();
    	CriteriaQuery<FLIGHT> cqFlight = builder.createQuery(FLIGHT.class);
    	Root<FLIGHT> fRoot = cqFlight.from(FLIGHT.class);
    	cqFlight.select(fRoot).where(builder.equal(fRoot.get("ID").as(Integer.class), flightId));
    	TypedQuery<FLIGHT> fQuery = em.createQuery(cqFlight);
    	FLIGHT f = fQuery.getSingleResult();
    	
    	List<FLIGHT> fList = p.getFlights();
    	fList.add(f);
    	p.setFlights(fList);
    	f.getPassengers().add(p);
    }
    
    public List<PASSENGER> getPassengers(){
    	
    	TypedQuery<PASSENGER> query = em.createQuery("SELECT p FROM PASSENGER p", PASSENGER.class);
    	
    	List<PASSENGER> pList = query.getResultList();
    	
    	return pList;
    }
    
    public PASSENGER getPassenger(Integer passengerId) {
    	// Getting the passenger by id
    	CriteriaBuilder builder = em.getCriteriaBuilder();
    	CriteriaQuery<PASSENGER> cqPassenger = builder.createQuery(PASSENGER.class);
    	Root<PASSENGER> pRoot = cqPassenger.from(PASSENGER.class);
    	cqPassenger.select(pRoot).where(builder.equal(pRoot.get("ID").as(Integer.class), passengerId));
    	TypedQuery<PASSENGER> pQuery = em.createQuery(cqPassenger);
    	PASSENGER p = null;
    	try {
    		p = pQuery.getSingleResult();	
    	}catch(NoResultException e) {
    		return null;
    	}
    	return p;    	
    }
    
    public PASSENGER updatePassenger(Integer passengerId, PASSENGER pUpdated) {
    		
    	PASSENGER p = em.find(PASSENGER.class, passengerId);
    	
    	if(p==null) {
    		return null;
    	}
    	if(pUpdated.getFIRSTNAME() != null) {
    		p.setFIRSTNAME(pUpdated.getFIRSTNAME());
    	}
    	if(pUpdated.getLASTNAME() != null) {
    		p.setLASTNAME(pUpdated.getLASTNAME());
    	}
    	if(pUpdated.getDOB() != null) {
    		p.setDOB(pUpdated.getDOB());
    	}
    	if(pUpdated.getGENDER() != null) {
    		p.setGENDER(pUpdated.getGENDER());
    	}
    	
    	return p;
    	
    }
    
    
	public PASSENGER updatePassenger2(Integer passengerId, PASSENGER pUpdated) {
	    	
    	pUpdated.setID(passengerId);
    	
    	PASSENGER pCheckExist = em.find(PASSENGER.class, passengerId);
    	
    	if(pCheckExist == null) {
    		return null;
    	}
    	
    	em.merge(pUpdated);
    	
    	return pUpdated;    	
	    	
	 }
}
