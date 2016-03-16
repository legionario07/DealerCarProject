package br.com.dealercar.teste.itensrevisao;

import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.dao.itensrevisao.PneuDAO;
import br.com.dealercar.domain.produtosrevisao.Pneu;
import br.com.dealercar.domain.produtosrevisao.FormaDeVenda;

public class PneuDAOTest {

	public static void cadastrar(){
		Pneu pneu = new Pneu();
		pneu.setMarca("TEste");
		pneu.setDescricao("Pneu Para Carros");
		pneu.setTipo("Traseiro");
		pneu.setValor(17.98d);
		pneu.setQuantidade(10);
		
		FormaDeVenda formaDeVenda = new FormaDeVenda();
		formaDeVenda.setId(1);
		
		pneu.setFormaDeVenda(formaDeVenda);
		
		new PneuDAO().cadastrar(pneu);
		
		
	}
	
	public static void excluir(){
		Pneu pneu = new Pneu();
		pneu.setId(3);
		
		new PneuDAO().excluir(pneu);
		
	}
	
	public static void editar(){
		Pneu pneu = new Pneu();
		pneu.setId(2);
		pneu.setMarca("Cofab");
		pneu.setDescricao("Pneu Para Carros");
		pneu.setTipo("Dianteiro");
		pneu.setValor(17.98d);
		pneu.setQuantidade(10);
		
		FormaDeVenda formaDeVenda = new FormaDeVenda();
		formaDeVenda.setId(1);
		
		pneu.setFormaDeVenda(formaDeVenda);
		
		new PneuDAO().editar(pneu);
		
		
	}
	
	public static void pesquisarPorID(){
		Pneu pneu = new Pneu();
		pneu.setId(1);
		
		pneu = new PneuDAO().pesquisarPorID(pneu);
		
		System.out.println(pneu);
		
	}
	
	public static void pesquisarPorMarca(){
		Pneu pneu = new Pneu();
		pneu.setMarca("Cofab");
		
		List<Pneu> lista = new ArrayList<Pneu>();
		
		lista = new PneuDAO().pesquisarPorMarca(pneu);
		
		for(Pneu a : lista){
			System.out.println(a);
		}
		
	}
	
	public static void listar(){
		
		List<Pneu> lista = new ArrayList<Pneu>();
		
		lista = new PneuDAO().listarTodos();
		
		for(Pneu a : lista){
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
