package br.com.dealercar.strategy.valida;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.dao.automotivos.FabricanteDAO;
import br.com.dealercar.domain.EntidadeDominio;
import br.com.dealercar.domain.automotivos.Fabricante;

/**
 * Classe Strategy reponsável pela Validação de um FAbricante
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
	 * @param recebe um objeto Fabricante e faz a Validação pelo Nome
	 * @return Retorna um objeto Fabricante do BD válido ou Null se não for encontrado
	 */
	public EntidadeDominio validar(EntidadeDominio entDominio) {

		Fabricante rFabricante = null;

		//Verifica se a classe passada no Parametro eh um objeto Fabricante
		if (entDominio instanceof Fabricante) {

			Fabricante retorno = (Fabricante) entDominio;
			
			FabricanteDAO dao = new FabricanteDAO();

			List<Fabricante> lista = new ArrayList<Fabricante>();
			lista = dao.listarTodos();

			for (Fabricante c : lista) {
				if (retorno.getNome().toUpperCase().equals(c.getNome())) {
					
					rFabricante = c;

				}
			}
		}
		
		return rFabricante;

	}

}
