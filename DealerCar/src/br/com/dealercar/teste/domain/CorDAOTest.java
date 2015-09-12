package br.com.dealercar.teste.domain;

import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.dao.CorDAO;
import br.com.dealercar.domain.Cor;

public class CorDAOTest {

	public static void listarTodos() {
		CorDAO corDao = new CorDAO();
		List<Cor> lista = new ArrayList<Cor>();
		lista = corDao.listarTodos();

		for (Cor cor : lista) {
			System.out.println(cor);
		}
	}

	public static void pesquisarPorID() {
		Cor cor = new Cor();
		cor.setId(4);

		CorDAO corDao = new CorDAO();
		System.out.println(corDao.pesquisarPorID(cor));
	}

	public static void editar() {
		Cor cor = new Cor();
		cor.setId(4);

		CorDAO corDao = new CorDAO();
		System.out.println(corDao.pesquisarPorID(cor));
	}

	public static void excluir() {
		Cor cor = new Cor();
		cor.setId(23);

		CorDAO corDao = new CorDAO();
		corDao.excluir(cor);
	}

	public static void cadastrar() {
		Cor cor = new Cor();
		cor.setNome("verde claro");

		CorDAO corDao = new CorDAO();
		corDao.cadastrar(cor);
	}

	public static void main(String[] args) {

		// listarTodos();
		// pesquisarPorID();
		cadastrar();
		//excluir();
	}
}
