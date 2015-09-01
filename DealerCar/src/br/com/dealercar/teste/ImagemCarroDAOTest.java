package br.com.dealercar.teste;

import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.dao.ImagemCarroDAO;
import br.com.dealercar.domain.ImagemCarro;

public class ImagemCarroDAOTest {
	
	@SuppressWarnings("unused")
	private static void cadastrar() {
		ImagemCarro carroUrl = new ImagemCarro("teste/caminho", "carro teste");
		
		ImagemCarroDAO dao = new ImagemCarroDAO();
		dao.cadastrar(carroUrl);
	}
	
	@SuppressWarnings("unused")
	private static void pesquisarPorID() {
		ImagemCarro carroUrl = new ImagemCarro();
		carroUrl.setId(2);
		
		ImagemCarroDAO carroUrlDao = new ImagemCarroDAO();
		System.out.println(carroUrlDao.pesquisarPorID(carroUrl));
	}
	
	@SuppressWarnings("unused")
	private static void listarTodos() {
		List<ImagemCarro> lista = new ArrayList<ImagemCarro>();
		
		ImagemCarroDAO  carroUrlDao = new ImagemCarroDAO();
		lista = carroUrlDao.listarTodos();
		
		for(ImagemCarro c : lista) {
			System.out.println(c);
		}
	}

	@SuppressWarnings("unused")
	private static void editar() {
		ImagemCarro carroUrl = new ImagemCarro();
		carroUrl.setId(2);
		carroUrl.setDescricao("Camaro Cupê");
		carroUrl.setCaminho("/images/conversivel/camaro_cupe.jpeg");
		
		ImagemCarroDAO dao = new ImagemCarroDAO();
		dao.editar(carroUrl);
	}
	
	@SuppressWarnings("unused")
	private static void excluir() {
		ImagemCarro carroUrl = new ImagemCarro();
		carroUrl.setId(6);;
		
		ImagemCarroDAO dao = new ImagemCarroDAO();
		dao.excluir(carroUrl);
	}
	
	public static void main(String[] args) {
		
		//pesquisarPorID();
		//editar();
		//cadastrar();
		//excluir();
		//listarTodos();
		
		
	}
}
