package br.com.dealercar.core.teste.domain;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.dealercar.core.dao.CargoDAO;
import br.com.dealercar.domain.Cargo;
import br.com.dealercar.domain.EntidadeDominio;

public class CargoDAOTest {

	@Test
	public void listarTodos() {
		CargoDAO cargoDao = new CargoDAO();
		List<EntidadeDominio> lista = new ArrayList<EntidadeDominio>();
		lista = cargoDao.listarTodos();

		for (EntidadeDominio cargo : lista) {
			System.out.println(cargo);
		}
	}

	@Test
	@Ignore
	public void pesquisarPorID() {
		Cargo cargo = new Cargo();
		cargo.setId(4);

		CargoDAO cargoDao = new CargoDAO();
		System.out.println(cargoDao.pesquisarPorID(cargo));
	}

	@Test
	@Ignore
	public void editar() {
		Cargo cargo = new Cargo();
		cargo.setId(5);
		cargo.setNome("TESTE2");

		new CargoDAO().editar(cargo);
	}

	@Test
	@Ignore
	public void excluir() {
		Cargo cargo = new Cargo();
		cargo.setId(5);

		CargoDAO cargoDao = new CargoDAO();
		cargoDao.excluir(cargo);
	}

	@Test
	@Ignore
	public void cadastrar() {
		Cargo cargo = new Cargo();
		cargo.setNome("TESTE");

		CargoDAO cargoDao = new CargoDAO();
		cargoDao.cadastrar(cargo);
	}

}
