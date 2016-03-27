package br.com.dealercar.core.teste.automotivos;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.dealercar.core.dao.automotivos.TaxasAdicionaisDAO;
import br.com.dealercar.domain.EntidadeDominio;
import br.com.dealercar.domain.taxasadicionais.TaxaLavagem;
import br.com.dealercar.domain.taxasadicionais.TaxasAdicionais;

public class TaxasAdicionaisDAOTest {

	public TaxasAdicionaisDAO taxasDAO = new TaxasAdicionaisDAO();

	@Test
	@Ignore
	public void cadastrar() {

		TaxasAdicionais taxa = new TaxasAdicionais();
		taxa.setDescricao("Combustivel");
		taxa.setValor(40.00d);

		taxasDAO.cadastrar(taxa);

	}

	@Test
	@Ignore
	public void editar() {

		TaxasAdicionais taxaAdicional = new TaxasAdicionais();

		taxaAdicional.setId(4);
		taxaAdicional.setDescricao("Combustivel");
		taxaAdicional.setValor(70.00d);
		taxasDAO.editar(taxaAdicional);
	}

	@Test
	@Ignore
	public void excluir() {

		TaxaLavagem taxaLavagem = new TaxaLavagem();

		taxaLavagem.setDescricao("teste");
		taxasDAO.excluir(taxaLavagem);
	}

	@Test
	@Ignore
	public void pesquisarPorID() {
		TaxasAdicionais taxaAdicional = new TaxasAdicionais();

		taxaAdicional.setId(4);
		taxaAdicional = taxasDAO.pesquisarPorID(taxaAdicional);
		System.out.println(taxaAdicional.getId());
		System.out.println(taxaAdicional.getDescricao());
		System.out.println(taxaAdicional.getValor());

	}

	@Test
	@Ignore
	public void pesquisarPorTaxa() {
		TaxasAdicionais taxaAdicional = new TaxasAdicionais();

		String nome = "Lavagem";
		
		taxaAdicional.setId(4);
		taxaAdicional = taxasDAO.pesquisarPorTaxa(nome);
		System.out.println(taxaAdicional.getId());
		System.out.println(taxaAdicional.getDescricao());
		System.out.println(taxaAdicional.getValor());

	}
	
	@Test
	@Ignore
	public void listarTodos() {

		List<EntidadeDominio> taxas = new ArrayList<EntidadeDominio>();

		taxas = taxasDAO.listarTodos();
		for (EntidadeDominio taxaAdicional : taxas) {
			System.out.println(taxaAdicional.getId());
			System.out.println(((TaxasAdicionais) taxaAdicional).getDescricao());
			System.out.println(((TaxasAdicionais) taxaAdicional).getValor());
		}

	}


}
