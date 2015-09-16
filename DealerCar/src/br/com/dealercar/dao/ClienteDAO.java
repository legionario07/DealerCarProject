package br.com.dealercar.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.domain.Cidade;
import br.com.dealercar.domain.Cliente;
import br.com.dealercar.domain.Endereco;
import br.com.dealercar.factory.Conexao;
import br.com.dealercar.util.JSFUtil;


public class ClienteDAO extends AbstractPesquisaDAO<Cliente> {

	/**
	 * 
	 * @param cliente Recebe um cliente  e cadastra no Banco de Dados
	 * @param 
	 */
	public void cadastrar(Cliente cliente) {

		StringBuffer sql = new StringBuffer();
		sql.append("insert into clientes ");
		sql.append("(nome, data_nasc, nome_mae, sexo, telefone, celular, rg, cpf, email, ");
		sql.append("endereco, id_cidade) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

		Connection con = Conexao.getConnection();

		try {

			PreparedStatement ps = con.prepareStatement(sql.toString());
			int i = 0;
			ps.setString(++i, cliente.getNome().toUpperCase());
			ps.setString(++i, cliente.getDataNasc());
			ps.setString(++i, cliente.getNomeMae().toUpperCase());
			ps.setString(++i, cliente.getSexo().toUpperCase());
			ps.setString(++i, cliente.getTelefone());
			ps.setString(++i, cliente.getCelular());
			ps.setString(++i, cliente.getRG().toUpperCase());
			ps.setString(++i, cliente.getCPF());
			ps.setString(++i, cliente.getEmail().toUpperCase());

			StringBuffer endereco = new StringBuffer();
			
			//Concatenando o endereço para add no BD
			endereco.append(cliente.getEndereco().getRua() + ", ");
			endereco.append(cliente.getEndereco().getNumero() + ", ");
			if(cliente.getEndereco().getComplemento() != null){
				endereco.append(cliente.getEndereco().getComplemento() + ", ");
			}
			endereco.append(cliente.getEndereco().getBairro());
			
			ps.setString(++i, endereco.toString());
			ps.setInt(++i, cliente.getCidade().getId());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}

	/**
	 * 
	 * @param cliente Recebe um Ciente e  edita seus dados do Banco de Dados
	 * @param cidade
	 */
	public void editar(Cliente cliente) {

		StringBuffer sql = new StringBuffer();
		sql.append("update clientes set nome = ?, data_nasc = ?, nome_mae = ?, sexo = ?, ");
		sql.append("telefone = ?, celular= ?, rg = ?, cpf = ?, email=?, endereco = ?, ");
		sql.append("id_cidade = ? where id=?");
		
		Connection con = Conexao.getConnection();

		try {

			PreparedStatement ps = con.prepareStatement(sql.toString());

			int i = 0;
			
			ps.setString(++i, cliente.getNome().toUpperCase());
			ps.setString(++i, cliente.getDataNasc());
			ps.setString(++i, cliente.getNomeMae().toUpperCase());
			ps.setString(++i, cliente.getSexo().toUpperCase());
			ps.setString(++i, cliente.getTelefone());
			ps.setString(++i, cliente.getCelular());
			ps.setString(++i, cliente.getRG().toUpperCase());
			ps.setString(++i, cliente.getCPF());
			ps.setString(++i, cliente.getEmail().toUpperCase());
			
			StringBuffer endereco = new StringBuffer();
			
			//Concatenando o endereço para add no BD
			endereco.append(cliente.getEndereco().getRua() + ", ");
			endereco.append(cliente.getEndereco().getNumero() + ", ");
			if(cliente.getEndereco().getComplemento() != null){
				endereco.append(cliente.getEndereco().getComplemento() + ", ");
			}
			endereco.append(cliente.getEndereco().getBairro());
			
			ps.setString(++i, endereco.toString());
			
			ps.setInt(++i, cliente.getCidade().getId());
			ps.setInt(++i, cliente.getId());

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param cliente Recebe um Cliente e exclui do Banco de dados
	 *  localizando pelo seu Id
	 */
	public void excluir(Cliente cliente) {

		String sql = "delete from clientes where id = ?";

		Connection con = Conexao.getConnection();

		try {

			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, cliente.getId());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

	}

	/**
	 * 
	 * @return Retorna todos os clientes do Banco de dados em forma de List<CLiente>
	 */
	public List<Cliente> listarTodos() {

		StringBuffer sql = new StringBuffer(); 
		sql.append("select clientes.id, clientes.nome, clientes.data_nasc, ");
		sql.append("clientes.nome_mae, clientes.sexo, clientes.telefone, ");
		sql.append("clientes.celular, clientes.rg, clientes.cpf, clientes.email, ");
		sql.append("clientes.endereco, cidades.id, cidades.nome, cidades.uf ");
		sql.append("from clientes inner join cidades ");
		sql.append("where cidades.id = clientes.id_cidade order by clientes.id asc");

		List<Cliente> clientes = new ArrayList<Cliente>();

		Connection con = Conexao.getConnection();

		try {
			PreparedStatement ps = con.prepareStatement(sql.toString());
			ResultSet rSet = ps.executeQuery();

			while (rSet.next()) {

				Cidade cidadeRetorno = new Cidade();
				cidadeRetorno.setId(rSet.getInt("cidades.id"));
				cidadeRetorno.setNome(rSet.getString("cidades.nome"));

				Cliente clienteRetorno = new Cliente();

				clienteRetorno.setId(rSet.getInt("clientes.id"));
				clienteRetorno.setNome(rSet.getString("clientes.nome"));
				clienteRetorno.setDataNasc(rSet.getString("clientes.data_nasc"));
				clienteRetorno.setNomeMae(rSet.getString("clientes.nome_mae"));
				clienteRetorno.setSexo(rSet.getString("clientes.sexo"));
				clienteRetorno.setTelefone(rSet.getString("clientes.telefone"));
				clienteRetorno.setCelular(rSet.getString("clientes.celular"));
				clienteRetorno.setRG(rSet.getString("clientes.rg"));
				clienteRetorno.setCPF(rSet.getString("clientes.cpf"));
				clienteRetorno.setEmail(rSet.getString("clientes.email"));
				
				Endereco end = new Endereco();
				/**
				 * Alterando o formato de armazenamento da endereço para o Banco de
				 * Dados Aceitar
				 * Quando encontra a virgula a String é separada.
				 * Assim A rua, O numero, o complemento é armezanado adequadamente
				 */
				String[] endereco = rSet.getString("clientes.endereco").split(",");
				
					if(endereco.length == 4) {
						end.setRua(endereco[0].trim());
						end.setNumero(endereco[1].trim());
						end.setComplemento(endereco[2].trim());
						end.setBairro(" - " + endereco[3].trim());
					} else {
						end.setRua(endereco[0].trim());
						end.setNumero(endereco[1].trim());
						end.setBairro(" - " + endereco[2].trim());
					}
				
				clienteRetorno.setEndereco(end);				
				
				clienteRetorno.setCidade(cidadeRetorno);

				clientes.add(clienteRetorno);
			}
			
			rSet.close();

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

		return clientes;

	}

	/**
	 * 
	 * @param cliente Recebe um cliente e localiza pelo Id no Banco de Dados
	 * @return Retorna um Objeto de Cliente
	 */
	public Cliente pesquisarPorID(Cliente cliente) {

		StringBuffer sql = new StringBuffer(); 
		sql.append("select clientes.id, clientes.nome, clientes.data_nasc, ");
		sql.append("clientes.nome_mae, clientes.sexo, clientes.telefone, ");
		sql.append("clientes.celular, clientes.rg, clientes.cpf, clientes.email, ");
		sql.append("clientes.endereco, clientes.id_cidade, cidades.nome, cidades.uf ");
		sql.append("from clientes inner join cidades on clientes.id_cidade = cidades.id where clientes.id = ?");

		Cliente clienteRetorno = null;

		Connection con = Conexao.getConnection();

		try {
			PreparedStatement ps = con.prepareStatement(sql.toString());
			ps.setInt(1, cliente.getId());

			ResultSet rSet = ps.executeQuery();

			while (rSet.next()) {
				clienteRetorno = new Cliente();

				clienteRetorno.setId(rSet.getInt("clientes.id"));
				clienteRetorno.setNome(rSet.getString("clientes.nome"));
				clienteRetorno.setDataNasc(rSet.getString("clientes.data_nasc"));
				clienteRetorno.setNomeMae(rSet.getString("clientes.nome_mae"));
				clienteRetorno.setSexo(rSet.getString("clientes.sexo"));
				clienteRetorno.setTelefone(rSet.getString("clientes.telefone"));
				clienteRetorno.setRG(rSet.getString("clientes.rg"));
				clienteRetorno.setCPF(rSet.getString("clientes.cpf"));
				clienteRetorno.setTelefone(rSet.getString("clientes.telefone"));
				clienteRetorno.setCelular(rSet.getString("clientes.celular"));
				clienteRetorno.setEmail(rSet.getString("clientes.email"));

				Endereco end = new Endereco();
				/**
				 * Alterando o formato de armazenamento da endereço para o Banco de
				 * Dados Aceitar
				 * Quando encontra a virgula a String é separada.
				 * Assim A rua, O numero, o complemento é armezanado adequadamente
				 */
				String[] endereco = rSet.getString("clientes.endereco").split(",");
				
					if(endereco.length == 4) {
						end.setRua(endereco[0].trim());
						end.setNumero(endereco[1].trim());
						end.setComplemento(endereco[2].trim());
						end.setBairro(endereco[3].trim());
					} else {
						end.setRua(endereco[0].trim());
						end.setNumero(endereco[1].trim());
						end.setBairro(endereco[2].trim());
					}
				
				clienteRetorno.setEndereco(end);				
				
				Cidade cidade = new Cidade();
				cidade.setId(rSet.getInt("clientes.id_cidade"));
				cidade.setNome(rSet.getString("cidades.nome"));
				cidade.setUf(rSet.getString("cidades.uf"));

				clienteRetorno.setCidade(cidade);

			}
			
			rSet.close();

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

		return clienteRetorno;

	}
	
	/**
	 * 
	 * @param cliente Recebe um objeto de Cliente e localiza no Banco de Dadoos
	 *  pelo seu CPF
	 * @return Retorna um Objeto Cliente
	 */
	public Cliente pesquisarPorCPF(Cliente cliente) {

		StringBuffer sql = new StringBuffer();
		sql.append("select clientes.id, clientes.nome, clientes.data_nasc, ");
		sql.append("clientes.nome_mae, clientes.sexo, clientes.telefone, clientes.celular, ");
		sql.append("clientes.rg, clientes.cpf, clientes.email, ");
		sql.append("clientes.endereco, clientes.id_cidade, cidades.nome, cidades.uf ");
		sql.append("from clientes inner join cidades on clientes.id_cidade = cidades.id where clientes.cpf = ?");

		Cliente clienteRetorno = null;

		Connection con = Conexao.getConnection();

		try {
			PreparedStatement ps = con.prepareStatement(sql.toString());
			ps.setString(1, cliente.getCPF());

			ResultSet rSet = ps.executeQuery();

			while (rSet.next()) {
				clienteRetorno = new Cliente();

				clienteRetorno.setId(rSet.getInt("clientes.id"));
				clienteRetorno.setNome(rSet.getString("clientes.nome"));
				clienteRetorno.setDataNasc(rSet.getString("clientes.data_nasc"));
				clienteRetorno.setNomeMae(rSet.getString("clientes.nome_mae"));
				clienteRetorno.setSexo(rSet.getString("clientes.sexo"));
				clienteRetorno.setTelefone(rSet.getString("clientes.telefone"));
				clienteRetorno.setRG(rSet.getString("clientes.rg"));
				clienteRetorno.setCPF(rSet.getString("clientes.cpf"));
				clienteRetorno.setTelefone(rSet.getString("clientes.telefone"));
				clienteRetorno.setCelular(rSet.getString("clientes.celular"));
				clienteRetorno.setEmail(rSet.getString("clientes.email"));

				Endereco end = new Endereco();

				/**
				 * Alterando o formato de armazenamento da endereço para o Banco de
				 * Dados Aceitar
				 * Quando encontra a virgula a String é separada.
				 * Assim A rua, O numero, o complemento é armezanado adequadamente
				 */
				String[] endereco = rSet.getString("clientes.endereco").split(",");
				
					if(endereco.length == 4) {
						end.setRua(endereco[0].trim());
						end.setNumero(endereco[1].trim());
						end.setComplemento(endereco[2].trim());
						end.setBairro(endereco[3].trim());
					} else {
						end.setRua(endereco[0].trim());
						end.setNumero(endereco[1].trim());
						end.setBairro(endereco[2].trim());
					}
				
				clienteRetorno.setEndereco(end);				

				Cidade cidade = new Cidade();
				cidade.setId(rSet.getInt("clientes.id_cidade"));
				cidade.setNome(rSet.getString("cidades.nome"));
				cidade.setUf(rSet.getString("cidades.uf"));

				clienteRetorno.setCidade(cidade);

			}
			
			rSet.close();

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

		return clienteRetorno;

	}

	/**
	 * 
	 * @param clientes Recebe um objeto do Tipo Cliente e localiza no Banco pelo nome
	 * @return Retorna um Objeto do tipo Cliente
	 */
	public List<Cliente> pesquisarPorNome(Cliente cliente) {

		StringBuffer sql = new StringBuffer();
		sql.append("select distinct clientes.id, clientes.nome, clientes.data_nasc, ");
		sql.append("clientes.nome_mae, clientes.sexo, clientes.telefone, ");
		sql.append("clientes.celular, clientes.rg, clientes.cpf, clientes.email, ");
		sql.append("clientes.endereco, clientes.id_cidade, cidades.nome, cidades.uf ");
		sql.append("from clientes inner join cidades on clientes.id_cidade = cidades.id ");
		sql.append("where clientes.nome like ? order by clientes.nome asc");

		List<Cliente> lista = new ArrayList<Cliente>();

		Connection con = Conexao.getConnection();

		try {

			PreparedStatement ps = con.prepareStatement(sql.toString());
			ps.setString(1, "%" + cliente.getNome().toUpperCase() + "%");

			ResultSet rSet = ps.executeQuery();

			while (rSet.next()) {

				Cliente clienteRetorno = new Cliente();

				clienteRetorno.setId(rSet.getInt("clientes.id"));
				clienteRetorno.setNome(rSet.getString("clientes.nome"));
				clienteRetorno.setDataNasc(rSet.getString("clientes.data_nasc"));
				clienteRetorno.setNomeMae(rSet.getString("clientes.nome_mae"));
				clienteRetorno.setSexo(rSet.getString("clientes.sexo"));
				clienteRetorno.setTelefone(rSet.getString("clientes.telefone"));
				clienteRetorno.setCelular(rSet.getString("clientes.celular"));
				clienteRetorno.setRG(rSet.getString("clientes.rg"));
				clienteRetorno.setCPF(rSet.getString("clientes.cpf"));
				clienteRetorno.setTelefone(rSet.getString("clientes.telefone"));
				clienteRetorno.setEmail(rSet.getString("clientes.email"));

				Endereco end = new Endereco();
				/**
				 * Alterando o formato de armazenamento da endereço para o Banco de
				 * Dados Aceitar
				 * Quando encontra a virgula a String é separada.
				 * Assim A rua, O numero, o complemento é armezanado adequadamente
				 */
				String[] endereco = rSet.getString("clientes.endereco").split(",");
				
					if(endereco.length == 4) {
						end.setRua(endereco[0].trim());
						end.setNumero(endereco[1].trim());
						end.setComplemento(endereco[2].trim());
						end.setBairro(endereco[3].trim());
					} else {
						end.setRua(endereco[0].trim());
						end.setNumero(endereco[1].trim());
						end.setBairro(endereco[2].trim());
					}
				
				clienteRetorno.setEndereco(end);				

				
				Cidade cidade = new Cidade();
				cidade.setId(rSet.getInt("clientes.id_cidade"));
				cidade.setNome(rSet.getString("cidades.nome"));
				cidade.setUf(rSet.getString("cidades.uf"));

				clienteRetorno.setCidade(cidade);

				lista.add(clienteRetorno);

			}
			
			rSet.close();

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

		return lista;
	}

}
