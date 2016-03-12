package br.com.dealercar.dao.itensrevisao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.domain.produtosrevisao.Embreagem;
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
public class EmbreagemDAO extends AbstractPesquisaItensRevisao<Embreagem> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Connection con = null;

	@Override
	public void cadastrar(Embreagem embreagem) {

		StringBuffer sql = new StringBuffer();
		sql.append("insert into embreagem ");
		sql.append("(descricao, marca, tipo, id_forma_de_venda, valor, quantidade) ");
		sql.append("values ( ? , ? , ? , ? , ? , ? ) ");

		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			int i = 0;
			pstm.setString(++i, embreagem.getDescricao().toUpperCase());
			pstm.setString(++i, embreagem.getMarca().toUpperCase());
			pstm.setString(++i, embreagem.getTipo().toUpperCase());
			pstm.setInt(++i, embreagem.getFormaDeVenda().getId());
			pstm.setDouble(++i, embreagem.getValor());
			pstm.setInt(++i, embreagem.getQuantidade());

			pstm.executeUpdate();

			pstm.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro("Erro ao cadastrar Embreagem no Banco de Dados.");
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

	}

	@Override
	public void excluir(Embreagem embreagem) {

		StringBuffer sql = new StringBuffer();
		sql.append("delete from embreagem ");
		sql.append("where id = ? ");

		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			int i = 0;
			pstm.setInt(++i, embreagem.getId());
			pstm.executeUpdate();

			pstm.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro("Erro ao excluir Embreagem no Banco de Dados.");
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

	}

	@Override
	public void editar(Embreagem embreagem) {

		StringBuffer sql = new StringBuffer();
		sql.append("update embreagem set ");
		sql.append("descricao = ?, marca = ?, tipo = ?, id_forma_de_venda = ?, ");
		sql.append("valor = ?, quantidade = ? ");
		sql.append("where id = ? ");

		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			int i = 0;

			pstm.setString(++i, embreagem.getDescricao().toUpperCase());
			pstm.setString(++i, embreagem.getMarca().toUpperCase());
			pstm.setString(++i, embreagem.getTipo().toUpperCase());
			pstm.setInt(++i, embreagem.getFormaDeVenda().getId());
			pstm.setDouble(++i, embreagem.getValor());
			pstm.setInt(++i, embreagem.getQuantidade());
			pstm.setInt(++i, embreagem.getId());
			pstm.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro("Erro ao Editar a Embreagem no Banco de Dados.");
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

	}

	@Override
	public Embreagem pesquisarPorID(Embreagem embreagem) {

		StringBuffer sql = new StringBuffer();
		sql.append("select * from embreagem ");
		sql.append("where id = ? ");

		Embreagem embreagemRetorno = null;

		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			int i = 0;
			pstm.setInt(++i, embreagem.getId());
			ResultSet rSet = pstm.executeQuery();

			while (rSet.next()) {

				embreagemRetorno = new Embreagem();
				embreagemRetorno.setId(rSet.getInt("id"));
				embreagemRetorno.setDescricao(rSet.getString("descricao"));
				embreagemRetorno.setTipo(rSet.getString("tipo"));
				embreagemRetorno.setMarca(rSet.getString("marca"));
				embreagemRetorno.setValor(rSet.getDouble("valor"));
				embreagemRetorno.setQuantidade(rSet.getInt("quantidade"));

				FormaDeVenda formaDeVenda = new FormaDeVenda();
				formaDeVenda.setId(rSet.getInt("id_forma_de_venda"));
				formaDeVenda = new FormaDeVendaDAO().pesquisarPorID(formaDeVenda);

				embreagemRetorno.setFormaDeVenda(formaDeVenda);

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
			JSFUtil.adicionarMensagemErro("Erro ao buscar a Embreagem no Banco de Dados.");
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

		return embreagemRetorno;
	}

	@Override
	public List<Embreagem> pesquisarPorMarca(Embreagem embreagem) {

		StringBuffer sql = new StringBuffer();
		sql.append("select * from embreagem ");
		sql.append("where marca = ? ");

		List<Embreagem> lista = new ArrayList<Embreagem>();
		Embreagem embreagemRetorno = null;

		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			int i = 0;
			pstm.setString(++i, embreagem.getMarca().toUpperCase());
			ResultSet rSet = pstm.executeQuery();

			while (rSet.next()) {
				embreagemRetorno = new Embreagem();
				embreagemRetorno.setId(rSet.getInt("id"));
				embreagemRetorno.setDescricao(rSet.getString("descricao"));
				embreagemRetorno.setTipo(rSet.getString("tipo"));
				embreagemRetorno.setMarca(rSet.getString("marca"));
				embreagemRetorno.setValor(rSet.getDouble("valor"));
				embreagemRetorno.setQuantidade(rSet.getInt("quantidade"));

				FormaDeVenda formaDeVenda = new FormaDeVenda();
				formaDeVenda.setId(rSet.getInt("id_forma_de_venda"));
				formaDeVenda = new FormaDeVendaDAO().pesquisarPorID(formaDeVenda);

				embreagemRetorno.setFormaDeVenda(formaDeVenda);

				lista.add(embreagemRetorno);

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
	public List<Embreagem> listarTodos() {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from embreagem ");

		List<Embreagem> lista = new ArrayList<Embreagem>();
		Embreagem embreagemRetorno = null;

		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			ResultSet rSet = pstm.executeQuery();

			while (rSet.next()) {
				embreagemRetorno = new Embreagem();
				embreagemRetorno.setId(rSet.getInt("id"));
				embreagemRetorno.setDescricao(rSet.getString("descricao"));
				embreagemRetorno.setTipo(rSet.getString("tipo"));
				embreagemRetorno.setMarca(rSet.getString("marca"));
				embreagemRetorno.setValor(rSet.getDouble("valor"));
				embreagemRetorno.setQuantidade(rSet.getInt("quantidade"));

				FormaDeVenda formaDeVenda = new FormaDeVenda();
				formaDeVenda.setId(rSet.getInt("id_forma_de_venda"));
				formaDeVenda = new FormaDeVendaDAO().pesquisarPorID(formaDeVenda);

				embreagemRetorno.setFormaDeVenda(formaDeVenda);

				lista.add(embreagemRetorno);

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
