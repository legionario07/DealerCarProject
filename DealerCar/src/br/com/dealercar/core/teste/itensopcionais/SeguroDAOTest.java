package br.com.dealercar.core.teste.itensopcionais;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.dealercar.core.dao.itensopcionais.SeguroDAO;
import br.com.dealercar.core.dao.itensopcionais.TipoSeguroDAO;
import br.com.dealercar.domain.EntidadeDominio;
import br.com.dealercar.domain.itensopcionais.Seguro;
import br.com.dealercar.domain.itensopcionais.TipoSeguro;

public class SeguroDAOTest {

	@Test
	@Ignore
	public void cadastrar() {
		Seguro seguro = new Seguro();
		seguro.setDescricao("Liberty");

		TipoSeguro tipoSeguro = new TipoSeguro(1);
		tipoSeguro = new TipoSeguroDAO().pesquisarPorID(tipoSeguro);
		seguro.setTipoSeguro(tipoSeguro);
		seguro.setValor(10.00D);

		SeguroDAO seguroDao = new SeguroDAO();
		seguroDao.cadastrar(seguro);
	}

	@Test
	@Ignore
	public void listarTodos() {
		List<EntidadeDominio> lista = new ArrayList<EntidadeDominio>();

		SeguroDAO segDao = new SeguroDAO();
		lista = segDao.listarTodos();

		for (EntidadeDominio s : lista) {
			System.out.println(s);
		}
	}

	@Test
	@Ignore
	public void editar() {
		Seguro seguro = new Seguro();
		seguro.setCodigo(3);
		seguro.setDescricao("Porto Seguro");

		TipoSeguro tipoSeguro = new TipoSeguro(1);
		tipoSeguro = new TipoSeguroDAO().pesquisarPorID(tipoSeguro);
		seguro.setTipoSeguro(tipoSeguro);
		seguro.setValor(18.40D);

		SeguroDAO seguroDao = new SeguroDAO();
		seguroDao.editar(seguro);
	}
	 
	@Test
	@Ignore
	public void excluir() {
		Seguro seguro = new Seguro();
		
		seguro.setCodigo(3);

		SeguroDAO seguroDao = new SeguroDAO();
		seguroDao.excluir(seguro);
	}
	
	@Test
	@Ignore
	public void pesquisarPorCodigo() {
		Seguro seguro = new Seguro();
		seguro.setCodigo(4);
		
		SeguroDAO seguroDao = new SeguroDAO();
		System.out.println(seguroDao.pesquisarPorCodigo(seguro));
	}
	
	@Test
	public void pesquisarPorCodigoETipoSeguro() {
		Seguro seguro = new Seguro();
		seguro.setCodigo(4);
		TipoSeguro tipoSeguro = new TipoSeguro();
		tipoSeguro.setId(1);
		seguro.setTipoSeguro(tipoSeguro);
		
		SeguroDAO seguroDao = new SeguroDAO();
		System.out.println(seguroDao.pesquisarPorCodigoDoTipoSeguro(seguro));
	}


	@Test
	@Ignore
	public void listarApenasNomesDiferentes() {
		List<EntidadeDominio> lista = new ArrayList<EntidadeDominio>();

		SeguroDAO segDao = new SeguroDAO();
		lista = segDao.listarApenasNomesDiferentes();

		for (EntidadeDominio s : lista) {
			System.out.println(s);
		}
	}
	


}
