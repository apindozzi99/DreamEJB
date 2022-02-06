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


public class IncentiveManagerTest {
	@InjectMocks
	IncentiveManager manager;
	 
	 EntityManager entityManager;
	 
	 @Mock
	 private Incentive inc;
	 
	 @Mock
	 private Farmer f;
	 
	 @Mock
	 private Field field;
	 
	 @Mock
	 private Field wrongfield;
	 
	 @Mock
	 private Usr usr;
	 @Mock
	 private Agronomistreport ar;
	 
	 @Mock
	 TypedQuery<Agronomistreport> query;
	
	 @Mock
	 TypedQuery<Production> query1;

	@Before
	public void setUp() throws Exception {
		entityManager = mock(EntityManager.class);
		manager= new IncentiveManager();
		manager.setEm(entityManager);
		Agronomistreport agrRep = new Agronomistreport();
		agrRep.setDate(validDate);
		agrRep.setFarmer(f);
		agrRep.setFieldBean(field);
		agrRep.setIdagronomistReport(1);
		agrRep.setName("Bob");
	    query = mock(TypedQuery.class);
	    query1 = mock(TypedQuery.class);
		when((query).setParameter(anyInt(), any())).thenReturn(query);
		when((query1).setParameter(anyInt(), any())).thenReturn(query1);
	   
	}

	@After
	public void tearDown() throws Exception {
    }
	

	@Test
	public void getAllAgronomistReportTest() throws NonUniqueResultException, CredentialsException, NamingException {
		TypedQuery<Agronomistreport> query = mock(TypedQuery.class);
	     when(entityManager.createNamedQuery("Agronomistreport.findAll", Agronomistreport.class)).thenReturn( query);
	     List<Agronomistreport> ars = new LinkedList<Agronomistreport>();
	     when((query).getResultList()).thenReturn(ars);
	     List<Agronomistreport> arsChecked =manager.getAllAgronomistReport();	     
	    verify(entityManager).createNamedQuery("Agronomistreport.findAll", Agronomistreport.class);
	    verify(query).getResultList();
		assertSame(ars, arsChecked);
	}
	
	@Test
	public void getDetailTestWrongId() throws NonUniqueResultException, CredentialsException, NamingException {
		List<Agronomistreport> ars = new LinkedList<Agronomistreport>();
		when(entityManager.createNamedQuery("Agronomistreport.getReport", Agronomistreport.class)).thenReturn(query);
        when(query.getResultList()).thenReturn(ars);
        Agronomistreport arChecked = manager.getDetail(0);
        verify(entityManager).createNamedQuery("Agronomistreport.getReport", Agronomistreport.class);
	    verify(query).getResultList();
        assertNull(arChecked);
	}
	
	@Test
	public void getProductionBeforeWrongField() throws NonUniqueResultException, CredentialsException, NamingException {
		List<Production> prs = new LinkedList<Production>();
		when(entityManager.createNamedQuery("Production.getProductionBefore", Production.class)).thenReturn(query1);
        when(query1.getResultList()).thenReturn(prs);
		List<Production> prsChecked = manager.getProductionBefore(wrongfield, validDate);
        verify(entityManager).createNamedQuery("Production.getProductionBefore", Production.class);
	    verify(query1).getResultList();
        assertNotSame(prs, prsChecked);
	}
	
	@Test
	public void getProductionAfterWrongField() throws NonUniqueResultException, CredentialsException, NamingException {
		List<Production> prs = new LinkedList<Production>();
		when(entityManager.createNamedQuery("Production.getProductionAfter", Production.class)).thenReturn(query1);
        when(query1.getResultList()).thenReturn(prs);
		List<Production> prsChecked = manager.getProductionAfter(wrongfield, validDate);
        verify(entityManager).createNamedQuery("Production.getProductionAfter", Production.class);
	    verify(query1).getResultList();
        assertNotSame(prs, prsChecked);
	}

}

