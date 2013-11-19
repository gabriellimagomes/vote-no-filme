package br.com.gabriel.filme.repositories;

import java.util.List;

import br.com.gabriel.filme.models.Ranking;
import br.com.gabriel.filme.models.Usuario;
import br.com.gabriel.filme.models.Voto;

public interface VotoRepository {
	
	void votar(Voto voto);

	List<Ranking> rankingGeral();
	
	List<Ranking> rankingPorUsuario(Usuario usuario);

}
