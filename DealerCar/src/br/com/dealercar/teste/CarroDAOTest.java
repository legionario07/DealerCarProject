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

		Carro carro = new Carro("Dgx8587", "2009", 4, 3, new Cor(5), new Modelo(2), new Categoria(6),
				new ImagemCarro(1), SituacaoType.Disponivel);

		CarroDAO carDao = new CarroDAO();
		carDao.cadastrar(carro);

		System.out.println(carro);

	}

	@SuppressWarnings("unused")
	private static void pesquisarPorPlaca() {
		Carro carro = new Carro("Dgx8587");

		CarroDAO carDao = new CarroDAO();
		System.out.println(carDao.pesquisarPorPlaca(carro));

	}
	
	private static void listarTodos() {
		List<Carro> lista = new ArrayList<Carro>();
		
		CarroDAO carDao = new CarroDAO();
		lista = carDao.listarTodos();
		
		for(Carro c : lista) {
			System.out.println(c);
		}
	}

	public static void main(String[] args) {

		// cadastrar();
		//pesquisarPorPlaca();
		listarTodos();
	}

}
