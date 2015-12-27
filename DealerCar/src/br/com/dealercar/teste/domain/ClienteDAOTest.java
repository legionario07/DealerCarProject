package br.com.dealercar.teste.domain;

import java.util.List;

import br.com.dealercar.dao.ClienteDAO;
import br.com.dealercar.domain.Cliente;

public class ClienteDAOTest {

	/*
	@SuppressWarnings("unused")
	public static void cadastrar() {
		Cidade cidade = new Cidade();
		cidade.setId(5);
		Cliente cliente = new Cliente("Luiza da Silva", "01-01-1961", "Luiza da Costa", "FEMININO", 
									"96442823", "22345828", "8277357", "391428634",
									"luiza@teste.com", new Endereco("Rua do Luiza", "11", "casa 2", "centro"), cidade);

		ClienteDAO dao = new ClienteDAO();
		dao.cadastrar(cliente);

	}

	@SuppressWarnings("unused")
	public static void editar() {
		Cidade cidade = new Cidade();
		cidade.setId(1);

		Cliente cliente = new Cliente("Luiza da Silva", "01-01-1961", "Luiza da Costa", "FEMININO", 
				"96442823", "22345828", "8277357", "39192834",
				"luiza@teste.com", new Endereco("Rua do Luiza", "11", "casa 2", "centro"), cidade);
		cliente.setId(3);

		ClienteDAO dao = new ClienteDAO();
		dao.editar(cliente);

	}
*/
	public static void listarTodos() {

		ClienteDAO dao = new ClienteDAO();
		List<Cliente> clientes = dao.listarTodos();

		for (Cliente c : clientes) {
			System.out.println(c);
		}
	}

/*	public static void pesquisarPorID() {
		Cliente cliente = new Cliente();
		cliente.setId(16);

		ClienteDAO dao = new ClienteDAO();
		Cliente clienteRetorno = dao.pesquisarPorID(cliente);

		System.out.println(clienteRetorno);
	}

	public static void pesquisarPorCPF() {
		Cliente cliente = new Cliente();
		cliente.setCPF("369.429.508-90");

		ClienteDAO dao = new ClienteDAO();
		Cliente clienteRetorno = dao.pesquisarPorCPF(cliente);

		System.out.println(clienteRetorno);
	}
	
	public static void pesquisarPorNome() {
		
		Cliente cliente = new Cliente();
		cliente.setNome("silva");
		
		ClienteDAO dao = new ClienteDAO();
		List<Cliente> lista = dao.pesquisarPorNome(cliente);
		
		for(Cliente cli : lista) {
			System.out.println(cli);
		}
	}
		
		
		*/


	public static void main(String[] args) {

		//cadastrar();
		 //editar();
		listarTodos();
		// pesquisarPorID();
		//pesquisarPorNome();
		//pesquisarPorCPF();
		
	}

}
