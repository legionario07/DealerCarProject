package br.com.dealercar.dao.automotivos;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.dao.IDAO;
import br.com.dealercar.domain.automotivos.Categoria;
import br.com.dealercar.util.JSFUtil;

/**
 * Classe responsavel por realizar a Persistencia da Categoria no BD
 * @author Paulinho
 *
 */
public class CategoriaDAO implements IDAO<Categoria>, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param categoria Recebe um objeto de Categoria e cadastra no BD
	 */
	public void cadastrar(Categoria categoria) {
		String sql = "insert into categorias (nome, descricao, vlr_diaria) values (?, ?, ?)";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, categoria.getNome());
			ps.setString(2, categoria.getDescricao());
			ps.setDouble(3, categoria.getValorDiaria());
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}

	/**
	 * 
	 * @param categoria Recebe um objeto de Categoria e edita seus dados no BD
	 */
	public void editar(Categoria categoria) {

		String sql = "update categorias set nome = ?, descricao = ?, vlr_diaria = ? where id = ? ";
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, categoria.getNome());
			ps.setString(2, categoria.getDescricao());
			ps.setDouble(3, categoria.getValorDiaria());
			ps.setInt(4, categoria.getId());
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

	}

	/**
	 * 
	 * @param categoria Recebe um objeto de Categoria e exclui do BD de acordo com o ID
	 */
	public void excluir(Categoria categoria) {

		String sql = "delete from categorias where id = ?";

		try {

			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, categoria.getId());
			ps.executeUpdate();

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
	public Categoria pesquisarPorID(Categoria categoria) {

		String sql = "select * from categorias where id = ?";

		Categoria categoriaRetorno = null;

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, categoria.getId());

			ResultSet rSet = ps.executeQuery();

			while (rSet.next()) {
				categoriaRetorno = new Categoria();
				categoriaRetorno.setId(rSet.getInt("id"));
				categoriaRetorno.setNome(rSet.getString("nome"));
				categoriaRetorno.setDescricao(rSet.getString("descricao"));
				categoriaRetorno.setValorDiaria(rSet.getDouble("vlr_diaria"));
			}
			
			rSet.close();

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
	public List<Categoria> listarTodos() {
		String sql = "select * from categorias";

		List<Categoria> listaRetorno = new ArrayList<Categoria>();

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rSet = ps.executeQuery();
			
			while(rSet.next()){
				Categoria categoriaRetorno = new Categoria();
				categoriaRetorno.setId(rSet.getInt("id"));
				categoriaRetorno.setNome(rSet.getString("nome"));
				categoriaRetorno.setDescricao(rSet.getString("descricao"));
				categoriaRetorno.setValorDiaria(rSet.getDouble("vlr_diaria"));
				
				listaRetorno.add(categoriaRetorno);
				
			}
			
			rSet.close();

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

		return listaRetorno;
	}

}
