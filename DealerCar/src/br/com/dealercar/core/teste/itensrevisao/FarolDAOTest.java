package br.com.dealercar.core.teste.itensrevisao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.dealercar.core.dao.itensrevisao.FarolDAO;
import br.com.dealercar.domain.EntidadeDominio;
import br.com.dealercar.domain.produtosrevisao.Farol;
import br.com.dealercar.domain.produtosrevisao.FormaDeVenda;

public class FarolDAOTest {

	@Test
	@Ignore
	public void cadastrar(){
		Farol farol = new Farol();
		farol.setMarca("TEste");
		farol.setDescricao("Farol Para Carros");
		farol.setTipo("Traseiro");
		farol.setValor(17.98d);
		farol.setQuantidade(10);
		
		FormaDeVenda formaDeVenda = new FormaDeVenda();
		formaDeVenda.setId(1);
		
		farol.setFormaDeVenda(formaDeVenda);
		
		new FarolDAO().cadastrar(farol);
		
		
	}
	
	@Test
	@Ignore
	public void excluir(){
		Farol farol = new Farol();
		farol.setId(3);
		
		new FarolDAO().excluir(farol);
		
	}
	
	@Test
	@Ignore
	public void editar(){
		Farol farol = new Farol();
		farol.setId(2);
		farol.setMarca("Cofab");
		farol.setDescricao("Farol Para Carros");
		farol.setTipo("Dianteiro");
		farol.setValor(17.98d);
		farol.setQuantidade(10);
		
		FormaDeVenda formaDeVenda = new FormaDeVenda();
		formaDeVenda.setId(1);
		
		farol.setFormaDeVenda(formaDeVenda);
		
		new FarolDAO().editar(farol);
		
		
	}
	
	@Test
	@Ignore
	public void pesquisarPorID(){
		Farol farol = new Farol();
		farol.setId(1);
		
		farol = new FarolDAO().pesquisarPorID(farol);
		
		System.out.println(farol);
		
	}
	
	@Test
	@Ignore
	public void pesquisarPorMarca(){
		Farol farol = new Farol();
		farol.setMarca("Cofab");
		
		List<Farol> lista = new ArrayList<Farol>();
		
		lista = new FarolDAO().pesquisarPorMarca(farol);
		
		for(Farol a : lista){
			System.out.println(a);
		}
		
	}
	
	@Test
	@Ignore
	public void listar(){
		
		List<EntidadeDominio> lista = new ArrayList<EntidadeDominio>();
		
		lista = new FarolDAO().listarTodos();
		
		for(EntidadeDominio a : lista){
			System.out.println(a);
		}
	}
	
	@Test
	@Ignore
	public void pesquisarPorDescricaoMarcaTipo(){
		Farol farol = new Farol();
		
		String stringFarol = "LANTERNA (FAROL) - MASCARA NEGRA - DIANTEIRO";
		
		farol = new FarolDAO().pesquisarPorDescricaoMarcaTipo(stringFarol);
		
		System.out.println(farol.getId());
		System.out.println(farol.getDescricao());
		System.out.println(farol.getMarca());
		System.out.println(farol.getTipo());
		System.out.println(farol.getValor());
		
	}
	
}
