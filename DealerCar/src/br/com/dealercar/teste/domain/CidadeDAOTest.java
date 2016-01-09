package br.com.dealercar.teste.domain;

import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.dao.CidadeDAO;
import br.com.dealercar.domain.Cidade;
import br.com.dealercar.domain.Estado;

public class CidadeDAOTest {

	@SuppressWarnings("unused")
	private static void cadastrar() {
		Cidade cidade = new Cidade();
		cidade.setNome("IlhaBela");
		CidadeDAO cDao = new CidadeDAO();
		cDao.cadastrar(cidade);

	}

	@SuppressWarnings("unused")
	private static void excluir() {
		Cidade cidade = new Cidade();
		cidade.setId(11);

		CidadeDAO cDAo = new CidadeDAO();
		cDAo.excluir(cidade);

	}

	@SuppressWarnings("unused")
	private static void editar() {
		Cidade cidade = new Cidade();
		cidade.setId(1);
		cidade.setNome("Suzano");

		CidadeDAO cDao = new CidadeDAO();
		cDao.editar(cidade);
	}

	@SuppressWarnings("unused")
	private static void procurarPorId() {
		Cidade cidade = new Cidade();
		cidade.setId(5);
		Cidade cRetorno = new Cidade();

		CidadeDAO cDao = new CidadeDAO();
		cRetorno = cDao.pesquisarPorID(cidade);

		System.out.println(cRetorno);

	}

	@SuppressWarnings("unused")
	private static void pesquisarPorNome() {
		Cidade cidade = new Cidade();
		cidade.setNome("Salesópolis");

		CidadeDAO cDao = new CidadeDAO();
		List<Cidade> cidades = new ArrayList<Cidade>();
		cidades = cDao.pesquisarPorNome(cidade);

		for (Cidade c : cidades) {
			System.out.println(c);
		}
	}
	
	@SuppressWarnings("unused")
	private static void pesquisarPorEstado() {
		
		Estado estado = new Estado();
		estado.setId(3);

		CidadeDAO cDao = new CidadeDAO();
		List<Cidade> cidades = new ArrayList<Cidade>();
		cidades = cDao.pesquisarPorIDEstado(estado);

		for (Cidade c : cidades) {
			System.out.println(c);
		}
	}
	
	//@SuppressWarnings("unused")
	private static void pesquisarPorUFEstado() {
		
		Estado estado = new Estado();
		estado.setUf("SP");

		CidadeDAO cDao = new CidadeDAO();
		List<Cidade> cidades = new ArrayList<Cidade>();
		cidades = cDao.pesquisarPorUFEstado(estado);

		for (Cidade c : cidades) {
			System.out.println(c);
		}
	}

	@SuppressWarnings("unused")
	private static void listarTodos() {
		CidadeDAO cDao = new CidadeDAO();
		List<Cidade> cidades = new ArrayList<Cidade>();

		cidades = cDao.listarTodos();

		for (Cidade c : cidades) {
			System.out.println(c);
		}

	}

	public static void main(String[] args) {

		// cadastrar();
		// excluir();
		// editar();
		//procurarPorId();
		//pesquisarPorNome();
		//listarTodos();
		//pesquisarPorEstado();
		pesquisarPorUFEstado();

	}

}
