package com.airline.models;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: PILOT
 *
 */
@NamedQuery(name="PILOT.findById", query="SELECT p FROM PILOT P WHERE p.ID = :ID")
@Entity
public class PILOT implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public PILOT() {
		super();
	}
   
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer ID;
	
	private String FIRSTNAME;
	
	private String LASTNAME;
	
	private Integer PILOTLICENSE;
	
	@Enumerated(EnumType.STRING)
	private PilotRank PILOTRANK;
	
	@ManyToOne
	@JoinColumn(name="flight_fk")
	private FLIGHT FLIGHTFORPILOT;

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

	public Integer getPILOTLICENSE() {
		return PILOTLICENSE;
	}

	public void setPILOTLICENSE(Integer pILOTLICENSE) {
		PILOTLICENSE = pILOTLICENSE;
	}

	public PilotRank getPILOTRANK() {
		return PILOTRANK;
	}

	public void setPILOTRANK(PilotRank pILOTRANK) {
		PILOTRANK = pILOTRANK;
	}

	public FLIGHT getFLIGHTFORPILOT() {
		return FLIGHTFORPILOT;
	}

	public void setFLIGHTFORPILOT(FLIGHT fLIGHTFORPILOT) {
		FLIGHTFORPILOT = fLIGHTFORPILOT;
	}

	@Override
	public String toString() {
		return "PILOT [ID=" + ID + ", FIRSTNAME=" + FIRSTNAME + ", LASTNAME=" + LASTNAME + ", PILOTLICENSE="
				+ PILOTLICENSE + ", PILOTRANK=" + PILOTRANK + ", FLIGHTFORPILOT=" + FLIGHTFORPILOT + "]";
	}
	
	
}
