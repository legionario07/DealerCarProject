package br.com.dealercar.strategy.pesquisa;

import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.dao.automotivos.FabricanteDAO;
import br.com.dealercar.domain.EntidadeDominio;
import br.com.dealercar.domain.automotivos.Carro;
import br.com.dealercar.domain.automotivos.Fabricante;

public class ConsultaFabricante implements IConsultaCarroStrategy {

	/**
	 * 
	 * @param recebe um objeto Fabricante
	 * @return Retorna um objeto Fabricante do BD válido ou Null se não for
	 *         encontrado
	 */
	public EntidadeDominio consultar(Carro carro) {

		Fabricante fabricante = null;

		FabricanteDAO dao = new FabricanteDAO();

		List<Fabricante> lista = new ArrayList<Fabricante>();
		lista = dao.listarTodos();

		for (Fabricante c : lista) {
			if (carro.getModelo().getNome().toUpperCase().equals(c.getNome())) {

				fabricante = c;

			}
		}

		return fabricante;

	}

}
