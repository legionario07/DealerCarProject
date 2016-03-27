package br.com.dealercar.web.command.reservas;

import br.com.dealercar.core.aplicacao.Resultado;
import br.com.dealercar.domain.EntidadeDominio;

public class ConsultarReservaCommand extends AbstractReservaCommand { 
	public Resultado execute(EntidadeDominio entidade) {

		return fachada.consultar(entidade);
	}

}
