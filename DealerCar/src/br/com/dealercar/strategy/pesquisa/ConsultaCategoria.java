package br.com.dealercar.strategy.pesquisa;

import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.dao.automotivos.CategoriaDAO;
import br.com.dealercar.domain.EntidadeDominio;
import br.com.dealercar.domain.automotivos.Carro;
import br.com.dealercar.domain.automotivos.Categoria;

public class ConsultaCategoria implements IConsultaCarroStrategy {

	/**
	 * Recebe um Objeto Categoria
	 * Return Um objeto do Tipo Categoria ou Null se não localizar
	 */
	public EntidadeDominio consultar(Carro carro) {
		Categoria categoria = null;

		CategoriaDAO dao = new CategoriaDAO();

		List<Categoria> lista = new ArrayList<Categoria>();
		lista = dao.listarTodos();

		for (Categoria c : lista) {
			if (carro.getCategoria().getNome().toUpperCase().equals(c.getNome())) {

				categoria = c;

			}
		}

		return categoria;
	}

}
