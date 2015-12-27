package br.com.dealercar.strategy.valida;

import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.dao.automotivos.CategoriaDAO;
import br.com.dealercar.domain.EntidadeDominio;
import br.com.dealercar.domain.automotivos.Categoria;

/**
 * Classe Strategy reponsável pela Validação de uma Categoria
 * @author Paulinho
 *
 */
public class ValidaCategoria implements IValidacaoStrategy {

	/**
	 * 
	 * @param recebe um objeto Categoria e faz a Validação pelo Nome
	 * @return Retorna um objeto Categoria do BD válido ou Null se não for encontrado
	 */
	public EntidadeDominio validar(EntidadeDominio entDominio) {

		Categoria rCategoria = null;

		//Verifica se a classe passada no Parametro eh um objeto Categoria
		if (entDominio instanceof Categoria) {

			Categoria retorno = (Categoria) entDominio;
			
			CategoriaDAO dao = new CategoriaDAO();

			List<Categoria> lista = new ArrayList<Categoria>();
			lista = dao.listarTodos();

			for (Categoria c : lista) {
				if (retorno.getNome().toUpperCase().equals(c.getNome())) {
					
					rCategoria = c;

				}
			}
		}
		
		return rCategoria;

	}

}