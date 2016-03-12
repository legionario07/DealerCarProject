package br.com.dealercar.dao.itensrevisao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.domain.produtosrevisao.Farol;
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
public class FarolDAO extends AbstractPesquisaItensRevisao<Farol> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Connection con = null;

	@Override
	public void cadastrar(Farol farol) {

		StringBuffer sql = new StringBuffer();
		sql.append("insert into farol ");
		sql.append("(descricao, marca, tipo, id_forma_de_venda, valor, quantidade) ");
		sql.append("values ( ? , ? , ? , ? , ? , ? ) ");

		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			int i = 0;
			pstm.setString(++i, farol.getDescricao().toUpperCase());
			pstm.setString(++i, farol.getMarca().toUpperCase());
			pstm.setString(++i, farol.getTipo().toUpperCase());
			pstm.setInt(++i, farol.getFormaDeVenda().getId());
			pstm.setDouble(++i, farol.getValor());
			pstm.setInt(++i, farol.getQuantidade());

			pstm.executeUpdate();

			pstm.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro("Erro ao cadastrar Farol no Banco de Dados.");
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

	}

	@Override
	public void excluir(Farol farol) {

		StringBuffer sql = new StringBuffer();
		sql.append("delete from farol ");
		sql.append("where id = ? ");

		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			int i = 0;
			pstm.setInt(++i, farol.getId());
			pstm.executeUpdate();

			pstm.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro("Erro ao excluir Farol no Banco de Dados.");
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

	}

	@Override
	public void editar(Farol farol) {

		StringBuffer sql = new StringBuffer();
		sql.append("update farol set ");
		sql.append("descricao = ?, marca = ?, tipo = ?, id_forma_de_venda = ?, ");
		sql.append("valor = ?, quantidade = ? ");
		sql.append("where id = ? ");

		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			int i = 0;

			pstm.setString(++i, farol.getDescricao().toUpperCase());
			pstm.setString(++i, farol.getMarca().toUpperCase());
			pstm.setString(++i, farol.getTipo().toUpperCase());
			pstm.setInt(++i, farol.getFormaDeVenda().getId());
			pstm.setDouble(++i, farol.getValor());
			pstm.setInt(++i, farol.getQuantidade());
			pstm.setInt(++i, farol.getId());
			pstm.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro("Erro ao Editar o Farol no Banco de Dados.");
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

	}

	@Override
	public Farol pesquisarPorID(Farol farol) {

		StringBuffer sql = new StringBuffer();
		sql.append("select * from farol ");
		sql.append("where id = ? ");

		Farol farolRetorno = null;

		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			int i = 0;
			pstm.setInt(++i, farol.getId());
			ResultSet rSet = pstm.executeQuery();

			while (rSet.next()) {

				farolRetorno = new Farol();
				farolRetorno.setId(rSet.getInt("id"));
				farolRetorno.setDescricao(rSet.getString("descricao"));
				farolRetorno.setTipo(rSet.getString("tipo"));
				farolRetorno.setMarca(rSet.getString("marca"));
				farolRetorno.setValor(rSet.getDouble("valor"));
				farolRetorno.setQuantidade(rSet.getInt("quantidade"));

				FormaDeVenda formaDeVenda = new FormaDeVenda();
				formaDeVenda.setId(rSet.getInt("id_forma_de_venda"));
				formaDeVenda = new FormaDeVendaDAO().pesquisarPorID(formaDeVenda);

				farolRetorno.setFormaDeVenda(formaDeVenda);

			}

			/**
			 * Se DaoUtil.isCallFromDao != -1 a connection será fechada no DAO
			 * de chamador
			 */
			if (DaoUtil.isCallFromDao() == -1) {
				rSet.close();
				pstm.close();
				con.close();
			}

		} catch (SQLException e) {
			e.printStackTrace();
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro("Erro ao buscar o Farol no Banco de Dados.");
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

		return farolRetorno;
	}

	@Override
	public List<Farol> pesquisarPorMarca(Farol farol) {

		StringBuffer sql = new StringBuffer();
		sql.append("select * from farol ");
		sql.append("where marca = ? ");

		List<Farol> lista = new ArrayList<Farol>();
		Farol farolRetorno = null;

		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			int i = 0;
			pstm.setString(++i, farol.getMarca().toUpperCase());
			ResultSet rSet = pstm.executeQuery();

			while (rSet.next()) {
				farolRetorno = new Farol();
				farolRetorno.setId(rSet.getInt("id"));
				farolRetorno.setDescricao(rSet.getString("descricao"));
				farolRetorno.setTipo(rSet.getString("tipo"));
				farolRetorno.setMarca(rSet.getString("marca"));
				farolRetorno.setValor(rSet.getDouble("valor"));
				farolRetorno.setQuantidade(rSet.getInt("quantidade"));

				FormaDeVenda formaDeVenda = new FormaDeVenda();
				formaDeVenda.setId(rSet.getInt("id_forma_de_venda"));
				formaDeVenda = new FormaDeVendaDAO().pesquisarPorID(formaDeVenda);

				farolRetorno.setFormaDeVenda(formaDeVenda);

				lista.add(farolRetorno);

			}

			rSet.close();
			pstm.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lista;
	}

	@Override
	public List<Farol> listarTodos() {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from farol ");

		List<Farol> lista = new ArrayList<Farol>();
		Farol farolRetorno = null;

		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			ResultSet rSet = pstm.executeQuery();

			while (rSet.next()) {
				farolRetorno = new Farol();
				farolRetorno.setId(rSet.getInt("id"));
				farolRetorno.setDescricao(rSet.getString("descricao"));
				farolRetorno.setTipo(rSet.getString("tipo"));
				farolRetorno.setMarca(rSet.getString("marca"));
				farolRetorno.setValor(rSet.getDouble("valor"));
				farolRetorno.setQuantidade(rSet.getInt("quantidade"));

				FormaDeVenda formaDeVenda = new FormaDeVenda();
				formaDeVenda.setId(rSet.getInt("id_forma_de_venda"));
				formaDeVenda = new FormaDeVendaDAO().pesquisarPorID(formaDeVenda);

				farolRetorno.setFormaDeVenda(formaDeVenda);

				lista.add(farolRetorno);

			}

			rSet.close();
			pstm.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lista;
	}
}
