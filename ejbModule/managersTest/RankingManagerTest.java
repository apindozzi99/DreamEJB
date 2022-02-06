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


public class RankingManagerTest {

	/*private Context  ctx;
    private EJBContainer ejbContainer;*/
	@InjectMocks
	 RankingManager manager;
	 
	 EntityManager entityManager;
	 
	 @Mock
	 private Ranking rank;
	 
	 @Mock
	 private Production prod;
	 
	 @Mock
	 private Production invalidProd;
	 
	 @Mock
	 TypedQuery<Ranking> query;
	 
	 @Mock
	 TypedQuery<String> query1;
	 
	 @Mock
	 TypedQuery<Incentive> query2;
	

	@Before
	public void setUp() throws Exception {
		entityManager = mock(EntityManager.class);
		manager= new RankingManager();
		manager.setEm(entityManager);
		
	    query = mock(TypedQuery.class);
		when((query).setParameter(anyInt(), any())).thenReturn(query);
		
		 query1 = mock(TypedQuery.class);
		 when((query1).setParameter(anyInt(), any())).thenReturn(query1);
		 
		 query2 = mock(TypedQuery.class);
		 when((query2).setParameter(anyInt(), any())).thenReturn(query2);
		
		rank = new Ranking();
		rank.setIdproduction(0);
		rank.setProduct("test");
		rank.setProduction(prod);
		rank.setScore(0);
	   
	}

	@After
	public void tearDown() throws Exception {
    }
	
	@Test
	public void getRankingTestWrongProd() throws NonUniqueResultException, CredentialsException {
		List<Ranking> ranks = new LinkedList<Ranking>();
		when(entityManager.createNamedQuery("Ranking.getRanking", Ranking.class)).thenReturn(query);
        when(query.getResultList()).thenReturn(ranks);
        List<Ranking> ranksChecked = manager.getRanking("invalidtest");
        verify(entityManager).createNamedQuery("Ranking.getRanking", Ranking.class);
	    verify(query).getResultList();
        assertNull(ranksChecked);
	}

	@Test
	public void getProductListTestWrongProd() throws NonUniqueResultException, CredentialsException {
		List<String> prods = new LinkedList<String>();
		when(entityManager.createNamedQuery("Ranking.getProductList", String.class)).thenReturn(query1);
        when(query1.getResultList()).thenReturn(prods);
        List<String> prodsChecked = manager.getProductList();
        verify(entityManager).createNamedQuery("Ranking.getProductList", String.class);
	    verify(query1).getResultList();
        assertSame(prods, prodsChecked);
	}
	
	@Test
	public void checkIfAlreadyExistsTestWrongProduction() throws NonUniqueResultException, CredentialsException {
		List<Incentive> incs = new LinkedList<Incentive>();
		when(entityManager.createNamedQuery("Incentive.checkUnique", Incentive.class)).thenReturn(query2);
        when(query2.getResultList()).thenReturn(incs);
        Boolean check = manager.checkIfAlreadyExists(invalidProd);
        verify(entityManager).createNamedQuery("Incentive.checkUnique", Incentive.class);
	    verify(query2).getResultList();
        assertFalse(check);
	}

}

