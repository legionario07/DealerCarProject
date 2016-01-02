package br.com.dealercar.domain.itensrevisao;

/**
 * Classe responsavel pela revisao do Motor do Carro
 * @author Paulinho
 *
 */
public class Motor extends Componentes{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public Motor() {
	}
	
	public Motor(String situacao){
		this.setSituacao(situacao);
	}
	

	@Override
	public String toString() {
		StringBuffer retorno = new StringBuffer();
		retorno.append("\nMotor: ");
		retorno.append(this.getSituacao());
				
		return retorno.toString();
	}

}
