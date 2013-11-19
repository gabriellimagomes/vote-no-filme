package br.com.gabriel.filme.controllers;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.gabriel.filme.models.Usuario;
import br.com.gabriel.filme.models.UsuarioSession;
import br.com.gabriel.filme.repositories.UsuarioRepository;

@Resource
public class UsuarioController {

	private final Result result;
	private final UsuarioRepository repository;
	
	private final UsuarioSession usuarioSession;
	
	public UsuarioController(Result result, UsuarioRepository repository, UsuarioSession usuarioSession) {
		this.result = result;
		this.repository = repository;
	
		this.usuarioSession = usuarioSession;
	}
	
	@Get("/usuarios/new")
	public Usuario newUsuario() {
		return usuarioSession.getUsuario();
	}
	
	@Put("/usuarios")
	public void update(Usuario usuario) {
		repository.update(usuario);
		result.redirectTo(VotoController.class).ranking();
	}
}