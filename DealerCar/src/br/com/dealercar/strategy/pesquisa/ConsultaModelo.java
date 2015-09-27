package br.com.dealercar.strategy.pesquisa;

import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.dao.automotivos.ModeloDAO;
import br.com.dealercar.domain.EntidadeDominio;
import br.com.dealercar.domain.automotivos.Carro;
import br.com.dealercar.domain.automotivos.Modelo;

public class ConsultaModelo implements IConsultaCarroStrategy {

	/**
	 * Recebe um Objeto Carro
	 * Return Um objeto do Tipo Modelo ou Null se não localizar
	 */
	public EntidadeDominio consultar(Carro carro) {
		
		Modelo modelo = null;

		ModeloDAO dao = new ModeloDAO();

		List<Modelo> lista = new ArrayList<Modelo>();
		lista = dao.listarTodos();

		for (Modelo c : lista) {
			if (carro.getModelo().getNome().toUpperCase().equals(c.getNome())) {

				modelo = c;

			}
		}

		return modelo;
	}

}
