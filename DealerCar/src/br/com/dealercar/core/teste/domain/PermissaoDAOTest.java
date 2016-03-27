package br.com.dealercar.core.teste.domain;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.dealercar.core.dao.PermissaoDAO;
import br.com.dealercar.domain.EntidadeDominio;

public class PermissaoDAOTest {
	
	private static PermissaoDAO perDao = new PermissaoDAO();
	
	@Test
	@Ignore
	public void listarTodos(){
		
		List<EntidadeDominio> lista = new ArrayList<EntidadeDominio>();
		lista = perDao.listarTodos();
		
		for(EntidadeDominio p : lista){
			System.out.println(p);
		}
		
	}
	

}
