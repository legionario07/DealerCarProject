package br.com.dealercar.domain.itensrevisao;

/**
 * Classe responsavel pela revisao da Embreagem do Carro
 * @author Paulinho
 *
 */
public class Embreagem extends Componentes{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public Embreagem() {
	}
	
	public Embreagem(String situacao){
		this.setSituacao(situacao);
	}
	

	@Override
	public String toString() {
		StringBuffer retorno = new StringBuffer();
		retorno.append("\nEmbreagem: ");
		retorno.append(this.getSituacao());
				
		return retorno.toString();
	}

}
