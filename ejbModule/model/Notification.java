package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the notification database table.
 * 
 */
@Entity
@NamedQuery(name="Notification.findAll", query="SELECT n FROM Notification n")
public class Notification implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idnotification;

	private String text;

	//bi-directional many-to-one association to Farmer
	@ManyToOne
	@JoinColumn(name="receiver")
	private Farmer farmer;

	//bi-directional many-to-one association to Policymaker
	@ManyToOne
	@JoinColumn(name="sender")
	private Policymaker policymaker;

	//bi-directional many-to-one association to Usr
	@ManyToOne
	@JoinColumn(name="receiver")
	private Usr usr;

	public Notification() {
	}

	public int getIdnotification() {
		return this.idnotification;
	}

	public void setIdnotification(int idnotification) {
		this.idnotification = idnotification;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Farmer getFarmer() {
		return this.farmer;
	}

	public void setFarmer(Farmer farmer) {
		this.farmer = farmer;
	}

	public Policymaker getPolicymaker() {
		return this.policymaker;
	}

	public void setPolicymaker(Policymaker policymaker) {
		this.policymaker = policymaker;
	}

	public Usr getUsr() {
		return this.usr;
	}

	public void setUsr(Usr usr) {
		this.usr = usr;
	}

}