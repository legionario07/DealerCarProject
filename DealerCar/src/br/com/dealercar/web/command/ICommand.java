package br.com.dealercar.web.command;

import br.com.dealercar.core.aplicacao.Resultado;
import br.com.dealercar.domain.EntidadeDominio;

public interface ICommand {
	
	public Resultado execute(EntidadeDominio entidade);

}
