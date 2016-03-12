package br.com.dealercar.teste.itensrevisao;

import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.dao.itensrevisao.CorreiaDentadaDAO;
import br.com.dealercar.domain.produtosrevisao.CorreiaDentada;
import br.com.dealercar.domain.produtosrevisao.FormaDeVenda;

public class CorreiaDentadaTest {

	public static void cadastrar(){
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
	
	public static void excluir(){
		CorreiaDentada correiaDentada = new CorreiaDentada();
		correiaDentada.setId(3);
		
		new CorreiaDentadaDAO().excluir(correiaDentada);
		
	}
	
	public static void editar(){
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
	
	public static void pesquisarPorID(){
		CorreiaDentada correiaDentada = new CorreiaDentada();
		correiaDentada.setId(1);
		
		correiaDentada = new CorreiaDentadaDAO().pesquisarPorID(correiaDentada);
		
		System.out.println(correiaDentada);
		
	}
	
	public static void pesquisarPorMarca(){
		CorreiaDentada correiaDentada = new CorreiaDentada();
		correiaDentada.setMarca("Cofab");
		
		List<CorreiaDentada> lista = new ArrayList<CorreiaDentada>();
		
		lista = new CorreiaDentadaDAO().pesquisarPorMarca(correiaDentada);
		
		for(CorreiaDentada a : lista){
			System.out.println(a);
		}
		
	}
	
	public static void listar(){
		
		List<CorreiaDentada> lista = new ArrayList<CorreiaDentada>();
		
		lista = new CorreiaDentadaDAO().listarTodos();
		
		for(CorreiaDentada a : lista){
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
