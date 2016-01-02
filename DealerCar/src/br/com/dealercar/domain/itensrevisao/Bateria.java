package br.com.dealercar.domain.itensrevisao;

/**
 * Classe responsavel pela revisao da Bateria do Carro
 * @author Paulinho
 *
 */
public class Bateria extends Componentes{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public Bateria() {
	}
	
	public Bateria(String situacao){
		this.setSituacao(situacao);
	}
	

	@Override
	public String toString() {
		StringBuffer retorno = new StringBuffer();
		retorno.append("\nBateria: ");
		retorno.append(this.getSituacao());
				
		return retorno.toString();
		}

}
