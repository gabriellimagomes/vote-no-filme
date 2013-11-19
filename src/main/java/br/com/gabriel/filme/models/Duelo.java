package br.com.gabriel.filme.models;

public class Duelo {

	private Filme filme1;
	private Filme filme2;
	
	public Duelo(Filme filme1, Filme filme2) {
		this.filme1 = filme1;
		this.filme2 = filme2;
	}

	public Filme getFilme1() {
		return filme1;
	}

	public Filme getFilme2() {
		return filme2;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((filme1 == null) ? 0 : filme1.hashCode());
		result = prime * result + ((filme2 == null) ? 0 : filme2.hashCode());
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
		Duelo other = (Duelo) obj;
		if (filme1 == null) {
			if (other.filme1 != null)
				return false;
		} else if (!filme1.equals(other.filme1))
			return false;
		if (filme2 == null) {
			if (other.filme2 != null)
				return false;
		} else if (!filme2.equals(other.filme2))
			return false;
		return true;
	}

	
}
