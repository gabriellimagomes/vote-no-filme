package br.com.gabriel.filme.repositories;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.hibernate.Session;

import br.com.caelum.vraptor.ioc.Component;
import br.com.gabriel.filme.models.Filme;
import br.com.gabriel.filme.models.Voto;

@Component
public class VotoRepositoryImpl
    extends Repository<Voto, Long>
    implements VotoRepository {

	VotoRepositoryImpl(Session session) {
		super(session);
	}

	@Override
	public void votar(Voto voto) {
		session.save(voto);
	}

	@Override
	public Map<Filme, Integer> ranking() {
		return null;
	}

	@Override
	public Set<Filme> getDoisFilmesAleatorios() {
		Set<Filme> doisFilmesParaDuelo = new HashSet<Filme>();
		doisFilmesParaDuelo.add(new Filme(1L, "Filme A"));
		doisFilmesParaDuelo.add(new Filme(2L, "Filme B"));
		
		return doisFilmesParaDuelo;
	}
}
