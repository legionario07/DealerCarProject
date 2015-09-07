package br.com.dealercar.teste.itensopcionais;

import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.dao.itensopcionais.TipoSeguroDAO;
import br.com.dealercar.domain.itensopcionais.TipoSeguro;
import br.com.dealercar.enums.SeguroType;

public class TipoSeguroDAOTest {

	@SuppressWarnings("unused")
	private static void cadastrar() {
	
		TipoSeguro tipoSeguro =  new TipoSeguro();
		tipoSeguro.setNome(SeguroType.COMPREENSIVA);
		
		TipoSeguroDAO tipoDao = new TipoSeguroDAO();
		tipoDao.cadastrar(tipoSeguro);
	}
	
	@SuppressWarnings("unused")
	private static void listarTodos() {
		List<TipoSeguro> lista = new ArrayList<TipoSeguro> ();
		
		TipoSeguroDAO tipoDao = new TipoSeguroDAO();
		lista = tipoDao.listarTodos();
		
		for(TipoSeguro ts : lista) {
			System.out.println(ts);
		}
	}
	
	@SuppressWarnings("unused")
	private static void editar() {
		TipoSeguro tipo = new TipoSeguro();
		tipo.setId(1);
		tipo.setNome(SeguroType.COMPREENSIVA);
		
		TipoSeguroDAO tipoDao = new TipoSeguroDAO();
		tipoDao.editar(tipo);
	}
	
	@SuppressWarnings("unused")
	private static void pesquisarPorId() {
		TipoSeguro tipo = new TipoSeguro();
		tipo.setId(2);
		
		TipoSeguroDAO tipoDao = new TipoSeguroDAO();
		
		System.out.println(tipoDao.pesquisarPorID(tipo));
	}
	
	public static void main(String[] args) {
		
		//cadastrar();
		
		//editar();
		//listarTodos();
		//pesquisarPorId();

	}

}
