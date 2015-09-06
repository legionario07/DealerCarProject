package br.com.dealercar.teste;

import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.dao.CarroDAO;
import br.com.dealercar.domain.Carro;
import br.com.dealercar.domain.Categoria;
import br.com.dealercar.domain.Cor;
import br.com.dealercar.domain.ImagemCarro;
import br.com.dealercar.domain.Modelo;
import br.com.dealercar.domain.SituacaoType;

public class CarroDAOTest {

	@SuppressWarnings("unused")
	private static void cadastrar() {

		Carro carro = new Carro("Dgx-9999", "2010", 4, 3, new Cor(7), new Modelo(3), new Categoria(6),
				new ImagemCarro(2), SituacaoType.Locado);

		CarroDAO carDao = new CarroDAO();
		carDao.cadastrar(carro);

		System.out.println(carro);

	}

	@SuppressWarnings("unused")
	private static void pesquisarPorPlaca() {
		Carro carro = new Carro("TTT-9999");

		CarroDAO carDao = new CarroDAO();
		System.out.println(carDao.pesquisarPorPlaca(carro));

	}
	
	@SuppressWarnings("unused")
	private static void listarTodos() {
		List<Carro> lista = new ArrayList<Carro>();
		
		CarroDAO carDao = new CarroDAO();
		lista = carDao.listarTodos();
		
		for(Carro c : lista) {
			System.out.println(c);
		}
	}
	
	@SuppressWarnings("unused")
	private static void excluir(){
		Carro carro = new Carro("Dgx-9999");
		
		CarroDAO carroDao = new CarroDAO();
		carroDao.excluir(carro);
	}
	
	@SuppressWarnings("unused")
	private static void editar() {
		Carro carro = new Carro("DGX-9999", "2011", 4, 3, new Cor(7), new Modelo(3), new Categoria(6),
				new ImagemCarro(2), SituacaoType.Locado);
		
		CarroDAO carroDao = new CarroDAO();
		
		carroDao.editar(carro);
		
	}

	public static void main(String[] args) {

		//cadastrar();
		//excluir();
		//editar();
		//pesquisarPorPlaca();
		//listarTodos();
		
	}

}
