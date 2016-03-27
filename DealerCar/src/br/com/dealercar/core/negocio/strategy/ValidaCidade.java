package br.com.dealercar.core.negocio.strategy;

import br.com.dealercar.domain.Cidade;
import br.com.dealercar.domain.EntidadeDominio;

/**
 * Classe Strategy reponsável pela Validação de um Cidade
 * 
 * @author Paulinho
 *
 */
public class ValidaCidade implements IValidacaoStrategy {

	/**
	 * 
	 * @param recebe
	 *            um objeto Cidade e faz a Validação pelo Nome
	 * @return Retorna um objeto Cidade do BD válido ou Null se não for encontrado
	 */
	public String validar(EntidadeDominio entDominio) {

		StringBuffer retorno = null;
		Cidade cidade = null;

		// Verifica se a classe passada no Parametro eh um objeto Cidade
		if (entDominio instanceof Cidade) {
			retorno = new StringBuffer();
			cidade = (Cidade) entDominio;

			if (cidade.getNome().equals("")) {
				retorno.append("O nome da Cidade deve ser preenchido");
				return retorno.toString();
			}
			
			if (cidade.getEstado().getNome().equals("")) {
				retorno.append("O estado deve ser preenchido");
				return retorno.toString();
			}

		} else {
			return null;
		}

		return null;
	}
}
