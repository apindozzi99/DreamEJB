package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the help database table.
 * 
 */
@Embeddable
public class HelpPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private int idhelp;

	@Column(insertable=false, updatable=false)
	private String email;

	public HelpPK() {
	}
	public int getIdhelp() {
		return this.idhelp;
	}
	public void setIdhelp(int idhelp) {
		this.idhelp = idhelp;
	}
	public String getEmail() {
		return this.email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof HelpPK)) {
			return false;
		}
		HelpPK castOther = (HelpPK)other;
		return 
			(this.idhelp == castOther.idhelp)
			&& this.email.equals(castOther.email);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idhelp;
		hash = hash * prime + this.email.hashCode();
		
		return hash;
	}
}