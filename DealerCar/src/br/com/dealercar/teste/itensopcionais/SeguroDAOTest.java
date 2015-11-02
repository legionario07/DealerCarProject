package br.com.dealercar.teste.itensopcionais;

import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.dao.itensopcionais.SeguroDAO;
import br.com.dealercar.dao.itensopcionais.TipoSeguroDAO;
import br.com.dealercar.domain.itensopcionais.Seguro;
import br.com.dealercar.domain.itensopcionais.TipoSeguro;

public class SeguroDAOTest {

	public static void cadastrar() {
		Seguro seguro = new Seguro();
		seguro.setDescricao("Liberty");

		TipoSeguro tipoSeguro = new TipoSeguro(1);
		tipoSeguro = new TipoSeguroDAO().pesquisarPorID(tipoSeguro);
		seguro.setTipoSeguro(tipoSeguro);
		seguro.setValor(10.00D);

		SeguroDAO seguroDao = new SeguroDAO();
		seguroDao.cadastrar(seguro);
	}

	public static void listarTodos() {
		List<Seguro> lista = new ArrayList<Seguro>();

		SeguroDAO segDao = new SeguroDAO();
		lista = segDao.listarTodos();

		for (Seguro s : lista) {
			System.out.println(s);
		}
	}

	public static void editar() {
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
	 
	public static void excluir() {
		Seguro seguro = new Seguro();
		
		seguro.setCodigo(3);

		SeguroDAO seguroDao = new SeguroDAO();
		seguroDao.excluir(seguro);
	}
	
	public static void pesquisarPorCodigo() {
		Seguro seguro = new Seguro();
		seguro.setCodigo(4);
		
		SeguroDAO seguroDao = new SeguroDAO();
		System.out.println(seguroDao.pesquisarPorCodigo(seguro));
	}

	public static void listarApenasNomesDiferentes() {
		List<Seguro> lista = new ArrayList<Seguro>();

		SeguroDAO segDao = new SeguroDAO();
		lista = segDao.listarApenasNomesDiferentes();

		for (Seguro s : lista) {
			System.out.println(s);
		}
	}
	

	public static void main(String[] args) {

		//cadastrar();
		//editar();
		//pesquisarPorCodigo();
		//excluir();
		//listarTodos();
		listarApenasNomesDiferentes();
	}

}
