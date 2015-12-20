package br.com.dealercar.domain.taxasadicionais;

public class TaxaCombustivel extends TaxasAdicionais {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		StringBuffer retorno = new StringBuffer();
		if(this.isFoiCobrado()==false){
			retorno.append("False");
			return retorno.toString();
		}
		
		retorno.append("\nDescrição: ");
		retorno.append(this.getDescricao());
		retorno.append("\nValor: ");
		retorno.append(this.getValor());
		
		return retorno.toString();
		
	}
}
