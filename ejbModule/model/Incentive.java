package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the incentive database table.
 * 
 */
@Entity
@NamedQuery(name="Incentive.findAll", query="SELECT i FROM Incentive i")
public class Incentive implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idincentive;

	private float amount;

	@Temporal(TemporalType.DATE)
	private Date date;

	//bi-directional many-to-one association to Production
	@ManyToOne
	@JoinColumn(name="idproduction")
	private Production production;

	public Incentive() {
	}

	public int getIdincentive() {
		return this.idincentive;
	}

	public void setIdincentive(int idincentive) {
		this.idincentive = idincentive;
	}

	public float getAmount() {
		return this.amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Production getProduction() {
		return this.production;
	}

	public void setProduction(Production production) {
		this.production = production;
	}

}