package br.com.dealercar.strategy.valida;

import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.dao.EstadoDAO;
import br.com.dealercar.domain.Estado;
import br.com.dealercar.domain.EntidadeDominio;

/**
 * Classe Strategy reponsável pela Validação de uma Estado
 * @author Paulinho
 *
 */
public class ValidaEstado implements IValidacaoStrategy{

	/**
	 * 
	 * @param recebe um objeto Estado e faz a Validação pelo Nome
	 * @return Retorna um objeto Estado do BD válido ou Null se não for encontrado
	 */
	public EntidadeDominio validar(EntidadeDominio entDominio) {

		Estado rEstado = null;

		//Verifica se a classe passada no Parametro eh um objeto Estado
		if (entDominio instanceof Estado) {

			Estado retorno = (Estado) entDominio;
			
			EstadoDAO dao = new EstadoDAO();

			List<Estado> lista = new ArrayList<Estado>();
			lista = dao.listarTodos();

			for (Estado c : lista) {
				if (retorno.getUf().toUpperCase().equals(c.getUf())) {
					
					rEstado = c;

				}
			}
		}
		
		return rEstado;

	}

}
