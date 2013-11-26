package br.com.gabriel.filme.models;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.SessionScoped;
import br.com.gabriel.filme.repositories.FilmeRepository;

@SessionScoped
@Component
public class DueloDisponivel implements Serializable {

	private static final int INDEX_FILME_1 = 0;
	private static final int INDEX_FILME_2 = 1;
	private final Set<Duelo> duelosDisponiveis;
	private final Iterator<Duelo> iteratorDuelos;
	
	public DueloDisponivel(FilmeRepository filmeRepository) {		
		List<Long> idsFilmes = filmeRepository.getIdsFilmes();
		
		duelosDisponiveis = new HashSet<Duelo>();
		
		Collections.shuffle(idsFilmes);
		
		Combinacao combinacao = new Combinacao(idsFilmes.toArray(new Long[idsFilmes.size()]), 2);
		Long[] idsDuelo;
		
		while (combinacao.hasNext()) {
			idsDuelo = combinacao.next();
			duelosDisponiveis.add(new Duelo(filmeRepository.find(idsDuelo[INDEX_FILME_1]), filmeRepository.find(idsDuelo[INDEX_FILME_2])));
		}
		
		iteratorDuelos = duelosDisponiveis.iterator();
	}

	public Duelo duelo() {
		return iteratorDuelos.next();
	}

	public boolean temDuelo() {
		return iteratorDuelos.hasNext();
	}

	public Set<Duelo> duelosDisponiveis() {
		return Collections.unmodifiableSet(duelosDisponiveis);
	}
}
