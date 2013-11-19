package br.com.gabriel.filme.controllers;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.caelum.vraptor.util.test.MockResult;
import br.com.gabriel.filme.exception.VotoInvalidoException;
import br.com.gabriel.filme.models.Duelo;
import br.com.gabriel.filme.models.DueloDisponivel;
import br.com.gabriel.filme.models.Filme;
import br.com.gabriel.filme.models.Usuario;
import br.com.gabriel.filme.models.UsuarioSession;
import br.com.gabriel.filme.models.Voto;
import br.com.gabriel.filme.repositories.VotoRepositoryImpl;

public class VotoControllerTest {
	@Mock
	private VotoRepositoryImpl repository;
	@Mock
	private DueloDisponivel dueloDisponivel;
	@Mock
	private UsuarioController mockUsuarioController;
	@Mock
	private UsuarioSession usuarioSession;
	private VotoController controller;
	private Voto voto;
	private Filme filme1;
	private Filme filme2;
	private Filme filmeVotado;

	private MockResult result;
	private Duelo dueloComDoisFilmes;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		result = spy(new MockResult());		
		controller = new VotoController(result, repository, dueloDisponivel, usuarioSession);
		
		criaFilmes();
		criaDueloComDoisFilmes();
		voto = new Voto(dueloComDoisFilmes, filmeVotado, criaUsuario());
	}
	
	private Usuario criaUsuario() {
		Usuario usuario = new Usuario();
		usuario.setId(1L);
		
		return usuario;
	}

	private Duelo criaDuelo(Filme filme1, Filme filme2) {
		
		return new Duelo(filme1, filme2);
	}

	private Duelo criaDueloComDoisFilmes() {
		
		dueloComDoisFilmes = criaDuelo(filme1, filme2);
		return dueloComDoisFilmes;
	}
	
	private void criaFilmes() {
		filme1 = new Filme(1L, "Filme A");
		filme2 = new Filme(2L, "Filme B");
		filmeVotado = new Filme(1L, "Filme A");
	}
	
	@Test
	public void deveVotarEmUmFilme(){
		controller.votar(dueloComDoisFilmes, filmeVotado);
		verify(repository, only()).votar(voto);		
	}
	
	@Test
	public void deveTrazerProximosDoisFilmesDepoisDoVoto(){
		VotoController spyController = spy(controller);
		when(result.redirectTo(controller)).thenReturn(spyController);
		
		when(dueloDisponivel.duelo()).thenReturn(dueloComDoisFilmes);
		when(dueloDisponivel.temDuelo()).thenReturn(true);
		
		controller.votar(dueloComDoisFilmes, filmeVotado);
		
		verify(spyController).index();
		
	}
	
	@Test
	public void deveDarErroCasoVoteEmUmFilmeQueNaoFoiPassado(){
		try {
			filmeVotado =  new Filme(3L, "Filme que nao foi passado pro duelo");
			controller.votar(dueloComDoisFilmes, filmeVotado);
		
		} catch (VotoInvalidoException e) {
			verify(repository, never()).votar(voto);
		}
	}
	
	@Test
	public void deveTrazerDoisFilmesProDuelo(){
		when(dueloDisponivel.temDuelo()).thenReturn(true);
		when(dueloDisponivel.duelo()).thenReturn(criaDueloComDoisFilmes());
		
		controller.index();
		assertTrue("Deve retornar os filmes para o duelo", containsNoResult("filme1"));
		assertTrue("Deve retornar os filmes para o duelo", containsNoResult("filme2"));
	}
	
	@Test
	public void deveTrazerRankingDosFilmes() {
		controller.ranking();
		
		verify(repository).rankingPorUsuario(any(Usuario.class));
		verify(repository).rankingGeral();
		
		assertTrue("Retornou ranking do usuario", containsNoResult("rankingUsuario"));
		assertTrue("Retornou ranking geral", containsNoResult("rankingGeral"));
	}
	
	@Test
	public void deveTrazerCamposParaUsuarioPreencherAposAcabaremFilmesParaVotar() {
		
		when(result.redirectTo(UsuarioController.class)).thenReturn(mockUsuarioController);
		when(dueloDisponivel.temDuelo()).thenReturn(false);
		
		controller.votar(dueloComDoisFilmes, filmeVotado);
		
		verify(mockUsuarioController).newUsuario();
		
	}
	
	private boolean containsNoResult(String nome) {
		return result.included().containsKey(nome);
	}
}
