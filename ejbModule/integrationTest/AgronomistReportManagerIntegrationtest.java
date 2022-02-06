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
 * Class which tests the AgronomistReport and the consistency with the model
 * Test the connections between the other Manager
 * 
 */
public class AgronomistReportManagerIntegrationtest {
	
	private static final int VALID_ID = 999;
	private static final String VALID_NAME = "test";
	private static final String VALID_FARMER_EMAIL= "1";
	private static final Date VALID_DATE= new Date();
	private static final String VALID_DETAIL= "detailTest";
	private static final float VALID_LOCATION = 545;
	
	private static final float INVALID_LOCATION = 5435;
	
	private static final int INVALID_ID = 100;

	private Farmer VALID_FARMER;
	private Field VALID_FIELD;
	
	private Field INVALID_FIELD;
	
	 private static EntityManagerFactory emf;
	    private static EntityManager em;

	    private AgronomistReportManager manager;

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

	        manager = new AgronomistReportManager();
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
        Agronomistreport ar = new Agronomistreport();
        em.getTransaction().begin();
        VALID_FARMER=em.find(Farmer.class, VALID_FARMER_EMAIL);
        VALID_FIELD=em.find(Field.class, VALID_LOCATION);
        em.getTransaction().commit();
        
        ar.setDate(VALID_DATE);
        ar.setDetails(VALID_DETAIL);
        ar.setFarmer(VALID_FARMER);
        ar.setFieldBean(VALID_FIELD);
        ar.setName(VALID_NAME);
        ar.setIdagronomistReport(VALID_ID);

        em.getTransaction().begin();
        em.persist(ar);
        em.flush();
        em.getTransaction().commit();
    }

    private void removeTestData() {
        em.getTransaction().begin();
        Agronomistreport ar = em.find(Agronomistreport.class, VALID_ID);
        if (ar != null) {
            em.remove(ar);
        }
        em.getTransaction().commit();
    }

	@Test
	public void getAllAgrnomistReportTest() {
		List<Agronomistreport> ars = manager.getAllAgronomistReport();
		assertNotNull(ars);
	}
	
	@Test
	public void getDetailTestValidId() throws NonUniqueResultException, CredentialsException {
		Agronomistreport ar = manager.getDetail(VALID_ID);
		assertNotNull(ar);
	}
	
	@Test
	public void getDetailtestWrongId() throws NonUniqueResultException, CredentialsException {
		Agronomistreport ar = manager.getDetail(INVALID_ID);
		assertNull(ar);
	}
	
	@Test
	public void getProductionBeforeTest() throws NonUniqueResultException, CredentialsException {
		List<Production> prods = manager.getProductionBefore(VALID_FIELD, VALID_DATE);
		assertNotNull(prods);
	}
	
	@Test
	public void getProductionBeforeTestWrongField() throws NonUniqueResultException, CredentialsException {
		 em.getTransaction().begin();
	     INVALID_FIELD=em.find(Field.class, INVALID_LOCATION);
	     em.getTransaction().commit();
		 List<Production> prods = manager.getProductionBefore(INVALID_FIELD, VALID_DATE);
		 assertNull(prods);
	}
	
	@Test
	public void getProductionAfterTest() throws NonUniqueResultException, CredentialsException {
		List<Production> prods = manager.getProductionAfter(VALID_FIELD, VALID_DATE);
		assertNotNull(prods);
	}
	
	@Test
	public void getProductionAfterTestWrongField() throws NonUniqueResultException, CredentialsException {
		 em.getTransaction().begin();
	        INVALID_FIELD=em.find(Field.class, INVALID_LOCATION);
	        em.getTransaction().commit();
	       // List<Production> prodsNul[];
		List<Production> prods = manager.getProductionAfter(INVALID_FIELD, VALID_DATE);
		assertNull(prods);
	}

}
