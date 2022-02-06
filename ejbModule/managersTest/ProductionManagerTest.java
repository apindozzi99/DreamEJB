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


public class ProductionManagerTest {

	@InjectMocks
	 ProductionManager manager;
	 
	 EntityManager entityManager;
	 
	 @Mock
	 private Farmer f;
	 @Mock
	 private Field field;
	 @Mock
	 private Field invalidfield;
	 @Mock
	 private Production prod;
	 
	 @Mock
	 TypedQuery<Production> query;
	

	@Before
	public void setUp() throws Exception {
		entityManager = mock(EntityManager.class);
		manager= new ProductionManager();
		manager.setEm(entityManager);
		
	    query = mock(TypedQuery.class);
		when((query).setParameter(anyInt(), any())).thenReturn(query);
		Date date = new Date();
		prod = new Production();
		prod.setAvgHumiditySoil(0);
		prod.setAvgRainFall(0);
		prod.setAvgWater(0);
		prod.setCollectedAmount(0);
		prod.setCollectedDate(date);
		prod.setField(field);
		prod.setIdproduction(0);
		prod.setPlantedAmount(0);
		prod.setPlantedDate(date);
	}

	@After
	public void tearDown() throws Exception {
    }
	

	@Test
	public void getAllProductionTestWrongField() throws NonUniqueResultException, CredentialsException, NamingException {
		List<Production> prods = new LinkedList<Production>();
		when(entityManager.createNamedQuery("Production.getAll", Production.class)).thenReturn(query);
        when(query.getResultList()).thenReturn(prods);
        List<Production> prodsChecked = manager.getAllProduction(invalidfield);
        verify(entityManager).createNamedQuery("Production.getAll", Production.class);
	    verify(query).getResultList();
        assertNull(prodsChecked);
	}
	
	@Test
	public void getProduction() throws NonUniqueResultException, CredentialsException {
		List<Production> prods = new LinkedList<Production>();
		when(entityManager.createNamedQuery("Production.getProduction", Production.class)).thenReturn(query);
        when(query.getResultList()).thenReturn(prods);
        Production prodChecked = manager.getProduction(1);
        verify(entityManager).createNamedQuery("Production.getProduction", Production.class);
	    verify(query).getResultList();
        assertNull(prodChecked);
	}


}

