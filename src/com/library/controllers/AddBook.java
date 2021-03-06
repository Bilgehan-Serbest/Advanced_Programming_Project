package com.library.controllers;

import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.library.models.BOOK;
import com.library.models.LIBRARY;
import com.library.service.LibraryService;


/**
 * Servlet implementation class AddPassenger
 */
@WebServlet("/CreateBookAndAddToLibrary")
public class AddBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	LibraryService ls;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddBook() {
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

		String title = request.getParameter("bookTitle");
		String author = request.getParameter("bookAuthor");
		String publisher = request.getParameter("bookPublisher");
		String libraryId = request.getParameter("bookLibraryId");
		
		System.out.println(title);
		System.out.println(author);
		System.out.println(publisher);
		System.out.println(libraryId);
		
		BOOK b = new BOOK();
		
		b.setTITLE(title);
		b.setAUTHOR(author);
		b.setPUBLISHER(publisher);		
		
//		System.out.println(b.getTITLE());
//		System.out.println(b.getAUTHOR());
//		System.out.println(b.getPUBLISHER());
//		System.out.println(b.getLIBRARYOFBOOK().getID());
		
		ls.addBookToLibrary(b, libraryId);
		
		System.out.println(b);
					
		response.sendRedirect("Books");
	}

}
