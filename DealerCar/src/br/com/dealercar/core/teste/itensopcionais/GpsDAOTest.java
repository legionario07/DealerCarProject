package br.com.dealercar.core.teste.itensopcionais;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.dealercar.core.dao.itensopcionais.GpsDAO;
import br.com.dealercar.domain.EntidadeDominio;
import br.com.dealercar.domain.itensopcionais.Gps;

public class GpsDAOTest {
	
	@Test
	@Ignore
	public void cadastrar() {

		Gps gps = new Gps("Teste 4", 45.00, "Teste", "98833848e", "portugues");

		GpsDAO gpsDao = new GpsDAO();
		gpsDao.cadastrar(gps);

	}

	@Test
	@Ignore
	public void editar() {
	
		Gps gps = new Gps("Teste", 40.00, "marca alterada", "293940", "ingles");
		gps.setCodigo(1);

		GpsDAO gpsDao = new GpsDAO();
		gpsDao.editar(gps);

	}

	@Test
	@Ignore
	public void excluir() {
		
		Gps gps = new Gps(2);
		
		GpsDAO gpsDao = new GpsDAO();
		gpsDao.excluir(gps);

	}

	@Test
	@Ignore
	public void listarTodos() {
		List<EntidadeDominio> lista = new ArrayList<EntidadeDominio>();
		
		GpsDAO gpsDao = new GpsDAO();
		
		lista = gpsDao.listarTodos();
		
		for(EntidadeDominio gps : lista){
			System.out.println(gps);
		}
		
	}

	@Test
	@Ignore
	public void pesquisarPorID() {

		Gps gps = new Gps();
		gps.setCodigo(99);
		
		GpsDAO gpsDao = new GpsDAO();
		
		System.out.println(gpsDao.pesquisarPorID(gps));
		
	}
 

}
