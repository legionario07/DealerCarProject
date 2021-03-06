package br.com.dealercar.core.dao.itensopcionais;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.core.factory.Conexao;
import br.com.dealercar.core.util.JSFUtil;
import br.com.dealercar.domain.EntidadeDominio;
import br.com.dealercar.domain.itensopcionais.BebeConforto;

/**
 * REaliza a persistencia dos ITEM OPCIONAL BebeConforto
 * @author Paulinho
 *
 */
public class BebeConfortoDAO extends AbstractPesquisaItensOpcionais {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Connection con = null;

	/**
	 * 
	 * @param BebeConforto
	 *            Recebe um BebeConforto como parametro e cadastra no Banco de
	 *            Dados
	 */
	public void cadastrar(EntidadeDominio entidade) {
		
		if(!(entidade instanceof BebeConforto))
			return;
		
		BebeConforto bebeConforto = new BebeConforto();
		bebeConforto = (BebeConforto) entidade;
		
		StringBuffer sql = new StringBuffer();
		sql.append("insert into bebe_confortos (descricao, valor, marca, numero_patrimonio, meses_bebe) ");
		sql.append("values (?, ?, ?, ?, ?)");
		
		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			pstm.setString(1, bebeConforto.getDescricao());
			pstm.setDouble(2, bebeConforto.getValor());
			pstm.setString(3, bebeConforto.getMarca());
			pstm.setString(4, bebeConforto.getNumeroPatrimonio());
			pstm.setInt(5, bebeConforto.getMesesBebe());
			pstm.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

	}

	/**
	 * 
	 * @param BebeConforto
	 *            Recebe um BebeConforto e exclui do Banco de Dados pelo seu
	 *            Codigo
	 */
	public void excluir(EntidadeDominio entidade) {
		
		if(!(entidade instanceof BebeConforto))
			return;
		
		BebeConforto bebeConforto = new BebeConforto();
		bebeConforto = (BebeConforto) entidade;
		
		StringBuffer sql = new StringBuffer();
		sql.append("delete from bebe_confortos where codigo = ?");
		
		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			pstm.setInt(1, bebeConforto.getCodigo());
			pstm.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}

	/**
	 * 
	 * @param BebeConforto
	 *            Recebe um BebeConforto e edita de acordo com seu codigo
	 */
	public void editar(EntidadeDominio entidade) {
		
		if(!(entidade instanceof BebeConforto))
			return;
		
		BebeConforto bebeConforto = new BebeConforto();
		bebeConforto = (BebeConforto) entidade;
		
		StringBuffer sql = new StringBuffer();
		sql.append("update bebe_confortos set descricao = ?, valor = ?, ");
		sql.append("marca = ?, numero_patrimonio = ?, meses_bebe = ? where codigo = ?");
		
		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			pstm.setString(1, bebeConforto.getDescricao());
			pstm.setDouble(2, bebeConforto.getValor());
			pstm.setString(3, bebeConforto.getMarca());
			pstm.setString(4, bebeConforto.getNumeroPatrimonio());
			pstm.setInt(5, bebeConforto.getMesesBebe());
			pstm.setInt(6, bebeConforto.getCodigo());
			pstm.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}

	/**
	 * 
	 * @return Lista os BebeConforto cadastrados no Banco e exibe em forma de
	 *         List<BebeConforto>
	 */
	public List<EntidadeDominio> listarTodos() {

		StringBuffer sql = new StringBuffer();
		sql.append("select * from bebe_confortos where codigo <> 99");

		List<EntidadeDominio> listaRetorno = new ArrayList<EntidadeDominio>();
		
		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());

			ResultSet rSet = pstm.executeQuery();

			while (rSet.next()) {
				BebeConforto bebeConforto = new BebeConforto();
				bebeConforto.setCodigo(rSet.getInt("codigo"));
				bebeConforto.setDescricao(rSet.getString("descricao"));
				bebeConforto.setValor(rSet.getDouble("valor"));
				bebeConforto.setMarca(rSet.getString("marca"));
				bebeConforto.setNumeroPatrimonio(rSet.getString("numero_patrimonio"));
				bebeConforto.setMesesBebe(rSet.getInt("meses_bebe"));

				listaRetorno.add(bebeConforto);

			}
			

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

		return listaRetorno;

	}

	/**
	 * 
	 * @param BebeConforto
	 *            Recebe um Objeto BebeConforto e localiza no Banco pelo Codigo
	 * @return Retorno um objeto BebeConforto
	 */
	public BebeConforto pesquisarPorCodigo(EntidadeDominio entidade) {
		
		if(!(entidade instanceof BebeConforto))
			return null;
		
		BebeConforto bebeConforto = new BebeConforto();
		bebeConforto = (BebeConforto) entidade;

		StringBuffer sql = new StringBuffer();
		sql.append("select * from bebe_confortos where codigo = ?");

		BebeConforto bebeConfortoRetorno = null;
		
		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			pstm.setInt(1, bebeConforto.getCodigo());

			ResultSet rSet = pstm.executeQuery();

			while (rSet.next()) {
				bebeConfortoRetorno = new BebeConforto();

				bebeConfortoRetorno.setCodigo(rSet.getInt("codigo"));
				bebeConfortoRetorno.setDescricao(rSet.getString("descricao"));
				bebeConfortoRetorno.setValor(rSet.getDouble("valor"));
				bebeConfortoRetorno.setMarca(rSet.getString("marca"));
				bebeConfortoRetorno.setNumeroPatrimonio(rSet.getString("numero_patrimonio"));
				bebeConfortoRetorno.setMesesBebe(rSet.getInt("meses_bebe"));

			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

		return bebeConfortoRetorno;
	}

	/**
	 * 
	 * @param BebeConforto
	 *            Recebe um Objeto BebeConforto e localiza no Banco de Dados
	 *            pelo Codigo
	 * @return Retorna um objeto de BebeConforto
	 */
	public BebeConforto pesquisarPorID(EntidadeDominio entidade) {
		
		if(!(entidade instanceof BebeConforto))
			return null;
		
		BebeConforto bebeConforto = new BebeConforto();
		bebeConforto = (BebeConforto) entidade;

		StringBuffer sql = new StringBuffer();
		sql.append("select * from bebe_confortos where codigo = ?");

		BebeConforto bConfortoRetorno = null;
		
		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			pstm.setInt(1, bebeConforto.getCodigo());

			ResultSet rSet = pstm.executeQuery();

			while (rSet.next()) {
				bConfortoRetorno = new BebeConforto();
				bConfortoRetorno.setCodigo(rSet.getInt("codigo"));
				bConfortoRetorno.setDescricao(rSet.getString("descricao"));
				bConfortoRetorno.setValor(rSet.getDouble("valor"));
				bConfortoRetorno.setMarca(rSet.getString("marca"));
				bConfortoRetorno.setMesesBebe(rSet.getInt("meses_bebe"));
				bConfortoRetorno.setNumeroPatrimonio(rSet.getString("numero_patrimonio"));

			}
			

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

		return bConfortoRetorno;
	}

}
