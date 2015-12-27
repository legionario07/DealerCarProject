package br.com.dealercar.strategy.valida;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.dao.automotivos.FabricanteDAO;
import br.com.dealercar.domain.EntidadeDominio;
import br.com.dealercar.domain.automotivos.Fabricante;
import br.com.dealercar.domain.automotivos.Modelo;

/**
 * Classe Strategy repons�vel pela Valida��o de um FAbricante
 * @author Paulinho
 *
 */
public class ValidaFabricante implements IValidacaoStrategy, Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param recebe um objeto Modelo e faz a Valida��o pelo Nome
	 * @return Retorna um objeto Fabricante do BD v�lido ou Null se n�o for encontrado
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
