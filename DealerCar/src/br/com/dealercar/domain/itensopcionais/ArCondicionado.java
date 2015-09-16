package br.com.dealercar.domain.itensopcionais;

public class ArCondicionado extends Opcional{

	public ArCondicionado(){

	}
	
	public ArCondicionado(String descricao, double valor) {
		super();
	}
	
	@Override
	public String toString() {
	
		StringBuffer retorno = new StringBuffer();
		
		super.toString();
		retorno.append("\n\n");
		
		return retorno.toString();
	}
}
