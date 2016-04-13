package br.com.dealercar.core.teste.itensrevisao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.dealercar.core.dao.itensrevisao.AmortecedorDAO;
import br.com.dealercar.domain.EntidadeDominio;
import br.com.dealercar.domain.produtosrevisao.Amortecedor;
import br.com.dealercar.domain.produtosrevisao.FormaDeVenda;

public class AmortecedorDAOTest {

	@Test
	@Ignore
	public void cadastrar(){
		Amortecedor amortecedor = new Amortecedor();
		amortecedor.setMarca("TEste");
		amortecedor.setDescricao("Amortecedor Para Carros");
		amortecedor.setTipo("Traseiro");
		amortecedor.setValor(17.98d);
		amortecedor.setQuantidade(10);
		
		FormaDeVenda formaDeVenda = new FormaDeVenda();
		formaDeVenda.setId(1);
		
		amortecedor.setFormaDeVenda(formaDeVenda);
		
		new AmortecedorDAO().cadastrar(amortecedor);
		
		
	}
	
	@Test
	@Ignore
	public void excluir(){
		Amortecedor amortecedor = new Amortecedor();
		amortecedor.setId(3);
		
		new AmortecedorDAO().excluir(amortecedor);
		
	}
	
	@Test
	@Ignore
	public void editar(){
		Amortecedor amortecedor = new Amortecedor();
		amortecedor.setId(2);
		amortecedor.setMarca("Cofab");
		amortecedor.setDescricao("Amortecedor Para Carros");
		amortecedor.setTipo("Dianteiro");
		amortecedor.setValor(17.98d);
		amortecedor.setQuantidade(10);
		
		FormaDeVenda formaDeVenda = new FormaDeVenda();
		formaDeVenda.setId(1);
		
		amortecedor.setFormaDeVenda(formaDeVenda);
		
		new AmortecedorDAO().editar(amortecedor);
		
		
	}
	
	
	@Test
	@Ignore
	public void pesquisarPorID(){
		Amortecedor amortecedor = new Amortecedor();
		amortecedor.setId(1);
		
		amortecedor = new AmortecedorDAO().pesquisarPorID(amortecedor);
		
		System.out.println(amortecedor);
		
	}
	
	@Test
	@Ignore
	public void pesquisarPorMarca(){
		Amortecedor amortecedor = new Amortecedor();
		amortecedor.setMarca("Cofab");
		
		List<Amortecedor> lista = new ArrayList<Amortecedor>();
		
		lista = new AmortecedorDAO().pesquisarPorMarca(amortecedor);
		
		for(Amortecedor a : lista){
			System.out.println(a);
		}
		
	}
	
	@Test
	@Ignore
	public void pesquisarPorDescricaoMarcaTipo(){
		Amortecedor amortecedor = new Amortecedor();
		
		String stringAmortecedor = "AMORTECEDOR - COFAB - DIANTEIRO";
		
		amortecedor = new AmortecedorDAO().pesquisarPorDescricaoMarcaTipo(stringAmortecedor);
		
		System.out.println(amortecedor.getId());
		System.out.println(amortecedor.getDescricao());
		System.out.println(amortecedor.getMarca());
		System.out.println(amortecedor.getTipo());
		System.out.println(amortecedor.getValor());
		
	}
	
	@Test
	public void listar(){
		
		List<EntidadeDominio> lista = new ArrayList<EntidadeDominio>();
		
		lista = new AmortecedorDAO().listarTodos();
		
		for(EntidadeDominio a : lista){
			System.out.println(a);
		}
		
		
	}
	
}
