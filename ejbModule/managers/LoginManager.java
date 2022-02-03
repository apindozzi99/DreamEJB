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
public class LoginManager {

	@PersistenceContext(unitName = "DreamEJB")
	private EntityManager em;
	
	public Usr checkCredentials(String email, String password) throws CredentialsException, NonUniqueResultException {
    	/*Customer c = em.createNamedQuery("Customer.checkCredentials", Customer.class).setParameter(1, username).setParameter(2, password).getSingleResult();
    	return c;*/
    	List<Usr> cList = null;
		try {
			cList = em.createNamedQuery("Usr.checkCredentials", Usr.class).setParameter(1, email).setParameter(2, password).getResultList();
			System.out.println("ciao");
			} catch (PersistenceException e) {
			throw new CredentialsException("Could not verify credentals");
		}
		if (cList.isEmpty())
			return null;
		else if (cList.size() == 1)
			return cList.get(0);
		throw new NonUniqueResultException("More than one user registered with same credentials");
    }
}
