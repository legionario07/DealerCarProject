package br.com.dealercar.core.negocio.strategy;

import br.com.dealercar.domain.EntidadeDominio;
import br.com.dealercar.domain.automotivos.Modelo;

/**
 * Classe Strategy reponsável pela Validação de um Modelo de Carro
 * @author Paulinho
 *
 */
public class ValidaModelo implements IValidacaoStrategy {
	/**
	 * 
	 * @param recebe um objeto Modelo
	 * @return Retorna um objeto Modelo do BD válido ou Null se não for encontrado
	 */
	public String validar(EntidadeDominio entDominio) {

		StringBuffer retorno = null;
		Modelo modelo = null;

		// Verifica se a classe passada no Parametro eh um objeto Modelo
		if (entDominio instanceof Modelo) {
			retorno = new StringBuffer();
			modelo = (Modelo) entDominio;
			
			if (modelo.getNome().equals("")) {
				retorno.append("O nome da Modelo deve ser preenchido");
				return retorno.toString();
			}
			
			if (modelo.getFabricante()==null || modelo.getFabricante().getId()<1) {
				retorno.append("O Fabricante deve ser preenchido");
				return retorno.toString();
			}
			
			
		} else {
			return null;
		}

		return null;


	}
}
