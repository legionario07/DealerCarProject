package br.com.dealercar.core.negocio.strategy;

import br.com.dealercar.core.autenticacao.Permissao;
import br.com.dealercar.domain.EntidadeDominio;

/**
 * Classe Strategy reponsável pela Validação de um Permissao
 * 
 * @author Paulinho
 *
 */
public class ValidaPermissao implements IValidacaoStrategy {

	/**
	 * 
	 * @param recebe
	 *            um objeto Permissao e faz a Validação pelo Nome
	 * @return Retorna um objeto Permissao do BD válido ou Null se não for encontrado
	 */
	public String validar(EntidadeDominio entDominio) {

		StringBuffer retorno = null;
		Permissao permissao = null;

		// Verifica se a classe passada no Parametro eh um objeto Permissao
		if (entDominio instanceof Permissao) {
			retorno = new StringBuffer();
			permissao = (Permissao) entDominio;

			if (permissao.getNivel().equals("")) {
				retorno.append("O Nivel da Permissao deve ser preenchido");
				return retorno.toString();
			}

		} else {
			return null;
		}

		return null;
	}
}
