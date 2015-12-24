package br.com.dealercar.teste.domain;

import java.util.List;

import br.com.dealercar.dao.DevolucaoDAO;
import br.com.dealercar.dao.FuncionarioDAO;
import br.com.dealercar.dao.RetiradaDAO;
import br.com.dealercar.dao.automotivos.TaxaCombustivelDAO;
import br.com.dealercar.domain.Devolucao;
import br.com.dealercar.domain.Funcionario;
import br.com.dealercar.domain.Reserva;
import br.com.dealercar.domain.Retirada;
import br.com.dealercar.domain.taxasadicionais.TaxaCombustivel;
import br.com.dealercar.domain.taxasadicionais.TaxaLavagem;
import br.com.dealercar.domain.taxasadicionais.TaxasAdicionais;
import br.com.dealercar.util.DataUtil;

public class DevolucaoDAOTest {

	public static DevolucaoDAO devolucaoDAO = new DevolucaoDAO();

	/*
	@SuppressWarnings("unused")
	private static void cadastrar() {

		Devolucao devolucao = new Devolucao();

		Retirada retirada = new Retirada(9);
		retirada = new RetiradaDAO().pesquisarPorID(retirada);
		devolucao.setRetirada(retirada);

		Funcionario funcionario = new Funcionario(1);
		funcionario = new FuncionarioDAO().pesquisarPorID(funcionario);
		devolucao.setFuncionario(funcionario);

		Reserva reserva = new Reserva();
		devolucao.setReserva(reserva);

		TaxasAdicionais taxasAdicionais = new TaxasAdicionais();
		TaxaCombustivel c = new TaxaCombustivel();
		c.setId(1);
		c = new TaxaCombustivelDAO().pesquisarPorID(c);
		c.setFoiCobrado(true);

		System.out.println(c.isFoiCobrado());

		TaxaLavagem l = new TaxaLavagem();
		taxasAdicionais.setCombustivel(c);
		taxasAdicionais.setLavagem(l);

		devolucao.setTaxasAdicionais(taxasAdicionais);

		devolucao.setDataDevolucao(DataUtil.pegarDataAtualDoSistema());
		devolucao.setQuilometragem("199233");
		devolucao.setQtdeDiarias(DataUtil.devolverDataEmDias(devolucao.getRetirada().getDataRetirada()));
		devolucao.setValorFinal(devolucao.calcularValorFinal(retirada));

		devolucaoDAO.cadastrar(devolucao);

	}
	*/

	@SuppressWarnings("unused")
	private static void pesquisarPorID() {

		Devolucao devolucao = new Devolucao();

		devolucao.setId(12);

		devolucao = devolucaoDAO.pesquisarPorID(devolucao);

		System.out.println(devolucao);

	}

	@SuppressWarnings("unused")
	private static void listarTodos() {

		List<Devolucao> listaRetorno = devolucaoDAO.listarTodos();

		for (Devolucao d : listaRetorno) {
			System.out.println(d);
		}

	}

	private static void pesquisarPorCPF() {
		
		Devolucao devolucao = new Devolucao();

		Retirada retirada = new Retirada(3); 
		retirada = new RetiradaDAO().pesquisarPorID(retirada);
		
		devolucao.setRetirada(retirada);
		
		List<Devolucao> listaRetorno = devolucaoDAO.pesquisarPorCPF(devolucao);

		for(Devolucao d : listaRetorno){
			System.out.println(d);
		}
	}

	public static void main(String[] args) {

		 //cadastrar();
		//pesquisarPorID();
		 listarTodos();
		//pesquisarPorCPF();

	}
}
