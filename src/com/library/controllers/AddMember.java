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

import com.library.models.Gender;
import com.library.models.Member;
import com.library.service.MemberService;
/**
 * Servlet implementation class AddPassenger
 */
@WebServlet("/AddMember")
public class AddMember extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	MemberService ms;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddMember() {
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
		String fName = request.getParameter("memberFirstName");
		String lName = request.getParameter("memberLastName");
		String dob_raw = request.getParameter("memberDob");
		String gender = request.getParameter("gender");		
		
		Member m = new Member();
		
		m.setFirstName(fName);
		m.setLastName(lName);
		
		String[] dobArr = dob_raw.split("\\/");
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, Integer.parseInt(dobArr[2]));
		cal.set(Calendar.MONTH, Integer.parseInt(dobArr[0])-1);
		cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dobArr[1]));
		
		java.util.Date dob = cal.getTime();
				
		m.setDob(dob);
		
		m.setGender(Gender.valueOf(gender));
		
		//p.setFLIGHTCLASS(FlightClass.Coach);
		
		System.out.println(m);
		
		ms.addMember(m);
				
		request.setCharacterEncoding("UTF-8");
		response.sendRedirect("Members");
	}

}