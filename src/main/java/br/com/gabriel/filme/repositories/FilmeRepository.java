package br.com.gabriel.filme.repositories;

import java.util.List;

import br.com.gabriel.filme.models.Filme;

public interface FilmeRepository {
	
	Filme find(Long id);
	
	List<Long> getIdsFilmes();

}
