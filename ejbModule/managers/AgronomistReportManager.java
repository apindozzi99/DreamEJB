package managers;
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

/*
 * 
 * Class dealing with productions
 * It connects to the model to make requests to the database 
 * 
 */

public class AgronomistReportManager {
	
	@PersistenceContext(unitName = "DreamEJB")
	private EntityManager em;
	
	public void setEm(EntityManager em) {
		this.em=em;
	}
	/*
	 * 
	 * Method to get  all the existing report
	 * Return a list of AgronomistReoport 
	 * 
	 */
	
	public List<Agronomistreport> getAllAgronomistReport(){
		List<Agronomistreport> aList = null;
		aList = em.createNamedQuery("Agronomistreport.findAll", Agronomistreport.class).getResultList();
		return aList;
	}
	
	/*
	 * 
	 * Method to get an AgronomistReoport based on its id
	 * Return an AgronomistReoport or null if it's null
	 * 
	 * 
	 */
	public Agronomistreport getDetail(int id) throws CredentialsException, NonUniqueResultException {
		List<Agronomistreport> aList = null;
		try {
			aList = em.createNamedQuery("Agronomistreport.getReport", Agronomistreport.class).setParameter(1, id).getResultList();
			} catch (PersistenceException e) {
			throw new CredentialsException("Could not find the report");
		}
		if (aList.isEmpty())
			return null;
		else if (aList.size() == 1)
			return aList.get(0);
		throw new NonUniqueResultException("More than one report with same id");
		
    }
	
	/*
	 * 
	 * Method to get the score of a production before the arrivals of an agronomist
	 * The inputs are the Field and the Date to find all the productions before the arrivals of the agronomist
	 * The output is a List of production or null if the list is empty
	 * 
	 */
	
	public List<Production> getProductionBefore(Field field,Date date){
		List<Production> pList = null;
		pList = em.createNamedQuery("Production.getProductionBefore", Production.class).setParameter(1,field).setParameter(2, date).getResultList();
		if (pList.isEmpty())
			pList=null;
		return pList;
	}
	
	/*
	 * 
	 * Method to get the score of a production after the arrivals of an agronomist
	 * The inputs are the Field and the Date to find all the productions after the arrivals of the agronomist
	 * The output is a List of production or null if the list is empty
	 * 
	 */
	public List<Production> getProductionAfter(Field field,Date date){
		List<Production> pList = null;
		pList = em.createNamedQuery("Production.getProductionAfter", Production.class).setParameter(1,field).setParameter(2, date).getResultList();
		if (pList.isEmpty())
			pList=null;
		return pList;
	}

}
