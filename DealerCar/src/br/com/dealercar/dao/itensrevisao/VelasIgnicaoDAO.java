package br.com.dealercar.dao.itensrevisao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.domain.produtosrevisao.VelasIgnicao;
import br.com.dealercar.domain.produtosrevisao.FormaDeVenda;
import br.com.dealercar.factory.Conexao;
import br.com.dealercar.util.JSFUtil;

/**
 * Classe responsavel pelo gerenciamento das Velas de Ignição no Banco
 * @author Paulinho
 *
 */
public class VelasIgnicaoDAO extends AbstractPesquisaItensRevisao<VelasIgnicao>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Connection con = null;

	@Override
	public void cadastrar(VelasIgnicao velasIgnicao) {

		StringBuffer sql = new StringBuffer();
		sql.append("insert into velas_ignicao ");
		sql.append("(descricao, marca, tipo, id_forma_de_venda, valor, quantidade) ");
		sql.append("values ( ? , ? , ? , ? , ? , ? ) ");
		
		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			int i = 0;
			pstm.setString(++i, velasIgnicao.getDescricao());
			pstm.setString(++i, velasIgnicao.getMarca());
			pstm.setString(++i, velasIgnicao.getTipo());
			pstm.setInt(++i, velasIgnicao.getFormaDeVenda().getId());
			pstm.setDouble(++i, velasIgnicao.getValor());
			pstm.setInt(++i, velasIgnicao.getQuantidade());

			pstm.executeUpdate();

			pstm.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro("Erro ao cadastrar VelasIgnicao no Banco de Dados.");
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

	}

	@Override
	public void excluir(VelasIgnicao velasIgnicao) {

		StringBuffer sql = new StringBuffer();
		sql.append("delete from velas_ignicao ");
		sql.append("where id = ? ");
		
		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			int i = 0;
			pstm.setInt(++i, velasIgnicao.getId());
			pstm.executeUpdate();

			pstm.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro("Erro ao excluir VelasIgnicao no Banco de Dados.");
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

	}

	@Override
	public void editar(VelasIgnicao velasIgnicao) {

		StringBuffer sql = new StringBuffer();
		sql.append("update velas_ignicao set ");
		sql.append("descricao = ?, marca = ?, tipo = ?, id_forma_de_venda = ?, ");
		sql.append("valor = ?, quantidade = ? ");
		sql.append("where id = ? ");
		
		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			int i = 0;

			pstm.setString(++i, velasIgnicao.getDescricao());
			pstm.setString(++i, velasIgnicao.getMarca());
			pstm.setString(++i, velasIgnicao.getTipo());
			pstm.setInt(++i, velasIgnicao.getFormaDeVenda().getId());
			pstm.setDouble(++i, velasIgnicao.getValor());
			pstm.setInt(++i, velasIgnicao.getQuantidade());
			pstm.setInt(++i, velasIgnicao.getId());
			pstm.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro("Erro ao Editar o VelasIgnicao no Banco de Dados.");
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

	}

	@Override
	public VelasIgnicao pesquisarPorID(VelasIgnicao velasIgnicao) {

		StringBuffer sql = new StringBuffer();
		sql.append("select * from velas_ignicao ");
		sql.append("where id = ? ");

		VelasIgnicao velasIgnicaoRetorno = null;

		boolean flagConnetionWasActive = true; // true = metodo foi chamado de
												// outro DAO

		/**
		 * Verifica se a con esta nula, se SIM - metodo nao teve origem de outro
		 * DAO
		 */
		if (con == null) {
			con = Conexao.getConnection();
			flagConnetionWasActive = false;
		}

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			int i = 0;
			pstm.setInt(++i, velasIgnicao.getId());
			ResultSet rSet = pstm.executeQuery();

			while (rSet.next()) {

				velasIgnicaoRetorno = new VelasIgnicao();
				velasIgnicaoRetorno.setId(rSet.getInt("id"));
				velasIgnicaoRetorno.setDescricao(rSet.getString("descricao"));
				velasIgnicaoRetorno.setTipo(rSet.getString("tipo"));
				velasIgnicaoRetorno.setMarca(rSet.getString("marca"));
				velasIgnicaoRetorno.setValor(rSet.getDouble("valor"));
				velasIgnicaoRetorno.setQuantidade(rSet.getInt("quantidade"));

				FormaDeVenda formaDeVenda = new FormaDeVenda();
				formaDeVenda.setId(rSet.getInt("id_forma_de_venda"));
				formaDeVenda = new FormaDeVendaDAO().pesquisarPorID(formaDeVenda);

				velasIgnicaoRetorno.setFormaDeVenda(formaDeVenda);

			}

			/**
			 * Se flagConnectionWasActive = true a connection será fechada no
			 * DAO de chamador
			 */
			if (flagConnetionWasActive == false) {
				rSet.close();
				pstm.close();
				con.close();
			}

		} catch (SQLException e) {
			e.printStackTrace();
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro("Erro ao buscar o VelasIgnicao no Banco de Dados.");
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

		return velasIgnicaoRetorno;
	}

	@Override
	public List<VelasIgnicao> pesquisarPorMarca(VelasIgnicao velasIgnicao) {

		StringBuffer sql = new StringBuffer();
		sql.append("select * from velas_ignicao ");
		sql.append("where marca = ? ");

		List<VelasIgnicao> lista = new ArrayList<VelasIgnicao>();
		VelasIgnicao velasIgnicaoRetorno = null;
		
		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			int i = 0;
			pstm.setString(++i, velasIgnicao.getMarca());
			ResultSet rSet = pstm.executeQuery();

			while (rSet.next()) {
				velasIgnicaoRetorno = new VelasIgnicao();
				velasIgnicaoRetorno.setId(rSet.getInt("id"));
				velasIgnicaoRetorno.setDescricao(rSet.getString("descricao"));
				velasIgnicaoRetorno.setTipo(rSet.getString("tipo"));
				velasIgnicaoRetorno.setMarca(rSet.getString("marca"));
				velasIgnicaoRetorno.setValor(rSet.getDouble("valor"));
				velasIgnicaoRetorno.setQuantidade(rSet.getInt("quantidade"));

				FormaDeVenda formaDeVenda = new FormaDeVenda();
				formaDeVenda.setId(rSet.getInt("id_forma_de_venda"));
				formaDeVenda = new FormaDeVendaDAO().pesquisarPorID(formaDeVenda);

				velasIgnicaoRetorno.setFormaDeVenda(formaDeVenda);

				lista.add(velasIgnicaoRetorno);

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
	public List<VelasIgnicao> listarTodos() {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from velas_ignicao ");

		List<VelasIgnicao> lista = new ArrayList<VelasIgnicao>();
		VelasIgnicao velasIgnicaoRetorno = null;
		
		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			ResultSet rSet = pstm.executeQuery();

			while (rSet.next()) {
				velasIgnicaoRetorno = new VelasIgnicao();
				velasIgnicaoRetorno.setId(rSet.getInt("id"));
				velasIgnicaoRetorno.setDescricao(rSet.getString("descricao"));
				velasIgnicaoRetorno.setTipo(rSet.getString("tipo"));
				velasIgnicaoRetorno.setMarca(rSet.getString("marca"));
				velasIgnicaoRetorno.setValor(rSet.getDouble("valor"));
				velasIgnicaoRetorno.setQuantidade(rSet.getInt("quantidade"));

				FormaDeVenda formaDeVenda = new FormaDeVenda();
				formaDeVenda.setId(rSet.getInt("id_forma_de_venda"));
				formaDeVenda = new FormaDeVendaDAO().pesquisarPorID(formaDeVenda);

				velasIgnicaoRetorno.setFormaDeVenda(formaDeVenda);

				lista.add(velasIgnicaoRetorno);

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
