package br.com.dealercar.dao.itensrevisao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.domain.produtosrevisao.Pneu;
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
public class PneuDAO extends AbstractPesquisaItensRevisao<Pneu> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Connection con = null;

	@Override
	public void cadastrar(Pneu pneu) {

		StringBuffer sql = new StringBuffer();
		sql.append("insert into pneus ");
		sql.append("(descricao, marca, tipo, id_forma_de_venda, valor, quantidade) ");
		sql.append("values ( ? , ? , ? , ? , ? , ? ) ");

		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			int i = 0;
			pstm.setString(++i, pneu.getDescricao().toUpperCase());
			pstm.setString(++i, pneu.getMarca().toUpperCase());
			pstm.setString(++i, pneu.getTipo().toUpperCase());
			pstm.setInt(++i, pneu.getFormaDeVenda().getId());
			pstm.setDouble(++i, pneu.getValor());
			pstm.setInt(++i, pneu.getQuantidade());

			pstm.executeUpdate();

			pstm.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro("Erro ao cadastrar Pneu no Banco de Dados.");
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

	}

	@Override
	public void excluir(Pneu pneu) {

		StringBuffer sql = new StringBuffer();
		sql.append("delete from pneus ");
		sql.append("where id = ? ");

		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			int i = 0;
			pstm.setInt(++i, pneu.getId());
			pstm.executeUpdate();

			pstm.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro("Erro ao excluir Pneu no Banco de Dados.");
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

	}

	@Override
	public void editar(Pneu pneu) {

		StringBuffer sql = new StringBuffer();
		sql.append("update pneus set ");
		sql.append("descricao = ?, marca = ?, tipo = ?, id_forma_de_venda = ?, ");
		sql.append("valor = ?, quantidade = ? ");
		sql.append("where id = ? ");

		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			int i = 0;

			pstm.setString(++i, pneu.getDescricao().toUpperCase());
			pstm.setString(++i, pneu.getMarca().toUpperCase());
			pstm.setString(++i, pneu.getTipo().toUpperCase());
			pstm.setInt(++i, pneu.getFormaDeVenda().getId());
			pstm.setDouble(++i, pneu.getValor());
			pstm.setInt(++i, pneu.getQuantidade());
			pstm.setInt(++i, pneu.getId());
			pstm.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro("Erro ao Editar o Pneu no Banco de Dados.");
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

	}

	@Override
	public Pneu pesquisarPorID(Pneu pneu) {

		StringBuffer sql = new StringBuffer();
		sql.append("select * from pneus ");
		sql.append("where id = ? ");

		Pneu farolRetorno = null;

		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			int i = 0;
			pstm.setInt(++i, pneu.getId());
			ResultSet rSet = pstm.executeQuery();

			while (rSet.next()) {

				farolRetorno = new Pneu();
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
			JSFUtil.adicionarMensagemErro("Erro ao buscar o Pneu no Banco de Dados.");
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

		return farolRetorno;
	}

	@Override
	public List<Pneu> pesquisarPorMarca(Pneu pneu) {

		StringBuffer sql = new StringBuffer();
		sql.append("select * from pneus ");
		sql.append("where marca = ? ");

		List<Pneu> lista = new ArrayList<Pneu>();
		Pneu farolRetorno = null;

		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			int i = 0;
			pstm.setString(++i, pneu.getMarca().toUpperCase());
			ResultSet rSet = pstm.executeQuery();

			while (rSet.next()) {
				farolRetorno = new Pneu();
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
	public List<Pneu> listarTodos() {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from pneus ");
		sql.append("where id <> 99");

		List<Pneu> lista = new ArrayList<Pneu>();
		Pneu farolRetorno = null;

		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			ResultSet rSet = pstm.executeQuery();

			while (rSet.next()) {
				farolRetorno = new Pneu();
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
