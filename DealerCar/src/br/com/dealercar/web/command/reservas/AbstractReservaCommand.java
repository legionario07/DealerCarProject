package br.com.dealercar.web.command.reservas;

import br.com.dealercar.core.fachada.IFachada;
import br.com.dealercar.core.fachada.ReservaFachada;
import br.com.dealercar.web.command.ICommand;

public abstract class AbstractReservaCommand implements ICommand {

	public IFachada fachada = new ReservaFachada();
}
