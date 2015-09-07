package br.com.dealercar.teste.automotivos;

import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.dao.automotivos.ModeloDAO;
import br.com.dealercar.domain.automotivos.Fabricante;
import br.com.dealercar.domain.automotivos.Modelo;

public class ModeloDAOTest {
	
	
	@SuppressWarnings("unused")
	private static void cadastrar() {
		Modelo modelo = new Modelo();
		modelo.setNome("Teste");
		
		Fabricante fabricante = new Fabricante();
		fabricante.setId(4);
		
		modelo.setFabricante(fabricante);
		
		ModeloDAO mDao = new ModeloDAO();
		mDao.cadastrar(modelo, fabricante);
	}
	
	
	@SuppressWarnings("unused")
	private static void listarTodos() {
		List<Modelo> lista = new ArrayList<Modelo>();
		
		ModeloDAO mDao = new ModeloDAO();
		
		lista = mDao.listarTodos();
		
		for(Modelo m : lista) {
			System.out.println(m);
		}
	}
	
	
	@SuppressWarnings("unused")
	private static void editar() {
		Fabricante fabricante = new Fabricante();
		fabricante.setId(4);

		Modelo modelo = new Modelo();
		modelo.setId(1);
		modelo.setNome("Teste 474");
		
		ModeloDAO mDao = new ModeloDAO();
		mDao.editar(modelo, fabricante);
		
	}
	
	@SuppressWarnings("unused")
	private static void excluir() {
		Modelo modelo = new Modelo();
		modelo.setId(1);
		
		ModeloDAO mDao = new ModeloDAO();
		mDao.excluir(modelo);
	}
	
	
	@SuppressWarnings("unused")
	private static void pesquisarPorID() {
		
		Modelo modelo = new Modelo();
		modelo.setId(2);
		
		
		ModeloDAO mDao = new ModeloDAO();
		System.out.println(mDao.pesquisarPorID(modelo));
		
	}
	
	
	public static void main(String[] args) {
		
		//cadastrar();
		//listarTodos();
		//editar();
		//excluir();
		//pesquisarPorID();
		
		
	}
	

}
