package br.com.dealercar.dao.itensrevisao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.domain.produtosrevisao.FormaDeVenda;
import br.com.dealercar.factory.Conexao;
import br.com.dealercar.util.DaoUtil;
import br.com.dealercar.util.JSFUtil;

/**
 * Classe responsavel pelo gerenciamento dos farois no Banco
 * 
 * @author Paulinho
 *
 */
public class FormaDeVendaDAO extends AbstractPesquisaItensRevisao<FormaDeVenda> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Connection con = null;

	@Override
	public void cadastrar(FormaDeVenda formaDeVenda) {

		StringBuffer sql = new StringBuffer();
		sql.append("insert into forma_de_venda ");
		sql.append("(descricao) values (?) ");

		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			int i = 0;
			pstm.setString(++i, formaDeVenda.getDescricao().toUpperCase());
			pstm.executeUpdate();

			pstm.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

	}

	@Override
	public void excluir(FormaDeVenda formaDeVenda) {

		StringBuffer sql = new StringBuffer();
		sql.append("delete from forma_de_venda ");
		sql.append("where id = ? ");

		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			int i = 0;
			pstm.setInt(++i, formaDeVenda.getId());
			pstm.executeUpdate();

			pstm.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

	}

	@Override
	public void editar(FormaDeVenda formaDeVenda) {

		StringBuffer sql = new StringBuffer();
		sql.append("update forma_de_venda set ");
		sql.append("descricao =? ");
		sql.append("where id = ? ");

		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			int i = 0;
			pstm.setString(++i, formaDeVenda.getDescricao().toUpperCase());
			pstm.setInt(++i, formaDeVenda.getId());
			pstm.executeUpdate();

			pstm.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

	}

	@Override
	public List<FormaDeVenda> listarTodos() {

		StringBuffer sql = new StringBuffer();
		sql.append("select * from forma_de_venda ");

		List<FormaDeVenda> lista = new ArrayList<FormaDeVenda>();

		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			ResultSet rSet = pstm.executeQuery();

			while (rSet.next()) {
				FormaDeVenda formaDeVendaRetorno = new FormaDeVenda();
				formaDeVendaRetorno.setId(rSet.getInt("id"));
				formaDeVendaRetorno.setDescricao(rSet.getString("descricao"));

				lista.add(formaDeVendaRetorno);

			}

			rSet.close();
			pstm.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

		return lista;
	}

	@Override
	public FormaDeVenda pesquisarPorID(FormaDeVenda formaDeVenda) {

		StringBuffer sql = new StringBuffer();
		sql.append("select * from forma_de_venda ");
		sql.append("where id = ? ");

		FormaDeVenda formaDeVendaRetorno = null;
		
		con = Conexao.getConnection();
		
		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			int i = 0;
			pstm.setInt(++i, formaDeVenda.getId());
			ResultSet rSet = pstm.executeQuery();

			while (rSet.next()) {
				formaDeVendaRetorno = new FormaDeVenda();
				formaDeVendaRetorno.setId(rSet.getInt("id"));
				formaDeVendaRetorno.setDescricao(rSet.getString("descricao"));

			}

			/**
			 * Se DaoUtil.isCallFromDao != -1 a connection será fechada no
			 * DAO de chamador
			 */
			if(DaoUtil.isCallFromDao() == -1) {
				rSet.close();
				pstm.close();
				con.close();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

		return formaDeVendaRetorno;
	}

	@Override
	public List<FormaDeVenda> pesquisarPorMarca(FormaDeVenda formaDeVenda) {
		// TODO Auto-generated method stub
		return null;
	}

}
