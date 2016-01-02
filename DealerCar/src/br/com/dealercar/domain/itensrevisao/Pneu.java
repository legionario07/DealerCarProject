package br.com.dealercar.domain.itensrevisao;

import br.com.dealercar.enums.PosicaoPneu;

/**
 * Classe responsavel pela revisao do Pneu do Carro
 * @author Paulinho
 *
 */
public class Pneu extends Componentes{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PosicaoPneu posicaoPneu;
	
	public Pneu(){
	}
	
	public Pneu(String situacao){
		this.setSituacao(situacao);
	}
	
	public PosicaoPneu getPosicaoPneu() {
		return posicaoPneu;
	}

	public void setPosicaoPneu(PosicaoPneu posicaoPneu) {
		this.posicaoPneu = posicaoPneu;
	}


	@Override
	public String toString() {
		StringBuffer retorno = new StringBuffer();
		retorno.append("\nDescricao: ");
		retorno.append(this.getPosicaoPneu());
		retorno.append(" - ");
		retorno.append(this.getSituacao());
		return retorno.toString();
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((posicaoPneu == null) ? 0 : posicaoPneu.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pneu other = (Pneu) obj;
		if (posicaoPneu != other.posicaoPneu)
			return false;
		return true;
	}

}
