package br.com.dealercar.domain.itensrevisao;

/**
 * Classe responsavel pela revisao do Freio do Carro
 * @author Paulinho
 *
 */
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
