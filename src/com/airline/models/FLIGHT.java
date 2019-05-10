package com.airline.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Flight
 *
 */
@NamedQuery(name = "FLIGHT.findById", query="SELECT f FROM FLIGHT f WHERE f.ID =:ID")
@Entity
public class FLIGHT implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public FLIGHT() {
		super();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer ID;
	
	@Enumerated(EnumType.STRING)
	private FlightDestinations FLIGHTORIGIN;
	
	@Enumerated(EnumType.STRING)
	private FlightDestinations FLIGHTDESTINATION;
	
	private Integer PRICE;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date FLIGHTTIME;
	
	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
	@JoinColumn(name="airplane_fk")
	private AIRPLANE AIRPLANEDETAIL;
	
	@OneToMany(mappedBy="FLIGHTFORPILOT", cascade= {CascadeType.REMOVE})
	private List<PILOT> pilots;
	
	@ManyToMany
	@JoinTable(name="f_p_join", joinColumns=@JoinColumn(name = "flight_fk"), inverseJoinColumns=@JoinColumn(name="passenger_fk"))
	private List<PASSENGER> passengers;

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public FlightDestinations getFLIGHTORIGIN() {
		return FLIGHTORIGIN;
	}

	public void setFLIGHTORIGIN(FlightDestinations fLIGHTORIGIN) {
		FLIGHTORIGIN = fLIGHTORIGIN;
	}

	public FlightDestinations getFLIGHTDESTINATION() {
		return FLIGHTDESTINATION;
	}

	public void setFLIGHTDESTINATION(FlightDestinations fLIGHTDESTINATION) {
		FLIGHTDESTINATION = fLIGHTDESTINATION;
	}

	public Integer getPRICE() {
		return PRICE;
	}

	public void setPRICE(Integer pRICE) {
		PRICE = pRICE;
	}

	public Date getFLIGHTTIME() {
		return FLIGHTTIME;
	}

	public void setFLIGHTTIME(Date fLIGHTTIME) {
		FLIGHTTIME = fLIGHTTIME;
	}
	
	

	public AIRPLANE getAIRPLANEDETAIL() {
		return AIRPLANEDETAIL;
	}

	public void setAIRPLANEDETAIL(AIRPLANE aIRPLANEDETAIL) {
		AIRPLANEDETAIL = aIRPLANEDETAIL;
	}

	
	public List<PILOT> getPilots() {
		return pilots;
	}

	public void setPilots(List<PILOT> pilots) {
		this.pilots = pilots;
	}
	

	public List<PASSENGER> getPassengers() {
		return passengers;
	}

	public void setPassengers(List<PASSENGER> passengers) {
		this.passengers = passengers;
	}

	@Override
	public String toString() {
		return "FLIGHT [ID=" + ID + ", FLIGHTORIGIN=" + FLIGHTORIGIN + ", FLIGHTDESTINATION=" + FLIGHTDESTINATION
				+ ", PRICE=" + PRICE + ", FLIGHTTIME=" + FLIGHTTIME + ", AIRPLANEDETAIL=" + AIRPLANEDETAIL + ", pilots="
				+ pilots + "]";
	}


	
	
}
