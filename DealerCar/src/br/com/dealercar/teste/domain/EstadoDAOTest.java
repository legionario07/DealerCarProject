package br.com.dealercar.teste.domain;

import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.dao.EstadoDAO;
import br.com.dealercar.domain.Estado;

public class EstadoDAOTest {

	private static void listarTodos(){

		List<Estado> lista = new ArrayList<Estado>();
		
		lista = new EstadoDAO().listarTodos();
		
		for(Estado e : lista){
			System.out.println(e);
		}
		
	}
	
	public static void pesquisarPorID(){
		
		Estado estado = new Estado();
		
		estado.setId(2);
		
		estado = new EstadoDAO().pesquisarPorID(estado);
		
		System.out.println(estado);
	}
	
	public static void main(String[] args) {
		
		listarTodos();
		pesquisarPorID();
		
	}
}
