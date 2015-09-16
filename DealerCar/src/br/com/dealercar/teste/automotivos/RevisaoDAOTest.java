package br.com.dealercar.teste.automotivos;

import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.domain.ItemParaVerificar;
import br.com.dealercar.domain.Revisao;
import br.com.dealercar.domain.automotivos.Carro;
import br.com.dealercar.domain.itensrevisao.Pneu;

public class RevisaoDAOTest {

	public static void main(String[] args) {
		Revisao revisao = new Revisao();
		
		List<ItemParaVerificar> itens = new ArrayList<ItemParaVerificar>();

		Pneu pneu = new Pneu("Pneu");
		List<String> posicoes= new ArrayList<String>();
		posicoes.add("dianteiro esquerdo");
		posicoes.add("Traseiro direito");
		
		pneu.setPosicaoPneu(posicoes);
		
		itens.add(pneu);
		
		revisao.setItensParaVerificar(itens);
		
		Carro carro = new Carro();
		carro.setPlaca("uyy-9881");
		
		revisao.setCarro(carro);
		System.out.println(revisao);
		
		
	}

}
