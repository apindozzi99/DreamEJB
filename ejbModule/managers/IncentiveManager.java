package managers;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import org.junit.Test;

import model.*;
import exceptions.CredentialsException;

@Stateless
@LocalBean
public class IncentiveManager {

	@PersistenceContext(unitName = "DreamEJB")
	private EntityManager em;
	
	public void setEm(EntityManager em) {
		this.em=em;
	}
	
	public List<Incentive> getAll(){
		List<Incentive> iList = null;
		iList = em.createNamedQuery("Incentive.findAll", Incentive.class).getResultList();
		return iList;
	}

}
