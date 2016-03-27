package br.com.dealercar.core.negocio.strategy;

import br.com.dealercar.domain.EntidadeDominio;
import br.com.dealercar.domain.itensopcionais.Seguro;

/**
 * Classe Strategy reponsável pela Validação de um Seguro de Carro
 * @author Paulinho
 *
 */
public class ValidaSeguro implements IValidacaoStrategy {
	/**
	 * 
	 * @param recebe um objeto Seguro
	 * @return Retorna um objeto Seguro do BD válido ou Null se não for encontrado
	 */
	public String validar(EntidadeDominio entDominio) {

		StringBuffer retorno = null;
		Seguro seguro = null;

		// Verifica se a classe passada no Parametro eh um objeto Seguro
		if (entDominio instanceof Seguro) {
			retorno = new StringBuffer();
			seguro = (Seguro) entDominio;
			
			if (seguro.getDescricao().equals("")) {
				retorno.append("O nome do Seguro deve ser preenchido");
				return retorno.toString();
			}
			if (seguro.getValor()<=0) {
				retorno.append("O valor do Seguro deve ser maior que 0");
				return retorno.toString();
			}
			if (seguro.getTipoSeguro()==null || seguro.getTipoSeguro().getId()<1) {
				retorno.append("O Tipo de Seguro deve ser preenchido");
				return retorno.toString();
			} 
			
		} else {
			return null;
		}

		return null;


	}
}
