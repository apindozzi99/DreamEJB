package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the help database table.
 * 
 */
@Entity
@NamedQuery(name="Help.findAll", query="SELECT h FROM Help h")
public class Help implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private HelpPK id;

	private String problem;

	//bi-directional many-to-one association to Farmer
	@ManyToOne
	@JoinColumn(name="email")
	private Farmer farmer;

	//bi-directional many-to-one association to Field
	@ManyToOne
	@JoinColumn(name="location")
	private Field field;

	public Help() {
	}

	public HelpPK getId() {
		return this.id;
	}

	public void setId(HelpPK id) {
		this.id = id;
	}

	public String getProblem() {
		return this.problem;
	}

	public void setProblem(String problem) {
		this.problem = problem;
	}

	public Farmer getFarmer() {
		return this.farmer;
	}

	public void setFarmer(Farmer farmer) {
		this.farmer = farmer;
	}

	public Field getField() {
		return this.field;
	}

	public void setField(Field field) {
		this.field = field;
	}

}