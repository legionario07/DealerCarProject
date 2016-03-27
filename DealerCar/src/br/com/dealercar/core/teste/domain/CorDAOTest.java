package br.com.dealercar.core.teste.domain;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.dealercar.core.dao.CorDAO;
import br.com.dealercar.domain.Cor;
import br.com.dealercar.domain.EntidadeDominio;

public class CorDAOTest {

	@Test
	@Ignore
	public void listarTodos() {
		CorDAO corDao = new CorDAO();
		List<EntidadeDominio> lista = new ArrayList<EntidadeDominio>();
		lista = corDao.listarTodos();

		for (EntidadeDominio cor : lista) {
			System.out.println(cor);
		}
	}

	@Test
	@Ignore
	public void pesquisarPorID() {
		Cor cor = new Cor();
		cor.setId(4);

		CorDAO corDao = new CorDAO();
		System.out.println(corDao.pesquisarPorID(cor));
	}

	@Test
	@Ignore
	public void editar() {
		Cor cor = new Cor();
		cor.setId(4);

		CorDAO corDao = new CorDAO();
		System.out.println(corDao.pesquisarPorID(cor));
	}

	@Test
	@Ignore
	public void excluir() {
		Cor cor = new Cor();
		cor.setId(23);

		CorDAO corDao = new CorDAO();
		corDao.excluir(cor);
	}

	@Test
	@Ignore
	public void cadastrar() {
		Cor cor = new Cor();
		cor.setNome("verde claro");

		CorDAO corDao = new CorDAO();
		corDao.cadastrar(cor);
	}

}
