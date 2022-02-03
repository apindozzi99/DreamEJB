package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the policymaker database table.
 * 
 */
@Entity
@NamedQuery(name="Policymaker.findAll", query="SELECT p FROM Policymaker p")
public class Policymaker implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int secretCode;

	//bi-directional many-to-one association to Notification
	@OneToMany(mappedBy="policymaker")
	private List<Notification> notifications;

	//bi-directional many-to-one association to Usr
	@ManyToOne
	@JoinColumn(name="email")
	private Usr usr;

	public Policymaker() {
	}

	public int getSecretCode() {
		return this.secretCode;
	}

	public void setSecretCode(int secretCode) {
		this.secretCode = secretCode;
	}

	public List<Notification> getNotifications() {
		return this.notifications;
	}

	public void setNotifications(List<Notification> notifications) {
		this.notifications = notifications;
	}

	public Notification addNotification(Notification notification) {
		getNotifications().add(notification);
		notification.setPolicymaker(this);

		return notification;
	}

	public Notification removeNotification(Notification notification) {
		getNotifications().remove(notification);
		notification.setPolicymaker(null);

		return notification;
	}

	public Usr getUsr() {
		return this.usr;
	}

	public void setUsr(Usr usr) {
		this.usr = usr;
	}

}