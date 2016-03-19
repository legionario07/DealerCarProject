package br.com.dealercar.domain.produtosrevisao;

public class Farol extends ProdutoRevisao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Farol() {
		super();
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
			if (obj == null)
			return false;
			if (!(obj instanceof Farol))
			return false;
			Farol other = (Farol) obj;
			if (getDescricao() == null){
			if (other.getDescricao() != null)
			return false;
			} else if (!getDescricao().equals(other.getDescricao()))
			return false;
			return true;
	}
	
}
