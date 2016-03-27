package br.com.dealercar.core.teste.automotivos;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.dealercar.core.dao.automotivos.CategoriaDAO;
import br.com.dealercar.domain.EntidadeDominio;
import br.com.dealercar.domain.automotivos.Categoria;

public class CategoriaDAOTest {

	@Test
	@Ignore
	public void cadastrar() {
		Categoria categoria = new Categoria();
		categoria.setNome("Teste");
		categoria.setDescricao("Portas: 2 ou 3. "
				+ "Ocupantes:  até 4 Pessoas. "
			    + "Bagagem: até 3 malas médias.");
		categoria.setValorDiaria(70D);
		
		CategoriaDAO catDao = new CategoriaDAO();
		catDao.cadastrar(categoria);
	}
	
	
	@Test
	@Ignore
	public void editar() {
		Categoria categoria = new Categoria();
		categoria.setId(3);
		categoria.setNome("SUV");
		categoria.setDescricao("Portas: 4. Ocupantes: até 4 Pessoas. Bagagem: até 3 malas médias.");
		categoria.setValorDiaria(110D);
		
		CategoriaDAO catDao = new CategoriaDAO();
		catDao.editar(categoria);
		
	}
	
	@Test
	@Ignore
	public void pesquisarPorID() {
		Categoria categoria = new Categoria();
		categoria.setId(5);
		
		CategoriaDAO catDao = new CategoriaDAO();
		categoria=catDao.pesquisarPorID(categoria);
		System.out.println(categoria);
		
	}
	
	@Test
	@Ignore
	public void excluir() {
		Categoria categoria = new Categoria();
		categoria.setId(9);
		
		CategoriaDAO catDao =  new CategoriaDAO();
		catDao.excluir(categoria);
		
	}
	
	@Test
	public void listarTodos() {
		List<EntidadeDominio> lista = new ArrayList<EntidadeDominio>();
		CategoriaDAO catDao = new CategoriaDAO();
		lista=catDao.listarTodos();
		
		for(EntidadeDominio cat : lista) {
			System.out.println(cat);
		}
		
	}
	

}
