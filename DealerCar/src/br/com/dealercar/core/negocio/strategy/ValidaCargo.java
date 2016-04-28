package br.com.dealercar.core.negocio.strategy;

import br.com.dealercar.domain.Cargo;
import br.com.dealercar.domain.EntidadeDominio;

/**
 * Classe Strategy repons�vel pela Valida��o de um Cargo
 * 
 * @author Paulinho
 *
 */
public class ValidaCargo implements IValidacaoStrategy {

	/**
	 * 
	 * @param recebe
	 *            um objeto Cargo e faz a Valida��o pelo Nome
	 * @return Retorna um objeto Cargo do BD v�lido ou Null se n�o for encontrado
	 */
	public String validar(EntidadeDominio entDominio) {

		StringBuffer retorno = null;
		Cargo cargo = null;

		// Verifica se a classe passada no Parametro eh um objeto Cargo
		if (entDominio instanceof Cargo) {
			retorno = new StringBuffer();
			cargo = (Cargo) entDominio;

			if (cargo.getNome().equals("")) {
				retorno.append("O nome da Cargo deve ser preenchido");
				return retorno.toString();
			}

		} else {
			return null;
		}

		return null;
	}
}
