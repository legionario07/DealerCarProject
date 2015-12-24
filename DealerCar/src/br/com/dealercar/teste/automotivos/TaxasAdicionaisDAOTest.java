package br.com.dealercar.teste.automotivos;

import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.dao.automotivos.TaxasAdicionaisDAO;
import br.com.dealercar.domain.taxasadicionais.TaxaLavagem;
import br.com.dealercar.domain.taxasadicionais.TaxasAdicionais;

public class TaxasAdicionaisDAOTest {

	private static TaxasAdicionaisDAO taxasDAO = new TaxasAdicionaisDAO();

	@SuppressWarnings("unused")
	private static void cadastrar() {

		TaxasAdicionais taxa = new TaxasAdicionais();
		taxa.setDescricao("Combustivel");
		taxa.setValor(40.00d);

		taxasDAO.cadastrar(taxa);

	}

	@SuppressWarnings("unused")
	private static void editar() {

		TaxasAdicionais taxaAdicional = new TaxasAdicionais();

		taxaAdicional.setId(4);
		taxaAdicional.setDescricao("Combustivel");
		taxaAdicional.setValor(70.00d);
		taxasDAO.editar(taxaAdicional);
	}

	@SuppressWarnings("unused")
	private static void excluir() {

		TaxaLavagem taxaLavagem = new TaxaLavagem();

		taxaLavagem.setDescricao("teste");
		taxasDAO.excluir(taxaLavagem);
	}

	@SuppressWarnings("unused")
	private static void pesquisarPorID() {
		TaxasAdicionais taxaAdicional = new TaxasAdicionais();

		taxaAdicional.setId(4);
		taxaAdicional = taxasDAO.pesquisarPorID(taxaAdicional);
		System.out.println(taxaAdicional.getId());
		System.out.println(taxaAdicional.getDescricao());
		System.out.println(taxaAdicional.getValor());

	}

	@SuppressWarnings("unused")
	private static void pesquisarPorTaxa() {
		TaxasAdicionais taxaAdicional = new TaxasAdicionais();

		String nome = "Lavagem";
		
		taxaAdicional.setId(4);
		taxaAdicional = taxasDAO.pesquisarPorTaxa(nome);
		System.out.println(taxaAdicional.getId());
		System.out.println(taxaAdicional.getDescricao());
		System.out.println(taxaAdicional.getValor());

	}
	
	@SuppressWarnings("unused")
	private static void listarTodos() {

		List<TaxasAdicionais> taxas = new ArrayList<TaxasAdicionais>();

		taxas = taxasDAO.listarTodos();
		for (TaxasAdicionais taxaAdicional : taxas) {
			System.out.println(taxaAdicional.getId());
			System.out.println(taxaAdicional.getDescricao());
			System.out.println(taxaAdicional.getValor());
		}

	}

	public static void main(String[] args) {

		// cadastrar();
		// editar();
		// excluir();
		// pesquisarPorID();
		//listarTodos();
		pesquisarPorTaxa();
	}

}
