package br.com.dealercar.teste.itensopcionais;


import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.dao.itensopcionais.BebeConfortoDAO;
import br.com.dealercar.domain.itensopcionais.BebeConforto;

public class BebeConfortoDAOTest {
	
	public static void cadastrar() {

		BebeConforto bebeConforto = new BebeConforto("Teste 2", 45.00, "Teste", "98838484", 10);

		BebeConfortoDAO bebeConfortoDao = new BebeConfortoDAO();
		bebeConfortoDao.cadastrar(bebeConforto);

	}

	public static void editar() {
	
		BebeConforto bebeConforto = new BebeConforto("Teste", 40.00, "marca alterada", "293940", 19);
		bebeConforto.setCodigo(1);

		BebeConfortoDAO bebeConfortoDao = new BebeConfortoDAO();
		bebeConfortoDao.editar(bebeConforto);

	}

	public static void excluir() {
		
		BebeConforto bebeConforto = new BebeConforto(2);
		
		BebeConfortoDAO bebeConfortoDao = new BebeConfortoDAO();
		bebeConfortoDao.excluir(bebeConforto);

	}

	public static void listarTodos() {
		List<BebeConforto> lista = new ArrayList<BebeConforto>();
		
		BebeConfortoDAO bebeConfortoDao = new BebeConfortoDAO();
		
		lista = bebeConfortoDao.listarTodos();
		
		for(BebeConforto bebeConforto : lista){
			System.out.println(bebeConforto);
		}
		
	}

	public static void pesquisarPorID() {

		BebeConforto bebeConforto = new BebeConforto();
		bebeConforto.setCodigo(1);
		
		BebeConfortoDAO bebeConfortoDao = new BebeConfortoDAO();
		
		System.out.println(bebeConfortoDao.pesquisarPorID(bebeConforto));
		
	}
	 
	public static void main(String[] args) {
		
		//cadastrar();
		//editar();
		//excluir();
		//listarTodos();
		//pesquisarPorID();

	}

}
