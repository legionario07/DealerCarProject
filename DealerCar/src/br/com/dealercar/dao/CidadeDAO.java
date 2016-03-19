package br.com.dealercar.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.domain.Cidade;
import br.com.dealercar.domain.Estado;
import br.com.dealercar.factory.Conexao;
import br.com.dealercar.util.JSFUtil;

public class CidadeDAO extends AbstractPesquisaDAO<Cidade> implements Serializable {

	private Connection con = null;
	
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
		sql.append("insert into cidades_tbl (nome, id_estado)  values (?, ?)");
		
		con = Conexao.getConnection();
		
		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			pstm.setString(1, cidade.getNome().toUpperCase());
			pstm.setInt(2, cidade.getEstado().getId());

			pstm.executeUpdate();

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
		sql.append("delete from cidades_tbl where id = ?");
		
		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			pstm.setInt(1, cidade.getId());

			pstm.executeUpdate();
			
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
		sql.append("update cidades_tbl set nome = ?, id_estado = ? where id=?");

		con = Conexao.getConnection();
		
		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			pstm.setString(1, cidade.getNome().toUpperCase());
			pstm.setInt(2, cidade.getEstado().getId());
			pstm.setInt(3, cidade.getId());

			pstm.executeUpdate();
			
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
		sql.append("select * from cidades_tbl where id = ? ");
		
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
				
				Estado estadoRetorno = new Estado();
				estadoRetorno.setId(rSet.getInt("id_estado"));
				
				estadoRetorno = new EstadoDAO().pesquisarPorID(estadoRetorno);
				cidadeRetorno.setEstado(estadoRetorno);

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
		sql.append("select distinct * from cidades_tbl ");
		sql.append("where nome like ? order by nome asc");
		
		List<Cidade> cidades = new ArrayList<Cidade>();
		
		Cidade cidadeRetorno = null;
		
		con = Conexao.getConnection();

		try {

			PreparedStatement pstm = con.prepareStatement(sql.toString());
			pstm.setString(1, "%" + cidade.getNome().toUpperCase() + "%");

			ResultSet rSet = pstm.executeQuery();

			while (rSet.next()) {
				cidadeRetorno = new Cidade();

				cidadeRetorno.setId(rSet.getInt("id"));
				cidadeRetorno.setNome(rSet.getString("nome"));
				
				Estado estadoRetorno = new Estado();
				estadoRetorno.setId(rSet.getInt("id_estado"));
				
				estadoRetorno = new EstadoDAO().pesquisarPorID(estadoRetorno);
				cidadeRetorno.setEstado(estadoRetorno);

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
		sql.append("select * from cidades_tbl");
		List<Cidade> cidades = new ArrayList<Cidade>();
		
		Cidade cidadeRetorno = null;
		
		con = Conexao.getConnection();

		try {

			PreparedStatement pstm = con.prepareStatement(sql.toString());
			ResultSet rSet = pstm.executeQuery();

			while (rSet.next()) {
				
				cidadeRetorno = new Cidade();
				cidadeRetorno.setId(rSet.getInt("id"));
				cidadeRetorno.setNome(rSet.getString("nome"));
				
				Estado estadoRetorno = new Estado();
				estadoRetorno.setId(rSet.getInt("id_estado"));
				
				estadoRetorno = new EstadoDAO().pesquisarPorID(estadoRetorno);
				cidadeRetorno.setEstado(estadoRetorno);

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
	 * @param cidade Recebe uma cidade e pesquisa no Banco de Dados por seu Estado
	 * @return Retorna um array de Cidades
	 */
	public List<Cidade> pesquisarPorIDEstado(Estado estado) {

		StringBuffer sql = new StringBuffer();
		sql.append("select * from cidades_tbl ");
		sql.append("inner join estados on cidades_tbl.id_estado = estados.id ");
		sql.append("where estados.id = ? ");
		List<Cidade> lista = new ArrayList<Cidade>();
		
		con = Conexao.getConnection();
		
		Cidade cidade = null;

		try {

			PreparedStatement pstm = con.prepareStatement(sql.toString());
			pstm.setInt(1, estado.getId());

			ResultSet rSet = pstm.executeQuery();

			while (rSet.next()) {
				cidade = new Cidade();

				cidade.setId(rSet.getInt("id"));
				cidade.setNome(rSet.getString("nome"));
				
				Estado estadoRetorno = new Estado();
				estadoRetorno.setId(rSet.getInt("id_estado"));
				
				estadoRetorno = new EstadoDAO().pesquisarPorID(estadoRetorno);
				cidade.setEstado(estadoRetorno);

				lista.add(cidade);
			}
			

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

		return lista;
	}
	
	/**
	 * 
	 * @param cidade Recebe uma cidade e pesquisa no Banco de Dados por seu Estado
	 * @return Retorna um array de Cidades
	 */
	public List<Cidade> pesquisarPorUFEstado(Estado estado) {

		StringBuffer sql = new StringBuffer();
		sql.append("select * from cidades_tbl ");
		sql.append("inner join estados on cidades_tbl.id_estado = estados.id ");
		sql.append("where estados.uf = ? ");
		List<Cidade> lista = new ArrayList<Cidade>();
		
		con = Conexao.getConnection();
		
		Cidade cidade = null;

		try {

			PreparedStatement pstm = con.prepareStatement(sql.toString());
			pstm.setString(1, estado.getUf());

			ResultSet rSet = pstm.executeQuery();

			while (rSet.next()) {
				cidade = new Cidade();

				cidade.setId(rSet.getInt("id"));
				cidade.setNome(rSet.getString("nome"));
				
				Estado estadoRetorno = new Estado();
				estadoRetorno.setId(rSet.getInt("id_estado"));
				estadoRetorno = new EstadoDAO().pesquisarPorID(estadoRetorno);
				cidade.setEstado(estadoRetorno);

				lista.add(cidade);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

		return lista;
	}


}

