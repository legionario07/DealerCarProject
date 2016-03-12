package br.com.dealercar.teste.itensrevisao;

import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.dao.itensrevisao.EmbreagemDAO;
import br.com.dealercar.domain.produtosrevisao.Embreagem;
import br.com.dealercar.domain.produtosrevisao.FormaDeVenda;

public class EmbreagemTest {

	public static void cadastrar(){
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
	
	public static void excluir(){
		Embreagem embreagem = new Embreagem();
		embreagem.setId(3);
		
		new EmbreagemDAO().excluir(embreagem);
		
	}
	
	public static void editar(){
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
	
	public static void pesquisarPorID(){
		Embreagem embreagem = new Embreagem();
		embreagem.setId(1);
		
		embreagem = new EmbreagemDAO().pesquisarPorID(embreagem);
		
		System.out.println(embreagem);
		
	}
	
	public static void pesquisarPorMarca(){
		Embreagem embreagem = new Embreagem();
		embreagem.setMarca("Cofab");
		
		List<Embreagem> lista = new ArrayList<Embreagem>();
		
		lista = new EmbreagemDAO().pesquisarPorMarca(embreagem);
		
		for(Embreagem a : lista){
			System.out.println(a);
		}
		
	}
	
	public static void listar(){
		
		List<Embreagem> lista = new ArrayList<Embreagem>();
		
		lista = new EmbreagemDAO().listarTodos();
		
		for(Embreagem a : lista){
			System.out.println(a);
		}
		
		
	}
	
	public static void main(String[] args) {
		
		cadastrar();
		//excluir();
		//editar();
		//pesquisarPorID();
		//pesquisarPorMarca();
		listar();
		
	}
	
}
