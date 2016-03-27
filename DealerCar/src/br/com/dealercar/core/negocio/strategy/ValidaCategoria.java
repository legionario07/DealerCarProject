package br.com.dealercar.core.negocio.strategy;

import br.com.dealercar.domain.EntidadeDominio;
import br.com.dealercar.domain.automotivos.Categoria;

/**
 * Classe Strategy reponsável pela Validação de uma Categoria
 * 
 * @author Paulinho
 *
 */
public class ValidaCategoria implements IValidacaoStrategy {

	/**
	 * 
	 * @param recebe
	 *            um objeto Categoria e faz a Validação pelo Nome
	 * @return Retorna um objeto Categoria do BD válido ou Null se não for
	 *         encontrado
	 */
	public String validar(EntidadeDominio entDominio) {

		StringBuffer retorno = null;
		Categoria categoria = null;

		// Verifica se a classe passada no Parametro eh um objeto Categoria
		if (entDominio instanceof Categoria) {
			retorno = new StringBuffer();
			categoria = (Categoria) entDominio;
			
			if (categoria.getValorDiaria() <= 0 || categoria.getValorDiaria() == null) {
				retorno.append("O valor de locação da Categoria deve ser maior que 0");
				return retorno.toString();
			}
			if (categoria.getDescricao().equals("")) {
				retorno.append("A Descrição deve ser prenchida");
				return retorno.toString();
			}
			if (categoria.getNome().equals("")) {
				retorno.append("O nome da Categoria deve ser preenchido");
				return retorno.toString();
			}

		} else {
			return null;
		}

		return null;

	}

}