package br.com.dealercar.core.negocio.strategy;

import br.com.dealercar.core.negocio.Revisao;
import br.com.dealercar.domain.EntidadeDominio;

/**
 * Classe Strategy reponsável pela Validação de um Revisao
 * 
 * @author Paulinho
 *
 */
public class ValidaRevisao implements IValidacaoStrategy {

	/**
	 * 
	 * @param recebe
	 *            um objeto Revisao
	 * @return Retorna uma String se com a mensagem do erro
	 */
	public String validar(EntidadeDominio entDominio) {

		StringBuffer retorno = null;
		Revisao revisao = null;

		// Verifica se a classe passada no Parametro eh um objeto Revisao
		if (entDominio instanceof Revisao) {
			retorno = new StringBuffer();
			revisao = (Revisao) entDominio;

			if (revisao.getQuilometragem().equals("")) {
				retorno.append("É necessário preencher a Quilometragem");
				return retorno.toString();
			}

			if (revisao.getFuncionario().getId() < 0) {
				retorno.append("É Necessário preencher o Funcionário que realizou a Retirada");
				return retorno.toString();
			}

			if (revisao.getDataRevisao()==null) {
				retorno.append("A data da Revisao deve ser Preenchida");
				return retorno.toString();
			}

		} else {
			return null;
		}

		return null;

	}
}
