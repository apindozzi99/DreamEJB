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

import model.Usr;


@Stateless
@LocalBean
public class RegistrationManager {
	
	@PersistenceContext(unitName = "DreamEJB")
	private EntityManager em;
	 /*EntityManagerFactory emf = 
              Persistence.createEntityManagerFactory("TelcoServiceDB");
      EntityManager em = emf.createEntityManager();*/
      

    
    public Usr getUser(String email) {
    	Usr u = em.find(Usr.class, email);
    	return u;
    }
    
    
}

