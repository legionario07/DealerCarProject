package br.com.dealercar.domain;

import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.dao.ClienteDAO;

public class Cliente extends Pessoa {

	private String nomeMae;
	private String RG;
	private String CPF;
	private String email;

	public Cliente() {
		

	}

	public Cliente(String nome, String dataNasc, String nomeMae, String telefone, String RG, String CPF, String email,
			String endereco, Cidade cidade) {
		this.setNome(nome);
		this.setDataNasc(dataNasc);
		this.setNomeMae(nomeMae);
		this.setTelefone(telefone);
		this.setRG(RG);
		this.setCPF(CPF);
		this.setEmail(email);
		this.setEndereco(endereco);
		this.setCidade(cidade);
	}
	
	public Cliente(String nome, String dataNasc, String telefone, String RG, String CPF, String endereco,
			Cidade cidade) {
		this.setNome(nome);
		this.setDataNasc(dataNasc);
		this.setTelefone(telefone);
		this.setRG(RG);
		this.setCPF(CPF);
		this.setEndereco(endereco);
		this.setCidade(cidade);
	}

	public String getNomeMae() {
		return nomeMae;
	}

	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
	}

	public String getRG() {
		return RG;
	}

	public void setRG(String rG) {
		RG = rG;
	}

	public String getCPF() {
		return CPF;
	}

	public void setCPF(String cPF) {
		CPF = cPF;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	//Método que cadastra o Cliente e sua respectiva cidade no Banco de Dados
	public void cadastrar(Cliente cliente, Cidade cidade) {
		ClienteDAO cliDao = new ClienteDAO();
		cliDao.cadastrar(cliente, cidade);
	}
	
	//Método que edita o Cliente e sua respectiva cidade no Banco de Dados
	public void editar(Cliente cliente, Cidade cidade) {
		ClienteDAO cliDao =  new ClienteDAO();
		cliDao.editar(cliente, cidade);
	}
	
	//Método que exlui o cliente no Banco de Dados
	public void excluir(Cliente cliente) {
		ClienteDAO cliDao =  new ClienteDAO();
		cliDao.excluir(cliente);
	}
	
	//Método que retorna uma Lista de Todos os Clientes do Banco de Dados
	public List<Cliente> listarTodos(){
		List<Cliente> listaRetorno = new ArrayList<Cliente>();
		ClienteDAO cliDao =  new ClienteDAO();
		listaRetorno=cliDao.listarTodos();
		
		return listaRetorno;
	}

	//Método que retorna um Cliente de acordo com seu Id
	public Cliente pesquisarPorID(Cliente cliente) {
		Cliente clienteRetorno = new Cliente();
		ClienteDAO cliDao = new ClienteDAO();
		clienteRetorno = cliDao.pesquisarPorID(cliente);
		
		return clienteRetorno;
	}
	
	//Método que retorna um Cliente pesquisando por seu nome
	public List<Cliente> pesquisarPorNome(Cliente cliente) {
		List<Cliente> listaRetorno = new ArrayList<Cliente>();
		ClienteDAO cliDao =  new ClienteDAO();
		listaRetorno=cliDao.pesquisarPorNome(cliente);
		
		return listaRetorno;
	}
	
	
	
	@Override
	public String toString() {
		String retorno;
		retorno="Id: " + this.getId() + " - "
				+ "Nome: " + this.getNome() + " - "
				+ "Data de Nascimento: " + this.getDataNasc() + " - "
				+ "Nome da Mãe: " + this.getNomeMae() + " - "
				+ "Telefone: " + this.getTelefone() + " - "
				+ "Rg: " + this.getRG() + " - "
				+ "Cpf: " + this.getCPF() + " - "
				+ "Email: " + this.getEmail() + " - "
				+ "Endereço: " + this.getEndereco() + " - "
				+ "Cidade: " + this.getCidade().getNome() + "\n";
		
		return retorno;
	}

}
