package br.com.dealercar.domain.itensrevisao;
/**
 * Classe responsavel pela revisao da Temperatura do Carro
 * @author Paulinho
 *
 */
public class Arrefecimento extends Componentes{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public Arrefecimento() {
	}
	
	public Arrefecimento(String situacao) {
		
		this.setSituacao(situacao);
	}
	

	@Override
	public String toString() {
		StringBuffer retorno = new StringBuffer();
		retorno.append("\nArrefecimento: ");
		retorno.append(this.getSituacao());
		
		return retorno.toString();
	}
}
