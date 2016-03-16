package br.com.dealercar.teste.itensrevisao;

import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.dao.itensrevisao.FiltroDeOleoMotorDAO;
import br.com.dealercar.domain.produtosrevisao.FiltroDeOleoMotor;
import br.com.dealercar.domain.produtosrevisao.FormaDeVenda;

public class FiltroDeOleoMotorDAOTest {

	public static void cadastrar(){
		FiltroDeOleoMotor filtroDeOleoMotor = new FiltroDeOleoMotor();
		filtroDeOleoMotor.setMarca("TEste");
		filtroDeOleoMotor.setDescricao("FiltroDeOleoMotor Para Carros");
		filtroDeOleoMotor.setTipo("Traseiro");
		filtroDeOleoMotor.setValor(17.98d);
		filtroDeOleoMotor.setQuantidade(10);
		
		FormaDeVenda formaDeVenda = new FormaDeVenda();
		formaDeVenda.setId(1);
		
		filtroDeOleoMotor.setFormaDeVenda(formaDeVenda);
		
		new FiltroDeOleoMotorDAO().cadastrar(filtroDeOleoMotor);
		
		
	}
	
	public static void excluir(){
		FiltroDeOleoMotor filtroDeOleoMotor = new FiltroDeOleoMotor();
		filtroDeOleoMotor.setId(3);
		
		new FiltroDeOleoMotorDAO().excluir(filtroDeOleoMotor);
		
	}
	
	public static void editar(){
		FiltroDeOleoMotor filtroDeOleoMotor = new FiltroDeOleoMotor();
		filtroDeOleoMotor.setId(2);
		filtroDeOleoMotor.setMarca("Cofab");
		filtroDeOleoMotor.setDescricao("FiltroDeOleoMotor Para Carros");
		filtroDeOleoMotor.setTipo("Dianteiro");
		filtroDeOleoMotor.setValor(17.98d);
		filtroDeOleoMotor.setQuantidade(10);
		
		FormaDeVenda formaDeVenda = new FormaDeVenda();
		formaDeVenda.setId(1);
		
		filtroDeOleoMotor.setFormaDeVenda(formaDeVenda);
		
		new FiltroDeOleoMotorDAO().editar(filtroDeOleoMotor);
		
		
	}
	
	public static void pesquisarPorID(){
		FiltroDeOleoMotor filtroDeOleoMotor = new FiltroDeOleoMotor();
		filtroDeOleoMotor.setId(1);
		
		filtroDeOleoMotor = new FiltroDeOleoMotorDAO().pesquisarPorID(filtroDeOleoMotor);
		
		System.out.println(filtroDeOleoMotor);
		
	}
	
	public static void pesquisarPorMarca(){
		FiltroDeOleoMotor filtroDeOleoMotor = new FiltroDeOleoMotor();
		filtroDeOleoMotor.setMarca("Cofab");
		
		List<FiltroDeOleoMotor> lista = new ArrayList<FiltroDeOleoMotor>();
		
		lista = new FiltroDeOleoMotorDAO().pesquisarPorMarca(filtroDeOleoMotor);
		
		for(FiltroDeOleoMotor a : lista){
			System.out.println(a);
		}
		
	}
	
	public static void listar(){
		
		List<FiltroDeOleoMotor> lista = new ArrayList<FiltroDeOleoMotor>();
		
		lista = new FiltroDeOleoMotorDAO().listarTodos();
		
		for(FiltroDeOleoMotor a : lista){
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
