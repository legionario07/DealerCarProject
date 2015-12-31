package br.com.dealercar.domain.itensrevisao;

/**
 * Classe responsavel pela revisao da Bateria do Carro
 * @author Paulinho
 *
 */
public class Bateria extends Componentes{

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
