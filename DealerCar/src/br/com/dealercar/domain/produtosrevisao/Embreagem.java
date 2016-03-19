package br.com.dealercar.domain.produtosrevisao;

public class Embreagem extends ProdutoRevisao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Embreagem() {
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
			if (!(obj instanceof Embreagem))
			return false;
			Embreagem other = (Embreagem) obj;
			if (getDescricao() == null){
			if (other.getDescricao() != null)
			return false;
			} else if (!getDescricao().equals(other.getDescricao()))
			return false;
			return true;
	}
}
