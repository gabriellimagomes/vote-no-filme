package br.com.gabriel.filme.repositories;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.jintegrity.core.JIntegrity;
import com.jintegrity.helper.HibernateHelper;

public class FilmeRepositoryImplTest {
	
	private JIntegrity helper = new JIntegrity();
	private FilmeRepository repository;
	
	@BeforeClass
	public static void beforeClass() {
		HibernateHelper.sessionFactory();
	}

	@Before
	public void setUp() {
		helper.path("dataset").cleanAndInsert("filme");
		repository = new FilmeRepositoryImpl(HibernateHelper.currentSession());
	}
	
	@After
	public void tearDown() {
		HibernateHelper.close();
		helper.clean();
	}

    @Test
    public void deveTrazerIdsFilmes() {
    	
    	List<Long> idsFilmesEsperados = criaIdsFilmes();
		List<Long> idsFilmesRecebidos = repository.getIdsFilmes();
		
		Assert.assertEquals(idsFilmesEsperados.size(), idsFilmesRecebidos.size());
		
		for (Long id : idsFilmesRecebidos) {
			Assert.assertTrue(idsFilmesEsperados.contains(id));
		}
  	}

	private List<Long> criaIdsFilmes() {
		List<Long> idFilmes = new ArrayList<Long>();
		idFilmes.add(1L);
		idFilmes.add(2L);
		idFilmes.add(3L);
		idFilmes.add(4L);
		idFilmes.add(5L);
		
		return idFilmes;
	}
}

