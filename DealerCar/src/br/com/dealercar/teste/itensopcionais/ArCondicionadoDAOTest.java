package br.com.dealercar.teste.itensopcionais;

import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.dao.itensopcionais.ArCondicionadoDAO;
import br.com.dealercar.domain.itensopcionais.ArCondicionado;

public class ArCondicionadoDAOTest {

	public static void cadastrar() {

		ArCondicionado ar = new ArCondicionado("Test 2", 45.00);

		ArCondicionadoDAO arDao = new ArCondicionadoDAO();
		arDao.cadastrar(ar);

	}

	public static void editar() {
	
		ArCondicionado ar = new ArCondicionado();
		ar.setCodigo(1);
		ar.setDescricao("Teste");
		ar.setValor(40.00);

		ArCondicionadoDAO arDao = new ArCondicionadoDAO();
		arDao.editar(ar);

	}

	public static void excluir() {
		
		ArCondicionado ar = new ArCondicionado(1);
		
		ArCondicionadoDAO arDao = new ArCondicionadoDAO();
		arDao.excluir(ar);

	}

	public static void listarTodos() {
		List<ArCondicionado> lista = new ArrayList<ArCondicionado>();
		
		ArCondicionadoDAO arDao = new ArCondicionadoDAO();
		
		lista = arDao.listarTodos();
		
		for(ArCondicionado ar : lista){
			System.out.println(ar);
		}
		
	}

	public static void pesquisarPorID() {

		ArCondicionado ar = new ArCondicionado();
		ar.setCodigo(2);
		
		ArCondicionadoDAO arDao = new ArCondicionadoDAO();
		
		System.out.println(arDao.pesquisarPorID(ar));
		
	}

	public static void main(String[] args) {
		
		//cadastrar();
		//editar();
		//excluir();
		//listarTodos();
		//pesquisarPorID();
		
		
		
	}
}
