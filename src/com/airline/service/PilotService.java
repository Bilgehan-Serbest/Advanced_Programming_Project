package com.airline.service;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.airline.models.FLIGHT;
import com.airline.models.PILOT;

/**
 * Session Bean implementation class PilotService
 */
@Stateless
@LocalBean
public class PilotService {
	
	@PersistenceContext(unitName="airline")
	EntityManager em;

    /**
     * Default constructor. 
     */
    public PilotService() {
        // TODO Auto-generated constructor stub
    }
    
    public void addPilot(PILOT p) {
    	em.persist(p);
    }
    
    
  public void addNewPilotToFlight(PILOT p, String flightId) {
    	
	  	em.persist(p);
	  
    	TypedQuery<FLIGHT> fQuery = em.createNamedQuery("FLIGHT.findById", FLIGHT.class);
    	
    	fQuery.setParameter("ID", Integer.parseInt(flightId));
    	
    	FLIGHT f = fQuery.getSingleResult();    	
    	
    	List<PILOT> pList = f.getPilots();
    	
    	pList.add(p);
    	
    	f.setPilots(pList);
    	
    	p.setFLIGHTFORPILOT(f);
    	
    }

}
