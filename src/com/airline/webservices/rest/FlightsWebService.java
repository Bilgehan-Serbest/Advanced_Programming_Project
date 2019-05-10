package com.airline.webservices.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.airline.models.FLIGHT;
import com.airline.service.FlightService;
import com.airline.service.PassengerService;
import javax.transaction.Transactional;

@Path("/flights")
@Transactional
public class FlightsWebService {
	
	@PersistenceContext(unitName="airline")
	EntityManager em;
	
	@EJB
	FlightService fs;
	
	@Context
	UriInfo fUriInfo;
	
	
	public FlightsWebService() {
		
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<FLIGHT> getFlights(){
		List<FLIGHT> fList = fs.getFlights();
		
		return fList;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{flight_id}")
	public FLIGHT getFlight(@PathParam("flight_id") Integer flightId) {
		
		FLIGHT f = fs.getFlight(flightId);
		
		if(f == null) {
			throw new NotFoundException("The flight with an id of "+ flightId + "was not found");
		}
		
		return f;
	}
	
	@DELETE
	@Path("{flight_id}")
	public Response deleteFlight(@PathParam("flight_id") Integer flightId) {
		
		FLIGHT flightToRemove = em.find(FLIGHT.class, flightId);
		
		if(flightToRemove == null) {
			throw new NotFoundException("The flight with an id of "+ flightId + "was not found");
		}
		
		em.remove(flightToRemove);
		
		return Response.noContent().build();
	}
	
	
}
