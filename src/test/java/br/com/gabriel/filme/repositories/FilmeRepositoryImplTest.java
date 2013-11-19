package br.com.gabriel.filme.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import com.jintegrity.core.JIntegrity;
import com.jintegrity.helper.HibernateHelper;

public class FilmeRepositoryImplTest {
	
	private JIntegrity helper = new JIntegrity();
	@Mock
	private FilmeRepository repository;

	@Before
	public void setUp() {
		helper.path("dataset").cleanAndInsert("filme");
		repository = new FilmeRepositoryImpl(HibernateHelper.currentSession());
	}

    @Test 
    public void deveTrazerIdsFilmes() {
    	
    	List<Long> idsFilmesEsperados = criaIdsFilmes();
		Set<Long> idsFilmesRecebidos = repository.getIdsFilmes();
		
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

