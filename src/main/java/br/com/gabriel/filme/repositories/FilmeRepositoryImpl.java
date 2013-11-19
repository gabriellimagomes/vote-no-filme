package br.com.gabriel.filme.repositories;

import java.util.HashSet;
import java.util.Set;

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
	public Set<Long> getIdsFilmes() {
		Query createQuery = session.createQuery("select f.id from Filme f");
		return new HashSet<Long>(createQuery.list());
	}
}
