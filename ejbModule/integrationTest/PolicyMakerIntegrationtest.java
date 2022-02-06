package integrationTest;

import static org.junit.Assert.*;

import java.util.Date;
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
 * Class which tests the PolicyMakerManager and the consistency with the model
 * Test the connections between the other Manager
 * 
 */

public class PolicyMakerIntegrationtest {
	private static final String VALID_EMAIL = "test_email";
	private static final int VALID_SECRETCODE= 3;
	
	
	private static final String INVALID_EMAIL= "1";
	private static final float INVALID_SECRETCODE = 4;

    private static EntityManagerFactory emf;
    private static EntityManager em;

    private PolicyMakerManager manager;

    private Policymaker VALID_PC;
	private Usr VALID_USR;
	private Usr INVALID_USR;
	
    @BeforeClass
    public static void setUpBeforeClass() {
        emf = Persistence.createEntityManagerFactory("DreamEJB-testing");
    }

    @AfterClass
    public static void tearDownAfterClass() {
        if (emf != null) {
            emf.close();
        }
    }

    @Before
   public void setUp() {
        em = emf.createEntityManager();

        manager = new PolicyMakerManager();
        manager.setEm(em);
        createTestData();
    }

    @After
    public void tearDown() {
        if (em != null) {
            removeTestData();
            em.close();
        }
    }
    
    private void createTestData() {
        Policymaker pc = new Policymaker();
        em.getTransaction().begin();
        VALID_USR=em.find(Usr.class, VALID_EMAIL);
        em.getTransaction().commit();
        
        pc.setSecretCode(VALID_SECRETCODE);
        pc.setUsr(VALID_USR);

        em.getTransaction().begin();
        em.persist(pc);
        em.flush();
        em.getTransaction().commit();
    }

    private void removeTestData() {
        em.getTransaction().begin();
        Policymaker p = em.find(Policymaker.class, VALID_SECRETCODE);
        if (p != null) {
            em.remove(p);
        }
        em.getTransaction().commit();
    }
    
    @Test
	public void getPolicyMakerValidUsr() throws CredentialsException {
    	Policymaker p = manager.getPolicyMaker(VALID_USR);
    	assertNotNull(p);
    }
    
    @Test
	public void getPolicyMakerInvalidUsr() throws CredentialsException {
    	 em.getTransaction().begin();
         INVALID_USR=em.find(Usr.class, INVALID_EMAIL);
         em.getTransaction().commit();
    	Policymaker p = manager.getPolicyMaker(INVALID_USR);
    	assertNull(p);
    }
}