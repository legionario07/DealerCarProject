package br.com.dealercar.dao.automotivos;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.dao.CorDAO;
import br.com.dealercar.dao.IDAO;
import br.com.dealercar.domain.Cor;
import br.com.dealercar.domain.automotivos.Carro;
import br.com.dealercar.domain.automotivos.Categoria;
import br.com.dealercar.domain.automotivos.ImagemCarro;
import br.com.dealercar.domain.automotivos.Modelo;
import br.com.dealercar.enums.SituacaoType;
import br.com.dealercar.factory.Conexao;
import br.com.dealercar.util.JSFUtil;

/**
 * Classe reponsável por realizar a persistencia dos Carros no BD
 * @author Paulinho
 *
 */
public class CarroDAO implements IDAO<Carro>, Serializable{
	
	Connection con = Conexao.getConnection();

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param carro Recebe um objeto de Carro e cadastra no BD
	 */
	public void cadastrar(Carro carro) {
		
		StringBuffer sql = new StringBuffer();
		sql.append("insert into carros (placa, ano, numero_portas, qtde_malas_suportadas, ");
		sql.append("id_cor, id_modelo, id_categoria, id_images, situacao) ");
		sql.append("values (? , ?, ?, ?, ?, ?, ? ,? , ?)");
		
		con = Conexao.getConnection();
		
		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			int i=0;
			pstm.setString(++i, carro.getPlaca().toUpperCase());
			pstm.setString(++i, carro.getAno());
			pstm.setInt(++i, carro.getQtdePortas());
			pstm.setInt(++i, carro.getQtdeMalasSuportadas());
			pstm.setInt(++i, carro.getCor().getId());
			pstm.setInt(++i, carro.getModelo().getId());
			pstm.setInt(++i, carro.getCategoria().getId());
			pstm.setInt(++i, carro.getCarroUrl().getId());
			pstm.setString(++i, carro.getSituacao().getDescricao());
			
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
	 * @param carro Recebe um objeto de Carro e exclui do BD de acordo com a placa
	 */
	public void excluir(Carro carro) {
		
		StringBuffer sql = new StringBuffer();
		sql.append("delete from carros where placa = ?");
		
		con = Conexao.getConnection();
		
		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			pstm.setString(1, carro.getPlaca());
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
	 * @param carro Recebe um objeto de Carro e localiza no BD por sua placa
	 * @return Retorna um objeto de Carro
	 */
	public Carro pesquisarPorPlaca(Carro carro) {
		
		StringBuffer sql = new StringBuffer(); 
		sql.append("select * from carros ");
		sql.append("where placa = ?");
		
		Carro carroRetorno = null;
		
		con = Conexao.getConnection();
		
		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			pstm.setString(1, carro.getPlaca());
			
			ResultSet rSet = pstm.executeQuery();
			
			while(rSet.next()) {
				carroRetorno = new Carro();
				carroRetorno.setPlaca(rSet.getString("placa"));
				carroRetorno.setAno(rSet.getString("ano"));
				carroRetorno.setQtdePortas(rSet.getInt("numero_portas"));
				carroRetorno.setQtdeMalasSuportadas(rSet.getInt("qtde_malas_suportadas"));
				
				Cor cor = new Cor(rSet.getInt("id_cor"));
				cor =  new CorDAO().pesquisarPorID(cor);
				carroRetorno.setCor(cor);
				
				Modelo modelo = new Modelo(rSet.getInt("id_modelo"));
				modelo = new ModeloDAO().pesquisarPorID(modelo);
				carroRetorno.setModelo(modelo);
				
				Categoria categoria = new Categoria(rSet.getInt("id_categoria"));
				categoria = new CategoriaDAO().pesquisarPorID(categoria);
				carroRetorno.setCategoria(categoria);
				
				ImagemCarro carroUrl = new ImagemCarro(rSet.getInt("id_images"));
				carroUrl = new ImagemCarroDAO().pesquisarPorID(carroUrl);
				carroRetorno.setCarroUrl(carroUrl);
				
				carroRetorno.setSituacao(SituacaoType.valueOf(rSet.getString("carros.situacao")));
			
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
		
		return carroRetorno;
	}
	
	/**
	 * 
	 * @param carro Recebe um objeto de Modelo e localiza no BD por sua placa e situaçao Disponivel
	 * @return Retorna um objeto de Carro
	 */
	public List<Carro> listarModelosDisponiveis(Modelo modelo) {
		
		StringBuffer sql = new StringBuffer(); 
		sql.append("select * from carros ");
		sql.append("where id_modelo = ? and situacao = ?");
		
		List<Carro> carros = new ArrayList<Carro>();
		
		con = Conexao.getConnection();
		
		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			pstm.setInt(1, modelo.getId());
			pstm.setString(2, SituacaoType.Disponivel.getDescricao());
			
			ResultSet rSet = pstm.executeQuery();
			
			while(rSet.next()) {
				
				Carro carroRetorno = new Carro();
				carroRetorno.setPlaca(rSet.getString("placa"));
				carroRetorno.setAno(rSet.getString("ano"));
				carroRetorno.setQtdePortas(rSet.getInt("numero_portas"));
				carroRetorno.setQtdeMalasSuportadas(rSet.getInt("qtde_malas_suportadas"));
				
				Cor cor = new Cor(rSet.getInt("id_cor"));
				cor =  new CorDAO().pesquisarPorID(cor);
				carroRetorno.setCor(cor);
				
				Modelo modeloRetorno = new Modelo(rSet.getInt("id_modelo"));
				modeloRetorno = new ModeloDAO().pesquisarPorID(modeloRetorno);
				carroRetorno.setModelo(modeloRetorno);
				
				Categoria categoria = new Categoria(rSet.getInt("id_categoria"));
				categoria = new CategoriaDAO().pesquisarPorID(categoria);
				carroRetorno.setCategoria(categoria);
				
				ImagemCarro carroUrl = new ImagemCarro(rSet.getInt("id_images"));
				carroUrl = new ImagemCarroDAO().pesquisarPorID(carroUrl);
				carroRetorno.setCarroUrl(carroUrl);
				
				carroRetorno.setSituacao(SituacaoType.valueOf(rSet.getString("carros.situacao")));
				
				carros.add(carroRetorno);
			
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
		
		return carros;
	}
	
	/**
	 * 
	 * @param carro Recebe um objeto Carro e edita todos os seus dados no BD
	 */
	public void editar(Carro carro) {
		
		StringBuffer sql = new StringBuffer();
		sql.append("update carros set placa = ?, ano = ?, ");
		sql.append("numero_portas = ?, qtde_malas_suportadas = ?, ");
		sql.append("id_cor = ?, id_modelo = ?, id_categoria = ?, ");
		sql.append("id_images = ?, situacao = ? where placa = ?");
		
		con = Conexao.getConnection();
		
		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			int i=0;
			pstm.setString(++i, carro.getPlaca());
			pstm.setString(++i, carro.getAno());
			pstm.setInt(++i, carro.getQtdePortas());
			pstm.setInt(++i, carro.getQtdeMalasSuportadas());
			pstm.setInt(++i, carro.getCor().getId());
			pstm.setInt(++i, carro.getModelo().getId());
			pstm.setInt(++i, carro.getCategoria().getId());
			pstm.setInt(++i, carro.getCarroUrl().getId());
			pstm.setString(++i, carro.getSituacao().getDescricao());
			pstm.setString(++i, carro.getPlaca());
			
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
	 * @return Retorna todos os carros cadastrados no BD em forma de List<Carro>
	 */
	public List<Carro> listarTodos() {
		
		StringBuffer sql = new StringBuffer(); 
		sql.append("select * from carros ");
		sql.append("order by ano asc");
		
		List<Carro> lista = new ArrayList<Carro>();
		
		con = Conexao.getConnection();
		
		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			ResultSet rSet = pstm.executeQuery();
			
			while(rSet.next()) {
				
				Carro carroRetorno = new Carro();
				carroRetorno.setPlaca(rSet.getString("placa"));
				carroRetorno.setAno(rSet.getString("ano"));
				carroRetorno.setQtdePortas(rSet.getInt("numero_portas"));
				carroRetorno.setQtdeMalasSuportadas(rSet.getInt("qtde_malas_suportadas"));
				
				Cor cor = new Cor(rSet.getInt("id_cor"));
				cor =  new CorDAO().pesquisarPorID(cor);
				carroRetorno.setCor(cor);
				
				Modelo modeloRetorno = new Modelo(rSet.getInt("id_modelo"));
				modeloRetorno = new ModeloDAO().pesquisarPorID(modeloRetorno);
				carroRetorno.setModelo(modeloRetorno);
				
				Categoria categoria = new Categoria(rSet.getInt("id_categoria"));
				categoria = new CategoriaDAO().pesquisarPorID(categoria);
				carroRetorno.setCategoria(categoria);
				
				ImagemCarro carroUrl = new ImagemCarro(rSet.getInt("id_images"));
				carroUrl = new ImagemCarroDAO().pesquisarPorID(carroUrl);
				carroRetorno.setCarroUrl(carroUrl);
				
				carroRetorno.setSituacao(SituacaoType.valueOf(rSet.getString("carros.situacao")));
				
				lista.add(carroRetorno);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
		
		return lista;
	}
	
	
	/**
	 * 
	 * @return Uma lista de Carro disponivel para Reserva
	 */
	public List<Carro> listarApenasDisponiveis() {
		
		StringBuffer sql = new StringBuffer(); 
		
		sql.append("select * from carros ");
		sql.append("inner join modelos on modelos.id = carros.id_modelo ");
		sql.append("where situacao = ? group by modelos.nome");
		
		List<Carro> lista = new ArrayList<Carro>();
		
		con = Conexao.getConnection();
		
		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			pstm.setString(1, SituacaoType.Disponivel.getDescricao());
			ResultSet rSet = pstm.executeQuery();
			
			while(rSet.next()) {
				
				Carro carroRetorno = new Carro();
				carroRetorno.setPlaca(rSet.getString("placa"));
				carroRetorno.setAno(rSet.getString("ano"));
				carroRetorno.setQtdePortas(rSet.getInt("numero_portas"));
				carroRetorno.setQtdeMalasSuportadas(rSet.getInt("qtde_malas_suportadas"));
				
				Cor cor = new Cor(rSet.getInt("id_cor"));
				cor =  new CorDAO().pesquisarPorID(cor);
				carroRetorno.setCor(cor);
				
				Modelo modeloRetorno = new Modelo(rSet.getInt("id_modelo"));
				modeloRetorno = new ModeloDAO().pesquisarPorID(modeloRetorno);
				carroRetorno.setModelo(modeloRetorno);
				
				Categoria categoria = new Categoria(rSet.getInt("id_categoria"));
				categoria = new CategoriaDAO().pesquisarPorID(categoria);
				carroRetorno.setCategoria(categoria);
				
				ImagemCarro carroUrl = new ImagemCarro(rSet.getInt("id_images"));
				carroUrl = new ImagemCarroDAO().pesquisarPorID(carroUrl);
				carroRetorno.setCarroUrl(carroUrl);
				
				carroRetorno.setSituacao(SituacaoType.valueOf(rSet.getString("carros.situacao")));
				
				lista.add(carroRetorno);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
		
		return lista;
	}
	

	@Override
	public Carro pesquisarPorID(Carro entidade) {
		// TODO Auto-generated method stub
		return null;
	}
}
