package br.com.dealercar.strategy.valida;

import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.dao.automotivos.FabricanteDAO;
import br.com.dealercar.domain.EntidadeDominio;
import br.com.dealercar.domain.automotivos.Fabricante;
import br.com.dealercar.domain.automotivos.Modelo;

public class ValidaFabricante implements IValidacaoStrategy{


	/**
	 * 
	 * @param recebe um objeto Modelo e faz a Validação pelo Nome
	 * @return Retorna um objeto Fabricante do BD válido ou Null se não for encontrado
	 */
	public EntidadeDominio validar(EntidadeDominio entDominio) {

		Fabricante rFabricante = null;

		//Verifica se a classe passada no Parametro eh um objeto Modelo
		if (entDominio instanceof Modelo) {

			Modelo retorno = (Modelo) entDominio;
			
			FabricanteDAO dao = new FabricanteDAO();

			List<Fabricante> lista = new ArrayList<Fabricante>();
			lista = dao.listarTodos();

			for (Fabricante c : lista) {
				if (retorno.getFabricante().getNome().toUpperCase().equals(c.getNome())) {
					
					rFabricante = c;

				}
			}
		}
		
		return rFabricante;

	}

}
