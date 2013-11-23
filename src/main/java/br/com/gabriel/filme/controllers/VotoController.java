package br.com.gabriel.filme.controllers;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.gabriel.filme.exception.VotoInvalidoException;
import br.com.gabriel.filme.models.Duelo;
import br.com.gabriel.filme.models.DueloDisponivel;
import br.com.gabriel.filme.models.Filme;
import br.com.gabriel.filme.models.UsuarioSession;
import br.com.gabriel.filme.models.Voto;
import br.com.gabriel.filme.repositories.VotoRepository;

@Resource
public class VotoController {

	private final Result result;
	private final VotoRepository repository;
	private final DueloDisponivel dueloDisponivel;
	private UsuarioSession usuarioSession;
	
	public VotoController(Result result, VotoRepository repository, DueloDisponivel dueloDisponivel, UsuarioSession usuarioSession) {
		this.result = result;
		this.repository = repository;
		this.dueloDisponivel = dueloDisponivel;
		this.usuarioSession = usuarioSession;
	}
	

	@Get
	@Path(value = { "/vote-no-filme", "/" })
	public void index() {
		Duelo duelo = dueloDisponivel.duelo();
		
		result.include("filme1", duelo.getFilme1());
		result.include("filme2", duelo.getFilme2());
	}

	@Post("/voto")
	public void votar(Duelo duelo, Filme filmeVotado) {
		
		Voto voto = new Voto(duelo, filmeVotado, usuarioSession.getUsuario());
		this.validaVoto(duelo, filmeVotado);
		
		
		repository.votar(voto);
		
		if (!dueloDisponivel.temDuelo()){
			result.redirectTo(UsuarioController.class).newUsuario();
		}else{
			result.redirectTo(this).index();
		}
	}
	
	public void ranking() {
		
		result.include("rankingUsuario", repository.rankingPorUsuario(usuarioSession.getUsuario()));
		result.include("rankingGeral", repository.rankingGeral());
	}
	
	private void validaVoto(Duelo duelo, Filme filmeVotado) {
		if (!filmeVotado.equals(duelo.getFilme1()) && !filmeVotado.equals(duelo.getFilme2()))
			throw new VotoInvalidoException("Voto inválido");
	}
}