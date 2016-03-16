package br.com.dealercar.teste.itensrevisao;

import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.dao.itensrevisao.FarolDAO;
import br.com.dealercar.domain.produtosrevisao.Farol;
import br.com.dealercar.domain.produtosrevisao.FormaDeVenda;

public class FarolDAOTest {

	public static void cadastrar(){
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
	
	public static void excluir(){
		Farol farol = new Farol();
		farol.setId(3);
		
		new FarolDAO().excluir(farol);
		
	}
	
	public static void editar(){
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
	
	public static void pesquisarPorID(){
		Farol farol = new Farol();
		farol.setId(1);
		
		farol = new FarolDAO().pesquisarPorID(farol);
		
		System.out.println(farol);
		
	}
	
	public static void pesquisarPorMarca(){
		Farol farol = new Farol();
		farol.setMarca("Cofab");
		
		List<Farol> lista = new ArrayList<Farol>();
		
		lista = new FarolDAO().pesquisarPorMarca(farol);
		
		for(Farol a : lista){
			System.out.println(a);
		}
		
	}
	
	public static void listar(){
		
		List<Farol> lista = new ArrayList<Farol>();
		
		lista = new FarolDAO().listarTodos();
		
		for(Farol a : lista){
			System.out.println(a);
		}
		
		
	}
	
	public static void main(String[] args) {
		
		//cadastrar();
		//excluir();
		//editar();
		//pesquisarPorID();
		//pesquisarPorMarca();
		listar();
		
	}
	
}
