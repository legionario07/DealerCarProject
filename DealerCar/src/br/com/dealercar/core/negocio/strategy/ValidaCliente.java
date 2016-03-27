package br.com.dealercar.core.negocio.strategy;

import br.com.dealercar.core.util.DataUtil;
import br.com.dealercar.domain.Cliente;
import br.com.dealercar.domain.EntidadeDominio;

/**
 * Classe Strategy reponsável pela Validação de um Cliente
 * @author Paulinho
 *
 */
public class ValidaCliente implements IValidacaoStrategy {
	
	/**
	 * 
	 * @param recebe um objeto Cliente e faz a validação pelo CPF
	 * @return Retorna um objeto Cliente do BD válido ou Null se não for encontrado
	 */
	public String validar(EntidadeDominio entDominio) {

		StringBuffer retorno = null;
		Cliente cliente = null;

		// Verifica se a classe passada no Parametro eh um objeto Cliente
		if (entDominio instanceof Cliente) {
			retorno = new StringBuffer();
			cliente = (Cliente) entDominio;
			
			/**
			 * Verificando o preenchimento do campo telefone
			 */
			if (cliente.getTelefone().length()<8) {
				retorno.append("Telefone Inválido");
				return retorno.toString();
			}
			/**
			 * O nome da Mãe deve ser preenchido
			 */
			if (cliente.getNomeMae().equals("")) {
				retorno.append("O nome da mãe deve ser preenchido");
				return retorno.toString();
			}
			
			/**
			 * O Cliente deve ser maior de 18 anos
			 */
			if (DataUtil.devolverDataEmAnos(cliente.getDataNasc())<18) {
				retorno.append("O Cliente deve ter no minimo 18 anos de Idade");
				return retorno.toString();
			}
			
			/**
			 * O cliente deve ter menos de 110 anos de idade
			 */
			if (DataUtil.validarIdadeMaxima(cliente.getDataNasc()) == -1) {
				retorno.append("O cliente não pode ter mais de 110 anos de idade.");
				return retorno.toString();
			}
			

		} else {
			return null;
		}

		return null;

	}
}
