package br.com.dealercar.core.teste.itensopcionais;


import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.dealercar.core.dao.itensopcionais.BebeConfortoDAO;
import br.com.dealercar.domain.EntidadeDominio;
import br.com.dealercar.domain.itensopcionais.BebeConforto;

public class BebeConfortoDAOTest {
	
	@Test
	@Ignore
	public void cadastrar() {

		BebeConforto bebeConforto = new BebeConforto("Teste 2", 45.00, "Teste", "98838484", 10);

		BebeConfortoDAO bebeConfortoDao = new BebeConfortoDAO();
		bebeConfortoDao.cadastrar(bebeConforto);

	}

	@Test
	@Ignore
	public void editar() {
	
		BebeConforto bebeConforto = new BebeConforto("Teste", 40.00, "marca alterada", "293940", 19);
		bebeConforto.setCodigo(1);

		BebeConfortoDAO bebeConfortoDao = new BebeConfortoDAO();
		bebeConfortoDao.editar(bebeConforto);

	}

	@Test
	@Ignore
	public void excluir() {
		
		BebeConforto bebeConforto = new BebeConforto(2);
		
		BebeConfortoDAO bebeConfortoDao = new BebeConfortoDAO();
		bebeConfortoDao.excluir(bebeConforto);

	}

	@Test
	@Ignore
	public void listarTodos() {
		List<EntidadeDominio> lista = new ArrayList<EntidadeDominio>();
		
		BebeConfortoDAO bebeConfortoDao = new BebeConfortoDAO();
		
		lista = bebeConfortoDao.listarTodos();
		
		for(EntidadeDominio bebeConforto : lista){
			System.out.println(bebeConforto);
		}
		
	}

	@Test
	@Ignore
	public void pesquisarPorID() {

		BebeConforto bebeConforto = new BebeConforto();
		bebeConforto.setCodigo(1);
		
		BebeConfortoDAO bebeConfortoDao = new BebeConfortoDAO();
		
		System.out.println(bebeConfortoDao.pesquisarPorID(bebeConforto));
		
	}
	 

}
