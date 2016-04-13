package br.com.dealercar.web.command.negocio;

import br.com.dealercar.core.fachada.IFachada;
import br.com.dealercar.core.fachada.ConducaoFachada;
import br.com.dealercar.web.command.ICommand;

public abstract class AbstractConducaoCommand implements ICommand {

	public IFachada fachada = new ConducaoFachada();
}
