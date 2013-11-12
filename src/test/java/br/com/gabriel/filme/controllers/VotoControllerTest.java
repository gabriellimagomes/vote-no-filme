package br.com.gabriel.filme.controllers;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.caelum.vraptor.util.test.MockResult;
import br.com.gabriel.filme.exception.VotoInvalidoException;
import br.com.gabriel.filme.models.Filme;
import br.com.gabriel.filme.models.Voto;
import br.com.gabriel.filme.repositories.VotoRepository;

public class VotoControllerTest {
	@Mock
	private VotoRepository repository;
	private VotoController controller;
	private Voto voto;
	private Filme filmeA;
	private Filme filmeB;
	private Filme filmeC;
	private Filme filmeD;
	private Filme filmeVotado;

	private MockResult result;
	private Set<Filme> doisFilmesParaDuelo;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		result = spy(new MockResult());
		controller = new VotoController(result, repository);
		
		criaFilmes();
		voto = new Voto(filmeA, filmeB, filmeVotado);
		criaDoisFilmesParaDuelo();
	}

	private void criaDoisFilmesParaDuelo() {
		doisFilmesParaDuelo = new HashSet<Filme>();
		doisFilmesParaDuelo.add(new Filme(1L, "Filme A"));
		doisFilmesParaDuelo.add(new Filme(2L, "Filme B"));
	}

	private void criaFilmes() {
		filmeA = new Filme(1L, "Filme A");
		filmeB = new Filme(2L, "Filme B");
		filmeC = new Filme(3L, "Filme C");
		filmeD = new Filme(4L, "Filme D");
		filmeVotado = new Filme(1L, "Filme A");
	}
	
	@Test
	public void deveVotarEmUmFilme(){
		controller.votar(filmeA, filmeB, filmeVotado);
		verify(repository, only()).votar(voto);		
	}
	
	@Test
	public void deveTrazerProximosDoisFilmesDepoisDoVoto(){
		VotoController spyController = spy(controller);
		when(result.forwardTo(controller)).thenReturn(spyController);
		
		controller.votar(filmeA, filmeB, filmeVotado);
		
		verify(spyController).index();
	}
	
	@Test(expected=VotoInvalidoException.class)
	public void deveDarErroCasoVoteEmUmFilmeQueNaoFoiPassado(){
		filmeVotado =  new Filme(3L, "Filme que nao foi passado pro duelo");
		controller.votar(filmeA, filmeB, filmeVotado);
		verify(repository, never()).votar(voto);
	}
	
	@Test
	public void deveApresentarRankingDosFilmesAtravesDosVotos(){
		
		Map<Filme, Integer> rankingEsperado = new HashMap<Filme, Integer>();
		rankingEsperado.put(filmeA, 3);
		rankingEsperado.put(filmeB, 2);
		rankingEsperado.put(filmeC, 1);
		rankingEsperado.put(filmeD, 0);
		
		
		controller.votar(filmeA, filmeB, filmeA);
		controller.votar(filmeD, filmeC, filmeC);
		controller.votar(filmeA, filmeC, filmeA);
		controller.votar(filmeB, filmeD, filmeB);
		controller.votar(filmeA, filmeD, filmeA);
		controller.votar(filmeB, filmeC, filmeB);
		
		Mockito.when(repository.ranking()).thenReturn(rankingEsperado);
		
		assertEquals(rankingEsperado, controller.ranking());
	}
	
	@Test
	public void deveTrazerDoisFilmesProDuelo(){
		when(repository.getDoisFilmesAleatorios()).thenReturn(doisFilmesParaDuelo);
		
		controller.index();
		assertTrue("Deve retornar os filmes para o duelo", result.included().containsKey("filme1"));
		assertTrue("Deve retornar os filmes para o duelo", result.included().containsKey("filme2"));
	}
}
