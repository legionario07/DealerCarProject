package br.com.dealercar.teste.automotivos;

import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.dao.automotivos.CategoriaDAO;
import br.com.dealercar.domain.automotivos.Categoria;

public class CategoriaDAOTest {

	@SuppressWarnings("unused")
	private static void cadastrar() {
		Categoria categoria = new Categoria();
		categoria.setNome("Teste");
		categoria.setDescricao("Portas: 2 ou 3. "
				+ "Ocupantes:  até 4 Pessoas. "
			    + "Bagagem: até 3 malas médias.");
		categoria.setValorDiaria(70D);
		
		CategoriaDAO catDao = new CategoriaDAO();
		catDao.cadastrar(categoria);
	}
	
	
	@SuppressWarnings("unused")
	private static void editar() {
		Categoria categoria = new Categoria();
		categoria.setId(3);
		categoria.setNome("SUV");
		categoria.setDescricao("Portas: 4. Ocupantes: até 4 Pessoas. Bagagem: até 3 malas médias.");
		categoria.setValorDiaria(110D);
		
		CategoriaDAO catDao = new CategoriaDAO();
		catDao.editar(categoria);
		
	}
	
	@SuppressWarnings("unused")
	private static void pesquisarPorID() {
		Categoria categoria = new Categoria();
		categoria.setId(5);
		
		CategoriaDAO catDao = new CategoriaDAO();
		categoria=catDao.pesquisarPorID(categoria);
		System.out.println(categoria);
		
	}
	
	@SuppressWarnings("unused")
	private static void excluir() {
		Categoria categoria = new Categoria();
		categoria.setId(9);
		
		CategoriaDAO catDao =  new CategoriaDAO();
		catDao.excluir(categoria);
		
	}
	
	@SuppressWarnings("unused")
	private static void listarTodos() {
		List<Categoria> lista = new ArrayList<Categoria>();
		CategoriaDAO catDao = new CategoriaDAO();
		lista=catDao.listarTodos();
		
		for(Categoria cat : lista) {
			System.out.println(cat);
		}
		
	}
	
	public static void main(String[] args) {
		
		//cadastrar();
		//editar();
		//pesquisarPorID();
		//excluir();
		//listarTodos();
		
	}

}
