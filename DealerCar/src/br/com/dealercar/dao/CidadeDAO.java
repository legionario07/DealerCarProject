package br.com.dealercar.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.domain.Cidade;
import br.com.dealercar.factory.Conexao;

public class CidadeDAO {

	public void cadastrar(Cidade cidade) {

		String sql = "insert into cidades (nome, uf)  values (?,?)";

		Connection con = Conexao.getConnection();

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, cidade.getNome());
			ps.setString(2, cidade.getUf());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void excluir(Cidade cidade) {
		String sql = "delete from cidades where id=?";

		Connection con = Conexao.getConnection();

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, cidade.getId());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void editar(Cidade cidade) {

		String sql = "update cidades set nome = ?, uf = ? where id=?";

		Connection con = Conexao.getConnection();

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, cidade.getNome());
			ps.setString(2, cidade.getUf());
			ps.setInt(3, cidade.getId());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public Cidade pequisarPorId(Cidade cidade) {

		String sql = "select * from cidades where id = ? ";
		Cidade cidadeRetorno = null;
		Connection con = Conexao.getConnection();

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, cidade.getId());

			ResultSet rSet = ps.executeQuery();

			while (rSet.next()) {
				cidadeRetorno = new Cidade();
				cidadeRetorno.setId(rSet.getInt("id"));
				cidadeRetorno.setNome(rSet.getString("nome"));
				cidadeRetorno.setUf(rSet.getString("uf"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cidadeRetorno;

	}

	public List<Cidade> pesquisarPorNome(Cidade cidade) {

		String sql = "select distinct * from cidades where nome like ? order by nome asc";
		List<Cidade> cidades = new ArrayList<Cidade>();
		
		Connection con = Conexao.getConnection();

		try {

			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, "%" + cidade.getNome() + "%");

			ResultSet rSet = ps.executeQuery();

			while (rSet.next()) {
				Cidade cidadeRetorno = new Cidade();

				cidadeRetorno.setId(rSet.getInt("id"));
				cidadeRetorno.setNome(rSet.getString("nome"));
				cidadeRetorno.setUf(rSet.getString("uf"));

				cidades.add(cidadeRetorno);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cidades;
	}

	public List<Cidade> listarTodos() {

		String sql = "select * from cidades";
		List<Cidade> cidades = new ArrayList<Cidade>();

		Connection con = Conexao.getConnection();

		try {

			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rSet = ps.executeQuery();

			while (rSet.next()) {
				Cidade cidadeRetorno = new Cidade();
				cidadeRetorno.setId(rSet.getInt("id"));
				cidadeRetorno.setNome(rSet.getString("nome"));
				cidadeRetorno.setUf(rSet.getString("uf"));

				cidades.add(cidadeRetorno);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cidades;

	}

}
