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
 * Class which tests the IncentiveManager and the consistency with the model
 * Test the connections between the other Manager
 * 
 */

public class IncentiveManagerIntegrationtest {
	private static final int VALID_ID = 9999;
	private static final int VALID_ID_PROD = 1;
	private static final Date  VALID_DATE = new Date();
	private static final float VALID_AMOUNT = 1000;
	
	private static final int INVALID_ID = 909;
	private static final float INVALID_FIELD_LOCATION = 64745;

    private static EntityManagerFactory emf;
    private static EntityManager em;

    private IncentiveManager manager;

    private Production VALID_PRODUCTION;
	private Incentive VALID_INCENTIVE;
	
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

        manager = new IncentiveManager();
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
        Incentive incentive = new Incentive();
        em.getTransaction().begin();
        VALID_PRODUCTION=em.find(Production.class, VALID_ID_PROD);
        em.getTransaction().commit();
        
        incentive.setIdincentive(VALID_ID);
        incentive.setProduction(VALID_PRODUCTION);
        incentive.setDate(VALID_DATE);
        incentive.setDate(VALID_DATE);

        em.getTransaction().begin();
        em.persist(incentive);
        em.flush();
        em.getTransaction().commit();
    }

    private void removeTestData() {
        em.getTransaction().begin();
        Incentive incentive = em.find(Incentive.class, VALID_ID);
        if (incentive != null) {
            em.remove(incentive);
        }
        em.getTransaction().commit();
    }
    
    @Test
	public void getAllIncentiveTest() {
    	List<Incentive> incentives = manager.getAll();
    	assertNotNull(incentives);
	}

}
