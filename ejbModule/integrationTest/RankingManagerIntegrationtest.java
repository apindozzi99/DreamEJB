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
 * Class which tests the RankingManager and the consistency with the model
 * Test the connections between the other Manager
 * 
 */

public class RankingManagerIntegrationtest {
	
	private static final int VALID_ID_PRODUCTION = 1;
	private static final float VALID_SCORE = 23;
	
	private static final int VALID_ID = 1112;
	private static final String VALID_PRODUCT = "crop";
	private static final Date VALID_PLANTED_DATE = new Date();
	private static final Date VALID_COLLECTED_DATE = new Date();
	private static final float VALID_PLANTED_AMOUNT = 1111;
	private static final float VALID_COLLECTED_AMOUNT = 1111;
	private static final float VALID_AVG_WATER = 11111;
	private static final float VALID_AVG_RAINFALL = 11111;
	private static final float VALID_AVG_HUMIDITYSOIL = 1111;
	private static final float VALID_FIELD_LOCATION = 536;

	private static final String INVALID_PRODUCT = "zucchini";
	private static final int INVALID_ID = 909;
	private static final float INVALID_FIELD_LOCATION = 64745;

	private Production VALID_PRODUCTION;
	private Field VALID_FIELD;
	
	 private static EntityManagerFactory emf;
	    private static EntityManager em;

	    private RankingManager manager;

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

	        manager = new RankingManager();
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
        Ranking ran = new Ranking();
        em.getTransaction().begin();
        VALID_PRODUCTION=em.find(Production.class, VALID_ID);
        em.getTransaction().commit();
        
        ran.setIdproduction(VALID_ID);
        ran.setProduction(VALID_PRODUCTION);
        ran.setProduct(VALID_PRODUCT);
        ran.setScore(VALID_SCORE);
        
        em.getTransaction().begin();
        em.persist(ran);
        em.flush();
        em.getTransaction().commit();
    }

    private void removeTestData() {
    	   em.getTransaction().begin();
           Ranking ran = em.find(Ranking.class, VALID_ID);
           if (ran != null) {
               em.remove(ran);
           }
           em.getTransaction().commit();
        em.getTransaction().begin();
        Production prod = em.find(Production.class, VALID_ID);
        if (prod != null) {
            em.remove(prod);
        }
        em.getTransaction().commit();
    }

	@Test
	public void getAllRankingTestValidProduction() {
		List<Ranking> rans = manager.getRanking(VALID_PRODUCT);
		assertNotNull(rans);
	}
	
	@Test
	public void getAllRankingTestInvalidProduction() {
		List<Ranking> rans = manager.getRanking(INVALID_PRODUCT);
		assertNull(rans);
	}
	
	@Test
	public void getProductListTest() {
		List<String> prods = manager.getProductList();
		assertNotNull(prods);
	}
	
	@Test
	public void getProductionListDescTestValidProduct() {
		List<Ranking> rans = manager.getProductListDesc(VALID_PRODUCT);
		assertNotNull(rans);
	}
	
	@Test
	public void getProductionListDescTestInvalidProduc() {
		List<Ranking> rans = manager.getProductListDesc(INVALID_PRODUCT);
		assertNull(rans);
	}
	
	@Test
	public void addNotificationInTestValidProduction() {
		em.getTransaction().begin();
        Production prod = em.find(Production.class, 5);
        em.getTransaction().commit();
		boolean check = manager.addNotification(prod);
		assertFalse(check);
	}

}
