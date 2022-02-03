package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the production database table.
 * 
 */
@Entity
@NamedQuery(name="Production.findAll", query="SELECT p FROM Production p")
@NamedQuery(name= "Production.getAll", query="SELECT p FROM Production p WHERE p.field= ?1")
@NamedQuery(name= "Production.getProduction", query="SELECT p FROM Production p where p.idproduction = ?1")
public class Production implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idproduction;

	private float avgHumiditySoil;

	private float avgRainFall;

	private float avgWater;

	private float collectedAmount;

	@Temporal(TemporalType.DATE)
	private Date collectedDate;

	private float plantedAmount;

	@Temporal(TemporalType.DATE)
	private Date plantedDate;

	private String product;

	//bi-directional many-to-one association to Incentive
	@OneToMany(mappedBy="production")
	private List<Incentive> incentives;

	//bi-directional many-to-many association to Fertilizer
	@ManyToMany
	@JoinTable(
		name="fertilizerproduction"
		, joinColumns={
			@JoinColumn(name="idProduction")
			}
		, inverseJoinColumns={
			@JoinColumn(name="idfertilizer")
			}
		)
	private List<Fertilizer> fertilizers;

	//bi-directional many-to-one association to Field
	@ManyToOne
	@JoinColumn(name="fieldLocation")
	private Field field;

	//bi-directional one-to-one association to Ranking
	@OneToOne(mappedBy="production")
	private Ranking ranking;

	public Production() {
	}

	public int getIdproduction() {
		return this.idproduction;
	}

	public void setIdproduction(int idproduction) {
		this.idproduction = idproduction;
	}

	public float getAvgHumiditySoil() {
		return this.avgHumiditySoil;
	}

	public void setAvgHumiditySoil(float avgHumiditySoil) {
		this.avgHumiditySoil = avgHumiditySoil;
	}

	public float getAvgRainFall() {
		return this.avgRainFall;
	}

	public void setAvgRainFall(float avgRainFall) {
		this.avgRainFall = avgRainFall;
	}

	public float getAvgWater() {
		return this.avgWater;
	}

	public void setAvgWater(float avgWater) {
		this.avgWater = avgWater;
	}

	public float getCollectedAmount() {
		return this.collectedAmount;
	}

	public void setCollectedAmount(float collectedAmount) {
		this.collectedAmount = collectedAmount;
	}

	public Date getCollectedDate() {
		return this.collectedDate;
	}

	public void setCollectedDate(Date collectedDate) {
		this.collectedDate = collectedDate;
	}

	public float getPlantedAmount() {
		return this.plantedAmount;
	}

	public void setPlantedAmount(float plantedAmount) {
		this.plantedAmount = plantedAmount;
	}

	public Date getPlantedDate() {
		return this.plantedDate;
	}

	public void setPlantedDate(Date plantedDate) {
		this.plantedDate = plantedDate;
	}

	public String getProduct() {
		return this.product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public List<Incentive> getIncentives() {
		return this.incentives;
	}

	public void setIncentives(List<Incentive> incentives) {
		this.incentives = incentives;
	}

	public Incentive addIncentive(Incentive incentive) {
		getIncentives().add(incentive);
		incentive.setProduction(this);

		return incentive;
	}

	public Incentive removeIncentive(Incentive incentive) {
		getIncentives().remove(incentive);
		incentive.setProduction(null);

		return incentive;
	}

	public List<Fertilizer> getFertilizers() {
		return this.fertilizers;
	}

	public void setFertilizers(List<Fertilizer> fertilizers) {
		this.fertilizers = fertilizers;
	}

	public Field getField() {
		return this.field;
	}

	public void setField(Field field) {
		this.field = field;
	}

	public Ranking getRanking() {
		return this.ranking;
	}

	public void setRanking(Ranking ranking) {
		this.ranking = ranking;
	}

}