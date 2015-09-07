package br.com.dealercar.teste.domain;

import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.dao.FuncionarioDAO;
import br.com.dealercar.domain.Cidade;
import br.com.dealercar.domain.Funcionario;
import br.com.dealercar.domain.Usuario;

public class FuncionarioDAOTest {

	public static void cadastrar() {

		Funcionario funcionario = new Funcionario("Paulo Sergio", "31/07/1988", "Masculino", "971986033",
				"Administrador", 3000.00D, new Cidade(5), new Usuario(1));

		FuncionarioDAO funDao = new FuncionarioDAO();
		funDao.cadastrar(funcionario);

	}

	public static void editar() {
		Funcionario funcionario = new Funcionario("Paulo Sergio", "31/07/1988", "Masculino", "47986737", "971986033",
				"Administrador", 3000.00D, new Cidade(5), new Usuario(1));

		funcionario.setId(1);

		FuncionarioDAO funDao = new FuncionarioDAO();
		funDao.editar(funcionario);

	}

	public static void excluir() {

		Funcionario funcionario = new Funcionario(2);

		FuncionarioDAO funDao = new FuncionarioDAO();
		funDao.excluir(funcionario);
	}
	
	public static void listarTodos() {
		List<Funcionario> lista = new ArrayList<Funcionario> ();
		
		FuncionarioDAO fDao = new FuncionarioDAO();
		
		lista = fDao.listarTodos();
		
		for(Funcionario f : lista) {
			System.out.println(f);
		}
	}

	public static void pesquisarPorNome() {
		List<Funcionario> lista = new ArrayList<Funcionario> ();
		
		Funcionario funcionario = new Funcionario();
		funcionario.setNome("Paulo");
		
		FuncionarioDAO fDao = new FuncionarioDAO();
		
		lista = fDao.pesquisarPorNome(funcionario);
		
		for(Funcionario f : lista) {
			System.out.println(f);
		}
	}
	
	public static void pesquisarPorID() {
		Funcionario funcionario = new Funcionario(1);
		
		FuncionarioDAO fDao = new FuncionarioDAO();
		
		System.out.println(fDao.pesquisarPorID(funcionario));
	}
	
	public static void main(String[] args) {

		cadastrar();
	}

}
