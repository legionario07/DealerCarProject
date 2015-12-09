package br.com.dealercar.teste.domain;

import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.dao.FuncionarioDAO;
import br.com.dealercar.domain.Funcionario;

public class FuncionarioDAOTest {

	/*
	public static void cadastrar() {

		Funcionario funcionario = new Funcionario("Luis Gustavo", "31/07/1988", "Masculino", "971986033",
				new Endereco("Rua xv de NOvembro", "13", "Centro"), new Cidade(5),	"Administrador", 4000.00D, new Usuario(2));

		FuncionarioDAO funDao = new FuncionarioDAO();
		funDao.cadastrar(funcionario);

	}

	public static void editar() {
		Funcionario funcionario = new Funcionario("Paulo Sergio", "31/07/1988", "Masculino", "47986737",
				new Endereco("Rua xv de NOvembro", "13", "Centro"), new Cidade(5),  "971986033", 
				"Administrador", 3000.00D, new Usuario(1));

		funcionario.setId(1);

		FuncionarioDAO funDao = new FuncionarioDAO();
		funDao.editar(funcionario);

	}

	public static void excluir() {

		Funcionario funcionario = new Funcionario(4);

		FuncionarioDAO funDao = new FuncionarioDAO();
		funDao.excluir(funcionario);
	}
	*/
	
	public static void listarTodos() {
		List<Funcionario> lista = new ArrayList<Funcionario> ();
		
		FuncionarioDAO fDao = new FuncionarioDAO();
		
		lista = fDao.listarTodos();
		
		for(Funcionario f : lista) {
			System.out.println(f);
		}
	}

	/*
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
	
	public static void pesquisarPorUsuario(){
		Funcionario funcionario = new Funcionario();
		Usuario usuario = new Usuario();
		usuario.setLogin("legionario");
		usuario = new UsuarioDAO().pesquisarPorLogin(usuario);
		
		funcionario = new FuncionarioDAO().pesquisarPorUsuario(usuario);
		
		System.out.println(funcionario);

		
	
	  }
	 */
	 
	
	
	public static void main(String[] args) {

		//cadastrar();
		//editar();
		//pesquisarPorNome();
		//excluir();
		//pesquisarPorID();
		//pesquisarPorNome();
		listarTodos();
		//pesquisarPorUsuario();
		
	}

}
