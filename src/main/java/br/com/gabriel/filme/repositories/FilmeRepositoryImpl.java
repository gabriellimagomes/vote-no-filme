package br.com.gabriel.filme.repositories;

import org.hibernate.Session;

import br.com.caelum.vraptor.ioc.Component;
import br.com.gabriel.filme.models.Filme;

@Component
public class FilmeRepositoryImpl
    extends Repository<Filme, Long>
    implements FilmeRepository {

	FilmeRepositoryImpl(Session session) {
		super(session);
	}
}
