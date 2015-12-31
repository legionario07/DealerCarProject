package br.com.dealercar.domain.itensrevisao;
/**
 * Classe responsavel pela revisao da Temperatura do Carro
 * @author Paulinho
 *
 */
public class Arrefecimento extends Componentes{

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
