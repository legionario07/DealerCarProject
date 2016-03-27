package br.com.dealercar.core.dao.automotivos;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.core.dao.IDAO;
import br.com.dealercar.core.factory.Conexao;
import br.com.dealercar.core.util.JSFUtil;
import br.com.dealercar.domain.EntidadeDominio;
import br.com.dealercar.domain.automotivos.Categoria;

/**
 * Classe responsavel por realizar a Persistencia da Categoria no BD
 * @author Paulinho
 *
 */
public class CategoriaDAO implements IDAO, Serializable{
	
	private Connection con = null;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param categoria Recebe um objeto de Categoria e cadastra no BD
	 */
	public void cadastrar(EntidadeDominio entidade) {
		
		if(!(entidade instanceof Categoria))
			return;
		
		Categoria categoria = new Categoria();
		categoria = (Categoria) entidade;
		
		StringBuffer sql = new StringBuffer();
		sql.append("insert into categorias ");
		sql.append("(nome, descricao, vlr_diaria) values (?, ?, ?)");
		
		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			pstm.setString(1, categoria.getNome());
			pstm.setString(2, categoria.getDescricao());
			pstm.setDouble(3, categoria.getValorDiaria());
			pstm.executeUpdate();
			

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}

	/**
	 * 
	 * @param categoria Recebe um objeto de Categoria e edita seus dados no BD
	 */
	public void editar(EntidadeDominio entidade) {
		
		if(!(entidade instanceof Categoria))
			return;
		
		Categoria categoria = new Categoria();
		categoria = (Categoria) entidade;

		StringBuffer sql = new StringBuffer();
		sql.append("update categorias set nome = ?, ");
		sql.append("descricao = ?, vlr_diaria = ? ");
		sql.append("where id = ? ");
		
		con = Conexao.getConnection();
		
		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			pstm.setString(1, categoria.getNome());
			pstm.setString(2, categoria.getDescricao());
			pstm.setDouble(3, categoria.getValorDiaria());
			pstm.setInt(4, categoria.getId());
			pstm.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

	}

	/**
	 * 
	 * @param categoria Recebe um objeto de Categoria e exclui do BD de acordo com o ID
	 */
	public void excluir(EntidadeDominio entidade) {
		
		if(!(entidade instanceof Categoria))
			return;
		
		Categoria categoria = new Categoria();
		categoria = (Categoria) entidade;

		StringBuffer sql = new StringBuffer();
		sql.append("delete from categorias where id = ?");

		con = Conexao.getConnection();
		
		try {

			PreparedStatement pstm = con.prepareStatement(sql.toString());
			pstm.setInt(1, categoria.getId());
			pstm.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}

	/**
	 * 
	 * @param categoria Recebe um objeto de Categoria e pesquisa no BD
	 *  localizando por seu ID
	 * @return Retorna um objeto de Categoria
	 */
	public Categoria pesquisarPorID(EntidadeDominio entidade) {

		if(!(entidade instanceof Categoria))
			return null;
		
		Categoria categoria = new Categoria();
		categoria = (Categoria) entidade;
		
		StringBuffer sql = new StringBuffer();
		sql.append("select * from categorias where id = ?");

		Categoria categoriaRetorno = null;
		
		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			pstm.setInt(1, categoria.getId());

			ResultSet rSet = pstm.executeQuery();

			while (rSet.next()) {
				categoriaRetorno = new Categoria();
				categoriaRetorno.setId(rSet.getInt("id"));
				categoriaRetorno.setNome(rSet.getString("nome"));
				categoriaRetorno.setDescricao(rSet.getString("descricao"));
				categoriaRetorno.setValorDiaria(rSet.getDouble("vlr_diaria"));
			}
			

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

		return categoriaRetorno;

	}

	/**
	 * 
	 * @return Retorna todas as Categorias cadastradas no BD em forma de List<Categoria>
	 */
	public List<EntidadeDominio> listarTodos() {
		
		StringBuffer sql = new StringBuffer();
		sql.append("select * from categorias");

		List<EntidadeDominio> listaRetorno = new ArrayList<EntidadeDominio>();
		
		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			ResultSet rSet = pstm.executeQuery();
			
			while(rSet.next()){
				Categoria categoriaRetorno = new Categoria();
				categoriaRetorno.setId(rSet.getInt("id"));
				categoriaRetorno.setNome(rSet.getString("nome"));
				categoriaRetorno.setDescricao(rSet.getString("descricao"));
				categoriaRetorno.setValorDiaria(rSet.getDouble("vlr_diaria"));
				
				listaRetorno.add(categoriaRetorno);
				
			}
			

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

		return listaRetorno;
	}

}
