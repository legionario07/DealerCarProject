package br.com.dealercar.web.bean;

import java.util.HashMap;
import java.util.Map;

import br.com.dealercar.web.command.CadastrarCommand;
import br.com.dealercar.web.command.ConsultarCommand;
import br.com.dealercar.web.command.EditarCommand;
import br.com.dealercar.web.command.ExcluirCommand;
import br.com.dealercar.web.command.ICommand;
import br.com.dealercar.web.command.ListarCommand;
import br.com.dealercar.web.command.reservas.CadastrarReservaCommand;
import br.com.dealercar.web.command.reservas.ConsultarReservaCommand;
import br.com.dealercar.web.command.reservas.EditarReservaCommand;
import br.com.dealercar.web.command.reservas.ExcluirReservaCommand;
import br.com.dealercar.web.command.reservas.ListarReservaCommand;

public abstract class AbstractBean implements IBean{
	
	protected static Map<String, ICommand> mapCommands = null;
	protected static Map<String, ICommand> mapReservaCommands = null;
	
	
	private boolean ehCadastrado = false;
	private boolean jaPesquisei = false;
	
	public AbstractBean(){
		mapCommands = new HashMap<String, ICommand>();
		mapCommands.put("EDITAR", new EditarCommand());
		mapCommands.put("EXCLUIR", new ExcluirCommand());
		mapCommands.put("CADASTRAR", new CadastrarCommand());
		mapCommands.put("CONSULTAR", new ConsultarCommand());
		mapCommands.put("LISTAR", new ListarCommand());
		
		mapReservaCommands = new HashMap<String, ICommand>();
		mapReservaCommands.put("EDITAR", new EditarReservaCommand());
		mapReservaCommands.put("EXCLUIR", new ExcluirReservaCommand());
		mapReservaCommands.put("CADASTRAR", new CadastrarReservaCommand());
		mapReservaCommands.put("CONSULTAR", new ConsultarReservaCommand());
		mapReservaCommands.put("LISTAR", new ListarReservaCommand());
		
	}

	/**
	 * Realiza os Crud no Bean
	 */
	public abstract void executar();
	
	public boolean isEhCadastrado() {
		return ehCadastrado;
	}
	public void setEhCadastrado(boolean ehCadastrado) {
		this.ehCadastrado = ehCadastrado;
	}
	public boolean isJaPesquisei() {
		return jaPesquisei;
	}
	public void setJaPesquisei(boolean jaPesquisei) {
		this.jaPesquisei = jaPesquisei;
	}

}
