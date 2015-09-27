package br.com.dealercar.strategy.pesquisa;

import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.dao.CorDAO;
import br.com.dealercar.domain.Cor;
import br.com.dealercar.domain.EntidadeDominio;
import br.com.dealercar.domain.automotivos.Carro;

public class ConsultaCor implements IConsultaCarroStrategy  {

	/**
	 * Recebe um Objeto Carro
	 * Return Um objeto do Tipo Cor ou Null se não localizar
	 */
	public EntidadeDominio consultar(Carro carro) {
		Cor cor = null;

		CorDAO dao = new CorDAO();

		List<Cor> lista = new ArrayList<Cor>();
		lista = dao.listarTodos();

		for (Cor c : lista) {
			if (carro.getCor().getNome().toUpperCase().equals(c.getNome())) {

				cor = c;

			}
		}

		return cor;
	}

}
