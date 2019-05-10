package com.airline.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entity implementation class for Entity: Passenger
 *
 */
@Entity
@XmlRootElement
public class PASSENGER implements Serializable {

	@Transient
	private static final long serialVersionUID = 1L;

	public PASSENGER() {
		super();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer ID;
	
	private String FIRSTNAME;
	
	private String LASTNAME;
	
	@Temporal(TemporalType.DATE)
	private Date DOB;
	
	@Enumerated(EnumType.STRING)
	private Gender GENDER;
	
	@Enumerated(EnumType.STRING)
	private FlightClass FLIGHTCLASS;

	@ManyToMany(mappedBy = "passengers")
	private List<FLIGHT> Flights;

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public String getFIRSTNAME() {
		return FIRSTNAME;
	}

	public void setFIRSTNAME(String fIRSTNAME) {
		FIRSTNAME = fIRSTNAME;
	}

	public String getLASTNAME() {
		return LASTNAME;
	}

	public void setLASTNAME(String lASTNAME) {
		LASTNAME = lASTNAME;
	}

	public Date getDOB() {
		return DOB;
	}

	public void setDOB(Date dOB) {
		DOB = dOB;
	}

	public Gender getGENDER() {
		return GENDER;
	}

	public void setGENDER(Gender gENDER) {
		GENDER = gENDER;
	}

	public FlightClass getFLIGHTCLASS() {
		return FLIGHTCLASS;
	}

	public void setFLIGHTCLASS(FlightClass fLIGHTCLASS) {
		FLIGHTCLASS = fLIGHTCLASS;
	}

	public List<FLIGHT> getFlights() {
		return Flights;
	}

	public void setFlights(List<FLIGHT> flights) {
		Flights = flights;
	}

	@Override
	public String toString() {
		return "PASSENGER [ID=" + ID + ", FIRSTNAME=" + FIRSTNAME + ", LASTNAME=" + LASTNAME + ", DOB=" + DOB
				+ ", GENDER=" + GENDER + ", FLIGHTCLASS=" + FLIGHTCLASS + ", Flights=" + Flights + "]";
	}
	

   
	
}
