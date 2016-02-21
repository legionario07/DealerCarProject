package br.com.dealercar.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.domain.Cidade;
import br.com.dealercar.domain.Endereco;
import br.com.dealercar.domain.Funcionario;
import br.com.dealercar.domain.Usuario;
import br.com.dealercar.factory.Conexao;
import br.com.dealercar.util.JSFUtil;

/**
 * Classe responsável por persistir os Funcionários no BD
 * 
 * @author Paulinho
 *
 */
public class FuncionarioDAO extends AbstractPesquisaDAO<Funcionario>implements Serializable {

	Connection con = Conexao.getConnection();

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param funcionario
	 *            Recebe um funcionario e uma Cidade e cadastra no Banco de
	 *            Dados
	 * @param cidade
	 */
	public void cadastrar(Funcionario funcionario) {

		StringBuffer sql = new StringBuffer();
		sql.append("insert into funcionarios ");
		sql.append("(nome, data_nasc, sexo, rua, numero, complemento, bairro, telefone, celular, ");
		sql.append("id_cidade, id_usuario, cargo, salario) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

		con = Conexao.getConnection();

		try {

			PreparedStatement pstm = con.prepareStatement(sql.toString());
			int i = 0;
			pstm.setString(++i, funcionario.getNome().toUpperCase());

			// colocando formato string para armazenar no banco de dados
			SimpleDateFormat stf = new SimpleDateFormat("yyyy/MM/dd");
			String dataNascimento = stf.format(funcionario.getDataNasc());
			pstm.setString(++i, dataNascimento);

			pstm.setString(++i, funcionario.getSexo());
			pstm.setString(++i, funcionario.getEndereco().getRua());
			pstm.setString(++i, funcionario.getEndereco().getNumero());
			pstm.setString(++i, funcionario.getEndereco().getComplemento());
			pstm.setString(++i, funcionario.getEndereco().getBairro());
			pstm.setString(++i, funcionario.getTelefone());
			pstm.setString(++i, funcionario.getCelular());
			pstm.setInt(++i, funcionario.getEndereco().getCidade().getId());
			pstm.setInt(++i, funcionario.getUsuario().getId());
			pstm.setString(++i, funcionario.getCargo());
			pstm.setDouble(++i, funcionario.getSalario());

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
	 * @param funcionario
	 *            Recebe um Funcionario e uma Cidade e edita seus dados do Banco
	 *            de Dados
	 * @param
	 */
	public void editar(Funcionario funcionario) {

		StringBuffer sql = new StringBuffer();
		sql.append("update funcionarios set ");
		sql.append("nome = ?, data_nasc = ?, sexo = ?, ");
		sql.append("rua = ?, numero = ?, complemento = ?, bairro = ?, ");
		sql.append("telefone = ?, celular = ?, cargo = ?, salario = ?, ");
		sql.append("id_cidade = ?, id_usuario = ? where id = ?");

		con = Conexao.getConnection();

		try {

			PreparedStatement pstm = con.prepareStatement(sql.toString());
			int i = 0;
			pstm.setString(++i, funcionario.getNome().toUpperCase());

			// colocando formato string para armazenar no banco de dados
			SimpleDateFormat stf = new SimpleDateFormat("yyyy/MM/dd");
			String dataNascimento = stf.format(funcionario.getDataNasc());
			pstm.setString(++i, dataNascimento);

			pstm.setString(++i, funcionario.getSexo());
			pstm.setString(++i, funcionario.getEndereco().getRua());
			pstm.setString(++i, funcionario.getEndereco().getNumero());
			pstm.setString(++i, funcionario.getEndereco().getComplemento());
			pstm.setString(++i, funcionario.getEndereco().getBairro());
			pstm.setString(++i, funcionario.getTelefone());
			pstm.setString(++i, funcionario.getCelular());
			pstm.setString(++i, funcionario.getCargo().toUpperCase());
			pstm.setDouble(++i, funcionario.getSalario());
			pstm.setInt(++i, funcionario.getEndereco().getCidade().getId());
			pstm.setInt(++i, funcionario.getUsuario().getId());
			pstm.setInt(++i, funcionario.getId());

			// editando o Usuario do Funcionario
			new UsuarioDAO().editar(funcionario.getUsuario());

			pstm.executeUpdate();

			pstm.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param funcionario
	 *            Recebe um Funcionario e exclui do Banco de dados localizando
	 *            pelo seu Id
	 */
	public void excluir(Funcionario funcionario) {

		String sql = "delete from funcionarios where id = ?";

		con = Conexao.getConnection();

		try {

			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setInt(1, funcionario.getId());

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
	 * @return Retorna todos os funcionarios do Banco de dados em forma de List
	 *         <Funcionario>
	 */
	public List<Funcionario> listarTodos() {

		StringBuffer sql = new StringBuffer();
		sql.append("select * from funcionarios ");
		sql.append("order by nome asc");

		List<Funcionario> funcionarios = new ArrayList<Funcionario>();

		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			ResultSet rSet = pstm.executeQuery();

			while (rSet.next()) {

				Funcionario funcionarioRetorno = new Funcionario();

				funcionarioRetorno.setId(rSet.getInt("id"));
				funcionarioRetorno.setNome(rSet.getString("nome"));
				funcionarioRetorno.setDataNasc(rSet.getDate("data_nasc"));
				funcionarioRetorno.setSexo(rSet.getString("sexo"));
				funcionarioRetorno.setTelefone(rSet.getString("telefone"));

				Endereco end = new Endereco();
				end.setRua(rSet.getString("rua"));
				end.setNumero(rSet.getString("numero"));
				end.setComplemento(rSet.getString("complemento"));
				end.setBairro(rSet.getString("bairro"));

				Cidade cidadeRetorno = new Cidade();
				cidadeRetorno.setId(rSet.getInt("id_cidade"));
				cidadeRetorno = new CidadeDAO().pesquisarPorID(cidadeRetorno);

				end.setCidade(cidadeRetorno);
				funcionarioRetorno.setEndereco(end);

				funcionarioRetorno.setCelular(rSet.getString("celular"));
				funcionarioRetorno.setCargo(rSet.getString("cargo"));
				funcionarioRetorno.setSalario(rSet.getDouble("salario"));

				Usuario usuario = new Usuario(rSet.getInt("id_usuario"));
				usuario = new UsuarioDAO().pesquisarPorID(usuario);

				funcionarioRetorno.setUsuario(usuario);

				funcionarios.add(funcionarioRetorno);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

		return funcionarios;

	}

	/**
	 * 
	 * @param funcionario
	 *            Recebe um funcionario e localiza pelo Id no Banco de Dados
	 * @return Retorna um Objeto de Funcionario
	 */
	public Funcionario pesquisarPorID(Funcionario funcionario) {

		StringBuffer sql = new StringBuffer();
		sql.append("select * from funcionarios ");
		sql.append("where id = ?");

		Funcionario funcionarioRetorno = null;

		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			pstm.setInt(1, funcionario.getId());

			ResultSet rSet = pstm.executeQuery();

			while (rSet.next()) {

				funcionarioRetorno = new Funcionario();
				funcionarioRetorno.setId(rSet.getInt("id"));
				funcionarioRetorno.setNome(rSet.getString("nome"));
				funcionarioRetorno.setDataNasc(rSet.getDate("data_nasc"));
				funcionarioRetorno.setSexo(rSet.getString("sexo"));
				funcionarioRetorno.setTelefone(rSet.getString("telefone"));

				Endereco end = new Endereco();
				end.setRua(rSet.getString("rua"));
				end.setNumero(rSet.getString("numero"));
				end.setComplemento(rSet.getString("complemento"));
				end.setBairro(rSet.getString("bairro"));

				Cidade cidadeRetorno = new Cidade();
				cidadeRetorno.setId(rSet.getInt("id_cidade"));
				cidadeRetorno = new CidadeDAO().pesquisarPorID(cidadeRetorno);

				end.setCidade(cidadeRetorno);
				funcionarioRetorno.setEndereco(end);

				funcionarioRetorno.setCelular(rSet.getString("celular"));
				funcionarioRetorno.setCargo(rSet.getString("cargo"));
				funcionarioRetorno.setSalario(rSet.getDouble("salario"));

				Usuario usuario = new Usuario(rSet.getInt("id_usuario"));
				usuario = new UsuarioDAO().pesquisarPorID(usuario);

				funcionarioRetorno.setUsuario(usuario);

			}

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

		return funcionarioRetorno;

	}

	/**
	 * 
	 * @param funcionarios
	 *            Recebe um objeto do Tipo Funcionario e localiza no Banco pelo
	 *            nome
	 * @return Retorna um Objeto do tipo Funcionario
	 */
	public List<Funcionario> pesquisarPorNome(Funcionario funcionario) {

		StringBuffer sql = new StringBuffer();
		sql.append("select * from funcionarios ");
		sql.append("where nome like ? order by nome asc");

		List<Funcionario> lista = new ArrayList<Funcionario>();

		con = Conexao.getConnection();

		try {

			PreparedStatement pstm = con.prepareStatement(sql.toString());
			pstm.setString(1, "%" + funcionario.getNome().toUpperCase() + "%");

			ResultSet rSet = pstm.executeQuery();

			while (rSet.next()) {

				Funcionario funcionarioRetorno = new Funcionario();

				funcionarioRetorno.setId(rSet.getInt("id"));
				funcionarioRetorno.setNome(rSet.getString("nome"));
				funcionarioRetorno.setDataNasc(rSet.getDate("data_nasc"));
				funcionarioRetorno.setSexo(rSet.getString("sexo"));
				funcionarioRetorno.setTelefone(rSet.getString("telefone"));

				Endereco end = new Endereco();
				end.setRua(rSet.getString("rua"));
				end.setNumero(rSet.getString("numero"));
				end.setComplemento(rSet.getString("complemento"));
				end.setBairro(rSet.getString("bairro"));

				Cidade cidadeRetorno = new Cidade();
				cidadeRetorno.setId(rSet.getInt("id_cidade"));
				cidadeRetorno = new CidadeDAO().pesquisarPorID(cidadeRetorno);

				end.setCidade(cidadeRetorno);
				funcionarioRetorno.setEndereco(end);

				funcionarioRetorno.setCelular(rSet.getString("celular"));
				funcionarioRetorno.setCargo(rSet.getString("cargo"));
				funcionarioRetorno.setSalario(rSet.getDouble("salario"));

				Usuario usuario = new Usuario(rSet.getInt("id_usuario"));
				usuario = new UsuarioDAO().pesquisarPorID(usuario);

				funcionarioRetorno.setUsuario(usuario);

				lista.add(funcionarioRetorno);

			}

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

		return lista;
	}

	/**
	 * 
	 * @param funcionario
	 *            Recebe um funcionario e localiza pelo Id no Banco de Dados
	 * @return Retorna um Objeto de Funcionario
	 */
	public Funcionario pesquisarPorUsuario(Usuario usuario) {

		StringBuffer sql = new StringBuffer();
		sql.append("select * from funcionarios ");
		sql.append("inner join usuarios on usuarios.id = funcionarios.id_usuario ");
		sql.append("where login = ?");

		Funcionario funcionarioRetorno = null;

		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			pstm.setString(1, usuario.getLogin());

			ResultSet rSet = pstm.executeQuery();

			while (rSet.next()) {

				funcionarioRetorno = new Funcionario();

				funcionarioRetorno.setId(rSet.getInt("id"));
				funcionarioRetorno.setNome(rSet.getString("nome"));
				funcionarioRetorno.setDataNasc(rSet.getDate("data_nasc"));
				funcionarioRetorno.setSexo(rSet.getString("sexo"));
				funcionarioRetorno.setTelefone(rSet.getString("telefone"));

				Endereco end = new Endereco();
				end.setRua(rSet.getString("rua"));
				end.setNumero(rSet.getString("numero"));
				end.setComplemento(rSet.getString("complemento"));
				end.setBairro(rSet.getString("bairro"));

				Cidade cidadeRetorno = new Cidade();
				cidadeRetorno.setId(rSet.getInt("id_cidade"));
				cidadeRetorno = new CidadeDAO().pesquisarPorID(cidadeRetorno);

				end.setCidade(cidadeRetorno);
				funcionarioRetorno.setEndereco(end);

				funcionarioRetorno.setCelular(rSet.getString("celular"));
				funcionarioRetorno.setCargo(rSet.getString("cargo"));
				funcionarioRetorno.setSalario(rSet.getDouble("salario"));

				Usuario usuarioRetorno = new Usuario(rSet.getInt("id_usuario"));
				usuarioRetorno = new UsuarioDAO().pesquisarPorID(usuarioRetorno);

				funcionarioRetorno.setUsuario(usuarioRetorno);

			}

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

		return funcionarioRetorno;

	}

}
