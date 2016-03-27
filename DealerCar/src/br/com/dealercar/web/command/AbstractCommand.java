package br.com.dealercar.web.command;

import br.com.dealercar.core.fachada.Fachada;
import br.com.dealercar.core.fachada.IFachada;

public abstract class AbstractCommand implements ICommand {

	public IFachada fachada = new Fachada();
}
