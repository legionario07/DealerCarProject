package br.com.dealercar.teste;

import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.dao.CoresDAO;
import br.com.dealercar.domain.Cor;

public class CoresDAOTest {

	@SuppressWarnings("unused")
	private static void listarTodos(){
		CoresDAO corDao = new CoresDAO();
		List<Cor> lista = new ArrayList<Cor>();
		lista = corDao.listarTodos();
		
		for(Cor cor : lista){
			System.out.println(cor);
		}
	}
	
	public static void main(String[] args) {
		
	//	listarTodos();
	}

}
