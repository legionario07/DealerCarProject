package br.com.dealercar.teste;

import java.util.List;

import br.com.dealercar.dao.ClienteDAO;
import br.com.dealercar.domain.Cidade;
import br.com.dealercar.domain.Cliente;

public class ClienteDAOTeste {

	@SuppressWarnings("unused")
	private static void cadastrar() {
		Cidade cidade = new Cidade();
		cidade.setId(5);
		Cliente cliente = new Cliente("Teste", "01-01-1981", "Mae teste", "98882823", "82777", "9192834",
				"teste@teste.com", "Rua do Teste", cidade);

		ClienteDAO dao = new ClienteDAO();
		dao.cadastrar(cliente, cidade);

	}
	
	@SuppressWarnings("unused")
	private static void editar() {
		Cidade cidade =  new Cidade();
		cidade.setId(1);
				
		Cliente cliente = new Cliente("Teste", "01-01-1981", "Mae teste", "98882823", "82777", "9192834",
				"teste@teste.com", "Rua do Teste", cidade);
		cliente.setId(3);
		
		ClienteDAO dao =  new ClienteDAO();
		dao.editar(cliente, cidade);
				
	}
	
	@SuppressWarnings("unused")
	private static void listarTodos() {
		
		ClienteDAO dao =  new ClienteDAO();
		List<Cliente> clientes = dao.listarTodos();
		
		for(Cliente c : clientes ) {
			System.out.println(c);
		}
	}
	
	@SuppressWarnings("unused")
	private static void pesquisarPorID() {
		Cliente cliente = new Cliente();
		cliente.setId(2);
		
		ClienteDAO dao = new ClienteDAO();
		Cliente clienteRetorno = dao.pesquisarPorID(cliente);
		
		System.out.println(clienteRetorno);
	}
	
	private static void pesquisarPorNome() {
		
		Cliente cliente = new Cliente();
		cliente.setNome("paulo");
		
		ClienteDAO dao = new ClienteDAO();
		List<Cliente> lista = dao.pesquisarPorNome(cliente);
		
		for(Cliente cli : lista) {
			System.out.println(cli);
		}
		
	}

	public static void main(String[] args) {

		//cadastrar();
		//editar();
		//listarTodos();
		//pesquisarPorID();
		pesquisarPorNome();
		
	}

}
