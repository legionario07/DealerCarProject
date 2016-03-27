package br.com.dealercar.core.teste.itensrevisao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.dealercar.core.dao.itensrevisao.FormaDeVendaDAO;
import br.com.dealercar.domain.EntidadeDominio;
import br.com.dealercar.domain.produtosrevisao.FormaDeVenda;

public class FormaDeVendaDAOTest {

	@Test
	@Ignore
	public void cadastrar(){
		FormaDeVenda formaDeVenda =  new FormaDeVenda();
		formaDeVenda.setDescricao("Teste");
		new FormaDeVendaDAO().cadastrar(formaDeVenda);
	}
	
	@Test
	@Ignore
	public void editar(){
		FormaDeVenda formaDeVenda =  new FormaDeVenda();
		formaDeVenda.setId(3);
		formaDeVenda.setDescricao("Teste2");
		new FormaDeVendaDAO().editar(formaDeVenda);
	}
	
	@Test
	@Ignore
	public void listar(){
		
		List<EntidadeDominio> lista = new ArrayList<EntidadeDominio>();
		lista = new FormaDeVendaDAO().listarTodos();
		
		for(EntidadeDominio f : lista){
			System.out.println(f);
		}
		
	}
	
	@Test
	@Ignore
	public void pesquisarPorId(){
		
		FormaDeVenda formaDeVenda = new FormaDeVenda();
		formaDeVenda.setId(1);
		
		formaDeVenda = new FormaDeVendaDAO().pesquisarPorID(formaDeVenda);
		
		System.out.println(formaDeVenda);
		
	}
	
	@Test
	@Ignore
	public void excluir(){
		
		FormaDeVenda formaDeVenda = new FormaDeVenda();
		formaDeVenda.setId(3);
		
		new FormaDeVendaDAO().excluir(formaDeVenda);
		
	}
	
	
}
