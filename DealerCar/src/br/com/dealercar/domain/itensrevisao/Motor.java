package br.com.dealercar.domain.itensrevisao;

/**
 * Classe responsavel pela revisao do Motor do Carro
 * @author Paulinho
 *
 */
public class Motor extends Componente{
	
	public Motor() {
		// TODO Auto-generated constructor stub
	}
	public Motor(boolean ok) {
		this.setOk(ok);
	}
	

	@Override
	public String toString() {
		StringBuffer retorno = new StringBuffer();
		retorno.append("\nMotor: ");
		retorno.append(this.isOk());
		
		return retorno.toString();
	}

}
