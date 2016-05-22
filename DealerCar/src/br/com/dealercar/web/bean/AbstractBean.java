package br.com.dealercar.web.bean;

import java.util.HashMap;
import java.util.Map;

import br.com.dealercar.web.command.CadastrarCommand;
import br.com.dealercar.web.command.ConsultarCommand;
import br.com.dealercar.web.command.EditarCommand;
import br.com.dealercar.web.command.ExcluirCommand;
import br.com.dealercar.web.command.ICommand;
import br.com.dealercar.web.command.ListarCommand;
import br.com.dealercar.web.command.negocio.CadastrarConducaoCommand;
import br.com.dealercar.web.command.negocio.ConsultarConducaoCommand;
import br.com.dealercar.web.command.negocio.EditarConducaoCommand;
import br.com.dealercar.web.command.negocio.ExcluirConducaoCommand;
import br.com.dealercar.web.command.negocio.ListarConducaoCommand;

public abstract class AbstractBean implements IBean{
	
	/**
	 * Command responsavel pelos CRUD
	 */
	protected static Map<String, ICommand> mapCommands = null;
	/**
	 * Command responsavel pelas Conduções
	 */
	protected static Map<String, ICommand> mapConducaoCommands = null;
	
	
	private boolean ehCadastrado = false;
	private boolean jaPesquisei = false;
	
	public AbstractBean(){
		mapCommands = new HashMap<String, ICommand>();
		mapCommands.put("EDITAR", new EditarCommand());
		mapCommands.put("EXCLUIR", new ExcluirCommand());
		mapCommands.put("CADASTRAR", new CadastrarCommand());
		mapCommands.put("CONSULTAR", new ConsultarCommand());
		mapCommands.put("LISTAR", new ListarCommand());
		
		mapConducaoCommands = new HashMap<String, ICommand>();
		mapConducaoCommands.put("EDITAR", new EditarConducaoCommand());
		mapConducaoCommands.put("EXCLUIR", new ExcluirConducaoCommand());
		mapConducaoCommands.put("CADASTRAR", new CadastrarConducaoCommand());
		mapConducaoCommands.put("CONSULTAR", new ConsultarConducaoCommand());
		mapConducaoCommands.put("LISTAR", new ListarConducaoCommand());
		
	}

	/**
	 * Realiza os Crud no Bean
	 * 	
	 * Método principal - De acordo com o Parametro passado na View
	 * é chamado o Command e executado a respectiva Fachada
	 */
	public abstract void executar();
	
	/**
	 * Limpa os dados na View
	 */
	public abstract void limparObjetos();
	
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
