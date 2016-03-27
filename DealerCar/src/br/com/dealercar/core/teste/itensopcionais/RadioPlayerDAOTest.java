package br.com.dealercar.core.teste.itensopcionais;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.dealercar.core.dao.itensopcionais.RadioPlayerDAO;
import br.com.dealercar.domain.EntidadeDominio;
import br.com.dealercar.domain.itensopcionais.RadioPlayer;

public class RadioPlayerDAOTest {
	
	@Test
	@Ignore
	public void cadastrar() {

		RadioPlayer radioPlayer = new RadioPlayer("Teste 4", 45.00, "Teste", "9883s8484", "Toshiba");

		RadioPlayerDAO radioPlayerDao = new RadioPlayerDAO();
		radioPlayerDao.cadastrar(radioPlayer);

	}

	@Test
	@Ignore
	public void editar() {
	
		RadioPlayer radioPlayer = new RadioPlayer("Teste", 40.00, "marca alterada", "293940", "Sony");
		radioPlayer.setCodigo(1);

		RadioPlayerDAO radioPlayerDao = new RadioPlayerDAO();
		radioPlayerDao.editar(radioPlayer);

	}

	@Test
	@Ignore
	public void excluir() {
		
		RadioPlayer radioPlayer = new RadioPlayer(2);
		
		RadioPlayerDAO radioPlayerDao = new RadioPlayerDAO();
		radioPlayerDao.excluir(radioPlayer);

	}

	@Test
	@Ignore
	public void listarTodos() {
		List<EntidadeDominio> lista = new ArrayList<EntidadeDominio>();
		
		RadioPlayerDAO radioPlayerDao = new RadioPlayerDAO();
		
		lista = radioPlayerDao.listarTodos();
		
		for(EntidadeDominio radioPlayer : lista){
			System.out.println(radioPlayer);
		}
		
	}

	@Test
	@Ignore
	public void pesquisarPorID() {

		RadioPlayer radioPlayer = new RadioPlayer();
		radioPlayer.setCodigo(1);
		
		RadioPlayerDAO radioPlayerDao = new RadioPlayerDAO();
		
		System.out.println(radioPlayerDao.pesquisarPorID(radioPlayer));
		
	}
 

}
