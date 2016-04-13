package br.com.dealercar.core.teste.negocio;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.dealercar.core.autenticacao.Funcionario;
import br.com.dealercar.core.dao.DevolucaoDAO;
import br.com.dealercar.core.dao.FuncionarioDAO;
import br.com.dealercar.core.dao.RetiradaDAO;
import br.com.dealercar.core.negocio.Devolucao;
import br.com.dealercar.core.negocio.Reserva;
import br.com.dealercar.core.negocio.Retirada;
import br.com.dealercar.core.util.DataUtil;
import br.com.dealercar.domain.EntidadeDominio;
import br.com.dealercar.domain.taxasadicionais.TaxaLavagem;
import br.com.dealercar.domain.taxasadicionais.TaxasAdicionais;

public class DevolucaoDAOTest {

	public static DevolucaoDAO devolucaoDAO = new DevolucaoDAO();

	@Test
	@Ignore
	public void cadastrar() {

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
		

		TaxaLavagem l = new TaxaLavagem();
		taxasAdicionais.setLavagem(l);

		//devolucao.setTaxasAdicionais((List<TaxasAdicionais>) taxasAdicionais);

		devolucao.setDataDevolucao(DataUtil.pegarDataAtualDoSistema());
		devolucao.setQuilometragem("199233");
		devolucao.setQtdeDiarias(DataUtil.devolverDataEmDias(devolucao.getRetirada().getDataRetirada()));
		//devolucao.setValorFinal(devolucao.calcularValorFinal(retirada));

		devolucaoDAO.cadastrar(devolucao);

	}

	@Test
	@Ignore
	public void pesquisarPorID() {

		Devolucao devolucao = new Devolucao();

		devolucao.setId(12);

		devolucao = devolucaoDAO.pesquisarPorID(devolucao);

		System.out.println(devolucao);

	}

	@Test
	@Ignore
	public void listarTodos() {

		List<EntidadeDominio> listaRetorno = devolucaoDAO.listarTodos();

		for (EntidadeDominio d : listaRetorno) {
			System.out.println(d);
		}

	}

	@Test
	@Ignore
	public void pesquisarPorCPF() {
		
		Devolucao devolucao = new Devolucao();

		Retirada retirada = new Retirada(3); 
		retirada = new RetiradaDAO().pesquisarPorID(retirada);
		
		devolucao.setRetirada(retirada);
		
		List<EntidadeDominio> listaRetorno = devolucaoDAO.pesquisarPorCPF(devolucao);

		for(EntidadeDominio d : listaRetorno){
			System.out.println(d);
		}
	}

}
