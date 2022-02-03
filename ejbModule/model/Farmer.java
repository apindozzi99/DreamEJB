package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the farmer database table.
 * 
 */
@Entity
@NamedQuery(name="Farmer.findAll", query="SELECT f FROM Farmer f")
public class Farmer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String email;

	@Temporal(TemporalType.DATE)
	private Date birthDate;

	private byte confirmartion;

	private String name;

	//bi-directional many-to-one association to Agronomistreport
	@OneToMany(mappedBy="farmer")
	private List<Agronomistreport> agronomistreports;

	//bi-directional one-to-one association to Usr
	@OneToOne
	@PrimaryKeyJoinColumn(name="email")
	private Usr usr;

	//bi-directional many-to-one association to Field
	@OneToMany(mappedBy="farmer")
	private List<Field> fields;

	//bi-directional many-to-one association to Help
	@OneToMany(mappedBy="farmer")
	private List<Help> helps;

	//bi-directional many-to-one association to Notification
	@OneToMany(mappedBy="farmer")
	private List<Notification> notifications;

	public Farmer() {
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirthDate() {
		return this.birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public byte getConfirmartion() {
		return this.confirmartion;
	}

	public void setConfirmartion(byte confirmartion) {
		this.confirmartion = confirmartion;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Agronomistreport> getAgronomistreports() {
		return this.agronomistreports;
	}

	public void setAgronomistreports(List<Agronomistreport> agronomistreports) {
		this.agronomistreports = agronomistreports;
	}

	public Agronomistreport addAgronomistreport(Agronomistreport agronomistreport) {
		getAgronomistreports().add(agronomistreport);
		agronomistreport.setFarmer(this);

		return agronomistreport;
	}

	public Agronomistreport removeAgronomistreport(Agronomistreport agronomistreport) {
		getAgronomistreports().remove(agronomistreport);
		agronomistreport.setFarmer(null);

		return agronomistreport;
	}

	public Usr getUsr() {
		return this.usr;
	}

	public void setUsr(Usr usr) {
		this.usr = usr;
	}

	public List<Field> getFields() {
		return this.fields;
	}

	public void setFields(List<Field> fields) {
		this.fields = fields;
	}

	public Field addField(Field field) {
		getFields().add(field);
		field.setFarmer(this);

		return field;
	}

	public Field removeField(Field field) {
		getFields().remove(field);
		field.setFarmer(null);

		return field;
	}

	public List<Help> getHelps() {
		return this.helps;
	}

	public void setHelps(List<Help> helps) {
		this.helps = helps;
	}

	public Help addHelp(Help help) {
		getHelps().add(help);
		help.setFarmer(this);

		return help;
	}

	public Help removeHelp(Help help) {
		getHelps().remove(help);
		help.setFarmer(null);

		return help;
	}

	public List<Notification> getNotifications() {
		return this.notifications;
	}

	public void setNotifications(List<Notification> notifications) {
		this.notifications = notifications;
	}

	public Notification addNotification(Notification notification) {
		getNotifications().add(notification);
		notification.setFarmer(this);

		return notification;
	}

	public Notification removeNotification(Notification notification) {
		getNotifications().remove(notification);
		notification.setFarmer(null);

		return notification;
	}

}