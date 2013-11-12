package br.com.gabriel.filme.controllers;

import java.util.List;

import br.com.gabriel.filme.models.Filme;
import br.com.gabriel.filme.repositories.FilmeRepository;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;

@Resource
public class FilmeController {

	private final Result result;
	private final FilmeRepository repository;
	
	private final Validator validator;
	
	public FilmeController(Result result, FilmeRepository repository, 
	Validator validator) {
		this.result = result;
		this.repository = repository;
	
		this.validator = validator;
	}
	
	@Get("/filmes")
	public List<Filme> index() {
		return repository.findAll();
	}
	
	@Post("/filmes")
	public void create(Filme filme) {
		validator.validate(filme);
		validator.onErrorUsePageOf(this).newFilme();
		repository.create(filme);
		result.redirectTo(this).index();
	}
	
	@Get("/filmes/new")
	public Filme newFilme() {
		return new Filme();
	}
	
	@Put("/filmes")
	public void update(Filme filme) {
		validator.validate(filme);
		validator.onErrorUsePageOf(this).edit(filme);
		repository.update(filme);
		result.redirectTo(this).index();
	}
	
	@Get("/filmes/{filme.id}/edit")
	public Filme edit(Filme filme) {
		
		return repository.find(filme.getId());
	}

	@Get("/filmes/{filme.id}")
	public Filme show(Filme filme) {
		return repository.find(filme.getId());
	}

	@Delete("/filmes/{filme.id}")
	public void destroy(Filme filme) {
		repository.destroy(repository.find(filme.getId()));
		result.redirectTo(this).index();  
	}
}