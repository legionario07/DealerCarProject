package br.com.dealercar.core.teste.conducao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.dealercar.core.autenticacao.Funcionario;
import br.com.dealercar.core.dao.RetiradaDAO;
import br.com.dealercar.domain.Cliente;
import br.com.dealercar.domain.EntidadeDominio;
import br.com.dealercar.domain.automotivos.Carro;
import br.com.dealercar.domain.conducao.Retirada;
import br.com.dealercar.domain.itensopcionais.Opcional;

public class RetiradaDAOTest {

	@Test
	@Ignore
	public void cadastrar() {

		Retirada retirada = new Retirada();
		retirada.setCarro(new Carro("BBB-9999"));
		retirada.setCliente(new Cliente(18));
		retirada.setFuncionario(new Funcionario(3));
		retirada.setDataRetirada(new Date(14 / 11 / 2015));
		retirada.setDataDevolucao(new Date(30 / 11 / 2015));
		retirada.setOpcional(new Opcional(1));
		retirada.setQuilometragem("199911");

		RetiradaDAO dao = new RetiradaDAO();

		dao.cadastrar(retirada);

	}

	@Test
	@Ignore
	public void listarTodos() {

		List<EntidadeDominio> lista = new ArrayList<EntidadeDominio>();

		lista = new RetiradaDAO().listarTodos();

		for (EntidadeDominio r : lista) {
			System.out.println(r);
		}
	}

	@Test
	@Ignore
	public void pesquisarPorId() {

		Retirada retirada = new Retirada(1);

		retirada = new RetiradaDAO().pesquisarPorID(retirada);

		System.out.println(retirada);

	}

	@Test
	@Ignore
	public void pesquisarPorPlaca() {

		Carro carro = new Carro();
		carro.setPlaca("FFF-3333");

		List<Retirada> lista = new ArrayList<Retirada>();
		lista = new RetiradaDAO().pesquisarPorPlaca(carro);

		for (Retirada retirada : lista) {
			System.out.println(retirada);
		}

	}

	@Test
	@Ignore
	public void pesquisarRetiradaAtivaPorCPF() {

		Cliente cliente = new Cliente();
		cliente.setCPF("369.429.508-90");

		List<Retirada> lista = new ArrayList<Retirada>();
		lista = new RetiradaDAO().pesquisarRetiradaAtivaPorCPF(cliente);

		for (Retirada retirada : lista) {
			System.out.println(retirada);
		}

		System.out.println(lista.size());
	}

	@Test
	@Ignore
	public void pesquisarPorCPF() {

		Cliente cliente = new Cliente();
		cliente.setCPF("369.429.508-90");

		Retirada retirada = new Retirada();
		retirada = (Retirada) new RetiradaDAO().pesquisarPorCPF(cliente);

		System.out.println(retirada);

	}

	@Test
	@Ignore
	public void setarDataDeCadastro() {
		Date dataRetirada = null;
		// Setando a Data inicio como a data do Cadastro
		Date data = Calendar.getInstance().getTime();
		SimpleDateFormat stf = new SimpleDateFormat("dd/MM/yyyy");
		String dataInicio = stf.format(data);

		try {
			dataRetirada = stf.parse(dataInicio);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println(stf.format(dataRetirada));
	}

	@Test
	public void pesquisarPorIntervalo() {

		Retirada retirada = new Retirada();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String dataRetirada = "01/01/2016";
		String dataDevolucao = "31/01/2016";
		try {
			retirada.setDataRetirada(sdf.parse(dataRetirada));
			retirada.setDataDevolucao(sdf.parse(dataDevolucao));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		List<EntidadeDominio> lista = new ArrayList<EntidadeDominio>();
		lista = new RetiradaDAO().pesquisarPorIntervaloData(retirada);

		for (EntidadeDominio r : lista) {
			System.out.println(((Retirada) r).getDataRetirada());
		}

	}

	@Test
	@Ignore
	public void pesquisarPorUltimo() {
		int i = new RetiradaDAO().pesquisarPorUltimoID();
		System.out.println(i);
	}

}
