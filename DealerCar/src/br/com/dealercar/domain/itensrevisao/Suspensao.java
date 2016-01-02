package br.com.dealercar.domain.itensrevisao;

/**
 * Classe responsavel pela revisao da Suspensão do Carro
 * @author Paulinho
 *
 */
public class Suspensao extends Componentes{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public Suspensao() {
	}
	
	public Suspensao(String situacao){
		this.setSituacao(situacao);
	}
	
	

	@Override
	public String toString() {
		StringBuffer retorno = new StringBuffer();
		retorno.append("\nSuspensao: ");
		retorno.append(this.getSituacao());
				
		return retorno.toString();
	}

}
