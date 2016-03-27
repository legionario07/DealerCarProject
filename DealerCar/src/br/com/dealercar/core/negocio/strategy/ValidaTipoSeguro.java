package br.com.dealercar.core.negocio.strategy;

import br.com.dealercar.domain.EntidadeDominio;
import br.com.dealercar.domain.itensopcionais.TipoSeguro;

/**
 * Classe Strategy reponsável pela Validação de um TipoSeguro de Carro
 * @author Paulinho
 *
 */
public class ValidaTipoSeguro implements IValidacaoStrategy {
	/**
	 * 
	 * @param recebe um objeto TipoSeguro
	 * @return Retorna um objeto TipoSeguro do BD válido ou Null se não for encontrado
	 */
	public String validar(EntidadeDominio entDominio) {

		StringBuffer retorno = null;
		TipoSeguro tipoSeguro = null;

		// Verifica se a classe passada no Parametro eh um objeto TipoSeguro
		if (entDominio instanceof TipoSeguro) {
			retorno = new StringBuffer();
			tipoSeguro = (TipoSeguro) entDominio;
			
			if (tipoSeguro.getNome().equals("")) {
				retorno.append("O nome do TipoSeguro deve ser preenchido");
				return retorno.toString();
			}
			
		} else {
			return null;
		}

		return null;


	}
}
