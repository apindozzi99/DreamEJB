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

public class ProductionManagerIntegrationtest {
	
	private static final int VALID_ID = 1111;
	private static final String VALID_PRODUCT = "crop";
	private static final Date VALID_PLANTED_DATE = new Date();
	private static final Date VALID_COLLECTED_DATE = new Date();
	private static final float VALID_PLANTED_AMOUNT = 1111;
	private static final float VALID_COLLECTED_AMOUNT = 1111;
	private static final float VALID_AVG_WATER = 11111;
	private static final float VALID_AVG_RAINFALL = 11111;
	private static final float VALID_AVG_HUMIDITYSOIL = 1111;
	private static final float VALID_FIELD_LOCATION = 536;
	
	private static final int INVALID_ID = 909;
	private static final float INVALID_FIELD_LOCATION = 64745;

	private Field VALID_FIELD;
	private Field INVALID_FIELD;
	
	 private static EntityManagerFactory emf;
	    private static EntityManager em;

	    private ProductionManager manager;

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

	        manager = new ProductionManager();
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
        Production prod = new Production();
        em.getTransaction().begin();
        VALID_FIELD=em.find(Field.class, VALID_FIELD_LOCATION);
        em.getTransaction().commit();
        
        prod.setIdproduction(VALID_ID);
        prod.setAvgHumiditySoil(VALID_AVG_HUMIDITYSOIL);
        prod.setAvgRainFall(VALID_AVG_RAINFALL);
        prod.setAvgWater(VALID_AVG_WATER);
        prod.setCollectedAmount(VALID_COLLECTED_AMOUNT);
        prod.setPlantedAmount(VALID_PLANTED_AMOUNT);
        prod.setCollectedDate(VALID_COLLECTED_DATE);
        prod.setPlantedDate(VALID_PLANTED_DATE);
        prod.setField(VALID_FIELD);
        prod.setProduct(VALID_PRODUCT);
        
        em.getTransaction().begin();
        em.persist(prod);
        em.flush();
        em.getTransaction().commit();
    }

    private void removeTestData() {
        em.getTransaction().begin();
        Production prod = em.find(Production.class, VALID_ID);
        if (prod != null) {
            em.remove(prod);
        }
        em.getTransaction().commit();
    }

	@Test
	public void getAllProductionTestValidField() {
		List<Production> prods = manager.getAllProduction(VALID_FIELD);
		assertNotNull(prods);
	}
	
	@Test
	public void getAllProductionTestWrongField() {
		 em.getTransaction().begin();
	     INVALID_FIELD=em.find(Field.class, INVALID_FIELD_LOCATION);
	     em.getTransaction().commit();
		List<Production> prods = null;
		prods = manager.getAllProduction(INVALID_FIELD);
		assertNull(prods);
	}
	
	@Test
	public void getProductionValidId() throws NonUniqueResultException, CredentialsException {
		Production prod = manager.getProduction(VALID_ID);
		assertNotNull(prod);
	}
	
	@Test
	public void getProductionWrongId() throws NonUniqueResultException, CredentialsException {
		Production prod = manager.getProduction(INVALID_ID);
		assertNull(prod);
	}

}
