package br.com.dealercar.web.command;

import br.com.dealercar.core.aplicacao.Resultado;
import br.com.dealercar.domain.EntidadeDominio;

public class EditarCommand extends AbstractCommand { 
	public Resultado execute(EntidadeDominio entidade) {

		return fachada.editar(entidade);
	}

}
