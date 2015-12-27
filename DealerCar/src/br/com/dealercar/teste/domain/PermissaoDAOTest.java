package br.com.dealercar.teste.domain;

import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.autenticacao.Permissao;
import br.com.dealercar.dao.PermissaoDAO;

public class PermissaoDAOTest {
	
	private static PermissaoDAO perDao = new PermissaoDAO();
	
	public static void listarTodos(){
		
		List<Permissao> lista = new ArrayList<Permissao>();
		lista = perDao.listarTodos();
		
		for(Permissao p : lista){
			System.out.println(p);
		}
		
	}
	
	public static void main(String[] args) {
	
		listarTodos();
	}

}
