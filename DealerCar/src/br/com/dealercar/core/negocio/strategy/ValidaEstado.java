package br.com.dealercar.core.negocio.strategy;

import br.com.dealercar.domain.Estado;
import br.com.dealercar.domain.EntidadeDominio;

/**
 * Classe Strategy reponsável pela Validação de um Estado
 * 
 * @author Paulinho
 *
 */
public class ValidaEstado implements IValidacaoStrategy {

	/**
	 * 
	 * @param recebe
	 *            um objeto Estado e faz a Validação pelo Nome
	 * @return Retorna um objeto Estado do BD válido ou Null se não for encontrado
	 */
	public String validar(EntidadeDominio entDominio) {

		StringBuffer retorno = null;
		Estado estado = null;

		// Verifica se a classe passada no Parametro eh um objeto Estado
		if (entDominio instanceof Estado) {
			retorno = new StringBuffer();
			estado = (Estado) entDominio;

			if (estado.getNome().equals("")) {
				retorno.append("O nome da Estado deve ser preenchido");
				return retorno.toString();
			}
			
			if (estado.getUf().equals("")) {
				retorno.append("O estado deve ter uma UF");
				return retorno.toString();
			}

		} else {
			return null;
		}

		return null;
	}
}
