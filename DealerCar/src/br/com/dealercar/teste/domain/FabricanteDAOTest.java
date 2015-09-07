package br.com.dealercar.teste.domain;

import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.dao.automotivos.FabricanteDAO;
import br.com.dealercar.domain.automotivos.Fabricante;

public class FabricanteDAOTest {

	@SuppressWarnings("unused")
	private static void cadastrar() {
		Fabricante fabricante = new Fabricante("Teste");
		
		FabricanteDAO fDao = new FabricanteDAO();
		fDao.cadastrar(fabricante);
	}
	
	@SuppressWarnings("unused")
	private static void listarTodos() {
		List<Fabricante> lista = new ArrayList<Fabricante>();
		
		FabricanteDAO fDao = new FabricanteDAO();
		
		lista = fDao.listarTodos();
		
		for(Fabricante f : lista) {
			System.out.println(f);
		}
	}
	
	@SuppressWarnings("unused")
	private static void editar() {
		Fabricante fabricante = new Fabricante();
		fabricante.setId(26);
		fabricante.setNome("teste24");
		
		FabricanteDAO fDao = new FabricanteDAO();
		fDao.editar(fabricante);
		
	}
	
	@SuppressWarnings("unused")
	private static void excluir() {
		Fabricante fabricante = new Fabricante();
		fabricante.setId(25);
		
		FabricanteDAO fDao = new FabricanteDAO();
		fDao.excluir(fabricante);
	}
	
	@SuppressWarnings("unused")
	private static void pesquisarPorID() {
		Fabricante fabricante = new Fabricante();
		fabricante.setId(25);
		
		FabricanteDAO fDao =  new FabricanteDAO();
		System.out.println(fDao.pesquisarPorID(fabricante));
	}
	public static void main(String[] args) {
		//cadastrar();
		//listarTodos();
		//editar();
		//excluir();
		//pesquisarPorID();
		
	}

}
