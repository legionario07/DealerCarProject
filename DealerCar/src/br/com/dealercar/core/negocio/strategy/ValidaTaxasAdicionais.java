package br.com.dealercar.core.negocio.strategy;

import br.com.dealercar.domain.EntidadeDominio;
import br.com.dealercar.domain.taxasadicionais.TaxasAdicionais;

/**
 * Classe Strategy repons�vel pela Valida��o de uma TaxaAdicionais
 * 
 * @author Paulinho
 *
 */
public class ValidaTaxasAdicionais implements IValidacaoStrategy {

	/**
	 * 
	 * @param recebe
	 *            um objeto TaxaAdicionais e faz a Valida��o pelo Nome
	 * @return Retorna um objeto TaxaAdicionais do BD v�lido ou Null se n�o for
	 *         encontrado
	 */
	public String validar(EntidadeDominio entDominio) {

		StringBuffer retorno = null;
		TaxasAdicionais taxaAdicionais = null;

		// Verifica se a classe passada no Parametro eh um objeto TaxaAdicionais
		if (entDominio instanceof TaxasAdicionais) {
			retorno = new StringBuffer();
			taxaAdicionais = (TaxasAdicionais) entDominio;
			
			if (taxaAdicionais.getValor() <= 0 ) {
				retorno.append("O valor da TaxaAdicionais deve ser maior que 0");
				return retorno.toString();
			}
			if (taxaAdicionais.getDescricao().equals("")) {
				retorno.append("A Descri��o deve ser prenchida");
				return retorno.toString();
			}

		} else {
			return null;
		}

		return null;

	}

}