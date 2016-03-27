package br.com.dealercar.core.teste.itensopcionais;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.dealercar.core.dao.itensopcionais.CadeirinhaBebeDAO;
import br.com.dealercar.domain.EntidadeDominio;
import br.com.dealercar.domain.itensopcionais.CadeirinhaBebe;

public class CadeirinhaBebeDAOTest {

	@Test
	@Ignore
	public void cadastrar() {

		CadeirinhaBebe cadeirinhaBebe = new CadeirinhaBebe("Teste 3", 45.00, "Teste", "98838s484", 5);

		CadeirinhaBebeDAO cadeirinhaBebeDao = new CadeirinhaBebeDAO();
		cadeirinhaBebeDao.cadastrar(cadeirinhaBebe);

	}

	@Test
	@Ignore
	public void editar() {

		CadeirinhaBebe cadeirinhaBebe = new CadeirinhaBebe("Teste", 40.00, "marca alterada", "293940", 19);
		cadeirinhaBebe.setCodigo(1);

		CadeirinhaBebeDAO cadeirinhaBebeDao = new CadeirinhaBebeDAO();
		cadeirinhaBebeDao.editar(cadeirinhaBebe);

	}

	@Test
	@Ignore
	public void excluir() {

		CadeirinhaBebe cadeirinhaBebe = new CadeirinhaBebe(2);

		CadeirinhaBebeDAO cadeirinhaBebeDao = new CadeirinhaBebeDAO();
		cadeirinhaBebeDao.excluir(cadeirinhaBebe);

	}

	@Test
	@Ignore
	public void listarTodos() {
		List<EntidadeDominio> lista = new ArrayList<EntidadeDominio>();

		CadeirinhaBebeDAO cadeirinhaBebeDao = new CadeirinhaBebeDAO();

		lista = cadeirinhaBebeDao.listarTodos();

		for (EntidadeDominio cadeirinhaBebe : lista) {
			System.out.println(cadeirinhaBebe);
		}

	}

	@Test
	@Ignore
	public void pesquisarPorID() {

		CadeirinhaBebe cadeirinhaBebe = new CadeirinhaBebe();
		cadeirinhaBebe.setCodigo(1);

		CadeirinhaBebeDAO cadeirinhaBebeDao = new CadeirinhaBebeDAO();

		System.out.println(cadeirinhaBebeDao.pesquisarPorID(cadeirinhaBebe));

	}


}
