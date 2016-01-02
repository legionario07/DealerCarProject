package br.com.dealercar.domain.itensrevisao;

import java.util.ArrayList;
import java.util.List;

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
		retorno.append("Descricao: ");
		retorno.append(this.getPosicaoPneu().getDescricao());
		retorno.append(this.getSituacao());
		return retorno.toString();
	}

}
