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

import com.library.models.BOOK;
import com.library.service.BookService;


@Path("/books")
@Transactional
public class BookWebService {

	@PersistenceContext(unitName="librarymanagement")
	EntityManager em;
	
	@EJB
	BookService bs;
	
	@Context
	UriInfo bUriInfo;	
	
	public BookWebService() {
		
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<BOOK> getbooks(){
		List<BOOK> bList = bs.getBooks();
		
		return bList;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{book_id}")
	public BOOK getbook(@PathParam("book_id") Integer bookId) {
		BOOK b = bs.getBook(bookId);
		
		if(b==null) {
			throw new NotFoundException("The book with an id  of "+ bookId + " was not found");
		}		
		return b;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addbook(BOOK b) {
		
		b = bs.addBook(b);
		
		UriBuilder bUriBuilder = bUriInfo.getAbsolutePathBuilder();
		
		URI bUri = bUriBuilder.path(String.valueOf(b.getID())).build();
		
		return Response.created(bUri).build();
	}
	
	
	@PUT
	@Path("/edit/{bId}")
	@Consumes("application/json")
	public Response updatebook(@PathParam("bId") Integer bookId, BOOK bUpdated) {
		
		bUpdated = bs.updateBook(bookId, bUpdated);
		
		if(bUpdated == null) {
			throw new NotFoundException("The book with an id of "+ bookId + " was not found.");
		}
		
		return Response.ok(bUpdated).build();
	}
	
	@DELETE
	@Path("{book_id}")
	public Response deletebook(@PathParam("book_id") Integer bookId) {
		
		BOOK bookToRemove = em.find(BOOK.class, bookId);
		
		if(bookToRemove == null) {
			throw new NotFoundException("The book with an id of "+ bookId + "was not found");
		}
		
		em.remove(bookToRemove);
		
		return Response.noContent().build();
	}
}
