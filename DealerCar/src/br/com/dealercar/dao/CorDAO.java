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
		StringBuffer sql = new StringBuffer();
		sql.append("insert into cores (nome) values (?)");
		
		Connection con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			pstm.setString(1, cor.getNome());

			pstm.executeUpdate();
			
			pstm.close();
			con.close();

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

		StringBuffer sql = new StringBuffer();
		sql.append("delete from cores where id = ?");
		
		Connection con = Conexao.getConnection();

		try {

			PreparedStatement pstm = con.prepareStatement(sql.toString());
			pstm.setInt(1, cor.getId());

			pstm.executeUpdate();
			
			pstm.close();
			con.close();

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
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			int i = 0;
			pstm.setInt(++i, cor.getId());
			pstm.setString(++i, cor.getNome());
			pstm.setInt(++i, cor.getId());

			pstm.executeUpdate();
			
			pstm.close();
			con.close();

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
		StringBuffer sql = new StringBuffer();
		sql.append("select * from cores");
		
		List<Cor> listaCores = new ArrayList<Cor>();
		Connection con = Conexao.getConnection();

		try {

			PreparedStatement pstm = con.prepareStatement(sql.toString());

			ResultSet rSet = pstm.executeQuery();

			while (rSet.next()) {
				Cor cor = new Cor();
				cor.setId(rSet.getInt("id"));
				cor.setNome(rSet.getString("nome"));
				listaCores.add(cor);

			}


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
		StringBuffer sql = new StringBuffer();
		sql.append("select * from cores where id = ?");
		
		Cor corRetorno = null;
		
		Connection con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			pstm.setInt(1, cor.getId());

			ResultSet rSet = pstm.executeQuery();

			while (rSet.next()) {
				corRetorno = new Cor();
				corRetorno.setId(rSet.getInt("id"));
				corRetorno.setNome(rSet.getString("nome"));
			}

			
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

		return corRetorno;
	}

}
