package br.com.dealercar.teste.itensopcionais;

import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.dao.itensopcionais.ArCondicionadoDAO;
import br.com.dealercar.dao.itensopcionais.BebeConfortoDAO;
import br.com.dealercar.dao.itensopcionais.CadeirinhaBebeDAO;
import br.com.dealercar.dao.itensopcionais.GpsDAO;
import br.com.dealercar.dao.itensopcionais.OpcionalDAO;
import br.com.dealercar.dao.itensopcionais.RadioPlayerDAO;
import br.com.dealercar.dao.itensopcionais.SeguroDAO;
import br.com.dealercar.domain.itensopcionais.ArCondicionado;
import br.com.dealercar.domain.itensopcionais.BebeConforto;
import br.com.dealercar.domain.itensopcionais.CadeirinhaBebe;
import br.com.dealercar.domain.itensopcionais.Gps;
import br.com.dealercar.domain.itensopcionais.Itens;
import br.com.dealercar.domain.itensopcionais.Opcional;
import br.com.dealercar.domain.itensopcionais.RadioPlayer;
import br.com.dealercar.domain.itensopcionais.Seguro;

public class OpcionalDAOTest {

	private static ArCondicionado ar = new ArCondicionado();
	private static BebeConforto bebe = new BebeConforto();
	private static CadeirinhaBebe cadeirinha = new CadeirinhaBebe();
	private static Gps gps = new Gps();
	private static RadioPlayer radio = new RadioPlayer();
	private static Seguro seguro = new Seguro();
	
	private static ArCondicionadoDAO arDao = new ArCondicionadoDAO();
	private static BebeConfortoDAO bebeDao = new BebeConfortoDAO();
	private static CadeirinhaBebeDAO cadeDao = new CadeirinhaBebeDAO();
	private static GpsDAO gpsDao = new GpsDAO();
	private static RadioPlayerDAO radioDao = new RadioPlayerDAO();
	private static SeguroDAO seguroDao = new SeguroDAO();
	
	public static void preencherObjetos(){
	
		ar.setCodigo(3);
		ar = arDao.pesquisarPorID(ar);
		
		bebe.setCodigo(1);
		bebe = bebeDao.pesquisarPorCodigo(bebe);
		
		cadeirinha.setCodigo(1);
		cadeDao.pesquisarPorID(cadeirinha);
		
		gps.setCodigo(1);
		gps = gpsDao.pesquisarPorID(gps);
		
		radio.setCodigo(1);
		radio = radioDao.pesquisarPorID(radio);
		
		seguro.setCodigo(1);
		seguro = seguroDao.pesquisarPorCodigo(seguro);
		
	}
	
	public static void cadastrar(){
		
		preencherObjetos();
	
		Opcional itemOpcional = new Opcional();
		
		OpcionalDAO opDao = new OpcionalDAO();
		
		List<Itens> itens = new ArrayList<Itens>();
		
		itemOpcional.setSeguro(seguro);
	
		itemOpcional.setArCondicionado(ar);
		itens.add(cadeirinha);
		itens.add(radio);
	
		itemOpcional.setItens(itens);
		
		opDao.cadastrar(itemOpcional);
		
		
	}
	
	public static void excluir(){
		

	}
	
	public static void pesquisarPorId(){
		
		Opcional opcional = new Opcional(6);
		
		OpcionalDAO opDao = new OpcionalDAO();
		opcional = opDao.pesquisarPorID(opcional);
		
		System.out.println(opcional);
		
	}
	
	public static void pesquisarPorUltimoCadastrado(){
		
		
		OpcionalDAO opDao = new OpcionalDAO();
		System.out.println(opDao.pesquisarPorUltimoCadastrado());
		
	}
	
	public static void listarTodos(){
		
		List<Opcional> lista = new ArrayList<Opcional>();
		
		lista = new OpcionalDAO().listarTodos();
		
		for(Opcional o : lista){
			System.out.println(o);
		}
	}
	
	public static void main(String[] args) {

		//cadastrar();
		//pesquisarPorId();
		listarTodos();
		//pesquisarPorUltimoCadastrado();
		
	}

}
