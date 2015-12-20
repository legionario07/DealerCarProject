package br.com.dealercar.domain.itensrevisao;

public class Suspensao extends Componente{
	
	public Suspensao() {
		// TODO Auto-generated constructor stub
	}
	
	public Suspensao(boolean ok) {
		this.setOk(ok);
	}
	

	@Override
	public String toString() {
		StringBuffer retorno = new StringBuffer();
		retorno.append("Suspensao: ");
		retorno.append(this.isOk());
		
		return retorno.toString();
	}

}
