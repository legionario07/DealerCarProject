package br.com.dealercar.strategy.pesquisa;

import br.com.dealercar.domain.EntidadeDominio;
import br.com.dealercar.domain.automotivos.Carro;

/**
 * 
 * @author Paulinho
 *Interface responsável pelo consulta no Banco de Dados de Acordo com algum critério
 */
public interface IConsultaCarroStrategy {
	
	/**
	 * 
	 * @param entDominio Um Objeto Carro para ser cosultado
	 * @return uma Entidade Dominio se EntideDominio for encontrada ou uma EntidadeDominio com NUll
	 */
	EntidadeDominio consultar(Carro carro);
	

}
