package br.com.dealercar.teste.automotivos;

import br.com.dealercar.dao.automotivos.TaxaLavagemDAO;
import br.com.dealercar.domain.taxasadicionais.TaxaLavagem;

public class TaxasAdicionaisDAOTest {

	@SuppressWarnings("unused")
	private static void cadastrar(){
		
		TaxaLavagem taxaLavagem = new TaxaLavagem();
		taxaLavagem.setValor(15.00);
		new TaxaLavagemDAO().cadastrar(taxaLavagem);
		
	}
	
	@SuppressWarnings("unused")
	private static void editar(){
		
		TaxaLavagem taxaLavagem = new TaxaLavagem();
		
		taxaLavagem.setId(1);
		taxaLavagem.setValor(40.00);
		new TaxaLavagemDAO().editar(taxaLavagem);
	}
	
	@SuppressWarnings("unused")
	private static void pesquisarPorID(){
		TaxaLavagem taxaLavagem = new TaxaLavagem();
		taxaLavagem.setId(1);
		taxaLavagem = new TaxaLavagemDAO().pesquisarPorID(taxaLavagem);
		
		System.out.println(taxaLavagem);
		System.out.println(taxaLavagem.getValor());
		
	}
	
	public static void main(String[] args) {

		//cadastrar();
		//editar();
		//pesquisarPorID();
	}

}
