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
	public List<Production> getAllProduction(Field field) {
		List<Production> pList = null;
		pList = em.createNamedQuery("Production.getAll", Production.class).setParameter(1, field).getResultList();
		return pList; 
	}
	
}