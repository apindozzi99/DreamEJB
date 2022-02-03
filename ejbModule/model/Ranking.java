package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ranking database table.
 * 
 */
@Entity
@NamedQuery(name ="Ranking.findAll", query="SELECT r FROM Ranking r")
@NamedQuery(name = "Ranking.getRanking", query= "SELECT r FROM Ranking r where r.product = ?1 order by r.score DESC")
@NamedQuery(name = "Ranking.getRankingDesc", query= "SELECT r FROM Ranking r where r.product = ?1 order by r.score ASC")
@NamedQuery(name = "Ranking.getProductList", query = "SELECT DISTINCT r.product FROM Ranking r")
public class Ranking implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idproduction;

	private String product;

	private float score;

	//bi-directional one-to-one association to Production
	@OneToOne
	@PrimaryKeyJoinColumn(name="idproduction")
	private Production production;

	public Ranking() {
	}

	public int getIdproduction() {
		return this.idproduction;
	}

	public void setIdproduction(int idproduction) {
		this.idproduction = idproduction;
	}

	public String getProduct() {
		return this.product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public float getScore() {
		return this.score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public Production getProduction() {
		return this.production;
	}

	public void setProduction(Production production) {
		this.production = production;
	}

}