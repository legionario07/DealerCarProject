package br.com.dealercar.core.teste.automotivos;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.dealercar.core.dao.automotivos.CarroDAO;
import br.com.dealercar.domain.Cor;
import br.com.dealercar.domain.EntidadeDominio;
import br.com.dealercar.domain.automotivos.Carro;
import br.com.dealercar.domain.automotivos.Categoria;
import br.com.dealercar.domain.automotivos.Modelo;
import br.com.dealercar.domain.enums.SituacaoType;

public class CarroDAOTest {

	@Test
	@Ignore
	public void cadastrar() {

		Carro carro = new Carro("Dgx-9999", "2010", 4, 3, new Cor(7), new Modelo(3), new Categoria(6),
				SituacaoType.Locado);

		CarroDAO carDao = new CarroDAO();
		carDao.cadastrar(carro);

		System.out.println(carro);

	}


	@Test
	@Ignore
	public void pesquisarPorPlaca() {
		Carro carro = new Carro("TTT-9999");

		CarroDAO carDao = new CarroDAO();
		System.out.println(carDao.pesquisarPorPlaca(carro));

	}

	@Test
	@Ignore
	public void listarTodos() {
		List<EntidadeDominio> lista = new ArrayList<EntidadeDominio>();

		CarroDAO carDao = new CarroDAO();
		lista = carDao.listarTodos();

		for (EntidadeDominio c : lista) {
			System.out.println(c);
		}
	}


	@Test
	@Ignore
	public void excluir() {
		Carro carro = new Carro("Dgx-9999");

		CarroDAO carroDao = new CarroDAO();
		carroDao.excluir(carro);
	}


	@Test
	@Ignore
	public void editar() {
		Carro carro = new Carro("DGX-9999", "2011", 4, 3, new Cor(7), new Modelo(3), new Categoria(6),
				 SituacaoType.Locado);

		CarroDAO carroDao = new CarroDAO();

		carroDao.editar(carro);

	}

	@Test
	@Ignore
	public static void pesquisarCarrosDisponiveis() {
		
		List<Carro> lista = new ArrayList<Carro>();

		CarroDAO carDao = new CarroDAO(); 
		lista = carDao.listarApenasDisponiveis();

		for (Carro c : lista) {
			System.out.println(c.getModelo().getNome());
		}
		
	}
	
	@Test
	@Ignore
	public static void pesquisarPorModelo() {
		
		List<Carro> lista = new ArrayList<Carro>();

		Modelo m = new Modelo(5);
		
		CarroDAO carDao = new CarroDAO(); 
		lista = carDao.listarModelosDisponiveis(m);

		for (Carro c : lista) {
			System.out.print(c.getModelo().getNome());
			System.out.print(c.getPlaca());
			System.out.println("\n");
			
		}
		
	}
	


}
