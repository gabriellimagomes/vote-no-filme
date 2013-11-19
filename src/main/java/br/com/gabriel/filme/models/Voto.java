package br.com.gabriel.filme.models;

import javax.persistence.ManyToOne;

@javax.persistence.Entity
public class Voto extends Entity {
	
	@ManyToOne
	private final Filme filmeVotado;
	@ManyToOne
	private final Filme filme1;
	@ManyToOne
	private final Filme filme2;
	@ManyToOne
	private final Usuario usuario;

	public Voto(Duelo duelo, Filme filmeVotado, Usuario usuario) {
		this.filme1 = duelo.getFilme1();
		this.filme2 = duelo.getFilme2();
		this.filmeVotado = filmeVotado;
		this.usuario = usuario;
	}

	public Filme getFilmeVotado() {
		return filmeVotado;
	}

	public Filme getFilme1() {
		return filme1;
	}

	public Filme getFilme2() {
		return filme2;
	}

	public Usuario getUsuario() {
		return usuario;
	}

}
