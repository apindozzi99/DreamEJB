

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


public class FieldManagerTest {

	/*private Context  ctx;
    private EJBContainer ejbContainer;*/
	@InjectMocks
	 FieldManager manager;
	 
	 EntityManager entityManager;
	 
	 @Mock
	 private Farmer f;
	 
	 @Mock
	 private Usr usr;
	 @Mock
	 private Field field;
	 
	 @Mock
	 TypedQuery<Field> query;
	

	@Before
	public void setUp() throws Exception {
		/*ejbContainer = EJBContainer.createEJBContainer();
        System.out.println("Opening the container" );
        ctx = ejbContainer.getContext();*/
		entityManager = mock(EntityManager.class);
		manager= new FieldManager();
		manager.setEm(entityManager);
		
	    query = mock(TypedQuery.class);
		when((query).setParameter(anyInt(), any())).thenReturn(query);
		
		field = new Field();
		field.setLocation(1);
		field.setFarmer(f);
		field.setSize(1000);
	   
	}

	@After
	public void tearDown() throws Exception {
		/*ejbContainer.close();
        System.out.println("Closing the container" );*/
    }
	

	@Test
	public void getAllTest() throws NonUniqueResultException, CredentialsException, NamingException {
		/*LoginManager manager = (LoginManager) ejbContainer.getContext().lookup("java:global/LoginManager");
		assertNotNull(manager);
		Usr usr = manager.checkCredentials("6", "5");
		assertNotNull(usr);*/
		TypedQuery<Field> query = mock(TypedQuery.class);
	     when(entityManager.createNamedQuery("Field.findAll", Field.class)).thenReturn( query);
	     List<Field> fields = new LinkedList<Field>();
	     when((query).getResultList()).thenReturn(fields);
	     List<Field> fieldsChecked =manager.getAllFields();
	     
	    verify(entityManager).createNamedQuery("Field.findAll", Field.class);
	    verify(query).getResultList();
		assertSame(fields, fieldsChecked);
	}
	
	@Test
	public void getFieldTesWrongLocation() throws NonUniqueResultException, CredentialsException {
		List<Field> fields = new LinkedList<Field>();
		when(entityManager.createNamedQuery("Field.getField", Field.class)).thenReturn(query);
        when(query.getResultList()).thenReturn(fields);
        verify(entityManager).createNamedQuery("Field.findAll", Field.class);
	    verify(query).getResultList();
       // Field fieldChecked = manager.getField(1);
        //assertSame(fields.get(0), fieldChecked);
	}


}

