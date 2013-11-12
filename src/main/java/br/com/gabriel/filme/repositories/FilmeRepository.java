package br.com.gabriel.filme.repositories;

import java.util.List;

import br.com.gabriel.filme.models.Filme;

public interface FilmeRepository {
	/*
	 * Delete the methods you don't want to expose
	 */
	 
	void create(Filme entity);
	
	Filme update(Filme entity);
	
	void destroy(Filme entity);
	
	Filme find(Long id);
	
	List<Filme> findAll();

}
