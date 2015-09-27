package br.com.dealercar.strategy.valida;

import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.dao.CorDAO;
import br.com.dealercar.domain.Cor;
import br.com.dealercar.domain.EntidadeDominio;

public class ValidaCor implements IValidacaoStrategy {

	/**
	 * 
	 * @param recebe um objeto Cor
	 * @return Retorna um objeto Cor do BD válido ou Null se não for encontrado
	 */
	public EntidadeDominio validar(EntidadeDominio entDominio) {

		Cor rCor = null;

		//Verifica se a classe passada no Parametro eh um objeto Cor
		if (entDominio instanceof Cor) {

			Cor retorno = (Cor) entDominio;
			
			CorDAO dao = new CorDAO();

			List<Cor> lista = new ArrayList<Cor>();
			lista = dao.listarTodos();

			for (Cor c : lista) {
				if (retorno.getNome().toUpperCase().equals(c.getNome())) {
					
					rCor = c;

				}
			}
		}
		
		return rCor;

	}
}
