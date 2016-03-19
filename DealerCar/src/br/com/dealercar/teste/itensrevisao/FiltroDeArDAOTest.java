package br.com.dealercar.teste.itensrevisao;

import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.dao.itensrevisao.FiltroDeArDAO;
import br.com.dealercar.domain.produtosrevisao.FiltroDeAr;
import br.com.dealercar.domain.produtosrevisao.FormaDeVenda;

public class FiltroDeArDAOTest {

	public static void cadastrar(){
		FiltroDeAr filtroDeAr = new FiltroDeAr();
		filtroDeAr.setMarca("TEste");
		filtroDeAr.setDescricao("FiltroDeAr Para Carros");
		filtroDeAr.setTipo("Traseiro");
		filtroDeAr.setValor(17.98d);
		filtroDeAr.setQuantidade(10);
		
		FormaDeVenda formaDeVenda = new FormaDeVenda();
		formaDeVenda.setId(1);
		
		filtroDeAr.setFormaDeVenda(formaDeVenda);
		
		new FiltroDeArDAO().cadastrar(filtroDeAr);
		
		
	}
	
	public static void excluir(){
		FiltroDeAr filtroDeAr = new FiltroDeAr();
		filtroDeAr.setId(3);
		
		new FiltroDeArDAO().excluir(filtroDeAr);
		
	}
	
	public static void editar(){
		FiltroDeAr filtroDeAr = new FiltroDeAr();
		filtroDeAr.setId(2);
		filtroDeAr.setMarca("Cofab");
		filtroDeAr.setDescricao("FiltroDeAr Para Carros");
		filtroDeAr.setTipo("Dianteiro");
		filtroDeAr.setValor(17.98d);
		filtroDeAr.setQuantidade(10);
		
		FormaDeVenda formaDeVenda = new FormaDeVenda();
		formaDeVenda.setId(1);
		
		filtroDeAr.setFormaDeVenda(formaDeVenda);
		
		new FiltroDeArDAO().editar(filtroDeAr);
		
		
	}
	
	public static void pesquisarPorID(){
		FiltroDeAr filtroDeAr = new FiltroDeAr();
		filtroDeAr.setId(1);
		
		filtroDeAr = new FiltroDeArDAO().pesquisarPorID(filtroDeAr);
		
		System.out.println(filtroDeAr);
		
	}
	
	public static void pesquisarPorMarca(){
		FiltroDeAr filtroDeAr = new FiltroDeAr();
		filtroDeAr.setMarca("Cofab");
		
		List<FiltroDeAr> lista = new ArrayList<FiltroDeAr>();
		
		lista = new FiltroDeArDAO().pesquisarPorMarca(filtroDeAr);
		
		for(FiltroDeAr a : lista){
			System.out.println(a);
		}
		
	}
	
	public static void listar(){
		
		List<FiltroDeAr> lista = new ArrayList<FiltroDeAr>();
		
		lista = new FiltroDeArDAO().listarTodos();
		
		for(FiltroDeAr a : lista){
			System.out.println(a);
		}
		
	}
	
	public static void pesquisarPorDescricaoMarcaTipo(){
		FiltroDeAr filtroDeAr = new FiltroDeAr();
		
		String stringFiltroDeAr = "FILTRO DE AR - FRAM - MOTOR EM GERAL";
		
		filtroDeAr = new FiltroDeArDAO().pesquisarPorDescricaoMarcaTipo(stringFiltroDeAr);
		
		System.out.println(filtroDeAr.getId());
		System.out.println(filtroDeAr.getDescricao());
		System.out.println(filtroDeAr.getMarca());
		System.out.println(filtroDeAr.getTipo());
		System.out.println(filtroDeAr.getValor());
		
	}
	

	
	public static void main(String[] args) {
		
		//cadastrar();
		//excluir();
		//editar();
		//pesquisarPorID();
		//pesquisarPorMarca();
		//listar();
		pesquisarPorDescricaoMarcaTipo();
		
	}
	
}
