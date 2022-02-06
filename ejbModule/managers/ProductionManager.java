package managers;

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
public class ProductionManager {


	@PersistenceContext(unitName = "DreamEJB")
	private EntityManager em;
	
	public void setEm(EntityManager em) {
		this.em=em;
	}
	
	public List<Production> getAllProduction(Field field) {
		List<Production> pList = null;
		pList = em.createNamedQuery("Production.getAll", Production.class).setParameter(1, field).getResultList();
		if (pList.isEmpty())
			pList=null;
		return pList; 
	}

	public Production getProduction(int idProduction) throws CredentialsException, NonUniqueResultException {
    	List<Production> pList = null;
		try {
			pList = em.createNamedQuery("Production.getProduction", Production.class).setParameter(1, idProduction).getResultList();
			} catch (PersistenceException e) {
			throw new CredentialsException("Could not find the production");
		}
		if (pList.isEmpty())
			return null;
		else if (pList.size() == 1)
			return pList.get(0);
		throw new NonUniqueResultException("More than one production with same idproduction");
    }
}