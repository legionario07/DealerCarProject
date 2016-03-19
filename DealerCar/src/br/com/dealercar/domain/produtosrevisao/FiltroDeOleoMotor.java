package br.com.dealercar.domain.produtosrevisao;

public class FiltroDeOleoMotor extends ProdutoRevisao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FiltroDeOleoMotor() {
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
			if (!(obj instanceof FiltroDeOleoMotor))
			return false;
			FiltroDeOleoMotor other = (FiltroDeOleoMotor) obj;
			if (getDescricao() == null){
			if (other.getDescricao() != null)
			return false;
			} else if (!getDescricao().equals(other.getDescricao()))
			return false;
			return true;
	}
	
}
