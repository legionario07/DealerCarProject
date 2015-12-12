package br.com.dealercar.domain.itensrevisao;

import java.util.ArrayList;
import java.util.List;

public class Pneu extends Componente{
	
	private List<String> posicaoPneu = new ArrayList<String>();
	
	public Pneu(){
		
	}
	/**
	 * 
	 * @param id
	 */
	public Pneu(int id) {
		super(id);
	}
	/**
	 * 
	 * @param descricao
	 */
	public Pneu(String descricao) {
		super(descricao);
	}
	/**
	 * 
	 * @param id
	 * @param descricao
	 */
	public Pneu(int id, String descricao, List<Pneu> pneus) {
		super(id, descricao);
		this.setPosicaoPneu(posicaoPneu);
	}

	public List<String> getPosicaoPneu() {
		return posicaoPneu;
	}
	public void setPosicaoPneu(List<String> posicaoPneu) {
		this.posicaoPneu = posicaoPneu;
	}
	
	@Override
	public String toString() {
		StringBuffer retorno = new StringBuffer();
		retorno.append("Descricao: ");
		for(String p : posicaoPneu) {
			retorno.append(p);
		}
		return retorno.toString();
	}

}
