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
import br.com.dealercar.domain.produtosrevisao.VelasIgnicao;

/**
 * Classe responsavel pelo gerenciamento das Velas de Ignição no Banco
 * 
 * @author Paulinho
 *
 */
public class VelasIgnicaoDAO extends AbstractPesquisaItensRevisao<VelasIgnicao> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Connection con = null;

	@Override
	public void cadastrar(EntidadeDominio entidade) {
		
		if(!(entidade instanceof VelasIgnicao))
			return;
		
		VelasIgnicao velasIgnicao = new VelasIgnicao();
		velasIgnicao = (VelasIgnicao) entidade;

		StringBuffer sql = new StringBuffer();
		sql.append("insert into velas_ignicao ");
		sql.append("(descricao, marca, tipo, id_forma_de_venda, valor, quantidade) ");
		sql.append("values ( ? , ? , ? , ? , ? , ? ) ");

		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			int i = 0;
			pstm.setString(++i, velasIgnicao.getDescricao().toUpperCase());
			pstm.setString(++i, velasIgnicao.getMarca().toUpperCase());
			pstm.setString(++i, velasIgnicao.getTipo().toUpperCase());
			pstm.setInt(++i, velasIgnicao.getFormaDeVenda().getId());
			pstm.setDouble(++i, velasIgnicao.getValor());
			pstm.setInt(++i, velasIgnicao.getQuantidade());

			pstm.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro("Erro ao cadastrar VelasIgnicao no Banco de Dados.");
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

	}

	@Override
	public void excluir(EntidadeDominio entidade) {
		
		if(!(entidade instanceof VelasIgnicao))
			return;
		
		VelasIgnicao velasIgnicao = new VelasIgnicao();
		velasIgnicao = (VelasIgnicao) entidade;

		StringBuffer sql = new StringBuffer();
		sql.append("delete from velas_ignicao ");
		sql.append("where id = ? ");

		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			int i = 0;
			pstm.setInt(++i, velasIgnicao.getId());
			pstm.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro("Erro ao excluir VelasIgnicao no Banco de Dados.");
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

	}

	@Override
	public void editar(EntidadeDominio entidade) {
		
		if(!(entidade instanceof VelasIgnicao))
			return;
		
		VelasIgnicao velasIgnicao = new VelasIgnicao();
		velasIgnicao = (VelasIgnicao) entidade;

		StringBuffer sql = new StringBuffer();
		sql.append("update velas_ignicao set ");
		sql.append("descricao = ?, marca = ?, tipo = ?, id_forma_de_venda = ?, ");
		sql.append("valor = ?, quantidade = ? ");
		sql.append("where id = ? ");

		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			int i = 0;

			pstm.setString(++i, velasIgnicao.getDescricao().toUpperCase());
			pstm.setString(++i, velasIgnicao.getMarca().toUpperCase());
			pstm.setString(++i, velasIgnicao.getTipo().toUpperCase());
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
	public VelasIgnicao pesquisarPorID(EntidadeDominio entidade) {
		
		if(!(entidade instanceof VelasIgnicao))
			return null;
		
		VelasIgnicao velasIgnicao = new VelasIgnicao();
		velasIgnicao = (VelasIgnicao) entidade;

		StringBuffer sql = new StringBuffer();
		sql.append("select * from velas_ignicao ");
		sql.append("where id = ? ");

		VelasIgnicao velasIgnicaoRetorno = null;

		con = Conexao.getConnection();

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
			pstm.setString(++i, velasIgnicao.getMarca().toUpperCase());
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


		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lista;
	}

	@Override
	public List<EntidadeDominio> listarTodos() {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from velas_ignicao ");
		sql.append("where id <> 99");

		List<EntidadeDominio> lista = new ArrayList<EntidadeDominio>();
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

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lista;
	}

	@Override
	public VelasIgnicao pesquisarPorDescricaoMarcaTipo(String produtoRevisao) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from velas_ignicao ");
		sql.append("where descricao = ? and ");
		sql.append("marca = ? and ");
		sql.append("tipo = ? ");

		String[] arrayString = produtoRevisao.split(" - ");
				
		VelasIgnicao velasIgnicaoRetorno = null;

		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			int i = 0;
			pstm.setString(++i, arrayString[0]);
			pstm.setString(++i, arrayString[1]);
			pstm.setString(++i, arrayString[2]);
			
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


		} catch (SQLException e) {
			e.printStackTrace();
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro("Erro ao buscar Produto da Revisao no Banco de Dados.");
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

		return velasIgnicaoRetorno;
	}

}
