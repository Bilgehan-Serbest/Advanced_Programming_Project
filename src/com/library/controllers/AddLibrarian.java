package com.library.controllers;


import java.io.IOException;
import java.util.Calendar;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.library.models.Gender;
import com.library.models.LIBRARIAN;
import com.library.models.MEMBER;
import com.library.service.LibraryService;

/**
 * Servlet implementation class AddPilot
 */
@WebServlet("/AddLibrarianToLibrary")
public class AddLibrarian extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	LibraryService ls;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddLibrarian() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		Librarian l = new Librarian();
//		l.setFirstName("Griselda");
//		l.setLastName("Cavendish");
//		
//		ls.addLibrarianToLibrary(l);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		String fName = request.getParameter("librarianFirstName");
		String lName = request.getParameter("librarianLastName");
		String lId = request.getParameter("librarianLibraryId");
		
		LIBRARIAN l = new LIBRARIAN();
		
		l.setFIRSTNAME(fName);
		l.setLASTNAME(lName);
		
		ls.addLibrarianToLibrary(l, lId);

		//p.setFLIGHTCLASS(FlightClass.Coach);
		
		System.out.println(l);
		
		response.sendRedirect("Libraries");
	}

}
