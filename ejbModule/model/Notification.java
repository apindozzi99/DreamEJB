package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


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

	//bi-directional many-to-many association to Farmer
	@ManyToMany
	@JoinTable(
		name="farmernotification"
		, joinColumns={
			@JoinColumn(name="idNotication")
			}
		, inverseJoinColumns={
			@JoinColumn(name="emailFarmer")
			}
		)
	private List<Farmer> farmers;

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

	public List<Farmer> getFarmers() {
		return this.farmers;
	}

	public void setFarmers(List<Farmer> farmers) {
		this.farmers = farmers;
	}

	public Usr getUsr() {
		return this.usr;
	}

	public void setUsr(Usr usr) {
		this.usr = usr;
	}

}