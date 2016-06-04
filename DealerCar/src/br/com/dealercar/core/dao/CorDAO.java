package br.com.dealercar.core.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import br.com.dealercar.core.factory.Conexao;
import br.com.dealercar.core.util.JSFUtil;
import br.com.dealercar.domain.EntidadeDominio;
import br.com.dealercar.domain.automotivos.Cor;

public class CorDAO implements IDAO, Serializable {

	private Connection con = null;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param usuario
	 *            Recebe um objeto Cor e cadastra no Banco de Dados pelo Id
	 * @return
	 */
	@Override
	public void cadastrar(EntidadeDominio entidade) {

		if (!(entidade instanceof Cor))
			return;

		Cor cor = new Cor();
		cor = (Cor) entidade;

		StringBuffer sql = new StringBuffer();
		sql.append("insert into cores (nome) values (?)");

		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			pstm.setString(1, cor.getNome().toUpperCase());

			pstm.executeUpdate();
			/*
			ResultSet rSet = pstm.getGeneratedKeys();
			while(rSet.next()){
				rSet.getInt(1);
			}
			*/

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

	public void excluir(EntidadeDominio entidade) {

		if (!(entidade instanceof Cor))
			return;

		Cor cor = new Cor();
		cor = (Cor) entidade;

		StringBuffer sql = new StringBuffer();
		sql.append("delete from cores where id = ?");

		con = Conexao.getConnection();

		try {

			PreparedStatement pstm = con.prepareStatement(sql.toString());
			pstm.setInt(1, cor.getId());

			pstm.executeUpdate();

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
	public void editar(EntidadeDominio entidade) {

		if (!(entidade instanceof Cor))
			return;

		Cor cor = new Cor();
		cor = (Cor) entidade;

		StringBuffer sql = new StringBuffer();
		sql.append("update cores set id = ?, nome = ? where id = ?");

		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			int i = 0;
			pstm.setInt(++i, cor.getId());
			pstm.setString(++i, cor.getNome().toUpperCase());
			pstm.setInt(++i, cor.getId());

			pstm.executeUpdate();

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
	public List<EntidadeDominio> listarTodos() {

		StringBuffer sql = new StringBuffer();
		sql.append("select * from cores");

		List<EntidadeDominio> listaCores = new ArrayList<EntidadeDominio>();
		con = Conexao.getConnection();

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
	public EntidadeDominio pesquisarPorID(EntidadeDominio entidade) {

		if (!(entidade instanceof Cor))
			return null;

		Cor cor = new Cor();
		cor = (Cor) entidade;

		StringBuffer sql = new StringBuffer();
		sql.append("select * from cores where id = ?");

		Cor corRetorno = null;

		con = Conexao.getConnection();

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
