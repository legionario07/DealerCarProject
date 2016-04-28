package br.com.dealercar.core.negocio.strategy;

import br.com.dealercar.core.util.DataUtil;
import br.com.dealercar.domain.EntidadeDominio;
import br.com.dealercar.domain.conducao.Retirada;

/**
 * Classe Strategy reponsável pela Validação de um Retirada
 * 
 * @author Paulinho
 *
 */
public class ValidaRetirada implements IValidacaoStrategy {

	/**
	 * 
	 * @param recebe
	 *            um objeto Retirada 
	 * @return Retorna uma String se com a mensagem do erro
	 */
	public String validar(EntidadeDominio entDominio) {

		StringBuffer retorno = null;
		Retirada retirada = null;

		// Verifica se a classe passada no Parametro eh um objeto Retirada
		if (entDominio instanceof Retirada) {
			retorno = new StringBuffer();
			retirada = (Retirada) entDominio;

			if (retirada.getCarro().getPlaca().equals("")) {
				retorno.append("A retirada deve ter um Carro");
				return retorno.toString();
			}
			if (retirada.getCliente().getCPF().equals("")) {
				retorno.append("O Cliente deve ser Preechido");
				return retorno.toString();
			}
			if (retirada.getFuncionario().getId()<0) {
				retorno.append("É Necessário preencher o Funcionário que realizou a Retirada");
				return retorno.toString();
			}
			if (retirada.getOpcional().getSeguro().getCodigo()==0) {
				retorno.append("É Necessário preencher um Seguro");
				return retorno.toString();
			}
			if (retirada.getOpcional().getSeguro().getTipoSeguro().getId()==0) {
				retorno.append("É Necessário preencher um Tipo de Seguro");
				return retorno.toString();
			}
			
			// se i diferente de 1 a data esta incorreta
			if (DataUtil.compararDatas(retirada.getDataRetirada(), retirada.getDataDevolucao()) == -1) {
				// colocando formato string para armazenar no banco de dados
				retorno.append("A data de Devolução não pode ser menor que " + retirada.getDataRetirada());
				return retorno.toString();
			}

		} else {
			return null;
		}
		
		return null;

	}
}
