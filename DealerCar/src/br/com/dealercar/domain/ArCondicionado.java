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
		/*retorno.append("C�digo: ");
		retorno.append(this.getCodigo());
		retorno.append("\nDescri��o: ");
		retorno.append(this.getDescricao());
		retorno.append("\nValor:: ");
		retorno.append(this.getValor());*/
		retorno.append("\n\n");
		
		return retorno.toString();
	}
}
