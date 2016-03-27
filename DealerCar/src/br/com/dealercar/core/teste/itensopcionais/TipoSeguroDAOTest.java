package br.com.dealercar.core.teste.itensopcionais;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.dealercar.core.dao.itensopcionais.TipoSeguroDAO;
import br.com.dealercar.domain.EntidadeDominio;
import br.com.dealercar.domain.enums.SeguroType;
import br.com.dealercar.domain.itensopcionais.TipoSeguro;

public class TipoSeguroDAOTest {

	@Test
	@Ignore
	public void cadastrar() {
	
		TipoSeguro tipoSeguro =  new TipoSeguro();
		tipoSeguro.setNome(SeguroType.COMPREENSIVA);
		
		TipoSeguroDAO tipoDao = new TipoSeguroDAO();
		tipoDao.cadastrar(tipoSeguro);
	}

	@Test
	@Ignore
	public void listarTodos() {
		List<EntidadeDominio> lista = new ArrayList<EntidadeDominio> ();
		
		TipoSeguroDAO tipoDao = new TipoSeguroDAO();
		lista = tipoDao.listarTodos();
		
		for(EntidadeDominio ts : lista) {
			System.out.println(ts);
		}
	}
	
	@Test
	@Ignore
	public void editar() {
		TipoSeguro tipo = new TipoSeguro();
		tipo.setId(1);
		tipo.setNome(SeguroType.COMPREENSIVA);
		
		TipoSeguroDAO tipoDao = new TipoSeguroDAO();
		tipoDao.editar(tipo);
	}
	
	@Test
	@Ignore
	public void pesquisarPorId() {
		TipoSeguro tipo = new TipoSeguro();
		tipo.setId(2);
		
		TipoSeguroDAO tipoDao = new TipoSeguroDAO();
		
		System.out.println(tipoDao.pesquisarPorID(tipo));
	}
	

}
