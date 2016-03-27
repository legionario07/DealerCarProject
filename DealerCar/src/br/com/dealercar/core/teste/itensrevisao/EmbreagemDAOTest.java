package br.com.dealercar.core.teste.itensrevisao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.dealercar.core.dao.itensrevisao.EmbreagemDAO;
import br.com.dealercar.domain.EntidadeDominio;
import br.com.dealercar.domain.produtosrevisao.Embreagem;
import br.com.dealercar.domain.produtosrevisao.FormaDeVenda;

public class EmbreagemDAOTest {

	@Test
	@Ignore
	public void cadastrar(){
		Embreagem embreagem = new Embreagem();
		embreagem.setMarca("TEste");
		embreagem.setDescricao("Embreagem Para Carros");
		embreagem.setTipo("Traseiro");
		embreagem.setValor(17.98d);
		embreagem.setQuantidade(10);
		
		FormaDeVenda formaDeVenda = new FormaDeVenda();
		formaDeVenda.setId(1);
		
		embreagem.setFormaDeVenda(formaDeVenda);
		
		new EmbreagemDAO().cadastrar(embreagem);
		
		
	}
	
	@Test
	@Ignore
	public void excluir(){
		Embreagem embreagem = new Embreagem();
		embreagem.setId(3);
		
		new EmbreagemDAO().excluir(embreagem);
		
	}
	
	@Test
	@Ignore
	public void editar(){
		Embreagem embreagem = new Embreagem();
		embreagem.setId(2);
		embreagem.setMarca("Cofab");
		embreagem.setDescricao("Embreagem Para Carros");
		embreagem.setTipo("Dianteiro");
		embreagem.setValor(17.98d);
		embreagem.setQuantidade(10);
		
		FormaDeVenda formaDeVenda = new FormaDeVenda();
		formaDeVenda.setId(1);
		
		embreagem.setFormaDeVenda(formaDeVenda);
		
		new EmbreagemDAO().editar(embreagem);
		
		
	}
	
	@Test
	@Ignore
	public void pesquisarPorID(){
		Embreagem embreagem = new Embreagem();
		embreagem.setId(1);
		
		embreagem = new EmbreagemDAO().pesquisarPorID(embreagem);
		
		System.out.println(embreagem);
		
	}
	
	@Test
	@Ignore
	public void pesquisarPorMarca(){
		Embreagem embreagem = new Embreagem();
		embreagem.setMarca("Cofab");
		
		List<Embreagem> lista = new ArrayList<Embreagem>();
		
		lista = new EmbreagemDAO().pesquisarPorMarca(embreagem);
		
		for(Embreagem a : lista){
			System.out.println(a);
		}
		
	}
	
	@Test
	@Ignore
	public void listar(){
		
		List<EntidadeDominio> lista = new ArrayList<EntidadeDominio>();
		
		lista = new EmbreagemDAO().listarTodos();
		
		for(EntidadeDominio a : lista){
			System.out.println(a);
		}
		
	}
	
	@Test
	@Ignore
	public void pesquisarPorDescricaoMarcaTipo(){
		Embreagem embreagem = new Embreagem();
		
		String stringEmbreagem = "EMBREAGEM - LUK - HIDRÁULICA";
		
		embreagem = new EmbreagemDAO().pesquisarPorDescricaoMarcaTipo(stringEmbreagem);
		
		System.out.println(embreagem.getId());
		System.out.println(embreagem.getDescricao());
		System.out.println(embreagem.getMarca());
		System.out.println(embreagem.getTipo());
		System.out.println(embreagem.getValor());
		
	}
	
	
}
