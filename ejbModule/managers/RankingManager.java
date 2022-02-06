package managers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import model.*;
import exceptions.CredentialsException;


/*
 * 
 * Class dealing with Ranking and sends a notification if the farmer performed well or sends a request
 * to the Agronomist if the farmer performed bad.
 * It connects to the model to make requests to the database 
 * 
 */

@Stateless
@LocalBean
public class RankingManager {

	@PersistenceContext(unitName = "DreamEJB")
	private EntityManager em;
	
	public void setEm(EntityManager em) {
		this.em=em;
	}
	
	/*
	 * 
	 * Method to get the ranking based on a specific product
	 * Return a List of Ranking or null if the List is empty
	 * 
	 */
	
	public List<Ranking> getRanking(String product) {
		List<Ranking> rList = null;
		rList = em.createNamedQuery("Ranking.getRanking", Ranking.class).setParameter(1, product).getResultList();
		if (rList.isEmpty()) rList=null;
		return rList; 
	}
	
	/*
	 * 
	 * Method to get the ranking in ascending order
	 * Return a List of ranking 
	 * 
	 */
	public List<String> getProductList(){
		List <String> pList = null;
		pList = em.createNamedQuery("Ranking.getProductList", String.class).getResultList();
		return pList;
	}
	
	/*
	 * 
	 * Method to get the ranking in descending order based on a particular product
	 * Return a List of ranking or null if the list is empty
	 * 
	 */
	public List<Ranking> getProductListDesc(String product){
		List <Ranking> dList = null;
		dList = em.createNamedQuery("Ranking.getRankingDesc", Ranking.class).setParameter(1, product).getResultList();
		if (dList.isEmpty()) dList = null;
		return dList;
	}
	
	/*
	 * 
	 * Method to checks if an incentive already exist 
	 * Return False if the specific production doesn't' exist in incentive
	 * Return True if the specific production exists in incentive
	 * 
	 */
	public boolean checkIfAlreadyExists(Production production) {
		List<Incentive> cList=null;
		cList = em.createNamedQuery("Incentive.checkUnique", Incentive.class).setParameter(1, production).getResultList();
		if (cList.isEmpty())
			return false;
		else
			return true;
	}
	
	/*
	 * 
	 * Method to create a notifications and an incentive if the checkIfAlreadyExist is false
	 * Return True if evrything is correct else False
	 * 
	 */
	public boolean addNotification(Production production) {
		if (!this.checkIfAlreadyExists(production))
		{
			Incentive inc = new Incentive();
			inc.setAmount(1000);
			Date todayDate = new Date();
			inc.setDate(todayDate);
			inc.setProduction(production);
			em.persist(inc);
			Notification not = new Notification();
			not.setFarmer(production.getField().getFarmer());
			not.setText(production.getProduct());
			em.persist(not);
			System.out.println(inc.getIdincentive()+" "+inc.getAmount());
			
			return true;
		}
		else return false;
	}
	
	/*
	 * 
	 * Method to creat help to help the farmer who performed bad, based on a production
	 * Return true if evrythig is correct
	 * 
	 */
	
	public boolean sendHelp(Production production) {
		Help help = new Help();
		help.setFarmer(production.getField().getFarmer());
		help.setField(production.getField());
		help.setProblem("Problem with poduction of"+ production.getProduct());
		em.persist(help);
		return true;
	}
}