package br.com.dealercar.core.teste.domain;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.dealercar.core.dao.automotivos.FabricanteDAO;
import br.com.dealercar.domain.EntidadeDominio;
import br.com.dealercar.domain.automotivos.Fabricante;

public class FabricanteDAOTest {

	@Test
	@Ignore
	public  void cadastrar() {
		Fabricante fabricante = new Fabricante("Teste");
		
		FabricanteDAO fDao = new FabricanteDAO();
		fDao.cadastrar(fabricante);
	}

	@Test
	@Ignore
	public void listarTodos() {
		List<EntidadeDominio> lista = new ArrayList<EntidadeDominio>();
		
		FabricanteDAO fDao = new FabricanteDAO();
		
		lista = fDao.listarTodos();
		
		for(EntidadeDominio f : lista) {
			System.out.println(f);
		}
	}

	@Test
	@Ignore
	public void editar() {
		Fabricante fabricante = new Fabricante();
		fabricante.setId(26);
		fabricante.setNome("teste24");
		
		FabricanteDAO fDao = new FabricanteDAO();
		fDao.editar(fabricante);
		
	}
	
	@Test
	@Ignore
	public void excluir() {
		Fabricante fabricante = new Fabricante();
		fabricante.setId(25);
		
		FabricanteDAO fDao = new FabricanteDAO();
		fDao.excluir(fabricante);
	}
	
	@Test
	@Ignore
	public void pesquisarPorID() {
		Fabricante fabricante = new Fabricante();
		fabricante.setId(25);
		
		FabricanteDAO fDao =  new FabricanteDAO();
		System.out.println(fDao.pesquisarPorID(fabricante));
	}

}
