package com.airline.webservices.rest;

import java.net.URI;
import java.util.List;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;

import com.airline.models.FLIGHT;
import com.airline.models.PASSENGER;
import com.airline.service.PassengerService;

@Path("/passengers")
public class PassengersWebService {
	
	@PersistenceContext(unitName="airline")
	EntityManager em;
	
	@EJB
	PassengerService ps;
	
	@Context
	UriInfo pUriInfo;	
	
	public PassengersWebService() {
		
	}
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<PASSENGER> getPassengers(){
		List<PASSENGER> pList = ps.getPassengers();
		
		return pList;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	@Path("{passenger_id}")
	public PASSENGER getPassenger(@PathParam("passenger_id") Integer passengerId) {
		PASSENGER p = ps.getPassenger(passengerId);
		
		if(p==null) {
			throw new NotFoundException("The passenger with an id  of "+ passengerId + " was not found");
		}		
		return p;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addPassenger(PASSENGER p) {
		
		p = ps.addPassenger(p);
		
		UriBuilder pUriBuilder = pUriInfo.getAbsolutePathBuilder();
		
		URI pUri = pUriBuilder.path(String.valueOf(p.getID())).build();
		
		return Response.created(pUri).build();
	}
	
	@PUT
	@Path("/edit/{pId}")
	@Consumes("application/json")
	public Response updatePassenger(@PathParam("pId") Integer passengerId, PASSENGER pUpdated) {
		
		pUpdated = ps.updatePassenger(passengerId, pUpdated);
		
		if(pUpdated == null) {
			throw new NotFoundException("The passenger with an id of "+ passengerId + " was not found.");
		}
		
		return Response.ok(pUpdated).build();
	}
	
	@PUT
	@Path("/edit2/{pId}")
	@Consumes("application/json")
	public Response updatePassenger2(@PathParam("pId") Integer passengerId, PASSENGER pUpdated) {
		
		pUpdated = ps.updatePassenger2(passengerId, pUpdated);
		
		if(pUpdated == null) {
			throw new NotFoundException("The passenger with an id of "+ passengerId + " was not found.");
		}
		
		return Response.ok(pUpdated).build();
	}
	
}
