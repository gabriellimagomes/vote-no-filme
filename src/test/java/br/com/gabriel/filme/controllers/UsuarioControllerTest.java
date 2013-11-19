package br.com.gabriel.filme.controllers;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.caelum.vraptor.util.test.MockResult;
import br.com.gabriel.filme.models.Usuario;
import br.com.gabriel.filme.models.UsuarioSession;
import br.com.gabriel.filme.repositories.UsuarioRepository;

public class UsuarioControllerTest {
	
	@Mock
	private UsuarioRepository repository;
	@Mock
	private VotoController mockVotoController;
	@Mock
	private UsuarioSession usuarioSession;
	private MockResult result;
	private UsuarioController controller;
	private Usuario usuarioSoComId;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		
		result = spy(new MockResult());
		
		controller = new UsuarioController(result, repository, usuarioSession);
		
		criaUsuarioSoComId();
	}

	@Test 
	public void deveSalvarDadosDoUsuario() {
		controller.update(usuarioSoComId);
		
		verify(repository, never()).create(usuarioSoComId);
		verify(repository, only()).update(usuarioSoComId);
 	}
	
	@Test 
	public void deveTrazerUsuarioDaSessaoQuandoTrazerTelaDePreenchimentoDeDados() {
		when(usuarioSession.getUsuario()).thenReturn(usuarioSoComId);
		
		Usuario usuario = controller.newUsuario();
		
		Assert.assertEquals(usuarioSoComId, usuario);
	}
	
	private void criaUsuarioSoComId() {
		usuarioSoComId = new Usuario();
		usuarioSoComId.setId(1L);
	}

	@Test
	public void deveMostrarRankingAposSalvarDados(){
		when(result.redirectTo(VotoController.class)).thenReturn(mockVotoController);
		
		controller.update(any(Usuario.class));
		
		verify(mockVotoController).ranking();
	}
}
