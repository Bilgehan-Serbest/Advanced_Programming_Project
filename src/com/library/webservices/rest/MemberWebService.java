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
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import com.library.models.LIBRARY;
import com.library.models.MEMBER;
import com.library.service.MemberService;

import javax.ws.rs.PathParam;

@Path("/members")
@Transactional
public class MemberWebService {
	
	@PersistenceContext(unitName="librarymanagement")
	EntityManager em;
	
	@EJB
	MemberService ms;
	
	@Context
	UriInfo mUriInfo;	
	
	public MemberWebService() {
		
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<MEMBER> getMembers(){
		List<MEMBER> pList = ms.getMembers();
		
		return pList;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{member_id}")
	public MEMBER getMember(@PathParam("member_id") Integer memberId) {
		MEMBER m = ms.getMember(memberId);
		
		if(m==null) {
			throw new NotFoundException("The member with an id  of "+ memberId + " was not found");
		}		
		return m;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addMember(MEMBER m) {
		
		m = ms.addMember(m);
		
		UriBuilder mUriBuilder = mUriInfo.getAbsolutePathBuilder();
		
		URI mUri = mUriBuilder.path(String.valueOf(m.getID())).build();
		
		return Response.created(mUri).build();
	}
	
	
	@PUT
	@Path("/edit/{mId}")
	@Consumes("application/json")
	public Response updateMember(@PathParam("mId") Integer memberId, MEMBER pUpdated) {
		
		pUpdated = ms.updateMember(memberId, pUpdated);
		
		if(pUpdated == null) {
			throw new NotFoundException("The member with an id of "+ memberId + " was not found.");
		}
		
		return Response.ok(pUpdated).build();
	}
	
	@DELETE
	@Path("{member_id}")
	public Response deleteMember(@PathParam("member_id") Integer memberId) {
		
		MEMBER memberToRemove = em.find(MEMBER.class, memberId);
		
		if(memberToRemove == null) {
			throw new NotFoundException("The member with an id of "+ memberId + "was not found");
		}
		
		em.remove(memberToRemove);
		
		return Response.noContent().build();
	}
	
}
