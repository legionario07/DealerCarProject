package br.com.dealercar.core.negocio.strategy;

import java.util.Calendar;

import br.com.dealercar.domain.EntidadeDominio;
import br.com.dealercar.domain.automotivos.Carro;
/**
 * Classe Strategy repons�vel pela Valida��o de um Carro
 * @author Paulinho
 *
 */
public class ValidaCarro implements IValidacaoStrategy {

	/**
	 * 
	 * @param recebe um objeto Carro
	 * @return Retorna uma String null se todos os dados foram v�lidados
	 */
	public String validar(EntidadeDominio entDominio) {

		StringBuffer retorno = null;
		Carro carro = null;

		// Verifica se a classe passada no Parametro eh um objeto Carro
		if (entDominio instanceof Carro) {
			retorno = new StringBuffer();
			carro = (Carro) entDominio;
			
			if (carro.getCategoria().getId()<=0) {
				retorno.append("O Carro deve pertencer a uma Categoria");
				return retorno.toString();
			}
			
			int anoCarro = Integer.parseInt(carro.getAno());
			Calendar c = Calendar.getInstance();
			int ano = c.get(Calendar.YEAR);
			
			/**
			 * Ano de Fabrica��o deve ser inferior a 16 anor
			 */
			if ((ano - anoCarro)>=16) {
				retorno.append("O carro deve ter seu ano de Fabrica��o inferior a 16 anos");
				return retorno.toString();
			}
			if (carro.getPlaca().equals("")) {
				retorno.append("O Placa do Carro deve ser preenchida");
				return retorno.toString();
			}
			
			if(!carro.getPlaca().matches("\\w{3}-\\d{4}")){
				retorno.append("Placa Invalida");
				return retorno.toString();
			}

		} else {
			return null;
		}

		return null;

	}
}