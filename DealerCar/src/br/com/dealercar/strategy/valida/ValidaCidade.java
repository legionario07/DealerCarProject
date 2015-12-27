package br.com.dealercar.strategy.valida;

import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.dao.CidadeDAO;
import br.com.dealercar.domain.Cidade;
import br.com.dealercar.domain.EntidadeDominio;

/**
 * Classe Strategy repons�vel pela Valida��o de uma Cidade
 * @author Paulinho
 *
 */
public class ValidaCidade implements IValidacaoStrategy{

	/**
	 * 
	 * @param recebe um objeto Cidade e faz a Valida��o pelo Nome
	 * @return Retorna um objeto Cidade do BD v�lido ou Null se n�o for encontrado
	 */
	public EntidadeDominio validar(EntidadeDominio entDominio) {

		Cidade rCidade = null;

		//Verifica se a classe passada no Parametro eh um objeto Cidade
		if (entDominio instanceof Cidade) {

			Cidade retorno = (Cidade) entDominio;
			
			CidadeDAO dao = new CidadeDAO();

			List<Cidade> lista = new ArrayList<Cidade>();
			lista = dao.listarTodos();

			for (Cidade c : lista) {
				if (retorno.getNome().toUpperCase().equals(c.getNome())) {
					
					rCidade = c;

				}
			}
		}
		
		return rCidade;

	}

}
