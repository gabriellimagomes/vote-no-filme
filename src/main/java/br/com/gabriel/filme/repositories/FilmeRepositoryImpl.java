package br.com.gabriel.filme.repositories;

import java.util.List;

import org.hibernate.Query;
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Long> getIdsFilmes() {
		Query createQuery = session.createQuery("select f.id from Filme f");
		return createQuery.list();
	}
}
