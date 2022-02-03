package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the suggestionproduct database table.
 * 
 */
@Entity
@NamedQuery(name="Suggestionproduct.findAll", query="SELECT s FROM Suggestionproduct s")
public class Suggestionproduct implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idsuggestionproduct;

	private String product;

	//bi-directional many-to-one association to Field
	@ManyToOne
	@JoinColumn(name="position")
	private Field field;

	public Suggestionproduct() {
	}

	public int getIdsuggestionproduct() {
		return this.idsuggestionproduct;
	}

	public void setIdsuggestionproduct(int idsuggestionproduct) {
		this.idsuggestionproduct = idsuggestionproduct;
	}

	public String getProduct() {
		return this.product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public Field getField() {
		return this.field;
	}

	public void setField(Field field) {
		this.field = field;
	}

}