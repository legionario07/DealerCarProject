package br.com.dealercar.core.teste.negocio;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.dealercar.core.autenticacao.Funcionario;
import br.com.dealercar.core.dao.ReservaDAO;
import br.com.dealercar.core.negocio.Reserva;
import br.com.dealercar.domain.Cliente;
import br.com.dealercar.domain.EntidadeDominio;
import br.com.dealercar.domain.automotivos.Modelo;
import br.com.dealercar.domain.enums.SituacaoReserva;

public class ReservaDAOTest {

	@Test
	@Ignore
	public void cadastrar() {

		Reserva reserva = new Reserva(new Modelo(6), new Cliente(17), new Funcionario(1));

		ReservaDAO reservaDao = new ReservaDAO();
		reservaDao.cadastrar(reserva);

	}

	@Test
	@Ignore
	public void editar() {

		Reserva reserva = new Reserva(new Modelo(4), new Cliente(18), new Funcionario(1));
		reserva.setId(5);
		reserva.setSituacao(SituacaoReserva.CANCELADO);

		ReservaDAO reservaDao = new ReservaDAO();
		reservaDao.editar(reserva);

	}

	@Test
	@Ignore
	public void excluir() {
		Reserva reserva = new Reserva(5);

		ReservaDAO reservaDao = new ReservaDAO();
		reservaDao.excluir(reserva);
	}

	@Test
	@Ignore
	public void pesquisarPorID() {
		Reserva reserva = new Reserva(2);

		ReservaDAO reservaDao = new ReservaDAO();
		System.out.println(reservaDao.pesquisarPorID(reserva));
	}

	@Test
	@Ignore
	public void pesquisarPorCPF() {

		Cliente cliente = new Cliente();
		cliente.setCPF("369.429.508-90");

		Reserva reserva = new Reserva();
		reserva.setCliente(cliente);

		ReservaDAO reservaDao = new ReservaDAO();

		List<EntidadeDominio> lista = new ArrayList<EntidadeDominio>();
		lista = reservaDao.pesquisarPorCPF(reserva);

		for (EntidadeDominio r : lista) {
			System.out.println(r);
		}
	}

	@Test
	@Ignore
	public void pesquisarPorModelo() {

		Modelo modelo = new Modelo();
		modelo.setNome("MEGANE");

		Reserva reserva = new Reserva();
		// reserva.setId(3);
		reserva.setModelo(modelo);

		ReservaDAO reservaDao = new ReservaDAO();

		List<EntidadeDominio> lista = new ArrayList<EntidadeDominio>();
		lista = reservaDao.pesquisarPorModelo(reserva);

		for (EntidadeDominio r : lista) {
			System.out.println(r);
		}
	}
	
	@Test
	@Ignore
	public void pesquisarPorNome() {

		Cliente cliente = new Cliente();
		cliente.setNome("MARIA");

		Reserva reserva = new Reserva();
		reserva.setCliente(cliente);
		ReservaDAO reservaDao = new ReservaDAO();

		List<EntidadeDominio> lista = new ArrayList<EntidadeDominio>();
		lista = reservaDao.pesquisarPorNome(reserva);

		for (EntidadeDominio r : lista) {
			System.out.println(r);
		}
	}
	
	@Test
	@Ignore
	public void pesquisarReservasAtivas() {


		ReservaDAO reservaDao = new ReservaDAO();

		List<EntidadeDominio> lista = new ArrayList<EntidadeDominio>();
		lista = reservaDao.pesquisarReservasAtivas();

		for (EntidadeDominio r : lista) {
			System.out.println(r);
		}
	}
	
	@Test
	@Ignore
	public void pesquisarPorSituacao() {


		//ReservaDAO reservaDao = new ReservaDAO();

		Reserva reserva = new Reserva();
		reserva.setSituacao(SituacaoReserva.CANCELADO);
		
		List<Reserva> lista = new ArrayList<Reserva>();

		for (Reserva r : lista) {
			System.out.println(r);
		}
	}

	@Test
	@Ignore
	public void listarTodos() {

		ReservaDAO reservaDao = new ReservaDAO();

		List<EntidadeDominio> lista = new ArrayList<EntidadeDominio>();

		lista = reservaDao.listarTodos();

		for (EntidadeDominio r : lista) {
			System.out.println(r);
		}
	}
	
	
	@Test
	@Ignore
	public void pesquisarPorIntervalo(){
		
		Reserva reserva = new Reserva();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String dataInicio = "01/01/2016";
		String dataFim = "22/02/2016";
		try {
			reserva.setDataCadastroReserva(sdf.parse(dataInicio));
			reserva.setDataFim(sdf.parse(dataFim));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		List<EntidadeDominio> lista = new ArrayList<EntidadeDominio>();
		lista = new ReservaDAO().pesquisarPorIntervaloData(reserva);

		for(EntidadeDominio r : lista){
			System.out.println(r);
		}
	}


}
