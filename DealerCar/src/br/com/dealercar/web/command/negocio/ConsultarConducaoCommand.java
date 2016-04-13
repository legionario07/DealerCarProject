package br.com.dealercar.web.command.negocio;

import br.com.dealercar.core.aplicacao.Resultado;
import br.com.dealercar.domain.EntidadeDominio;

public class ConsultarConducaoCommand extends AbstractConducaoCommand { 
	public Resultado execute(EntidadeDominio entidade) {

		return fachada.consultar(entidade);
	}

}
