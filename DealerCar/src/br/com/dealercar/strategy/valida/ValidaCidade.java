package br.com.dealercar.strategy.valida;

import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.dao.CidadeDAO;
import br.com.dealercar.domain.Cidade;
import br.com.dealercar.domain.EntidadeDominio;

/**
 * Classe Strategy reponsável pela Validação de uma Cidade
 * @author Paulinho
 *
 */
public class ValidaCidade implements IValidacaoStrategy{

	/**
	 * 
	 * @param recebe um objeto Cidade e faz a Validação pelo Nome
	 * @return Retorna um objeto Cidade do BD válido ou Null se não for encontrado
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
