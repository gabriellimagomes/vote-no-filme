package br.com.gabriel.filme.repositories;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.gabriel.filme.models.Filme;
import br.com.gabriel.filme.models.Ranking;
import br.com.gabriel.filme.models.Usuario;

import com.jintegrity.core.JIntegrity;
import com.jintegrity.helper.HibernateHelper;

public class VotoRepositoryImplTest {

	private Filme filme1;
	private Filme filme2;
	private Filme filme3;
	private Filme filme4;
	private Filme filme5;
	private VotoRepositoryImpl repository;
	private JIntegrity helper = new JIntegrity();

	@BeforeClass
	public static void beforeClass() {
		HibernateHelper.sessionFactory();
		
	}

	@Before
	public void setUp() {
		helper.path("dataset").cleanAndInsert("filme").cleanAndInsert("usuario").cleanAndInsert("voto");
		repository = new VotoRepositoryImpl(HibernateHelper.currentSession());
		
		criaFilmes();
	}

	private void criaFilmes() {
		filme1 = new Filme(1L, "Filme A");
		filme2 = new Filme(2L, "Filme B");
		filme3 = new Filme(3L, "Filme C");
		filme4 = new Filme(4L, "Filme D");
		filme5 = new Filme(5L, "Filme E");
	}

	@After
	public void tearDown() {
		HibernateHelper.close();
		helper.clean();
	}

	@Test
	public void deveApresentarRankingDosFilmesAtravesDosVotosGerais() {

		assertEquals(criaRankingGeral(), repository.rankingGeral());

	}
	
	@Test
	public void deveApresentarRankingDosFilmesAtravesDosVotosDoUsuario() {
		
		Usuario usuario = new Usuario();
		usuario.setId(1L);
		
		assertEquals(criaRankingDoUsuario(), repository.rankingPorUsuario(usuario));
		
	}
	
	private List<Ranking> criaRankingDoUsuario() {
		List<Ranking> rankingEsperado = new ArrayList<Ranking>();
		rankingEsperado.add(new Ranking(filme1, 3L));
		rankingEsperado.add(new Ranking(filme2, 2L));
		rankingEsperado.add(new Ranking(filme3, 1L));
		rankingEsperado.add(new Ranking(filme4, 0L));
		rankingEsperado.add(new Ranking(filme5, 0L));
		
		return rankingEsperado;
	}
	
	private List<Ranking> criaRankingGeral() {
		List<Ranking> rankingEsperado = new ArrayList<Ranking>();
		rankingEsperado.add(new Ranking(filme1, 3L));
		rankingEsperado.add(new Ranking(filme2, 3L));
		rankingEsperado.add(new Ranking(filme3, 1L));
		rankingEsperado.add(new Ranking(filme4, 1L));
		rankingEsperado.add(new Ranking(filme5, 0L));
		
		return rankingEsperado;
	}
}
