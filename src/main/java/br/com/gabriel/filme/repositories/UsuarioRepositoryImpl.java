package br.com.gabriel.filme.repositories;

import org.hibernate.Session;

import br.com.caelum.vraptor.ioc.Component;
import br.com.gabriel.filme.models.Usuario;

@Component
public class UsuarioRepositoryImpl
    extends Repository<Usuario, Long>
    implements UsuarioRepository {

	UsuarioRepositoryImpl(Session session) {
		super(session);
	}
}
