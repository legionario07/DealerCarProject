package br.com.dealercar.core.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.core.factory.Conexao;
import br.com.dealercar.core.util.JSFUtil;
import br.com.dealercar.domain.Cidade;
import br.com.dealercar.domain.Cliente;
import br.com.dealercar.domain.Endereco;
import br.com.dealercar.domain.EntidadeDominio;


public class ClienteDAO extends AbstractPesquisaDAO implements Serializable{

	private Connection con = null;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param cliente Recebe um cliente  e cadastra no Banco de Dados
	 * @param 
	 */
	public void cadastrar(EntidadeDominio entidade) {
		
		if(!(entidade instanceof Cliente))
			return;
		
		Cliente cliente = new Cliente();
		cliente = (Cliente) entidade;

		StringBuffer sql = new StringBuffer();
		sql.append("insert into clientes ");
		sql.append("(nome, data_nasc, nome_mae, sexo, telefone, celular, rg, cpf, email, ");
		sql.append("rua, numero, complemento, bairro, id_cidade) ");
		sql.append("values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		
		con = Conexao.getConnection();

		try {

			PreparedStatement pstm = con.prepareStatement(sql.toString());
			int i = 0;
			pstm.setString(++i, cliente.getNome().toUpperCase());
			
			//colocando formato string para armazenar no banco de dados
			SimpleDateFormat stf = new SimpleDateFormat("yyyy/MM/dd");
			String dataNascimento = stf.format(cliente.getDataNasc());
			pstm.setString(++i, dataNascimento);
			
			pstm.setString(++i, cliente.getNomeMae().toUpperCase());
			pstm.setString(++i, cliente.getSexo());
			pstm.setString(++i, cliente.getTelefone());
			pstm.setString(++i, cliente.getCelular());
			pstm.setString(++i, cliente.getRG().toUpperCase());
			pstm.setString(++i, cliente.getCPF());
			pstm.setString(++i, cliente.getEmail().toUpperCase());
			pstm.setString(++i, cliente.getEndereco().getRua().toUpperCase());
			pstm.setString(++i, cliente.getEndereco().getNumero().toUpperCase());
			pstm.setString(++i, cliente.getEndereco().getComplemento().toUpperCase());
			pstm.setString(++i, cliente.getEndereco().getBairro().toUpperCase());
			pstm.setInt(++i, cliente.getEndereco().getCidade().getId());

			pstm.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}

	/**
	 * 
	 * @param cliente Recebe um Ciente e  edita seus dados do Banco de Dados
	 * @param cidade
	 */
	public void editar(EntidadeDominio entidade) {
		
		if(!(entidade instanceof Cliente))
			return;
		
		Cliente cliente = new Cliente();
		cliente = (Cliente) entidade;

		StringBuffer sql = new StringBuffer();
		sql.append("update clientes set nome = ?, data_nasc = ?, nome_mae = ?, sexo = ?, ");
		sql.append("telefone = ?, celular= ?, rg = ?, cpf = ?, email=?, ");
		sql.append("rua = ?, numero = ?, complemento = ?, bairro = ?, ");
		sql.append("id_cidade = ? where id = ?");
		
		con = Conexao.getConnection();

		try {

			PreparedStatement pstm = con.prepareStatement(sql.toString());

			int i = 0;
			
			pstm.setString(++i, cliente.getNome().toUpperCase());
			
			//colocando formato string para armazenar no banco de dados
			SimpleDateFormat stf = new SimpleDateFormat("yyyy/MM/dd");
			String dataNascimento = stf.format(cliente.getDataNasc());
			pstm.setString(++i, dataNascimento);
			
			pstm.setString(++i, cliente.getNomeMae().toUpperCase());
			pstm.setString(++i, cliente.getSexo());
			pstm.setString(++i, cliente.getTelefone());
			pstm.setString(++i, cliente.getCelular());
			pstm.setString(++i, cliente.getRG().toUpperCase());
			pstm.setString(++i, cliente.getCPF());
			pstm.setString(++i, cliente.getEmail().toUpperCase());
			pstm.setString(++i, cliente.getEndereco().getRua().toUpperCase());
			pstm.setString(++i, cliente.getEndereco().getNumero().toUpperCase());
			pstm.setString(++i, cliente.getEndereco().getComplemento().toUpperCase());
			pstm.setString(++i, cliente.getEndereco().getBairro().toUpperCase());
			pstm.setInt(++i, cliente.getEndereco().getCidade().getId());
			
			pstm.setInt(++i, cliente.getId());

			pstm.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param cliente Recebe um Cliente e exclui do Banco de dados
	 *  localizando pelo seu Id
	 */
	public void excluir(EntidadeDominio entidade) {
		
		if(!(entidade instanceof Cliente))
			return;
		
		Cliente cliente = new Cliente();
		cliente = (Cliente) entidade;

		StringBuffer sql = new StringBuffer();
		sql.append("delete from clientes where id = ?");

		con = Conexao.getConnection();
		
		try {

			PreparedStatement pstm = con.prepareStatement(sql.toString());
			pstm.setInt(1, cliente.getId());

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
	 * @return Retorna todos os clientes do Banco de dados em forma de List<CLiente>
	 */
	public List<EntidadeDominio> listarTodos() {

		StringBuffer sql = new StringBuffer(); 
		sql.append("select * from clientes ");

		List<EntidadeDominio> clientes = new ArrayList<EntidadeDominio>();
		
		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			ResultSet rSet = pstm.executeQuery();

			while (rSet.next()) {


				Cliente clienteRetorno = new Cliente();

				clienteRetorno.setId(rSet.getInt("id"));
				clienteRetorno.setNome(rSet.getString("nome"));
				clienteRetorno.setDataNasc(rSet.getDate("data_nasc"));
				clienteRetorno.setNomeMae(rSet.getString("nome_mae"));
				clienteRetorno.setSexo(rSet.getString("sexo"));
				clienteRetorno.setTelefone(rSet.getString("telefone"));
				clienteRetorno.setCelular(rSet.getString("celular"));
				clienteRetorno.setRG(rSet.getString("rg"));
				clienteRetorno.setCPF(rSet.getString("cpf"));
				clienteRetorno.setEmail(rSet.getString("email"));
				
				Endereco end = new Endereco();
				end.setRua(rSet.getString("rua"));
				end.setNumero(rSet.getString("numero"));
				end.setComplemento(rSet.getString("complemento"));
				end.setBairro(rSet.getString("bairro"));
				
				Cidade cidadeRetorno = new Cidade();
				cidadeRetorno.setId(rSet.getInt("id_cidade"));
				cidadeRetorno = (Cidade) new CidadeDAO().pesquisarPorID(cidadeRetorno);
					
				end.setCidade(cidadeRetorno);
					
				clienteRetorno.setEndereco(end);				
				
				clientes.add(clienteRetorno);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

		return clientes;

	}

	/**
	 * 
	 * @param cliente Recebe um cliente e localiza pelo Id no Banco de Dados
	 * @return Retorna um Objeto de Cliente
	 */
	public Cliente pesquisarPorID(EntidadeDominio entidade) {
		
		if(!(entidade instanceof Cliente))
			return null;
		
		Cliente cliente = new Cliente();
		cliente = (Cliente) entidade;

		StringBuffer sql = new StringBuffer(); 
		sql.append("select * from clientes ");
		sql.append("where clientes.id = ?");

		Cliente clienteRetorno = null;
		
		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			pstm.setInt(1, cliente.getId());

			ResultSet rSet = pstm.executeQuery();

			while (rSet.next()) {
				clienteRetorno = new Cliente();

				clienteRetorno.setId(rSet.getInt("id"));
				clienteRetorno.setNome(rSet.getString("nome"));
				clienteRetorno.setDataNasc(rSet.getDate("data_nasc"));
				clienteRetorno.setNomeMae(rSet.getString("nome_mae"));
				clienteRetorno.setSexo(rSet.getString("sexo"));
				clienteRetorno.setTelefone(rSet.getString("telefone"));
				clienteRetorno.setCelular(rSet.getString("celular"));
				clienteRetorno.setRG(rSet.getString("rg"));
				clienteRetorno.setCPF(rSet.getString("cpf"));
				clienteRetorno.setEmail(rSet.getString("email"));
				
				Endereco end = new Endereco();
				end.setRua(rSet.getString("rua"));
				end.setNumero(rSet.getString("numero"));
				end.setComplemento(rSet.getString("complemento"));
				end.setBairro(rSet.getString("bairro"));
				
				Cidade cidadeRetorno = new Cidade();
				cidadeRetorno.setId(rSet.getInt("id_cidade"));
				cidadeRetorno = (Cidade) new CidadeDAO().pesquisarPorID(cidadeRetorno);
					
				end.setCidade(cidadeRetorno);
					
				clienteRetorno.setEndereco(end);					

			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

		return clienteRetorno;

	}
	
	/**
	 * 
	 * @param cliente Recebe um objeto de Cliente e localiza no Banco de Dadoos
	 *  pelo seu CPF
	 * @return Retorna um Objeto Cliente
	 */
	public Cliente pesquisarPorCPF(EntidadeDominio entidade) {
		
		if(!(entidade instanceof Cliente))
			return null;
		
		Cliente cliente = new Cliente();
		cliente = (Cliente) entidade;

		StringBuffer sql = new StringBuffer();
		sql.append("select * from clientes ");
		sql.append("where cpf = ?");

		Cliente clienteRetorno = null;
		
		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			pstm.setString(1, cliente.getCPF());

			ResultSet rSet = pstm.executeQuery();

			while (rSet.next()) {
				clienteRetorno = new Cliente();

				clienteRetorno.setId(rSet.getInt("id"));
				clienteRetorno.setNome(rSet.getString("nome"));
				clienteRetorno.setDataNasc(rSet.getDate("data_nasc"));
				clienteRetorno.setNomeMae(rSet.getString("nome_mae"));
				clienteRetorno.setSexo(rSet.getString("sexo"));
				clienteRetorno.setTelefone(rSet.getString("telefone"));
				clienteRetorno.setCelular(rSet.getString("celular"));
				clienteRetorno.setRG(rSet.getString("rg"));
				clienteRetorno.setCPF(rSet.getString("cpf"));
				clienteRetorno.setEmail(rSet.getString("email"));
				
				Endereco end = new Endereco();
				end.setRua(rSet.getString("rua"));
				end.setNumero(rSet.getString("numero"));
				end.setComplemento(rSet.getString("complemento"));
				end.setBairro(rSet.getString("bairro"));
				
				Cidade cidadeRetorno = new Cidade();
				cidadeRetorno.setId(rSet.getInt("id_cidade"));
				cidadeRetorno = (Cidade) new CidadeDAO().pesquisarPorID(cidadeRetorno);
					
				end.setCidade(cidadeRetorno);
					
				clienteRetorno.setEndereco(end);		
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

		return clienteRetorno;

	}

	/**
	 * 
	 * @param clientes Recebe um objeto do Tipo Cliente e localiza no Banco pelo nome
	 * @return Retorna um Objeto do tipo Cliente
	 */
	public List<EntidadeDominio> pesquisarPorNome(EntidadeDominio entidade) {
		
		if(!(entidade instanceof Cliente))
			return null;
		
		Cliente cliente = new Cliente();
		cliente = (Cliente) entidade;

		StringBuffer sql = new StringBuffer();
		sql.append("select * from clientes ");
		sql.append("where nome like ? order by nome asc");

		List<EntidadeDominio> lista = new ArrayList<EntidadeDominio>();
		
		con = Conexao.getConnection();

		try {

			PreparedStatement pstm = con.prepareStatement(sql.toString());
			pstm.setString(1, "%" + cliente.getNome().toUpperCase() + "%");

			ResultSet rSet = pstm.executeQuery();

			while (rSet.next()) {

				Cliente clienteRetorno = new Cliente();


				clienteRetorno.setId(rSet.getInt("id"));
				clienteRetorno.setNome(rSet.getString("nome"));
				clienteRetorno.setDataNasc(rSet.getDate("data_nasc"));
				clienteRetorno.setNomeMae(rSet.getString("nome_mae"));
				clienteRetorno.setSexo(rSet.getString("sexo"));
				clienteRetorno.setTelefone(rSet.getString("telefone"));
				clienteRetorno.setCelular(rSet.getString("celular"));
				clienteRetorno.setRG(rSet.getString("rg"));
				clienteRetorno.setCPF(rSet.getString("cpf"));
				clienteRetorno.setEmail(rSet.getString("email"));
				
				Endereco end = new Endereco();
				end.setRua(rSet.getString("rua"));
				end.setNumero(rSet.getString("numero"));
				end.setComplemento(rSet.getString("complemento"));
				
				end.setBairro(rSet.getString("bairro"));
				
				Cidade cidadeRetorno = new Cidade();
				cidadeRetorno.setId(rSet.getInt("id_cidade"));
				cidadeRetorno = (Cidade) new CidadeDAO().pesquisarPorID(cidadeRetorno);
					
				end.setCidade(cidadeRetorno);
					
				clienteRetorno.setEndereco(end);			

				lista.add(clienteRetorno);

			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

		return lista;
	}

}
