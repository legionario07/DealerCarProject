package br.com.dealercar.domain.itensrevisao;

public class Embreagem extends Componente{
	
	public Embreagem() {
	}
	
	public Embreagem(boolean ok) {
		this.setOk(ok);
	}
	

	@Override
	public String toString() {
		StringBuffer retorno = new StringBuffer();
		retorno.append("\nEmbreagem: ");
		retorno.append(this.isOk());
		
		return retorno.toString();
	}

}
