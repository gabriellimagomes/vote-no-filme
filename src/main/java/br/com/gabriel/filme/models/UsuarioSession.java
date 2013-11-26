package br.com.gabriel.filme.models;

import java.io.Serializable;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.SessionScoped;
import br.com.gabriel.filme.repositories.UsuarioRepository;

@SessionScoped
@Component
public class UsuarioSession implements Serializable {
	
	private Usuario usuario;

	public UsuarioSession(UsuarioRepository usuarioRepository) {
		usuario = new Usuario();
		usuarioRepository.create(usuario);
	}

	public Usuario getUsuario() {
		return usuario;
	}

}
