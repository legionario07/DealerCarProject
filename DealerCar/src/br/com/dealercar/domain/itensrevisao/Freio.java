package br.com.dealercar.domain.itensrevisao;

/**
 * Classe responsavel pela revisao do Freio do Carro
 * @author Paulinho
 *
 */
public class Freio extends Componentes{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Freio() {
	}
	
	public Freio(String situacao){
		this.setSituacao(situacao);
	}
	

	@Override
	public String toString() {
		StringBuffer retorno = new StringBuffer();
		retorno.append("\nFreio: ");
		retorno.append(this.getSituacao());
		
		return retorno.toString();
	}

}
