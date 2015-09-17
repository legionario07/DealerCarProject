package br.com.dealercar.teste.domain;

import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.dao.ReservaDAO;
import br.com.dealercar.domain.Cliente;
import br.com.dealercar.domain.Funcionario;
import br.com.dealercar.domain.Reserva;
import br.com.dealercar.domain.automotivos.Modelo;
import br.com.dealercar.enums.SituacaoReserva;

public class ReservaDAOTest {

	public static void cadastrar() {

		Reserva reserva = new Reserva(new Modelo(6), new Cliente(17), new Funcionario(1));

		ReservaDAO reservaDao = new ReservaDAO();
		reservaDao.cadastrar(reserva);

	}
	
	public static void editar() {
		
		Reserva reserva = new Reserva(new Modelo(4), new Cliente(18), new Funcionario(1));
		reserva.setId(5);
		reserva.setSituacao(SituacaoReserva.CANCELADO);
		
		ReservaDAO reservaDao = new ReservaDAO();
		reservaDao.editar(reserva);
		
	}

	public static void excluir() {
		Reserva reserva = new Reserva(5);
		
		ReservaDAO reservaDao = new ReservaDAO();
		reservaDao.excluir(reserva);
	}
	
	public static void pesquisarPorID() {
		Reserva reserva = new Reserva(2);

		ReservaDAO reservaDao = new ReservaDAO();
		System.out.println(reservaDao.pesquisarPorID(reserva));
	}

	public static void pesquisarPorCPF() {

		Cliente cliente = new Cliente();
		cliente.setCPF("139.492.883-98");

		Reserva reserva = new Reserva();
		reserva.setCliente(cliente);

		ReservaDAO reservaDao = new ReservaDAO();

		List<Reserva> lista = new ArrayList<Reserva>();
		lista = reservaDao.pesquisarPorCPF(reserva);

		for (Reserva r : lista) {
			System.out.println(r);
		}
	}
	
	public static void pesquisarPorSituacao() {

		Reserva reserva = new Reserva();
		reserva.setSituacao(SituacaoReserva.ATIVO);

		ReservaDAO reservaDao = new ReservaDAO();

		List<Reserva> lista = new ArrayList<Reserva>();
		lista = reservaDao.pesquisarPorSituacao(reserva);

		for (Reserva r : lista) {
			System.out.println(r);
		}

	}

	public static void pesquisarPorModelo() {

		Modelo modelo = new Modelo();
		modelo.setNome("CONCEPT");

		Reserva reserva = new Reserva();
		//reserva.setId(3);
		reserva.setModelo(modelo);

		ReservaDAO reservaDao = new ReservaDAO();

		List<Reserva> lista = new ArrayList<Reserva>();
		lista = reservaDao.pesquisarPorModelo(reserva);

		for (Reserva r : lista) {
			System.out.println(r);
		}
	}
		
		public static void listarTodos() {
			
			ReservaDAO reservaDao = new ReservaDAO();
			
			List<Reserva> lista = new ArrayList<Reserva>();
			
			lista = reservaDao.listarTodos();
			
			for(Reserva r : lista) {
				System.out.println(r);
			}
	}
		
	public static void main(String[] args) {

		//cadastrar();
		//editar();
		//excluir();
		//pesquisarPorID();
		//pesquisarPorCPF();
		//pesquisarPorModelo();
		//pesquisarPorSituacao();
		//listarTodos();
		
		
	}

}
