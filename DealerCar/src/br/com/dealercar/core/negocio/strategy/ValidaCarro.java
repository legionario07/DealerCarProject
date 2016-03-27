package br.com.dealercar.core.negocio.strategy;

import java.util.Calendar;

import br.com.dealercar.domain.EntidadeDominio;
import br.com.dealercar.domain.automotivos.Carro;
/**
 * Classe Strategy reponsável pela Validação de um Carro
 * @author Paulinho
 *
 */
public class ValidaCarro implements IValidacaoStrategy {

	/**
	 * 
	 * @param recebe um objeto Carro
	 * @return Retorna um objeto Carro do BD válido ou null se nao encontrar
	 */
	public String validar(EntidadeDominio entDominio) {

		StringBuffer retorno = null;
		Carro carro = null;

		// Verifica se a classe passada no Parametro eh um objeto Carro
		if (entDominio instanceof Carro) {
			retorno = new StringBuffer();
			carro = (Carro) entDominio;
			
			if (carro.getCategoria().getNome().equals("")) {
				retorno.append("O Carro deve pertencer a uma Categoria");
				return retorno.toString();
			}
			
			int anoCarro = Integer.parseInt(carro.getAno());
			Calendar c = Calendar.getInstance();
			int ano = c.get(Calendar.YEAR);
			
			if ((ano - anoCarro)>=16) {
				retorno.append("O carro deve ter seu ano de Fabricação inferior a 16 anos");
				return retorno.toString();
			}
			if (carro.getPlaca().equals("")) {
				retorno.append("O Placa do Carro deve ser preenchida");
				return retorno.toString();
			}

		} else {
			return null;
		}

		return null;

	}
}