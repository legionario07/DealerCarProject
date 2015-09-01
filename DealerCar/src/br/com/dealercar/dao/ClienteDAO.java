package br.com.dealercar.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.domain.Cidade;
import br.com.dealercar.domain.Cliente;
import br.com.dealercar.factory.Conexao;
import br.com.dealercar.util.JSFUtil;

public class ClienteDAO {

	public void cadastrar(Cliente cliente, Cidade cidade) {

		StringBuffer sql = new StringBuffer();
		sql.append("insert into clientes ");
		sql.append("nome, data_nasc, nome_mae, sexo, telefone, celular, rg, cpf, email, ");
		sql.append("endereco, id_cidade) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

		Connection con = Conexao.getConnection();

		try {

			PreparedStatement ps = con.prepareStatement(sql.toString());
			ps.setString(1, cliente.getNome().toUpperCase());

			// Alterando o formato de armazenamento da data para o Banco de
			// Dados Aceitar
			String[] dNasc = cliente.getDataNasc().split("-|/");
			String dia = dNasc[0];
			String mes = dNasc[1];
			String ano = dNasc[2];
			cliente.setDataNasc(ano + "-" + mes + "-" + dia);

			ps.setString(2, cliente.getDataNasc().toUpperCase());
			ps.setString(3, cliente.getNomeMae().toUpperCase());
			ps.setString(4, cliente.getSexo().toUpperCase());
			ps.setString(5, cliente.getTelefone().toUpperCase());
			ps.setString(6, cliente.getCelular().toUpperCase());
			ps.setString(7, cliente.getRG().toUpperCase());
			ps.setString(8, cliente.getCPF().toUpperCase());
			ps.setString(9, cliente.getEmail().toUpperCase());
			ps.setString(10, cliente.getEndereco().toUpperCase());
			ps.setInt(11, cidade.getId());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}

	public void editar(Cliente cliente, Cidade cidade) {

		StringBuffer sql = new StringBuffer();
		sql.append("update clientes set nome = ?, data_nasc = ?, nome_mae = ?, sexo = ?, ");
		sql.append("telefone = ?, celular= ?, rg = ?, cpf = ?, email=?, endereco = ?, ");
		sql.append("id_cidade = ? where id=?");
		
		Connection con = Conexao.getConnection();

		try {

			PreparedStatement ps = con.prepareStatement(sql.toString());

			ps.setString(1, cliente.getNome().toUpperCase());

			// Alterando o formato de armazenamento da data para o Banco de
			// Dados Aceitar
			String[] dNasc = cliente.getDataNasc().split("-|/");
			String dia = dNasc[0];
			String mes = dNasc[1];
			String ano = dNasc[2];
			cliente.setDataNasc(ano + "-" + mes + "-" + dia);

			ps.setString(2, cliente.getDataNasc().toUpperCase());
			ps.setString(3, cliente.getNomeMae().toUpperCase());
			ps.setString(4, cliente.getSexo().toUpperCase());
			ps.setString(5, cliente.getTelefone().toUpperCase());
			ps.setString(6, cliente.getCelular().toUpperCase());
			ps.setString(7, cliente.getRG().toUpperCase());
			ps.setString(8, cliente.getCPF().toUpperCase());
			ps.setString(9, cliente.getEmail().toUpperCase());
			ps.setString(10, cliente.getEndereco().toUpperCase());
			ps.setInt(11, cidade.getId());
			ps.setInt(12, cliente.getId());

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

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

				String[] dNasc = rSet.getString("clientes.data_nasc").split("-|/");
				String dia = dNasc[2];
				String mes = dNasc[1];
				String ano = dNasc[0];

				clienteRetorno.setDataNasc(dia + "-" + mes + "-" + ano);
				clienteRetorno.setNomeMae(rSet.getString("clientes.nome_mae"));
				clienteRetorno.setSexo(rSet.getString("clientes.sexo"));
				clienteRetorno.setTelefone(rSet.getString("clientes.telefone"));
				clienteRetorno.setCelular(rSet.getString("clientes.celular"));
				clienteRetorno.setRG(rSet.getString("clientes.rg"));
				clienteRetorno.setCPF(rSet.getString("clientes.cpf"));
				clienteRetorno.setEmail(rSet.getString("clientes.email"));
				clienteRetorno.setEndereco(rSet.getString("clientes.endereco"));
				clienteRetorno.setCidade(cidadeRetorno);

				clientes.add(clienteRetorno);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

		return clientes;

	}

	public Cliente pesquisarPorID(Cliente cliente) {

		StringBuffer sql = new StringBuffer(); 
		sql.append("select clientes.id, clientes.nome, clientes.data_nasc, ");
		sql.append("clientes.nome_mae, clientes.sexo, clientes.telefone, ");
		sql.append("clientes.celular, clientes.rg, clientes.cpf, clientes.email, ");
		sql.append("clientes.endereco, clientes.id_cidade, cidades.nome, cidades.uf ");
		sql.append("from clientes inner join cidades where clientes.id = ?");

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
				
				String[] dNasc = rSet.getString("clientes.data_nasc").split("-|/");
				String dia = dNasc[2];
				String mes = dNasc[1];
				String ano = dNasc[0];
				
				clienteRetorno.setDataNasc(dia + "-" + mes + "-" + ano);
				clienteRetorno.setNomeMae(rSet.getString("clientes.nome_mae"));
				clienteRetorno.setSexo(rSet.getString("clientes.sexo"));
				clienteRetorno.setTelefone(rSet.getString("clientes.telefone"));
				clienteRetorno.setRG(rSet.getString("clientes.rg"));
				clienteRetorno.setCPF(rSet.getString("clientes.cpf"));
				clienteRetorno.setTelefone(rSet.getString("clientes.telefone"));
				clienteRetorno.setCelular(rSet.getString("clientes.celular"));
				clienteRetorno.setEmail(rSet.getString("clientes.email"));
				clienteRetorno.setEndereco(rSet.getString("clientes.endereco"));

				Cidade cidade = new Cidade();
				cidade.setId(rSet.getInt("clientes.id_cidade"));
				cidade.setNome(rSet.getString("cidades.nome"));
				cidade.setUf(rSet.getString("cidades.uf"));

				clienteRetorno.setCidade(cidade);

			}

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

		return clienteRetorno;

	}
	
	public Cliente pesquisarPorCPF(Cliente cliente) {

		StringBuffer sql = new StringBuffer();
		sql.append("select clientes.id, clientes.nome, clientes.data_nasc, ");
		sql.append("clientes.nome_mae, clientes.sexo, clientes.telefone, clientes.celular, ");
		sql.append("clientes.rg, clientes.cpf, clientes.email, ");
		sql.append("clientes.endereco, clientes.id_cidade, cidades.nome, cidades.uf ");
		sql.append("from clientes inner join cidades where clientes.cpf = ?");

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
				
				String[] dNasc = rSet.getString("clientes.data_nasc").split("-|/");
				String dia = dNasc[2];
				String mes = dNasc[1];
				String ano = dNasc[0];
				
				clienteRetorno.setDataNasc(dia + "-" + mes + "-" + ano);
				clienteRetorno.setNomeMae(rSet.getString("clientes.nome_mae"));
				clienteRetorno.setSexo(rSet.getString("clientes.sexo"));
				clienteRetorno.setTelefone(rSet.getString("clientes.telefone"));
				clienteRetorno.setRG(rSet.getString("clientes.rg"));
				clienteRetorno.setCPF(rSet.getString("clientes.cpf"));
				clienteRetorno.setTelefone(rSet.getString("clientes.telefone"));
				clienteRetorno.setCelular(rSet.getString("clientes.celular"));
				clienteRetorno.setEmail(rSet.getString("clientes.email"));
				clienteRetorno.setEndereco(rSet.getString("clientes.endereco"));

				Cidade cidade = new Cidade();
				cidade.setId(rSet.getInt("clientes.id_cidade"));
				cidade.setNome(rSet.getString("cidades.nome"));
				cidade.setUf(rSet.getString("cidades.uf"));

				clienteRetorno.setCidade(cidade);

			}

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

		return clienteRetorno;

	}

	public List<Cliente> pesquisarPorNome(Cliente clientes) {

		StringBuffer sql = new StringBuffer();
		sql.append("select clientes.id, clientes.nome, clientes.data_nasc, ");
		sql.append("clientes.nome_mae, clientes.sexo, clientes.telefone, ");
		sql.append("clientes.celular, clientes.rg, clientes.cpf, clientes.email, ");
		sql.append("clientes.endereco, clientes.id_cidade, cidades.nome, cidades.uf, MAX(clientes.id) ");
		sql.append("from clientes inner join cidades ");
		sql.append("where clientes.nome like ? order by clientes.nome asc");

		List<Cliente> lista = new ArrayList<Cliente>();

		Connection con = Conexao.getConnection();

		try {

			PreparedStatement ps = con.prepareStatement(sql.toString());
			ps.setString(1, "%" + clientes.getNome().toUpperCase() + "%");

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
				clienteRetorno.setEndereco(rSet.getString("clientes.endereco"));

				Cidade cidade = new Cidade();
				cidade.setId(rSet.getInt("clientes.id_cidade"));
				cidade.setNome(rSet.getString("cidades.nome"));
				cidade.setUf(rSet.getString("cidades.uf"));

				clienteRetorno.setCidade(cidade);

				lista.add(clienteRetorno);

			}

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

		return lista;
	}

}
