package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the fertilizer database table.
 * 
 */
@Entity
@NamedQuery(name="Fertilizer.findAll", query="SELECT f FROM Fertilizer f")
public class Fertilizer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idfertilizer;

	private String name;

	//bi-directional many-to-many association to Field
	@ManyToMany(mappedBy="fertilizers")
	private List<Field> fields;

	//bi-directional many-to-many association to Production
	@ManyToMany(mappedBy="fertilizers")
	private List<Production> productions;

	public Fertilizer() {
	}

	public int getIdfertilizer() {
		return this.idfertilizer;
	}

	public void setIdfertilizer(int idfertilizer) {
		this.idfertilizer = idfertilizer;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Field> getFields() {
		return this.fields;
	}

	public void setFields(List<Field> fields) {
		this.fields = fields;
	}

	public List<Production> getProductions() {
		return this.productions;
	}

	public void setProductions(List<Production> productions) {
		this.productions = productions;
	}

}