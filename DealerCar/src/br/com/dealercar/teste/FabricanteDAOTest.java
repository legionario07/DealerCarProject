package br.com.dealercar.teste;

import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.dao.FabricanteDAO;
import br.com.dealercar.domain.Fabricante;

public class FabricanteDAOTest {

	@SuppressWarnings("unused")
	private static void cadastrar() {
		Fabricante fabricante = new Fabricante("Audi");
		
		FabricanteDAO fDao = new FabricanteDAO();
		fDao.cadastrar(fabricante);
	}
	
	private static void listarTodos() {
		List<Fabricante> lista = new ArrayList<Fabricante>();
		
		FabricanteDAO fDao = new FabricanteDAO();
		
		lista = fDao.listarTodos();
		
		for(Fabricante f : lista) {
			System.out.println(f);
		}
	}
	
	public static void main(String[] args) {
		//cadastrar();
		listarTodos();
	}

}
