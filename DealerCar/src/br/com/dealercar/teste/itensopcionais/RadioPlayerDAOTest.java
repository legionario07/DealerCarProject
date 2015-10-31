package br.com.dealercar.teste.itensopcionais;

import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.dao.itensopcionais.RadioPlayerDAO;
import br.com.dealercar.domain.itensopcionais.RadioPlayer;

public class RadioPlayerDAOTest {
	
	public static void cadastrar() {

		RadioPlayer radioPlayer = new RadioPlayer("Teste 4", 45.00, "Teste", "9883s8484", "Toshiba");

		RadioPlayerDAO radioPlayerDao = new RadioPlayerDAO();
		radioPlayerDao.cadastrar(radioPlayer);

	}

	public static void editar() {
	
		RadioPlayer radioPlayer = new RadioPlayer("Teste", 40.00, "marca alterada", "293940", "Sony");
		radioPlayer.setCodigo(1);

		RadioPlayerDAO radioPlayerDao = new RadioPlayerDAO();
		radioPlayerDao.editar(radioPlayer);

	}

	public static void excluir() {
		
		RadioPlayer radioPlayer = new RadioPlayer(2);
		
		RadioPlayerDAO radioPlayerDao = new RadioPlayerDAO();
		radioPlayerDao.excluir(radioPlayer);

	}

	public static void listarTodos() {
		List<RadioPlayer> lista = new ArrayList<RadioPlayer>();
		
		RadioPlayerDAO radioPlayerDao = new RadioPlayerDAO();
		
		lista = radioPlayerDao.listarTodos();
		
		for(RadioPlayer radioPlayer : lista){
			System.out.println(radioPlayer);
		}
		
	}

	public static void pesquisarPorID() {

		RadioPlayer radioPlayer = new RadioPlayer();
		radioPlayer.setCodigo(1);
		
		RadioPlayerDAO radioPlayerDao = new RadioPlayerDAO();
		
		System.out.println(radioPlayerDao.pesquisarPorID(radioPlayer));
		
	}
 
	public static void main(String[] args) {
		
		//cadastrar();
		//editar();
		//excluir();
		//listarTodos();
		//pesquisarPorID();

	}

}
