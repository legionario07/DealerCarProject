package br.com.dealercar.core.teste.itensrevisao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.dealercar.core.dao.itensrevisao.CorreiaDentadaDAO;
import br.com.dealercar.domain.EntidadeDominio;
import br.com.dealercar.domain.produtosrevisao.CorreiaDentada;
import br.com.dealercar.domain.produtosrevisao.FormaDeVenda;

public class CorreiaDentadaDAOTest {

	@Test
	@Ignore
	public void cadastrar(){
		CorreiaDentada correiaDentada = new CorreiaDentada();
		correiaDentada.setMarca("TEste");
		correiaDentada.setDescricao("CorreiaDentada Para Carros");
		correiaDentada.setTipo("Traseiro");
		correiaDentada.setValor(17.98d);
		correiaDentada.setQuantidade(10);
		
		FormaDeVenda formaDeVenda = new FormaDeVenda();
		formaDeVenda.setId(1);
		
		correiaDentada.setFormaDeVenda(formaDeVenda);
		
		new CorreiaDentadaDAO().cadastrar(correiaDentada);
		
		
	}
	
	@Test
	@Ignore
	public void excluir(){
		CorreiaDentada correiaDentada = new CorreiaDentada();
		correiaDentada.setId(3);
		
		new CorreiaDentadaDAO().excluir(correiaDentada);
		
	}
	
	@Test
	@Ignore
	public void editar(){
		CorreiaDentada correiaDentada = new CorreiaDentada();
		correiaDentada.setId(2);
		correiaDentada.setMarca("Cofab");
		correiaDentada.setDescricao("CorreiaDentada Para Carros");
		correiaDentada.setTipo("Dianteiro");
		correiaDentada.setValor(17.98d);
		correiaDentada.setQuantidade(10);
		
		FormaDeVenda formaDeVenda = new FormaDeVenda();
		formaDeVenda.setId(1);
		
		correiaDentada.setFormaDeVenda(formaDeVenda);
		
		new CorreiaDentadaDAO().editar(correiaDentada);
		
		
	}
	
	@Test
	@Ignore
	public void pesquisarPorID(){
		CorreiaDentada correiaDentada = new CorreiaDentada();
		correiaDentada.setId(1);
		
		correiaDentada = new CorreiaDentadaDAO().pesquisarPorID(correiaDentada);
		
		System.out.println(correiaDentada);
		
	}
	
	@Test
	@Ignore
	public void pesquisarPorMarca(){
		CorreiaDentada correiaDentada = new CorreiaDentada();
		correiaDentada.setMarca("Cofab");
		
		List<CorreiaDentada> lista = new ArrayList<CorreiaDentada>();
		
		lista = new CorreiaDentadaDAO().pesquisarPorMarca(correiaDentada);
		
		for(CorreiaDentada a : lista){
			System.out.println(a);
		}
		
	}
	
	@Test
	@Ignore
	public void listar(){
		
		List<EntidadeDominio> lista = new ArrayList<EntidadeDominio>();
		
		lista = new CorreiaDentadaDAO().listarTodos();
		
		for(EntidadeDominio a : lista){
			System.out.println(a);
		}
		
	}
	
	@Test
	@Ignore
	public void pesquisarPorDescricaoMarcaTipo(){
		CorreiaDentada correiaDentada = new CorreiaDentada();
		
		String stringCorreiaDentada = "CORREIA DENTADA - DAYCO - MOTOR EM GERAL";
		
		correiaDentada = new CorreiaDentadaDAO().pesquisarPorDescricaoMarcaTipo(stringCorreiaDentada);
		
		System.out.println(correiaDentada.getId());
		System.out.println(correiaDentada.getDescricao());
		System.out.println(correiaDentada.getMarca());
		System.out.println(correiaDentada.getTipo());
		System.out.println(correiaDentada.getValor());
		
	}
	
	
}
