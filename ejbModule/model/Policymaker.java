package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the policymaker database table.
 * 
 */
@Entity
@NamedQuery(name="Policymaker.findAll", query="SELECT p FROM Policymaker p")
@NamedQuery(name="Policymaker.IsPolicy", query="SELECT p from Policymaker p where p.usr=?1")
public class Policymaker implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int secretCode;

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

	public Usr getUsr() {
		return this.usr;
	}

	public void setUsr(Usr usr) {
		this.usr = usr;
	}

}