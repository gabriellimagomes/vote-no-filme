package br.com.gabriel.filme.repositories;

import java.util.Map;
import java.util.Set;

import br.com.gabriel.filme.models.Filme;
import br.com.gabriel.filme.models.Voto;

public interface VotoRepository {
	/*
	 * Delete the methods you don't want to expose
	 */
	 
	/**void create(Voto entity);
	
	Voto update(Voto entity);
	
	void destroy(Voto entity);
	
	Voto find(Long id);
	
	List<Voto> findAll();
**/
	void votar(Voto voto);

	Map<Filme, Integer> ranking();

	Set<Filme> getDoisFilmesAleatorios();

}
