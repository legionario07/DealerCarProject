package br.com.dealercar.core.dao;


import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.core.factory.Conexao;
import br.com.dealercar.core.util.JSFUtil;
import br.com.dealercar.domain.Cargo;
import br.com.dealercar.domain.EntidadeDominio;

public class CargoDAO implements IDAO, Serializable{

	private Connection con = null;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param usuario Recebe um objeto Cargo e cadastra no Banco de Dados pelo Id
	 * @return
	 */
	@Override
	public void cadastrar(EntidadeDominio entidade) {
		
		if(!(entidade instanceof Cargo))
			return;
		
		Cargo cargo = new Cargo();
		cargo = (Cargo) entidade;
		
		StringBuffer sql = new StringBuffer();
		sql.append("insert into cargos (nome) values (?)");
		
		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			pstm.setString(1, cargo.getNome().toUpperCase());

			pstm.executeUpdate();
			

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 
	 * @param usuario
	 *            recebe um objeto Cargo e excluir do Banco de Dados pelo Id
	 * @return
	 */

	public void excluir(EntidadeDominio entidade) {

		if(!(entidade instanceof Cargo))
			return;
		
		Cargo cargo = new Cargo();
		cargo = (Cargo) entidade;
		
		StringBuffer sql = new StringBuffer();
		sql.append("delete from cargos where id = ?");
		
		con = Conexao.getConnection();

		try {

			PreparedStatement pstm = con.prepareStatement(sql.toString());
			pstm.setInt(1, cargo.getId());

			pstm.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * 
	 * @param cargo
	 *            Recebe um objeto Cargo e edita seus dados no Banco de DAdos
	 */
	@Override
	public void editar(EntidadeDominio entidade) {
		
		if(!(entidade instanceof Cargo))
			return;
		
		Cargo cargo = new Cargo();
		cargo = (Cargo) entidade;
		
		StringBuffer sql = new StringBuffer();
		sql.append("update cargos set id = ?, nome = ? where id = ?");
		
		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			int i = 0;
			pstm.setInt(++i, cargo.getId());
			pstm.setString(++i, cargo.getNome().toUpperCase());
			pstm.setInt(++i, cargo.getId());

			pstm.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 
	 * @return Retorna todas as cargos Cadastradas no BD em forma de List<Cargo>
	 */
	@Override
	public List<EntidadeDominio> listarTodos() {
		
		StringBuffer sql = new StringBuffer();
		sql.append("select * from cargos");
		sql.append(" order by id asc");
		
		List<EntidadeDominio> listaCargoes = new ArrayList<EntidadeDominio>();
		con = Conexao.getConnection();

		try {

			PreparedStatement pstm = con.prepareStatement(sql.toString());

			ResultSet rSet = pstm.executeQuery();

			while (rSet.next()) {
				Cargo cargo = new Cargo();
				cargo.setId(rSet.getInt("id"));
				cargo.setNome(rSet.getString("nome"));
				listaCargoes.add(cargo);

			}


		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		} 

		return listaCargoes;
	}

	/**
	 * 
	 * @param cargo
	 *            Recebe uma Cargo e pesquisa no BD por seu Id
	 * @return Retorna um objeto de Cargo
	 */
	@Override
	public EntidadeDominio pesquisarPorID(EntidadeDominio entidade) {
		
		if(!(entidade instanceof Cargo))
			return null;
		
		Cargo cargo = new Cargo();
		cargo = (Cargo) entidade;
		
		StringBuffer sql = new StringBuffer();
		sql.append("select * from cargos where id = ?");
		
		Cargo cargoRetorno = null;
		
		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			pstm.setInt(1, cargo.getId());

			ResultSet rSet = pstm.executeQuery();

			while (rSet.next()) {
				cargoRetorno = new Cargo();
				cargoRetorno.setId(rSet.getInt("id"));
				cargoRetorno.setNome(rSet.getString("nome"));
			}

			
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		} 
		return cargoRetorno;
	}

}
