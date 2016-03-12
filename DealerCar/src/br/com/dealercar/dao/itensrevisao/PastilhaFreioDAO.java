package br.com.dealercar.dao.itensrevisao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.domain.produtosrevisao.FormaDeVenda;
import br.com.dealercar.domain.produtosrevisao.PastilhaFreio;
import br.com.dealercar.factory.Conexao;
import br.com.dealercar.util.JSFUtil;

/**
 * Classe responsavel por gerenciar as Pastilhas de Freio no Banco
 * @author Paulinho
 *
 */
public class PastilhaFreioDAO extends AbstractPesquisaItensRevisao<PastilhaFreio>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Connection con = null;

	@Override
	public void cadastrar(PastilhaFreio pastilhaFreio) {

		StringBuffer sql = new StringBuffer();
		sql.append("insert into pastilhas_freios ");
		sql.append("(descricao, marca, tipo, id_forma_de_venda, valor, quantidade) ");
		sql.append("values ( ? , ? , ? , ? , ? , ? ) ");
		
		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			int i = 0;
			pstm.setString(++i, pastilhaFreio.getDescricao().toUpperCase());
			pstm.setString(++i, pastilhaFreio.getMarca().toUpperCase());
			pstm.setString(++i, pastilhaFreio.getTipo().toUpperCase());
			pstm.setInt(++i, pastilhaFreio.getFormaDeVenda().getId());
			pstm.setDouble(++i, pastilhaFreio.getValor());
			pstm.setInt(++i, pastilhaFreio.getQuantidade());

			pstm.executeUpdate();

			pstm.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro("Erro ao cadastrar PastilhaFreio no Banco de Dados.");
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

	}

	@Override
	public void excluir(PastilhaFreio pastilhaFreio) {

		StringBuffer sql = new StringBuffer();
		sql.append("delete from pastilhas_freios ");
		sql.append("where id = ? ");
		
		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			int i = 0;
			pstm.setInt(++i, pastilhaFreio.getId());
			pstm.executeUpdate();

			pstm.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro("Erro ao excluir PastilhaFreio no Banco de Dados.");
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

	}

	@Override
	public void editar(PastilhaFreio pastilhaFreio) {

		StringBuffer sql = new StringBuffer();
		sql.append("update pastilhas_freios set ");
		sql.append("descricao = ?, marca = ?, tipo = ?, id_forma_de_venda = ?, ");
		sql.append("valor = ?, quantidade = ? ");
		sql.append("where id = ? ");
		
		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			int i = 0;

			pstm.setString(++i, pastilhaFreio.getDescricao().toUpperCase());
			pstm.setString(++i, pastilhaFreio.getMarca().toUpperCase());
			pstm.setString(++i, pastilhaFreio.getTipo().toUpperCase());
			pstm.setInt(++i, pastilhaFreio.getFormaDeVenda().getId());
			pstm.setDouble(++i, pastilhaFreio.getValor());
			pstm.setInt(++i, pastilhaFreio.getQuantidade());
			pstm.setInt(++i, pastilhaFreio.getId());
			pstm.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro("Erro ao Editar o PastilhaFreio no Banco de Dados.");
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

	}

	@Override
	public PastilhaFreio pesquisarPorID(PastilhaFreio pastilhaFreio) {

		StringBuffer sql = new StringBuffer();
		sql.append("select * from pastilhas_freios ");
		sql.append("where id = ? ");

		PastilhaFreio pastilhaFreioRetorno = null;

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
			pstm.setInt(++i, pastilhaFreio.getId());
			ResultSet rSet = pstm.executeQuery();

			while (rSet.next()) {

				pastilhaFreioRetorno = new PastilhaFreio();
				pastilhaFreioRetorno.setId(rSet.getInt("id"));
				pastilhaFreioRetorno.setDescricao(rSet.getString("descricao"));
				pastilhaFreioRetorno.setTipo(rSet.getString("tipo"));
				pastilhaFreioRetorno.setMarca(rSet.getString("marca"));
				pastilhaFreioRetorno.setValor(rSet.getDouble("valor"));
				pastilhaFreioRetorno.setQuantidade(rSet.getInt("quantidade"));

				FormaDeVenda formaDeVenda = new FormaDeVenda();
				formaDeVenda.setId(rSet.getInt("id_forma_de_venda"));
				formaDeVenda = new FormaDeVendaDAO().pesquisarPorID(formaDeVenda);

				pastilhaFreioRetorno.setFormaDeVenda(formaDeVenda);

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
			JSFUtil.adicionarMensagemErro("Erro ao buscar o PastilhaFreio no Banco de Dados.");
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

		return pastilhaFreioRetorno;
	}

	@Override
	public List<PastilhaFreio> pesquisarPorMarca(PastilhaFreio pastilhaFreio) {

		StringBuffer sql = new StringBuffer();
		sql.append("select * from pastilhas_freios ");
		sql.append("where marca = ? ");

		List<PastilhaFreio> lista = new ArrayList<PastilhaFreio>();
		PastilhaFreio pastilhaFreioRetorno = null;
		
		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			int i = 0;
			pstm.setString(++i, pastilhaFreio.getMarca().toUpperCase());
			ResultSet rSet = pstm.executeQuery();

			while (rSet.next()) {
				pastilhaFreioRetorno = new PastilhaFreio();
				pastilhaFreioRetorno.setId(rSet.getInt("id"));
				pastilhaFreioRetorno.setDescricao(rSet.getString("descricao"));
				pastilhaFreioRetorno.setTipo(rSet.getString("tipo"));
				pastilhaFreioRetorno.setMarca(rSet.getString("marca"));
				pastilhaFreioRetorno.setValor(rSet.getDouble("valor"));
				pastilhaFreioRetorno.setQuantidade(rSet.getInt("quantidade"));

				FormaDeVenda formaDeVenda = new FormaDeVenda();
				formaDeVenda.setId(rSet.getInt("id_forma_de_venda"));
				formaDeVenda = new FormaDeVendaDAO().pesquisarPorID(formaDeVenda);

				pastilhaFreioRetorno.setFormaDeVenda(formaDeVenda);

				lista.add(pastilhaFreioRetorno);

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
	public List<PastilhaFreio> listarTodos() {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from pastilhas_freios ");

		List<PastilhaFreio> lista = new ArrayList<PastilhaFreio>();
		PastilhaFreio pastilhaFreioRetorno = null;
		
		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			ResultSet rSet = pstm.executeQuery();

			while (rSet.next()) {
				pastilhaFreioRetorno = new PastilhaFreio();
				pastilhaFreioRetorno.setId(rSet.getInt("id"));
				pastilhaFreioRetorno.setDescricao(rSet.getString("descricao"));
				pastilhaFreioRetorno.setTipo(rSet.getString("tipo"));
				pastilhaFreioRetorno.setMarca(rSet.getString("marca"));
				pastilhaFreioRetorno.setValor(rSet.getDouble("valor"));
				pastilhaFreioRetorno.setQuantidade(rSet.getInt("quantidade"));

				FormaDeVenda formaDeVenda = new FormaDeVenda();
				formaDeVenda.setId(rSet.getInt("id_forma_de_venda"));
				formaDeVenda = new FormaDeVendaDAO().pesquisarPorID(formaDeVenda);

				pastilhaFreioRetorno.setFormaDeVenda(formaDeVenda);

				lista.add(pastilhaFreioRetorno);

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
