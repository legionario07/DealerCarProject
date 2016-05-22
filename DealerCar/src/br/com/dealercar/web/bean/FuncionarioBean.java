package br.com.dealercar.web.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.dealercar.core.aplicacao.Resultado;
import br.com.dealercar.core.autenticacao.Funcionario;
import br.com.dealercar.core.autenticacao.Permissao;
import br.com.dealercar.domain.Cargo;
import br.com.dealercar.domain.EntidadeDominio;
import br.com.dealercar.domain.Estado;
import br.com.dealercar.web.command.ICommand;

@javax.faces.bean.ManagedBean(name = "MBFuncionario")
@ViewScoped
public class FuncionarioBean extends AbstractBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Funcionario funcionario = new Funcionario();
	private List<EntidadeDominio> listaFuncionario = new ArrayList<EntidadeDominio>();
	private List<EntidadeDominio> listaCidades = new ArrayList<EntidadeDominio>();
	private List<EntidadeDominio> listaEstados = new ArrayList<EntidadeDominio>();
	private List<EntidadeDominio> listaPermissoes = new ArrayList<EntidadeDominio>();
	private List<EntidadeDominio> listaCargos = new ArrayList<EntidadeDominio>();
	private int totalFuncionario;

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public List<EntidadeDominio> getListaEstados() {
		return listaEstados;
	}

	public void setListaEstados(List<EntidadeDominio> listaEstados) {
		this.listaEstados = listaEstados;
	}

	public List<EntidadeDominio> getListaFuncionario() {
		return listaFuncionario;
	}

	public void setListaFuncionario(List<EntidadeDominio> listaFuncionario) {
		this.listaFuncionario = listaFuncionario;
	}

	public List<EntidadeDominio> getListaCidades() {
		return listaCidades;
	}

	public void setListaCidades(List<EntidadeDominio> listaCidades) {
		this.listaCidades = listaCidades;
	}

	public List<EntidadeDominio> getListaPermissoes() {
		return listaPermissoes;
	}

	public List<EntidadeDominio> getListaCargos() {
		return listaCargos;
	}

	public void setListaCargos(List<EntidadeDominio> listaCargos) {
		this.listaCargos = listaCargos;
	}

	public void setListaPermissoes(List<EntidadeDominio> listaPermissoes) {
		this.listaPermissoes = listaPermissoes;
	}

	public int getTotalFuncionario() {
		return totalFuncionario;
	}

	public void setTotalFuncionario(int totalFuncionario) {
		this.totalFuncionario = totalFuncionario;
	}

	/**
	 * Carrega a listagem dos funcionarios e cidades assim que carrega a pagina
	 */
	@Override
	public void carregarListagem() {

		// escolhe o Command corretamente de acordo com a operacao
		ICommand command = mapCommands.get("LISTAR");

		Resultado resultado = new Resultado();

		resultado = command.execute(new Funcionario());
		if (resultado != null) {
			listaFuncionario = resultado.getEntidades();
		}

		resultado = command.execute(new Estado());
		if (resultado != null) {
			listaEstados = resultado.getEntidades();
		}

		resultado = command.execute(new Permissao());
		if (resultado != null) {
			listaPermissoes = resultado.getEntidades();
		}
		
		resultado = command.execute(new Cargo());
		if (resultado != null) {
			listaCargos = resultado.getEntidades();
		}

		totalFuncionario = listaFuncionario.size();

	}

	@Override
	public void executar() {

		// recebe a operacao a ser realizada
		String operacao = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("param");

		// escolhe o Command corretamente de acordo com a operacao
		ICommand command = mapCommands.get(operacao);
		Resultado resultado = new Resultado();

		resultado = command.execute(funcionario);
		if (resultado != null) {
			funcionario = (Funcionario) resultado.getEntidades().get(0);
		} else {
			
			setEhCadastrado(false);
			setJaPesquisei(true);
			funcionario.setNome("");
			
			return;
		}
		
		limparObjetos();
		
	}

	/**
	 * Pesquisa um funcionario no BD de acordo com o ID digitado pelo Usuário na
	 * Tela
	 */
	public void pesquisarPorID() {
		setEhCadastrado(false);
		setJaPesquisei(true);

		// Retorna um estado completo de acordo com um ID
		ICommand command = mapCommands.get("CONSULTAR");

		Resultado resultado = new Resultado();
		resultado = command.execute(funcionario);

		// Cliente foi encontrado
		if (resultado.getEntidades().get(0) != null) {
			funcionario = (Funcionario) resultado.getEntidades().get(0);
			setEhCadastrado(true);
			setJaPesquisei(false);
			return;
		} else {
			funcionario = new Funcionario();
		}
	}


	/**
	 * limpa a tela de pesquisa de Funcionario Deixando pronto para uma nova
	 * Pesquisa
	 */
	public void limparObjetos() {
		funcionario = new Funcionario();
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
		resultado = command.execute(funcionario.getEndereco().getCidade().getEstado());
		if (resultado != null) {
			funcionario.getEndereco().getCidade().setEstado((Estado) resultado.getEntidades().get(0));
		}
		
		// Lista todas as cidades de um ESTADO
		command = mapCommands.get("LISTAR");
		resultado = new Resultado();
		resultado = command.execute(funcionario.getEndereco().getCidade());

		if (resultado != null) {
			listaCidades = resultado.getEntidades();
		}
		

	}

}
