package br.com.dealercar.core.negocio.strategy;

import br.com.dealercar.domain.Cor;
import br.com.dealercar.domain.EntidadeDominio;

/**
 * Classe Strategy reponsável pela Validação de um Cor
 * 
 * @author Paulinho
 *
 */
public class ValidaCor implements IValidacaoStrategy {

	/**
	 * 
	 * @param recebe
	 *            um objeto Cor e faz a Validação pelo Nome
	 * @return Retorna um objeto Cor do BD válido ou Null se não for encontrado
	 */
	public String validar(EntidadeDominio entDominio) {

		StringBuffer retorno = null;
		Cor cor = null;

		// Verifica se a classe passada no Parametro eh um objeto Cor
		if (entDominio instanceof Cor) {
			retorno = new StringBuffer();
			cor = (Cor) entDominio;

			if (cor.getNome().equals("")) {
				retorno.append("O nome da Cor deve ser preenchido");
				return retorno.toString();
			}

		} else {
			return null;
		}

		return null;
	}
}
