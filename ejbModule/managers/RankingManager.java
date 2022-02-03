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

@Stateless
@LocalBean
public class RankingManager {

	@PersistenceContext(unitName = "DreamEJB")
	private EntityManager em;
	public List<Ranking> getRanking(String product) {
		List<Ranking> rList = null;
		rList = em.createNamedQuery("Ranking.getRanking", Ranking.class).setParameter(1, product).getResultList();
		return rList; 
	}
	public List<String> getProductList(){
		List <String> pList = null;
		pList = em.createNamedQuery("Ranking.getProductList", String.class).getResultList();
		return pList;
	}
	
	public List<Ranking> getProductListDesc(String product){
		List <Ranking> dList = null;
		dList = em.createNamedQuery("Ranking.getRankingDesc", Ranking.class).setParameter(1, product).getResultList();
		return dList;
	}
	
	public boolean checkIfAlreadyExists(Production production) {
		List<Incentive> cList=null;
		cList = em.createNamedQuery("Incentive.checkUnique", Incentive.class).setParameter(1, production).getResultList();
		if (cList.isEmpty())
			return false;
		else
			return true;
	}
	
	public boolean addNotification(Production production) {
		Incentive inc = new Incentive();
		inc.setAmount(1000);
		Date todayDate = new Date();
		inc.setDate(todayDate);
		inc.setProduction(production);
		em.persist(inc);
		return true;
	}
	
	public boolean sendHelp(Production production) {
		return false;
	}
}