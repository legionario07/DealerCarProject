package br.com.dealercar.domain;

public class ArCondicionado extends Opcional{

	public ArCondicionado() {

	}
	
	public ArCondicionado(String descricao, double valor) {
		super();
	}
	
	@Override
	public String toString() {
	
		StringBuffer retorno = new StringBuffer();
		
		super.toString();
		/*retorno.append("Código: ");
		retorno.append(this.getCodigo());
		retorno.append("\nDescrição: ");
		retorno.append(this.getDescricao());
		retorno.append("\nValor:: ");
		retorno.append(this.getValor());*/
		retorno.append("\n\n");
		
		return retorno.toString();
	}
}
