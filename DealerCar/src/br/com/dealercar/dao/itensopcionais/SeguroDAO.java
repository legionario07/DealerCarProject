package br.com.dealercar.dao.itensopcionais;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.dao.AbstractPesquisaItensOpcionais;
import br.com.dealercar.domain.itensopcionais.Seguro;
import br.com.dealercar.domain.itensopcionais.TipoSeguro;
import br.com.dealercar.factory.Conexao;
import br.com.dealercar.util.JSFUtil;

/**
 * REaliza a persistencia dos ITEM OPCIONAL Seguro
 * @author Paulinho
 *
 */
public class SeguroDAO extends AbstractPesquisaItensOpcionais<Seguro> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Connection con = Conexao.getConnection();


	/**
	 * 
	 * @pstmeguroam Seguro Recebe um Seguro e cadastra no Banco de Dados
	 */
	public void cadastrar(Seguro seguro) {

		StringBuffer sql = new StringBuffer();
		sql.append("insert into seguros (descricao, valor, tipo_seguro) ");
		sql.append("values (?, ?, ?)");
		
		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			pstm.setString(1, seguro.getDescricao());
			pstm.setDouble(2, seguro.getValor());
			pstm.setInt(3, seguro.getTipoSeguro().getId());
			pstm.executeUpdate();

			pstm.close();
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}

	/**
	 * 
	 * @pstmeguroam Seguro Recebe um Seguro e exclui-o do Banco de Dados
	 *            procurando-o pelo codigo
	 */
	public void excluir(Seguro seguro) {

		StringBuffer sql = new StringBuffer();
		sql.append("delete from seguros where codigo = ?");
		
		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			pstm.setInt(1, seguro.getCodigo());
			pstm.executeUpdate();
			
			pstm.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

	}

	/**
	 * 
	 * @pstmeguroam Seguro Recebe um Seguro e edita-o no Banco de Dados
	 *            Localizando-o pelo seu Codigo
	 */
	public void editar(Seguro seguro) {

		StringBuffer sql = new StringBuffer();
		sql.append("update seguros set descricao = ?, ");
		sql.append("valor = ?, tipo_seguro = ? where codigo = ?");
		
		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			pstm.setString(1, seguro.getDescricao());
			pstm.setDouble(2, seguro.getValor());
			pstm.setInt(3, seguro.getTipoSeguro().getId());
			pstm.setInt(4, seguro.getCodigo());

			pstm.executeUpdate();
			
			pstm.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}

	/**
	 * 
	 * @return Lista todos os ArCondionados do Banco de Dados e retorna um
	 *         objeto de Seguro
	 */
	public List<Seguro> listarTodos() {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from seguros ");
		sql.append("order by descricao asc");

		List<Seguro> listaRetorno = new ArrayList<Seguro>();
		
		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			ResultSet rSet = pstm.executeQuery();

			while (rSet.next()) {
				Seguro seguroRetorno = new Seguro();
				seguroRetorno.setCodigo(rSet.getInt("codigo"));
				seguroRetorno.setDescricao(rSet.getString("descricao"));

				TipoSeguro tipoSeguro = new TipoSeguro(rSet.getInt("tipo_seguro"));
				tipoSeguro = new TipoSeguroDAO().pesquisarPorID(tipoSeguro);

				seguroRetorno.setTipoSeguro(tipoSeguro);
				seguroRetorno.setValor(rSet.getDouble("valor"));

				listaRetorno.add(seguroRetorno);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

		return listaRetorno;
	}

	/**
	 * 
	 * @pstmeguroam Seguro Recebe um Objeto Seguro e localiza no Banco de Dados
	 *            pelo Codigo
	 * @return Retorna um objeto de Seguro
	 */
	@Override
	public Seguro pesquisarPorCodigo(Seguro seguro) {

		StringBuffer sql = new StringBuffer();
		sql.append("select * from seguros ");
		sql.append("where seguros.codigo = ?");

		Seguro seguroRetorno = null;
		
		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			pstm.setInt(1, seguro.getCodigo());
			
			ResultSet rSet = pstm.executeQuery();

			while (rSet.next()) {
				seguroRetorno = new Seguro();
				seguroRetorno.setCodigo(rSet.getInt("codigo"));
				seguroRetorno.setDescricao(rSet.getString("descricao"));

				TipoSeguro tipoSeguro = new TipoSeguro(rSet.getInt("tipo_seguro"));
				tipoSeguro = new TipoSeguroDAO().pesquisarPorID(tipoSeguro);

				seguroRetorno.setTipoSeguro(tipoSeguro);
				seguroRetorno.setValor(rSet.getDouble("valor"));
				
			}
			

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

		return seguroRetorno;
	}

	@Override
	public Seguro pesquisarPorID(Seguro entidade) {
		// TODO Auto-generated method stub
		return null;
	}


	/**
	 * 
	 * @return Lista todos os ArCondionados do Banco de Dados e retorna um
	 *         objeto de Seguro
	 */
	public List<Seguro> listarApenasNomesDiferentes() {
		
		StringBuffer sql = new StringBuffer();
		sql.append("select distinct seguros.codigo, seguros.descricao, seguros.tipo_seguro "); 
		sql.append("from seguros inner join tipo_seguro on tipo_seguro.id = seguros.tipo_seguro ");
		sql.append("where seguros.tipo_seguro=1 "); 
		sql.append("order by seguros.descricao asc"); 
		
		List<Seguro> listaRetorno = new ArrayList<Seguro>();
		
		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			ResultSet rSet = pstm.executeQuery();

			while (rSet.next()) {
				
				Seguro seguro = new Seguro();
				seguro.setCodigo(rSet.getInt("seguros.codigo"));
				seguro.setDescricao(rSet.getString("seguros.descricao"));

				TipoSeguro tipoSeguro = new TipoSeguro();

				tipoSeguro.setId(rSet.getInt("seguros.tipo_seguro"));
				
				tipoSeguro = new TipoSeguroDAO().pesquisarPorID(tipoSeguro);
				
				seguro.setTipoSeguro(tipoSeguro);

				listaRetorno.add(seguro);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

		return listaRetorno;
	}

	

}
