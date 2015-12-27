package br.com.dealercar.strategy.valida;

import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.dao.FuncionarioDAO;
import br.com.dealercar.domain.EntidadeDominio;
import br.com.dealercar.domain.Funcionario;

/**
 * Classe Strategy repons�vel pela Valida��o de um Funcionario
 * @author Paulinho
 *
 */
public class ValidaFuncionario implements IValidacaoStrategy {


	/**
	 * 
	 * @param recebe um objeto Funcionario e faz a valida��o pelo ID
	 * @return Retorna um objeto Funcionario do BD v�lido ou Null se n�o for encontrado
	 */
	public EntidadeDominio validar(EntidadeDominio entDominio) {

		Funcionario rFuncionario = null;

		//Verifica se a classe passada no Parametro eh um objeto Funcionario
		if (entDominio instanceof Funcionario) {

			Funcionario retorno = (Funcionario) entDominio;
			
			FuncionarioDAO dao = new FuncionarioDAO();

			List<Funcionario> lista = new ArrayList<Funcionario>();
			lista = dao.listarTodos();

			for (Funcionario c : lista) {
				if (retorno.getId() == c.getId()) {
					
					rFuncionario = c;

				}
			}
		}
		
		return rFuncionario;

	}


}
