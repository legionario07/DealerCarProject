package br.com.dealercar.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;

import br.com.dealercar.autenticacao.Permissao;
import br.com.dealercar.dao.CidadeDAO;
import br.com.dealercar.dao.FuncionarioDAO;
import br.com.dealercar.dao.PermissaoDAO;
import br.com.dealercar.dao.UsuarioDAO;
import br.com.dealercar.domain.Cidade;
import br.com.dealercar.domain.Funcionario;
import br.com.dealercar.util.JSFUtil;

@javax.faces.bean.ManagedBean(name = "MBFuncionario")
@ViewScoped
public class FuncionarioBean extends AbstractBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Funcionario funcionario = new Funcionario();
	private List<Funcionario> listaFuncionario = new ArrayList<Funcionario>();
	private List<Cidade> listaCidades = new ArrayList<Cidade>();
	private List<Permissao> listaPermissoes = new ArrayList<Permissao>();
	private int totalFuncionario;
	
	
	public Funcionario getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	public List<Funcionario> getListaFuncionario() {
		return listaFuncionario;
	}
	public void setListaFuncionario(List<Funcionario> listaFuncionario) {
		this.listaFuncionario = listaFuncionario;
	}
	public List<Cidade> getListaCidades() {
		return listaCidades;
	}
	public void setListaCidades(List<Cidade> listaCidades) {
		this.listaCidades = listaCidades;
	}
	public List<Permissao> getListaPermissoes() {
		return listaPermissoes;
	}
	public void setListaPermissoes(List<Permissao> listaPermissoes) {
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
	public void carregarListagem(){
		
		listaFuncionario = new FuncionarioDAO().listarTodos();
		listaCidades     = new CidadeDAO().listarTodos();
		listaPermissoes  = new PermissaoDAO().listarTodos();
		totalFuncionario = listaFuncionario.size();
		
	}
	
	/**
	 * Pesquisa um funcionario no BD de acordo com o ID digitado pelo Usuário na
	 * Tela
	 */
	public void pesquisarPorID() {
		this.setEhCadastrado(false);
		this.setJaPesquisei(true);

		for (Funcionario f : listaFuncionario) {
			if (funcionario.getId() == f.getId()) {
				this.setEhCadastrado(true);
				this.setJaPesquisei(false);
				break;
			}
		}

		if (isEhCadastrado()== false) {
			funcionario = new Funcionario();
			JSFUtil.adicionarMensagemNaoLocalizado("Funcionário Não encontrado.");
			return;
		}
		funcionario = new FuncionarioDAO().pesquisarPorID(funcionario);
	}

	
	/**
	 * Cadastra um novo Funcionario passando como parametro os dados do
	 * Funcionario e da Cidade que o usuário digitou na Tela
	 */
	public void cadastrar() {

		// Verifica a cidade escolhida para ser adicionado ao Funcionario que esta
		// sendo editado
		for (Cidade cid : listaCidades) {
			if (cid.getNome().equals(funcionario.getEndereco().getCidade().getNome())) {
				funcionario.getEndereco().setCidade(cid);
				break;
			}
		}
		
		// Verifica a permissão escolhida para ser adicionado ao Funcionario que esta
		// sendo cadastrado
		for (Permissao p : listaPermissoes) {
			if (p.getNivel().equals(funcionario.getUsuario().getPermissao().getNivel())) {
				funcionario.getUsuario().setPermissao(p);
				break;
			}
		}
		
		//cadastrando primeiro o usuario ao banco de dados
		new UsuarioDAO().cadastrar(funcionario.getUsuario());
		
		//pesquisa o usuario cadastrado e seta todos os dados no Funcionario
		funcionario.setUsuario(new UsuarioDAO().pesquisarPorLogin(funcionario.getUsuario()));
		
		new FuncionarioDAO().cadastrar(funcionario);

		JSFUtil.adicionarMensagemSucesso("Funcionário Cadastrado com Sucesso.");

		funcionario = new Funcionario();
	}
	
	
	/**
	 * Edita o Funcionario selecionado na tela
	 */
	public void editar() {


		// Verifica a cidade escolhida para ser adicionado ao Funcionario que esta
		// sendo editado
		for (Cidade cid : listaCidades) {
			if (cid.getNome().equals(funcionario.getEndereco().getCidade().getNome())) {
				funcionario.getEndereco().setCidade(cid);
				break;
			}

		}
		
		// Verifica a permissão escolhida para ser adicionado ao Funcionario que esta
		// sendo editado
		for (Permissao p : listaPermissoes) {
			if (p.getNivel().equals(funcionario.getUsuario().getPermissao().getNivel())) {
				funcionario.getUsuario().setPermissao(p);
				break;
			}
		}

		new FuncionarioDAO().editar(funcionario);
		
		JSFUtil.adicionarMensagemSucesso("Funcionario Editado com Sucesso.");
	}

	
	
	/**
	 * limpa a tela de pesquisa de Funcionario Deixando pronto para uma
	 * nova Pesquisa
	 */
	public void limparPesquisa() {
		funcionario = new Funcionario();
		setEhCadastrado(false);
	}
	
}
