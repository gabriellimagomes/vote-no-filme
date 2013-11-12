package br.com.gabriel.filme.models;

import javax.persistence.ManyToOne;

@javax.persistence.Entity
public class Voto extends Entity {
	
	@ManyToOne
	private final Filme filmeVotado;
	@ManyToOne
	private final Filme filmeA;
	@ManyToOne
	private final Filme filmeB;

	public Voto(Filme filmeA, Filme filmeB, Filme filmeVotado) {
		this.filmeA = filmeA;
		this.filmeB = filmeB;
		this.filmeVotado = filmeVotado;
	}

}
