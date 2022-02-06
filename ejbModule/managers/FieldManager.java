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

/*
 * 
 * Class that takes all the fields, or a particular field
 * It connects to the model to make requests to the database 
 * 
 */
@Stateless
@LocalBean
public class FieldManager {

	@PersistenceContext(unitName = "DreamEJB")
	private EntityManager em;
	
	public void setEm(EntityManager em) {
		this.em=em;
	}
	
	/*
	 * 
	 * Method that takes all the existing field
	 * Return a list of fields
	 * 
	 */
	public List<Field> getAllFields() {
		List<Field> fList = null;
		fList = em.createNamedQuery("Field.findAll", Field.class).getResultList();
		return fList; 
	}
	
	/*
	 * 
	 * Method that finds a particular field depending on the id of the field that is location
	 * Return a Field or null if the field doesn't exist
	 * 
	 */
	
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