package br.com.dealercar.teste.itensrevisao;

import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.dao.itensrevisao.AmortecedorDAO;
import br.com.dealercar.domain.produtosrevisao.Amortecedor;
import br.com.dealercar.domain.produtosrevisao.FormaDeVenda;

public class AmortecedorTest {

	public static void cadastrar(){
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
	
	public static void excluir(){
		Amortecedor amortecedor = new Amortecedor();
		amortecedor.setId(3);
		
		new AmortecedorDAO().excluir(amortecedor);
		
	}
	
	public static void editar(){
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
	
	public static void pesquisarPorID(){
		Amortecedor amortecedor = new Amortecedor();
		amortecedor.setId(1);
		
		amortecedor = new AmortecedorDAO().pesquisarPorID(amortecedor);
		
		System.out.println(amortecedor);
		
	}
	
	public static void pesquisarPorMarca(){
		Amortecedor amortecedor = new Amortecedor();
		amortecedor.setMarca("Cofab");
		
		List<Amortecedor> lista = new ArrayList<Amortecedor>();
		
		lista = new AmortecedorDAO().pesquisarPorMarca(amortecedor);
		
		for(Amortecedor a : lista){
			System.out.println(a);
		}
		
	}
	
	public static void listar(){
		
		List<Amortecedor> lista = new ArrayList<Amortecedor>();
		
		lista = new AmortecedorDAO().listarTodos();
		
		for(Amortecedor a : lista){
			System.out.println(a);
		}
		
		
	}
	
	public static void main(String[] args) {
		
		//cadastrar();
		//excluir();
		//editar();
		pesquisarPorID();
		//pesquisarPorMarca();
		//listar();
		
	}
	
}
