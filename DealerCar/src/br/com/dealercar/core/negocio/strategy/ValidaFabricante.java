package br.com.dealercar.core.negocio.strategy;

import java.io.Serializable;

import br.com.dealercar.domain.EntidadeDominio;
import br.com.dealercar.domain.automotivos.Fabricante;

/**
 * Classe Strategy reponsável pela Validação de um FAbricante
 * @author Paulinho
 *
 */
public class ValidaFabricante implements IValidacaoStrategy, Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param recebe um objeto Fabricante e faz a Validação pelo Nome
	 * @return Retorna um objeto Fabricante do BD válido ou Null se não for encontrado
	 */
	public String validar(EntidadeDominio entDominio) {

		StringBuffer retorno = null;
		Fabricante fabricante = null;

		// Verifica se a classe passada no Parametro eh um objeto Fabricante
		if (entDominio instanceof Fabricante) {
			retorno = new StringBuffer();
			fabricante = (Fabricante) entDominio;
			
			if (fabricante.getNome().equals("")) {
				retorno.append("O nome da Fabricante deve ser preenchido");
				return retorno.toString();
			}

		} else {
			return null;
		}

		return null;


	}

}
