package com.library.webservices.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.library.models.LIBRARY;
import com.library.service.LibraryService;

@Path("/libraries")
@Transactional
public class LibraryWebService {	
	@PersistenceContext(unitName="librarymanagement")
	EntityManager em;
	
	@EJB
	LibraryService ls;
	
	@Context
	UriInfo fUriInfo;
	
	
	public LibraryWebService() {
		
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<LIBRARY> getLibraries(){
		List<LIBRARY> lryList = ls.getLibraries();
		
		return lryList;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{library_id}")
	public LIBRARY getLibrary(@PathParam("library_id") Integer libraryId) {
		
		LIBRARY lry = ls.getLibrary(libraryId);
		
		if(lry == null) {
			throw new NotFoundException("The Library with an id of "+ libraryId + "was not found");
		}
		
		return lry;
	}
	
	@DELETE
	@Path("{library_id}")
	public Response deleteLibrary(@PathParam("library_id") Integer libraryId) {
		
		LIBRARY libraryToRemove = em.find(LIBRARY.class, libraryId);
		
		if(libraryToRemove == null) {
			throw new NotFoundException("The library with an id of "+ libraryId + "was not found");
		}
		
		em.remove(libraryToRemove);
		
		return Response.noContent().build();
	}
	
	
}

