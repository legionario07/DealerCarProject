package br.com.dealercar.core.teste.domain;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.dealercar.core.dao.CidadeDAO;
import br.com.dealercar.domain.Cidade;
import br.com.dealercar.domain.EntidadeDominio;
import br.com.dealercar.domain.Estado;

public class CidadeDAOTest {

	@Test
	@Ignore
	public void cadastrar() {
		Cidade cidade = new Cidade();
		cidade.setNome("IlhaBela");
		CidadeDAO cDao = new CidadeDAO();
		cDao.cadastrar(cidade);

	}

	@Test
	@Ignore
	public void excluir() {
		Cidade cidade = new Cidade();
		cidade.setId(11);

		CidadeDAO cDAo = new CidadeDAO();
		cDAo.excluir(cidade);

	}

	@Test
	@Ignore
	public void editar() {
		Cidade cidade = new Cidade();
		cidade.setId(1);
		cidade.setNome("Suzano");

		CidadeDAO cDao = new CidadeDAO();
		cDao.editar(cidade);
	}

	@Test
	@Ignore
	public void procurarPorId() {
		Cidade cidade = new Cidade();
		cidade.setId(5);
		Cidade cRetorno = new Cidade();

		CidadeDAO cDao = new CidadeDAO();
		cRetorno = (Cidade) cDao.pesquisarPorID(cidade);

		System.out.println(cRetorno);

	}

	@Test
	@Ignore
	public void pesquisarPorNome() {
		Cidade cidade = new Cidade();
		cidade.setNome("Salesópolis");

		CidadeDAO cDao = new CidadeDAO();
		List<EntidadeDominio> cidades = new ArrayList<EntidadeDominio>();
		cidades = cDao.pesquisarPorNome(cidade);

		for (EntidadeDominio c : cidades) {
			System.out.println(c);
		}
	}
	
	@Test
	@Ignore
	public void pesquisarPorEstado() {
		
		Estado estado = new Estado();
		estado.setId(3);

		CidadeDAO cDao = new CidadeDAO();
		List<EntidadeDominio> cidades = new ArrayList<EntidadeDominio>();
		cidades = cDao.pesquisarPorIDEstado(estado);

		for (EntidadeDominio c : cidades) {
			System.out.println(c);
		}
	}
	
	@Test
	@Ignore
	public void pesquisarPorUFEstado() {
		
		Estado estado = new Estado();
		estado.setUf("SP");

		List<EntidadeDominio> cidades = new ArrayList<EntidadeDominio>();

		for (EntidadeDominio c : cidades) {
			System.out.println(c);
		}
	}

	@Test
	@Ignore
	public void listarTodos() {
		CidadeDAO cDao = new CidadeDAO();
		List<EntidadeDominio> cidades = new ArrayList<EntidadeDominio>();

		cidades = cDao.listarTodos();

		for (EntidadeDominio c : cidades) {
			System.out.println(c);
		}

	}


}
