package br.com.gabriel.filme.models;

public class Ranking {

	private final Filme filme;
	private final Long quantidade;
	
	public Ranking(Filme filme, Long quantidade) {		
		this.filme = filme;
		this.quantidade = quantidade;
	}

	public Long getQuantidade() {
		return quantidade;
	}

	public Filme getFilme() {
		return filme;
	}

	@Override
	public String toString() {
		return "Ranking [filme=" + filme.getNome() + ", quantidade=" + quantidade + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((filme == null) ? 0 : filme.hashCode());
		result = prime * result
				+ ((quantidade == null) ? 0 : quantidade.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ranking other = (Ranking) obj;
		if (filme == null) {
			if (other.filme != null)
				return false;
		} else if (!filme.equals(other.filme))
			return false;
		if (quantidade == null) {
			if (other.quantidade != null)
				return false;
		} else if (!quantidade.equals(other.quantidade))
			return false;
		return true;
	}

}
