package br.com.dealercar.teste.automotivos;

import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.dao.automotivos.ModeloDAO;
import br.com.dealercar.domain.automotivos.Fabricante;
import br.com.dealercar.domain.automotivos.Modelo;

public class ModeloDAOTest {
	
	
	@SuppressWarnings("unused")
	private static void cadastrar() {
		Modelo modelo = new Modelo("Teste", new Fabricante(2));
		
		ModeloDAO mDao = new ModeloDAO();
		mDao.cadastrar(modelo);
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
		Modelo modelo = new Modelo(26, "Teste2", new Fabricante(5));

		ModeloDAO mDao = new ModeloDAO();
		mDao.editar(modelo);
		
	}
	
	@SuppressWarnings("unused")
	private static void excluir() {
		Modelo modelo = new Modelo(26);
		
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
	
	@SuppressWarnings("unused")
	private static void pesquisarModelosDisponiveis(){
		
		List<Modelo> lista = new ArrayList<Modelo>();
		
		ModeloDAO mDao = new ModeloDAO();
		
		lista = mDao.listarModelosDisponiveis();
		
		for(Modelo m : lista) {
			System.out.println(m);
		}
	}
	
	
	public static void main(String[] args) {
		
		//cadastrar();
		//listarTodos();
		//editar();
		//excluir();
		//pesquisarPorID();
		//pesquisarModelosDisponiveis();
		
		
	}
	

}
