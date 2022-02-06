package integrationTest;

import static org.junit.Assert.*;

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

public class LoginManagerIntegrationtest {
	
		private static final String EMAIL = "TestUsr";
	    private static final String PASSWORD = "test";
	    private static final String INVALID_EMAIL = "test";
	    private static final String INVALID_PASSWORD = "test_pss";

	    private static EntityManagerFactory emf;
	    private static EntityManager em;

	    private LoginManager manager;

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

	        manager = new LoginManager();
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
	        Usr usr = new Usr();
	        usr.setEmail(EMAIL);
	        usr.setPassword(PASSWORD);

	        em.getTransaction().begin();
	        em.persist(usr);
	        em.flush();
	        em.getTransaction().commit();
	    }

	    private void removeTestData() {
	        em.getTransaction().begin();
	        Usr usr = em.find(Usr.class, EMAIL);
	        if (usr != null) {
	            em.remove(usr);
	        }
	        em.getTransaction().commit();
	    }

	@Test
	public void CheckCredentialsCorrectUsr() throws NonUniqueResultException, CredentialsException {
		Usr usr = manager.checkCredentials(EMAIL, PASSWORD);
		assertNotNull(usr);
	}
	
	@Test
	public void CheckCredentialsWrongPassword() throws NonUniqueResultException, CredentialsException {
		Usr usr = manager.checkCredentials(EMAIL, INVALID_PASSWORD);
		assertNull(usr);
	}
	
	@Test
	public void CheckCredentialsWrongEmail() throws NonUniqueResultException, CredentialsException {
		Usr usr = manager.checkCredentials(INVALID_EMAIL, PASSWORD);
		assertNull(usr);
	}
	
	@Test
	public void CheckCredentialsWrongEmailWrongPassword() throws NonUniqueResultException, CredentialsException {
		Usr usr = manager.checkCredentials(INVALID_EMAIL, INVALID_PASSWORD);
		assertNull(usr);
	}

}
