package br.com.dealercar.core.negocio.strategy;

import java.text.SimpleDateFormat;

import br.com.dealercar.core.negocio.Reserva;
import br.com.dealercar.core.util.DataUtil;
import br.com.dealercar.domain.EntidadeDominio;

/**
 * Classe Strategy reponsável pela Validação de um Reserva
 * @author Paulinho
 *
 */
public class ValidaReserva implements IValidacaoStrategy {
	
	/**
	 * 
	 * @param recebe um objeto Reserva e faz a validação pelo CPF
	 * @return Retorna um objeto Reserva do BD válido ou Null se não for encontrado
	 */
	public String validar(EntidadeDominio entDominio) {

		StringBuffer retorno = null;
		Reserva reserva = null;

		// Verifica se a classe passada no Parametro eh um objeto Reserva
		if (entDominio instanceof Reserva) {
			retorno = new StringBuffer();
			reserva = (Reserva) entDominio;
			
			if (reserva.getModelo().getNome().equals("")) {
				retorno.append("A reserva deve ter um Modelo");
				return retorno.toString();
			}
			
			if (reserva.getCliente().getNome().equals("")) {
				retorno.append("O Cliente deve ser Preechido");
				return retorno.toString();
			}
			
			if (!reserva.getSituacao().equals("ATIVO") &&
					!reserva.getSituacao().equals("CANCELADO") &&
					!reserva.getSituacao().equals("FINALIZADO")) {
				retorno.append("A Sitação da Reserva esta incorreta");
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
			

		} else {
			return null;
		}

		return null;

	}
}
