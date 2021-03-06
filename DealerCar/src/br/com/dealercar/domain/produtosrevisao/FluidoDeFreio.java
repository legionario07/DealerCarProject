package br.com.dealercar.domain.produtosrevisao;

public class FluidoDeFreio extends ProdutoRevisao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public FluidoDeFreio() {
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
			if (!(obj instanceof FluidoDeFreio))
			return false;
			FluidoDeFreio other = (FluidoDeFreio) obj;
			if (getDescricao() == null){
			if (other.getDescricao() != null)
			return false;
			} else if (!getDescricao().equals(other.getDescricao()))
			return false;
			return true;
	}
	
}
