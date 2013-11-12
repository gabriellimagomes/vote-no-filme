package br.com.gabriel.filme.repositories;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.gabriel.filme.models.Filme;

import com.jintegrity.core.JIntegrity;
import com.jintegrity.helper.HibernateHelper;

public class VotoRepositoryImplTest {

	private Filme filmeA;
	private Filme filmeB;
	private Filme filmeC;
	private Filme filmeD;
	private static SessionFactory sessionFactory;
	private VotoRepositoryImpl repository;
	private JIntegrity helper = new JIntegrity();
	
	@BeforeClass
	public static void beforeClass(){
		HibernateHelper.sessionFactory();
	}
	
	@AfterClass
	public static void afterClass(){
		//HibernateHelper.close();
	}
	
	@Before
	public void setUp() {
		helper.path("dataset").cleanAndInsert("filme");
		repository = new VotoRepositoryImpl(HibernateHelper.currentSession());
	}
	
	@After
	public void tearDown(){
		//HibernateHelper.close();
        helper.clean();
	}

	@Test
	public void abc(){
		System.out.println("VotoRepositoryImplTest.abc()");
	}
	
	public void deveApresentarRankingDosFilmesAtravesDosVotos() {

		Map<Filme, Integer> rankingEsperado = new HashMap<Filme, Integer>();
		rankingEsperado.put(filmeA, 3);
		rankingEsperado.put(filmeB, 2);
		rankingEsperado.put(filmeC, 1);
		rankingEsperado.put(filmeD, 0);

		/**
		 * controller.votar(filmeA, filmeB, filmeA); controller.votar(filmeD,
		 * filmeC, filmeC); controller.votar(filmeA, filmeC, filmeA);
		 * controller.votar(filmeB, filmeD, filmeB); controller.votar(filmeA,
		 * filmeD, filmeA); controller.votar(filmeB, filmeC, filmeB);
		 * 
		 * VotoRepository repository = new VotoRepositoryImpl();
		 * 
		 * Mockito.when(repository.ranking()).thenReturn(rankingEsperado);
		 * 
		 * assertEquals(rankingEsperado, controller.ranking());
		 **/
	}
}
