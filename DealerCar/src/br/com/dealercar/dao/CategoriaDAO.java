package br.com.dealercar.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.domain.Categoria;
import br.com.dealercar.factory.Conexao;
import br.com.dealercar.util.JSFUtil;

public class CategoriaDAO {

	public void cadastrar(Categoria categoria) {
		String sql = "insert into categorias (nome, descricao, vlr_diaria) values (?, ?, ?)";

		Connection con = Conexao.getConnection();

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

	public void editar(Categoria categoria) {

		String sql = "update categorias set nome = ?, descricao = ?, vlr_diaria = ? where id = ? ";

		Connection con = Conexao.getConnection();

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

	public void excluir(Categoria categoria) {

		String sql = "delete from categorias where id = ?";

		Connection con = Conexao.getConnection();

		try {

			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, categoria.getId());
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}

	public Categoria pesquisarPorID(Categoria categoria) {

		String sql = "select * from categorias where id = ?";

		Categoria categoriaRetorno = null;

		Connection con = Conexao.getConnection();

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

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

		return categoriaRetorno;

	}

	public List<Categoria> listarTodos() {
		String sql = "select * from categorias";

		List<Categoria> listaRetorno = new ArrayList<Categoria>();

		Connection con = Conexao.getConnection();

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

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

		return listaRetorno;
	}

}
