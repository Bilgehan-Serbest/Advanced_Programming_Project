package com.airline.models;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Airplane
 *
 */
@Entity

public class AIRPLANE implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public AIRPLANE() {
		super();
	}   
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer ID;
	
	private String PLANEMAKE;
	
	private String MODELNAME;
	
	private Integer SEATINGCAPACITY;
	
	@OneToOne(mappedBy="AIRPLANEDETAIL")
	private FLIGHT FLIGHT;

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public String getPLANEMAKE() {
		return PLANEMAKE;
	}

	public void setPLANEMAKE(String pLANEMAKE) {
		PLANEMAKE = pLANEMAKE;
	}

	public String getMODELNAME() {
		return MODELNAME;
	}

	public void setMODELNAME(String mODELNAME) {
		MODELNAME = mODELNAME;
	}

	public Integer getSEATINGCAPACITY() {
		return SEATINGCAPACITY;
	}

	public void setSEATINGCAPACITY(Integer sEATINGCAPACITY) {
		SEATINGCAPACITY = sEATINGCAPACITY;
	}

	@Override
	public String toString() {
		return "Airplane [ID=" + ID + ", PLANEMAKE=" + PLANEMAKE + ", MODELNAME=" + MODELNAME + ", SEATINGCAPACITY="
				+ SEATINGCAPACITY + "]";
	}
	
	
	
}
