package br.com.dealercar.core.negocio.strategy;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.dealercar.core.util.DataUtil;
import br.com.dealercar.domain.EntidadeDominio;
import br.com.dealercar.domain.conducao.Reserva;
import br.com.dealercar.domain.enums.SituacaoReserva;

/**
 * Classe Strategy reponsável pela Validação de um Reserva
 * 
 * @author Paulinho
 *
 */
public class ValidaReserva implements IValidacaoStrategy {

	/**
	 * 
	 * @param recebe
	 *            um objeto Reserva 
	 * @return Retorna uma String se com a mensagem do erro
	 */
	public String validar(EntidadeDominio entDominio) {

		StringBuffer retorno = null;
		Reserva reserva = null;

		// Verifica se a classe passada no Parametro eh um objeto Reserva
		if (entDominio instanceof Reserva) {
			retorno = new StringBuffer();
			reserva = (Reserva) entDominio;

			if (reserva.getModelo().getId() < 0) {
				retorno.append("A reserva deve ter um Modelo");
				return retorno.toString();
			}
			
			if (reserva.getFuncionario().getId()<0) {
				retorno.append("É Necessário preencher o Funcionário que realizou a Retirada");
				return retorno.toString();
			}

			if (reserva.getCliente().getCPF().equals("")) {
				retorno.append("O Cliente deve ser Preenchido");
				return retorno.toString();
			}

			// se i diferente de 1 a data esta incorreta
			if (DataUtil.compararDatas(reserva.getDataCadastroReserva(), reserva.getDataFim()) != 1) {
				// colocando formato string para armazenar no banco de dados
				SimpleDateFormat stf = new SimpleDateFormat("dd/MM/yyyy");
				retorno.append("A data para Reserva está incorreta. " + "Deve ser maior que "
						+ stf.format(reserva.getDataCadastroReserva()));
				return retorno.toString();
			}

			List<SituacaoReserva> listaReservas = new ArrayList<SituacaoReserva>();
			listaReservas = Arrays.asList(SituacaoReserva.values());

			// Se for um cadastro a reserva sempre será cadastrada como ATIVO
			if (reserva.getSituacao() == null) {
				reserva.setSituacao(SituacaoReserva.ATIVO);
				return null;
			} else {
				for (SituacaoReserva s : listaReservas) {
					retorno = new StringBuffer();
					retorno.append("A Sitação da Reserva esta incorreta");
					if (!reserva.getSituacao().equals(s)) {
						return null;
					}
				}
			}

		} else {
			return null;
		}
		
		return null;

	}
}
