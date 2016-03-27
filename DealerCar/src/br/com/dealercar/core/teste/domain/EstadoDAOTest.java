package br.com.dealercar.core.teste.domain;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.dealercar.core.dao.EstadoDAO;
import br.com.dealercar.domain.EntidadeDominio;
import br.com.dealercar.domain.Estado;

public class EstadoDAOTest {

	@Test
	@Ignore
	public void listarTodos(){

		List<EntidadeDominio> lista = new ArrayList<EntidadeDominio>();
		
		lista = new EstadoDAO().listarTodos();
		
		for(EntidadeDominio e : lista){
			System.out.println(e);
		}
		
	}
	
	@Test
	@Ignore
	public void pesquisarPorID(){
		
		Estado estado = new Estado();
		
		estado.setId(2);
		
		
		System.out.println(estado);
	}
	
}
