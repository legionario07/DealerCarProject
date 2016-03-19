package br.com.dealercar.domain.produtosrevisao;

public class Pneu extends ProdutoRevisao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Pneu() {
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
			if (!(obj instanceof Pneu))
			return false;
			Pneu other = (Pneu) obj;
			if (getDescricao() == null){
			if (other.getDescricao() != null)
			return false;
			} else if (!getDescricao().equals(other.getDescricao()))
			return false;
			return true;
	}
	
}
