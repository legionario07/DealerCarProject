package br.com.dealercar.teste.itensrevisao;

import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.dao.itensrevisao.PastilhaFreioDAO;
import br.com.dealercar.domain.produtosrevisao.FormaDeVenda;
import br.com.dealercar.domain.produtosrevisao.PastilhaFreio;

public class PastilhaFreioDAOTest {

	public static void cadastrar(){
		PastilhaFreio pastilhaFreio = new PastilhaFreio();
		pastilhaFreio.setMarca("TEste 2");
		pastilhaFreio.setDescricao("PastilhaFreio Para Carros");
		pastilhaFreio.setTipo("Traseiro");
		pastilhaFreio.setValor(17.98d);
		pastilhaFreio.setQuantidade(10);
		
		FormaDeVenda formaDeVenda = new FormaDeVenda();
		formaDeVenda.setId(1);
		
		pastilhaFreio.setFormaDeVenda(formaDeVenda);
		
		new PastilhaFreioDAO().cadastrar(pastilhaFreio);
		
	}
	
	public static void excluir(){
		PastilhaFreio pastilhaFreio = new PastilhaFreio();
		pastilhaFreio.setId(3);
		
		new PastilhaFreioDAO().excluir(pastilhaFreio);
		
	}
	
	public static void editar(){
		PastilhaFreio pastilhaFreio = new PastilhaFreio();
		pastilhaFreio.setId(2);
		pastilhaFreio.setMarca("Cofab");
		pastilhaFreio.setDescricao("PastilhaFreio Para Carros");
		pastilhaFreio.setTipo("Dianteiro");
		pastilhaFreio.setValor(17.98d);
		pastilhaFreio.setQuantidade(10);
		
		FormaDeVenda formaDeVenda = new FormaDeVenda();
		formaDeVenda.setId(1);
		
		pastilhaFreio.setFormaDeVenda(formaDeVenda);
		
		new PastilhaFreioDAO().editar(pastilhaFreio);
		
	}
	
	public static void pesquisarPorID(){
		PastilhaFreio pastilhaFreio = new PastilhaFreio();
		pastilhaFreio.setId(1);
		
		pastilhaFreio = new PastilhaFreioDAO().pesquisarPorID(pastilhaFreio);
		
		System.out.println(pastilhaFreio);
		
	}
	
	public static void pesquisarPorMarca(){
		PastilhaFreio pastilhaFreio = new PastilhaFreio();
		pastilhaFreio.setMarca("Cofab");
		
		List<PastilhaFreio> lista = new ArrayList<PastilhaFreio>();
		
		lista = new PastilhaFreioDAO().pesquisarPorMarca(pastilhaFreio);
		
		for(PastilhaFreio a : lista){
			System.out.println(a);
		}
	}
	
	public static void pesquisarPorDescricaoMarcaTipo(){
		PastilhaFreio pastilhaFreio = new PastilhaFreio();
		
		String stringPastilhaFreio = "PASTILHAS DE FREIO (MECÂNICA) - BOSH - DIANTEIRO";
		
		pastilhaFreio = new PastilhaFreioDAO().pesquisarPorDescricaoMarcaTipo(stringPastilhaFreio);
		
		System.out.println(pastilhaFreio.getId());
		System.out.println(pastilhaFreio.getDescricao());
		System.out.println(pastilhaFreio.getMarca());
		System.out.println(pastilhaFreio.getTipo());
		System.out.println(pastilhaFreio.getValor());
		
	}
	
	public static void listar(){
		
		List<PastilhaFreio> lista = new ArrayList<PastilhaFreio>();
		
		lista = new PastilhaFreioDAO().listarTodos();
		
		for(PastilhaFreio a : lista){
			System.out.println(a);
		}
		
		
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
