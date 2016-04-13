package br.com.dealercar.web.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.dealercar.core.aplicacao.Resultado;
import br.com.dealercar.domain.Cliente;
import br.com.dealercar.domain.EntidadeDominio;
import br.com.dealercar.domain.Estado;
import br.com.dealercar.web.command.ICommand;

@ManagedBean(name = "MBCliente")
@ViewScoped
public class ClienteBean extends AbstractBean implements Serializable {

	/**
	 * Controlando a evolução dos objetos serialidos.... Ex.: Salva um objeto em
	 * um arquivo, meses depois em que adicionar um método e ou atributo na sua
	 * classe. Quando tentar deserializar o objeto naão é permitido mais.
	 * Mantendo o serialVersionUID este erro não ocorre. Assim é permitido
	 * deserializar objetos que foram modificados.
	 */
	private static final long serialVersionUID = 1L;

	private Cliente cliente = new Cliente();

	private List<EntidadeDominio> listaClientes = new ArrayList<EntidadeDominio>();
	private List<EntidadeDominio> listaCidades = new ArrayList<EntidadeDominio>();
	private List<EntidadeDominio> listaEstados = new ArrayList<EntidadeDominio>();
	private int totalClientes;
	
	public List<EntidadeDominio> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(List<EntidadeDominio> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public int getTotalClientes() {
		return totalClientes;
	}

	public void setTotalClientes(int totalClientes) {
		this.totalClientes = totalClientes;
	}

	public Cliente getCliente() {
		return cliente;
	}


	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<EntidadeDominio> getListaCidades() {
		return listaCidades;
	}

	public void setListaCidades(List<EntidadeDominio> listaCidades) {
		this.listaCidades = listaCidades;
	}

	public List<EntidadeDominio> getListaEstados() {
		return listaEstados;
	}

	public void setListaEstados(List<EntidadeDominio> listaEstados) {
		this.listaEstados = listaEstados;
	}

	/**
	 * carrega a listagem de todos os objetos de Clientes e Cidades ao iniciar a
	 * tela e calcula a quantidade existente e coloca na variavel totalClientes
	 */
	@Override
	public void carregarListagem() {

		// escolhe o Command corretamente de acordo com a operacao
		ICommand command = mapCommands.get("LISTAR");
		Resultado resultado = new Resultado();
		resultado = command.execute(new Cliente());
		if (resultado != null) {
			listaClientes = resultado.getEntidades();
		}

		resultado = command.execute(new Estado());
		if (resultado != null) {
			listaEstados = resultado.getEntidades();
		}

		setTotalClientes(listaClientes.size());
		
		
	}

	@Override
	public void executar() {

		// recebe a operacao a ser realizada
		String operacao = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("param");

		// escolhe o Command corretamente de acordo com a operacao
		ICommand command = mapCommands.get(operacao);

		Resultado resultado = new Resultado();

		resultado = command.execute(cliente);

		if (resultado != null) {
			cliente = (Cliente) resultado.getEntidades().get(0);
		}

		setEhCadastrado(false);
		setJaPesquisei(false);
	}

	/**
	 * Carrega a lista de cidades de acordo com o Estado selecionado
	 */
	public void atualizarCidades() {

		// Retorna um estado completo de acordo com um ID
		ICommand command = mapCommands.get("CONSULTAR");

		Resultado resultado = new Resultado();
		resultado = command.execute(cliente.getEndereco().getCidade().getEstado());
		if (resultado != null) {
			cliente.getEndereco().getCidade().setEstado((Estado) resultado.getEntidades().get(0));
		}
		
		// Lista todas as cidades de um ESTADO
		command = mapCommands.get("LISTAR");
		resultado = new Resultado();
		resultado = command.execute(cliente.getEndereco().getCidade());

		if (resultado != null) {
			listaCidades = resultado.getEntidades();
		}
		

	}


	/**
	 * Pesquisa no BD um cliente de acordo com o CPF digitado pleo Usuário na
	 * TEla
	 */
	public void pesquisarPorCPF() {

		setEhCadastrado(false);
		setJaPesquisei(true);

		// Retorna um estado completo de acordo com um ID
		ICommand command = mapCommands.get("CONSULTAR");

		Resultado resultado = new Resultado();
		resultado = command.execute(cliente);
		
		//Cliente foi encontrado
		if (resultado.getEntidades().get(0) != null) {
			cliente = (Cliente) resultado.getEntidades().get(0);
			setEhCadastrado(true);
			setJaPesquisei(false);
			return;
		} else {
			String cpf = cliente.getCPF();
			cliente = new Cliente();
			cliente.setCPF(cpf);
		}

	}
	
	/**
	 * limpa a tela de pesquisa de Cliente do Usuario Deixando pronto para uma
	 * nova Pesquisa
	 */
	public void limparObjetos() {
		cliente = new Cliente();
		setEhCadastrado(false);
		setJaPesquisei(false);
	}

}
