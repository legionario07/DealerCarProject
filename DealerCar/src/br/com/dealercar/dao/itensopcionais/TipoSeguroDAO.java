package br.com.dealercar.dao.itensopcionais;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.domain.itensopcionais.TipoSeguro;
import br.com.dealercar.enums.SeguroType;
import br.com.dealercar.factory.Conexao;
import br.com.dealercar.util.JSFUtil;

public class TipoSeguroDAO {

	/**
	 * 
	 * @return Retorna todos os tipos de Tipo de Seguro cadastrados no BD
	 */
	public List<TipoSeguro> listarTodos() {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from tipo_seguro");

		List<TipoSeguro> listaRetorno = new ArrayList<TipoSeguro>();

		Connection con = Conexao.getConnection();

		try {
			PreparedStatement ps = con.prepareStatement(sql.toString());
			ResultSet rSet = ps.executeQuery();

			while (rSet.next()) {
				TipoSeguro tipoSeguro = new TipoSeguro();
				tipoSeguro.setId(rSet.getInt("id"));

				// Condicional que verifica o tipo de Enum Cadastrada no BD
				// e retorna a descrição da ENUM
				if (rSet.getString("nome").equals("COMPREENSIVA")) {
					tipoSeguro.setNome(SeguroType.valueOf("COMPREENSIVA"));
				} else {
					tipoSeguro.setNome(SeguroType.valueOf("ROUBO"));
				}
				tipoSeguro.setValorAcrescido(rSet.getDouble("valor_acrescido"));

				listaRetorno.add(tipoSeguro);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

		return listaRetorno;
	}

	/**
	 * 
	 * @param tipoSeguro
	 *            Recebe um objeto tipoSeguro e adciona no BD
	 */

	public void cadastrar(TipoSeguro tipoSeguro) {
		StringBuffer sql = new StringBuffer();
		sql.append("insert into tipo_seguro (nome, valor_acrescido) ");
		sql.append("values (? , ?)");

		Connection con = Conexao.getConnection();

		try {
			PreparedStatement ps = con.prepareStatement(sql.toString());
			ps.setString(1, tipoSeguro.getNome().getDescricao().toUpperCase());
			ps.setDouble(2, tipoSeguro.getValorAcrescido());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

	}

	/**
	 * 
	 * @param tipoSeguro
	 *            Recebe um objeto TipoSeguro e edita os dados no BD
	 */
	public void editar(TipoSeguro tipoSeguro) {
		StringBuffer sql = new StringBuffer();
		sql.append("update tipo_seguro set nome = ?, valor_acrescido = ? ");
		sql.append("where id = ?");

		Connection con = Conexao.getConnection();

		try {
			PreparedStatement ps = con.prepareStatement(sql.toString());
			ps.setString(1, tipoSeguro.getNome().toString().toUpperCase());
			ps.setDouble(2, tipoSeguro.getValorAcrescido());
			ps.setInt(3, tipoSeguro.getId());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

	}

	/**
	 * 
	 * @param tipoSeguro
	 *            Recebe um objeto do tipo seguro e exclui do BD
	 */
	public void excluir(TipoSeguro tipoSeguro) {
		StringBuffer sql = new StringBuffer();
		sql.append("delete from tipo_seguro where id=?");

		Connection con = Conexao.getConnection();

		try {
			PreparedStatement ps = con.prepareStatement(sql.toString());
			ps.setInt(1, tipoSeguro.getId());
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}

	/**
	 * 
	 * @param tipoSeguro Recebe um objeto de TipoSeguro e localiza no BD
	 * através de seu ID
	 * @return Retorna um objeto TipoSeguro localizado no BD
	 */
	public TipoSeguro pesquisarPorID(TipoSeguro tipoSeguro) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from tipo_seguro where id = ?");

		TipoSeguro retorno = null;

		Connection con = Conexao.getConnection();

		try {
			PreparedStatement ps = con.prepareStatement(sql.toString());
			ps.setInt(1, tipoSeguro.getId());

			ResultSet rSet = ps.executeQuery();

			while (rSet.next()) {
				retorno = new TipoSeguro();
				
				retorno.setId(rSet.getInt("id"));

				// Condicional que verifica o tipo de Enum Cadastrada no BD
				// e retorna a descrição da ENUM
				if (rSet.getString("nome").equals("COMPREENSIVA")) {
					retorno.setNome(SeguroType.valueOf("COMPREENSIVA"));
				} else {
					retorno.setNome(SeguroType.valueOf("ROUBO"));
				}
				retorno.setValorAcrescido(rSet.getDouble("valor_acrescido"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

		return retorno;

	}

}
