package br.com.dealercar.domain.itensrevisao;

public class Bateria extends Componente{

	public Bateria() {
	}
	
	public Bateria(boolean ok) {
		this.setOk(ok);
	}
	

	@Override
	public String toString() {
		StringBuffer retorno = new StringBuffer();
		retorno.append("\nBateria: ");
		retorno.append(this.isOk());
		
		return retorno.toString();
		}

}
