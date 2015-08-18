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

public class ClienteDAO {

	public void cadastrar(Cliente cliente, Cidade cidade) {

		String sql = "insert into clientes " + "(nome, data_nasc, nome_mae," + " telefone, rg, " + "cpf, email, "
				+ "endereco, id_cidade) " + "values (?, ?, ?, ?, ?, ?, ?, ?, ?)";

		Connection con = Conexao.getConnection();

		try {

			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, cliente.getNome());

			// Alterando o formato de armazenamento da data para o Banco de
			// Dados Aceitar
			String[] dNasc = cliente.getDataNasc().split("-|//");
			String dia = dNasc[0];
			String mes = dNasc[1];
			String ano = dNasc[2];
			cliente.setDataNasc(ano + "-" + mes + "-" + dia);

			ps.setString(2, cliente.getDataNasc());
			ps.setString(3, cliente.getNomeMae());
			ps.setString(4, cliente.getTelefone());
			ps.setString(5, cliente.getRG());
			ps.setString(6, cliente.getCPF());
			ps.setString(7, cliente.getEmail());
			ps.setString(8, cliente.getEndereco());
			ps.setInt(9, cliente.getCidade().getId());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void editar(Cliente cliente, Cidade cidade) {

		String sql = "update clientes set nome = ?, data_nasc = ?, nome_mae = ?, "
				+ "telefone = ?, rg = ?, cpf = ?, email=?, endereco = ?, id_cidade = ? where id=?";
		Connection con = Conexao.getConnection();

		try {

			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, cliente.getNome());

			// Alterando o formato de armazenamento da data para o Banco de
			// Dados Aceitar
			String[] dNasc = cliente.getDataNasc().split("-|//");
			String dia = dNasc[0];
			String mes = dNasc[1];
			String ano = dNasc[2];
			cliente.setDataNasc(ano + "-" + mes + "-" + dia);

			ps.setString(2, cliente.getDataNasc());
			ps.setString(3, cliente.getNomeMae());
			ps.setString(4, cliente.getTelefone());
			ps.setString(5, cliente.getRG());
			ps.setString(6, cliente.getCPF());
			ps.setString(7, cliente.getEmail());
			ps.setString(8, cliente.getEndereco());
			ps.setInt(9, cidade.getId());
			ps.setInt(10, cliente.getId());

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
		}

	}

	public List<Cliente> listarTodos() {

		String sql = "select clientes.id, clientes.nome, clientes.data_nasc, "
				+ "clientes.nome_mae, clientes.telefone, " + "clientes.rg, clientes.cpf, clientes.email, "
				+ "clientes.endereco, cidades.id, cidades.nome, cidades.uf " 
				+ "from clientes inner join cidades"
				+ " where cidades.id = clientes.id_cidade";

		List<Cliente> clientes = new ArrayList<Cliente>();

		Connection con = Conexao.getConnection();

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rSet = ps.executeQuery();

			while (rSet.next()) {

				Cidade cidadeRetorno = new Cidade();
				cidadeRetorno.setId(rSet.getInt("cidades.id"));
				cidadeRetorno.setNome(rSet.getString("cidades.nome"));

				Cliente clienteRetorno = new Cliente();

				clienteRetorno.setId(rSet.getInt("clientes.id"));
				clienteRetorno.setNome(rSet.getString("clientes.nome"));
				
				String[] dNasc = rSet.getString("clientes.data_nasc").split("-|//");
				String dia = dNasc[2];
				String mes = dNasc[1];
				String ano = dNasc[0];
						
				clienteRetorno.setDataNasc(dia + "-" + mes + "-" + ano);
				clienteRetorno.setNomeMae(rSet.getString("clientes.nome_mae"));
				clienteRetorno.setTelefone(rSet.getString("clientes.telefone"));
				clienteRetorno.setRG(rSet.getString("clientes.rg"));
				clienteRetorno.setCPF(rSet.getString("clientes.cpf"));
				clienteRetorno.setEmail(rSet.getString("clientes.email"));
				clienteRetorno.setEndereco(rSet.getString("clientes.endereco"));
				clienteRetorno.setCidade(cidadeRetorno);

				clientes.add(clienteRetorno);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return clientes;

	}

	public Cliente pesquisarPorID(Cliente cliente) {
		
		String sql = "select clientes.id, clientes.nome, clientes.data_nasc, "
				+ "clientes.nome_mae, clientes.telefone, " 
				+ "clientes.rg, clientes.cpf, clientes.email, "
				+ "clientes.endereco, clientes.id_cidade, cidades.nome, cidades.uf "
				+ "from clientes inner join cidades"
				+ " where clientes.id = ?";
		
		Cliente clienteRetorno = null;
		
		Connection con = Conexao.getConnection();

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, cliente.getId());

			ResultSet rSet = ps.executeQuery();

			while (rSet.next()) {
				clienteRetorno = new Cliente();
				
				clienteRetorno.setId(rSet.getInt("clientes.id"));
				clienteRetorno.setNome(rSet.getString("clientes.nome"));
				clienteRetorno.setDataNasc(rSet.getString("clientes.data_nasc"));
				clienteRetorno.setNomeMae(rSet.getString("clientes.nome_mae"));
				clienteRetorno.setTelefone(rSet.getString("clientes.telefone"));
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
				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return clienteRetorno;

	}
	
	public List<Cliente> pesquisarPorNome(Cliente clientes ) {
		
		String sql = "select clientes.id, clientes.nome, clientes.data_nasc, "
				+ "clientes.nome_mae, clientes.telefone, " 
				+ "clientes.rg, clientes.cpf, clientes.email, "
				+ "clientes.endereco, clientes.id_cidade, cidades.nome, cidades.uf, MAX(clientes.id) "
				+ "from clientes inner join cidades"
				+ " where clientes.nome like ? order by clientes.nome asc";
		
		List<Cliente> lista = new ArrayList<Cliente>();
		
		Connection con =  Conexao.getConnection();
		
		try {
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, "%" + clientes.getNome() + "%");
			
			ResultSet rSet = ps.executeQuery();
			
			while(rSet.next()) {
				
				Cliente clienteRetorno = new Cliente();
				
				clienteRetorno.setId(rSet.getInt("clientes.id"));
				clienteRetorno.setNome(rSet.getString("clientes.nome"));
				clienteRetorno.setDataNasc(rSet.getString("clientes.data_nasc"));
				clienteRetorno.setNomeMae(rSet.getString("clientes.nome_mae"));
				clienteRetorno.setTelefone(rSet.getString("clientes.telefone"));
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
		}
		
		return lista;
	}

}
