package br.com.dealercar.strategy.valida;

import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.dao.ClienteDAO;
import br.com.dealercar.domain.EntidadeDominio;
import br.com.dealercar.domain.Cliente;

/**
 * Classe Strategy repons�vel pela Valida��o de um Cliente
 * @author Paulinho
 *
 */
public class ValidaCliente implements IValidacaoStrategy{
	
	/**
	 * 
	 * @param recebe um objeto Cliente e faz a valida��o pelo CPF
	 * @return Retorna um objeto Cliente do BD v�lido ou Null se n�o for encontrado
	 */
	public EntidadeDominio validar(EntidadeDominio entDominio) {

		Cliente rCliente = null;

		//Verifica se a classe passada no Parametro eh um objeto Cliente
		if (entDominio instanceof Cliente) {

			Cliente retorno = (Cliente) entDominio;
			
			ClienteDAO dao = new ClienteDAO();

			List<Cliente> lista = new ArrayList<Cliente>();
			lista = dao.listarTodos();

			for (Cliente c : lista) {
				if (retorno.getCPF().equals(c.getCPF())) {
					
					rCliente = c;

				}
			}
		}
		
		return rCliente;

	}

}
