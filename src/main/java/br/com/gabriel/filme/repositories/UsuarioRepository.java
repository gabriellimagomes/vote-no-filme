package br.com.gabriel.filme.repositories;

import br.com.gabriel.filme.models.Usuario;

public interface UsuarioRepository {
	 
	void create(Usuario entity);
	
	Usuario update(Usuario entity);
	
}
