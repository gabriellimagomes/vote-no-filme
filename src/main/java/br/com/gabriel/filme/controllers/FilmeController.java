package br.com.gabriel.filme.controllers;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.gabriel.filme.models.Filme;
import br.com.gabriel.filme.repositories.FilmeRepository;

@Resource
public class FilmeController {

	private final Result result;
	private final FilmeRepository repository;
	
	private final Validator validator;
	
	public FilmeController(Result result, FilmeRepository repository, Validator validator) {
		this.result = result;
		this.repository = repository;
	
		this.validator = validator;
	}
	
	@Post("/filmes")
	public void create(Filme filme) {
		validator.validate(filme);
		validator.onErrorUsePageOf(this).newFilme();
		repository.create(filme);
		result.redirectTo(this).newFilme();
	}
	
	@Get("/filmes/new")
	public Filme newFilme() {
		return new Filme();
	}
}