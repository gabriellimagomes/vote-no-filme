package br.com.gabriel.filme.controllers;

import java.util.Map;
import java.util.Set;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.gabriel.filme.exception.VotoInvalidoException;
import br.com.gabriel.filme.models.Filme;
import br.com.gabriel.filme.models.Voto;
import br.com.gabriel.filme.repositories.VotoRepository;

@Resource
public class VotoController {

	private final Result result;
	private final VotoRepository repository;
	
	public VotoController(Result result, VotoRepository repository) {
		this.result = result;
		this.repository = repository;
	}
	
	/**@Get("/votos")
	public List<Voto> index() {
		return repository.findAll();
	}
	
	@Post("/votos")
	public void create(Voto voto) {
		validator.validate(voto);
		validator.onErrorUsePageOf(this).newVoto();
		repository.create(voto);
		result.redirectTo(this).index();
	}
	
	@Get("/votos/new")
	public Voto newVoto() {
		return new Voto();
	}
	
	@Put("/votos")
	public void update(Voto voto) {
		validator.validate(voto);
		validator.onErrorUsePageOf(this).edit(voto);
		repository.update(voto);
		result.redirectTo(this).index();
	}
	
	@Get("/votos/{voto.id}/edit")
	public Voto edit(Voto voto) {
		
		return repository.find(voto.getId());
	}

	@Get("/votos/{voto.id}")
	public Voto show(Voto voto) {
		return repository.find(voto.getId());
	}

	@Delete("/votos/{voto.id}")
	public void destroy(Voto voto) {
		repository.destroy(repository.find(voto.getId()));
		result.redirectTo(this).index();  
	}
	 * @param filmeVotado2 
	 * @param filmeB 
**/
	public void votar(Filme filmeA, Filme filmeB, Filme filmeVotado) {
		Voto voto = new Voto(filmeA, filmeB, filmeVotado);
		this.validaVoto(filmeA, filmeB, filmeVotado);
		
		repository.votar(voto);
		result.forwardTo(this).index();
	}

	private void validaVoto(Filme filmeA, Filme filmeB, Filme filmeVotado) {
		if (!filmeVotado.equals(filmeA) && !filmeVotado.equals(filmeB))
			throw new VotoInvalidoException("Voto inválido");
	}

	public Map<Filme, Integer> ranking() {
		return repository.ranking();
	}

	@Get("/")
	public void index() {
		Set<Filme> filmesParaDuelo = repository.getDoisFilmesAleatorios();
		
		int contFilme = 1;
		for (Filme filme : filmesParaDuelo) {
			result.include("filme".concat(String.valueOf(contFilme++)), filme);
		}
	}
}