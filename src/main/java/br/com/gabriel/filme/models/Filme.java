package br.com.gabriel.filme.models;

@javax.persistence.Entity
public class Filme extends Entity {

	private String nome;

	public Filme(Long id, String nome) {
		this.setId(id);
		this.nome = nome;
	}

	@Deprecated //Hibernate eyes only
	public Filme() {}

	public Filme(Long id) {
		this.setId(id);
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	@Override
	public String toString() {
		return "Filme [id= "+ getId() + ", nome= "+ nome + "]";
	}
	
}
