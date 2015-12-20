package br.com.dealercar.domain.itensrevisao;

public class Freio extends Componente{
	
	public Freio() {
	}
	
	public Freio(boolean ok) {
		this.setOk(ok);
	}

	@Override
	public String toString() {
		StringBuffer retorno = new StringBuffer();
		retorno.append("\nFreio: ");
		retorno.append(this.isOk());
		
		return retorno.toString();
	}

}
