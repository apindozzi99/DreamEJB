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
public class PolicyMakerManager {
	
	@PersistenceContext(unitName = "DreamEJB")
	private EntityManager em;
	
	public void setEm(EntityManager em) {
		this.em=em;
	}
	
	public Policymaker getPolicyMaker(Usr usr) throws CredentialsException {
		List<Policymaker> pList = null;
		try {
			pList = em.createNamedQuery("Policymaker.IsPolicy", Policymaker.class).setParameter(1, usr).getResultList();
			} catch (PersistenceException e) {
			throw new CredentialsException("Could not find the policyMaker");
		}
		if (pList.isEmpty())
			return null;
		else if (pList.size() == 1)
			return pList.get(0);
		throw new NonUniqueResultException("More than one field with same location");
		
	}
}
