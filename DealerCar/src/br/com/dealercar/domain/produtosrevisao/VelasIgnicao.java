package br.com.dealercar.domain.produtosrevisao;

public class VelasIgnicao extends ProdutoRevisao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public VelasIgnicao() {
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
			if (!(obj instanceof VelasIgnicao))
			return false;
			VelasIgnicao other = (VelasIgnicao) obj;
			if (getDescricao() == null){
			if (other.getDescricao() != null)
			return false;
			} else if (!getDescricao().equals(other.getDescricao()))
			return false;
			return true;
	}
	
}
