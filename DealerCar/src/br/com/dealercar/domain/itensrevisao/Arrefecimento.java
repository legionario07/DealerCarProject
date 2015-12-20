package br.com.dealercar.domain.itensrevisao;

public class Arrefecimento extends Componente{

	public Arrefecimento() {
	}
	
	public Arrefecimento(boolean ok) {
		this.setOk(ok);
	}
	

	@Override
	public String toString() {
		StringBuffer retorno = new StringBuffer();
		retorno.append("\nArrefecimento: ");
		retorno.append(this.isOk());
		
		return retorno.toString();
	}
}
