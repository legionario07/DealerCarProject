package br.com.dealercar.core.negocio.strategy;

import br.com.dealercar.domain.EntidadeDominio;
import br.com.dealercar.domain.conducao.Devolucao;

/**
 * Classe Strategy repons�vel pela Valida��o de um Devolucao
 * 
 * @author Paulinho
 *
 */
public class ValidaDevolucao implements IValidacaoStrategy {

	/**
	 * 
	 * @param recebe
	 *            um objeto Devolucao
	 * @return Retorna uma String se com a mensagem do erro
	 */
	public String validar(EntidadeDominio entDominio) {

		StringBuffer retorno = null;
		Devolucao devolucao = null;

		// Verifica se a classe passada no Parametro eh um objeto Devolucao
		if (entDominio instanceof Devolucao) {
			retorno = new StringBuffer();
			devolucao = (Devolucao) entDominio;

			if (devolucao.getQuilometragem().equals("")) {
				retorno.append("� necess�rio preencher a Quilometragem");
				return retorno.toString();
			}

			if (devolucao.getFuncionario().getId() < 0) {
				retorno.append("� Necess�rio preencher o Funcion�rio que realizou a Retirada");
				return retorno.toString();
			}

			if (devolucao.getRetirada().getCarro().getPlaca().equals("")) {
				retorno.append("O Carro deve ser Preenchido");
				return retorno.toString();
			}

		} else {
			return null;
		}

		return null;

	}
}
