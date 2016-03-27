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
import br.com.dealercar.domain.produtosrevisao.Embreagem;
import br.com.dealercar.domain.produtosrevisao.FormaDeVenda;

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
	public void cadastrar(EntidadeDominio entidade) {
		
		if(!(entidade instanceof Embreagem))
			return;
		
		Embreagem embreagem = new Embreagem();
		embreagem = (Embreagem) entidade;
		

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

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro("Erro ao cadastrar Embreagem no Banco de Dados.");
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

	}

	@Override
	public void excluir(EntidadeDominio entidade) {
		
		if(!(entidade instanceof Embreagem))
			return;
		
		Embreagem embreagem = new Embreagem();
		embreagem = (Embreagem) entidade;

		StringBuffer sql = new StringBuffer();
		sql.append("delete from embreagem ");
		sql.append("where id = ? ");

		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			int i = 0;
			pstm.setInt(++i, embreagem.getId());
			pstm.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro("Erro ao excluir Embreagem no Banco de Dados.");
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

	}

	@Override
	public void editar(EntidadeDominio entidade) {
		
		if(!(entidade instanceof Embreagem))
			return;
		
		Embreagem embreagem = new Embreagem();
		embreagem = (Embreagem) entidade;

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
	public Embreagem pesquisarPorID(EntidadeDominio entidade) {
		
		if(!(entidade instanceof Embreagem))
			return null;
		
		Embreagem embreagem = new Embreagem();
		embreagem = (Embreagem) entidade;

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

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lista;
	}

	@Override
	public List<EntidadeDominio> listarTodos() {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from embreagem ");
		sql.append("where id <> 99");

		List<EntidadeDominio> lista = new ArrayList<EntidadeDominio>();
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

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lista;
	}

	@Override
	public Embreagem pesquisarPorDescricaoMarcaTipo(String produtoRevisao) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from embreagem ");
		sql.append("where descricao = ? and ");
		sql.append("marca = ? and ");
		sql.append("tipo = ? ");

		String[] arrayString = produtoRevisao.split(" - ");
				
		Embreagem embreagemRetorno = null;

		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			int i = 0;
			pstm.setString(++i, arrayString[0]);
			pstm.setString(++i, arrayString[1]);
			pstm.setString(++i, arrayString[2]);
			
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


		} catch (SQLException e) {
			e.printStackTrace();
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro("Erro ao buscar Produto da Revisao no Banco de Dados.");
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

		return embreagemRetorno;
	}
}
