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
public class FieldManager {

	@PersistenceContext(unitName = "DreamEJB")
	private EntityManager em;
	
	public List<Field> getAllFields() {
		List<Field> fList = null;
		fList = em.createNamedQuery("Field.findAll", Field.class).getResultList();
		return fList; 
	}
	
	public Field getField(float location) throws CredentialsException, NonUniqueResultException {
    	List<Field> fList = null;
		try {
			fList = em.createNamedQuery("Field.getField", Field.class).setParameter(1, location).getResultList();
			} catch (PersistenceException e) {
			throw new CredentialsException("Could not find the field");
		}
		if (fList.isEmpty())
			return null;
		else if (fList.size() == 1)
			return fList.get(0);
		throw new NonUniqueResultException("More than one field with same location");
    }
	
}