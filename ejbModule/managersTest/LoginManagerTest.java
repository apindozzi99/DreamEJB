package managersTest;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.*;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.embeddable.EJBContainer;
import javax.inject.Inject;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NamedQuery;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.mockito.stubbing.Answer;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Any;

import exceptions.CredentialsException;
import managers.*;
import model.*;


public class LoginManagerTest {
	@InjectMocks
	LoginManager manager;
	 
	 EntityManager entityManager;
	 
	 @Mock
	 private Usr usr;
	 
	 @Mock
	 TypedQuery<Usr> query;
	

	@Before
	public void setUp() throws Exception {
		entityManager = mock(EntityManager.class);
		usr = new Usr();
		manager= new LoginManager();
		manager.setEm(entityManager);
		usr.setEmail("test");
		usr.setPassword("test");
	    query = mock(TypedQuery.class);
		when((query).setParameter(anyInt(), any())).thenReturn(query);
	   
	}

	@After
	public void tearDown() throws Exception {
    }
	

	@Test
	public void checkLoginWrongEmail() throws NonUniqueResultException, CredentialsException {
		List<Usr> usrs = new LinkedList<Usr>();
		when(entityManager.createNamedQuery("Usr.checkCredentials", Usr.class)).thenReturn(query);
        when(query.getResultList()).thenReturn(usrs);
        Usr usrChecked = manager.checkCredentials("testInvalid", "test");
        verify(entityManager).createNamedQuery("Usr.checkCredentials", Usr.class);
	    verify(query).getResultList();
        assertNull(usrChecked);
	}
	
	@Test
	public void checkLoginWrongPasswrod() throws NonUniqueResultException, CredentialsException {
		List<Usr> usrs = new LinkedList<Usr>();
		when(entityManager.createNamedQuery("Usr.checkCredentials", Usr.class)).thenReturn(query);
        when(query.getResultList()).thenReturn(usrs);
        Usr usrChecked = manager.checkCredentials("test", "testInvalid");
        verify(entityManager).createNamedQuery("Usr.checkCredentials", Usr.class);
	    verify(query).getResultList();
        assertNull(usrChecked);
	}

}

