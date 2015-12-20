package br.com.dealercar.domain.itensrevisao;

import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.enums.PosicaoPneu;

public class Pneu extends Componente{
	
	private PosicaoPneu[] posicaoPneu = PosicaoPneu.values();
	
	public Pneu(){
	}
	
	public Pneu(boolean ok) {
		this.setOk(ok);
	}
	
	public PosicaoPneu[] getPosicaoPneu() {
		return posicaoPneu;
	}

	public void setPosicaoPneu(PosicaoPneu[] posicaoPneu) {
		this.posicaoPneu = posicaoPneu;
	}

	public List<String> getPosicoesPneu(){
		
		PosicaoPneu[] posicoes = PosicaoPneu.values();
		
		List<String> listaPosicoes = new ArrayList<String>();
		
		for(PosicaoPneu p : posicoes){
			listaPosicoes.add(p.getDescricao());
		}
		
		return listaPosicoes;
		
	}

	@Override
	public String toString() {
		StringBuffer retorno = new StringBuffer();
		retorno.append("Descricao: ");
		for(PosicaoPneu p : posicaoPneu) {
			retorno.append(p);
		}
		return retorno.toString();
	}

}
