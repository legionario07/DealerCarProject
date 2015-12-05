package br.com.dealercar.teste.domain;

import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.dao.UsuarioDAO;
import br.com.dealercar.domain.Permissao;
import br.com.dealercar.domain.Usuario;

public class UsuarioDAOTest {

	public static void cadastrar() {

		Usuario usuario = new Usuario("Teste", "teste", new Permissao(1), "SIM");

		UsuarioDAO usuarioDao = new UsuarioDAO();
		usuarioDao.cadastrar(usuario);

	}

	public static void editar() {

		Usuario usuario = new Usuario("legionario", "legionario", new Permissao(1), "SIM");

		UsuarioDAO usuarioDao = new UsuarioDAO();
		usuarioDao.editar(usuario);
	}

	public static void pesquisarPorID() {

		Usuario usuario = new Usuario(1);

		UsuarioDAO usuarioDao = new UsuarioDAO();
		System.out.println(usuarioDao.pesquisarPorID(usuario));

	}

	public static void pesquisarPorLogin() {

		Usuario usuario = new Usuario();
		usuario.setLogin("Felipe");

		UsuarioDAO usuarioDao = new UsuarioDAO();
		System.out.println(usuarioDao.pesquisarPorLogin(usuario));

	}

	public static void listarTodos() {

		List<Usuario> lista = new ArrayList<Usuario>();
		
		UsuarioDAO usuarioDao = new UsuarioDAO();
		
		lista = usuarioDao.listarTodos();
		
		for(Usuario u : lista){
			
			System.out.println(u);
		}
	}

	public static void autenticar() {
		Usuario usuario = new Usuario();
		usuario.setLogin("legionario");
		usuario.setSenha("legionario");
		usuario =  new UsuarioDAO().autenticar(usuario);
		if(usuario == null){
			System.out.println("Senha Incorreta");
		}else{
			System.out.println("Autenticado");
		}
		
	}

	public static void main(String[] args) {

		// cadastrar();
		// pesquisarPorID();
		// pesquisarPorLogin();
		 //editar();
		//listarTodos();
		autenticar();
	}

}
