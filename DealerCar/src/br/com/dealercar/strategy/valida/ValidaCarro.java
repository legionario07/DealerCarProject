package br.com.dealercar.strategy.valida;

import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.dao.automotivos.CarroDAO;
import br.com.dealercar.domain.EntidadeDominio;
import br.com.dealercar.domain.automotivos.Carro;

public class ValidaCarro implements IValidacaoStrategy {

	/**
	 * 
	 * @param recebe um objeto Carro
	 * @return Retorna um objeto Carro do BD válido ou null se nao encontrar
	 */
	public EntidadeDominio validar(EntidadeDominio entDominio) {

		Carro rCarro = null;

		//Verifica se a classe passada no Parametro eh um objeto Carro
		if (entDominio instanceof Carro) {

			Carro retorno = (Carro) entDominio;
			
			CarroDAO dao = new CarroDAO();

			List<Carro> lista = new ArrayList<Carro>();
			lista = dao.listarTodos();

			for (Carro c : lista) {
				if (retorno.getPlaca().toUpperCase().equals(c.getPlaca())) {
					
					rCarro = c;

				}
			}
		}
		
		return rCarro;

	}

}