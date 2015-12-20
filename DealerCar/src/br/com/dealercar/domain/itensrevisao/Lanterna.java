package br.com.dealercar.domain.itensrevisao;

public class Lanterna extends Componente{

	public Lanterna() {
		// TODO Auto-generated constructor stub
	}
	public Lanterna(boolean ok) {
		this.setOk(ok);
	}
	

	@Override
	public String toString() {
		StringBuffer retorno = new StringBuffer();
		retorno.append("\nLanterna: ");
		retorno.append(this.isOk());
		
		return retorno.toString();
	}
}
