package br.com.dealercar.teste.itensopcionais;

import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.dao.itensopcionais.GpsDAO;
import br.com.dealercar.domain.itensopcionais.Gps;

public class GpsDAOTest {
	
	public static void cadastrar() {

		Gps gps = new Gps("Teste 4", 45.00, "Teste", "98833848e", "portugues");

		GpsDAO gpsDao = new GpsDAO();
		gpsDao.cadastrar(gps);

	}

	public static void editar() {
	
		Gps gps = new Gps("Teste", 40.00, "marca alterada", "293940", "ingles");
		gps.setCodigo(1);

		GpsDAO gpsDao = new GpsDAO();
		gpsDao.editar(gps);

	}

	public static void excluir() {
		
		Gps gps = new Gps(2);
		
		GpsDAO gpsDao = new GpsDAO();
		gpsDao.excluir(gps);

	}

	public static void listarTodos() {
		List<Gps> lista = new ArrayList<Gps>();
		
		GpsDAO gpsDao = new GpsDAO();
		
		lista = gpsDao.listarTodos();
		
		for(Gps gps : lista){
			System.out.println(gps);
		}
		
	}

	public static void pesquisarPorID() {

		Gps gps = new Gps();
		gps.setCodigo(99);
		
		GpsDAO gpsDao = new GpsDAO();
		
		System.out.println(gpsDao.pesquisarPorID(gps));
		
	}
 
	public static void main(String[] args) {
		
		//cadastrar();
		//editar();
		//excluir();
		//listarTodos();
		//pesquisarPorID();


	}

}
