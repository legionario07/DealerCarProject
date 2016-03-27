package br.com.dealercar.core.dao.itensrevisao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.core.factory.Conexao;
import br.com.dealercar.core.util.JSFUtil;
import br.com.dealercar.domain.EntidadeDominio;
import br.com.dealercar.domain.produtosrevisao.FormaDeVenda;

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
	public void cadastrar(EntidadeDominio entidade) {
		
		if(!(entidade instanceof FormaDeVenda))
			return;
		
		FormaDeVenda formaDeVenda = new FormaDeVenda();
		formaDeVenda = (FormaDeVenda)  entidade;

		StringBuffer sql = new StringBuffer();
		sql.append("insert into forma_de_venda ");
		sql.append("(descricao) values (?) ");

		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			int i = 0;
			pstm.setString(++i, formaDeVenda.getDescricao().toUpperCase());
			pstm.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

	}

	@Override
	public void excluir(EntidadeDominio entidade) {
		
		if(!(entidade instanceof FormaDeVenda))
			return;
		
		FormaDeVenda formaDeVenda = new FormaDeVenda();
		formaDeVenda = (FormaDeVenda)  entidade;

		StringBuffer sql = new StringBuffer();
		sql.append("delete from forma_de_venda ");
		sql.append("where id = ? ");

		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			int i = 0;
			pstm.setInt(++i, formaDeVenda.getId());
			pstm.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

	}

	@Override
	public void editar(EntidadeDominio entidade) {
		
		if(!(entidade instanceof FormaDeVenda))
			return;
		
		FormaDeVenda formaDeVenda = new FormaDeVenda();
		formaDeVenda = (FormaDeVenda)  entidade;

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

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

	}

	@Override
	public List<EntidadeDominio> listarTodos() {

		StringBuffer sql = new StringBuffer();
		sql.append("select * from forma_de_venda ");

		List<EntidadeDominio> lista = new ArrayList<EntidadeDominio>();

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


		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

		return lista;
	}

	@Override
	public FormaDeVenda pesquisarPorID(EntidadeDominio entidade) {
		
		if(!(entidade instanceof FormaDeVenda))
			return null;
		
		FormaDeVenda formaDeVenda = new FormaDeVenda();
		formaDeVenda = (FormaDeVenda)  entidade;

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

	@Override
	public FormaDeVenda pesquisarPorDescricaoMarcaTipo(String produtoRevisao) {
		// TODO Auto-generated method stub
		return null;
	}

}
