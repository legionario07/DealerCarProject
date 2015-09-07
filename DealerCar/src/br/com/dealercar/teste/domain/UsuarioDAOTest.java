package br.com.dealercar.teste.domain;

import br.com.dealercar.dao.UsuarioDAO;
import br.com.dealercar.domain.Usuario;

public class UsuarioDAOTest {

	public static void cadastrar() {

		Usuario usuario = new Usuario("legionario", "teste");

		UsuarioDAO usuarioDao = new UsuarioDAO();
		usuarioDao.cadastrar(usuario);

	}

	public static void pesquisarPorID() {

		Usuario usuario = new Usuario(1);

		UsuarioDAO usuarioDao = new UsuarioDAO();
		System.out.println(usuarioDao.pesquisarPorID(usuario));

	}

	public static void pesquisarPorLogin() {

		Usuario usuario = new Usuario();
		usuario.setLogin("legionario");

		UsuarioDAO usuarioDao = new UsuarioDAO();
		System.out.println(usuarioDao.pesquisarPorLogin(usuario));

	}

	public static void main(String[] args) {

		//cadastrar();
		pesquisarPorID();
		pesquisarPorLogin();
	}

}
