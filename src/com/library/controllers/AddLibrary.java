package com.library.controllers;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.library.models.LIBRARY;
import com.library.service.LibraryService;

/**
 * Servlet implementation class AddFlight
 */
@WebServlet("/AddLibrary")
public class AddLibrary extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	@EJB
	LibraryService ls;
    public AddLibrary() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		String name = request.getParameter("libraryName");
		
		String address = request.getParameter("libraryAddress");
		
		System.out.println(name);
		System.out.println(address);
		
		LIBRARY l = new LIBRARY();
		
		l.setNAME(name);
		
		l.setADDRESS(address);
		
		ls.addLibrary(l);
		
		response.sendRedirect("Libraries");
	}

}
