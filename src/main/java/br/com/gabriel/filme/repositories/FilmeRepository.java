package br.com.gabriel.filme.repositories;

import java.util.Set;

import br.com.gabriel.filme.models.Filme;

public interface FilmeRepository {
	
	Filme find(Long id);
	
	Set<Long> getIdsFilmes();

}
