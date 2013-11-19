package br.com.gabriel.filme.models;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.SessionScoped;
import br.com.gabriel.filme.repositories.UsuarioRepository;

@SessionScoped
@Component
public class UsuarioSession {
	
	private Usuario usuario;

	public UsuarioSession(UsuarioRepository usuarioRepository) {
		usuario = new Usuario();
		usuarioRepository.create(usuario);
	}

	public Usuario getUsuario() {
		return usuario;
	}

}
