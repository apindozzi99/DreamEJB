package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the agronomistreport database table.
 * 
 */
@Entity
@NamedQuery(name="Agronomistreport.findAll", query="SELECT a FROM Agronomistreport a")
public class Agronomistreport implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idagronomistReport;

	@Temporal(TemporalType.DATE)
	private Date date;

	private String details;

	private String name;

	//bi-directional many-to-one association to Farmer
	@ManyToOne
	@JoinColumn(name="farmeremail")
	private Farmer farmer;

	public Agronomistreport() {
	}

	public int getIdagronomistReport() {
		return this.idagronomistReport;
	}

	public void setIdagronomistReport(int idagronomistReport) {
		this.idagronomistReport = idagronomistReport;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDetails() {
		return this.details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Farmer getFarmer() {
		return this.farmer;
	}

	public void setFarmer(Farmer farmer) {
		this.farmer = farmer;
	}

}