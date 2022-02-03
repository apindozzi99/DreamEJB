package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the field database table.
 * 
 */
@Entity
@NamedQuery(name="Field.findAll", query="SELECT f FROM Field f")
@NamedQuery(name="Field.getField", query = "SELECT f FROM Field f WHERE f.location = ?1")

public class Field implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private float location;

	private String details;

	private float size;

	//bi-directional many-to-one association to Farmer
	@ManyToOne
	@JoinColumn(name="farmeremail")
	private Farmer farmer;

	//bi-directional many-to-many association to Fertilizer
	@ManyToMany
	@JoinTable(
		name="suggestionfertilizer"
		, joinColumns={
			@JoinColumn(name="position")
			}
		, inverseJoinColumns={
			@JoinColumn(name="fertilizer")
			}
		)
	private List<Fertilizer> fertilizers;

	//bi-directional many-to-one association to Help
	@OneToMany(mappedBy="field")
	private List<Help> helps;

	//bi-directional many-to-one association to Production
	@OneToMany(mappedBy="field")
	private List<Production> productions;

	//bi-directional many-to-one association to Suggestionproduct
	@OneToMany(mappedBy="field")
	private List<Suggestionproduct> suggestionproducts;

	public Field() {
	}

	public float getLocation() {
		return this.location;
	}

	public void setLocation(float location) {
		this.location = location;
	}

	public String getDetails() {
		return this.details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public float getSize() {
		return this.size;
	}

	public void setSize(float size) {
		this.size = size;
	}

	public Farmer getFarmer() {
		return this.farmer;
	}

	public void setFarmer(Farmer farmer) {
		this.farmer = farmer;
	}

	public List<Fertilizer> getFertilizers() {
		return this.fertilizers;
	}

	public void setFertilizers(List<Fertilizer> fertilizers) {
		this.fertilizers = fertilizers;
	}

	public List<Help> getHelps() {
		return this.helps;
	}

	public void setHelps(List<Help> helps) {
		this.helps = helps;
	}

	public Help addHelp(Help help) {
		getHelps().add(help);
		help.setField(this);

		return help;
	}

	public Help removeHelp(Help help) {
		getHelps().remove(help);
		help.setField(null);

		return help;
	}

	public List<Production> getProductions() {
		return this.productions;
	}

	public void setProductions(List<Production> productions) {
		this.productions = productions;
	}

	public Production addProduction(Production production) {
		getProductions().add(production);
		production.setField(this);

		return production;
	}

	public Production removeProduction(Production production) {
		getProductions().remove(production);
		production.setField(null);

		return production;
	}

	public List<Suggestionproduct> getSuggestionproducts() {
		return this.suggestionproducts;
	}

	public void setSuggestionproducts(List<Suggestionproduct> suggestionproducts) {
		this.suggestionproducts = suggestionproducts;
	}

	public Suggestionproduct addSuggestionproduct(Suggestionproduct suggestionproduct) {
		getSuggestionproducts().add(suggestionproduct);
		suggestionproduct.setField(this);

		return suggestionproduct;
	}

	public Suggestionproduct removeSuggestionproduct(Suggestionproduct suggestionproduct) {
		getSuggestionproducts().remove(suggestionproduct);
		suggestionproduct.setField(null);

		return suggestionproduct;
	}

}