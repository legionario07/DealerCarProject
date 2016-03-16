package br.com.dealercar.teste.itensrevisao;

import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.dao.itensrevisao.VelasIgnicaoDAO;
import br.com.dealercar.domain.produtosrevisao.VelasIgnicao;
import br.com.dealercar.domain.produtosrevisao.FormaDeVenda;

public class VelasIgnicaoDAOTest {

	public static void cadastrar(){
		VelasIgnicao velasIgnicao = new VelasIgnicao();
		velasIgnicao.setMarca("TEste");
		velasIgnicao.setDescricao("VelasIgnicao Para Carros");
		velasIgnicao.setTipo("Traseiro");
		velasIgnicao.setValor(17.98d);
		velasIgnicao.setQuantidade(10);
		
		FormaDeVenda formaDeVenda = new FormaDeVenda();
		formaDeVenda.setId(1);
		
		velasIgnicao.setFormaDeVenda(formaDeVenda);
		
		new VelasIgnicaoDAO().cadastrar(velasIgnicao);
		
		
	}
	
	public static void excluir(){
		VelasIgnicao velasIgnicao = new VelasIgnicao();
		velasIgnicao.setId(3);
		
		new VelasIgnicaoDAO().excluir(velasIgnicao);
		
	}
	
	public static void editar(){
		VelasIgnicao velasIgnicao = new VelasIgnicao();
		velasIgnicao.setId(2);
		velasIgnicao.setMarca("Cofab");
		velasIgnicao.setDescricao("VelasIgnicao Para Carros");
		velasIgnicao.setTipo("Dianteiro");
		velasIgnicao.setValor(17.98d);
		velasIgnicao.setQuantidade(10);
		
		FormaDeVenda formaDeVenda = new FormaDeVenda();
		formaDeVenda.setId(1);
		
		velasIgnicao.setFormaDeVenda(formaDeVenda);
		
		new VelasIgnicaoDAO().editar(velasIgnicao);
		
		
	}
	
	public static void pesquisarPorID(){
		VelasIgnicao velasIgnicao = new VelasIgnicao();
		velasIgnicao.setId(1);
		
		velasIgnicao = new VelasIgnicaoDAO().pesquisarPorID(velasIgnicao);
		
		System.out.println(velasIgnicao);
		
	}
	
	public static void pesquisarPorMarca(){
		VelasIgnicao velasIgnicao = new VelasIgnicao();
		velasIgnicao.setMarca("Cofab");
		
		List<VelasIgnicao> lista = new ArrayList<VelasIgnicao>();
		
		lista = new VelasIgnicaoDAO().pesquisarPorMarca(velasIgnicao);
		
		for(VelasIgnicao a : lista){
			System.out.println(a);
		}
		
	}
	
	public static void listar(){
		
		List<VelasIgnicao> lista = new ArrayList<VelasIgnicao>();
		
		lista = new VelasIgnicaoDAO().listarTodos();
		
		for(VelasIgnicao a : lista){
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
