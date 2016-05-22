package br.com.dealercar.core.negocio.strategy;

import br.com.dealercar.core.autenticacao.Funcionario;
import br.com.dealercar.core.util.DataUtil;
import br.com.dealercar.domain.EntidadeDominio;

/**
 * Classe Strategy reponsável pela Validação de um Funcionario
 * 
 * @author Paulinho
 *
 */
public class ValidaFuncionario implements IValidacaoStrategy {

	/**
	 * 
	 * @param recebe um objeto Funcionario e faz a validação pelo ID
	 * @return Retorna um objeto Funcionario do BD válido ou Null se não for encontrado
	 */
	public String validar(EntidadeDominio entDominio) {

		StringBuffer retorno = null;
		Funcionario funcionario = null;

		// Verifica se a classe passada no Parametro eh um objeto Funcionario
		if (entDominio instanceof Funcionario) {
			retorno = new StringBuffer();
			funcionario = (Funcionario) entDominio;
			
			/**
			 * O cargo deve ser preenchido
			 */
			if (funcionario.getCargo().equals("")) {
				retorno.append("O cargo deve ser prenchido");
				return retorno.toString();
			}
			
			/**
			 * O login deve ser preenchido
			 */
			if (funcionario.getUsuario().getLogin()==null){
				retorno.append("O login deve ser deve ser prenchido");
				return retorno.toString();
			}
			
			if(funcionario.getUsuario().getLogin().equals("")) {
				retorno.append("O login deve ser deve ser prenchido");
				return retorno.toString();
			}
			
			/**
			 * DEve ser Ativo ou Não			 */
			if (funcionario.getUsuario().getAtivo().equals("")) {
				retorno.append("Deve ser ativo ou não");
				return retorno.toString();
			}
			
			
			/**
			 * O funcionário deve ter no minimo 16 anos
			 */
			if (DataUtil.devolverDataEmAnos(funcionario.getDataNasc())<16) {
				retorno.append("O funcionario deve ter no minimo 16 anos de Idade");
				return retorno.toString();
			}
			
			/**
			 * Não existe salario negativo
			 */
			if (funcionario.getSalario() < 0) {
				retorno.append("O Salário não pode ser negativo");
				return retorno.toString();
			}
			
			

		} else {
			return null;
		}

		return null;

	}

}
