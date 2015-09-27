package br.com.dealercar.strategy.valida;

import br.com.dealercar.domain.EntidadeDominio;

/**
 * 
 * @author Paulinho
 *Interface responsável pela validacao de algumas classes de dominio
 */
public interface IValidacaoStrategy {
	
	/**
	 * 
	 * @param entDominio Uma EntidadeDominio para ser validada
	 * @return uma Entidade Dominio se EntideDominio for encontrada ou uma EntidadeDominio co NUll
	 */
	EntidadeDominio validar(EntidadeDominio entDominio);
	

}
