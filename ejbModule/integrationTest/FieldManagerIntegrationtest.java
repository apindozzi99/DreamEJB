package integrationTest;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import exceptions.CredentialsException;
import managers.*;
import model.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/*
 * 
 * Class which tests the FieldManager and the consistency with the model
 * Test the connections between the other Manager
 *
 */

public class FieldManagerIntegrationtest {
	
	private static final float VALID_LOCATION = 00000;
	private static final float VALID_SIZE = 1;
	private static final String VALID_EMAIL = "1";
	private static final float INVALID_LOCATION = 11111;
	private Farmer VALID_FARMER;
	
	 private static EntityManagerFactory emf;
	    private static EntityManager em;

	    private FieldManager manager;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		 emf = Persistence.createEntityManagerFactory("DreamEJB-testing");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		 if (emf != null) {
	            emf.close();
	        }
	}

	@Before
	public void setUp() throws Exception {
		 em = emf.createEntityManager();

	        manager = new FieldManager();
	        manager.setEm(em);
	        createTestData();
	}

	@After
	public void tearDown() throws Exception {
		 if (em != null) {
	            removeTestData();
	            em.close();
	        }
	}
	
	private void createTestData() {
        Field field = new Field();
        em.getTransaction().begin();
        VALID_FARMER=em.find(Farmer.class, VALID_EMAIL);
        em.getTransaction().commit();
        
        field.setLocation(VALID_LOCATION);
        field.setSize(VALID_SIZE);
        field.setFarmer(VALID_FARMER);

        em.getTransaction().begin();
        em.persist(field);
        em.flush();
        em.getTransaction().commit();
    }

    private void removeTestData() {
        em.getTransaction().begin();
        Field field = em.find(Field.class, VALID_LOCATION);
        if (field != null) {
            em.remove(field);
        }
        em.getTransaction().commit();
    }

	@Test
	public void getAllFieldsTest() {
		List<Field> fields = manager.getAllFields();
		assertNotNull(fields);
	}
	
	@Test
	public void getFieldTestCorrectLocation() throws NonUniqueResultException, CredentialsException {
		Field field = manager.getField(VALID_LOCATION);
		assertNotNull(field);
	}
	
	@Test
	public void getFieldTestWrongLocation() throws NonUniqueResultException, CredentialsException {
		Field field = manager.getField(INVALID_LOCATION);
		assertNull(field);
	}

}
