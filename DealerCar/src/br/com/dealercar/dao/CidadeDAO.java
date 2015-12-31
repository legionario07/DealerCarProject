package br.com.dealercar.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.domain.Cidade;
import br.com.dealercar.factory.Conexao;
import br.com.dealercar.util.JSFUtil;

public class CidadeDAO extends AbstractPesquisaDAO<Cidade> implements Serializable {

	Connection con = Conexao.getConnection();
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 
	 * 
	 * @param cidade Recebe uma Cidade e cadastra no Banco de Dados
	 */
	@Override
	public void cadastrar(Cidade cidade) {

		StringBuffer sql = new StringBuffer();
		sql.append("insert into cidades (nome, uf)  values (?,?)");
		
		con = Conexao.getConnection();
		
		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			pstm.setString(1, cidade.getNome().toUpperCase());
			pstm.setString(2, cidade.getUf().toUpperCase());

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
	 * @param cidade Recebe uma cidade e exclui do Banco de Dados
	 * 	pesquisando pelo seu Id
	 */
	@Override
	public void excluir(Cidade cidade) {
		StringBuffer sql = new StringBuffer();
		sql.append("delete from cidades where id=?");
		
		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			pstm.setInt(1, cidade.getId());

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
	 * @param cidade Recebe uma Cidade e edita seus dados no Banco de DAdos
	 * 	pesquisando por seu Id
	 */
	@Override
	public void editar(Cidade cidade) {

		StringBuffer sql = new StringBuffer();
		sql.append("update cidades set nome = ?, uf = ? where id=?");

		con = Conexao.getConnection();
		
		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			pstm.setString(1, cidade.getNome().toUpperCase());
			pstm.setString(2, cidade.getUf().toUpperCase());
			pstm.setInt(3, cidade.getId());

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
	 * @param cidade Recebe uma cidade e pesquisa no banco de dados por Id
	 * @return Retorna a Cidade Localizada
	 */
	@Override
	public Cidade pesquisarPorID(Cidade cidade) {

		StringBuffer sql = new StringBuffer();
		sql.append("select * from cidades where id = ? ");
		
		Cidade cidadeRetorno = null;
		
		con = Conexao.getConnection();
		
		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			pstm.setInt(1, cidade.getId());

			ResultSet rSet = pstm.executeQuery();

			while (rSet.next()) {
				cidadeRetorno = new Cidade();
				cidadeRetorno.setId(rSet.getInt("id"));
				cidadeRetorno.setNome(rSet.getString("nome"));
				cidadeRetorno.setUf(rSet.getString("uf"));

			}
		

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

		StringBuffer sql = new StringBuffer();
		sql.append("select distinct * from cidades ");
		sql.append("where nome like ? order by nome asc");
		List<Cidade> cidades = new ArrayList<Cidade>();
		
		con = Conexao.getConnection();

		try {

			PreparedStatement pstm = con.prepareStatement(sql.toString());
			pstm.setString(1, "%" + cidade.getNome().toUpperCase() + "%");

			ResultSet rSet = pstm.executeQuery();

			while (rSet.next()) {
				Cidade cidadeRetorno = new Cidade();

				cidadeRetorno.setId(rSet.getInt("id"));
				cidadeRetorno.setNome(rSet.getString("nome"));
				cidadeRetorno.setUf(rSet.getString("uf"));

				cidades.add(cidadeRetorno);
			}
			

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

		StringBuffer sql = new StringBuffer();
		sql.append("select * from cidades");
		List<Cidade> cidades = new ArrayList<Cidade>();
		
		con = Conexao.getConnection();

		try {

			PreparedStatement pstm = con.prepareStatement(sql.toString());
			ResultSet rSet = pstm.executeQuery();

			while (rSet.next()) {
				Cidade cidadeRetorno = new Cidade();
				cidadeRetorno.setId(rSet.getInt("id"));
				cidadeRetorno.setNome(rSet.getString("nome"));
				cidadeRetorno.setUf(rSet.getString("uf"));

				cidades.add(cidadeRetorno);

			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

		return cidades;

	}


}
