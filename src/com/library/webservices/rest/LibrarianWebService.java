package com.library.webservices.rest;

import java.net.URI;
import java.util.List;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import com.library.models.LIBRARIAN;
import com.library.service.LibrarianService;

@Path("/librarians")
@Transactional
public class LibrarianWebService {
	@PersistenceContext(unitName="librarymanagement")
	EntityManager em;
	
	@EJB
	LibrarianService ls;
	
	@Context
	UriInfo lrnUriInfo;	
	
	public LibrarianWebService() {
		
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<LIBRARIAN> getLibrarians(){
		List<LIBRARIAN> lrnList = ls.getLibrarians();
		
		return lrnList;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{librarian_id}")
	public LIBRARIAN getLibrarian(@PathParam("librarian_id") Integer librarianId) {
		LIBRARIAN lrn = ls.getLibrarian(librarianId);
		
		if(lrn==null) {
			throw new NotFoundException("The librarian with an id  of "+ librarianId + " was not found");
		}		
		return lrn;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addLibrarian(LIBRARIAN lrn) {
		
		lrn = ls.addLibrarian(lrn);
		
		UriBuilder lrnUriBuilder = lrnUriInfo.getAbsolutePathBuilder();
		
		URI lrnUri = lrnUriBuilder.path(String.valueOf(lrn.getID())).build();
		
		return Response.created(lrnUri).build();
	}
	
	
	@PUT
	@Path("/edit/{lrnId}")
	@Consumes("application/json")
	public Response updateLibrarian(@PathParam("mId") Integer librarianId, LIBRARIAN lrnUpdated) {
		
		lrnUpdated = ls.updateLibrarian(librarianId, lrnUpdated);
		
		if(lrnUpdated == null) {
			throw new NotFoundException("The Librarian with an id of "+ librarianId + " was not found.");
		}
		
		return Response.ok(lrnUpdated).build();
	}
	
	@DELETE
	@Path("{librarian_id}")
	public Response deleteLibrarian(@PathParam("librarian_id") Integer librarianId) {
		
		LIBRARIAN librarianToRemove = em.find(LIBRARIAN.class, librarianId);
		
		if(librarianToRemove == null) {
			throw new NotFoundException("The Librarian with an id of "+ librarianId + "was not found");
		}
		
		em.remove(librarianToRemove);
		
		return Response.noContent().build();
	}
}
