package br.com.dealercar.teste.itensopcionais;

import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.dao.itensopcionais.CadeirinhaBebeDAO;
import br.com.dealercar.domain.itensopcionais.CadeirinhaBebe;

public class CadeirinhaBebeDAOTest {

	public static void cadastrar() {

		CadeirinhaBebe cadeirinhaBebe = new CadeirinhaBebe("Teste 3", 45.00, "Teste", "98838s484", 5);

		CadeirinhaBebeDAO cadeirinhaBebeDao = new CadeirinhaBebeDAO();
		cadeirinhaBebeDao.cadastrar(cadeirinhaBebe);

	}

	public static void editar() {

		CadeirinhaBebe cadeirinhaBebe = new CadeirinhaBebe("Teste", 40.00, "marca alterada", "293940", 19);
		cadeirinhaBebe.setCodigo(1);

		CadeirinhaBebeDAO cadeirinhaBebeDao = new CadeirinhaBebeDAO();
		cadeirinhaBebeDao.editar(cadeirinhaBebe);

	}

	public static void excluir() {

		CadeirinhaBebe cadeirinhaBebe = new CadeirinhaBebe(2);

		CadeirinhaBebeDAO cadeirinhaBebeDao = new CadeirinhaBebeDAO();
		cadeirinhaBebeDao.excluir(cadeirinhaBebe);

	}

	public static void listarTodos() {
		List<CadeirinhaBebe> lista = new ArrayList<CadeirinhaBebe>();

		CadeirinhaBebeDAO cadeirinhaBebeDao = new CadeirinhaBebeDAO();

		lista = cadeirinhaBebeDao.listarTodos();

		for (CadeirinhaBebe cadeirinhaBebe : lista) {
			System.out.println(cadeirinhaBebe);
		}

	}

	public static void pesquisarPorID() {

		CadeirinhaBebe cadeirinhaBebe = new CadeirinhaBebe();
		cadeirinhaBebe.setCodigo(1);

		CadeirinhaBebeDAO cadeirinhaBebeDao = new CadeirinhaBebeDAO();

		System.out.println(cadeirinhaBebeDao.pesquisarPorID(cadeirinhaBebe));

	}

	public static void main(String[] args) {

		//cadastrar();
		 //editar();
		//excluir();
		// listarTodos();
		 //pesquisarPorID();

	}

}
