package br.com.dealercar.dao.automotivos;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.dao.IDAO;
import br.com.dealercar.domain.Cor;
import br.com.dealercar.domain.automotivos.Carro;
import br.com.dealercar.domain.automotivos.Categoria;
import br.com.dealercar.domain.automotivos.Fabricante;
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
		sql.append("select carros.placa, carros.ano, carros.numero_portas, ");
		sql.append("carros.qtde_malas_suportadas, carros.id_cor, cores.nome, ");
		sql.append("carros.id_modelo, modelos.nome, modelos.id_fabricante, ");
		sql.append("carros.id_categoria, categorias.nome, ");
		sql.append("categorias.descricao, categorias.vlr_diaria, ");
		sql.append("carros.id_images, carros_images.caminho, carros_images.descricao, carros.situacao ");
		sql.append("from carros inner join cores on carros.id_cor = cores.id ");
		sql.append("inner join modelos on carros.id_modelo = modelos.id ");
		sql.append("inner join categorias on carros.id_categoria = categorias.id ");
		sql.append("inner join carros_images on carros.id_images = carros_images.id ");
		sql.append("where carros.placa = ?");
		
		Carro carroRetorno = null;
		
		con = Conexao.getConnection();
		
		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			pstm.setString(1, carro.getPlaca());
			
			ResultSet rSet = pstm.executeQuery();
			
			while(rSet.next()) {
				carroRetorno = new Carro();
				carroRetorno.setPlaca(rSet.getString("carros.placa"));
				carroRetorno.setAno(rSet.getString("carros.ano"));
				carroRetorno.setQtdePortas(rSet.getInt("carros.numero_portas"));
				carroRetorno.setQtdeMalasSuportadas(rSet.getInt("carros.qtde_malas_suportadas"));
				
				Cor cor = new Cor();
				cor.setId(rSet.getInt("carros.id_cor"));
				cor.setNome(rSet.getString("cores.nome"));
				carroRetorno.setCor(cor);
				
				Modelo modelo = new Modelo();
				modelo.setId(rSet.getInt("carros.id_modelo"));
				modelo.setNome(rSet.getString("modelos.nome"));
				
				Fabricante fabricante = new Fabricante();
				fabricante.setId(rSet.getInt("modelos.id_fabricante"));
				modelo.setFabricante(fabricante);
				carroRetorno.setModelo(modelo);
				
				Categoria categoria = new Categoria();
				categoria.setId(rSet.getInt("carros.id_categoria"));
				categoria.setNome(rSet.getString("categorias.nome"));
				categoria.setDescricao(rSet.getString("categorias.descricao"));
				categoria.setValorDiaria(rSet.getDouble("categorias.vlr_diaria"));
				carroRetorno.setCategoria(categoria);
				
				ImagemCarro carroUrl = new ImagemCarro();
				carroUrl.setId(rSet.getInt("carros.id_images"));
				carroUrl.setCaminho(rSet.getString("carros_images.caminho"));
				carroUrl.setDescricao(rSet.getString("carros_images.descricao"));
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
		sql.append("select carros.placa, carros.ano, carros.numero_portas, ");
		sql.append("carros.qtde_malas_suportadas, carros.id_cor, cores.nome, ");
		sql.append("carros.id_modelo, modelos.nome, modelos.id_fabricante, ");
		sql.append("carros.id_categoria, categorias.nome, ");
		sql.append("categorias.descricao, categorias.vlr_diaria, ");
		sql.append("carros.id_images, carros_images.caminho, carros_images.descricao, carros.situacao ");
		sql.append("from carros inner join cores on carros.id_cor = cores.id ");
		sql.append("inner join modelos on carros.id_modelo = modelos.id ");
		sql.append("inner join categorias on carros.id_categoria = categorias.id ");
		sql.append("inner join carros_images on carros.id_images = carros_images.id ");
		sql.append("where carros.id_modelo = ? and carros.situacao = ?");
		
		List<Carro> carros = new ArrayList<Carro>();
		
		con = Conexao.getConnection();
		
		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			pstm.setInt(1, modelo.getId());
			pstm.setString(2, SituacaoType.Disponivel.getDescricao());
			
			ResultSet rSet = pstm.executeQuery();
			
			while(rSet.next()) {
				
				Carro carroRetorno = new Carro();
				carroRetorno.setPlaca(rSet.getString("carros.placa"));
				carroRetorno.setAno(rSet.getString("carros.ano"));
				carroRetorno.setQtdePortas(rSet.getInt("carros.numero_portas"));
				carroRetorno.setQtdeMalasSuportadas(rSet.getInt("carros.qtde_malas_suportadas"));
				
				Cor cor = new Cor();
				cor.setId(rSet.getInt("carros.id_cor"));
				cor.setNome(rSet.getString("cores.nome"));
				carroRetorno.setCor(cor);
				
				Modelo mod = new Modelo();
				mod.setId(rSet.getInt("carros.id_modelo"));
				mod.setNome(rSet.getString("modelos.nome"));
				
				Fabricante fabricante = new Fabricante();
				fabricante.setId(rSet.getInt("modelos.id_fabricante"));
				modelo.setFabricante(fabricante);
				carroRetorno.setModelo(mod);
				
				Categoria categoria = new Categoria();
				categoria.setId(rSet.getInt("carros.id_categoria"));
				categoria.setNome(rSet.getString("categorias.nome"));
				categoria.setDescricao(rSet.getString("categorias.descricao"));
				categoria.setValorDiaria(rSet.getDouble("categorias.vlr_diaria"));
				carroRetorno.setCategoria(categoria);
				
				ImagemCarro carroUrl = new ImagemCarro();
				carroUrl.setId(rSet.getInt("carros.id_images"));
				carroUrl.setCaminho(rSet.getString("carros_images.caminho"));
				carroUrl.setDescricao(rSet.getString("carros_images.descricao"));
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
		sql.append("select carros.placa, carros.ano, carros.numero_portas, ");
		sql.append("carros.qtde_malas_suportadas, carros.id_cor, cores.nome, ");
		sql.append("carros.id_modelo, modelos.nome, modelos.id_fabricante, ");
		sql.append("carros.id_categoria, categorias.nome, ");
		sql.append("categorias.descricao, categorias.vlr_diaria, ");
		sql.append("carros.id_images, carros_images.caminho, carros_images.descricao, carros.situacao ");
		sql.append("from carros inner join cores ");
		sql.append("inner join modelos inner join categorias inner join carros_images ");
		sql.append(" where carros.id_cor = cores.id and carros.id_modelo = modelos.id ");
		sql.append("and carros.id_categoria = categorias.id and carros.id_images = carros_images.id ");
		sql.append("order by carros.ano asc");
		
		List<Carro> lista = new ArrayList<Carro>();
		
		con = Conexao.getConnection();
		
		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			ResultSet rSet = pstm.executeQuery();
			
			while(rSet.next()) {
				
				Carro carroRetorno = new Carro();
				carroRetorno.setPlaca(rSet.getString("carros.placa"));
				carroRetorno.setAno(rSet.getString("carros.ano"));
				carroRetorno.setQtdePortas(rSet.getInt("carros.numero_portas"));
				carroRetorno.setQtdeMalasSuportadas(rSet.getInt("carros.qtde_malas_suportadas"));
				
				Cor cor = new Cor();
				cor.setId(rSet.getInt("carros.id_cor"));
				cor.setNome(rSet.getString("cores.nome"));
				carroRetorno.setCor(cor);
				
				Modelo modelo = new Modelo();
				modelo.setId(rSet.getInt("carros.id_modelo"));
				modelo.setNome(rSet.getString("modelos.nome"));
				
				Fabricante fabricante = new Fabricante();
				fabricante.setId(rSet.getInt("modelos.id_fabricante"));
				modelo.setFabricante(fabricante);
				carroRetorno.setModelo(modelo);
				
				Categoria categoria = new Categoria();
				categoria.setId(rSet.getInt("carros.id_categoria"));
				categoria.setNome(rSet.getString("categorias.nome"));
				categoria.setDescricao(rSet.getString("categorias.descricao"));
				categoria.setValorDiaria(rSet.getDouble("categorias.vlr_diaria"));
				carroRetorno.setCategoria(categoria);
				
				ImagemCarro carroUrl = new ImagemCarro();
				carroUrl.setId(rSet.getInt("carros.id_images"));
				carroUrl.setCaminho(rSet.getString("carros_images.caminho"));
				carroUrl.setDescricao(rSet.getString("carros_images.descricao"));
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
		
		sql.append("select distinct carros.placa, carros.ano, carros.numero_portas, ");
		sql.append("carros.qtde_malas_suportadas, carros.id_cor, cores.nome, ");
		sql.append("carros.situacao, carros.id_modelo, "); 
		sql.append("carros.id_categoria, categorias.nome, categorias.descricao, categorias.vlr_diaria, ");
		sql.append("modelos.nome, modelos.id_fabricante, fabricantes.nome, "); 
		sql.append("carros.id_images, carros_images.caminho, carros_images.descricao "); 
		sql.append("from carros inner join modelos on carros.id_modelo = modelos.id ");
		sql.append("inner join carros_images on carros.id_images = carros_images.id ");
		sql.append("inner join cores on cores.id = carros.id_cor ");
		sql.append("inner join categorias on carros.id_categoria = categorias.id ");
		sql.append("inner join fabricantes on fabricantes.id = modelos.id_fabricante "); 
		sql.append("where carros.situacao = ? group by modelos.nome");
		
		List<Carro> lista = new ArrayList<Carro>();
		
		con = Conexao.getConnection();
		
		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			pstm.setString(1, SituacaoType.Disponivel.getDescricao());
			ResultSet rSet = pstm.executeQuery();
			
			while(rSet.next()) {
				
				Carro carroRetorno = new Carro();
				carroRetorno.setPlaca(rSet.getString("carros.placa"));
				carroRetorno.setAno(rSet.getString("carros.ano"));
				carroRetorno.setQtdePortas(rSet.getInt("carros.numero_portas"));
				carroRetorno.setQtdeMalasSuportadas(rSet.getInt("carros.qtde_malas_suportadas"));
				
				Cor cor = new Cor();
				cor.setId(rSet.getInt("carros.id_cor"));
				cor.setNome(rSet.getString("cores.nome"));
				carroRetorno.setCor(cor);
				
				Modelo modelo = new Modelo();
				modelo.setId(rSet.getInt("carros.id_modelo"));
				modelo.setNome(rSet.getString("modelos.nome"));
				
				Fabricante fabricante = new Fabricante();
				fabricante.setId(rSet.getInt("modelos.id_fabricante"));
				modelo.setFabricante(fabricante);
				carroRetorno.setModelo(modelo);
				
				Categoria categoria = new Categoria();
				categoria.setId(rSet.getInt("carros.id_categoria"));
				categoria.setNome(rSet.getString("categorias.nome"));
				categoria.setDescricao(rSet.getString("categorias.descricao"));
				categoria.setValorDiaria(rSet.getDouble("categorias.vlr_diaria"));
				carroRetorno.setCategoria(categoria);
				
				ImagemCarro carroUrl = new ImagemCarro();
				carroUrl.setId(rSet.getInt("carros.id_images"));
				carroUrl.setCaminho(rSet.getString("carros_images.caminho"));
				carroUrl.setDescricao(rSet.getString("carros_images.descricao"));
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
