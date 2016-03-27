package br.com.dealercar.core.teste.domain;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.dealercar.core.dao.ClienteDAO;
import br.com.dealercar.domain.Cidade;
import br.com.dealercar.domain.Cliente;
import br.com.dealercar.domain.EntidadeDominio;

public class ClienteDAOTest {

	@Test
	@Ignore
	public void cadastrar() {
		Cidade cidade = new Cidade();
		cidade.setId(5);
		Cliente cliente = new Cliente();

		ClienteDAO dao = new ClienteDAO();
		dao.cadastrar(cliente);

	}

	@Test
	@Ignore
	public void editar() {
		
		Cliente cliente = new Cliente(9);
		cliente = new ClienteDAO().pesquisarPorID(cliente);
		
		cliente.setRG("123412342");

		ClienteDAO dao = new ClienteDAO();
		dao.editar(cliente);

	}

	@Test
	@Ignore
	public void listarTodos() {

		ClienteDAO dao = new ClienteDAO();
		List<EntidadeDominio> clientes = dao.listarTodos();

		for (EntidadeDominio c : clientes) {
			System.out.println(c);
		}
	}

	@Test
	@Ignore
	public void pesquisarPorID() {
		Cliente cliente = new Cliente();
		cliente.setId(9);

		ClienteDAO dao = new ClienteDAO();
		Cliente clienteRetorno = dao.pesquisarPorID(cliente);

		System.out.println(clienteRetorno);
	}

	@Test
	@Ignore
	public void pesquisarPorCPF() {
		Cliente cliente = new Cliente();
		cliente.setCPF("369.429.508-90");

		ClienteDAO dao = new ClienteDAO();
		Cliente clienteRetorno = dao.pesquisarPorCPF(cliente);

		System.out.println(clienteRetorno);
	}
	
	@Test
	@Ignore
	public void pesquisarPorNome() {
		
		Cliente cliente = new Cliente();
		cliente.setNome("silva");
		
		ClienteDAO dao = new ClienteDAO();
		List<EntidadeDominio> lista = dao.pesquisarPorNome(cliente);
		
		for(EntidadeDominio cli : lista) {
			System.out.println(cli);
		}
	}
		

}
