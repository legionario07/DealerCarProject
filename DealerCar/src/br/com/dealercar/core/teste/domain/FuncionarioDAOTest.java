package br.com.dealercar.core.teste.domain;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.dealercar.core.autenticacao.Funcionario;
import br.com.dealercar.core.autenticacao.Usuario;
import br.com.dealercar.core.dao.FuncionarioDAO;
import br.com.dealercar.domain.EntidadeDominio;

public class FuncionarioDAOTest {

	@Test
	@Ignore
	public void cadastrar() {

		Funcionario funcionario = new Funcionario();

		FuncionarioDAO funDao = new FuncionarioDAO();
		funDao.cadastrar(funcionario);

	}

	@Test
	@Ignore
	public void editar() {
		Funcionario funcionario = new Funcionario();

		funcionario.setId(1);

		FuncionarioDAO funDao = new FuncionarioDAO();
		funDao.editar(funcionario);

	}

	@Test
	@Ignore
	public void excluir() {

		Funcionario funcionario = new Funcionario(4);

		FuncionarioDAO funDao = new FuncionarioDAO();
		funDao.excluir(funcionario);
	}
	
	@Test
	@Ignore
	public void listarTodos() {
		List<EntidadeDominio> lista = new ArrayList<EntidadeDominio> ();
		
		FuncionarioDAO fDao = new FuncionarioDAO();
		
		lista = fDao.listarTodos();
		
		for(EntidadeDominio f : lista) {
			System.out.println(f);
		}
	}

	@Test
	@Ignore
	public void pesquisarPorNome() {
		List<EntidadeDominio> lista = new ArrayList<EntidadeDominio> ();
		
		Funcionario funcionario = new Funcionario();
		funcionario.setNome("Paulo");
		
		FuncionarioDAO fDao = new FuncionarioDAO();
		
		lista = fDao.pesquisarPorNome(funcionario);
		
		for(EntidadeDominio f : lista) {
			System.out.println(f);
		}
	}
	
	@Test
	@Ignore
	public void pesquisarPorID() {
		Funcionario funcionario = new Funcionario(1);
		
		FuncionarioDAO fDao = new FuncionarioDAO();
		
		System.out.println(fDao.pesquisarPorID(funcionario));
	}
	
	@Test
	@Ignore
	public void pesquisarPorUsuario(){
		Funcionario funcionario = new Funcionario();
		Usuario usuario = new Usuario();
		usuario.setLogin("legionario");
		
		
		System.out.println(funcionario);

		
	
	  }
	 
	
}
