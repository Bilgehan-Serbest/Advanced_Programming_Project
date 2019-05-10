package com.library.controllers;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.library.service.MemberService;

/**
 * Servlet implementation class LendBookToMember
 */
@WebServlet("/LendBookToMember")
public class LendBookToMember extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	MemberService ms;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LendBookToMember() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberId = request.getParameter("member_id");
		String bookId = request.getParameter("book_id");
		
		ms.lendBookToMember(bookId, memberId);
		
		response.sendRedirect("Members");
	}

}
