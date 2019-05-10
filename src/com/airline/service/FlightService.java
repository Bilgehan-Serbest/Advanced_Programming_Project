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

import com.airline.models.AIRPLANE;
import com.airline.models.FLIGHT;
import com.airline.models.PASSENGER;
import com.airline.models.PILOT;

/**
 * Session Bean implementation class FlightService
 */
@Stateless
@LocalBean
public class FlightService {

    /**
     * Default constructor. 
     */
    public FlightService() {
        // TODO Auto-generated constructor stub
    }
    
    @PersistenceContext(unitName="airline")
    EntityManager em;

    public void addFlight(FLIGHT f, AIRPLANE a) {
    	em.persist(f);
    	em.persist(a);    	
    }
    
    public void addPilotToFlight(String pilotId, String flightId) {
    	
    	TypedQuery<FLIGHT> fQuery = em.createNamedQuery("FLIGHT.findById", FLIGHT.class);
    	
    	fQuery.setParameter("ID", Integer.parseInt(flightId));
    	
    	FLIGHT f = fQuery.getSingleResult();
    	
    	TypedQuery<PILOT> pQuery = em.createNamedQuery("PILOT.findById", PILOT.class);
    	
    	pQuery.setParameter("ID", Integer.parseInt(pilotId));
    	
    	PILOT p = pQuery.getSingleResult();
    	
    	List<PILOT> pList = f.getPilots();
    	
    	pList.add(p);
    	
    	f.setPilots(pList);
    	
    	p.setFLIGHTFORPILOT(f);
    	
    }
    
    public void addPassengerToFlight(String passengerId, String flightId) {
    	
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
    	
    	List<PASSENGER> pList = f.getPassengers();
    	pList.add(p);
    	f.setPassengers(pList);
    	p.getFlights().add(f);
    	
    }
    
    public List<FLIGHT> getFlights(){
    	TypedQuery<FLIGHT> query = em.createQuery("SELECT f FROM FLIGHT f", FLIGHT.class);    	
    	List<FLIGHT> results = query.getResultList();
    	return results;
    }
    
    public FLIGHT getFlight(Integer flightId) {
    	
    	TypedQuery<FLIGHT> fQuery = em.createNamedQuery("FLIGHT.findById", FLIGHT.class);
    	
    	fQuery.setParameter("id", flightId);
    	
    	FLIGHT f = null;
    	
    	try {
    		f = fQuery.getSingleResult();
    	} catch(NoResultException e) {
    		return null;
    	}
    	
    	return f;
    }
    
}
