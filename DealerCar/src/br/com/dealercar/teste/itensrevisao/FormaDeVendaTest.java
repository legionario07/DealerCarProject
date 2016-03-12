package br.com.dealercar.teste.itensrevisao;

import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.dao.itensrevisao.FormaDeVendaDAO;
import br.com.dealercar.domain.produtosrevisao.FormaDeVenda;

public class FormaDeVendaTest {

	public static void cadastrar(){
		FormaDeVenda formaDeVenda =  new FormaDeVenda();
		formaDeVenda.setDescricao("Teste");
		new FormaDeVendaDAO().cadastrar(formaDeVenda);
	}
	
	public static void editar(){
		FormaDeVenda formaDeVenda =  new FormaDeVenda();
		formaDeVenda.setId(3);
		formaDeVenda.setDescricao("Teste2");
		new FormaDeVendaDAO().editar(formaDeVenda);
	}
	
	public static void listar(){
		
		List<FormaDeVenda> lista = new ArrayList<FormaDeVenda>();
		lista = new FormaDeVendaDAO().listarTodos();
		
		for(FormaDeVenda f : lista){
			System.out.println(f);
		}
		
	}
	
	public static void pesquisarPorId(){
		
		FormaDeVenda formaDeVenda = new FormaDeVenda();
		formaDeVenda.setId(1);
		
		formaDeVenda = new FormaDeVendaDAO().pesquisarPorID(formaDeVenda);
		
		System.out.println(formaDeVenda);
		
	}
	
	public static void excluir(){
		
		FormaDeVenda formaDeVenda = new FormaDeVenda();
		formaDeVenda.setId(3);
		
		new FormaDeVendaDAO().excluir(formaDeVenda);
		
	}
	
	
	
	public static void main(String[] args) {
		//cadastrar();
		pesquisarPorId();
		//listar();
		//editar();
	//	excluir();
	}
}
