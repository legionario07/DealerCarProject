package br.com.dealercar.strategy.pesquisa;

import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.dao.automotivos.ImagemCarroDAO;
import br.com.dealercar.domain.EntidadeDominio;
import br.com.dealercar.domain.automotivos.Carro;
import br.com.dealercar.domain.automotivos.ImagemCarro;

public class ConsultaImagemCarro implements IConsultaCarroStrategy {

	/**
	 * Recebe um Objeto Carro
	 * Return Um objeto do Tipo ImagemCarro ou Null se não localizar
	 */
	public EntidadeDominio consultar(Carro carro) {
		ImagemCarro imagemCarro = null;

		ImagemCarroDAO dao = new ImagemCarroDAO();

		List<ImagemCarro> lista = new ArrayList<ImagemCarro>();
		lista = dao.listarTodos();

		for (ImagemCarro c : lista) {
			if (carro.getCarroUrl().getDescricao().toUpperCase().equals(c.getDescricao())) {

				imagemCarro = c;

			}
		}

		return imagemCarro;
	}

}
