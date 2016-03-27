package br.com.dealercar.core.negocio.strategy;

import br.com.dealercar.domain.EntidadeDominio;
import br.com.dealercar.domain.Pessoa;

/**
 * Classe Strategy reponsável pela Validação de um Pessoa
 * 
 * @author Paulinho
 *
 */
public class ValidaPessoa implements IValidacaoStrategy {

	/**
	 * 
	 * @param recebe
	 *            um objeto Pessoa e faz a validação pelo CPF
	 * @return Retorna um objeto Pessoa do BD válido ou Null se não for
	 *         encontrado
	 */
	public String validar(EntidadeDominio entDominio) {

		StringBuffer retorno = null;

		// Verifica se a classe passada no Parametro eh um objeto Pessoa
		if (Pessoa.class.isInstance(entDominio)) {
			retorno = new StringBuffer();
			Pessoa p = (Pessoa) entDominio;
			
			/**
			 * Nome deve estar preenchido
			 */
			if (p.getNome().equals("")) {
				retorno.append("O nome deve ser preenchido");
				return retorno.toString();
			}
			/**
			 * Não deve existir nome curto
			 */
			if (p.getNome().length() < 5) {
				retorno.append("O nome deve ser preenchido por completo");
				return retorno.toString();
			}
			/**
			 * Sexo deve estar preenchido com MASCULINO ou FEMININO
			 */
			if (!(p.getSexo().equals("MASCULINO") || p.getSexo().equals("FEMININO"))) {
				retorno.append("O sexo deve ser MASCULINO OU FEMININO");
				return retorno.toString();
			}
			/**
			 * Bairro deve estar preenchido
			 */
			if (p.getEndereco().getBairro().equals("")) {
				retorno.append("O Bairro deve ser preenchido");
				return retorno.toString();
			}
			/**
			 * Rua deve estar preenchida
			 */
			if (p.getEndereco().getRua().equals("")) {
				retorno.append("A Rua deve ser preenchida");
				return retorno.toString();
			}
			/**
			 * Cidade deve estar preenchida
			 */
			if (p.getEndereco().getCidade().equals("")) {
				retorno.append("A Cidade deve ser preenchida");
				return retorno.toString();
			}
			/**
			 * Estado deve estar preenchido
			 */
			if (p.getEndereco().getCidade().getEstado().getNome().equals("")) {
				retorno.append("O Estado deve deve ser preenchido");
				return retorno.toString();
			} else {
				return null;
			}

		}

		return null;
	}
}
