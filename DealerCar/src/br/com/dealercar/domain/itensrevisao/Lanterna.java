package br.com.dealercar.domain.itensrevisao;

/**
 * Classe responsavel pela revisao da Lanterna do Carro
 * @author Paulinho
 *
 */
public class Lanterna extends Componentes{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public Lanterna() {
	}
	public Lanterna(String situacao){
		this.setSituacao(situacao);
	}

	@Override
	public String toString() {
		StringBuffer retorno = new StringBuffer();
		retorno.append("\nLanterna: ");
		retorno.append(this.getSituacao());
				
		return retorno.toString();
	}
}
