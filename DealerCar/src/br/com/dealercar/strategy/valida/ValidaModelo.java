package br.com.dealercar.strategy.valida;

import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.dao.automotivos.ModeloDAO;
import br.com.dealercar.domain.EntidadeDominio;
import br.com.dealercar.domain.automotivos.Modelo;

public class ValidaModelo implements IValidacaoStrategy{
	/**
	 * 
	 * @param recebe um objeto Modelo
	 * @return Retorna um objeto Modelo do BD válido ou Null se não for encontrado
	 */
	public EntidadeDominio validar(EntidadeDominio entDominio) {

		Modelo rModelo = null;

		//Verifica se a classe passada no Parametro eh um objeto Modelo
		if (entDominio instanceof Modelo) {

			Modelo retorno = (Modelo) entDominio;
			
			ModeloDAO dao = new ModeloDAO();

			List<Modelo> lista = new ArrayList<Modelo>();
			lista = dao.listarTodos();

			for (Modelo c : lista) {
				if (retorno.getNome().toUpperCase().equals(c.getNome())) {
					
					rModelo = c;

				}
			}
		}
		
		return rModelo;

	}
}
