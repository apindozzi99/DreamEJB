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
public class AgronomistReportManager {
	
	@PersistenceContext(unitName = "DreamEJB")
	private EntityManager em;
	
	public void setEm(EntityManager em) {
		this.em=em;
	}
	
	public List<Agronomistreport> getAllAgronomistReport(){
		List<Agronomistreport> aList = null;
		aList = em.createNamedQuery("Agronomistreport.findAll", Agronomistreport.class).getResultList();
		return aList;
	}
	
	
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
	
	public List<Production> getProductionBefore(Field field,Date date){
		List<Production> pList = null;
		pList = em.createNamedQuery("Production.getProductionBefore", Production.class).setParameter(1,field).setParameter(2, date).getResultList();
		if (pList.isEmpty())
			pList=null;
		return pList;
	}
	
	public List<Production> getProductionAfter(Field field,Date date){
		List<Production> pList = null;
		pList = em.createNamedQuery("Production.getProductionAfter", Production.class).setParameter(1,field).setParameter(2, date).getResultList();
		if (pList.isEmpty())
			pList=null;
		return pList;
	}

}
