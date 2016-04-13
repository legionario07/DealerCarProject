package br.com.dealercar.core.dao.itensopcionais;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.core.factory.Conexao;
import br.com.dealercar.core.util.JSFUtil;
import br.com.dealercar.domain.EntidadeDominio;
import br.com.dealercar.domain.enums.SeguroType;
import br.com.dealercar.domain.itensopcionais.TipoSeguro;

/**
 * REaliza a persistencia dos ITEM OPCIONAL TipoSeguro
 * @author Paulinho
 *
 */
public class TipoSeguroDAO extends AbstractPesquisaItensOpcionais implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Connection con = null;

	/**
	 * 
	 * @return Retorna todos os tipos de Tipo de Seguro cadastrados no BD
	 */
	public List<EntidadeDominio> listarTodos() {
		
		StringBuffer sql = new StringBuffer();
		sql.append("select * from tipo_seguro");

		List<EntidadeDominio> listaRetorno = new ArrayList<EntidadeDominio>();
		
		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			ResultSet rSet = pstm.executeQuery();

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

	public void cadastrar(EntidadeDominio entidade) {
		
		if(!(entidade instanceof TipoSeguro))
			return;
		
		TipoSeguro tipoSeguro = new TipoSeguro();
		tipoSeguro = (TipoSeguro) entidade;
		
		StringBuffer sql = new StringBuffer();
		sql.append("insert into tipo_seguro (nome, valor_acrescido) ");
		sql.append("values (? , ?)");

		con = Conexao.getConnection();
		
		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			pstm.setString(1, tipoSeguro.getNome().getDescricao().toUpperCase());
			pstm.setDouble(2, tipoSeguro.getValorAcrescido());

			pstm.executeUpdate();
			
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
	public void editar(EntidadeDominio entidade) {
		
		if(!(entidade instanceof TipoSeguro))
			return;
		
		TipoSeguro tipoSeguro = new TipoSeguro();
		tipoSeguro = (TipoSeguro) entidade;
		
		StringBuffer sql = new StringBuffer();
		sql.append("update tipo_seguro set nome = ?, valor_acrescido = ? ");
		sql.append("where id = ?");
		
		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			pstm.setString(1, tipoSeguro.getNome().toString().toUpperCase());
			pstm.setDouble(2, tipoSeguro.getValorAcrescido());
			pstm.setInt(3, tipoSeguro.getId());

			pstm.executeUpdate();
			
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
	public void excluir(EntidadeDominio entidade) {
		
		if(!(entidade instanceof TipoSeguro))
			return;
		
		TipoSeguro tipoSeguro = new TipoSeguro();
		tipoSeguro = (TipoSeguro) entidade;
		
		StringBuffer sql = new StringBuffer();
		sql.append("delete from tipo_seguro where id=?");
		
		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			pstm.setInt(1, tipoSeguro.getId());
			pstm.executeUpdate();
			
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
	public TipoSeguro pesquisarPorID(EntidadeDominio entidade) {
		
		if(!(entidade instanceof TipoSeguro))
			return null;
		
		TipoSeguro tipoSeguro = new TipoSeguro();
		tipoSeguro = (TipoSeguro) entidade;
		
		StringBuffer sql = new StringBuffer();
		sql.append("select * from tipo_seguro where id = ?");

		TipoSeguro retorno = null;
		
		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			pstm.setInt(1, tipoSeguro.getId());

			ResultSet rSet = pstm.executeQuery();

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

	@Override
	public EntidadeDominio pesquisarPorCodigo(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		return null;
	}

}
