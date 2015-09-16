package br.com.dealercar.dao.automotivos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.dao.IDAO;
import br.com.dealercar.domain.automotivos.ImagemCarro;
import br.com.dealercar.factory.Conexao;
import br.com.dealercar.util.JSFUtil;

public class ImagemCarroDAO implements IDAO<ImagemCarro> {

	/**
	 * 
	 * @param carroUrl Recebe um Objeto de ImagemCarro e cadastra no BD
	 */
	public void cadastrar(ImagemCarro carroUrl) {
		String sql = "insert into carros_images (caminho, descricao) values (?, ?)";
		
		Connection con = Conexao.getConnection();
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, carroUrl.getCaminho());
			ps.setString(2, carroUrl.getDescricao());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
		
	}
	
	/**
	 * 
	 * @param carroUrl Recebe um objeto de ImagemCarro e edita seus dados no Bd
	 * localizando por seu ID
	 */
	public void editar(ImagemCarro carroUrl) {
		String sql = "update carros_images set caminho = ? , descricao = ? where id = ?";
		
		Connection con = Conexao.getConnection();
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, carroUrl.getCaminho());
			ps.setString(2, carroUrl.getDescricao());
			ps.setInt(3, carroUrl.getId());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @param carroUrl Recebe um objeto de ImagemCarro
	 * e exclui do BD localiando por seu ID
	 */
	public void excluir(ImagemCarro carroUrl) {
		String sql = "delete from carros_images where id = ?";
		
		Connection con = Conexao.getConnection();
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, carroUrl.getId());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
		
	}
	
	/**
	 * 
	 * @param carroUrl Recebe um objeto de ImagemCarro e pesquisa no BD
	 * de acordo com seu ID
	 * @return Retorna um objeto de ImagemCarro
	 */
	public ImagemCarro pesquisarPorID(ImagemCarro carroUrl) {
		String sql = "select * from carros_images where id = ?";
		ImagemCarro carroUrlRetorno = null;
		
		Connection con = Conexao.getConnection();
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, carroUrl.getId());
			
			ResultSet rSet = ps.executeQuery();
			
			while(rSet.next()) {
				carroUrlRetorno = new ImagemCarro();
				carroUrlRetorno.setId(rSet.getInt("id"));
				carroUrlRetorno.setCaminho(rSet.getString("caminho"));
				carroUrlRetorno.setDescricao(rSet.getString("descricao"));
				
			}
			
			rSet.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
		
		return carroUrlRetorno;
	}
	
	/**
	 * 
	 * @return Retorna todos os objetos de ImagemCarro cadastrados no Bd
	 * em forma de List<ImagemCarro>
	 */
	public List<ImagemCarro> listarTodos() {
		String sql = "select * from carros_images";
		List<ImagemCarro> lista =  new ArrayList<ImagemCarro>();

		Connection con = Conexao.getConnection();
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rSet = ps.executeQuery();
			
			while(rSet.next()) {
				ImagemCarro carroUrl = new ImagemCarro();
				carroUrl.setId(rSet.getInt("id"));
				carroUrl.setCaminho(rSet.getString("caminho"));
				carroUrl.setDescricao(rSet.getString("descricao"));

				lista.add(carroUrl);
				
			}
			
			rSet.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
		
		return lista;
	}
	
}
