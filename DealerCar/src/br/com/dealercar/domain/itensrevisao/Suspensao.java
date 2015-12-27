package br.com.dealercar.domain.itensrevisao;

/**
 * Classe responsavel pela revisao da Suspensão do Carro
 * @author Paulinho
 *
 */
public class Suspensao extends Componente{
	
	public Suspensao() {
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
