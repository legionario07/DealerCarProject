package br.com.dealercar.strategy.valida;

import br.com.dealercar.domain.Cidade;
import br.com.dealercar.domain.Cor;
import br.com.dealercar.domain.automotivos.Carro;
import br.com.dealercar.domain.automotivos.Categoria;
import br.com.dealercar.domain.automotivos.ImagemCarro;

public class ValidaTest {

	public static void validarCategoria() {

		Categoria cat = new Categoria();
		cat.setNome("Especial");

		ValidaCategoria validaCategoria = new ValidaCategoria();

		System.out.println(validaCategoria.validar(cat));

	}

	public static void validarCidade() {

		Cidade cidade = new Cidade();
		cidade.setNome("Suzano");

		ValidaCidade validaCidade = new ValidaCidade();

		System.out.println(validaCidade.validar(cidade));

	}

	public static void validarCor() {

		Cor cor = new Cor();
		cor.setNome("Azul");

		ValidaCor validaCor = new ValidaCor();

		System.out.println(validaCor.validar(cor));

	}

	public static void validarCarro() {

		Carro carro = new Carro();

		carro.setPlaca("DGX-888");

		ValidaCarro validaCarro = new ValidaCarro();
		System.out.println(validaCarro.validar(carro));

		carro = (Carro) validaCarro.validar(carro);
		
		if(carro != null){
			System.out.println("Encontrou a placa");
		} else {
			System.out.println("nao encontrou a placa");
		}
		
	}
	
	public static void validarImagemCarro() {

		ImagemCarro carroUrl = new ImagemCarro();

		carroUrl.setDescricao("GEELy");

		ValidaImagemCarro valCarroUrl = new ValidaImagemCarro();
		System.out.println(valCarroUrl.validar(carroUrl));

		carroUrl = (ImagemCarro) valCarroUrl.validar(carroUrl);
		
		if(carroUrl != null){
			System.out.println("Encontrou a Descricao");
		} else {
			System.out.println("nao encontrou a Descricao");
		}
		
	}

	public static void main(String[] args) {

		// validarCategoria();
		// validarCidade();
		// validarCor();
		//validarCarro();
		validarImagemCarro();
	}

}
