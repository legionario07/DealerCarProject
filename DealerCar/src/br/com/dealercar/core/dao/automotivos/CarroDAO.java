package br.com.dealercar.core.dao.automotivos;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import br.com.dealercar.core.dao.CorDAO;
import br.com.dealercar.core.dao.IDAO;
import br.com.dealercar.core.factory.Conexao;
import br.com.dealercar.core.util.JSFUtil;
import br.com.dealercar.domain.EntidadeDominio;
import br.com.dealercar.domain.automotivos.Carro;
import br.com.dealercar.domain.automotivos.Categoria;
import br.com.dealercar.domain.automotivos.Cor;
import br.com.dealercar.domain.automotivos.Modelo;
import br.com.dealercar.domain.enums.SituacaoType;

/**
 * Classe repons�vel por realizar a persistencia dos Carros no BD
 * @author Paulinho
 *
 */
public class CarroDAO implements IDAO, Serializable{
	
	private Connection con = null;

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param carro Recebe um objeto de Carro e cadastra no BD
	 */
	public void cadastrar(EntidadeDominio entidade) {
		
		if(!(entidade instanceof Carro))
			return;
		
		Carro carro = new Carro();
		carro = (Carro) entidade;
		
		StringBuffer sql = new StringBuffer();
		sql.append("insert into carros (placa, ano, numero_portas, qtde_malas_suportadas, ");
		sql.append("id_cor, id_modelo, id_categoria, url_imagem, situacao) ");
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
			pstm.setString(++i, carro.getUrlImagem());
			pstm.setString(++i, carro.getSituacao().getDescricao());
			
			pstm.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @param carro Recebe um objeto de Carro e exclui do BD de acordo com a placa
	 */
	public void excluir(EntidadeDominio entidade) {
		
		if(!(entidade instanceof Carro))
			return;
		
		Carro carro = new Carro();
		carro = (Carro) entidade;
		
		StringBuffer sql = new StringBuffer();
		sql.append("delete from carros where placa = ?");
		
		con = Conexao.getConnection();
		
		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			pstm.setString(1, carro.getPlaca());
			pstm.executeUpdate();
			
		} catch (MySQLIntegrityConstraintViolationException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro("N�o � Poss�vel excluir esse Veiculo, pois j� foi locado."
					+ "\nAltere para a Situa��o INDISPONIVEL");
		}catch (Exception e){
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
		
		
	}
	
	/**
	 * 
	 * @param carro Recebe um objeto de Carro e localiza no BD por sua placa
	 * @return Retorna um objeto de Carro
	 */
	public Carro pesquisarPorPlaca(EntidadeDominio entidade) {
		
		if(!(entidade instanceof Carro))
			return null;
		
		Carro carro = new Carro();
		carro = (Carro) entidade;
		
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
				cor =  (Cor) new CorDAO().pesquisarPorID(cor);
				carroRetorno.setCor(cor);
				
				Modelo modelo = new Modelo(rSet.getInt("id_modelo"));
				modelo = new ModeloDAO().pesquisarPorID(modelo);
				carroRetorno.setModelo(modelo);
				
				Categoria categoria = new Categoria(rSet.getInt("id_categoria"));
				categoria = new CategoriaDAO().pesquisarPorID(categoria);
				carroRetorno.setCategoria(categoria);
				
				carroRetorno.setUrlImagem(rSet.getString("url_imagem"));
				
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
	 * @param carro Recebe um objeto de Modelo e localiza no BD por sua placa e situa�ao Disponivel
	 * @return Retorna um objeto de Carro
	 */
	public List<EntidadeDominio> listarModelosDisponiveis(EntidadeDominio entidade) {
		
		if(!(entidade instanceof Carro))
			return null;

		Carro carro = new Carro();
		carro = (Carro) entidade;
		
		StringBuffer sql = new StringBuffer(); 
		sql.append("select * from carros ");
		sql.append("where id_modelo = ? and situacao = ?");
		
		List<EntidadeDominio> carros = new ArrayList<EntidadeDominio>();
		
		con = Conexao.getConnection();
		
		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			pstm.setInt(1, carro.getModelo().getId());
			pstm.setString(2, SituacaoType.Disponivel.getDescricao());
			
			ResultSet rSet = pstm.executeQuery();
			
			while(rSet.next()) {
				
				Carro carroRetorno = new Carro();
				carroRetorno.setPlaca(rSet.getString("placa"));
				carroRetorno.setAno(rSet.getString("ano"));
				carroRetorno.setQtdePortas(rSet.getInt("numero_portas"));
				carroRetorno.setQtdeMalasSuportadas(rSet.getInt("qtde_malas_suportadas"));
				
				Cor cor = new Cor(rSet.getInt("id_cor"));
				cor =  (Cor) new CorDAO().pesquisarPorID(cor);
				carroRetorno.setCor(cor);
				
				Modelo modeloRetorno = new Modelo(rSet.getInt("id_modelo"));
				modeloRetorno = new ModeloDAO().pesquisarPorID(modeloRetorno);
				carroRetorno.setModelo(modeloRetorno);
				
				Categoria categoria = new Categoria(rSet.getInt("id_categoria"));
				categoria = new CategoriaDAO().pesquisarPorID(categoria);
				carroRetorno.setCategoria(categoria);
				
				carroRetorno.setUrlImagem(rSet.getString("url_imagem"));
				
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
	public void editar(EntidadeDominio entidade) {
		
		if(!(entidade instanceof Carro))
			return;
		
		Carro carro = new Carro();
		carro = (Carro) entidade;
		
		StringBuffer sql = new StringBuffer();
		sql.append("update carros set placa = ?, ano = ?, ");
		sql.append("numero_portas = ?, qtde_malas_suportadas = ?, ");
		sql.append("id_cor = ?, id_modelo = ?, id_categoria = ?, ");
		sql.append("url_imagem = ?, situacao = ? where placa = ?");
		
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
			pstm.setString(++i, carro.getUrlImagem());
			pstm.setString(++i, carro.getSituacao().getDescricao());
			pstm.setString(++i, carro.getPlaca());
			
			pstm.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
		
	}
	
	
	/**
	 * 
	 * @return Retorna todos os carros cadastrados no BD em forma de List<Carro>
	 */
	public List<EntidadeDominio> listarTodos() {
		
		StringBuffer sql = new StringBuffer(); 
		sql.append("select * from carros ");
		sql.append("order by ano asc");
		
		List<EntidadeDominio> lista = new ArrayList<EntidadeDominio>();
		
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
				cor =  (Cor) new CorDAO().pesquisarPorID(cor);
				carroRetorno.setCor(cor);
				
				Modelo modeloRetorno = new Modelo(rSet.getInt("id_modelo"));
				modeloRetorno = new ModeloDAO().pesquisarPorID(modeloRetorno);
				carroRetorno.setModelo(modeloRetorno);
				
				Categoria categoria = new Categoria(rSet.getInt("id_categoria"));
				categoria = new CategoriaDAO().pesquisarPorID(categoria);
				carroRetorno.setCategoria(categoria);
				
				carroRetorno.setUrlImagem(rSet.getString("url_imagem"));
				
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
				cor =  (Cor) new CorDAO().pesquisarPorID(cor);
				carroRetorno.setCor(cor);
				
				Modelo modeloRetorno = new Modelo(rSet.getInt("id_modelo"));
				modeloRetorno = new ModeloDAO().pesquisarPorID(modeloRetorno);
				carroRetorno.setModelo(modeloRetorno);
				
				Categoria categoria = new Categoria(rSet.getInt("id_categoria"));
				categoria = new CategoriaDAO().pesquisarPorID(categoria);
				carroRetorno.setCategoria(categoria);
				
				carroRetorno.setUrlImagem(rSet.getString("url_imagem"));
				
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
	public Carro pesquisarPorID(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		return null;
	}
}
