package br.com.dealercar.teste.itensrevisao;

import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.dao.itensrevisao.FluidoDeFreioDAO;
import br.com.dealercar.domain.produtosrevisao.FluidoDeFreio;
import br.com.dealercar.domain.produtosrevisao.FormaDeVenda;

public class FluidoDeFreioTest {

	public static void cadastrar(){
		FluidoDeFreio fluidoDeFreio = new FluidoDeFreio();
		fluidoDeFreio.setMarca("TEste");
		fluidoDeFreio.setDescricao("FluidoDeFreio Para Carros");
		fluidoDeFreio.setTipo("Traseiro");
		fluidoDeFreio.setValor(17.98d);
		fluidoDeFreio.setQuantidade(10);
		
		FormaDeVenda formaDeVenda = new FormaDeVenda();
		formaDeVenda.setId(1);
		
		fluidoDeFreio.setFormaDeVenda(formaDeVenda);
		
		new FluidoDeFreioDAO().cadastrar(fluidoDeFreio);
		
		
	}
	
	public static void excluir(){
		FluidoDeFreio fluidoDeFreio = new FluidoDeFreio();
		fluidoDeFreio.setId(3);
		
		new FluidoDeFreioDAO().excluir(fluidoDeFreio);
		
	}
	
	public static void editar(){
		FluidoDeFreio fluidoDeFreio = new FluidoDeFreio();
		fluidoDeFreio.setId(2);
		fluidoDeFreio.setMarca("Cofab");
		fluidoDeFreio.setDescricao("FluidoDeFreio Para Carros");
		fluidoDeFreio.setTipo("Dianteiro");
		fluidoDeFreio.setValor(17.98d);
		fluidoDeFreio.setQuantidade(10);
		
		FormaDeVenda formaDeVenda = new FormaDeVenda();
		formaDeVenda.setId(1);
		
		fluidoDeFreio.setFormaDeVenda(formaDeVenda);
		
		new FluidoDeFreioDAO().editar(fluidoDeFreio);
		
		
	}
	
	public static void pesquisarPorID(){
		FluidoDeFreio fluidoDeFreio = new FluidoDeFreio();
		fluidoDeFreio.setId(1);
		
		fluidoDeFreio = new FluidoDeFreioDAO().pesquisarPorID(fluidoDeFreio);
		
		System.out.println(fluidoDeFreio);
		
	}
	
	public static void pesquisarPorMarca(){
		FluidoDeFreio fluidoDeFreio = new FluidoDeFreio();
		fluidoDeFreio.setMarca("Cofab");
		
		List<FluidoDeFreio> lista = new ArrayList<FluidoDeFreio>();
		
		lista = new FluidoDeFreioDAO().pesquisarPorMarca(fluidoDeFreio);
		
		for(FluidoDeFreio a : lista){
			System.out.println(a);
		}
		
	}
	
	public static void listar(){
		
		List<FluidoDeFreio> lista = new ArrayList<FluidoDeFreio>();
		
		lista = new FluidoDeFreioDAO().listarTodos();
		
		for(FluidoDeFreio a : lista){
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
