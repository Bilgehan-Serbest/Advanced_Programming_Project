package com.library.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.library.models.LIBRARY;
import com.library.models.MEMBER;
import com.library.service.LibraryService;
import com.library.service.MemberService;

/**
 * Servlet implementation class Libraries
 */
@WebServlet("/Libraries")
public class Libraries extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	@EJB
	LibraryService ls;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Libraries() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<LIBRARY> lryList = (List<LIBRARY>) ls.getLibraries();
		
		request.setAttribute("library_list", lryList);
		
		PrintWriter out = response.getWriter();
		
		out.println("Here the library list will be displayed");
		
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/views/library_list.jsp");
		
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
