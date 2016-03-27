package br.com.dealercar.core.teste.automotivos;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.dealercar.core.dao.automotivos.ModeloDAO;
import br.com.dealercar.domain.EntidadeDominio;
import br.com.dealercar.domain.automotivos.Fabricante;
import br.com.dealercar.domain.automotivos.Modelo;

public class ModeloDAOTest {
	

	@Test
	@Ignore
	public void cadastrar() {
		Modelo modelo = new Modelo("Teste", new Fabricante(2));
		
		ModeloDAO mDao = new ModeloDAO();
		mDao.cadastrar(modelo);
	}
	
	@Test
	@Ignore
	public void listarTodos() {
		List<EntidadeDominio> lista = new ArrayList<EntidadeDominio>();
		
		ModeloDAO mDao = new ModeloDAO();
		
		lista = mDao.listarTodos();
		
		for(EntidadeDominio m : lista) {
			System.out.println(m);
		}
	}
	
	
	@Test
	@Ignore
	public void editar() {
		Modelo modelo = new Modelo(26, "Teste2", new Fabricante(5));

		ModeloDAO mDao = new ModeloDAO();
		mDao.editar(modelo);
		
	}
	
	@Test
	@Ignore
	public void excluir() {
		Modelo modelo = new Modelo(26);
		
		ModeloDAO mDao = new ModeloDAO();
		mDao.excluir(modelo);
	}
	
	@Test
	@Ignore
	public void pesquisarPorID() {
		
		Modelo modelo = new Modelo();
		modelo.setId(2);
		
		
		ModeloDAO mDao = new ModeloDAO();
		System.out.println(mDao.pesquisarPorID(modelo));
		
	}
	
	@Test
	@Ignore
	public void pesquisarModelosDisponiveis(){
		
		List<Modelo> lista = new ArrayList<Modelo>();
		
		ModeloDAO mDao = new ModeloDAO();
		
		lista = mDao.listarModelosDisponiveis();
		
		for(Modelo m : lista) {
			System.out.println(m);
		}
	}
	
	

}
