package br.com.dealercar.core.teste.itensrevisao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.dealercar.core.dao.itensrevisao.FiltroDeArDAO;
import br.com.dealercar.domain.EntidadeDominio;
import br.com.dealercar.domain.produtosrevisao.FiltroDeAr;
import br.com.dealercar.domain.produtosrevisao.FormaDeVenda;

public class FiltroDeArDAOTest {

	@Test
	@Ignore
	public void cadastrar(){
		FiltroDeAr filtroDeAr = new FiltroDeAr();
		filtroDeAr.setMarca("TEste");
		filtroDeAr.setDescricao("FiltroDeAr Para Carros");
		filtroDeAr.setTipo("Traseiro");
		filtroDeAr.setValor(17.98d);
		filtroDeAr.setQuantidade(10);
		
		FormaDeVenda formaDeVenda = new FormaDeVenda();
		formaDeVenda.setId(1);
		
		filtroDeAr.setFormaDeVenda(formaDeVenda);
		
		new FiltroDeArDAO().cadastrar(filtroDeAr);
		
		
	}
	
	@Test
	@Ignore
	public void excluir(){
		FiltroDeAr filtroDeAr = new FiltroDeAr();
		filtroDeAr.setId(3);
		
		new FiltroDeArDAO().excluir(filtroDeAr);
		
	}
	
	@Test
	@Ignore
	public void editar(){
		FiltroDeAr filtroDeAr = new FiltroDeAr();
		filtroDeAr.setId(2);
		filtroDeAr.setMarca("Cofab");
		filtroDeAr.setDescricao("FiltroDeAr Para Carros");
		filtroDeAr.setTipo("Dianteiro");
		filtroDeAr.setValor(17.98d);
		filtroDeAr.setQuantidade(10);
		
		FormaDeVenda formaDeVenda = new FormaDeVenda();
		formaDeVenda.setId(1);
		
		filtroDeAr.setFormaDeVenda(formaDeVenda);
		
		new FiltroDeArDAO().editar(filtroDeAr);
		
		
	}
	
	@Test
	@Ignore
	public void pesquisarPorID(){
		FiltroDeAr filtroDeAr = new FiltroDeAr();
		filtroDeAr.setId(1);
		
		filtroDeAr = new FiltroDeArDAO().pesquisarPorID(filtroDeAr);
		
		System.out.println(filtroDeAr);
		
	}
	
	@Test
	@Ignore
	public void pesquisarPorMarca(){
		FiltroDeAr filtroDeAr = new FiltroDeAr();
		filtroDeAr.setMarca("Cofab");
		
		List<FiltroDeAr> lista = new ArrayList<FiltroDeAr>();
		
		lista = new FiltroDeArDAO().pesquisarPorMarca(filtroDeAr);
		
		for(FiltroDeAr a : lista){
			System.out.println(a);
		}
		
	}
	
	@Test
	@Ignore
	public void listar(){
		
		List<EntidadeDominio> lista = new ArrayList<EntidadeDominio>();
		
		lista = new FiltroDeArDAO().listarTodos();
		
		for(EntidadeDominio a : lista){
			System.out.println(a);
		}
		
	}
	
	@Test
	@Ignore
	public void pesquisarPorDescricaoMarcaTipo(){
		FiltroDeAr filtroDeAr = new FiltroDeAr();
		
		String stringFiltroDeAr = "FILTRO DE AR - FRAM - MOTOR EM GERAL";
		
		filtroDeAr = new FiltroDeArDAO().pesquisarPorDescricaoMarcaTipo(stringFiltroDeAr);
		
		System.out.println(filtroDeAr.getId());
		System.out.println(filtroDeAr.getDescricao());
		System.out.println(filtroDeAr.getMarca());
		System.out.println(filtroDeAr.getTipo());
		System.out.println(filtroDeAr.getValor());
		
	}
	
	
}
