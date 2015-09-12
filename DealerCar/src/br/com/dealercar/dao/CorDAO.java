package br.com.dealercar.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.domain.Cor;
import br.com.dealercar.factory.Conexao;
import br.com.dealercar.util.JSFUtil;

public class CorDAO implements IDAO<Cor> {


	/**
	 * 
	 * @param usuario Recebe um objeto Cor e cadastra no Banco de Dados pelo Id
	 * @return
	 */
	@Override
	public void cadastrar(Cor cor) {
		String sql = "insert into cores (nome) values (?)";

		Connection con = Conexao.getConnection();

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, cor.getNome());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}

	/**
	 * 
	 * @param usuario
	 *            recebe um objeto Cor e excluir do Banco de Dados pelo Id
	 * @return
	 */

	public void excluir(Cor cor) {

		String sql = "delete from cores where id = ?";

		Connection con = Conexao.getConnection();

		try {

			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, cor.getId());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

	}

	/**
	 * 
	 * @param cor
	 *            Recebe um objeto Cor e edita seus dados no Banco de DAdos
	 */
	@Override
	public void editar(Cor cor) {
		StringBuffer sql = new StringBuffer();
		sql.append("update cores set id = ?, nome = ? where id = ?");

		Connection con = Conexao.getConnection();

		try {
			PreparedStatement ps = con.prepareStatement(sql.toString());
			int i = 0;
			ps.setInt(++i, cor.getId());
			ps.setString(++i, cor.getNome());
			ps.setInt(++i, cor.getId());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}

	/**
	 * 
	 * @return Retorna todas as cores Cadastradas no BD em forma de List<Cor>
	 */
	@Override
	public List<Cor> listarTodos() {
		String sql = "select * from cores";
		List<Cor> listaCores = new ArrayList<Cor>();

		Connection con = Conexao.getConnection();

		try {

			PreparedStatement ps = con.prepareStatement(sql);

			ResultSet rSet = ps.executeQuery();

			while (rSet.next()) {
				Cor cor = new Cor();
				cor.setId(rSet.getInt("id"));
				cor.setNome(rSet.getString("nome"));
				listaCores.add(cor);

			}
			rSet.close();

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

		return listaCores;
	}

	/**
	 * 
	 * @param cor
	 *            Recebe uma Cor e pesquisa no BD por seu Id
	 * @return Retorna um objeto de Cor
	 */
	@Override
	public Cor pesquisarPorID(Cor cor) {
		String sql = "select * from cores where id = ?";
		Cor corRetorno = null;

		Connection con = Conexao.getConnection();

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, cor.getId());

			ResultSet rSet = ps.executeQuery();

			while (rSet.next()) {
				corRetorno = new Cor();
				corRetorno.setId(rSet.getInt("id"));
				corRetorno.setNome(rSet.getString("nome"));
			}

			rSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

		return corRetorno;
	}

}
