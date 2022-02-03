package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the usr database table.
 * 
 */
@Entity
@NamedQuery(name="Usr.findAll", query="SELECT u FROM Usr u")
@NamedQuery(name="Usr.checkCredentials", query = "SELECT u FROM Usr u WHERE u.email = ?1 and u.password = ?2")

public class Usr implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String email;

	private String password;

	//bi-directional one-to-one association to Farmer
	@OneToOne(mappedBy="usr")
	private Farmer farmer;

	//bi-directional many-to-one association to Notification
	@OneToMany(mappedBy="usr")
	private List<Notification> notifications;

	//bi-directional many-to-one association to Policymaker
	@OneToMany(mappedBy="usr")
	private List<Policymaker> policymakers;

	public Usr() {
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Farmer getFarmer() {
		return this.farmer;
	}

	public void setFarmer(Farmer farmer) {
		this.farmer = farmer;
	}

	public List<Notification> getNotifications() {
		return this.notifications;
	}

	public void setNotifications(List<Notification> notifications) {
		this.notifications = notifications;
	}

	public Notification addNotification(Notification notification) {
		getNotifications().add(notification);
		notification.setUsr(this);

		return notification;
	}

	public Notification removeNotification(Notification notification) {
		getNotifications().remove(notification);
		notification.setUsr(null);

		return notification;
	}

	public List<Policymaker> getPolicymakers() {
		return this.policymakers;
	}

	public void setPolicymakers(List<Policymaker> policymakers) {
		this.policymakers = policymakers;
	}

	public Policymaker addPolicymaker(Policymaker policymaker) {
		getPolicymakers().add(policymaker);
		policymaker.setUsr(this);

		return policymaker;
	}

	public Policymaker removePolicymaker(Policymaker policymaker) {
		getPolicymakers().remove(policymaker);
		policymaker.setUsr(null);

		return policymaker;
	}

}