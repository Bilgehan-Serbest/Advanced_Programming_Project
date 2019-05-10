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

import com.library.models.Book;
import com.library.models.Library;
import com.library.service.LibraryService;


/**
 * Servlet implementation class AddPassenger
 */
@WebServlet("/AddBook")
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

		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String publisher = request.getParameter("publisher");
		String libraryId = request.getParameter("library_id");
		Book b = new Book();
		
		b.setTitle(title);
		b.setAuthor(author);
		b.setPublisher(publisher);
		
		ls.addBookToLibrary(b.getId().toString(), libraryId);
		
		System.out.println(b);
			
		
		response.sendRedirect("Books");
	}

}
