package br.com.dealercar.core.teste.itensopcionais;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.dealercar.core.dao.itensopcionais.BebeConfortoDAO;
import br.com.dealercar.core.dao.itensopcionais.CadeirinhaBebeDAO;
import br.com.dealercar.core.dao.itensopcionais.GpsDAO;
import br.com.dealercar.core.dao.itensopcionais.OpcionalDAO;
import br.com.dealercar.core.dao.itensopcionais.RadioPlayerDAO;
import br.com.dealercar.core.dao.itensopcionais.SeguroDAO;
import br.com.dealercar.domain.EntidadeDominio;
import br.com.dealercar.domain.itensopcionais.BebeConforto;
import br.com.dealercar.domain.itensopcionais.CadeirinhaBebe;
import br.com.dealercar.domain.itensopcionais.Gps;
import br.com.dealercar.domain.itensopcionais.Itens;
import br.com.dealercar.domain.itensopcionais.Opcional;
import br.com.dealercar.domain.itensopcionais.RadioPlayer;
import br.com.dealercar.domain.itensopcionais.Seguro;

public class OpcionalDAOTest {

	private static BebeConforto bebe = new BebeConforto();
	private static CadeirinhaBebe cadeirinha = new CadeirinhaBebe();
	private static Gps gps = new Gps();
	private static RadioPlayer radio = new RadioPlayer();
	private static Seguro seguro = new Seguro();
	
	private static BebeConfortoDAO bebeDao = new BebeConfortoDAO();
	private static CadeirinhaBebeDAO cadeDao = new CadeirinhaBebeDAO();
	private static GpsDAO gpsDao = new GpsDAO();
	private static RadioPlayerDAO radioDao = new RadioPlayerDAO();
	private static SeguroDAO seguroDao = new SeguroDAO();
	
	@Ignore
	public void preencherObjetos(){
	
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
	
	@Test
	@Ignore
	public void cadastrar(){
		
		preencherObjetos();
	
		Opcional itemOpcional = new Opcional();
		
		OpcionalDAO opDao = new OpcionalDAO();
		
		List<Itens> itens = new ArrayList<Itens>();
		
		itemOpcional.setSeguro(seguro);
	
		itens.add(cadeirinha);
		itens.add(radio);
	
		itemOpcional.setItens(itens);
		
		opDao.cadastrar(itemOpcional);
		
		
	}
	
	@Test
	@Ignore
	public void excluir(){
		

	}
	
	@Test
	@Ignore
	public void pesquisarPorId(){
		
		Opcional opcional = new Opcional(6);
		
		OpcionalDAO opDao = new OpcionalDAO();
		opcional = opDao.pesquisarPorID(opcional);
		
		System.out.println(opcional);
		
	}

	@Test
	@Ignore
	public void pesquisarPorUltimoCadastrado(){
		
		
		OpcionalDAO opDao = new OpcionalDAO();
		System.out.println(opDao.pesquisarPorUltimoCadastrado());
		
	}
	
	@Test
	@Ignore
	public void listarTodos(){
		
		List<EntidadeDominio> lista = new ArrayList<EntidadeDominio>();
		
		lista = new OpcionalDAO().listarTodos();
		
		for(EntidadeDominio o : lista){
			System.out.println(o);
		}
	}
	

}
