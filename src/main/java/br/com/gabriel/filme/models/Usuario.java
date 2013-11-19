package br.com.gabriel.filme.models;


@javax.persistence.Entity
public class Usuario extends Entity {

	private String nome;
	private String email;

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public String toString() {
		return "Usuario [nome=" + nome + ", email=" + email + ", getId()=" + getId() + "]";
	}

}
