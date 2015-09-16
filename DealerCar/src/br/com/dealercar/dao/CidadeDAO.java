package br.com.dealercar.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.domain.Cidade;
import br.com.dealercar.factory.Conexao;
import br.com.dealercar.util.JSFUtil;

public class CidadeDAO extends AbstractPesquisaDAO<Cidade> {

	/** 
	 * 
	 * @param cidade Recebe uma Cidade e cadastra no Banco de Dados
	 */
	@Override
	public void cadastrar(Cidade cidade) {

		String sql = "insert into cidades (nome, uf)  values (?,?)";

		Connection con = Conexao.getConnection();

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, cidade.getNome().toUpperCase());
			ps.setString(2, cidade.getUf().toUpperCase());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

	}

	/**
	 * 
	 * @param cidade Recebe uma cidade e exclui do Banco de Dados
	 * 	pesquisando pelo seu Id
	 */
	@Override
	public void excluir(Cidade cidade) {
		String sql = "delete from cidades where id=?";

		Connection con = Conexao.getConnection();

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, cidade.getId());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

	}

	/**
	 * 
	 * @param cidade Recebe uma Cidade e edita seus dados no Banco de DAdos
	 * 	pesquisando por seu Id
	 */
	@Override
	public void editar(Cidade cidade) {

		String sql = "update cidades set nome = ?, uf = ? where id=?";

		Connection con = Conexao.getConnection();

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, cidade.getNome().toUpperCase());
			ps.setString(2, cidade.getUf().toUpperCase());
			ps.setInt(3, cidade.getId());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

	}

	/**
	 * 
	 * @param cidade Recebe uma cidade e pesquisa no banco de dados por Id
	 * @return Retorna a Cidade Localizada
	 */
	@Override
	public Cidade pesquisarPorID(Cidade cidade) {

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
			rSet.close();

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

		return cidadeRetorno;

	}

	/**
	 * 
	 * @param cidade Recebe uma cidade e pesquisa no Banco de Dados por seu Nome
	 * @return Retorna um array de Cidades
	 */
	@Override
	public List<Cidade> pesquisarPorNome(Cidade cidade) {

		String sql = "select distinct * from cidades where nome like ? order by nome asc";
		List<Cidade> cidades = new ArrayList<Cidade>();
		
		Connection con = Conexao.getConnection();

		try {

			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, "%" + cidade.getNome().toUpperCase() + "%");

			ResultSet rSet = ps.executeQuery();

			while (rSet.next()) {
				Cidade cidadeRetorno = new Cidade();

				cidadeRetorno.setId(rSet.getInt("id"));
				cidadeRetorno.setNome(rSet.getString("nome"));
				cidadeRetorno.setUf(rSet.getString("uf"));

				cidades.add(cidadeRetorno);
			}
			
			rSet.close();

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

		return cidades;
	}

	/**
	 * 
	 * @return Retorna um array de Cidades
	 */
	@Override
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
			
			rSet.close();

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

		return cidades;

	}


}
