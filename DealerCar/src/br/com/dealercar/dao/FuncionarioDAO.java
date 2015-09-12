package br.com.dealercar.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.domain.Cidade;
import br.com.dealercar.domain.Endereco;
import br.com.dealercar.domain.Funcionario;
import br.com.dealercar.domain.Usuario;
import br.com.dealercar.factory.Conexao;
import br.com.dealercar.util.JSFUtil;

public class FuncionarioDAO {
	
	/**
	 * 
	 * @param funcionario Recebe um funcionario e uma Cidade e cadastra no Banco de Dados
	 * @param cidade
	 */
	public void cadastrar(Funcionario funcionario) {

		StringBuffer sql = new StringBuffer();
		sql.append("insert into funcionarios ");
		sql.append("(nome, data_nasc, sexo, endereco, telefone, celular, ");
		sql.append("id_cidade, id_usuario, cargo, salario) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

		Connection con = Conexao.getConnection();

		try {

			PreparedStatement ps = con.prepareStatement(sql.toString());
			int i = 0;
			ps.setString(++i, funcionario.getNome().toUpperCase());

			// Alterando o formato de armazenamento da data para o Banco de
			// Dados Aceitar
			String[] dNasc = funcionario.getDataNasc().split("-|/");
			String dia = dNasc[0];
			String mes = dNasc[1];
			String ano = dNasc[2];
			funcionario.setDataNasc(ano + "-" + mes + "-" + dia);

			ps.setString(++i, funcionario.getDataNasc());
			ps.setString(++i, funcionario.getSexo());

			StringBuffer endereco = new StringBuffer();
			
			//Concatenando o endereço para add no BD
			endereco.append(funcionario.getEndereco().getRua() + ", ");
			endereco.append(funcionario.getEndereco().getNumero() + ", ");
			if(funcionario.getEndereco().getComplemento() != null){
				endereco.append(funcionario.getEndereco().getComplemento() + ", ");
			}
			endereco.append(funcionario.getEndereco().getBairro());
			
			ps.setString(++i, endereco.toString());
			ps.setString(++i, funcionario.getTelefone());
			ps.setString(++i, funcionario.getCelular());
			ps.setInt(++i, funcionario.getCidade().getId());
			ps.setInt(++i, funcionario.getUsuario().getId());
			ps.setString(++i, funcionario.getCargo());
			ps.setDouble(++i, funcionario.getSalario());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}

	/**
	 * 
	 * @param funcionario Recebe um Funcionario e uma Cidade e edita seus dados do Banco de Dados
	 * @param 
	 */
	public void editar(Funcionario funcionario) {

		StringBuffer sql = new StringBuffer();
		sql.append("update funcionarios set ");
		sql.append("nome = ?, data_nasc = ?, sexo = ?, endereco = ?, telefone = ?, ");
		sql.append("celular = ?, cargo = ?, salario = ?, ");
		sql.append("id_cidade = ?, id_usuario = ? where id = ?");

		Connection con = Conexao.getConnection();

		try {

			PreparedStatement ps = con.prepareStatement(sql.toString());
			int i = 0;
			ps.setString(++i, funcionario.getNome().toUpperCase());

			// Alterando o formato de armazenamento da data para o Banco de
			// Dados Aceitar
			String[] dNasc = funcionario.getDataNasc().split("-|/");
			String dia = dNasc[0];
			String mes = dNasc[1];
			String ano = dNasc[2];
			funcionario.setDataNasc(ano + "-" + mes + "-" + dia);
			
			ps.setString(++i, funcionario.getDataNasc());
			ps.setString(++i, funcionario.getSexo());

			StringBuffer endereco = new StringBuffer();
			
			//Concatenando o endereço para add no BD
			endereco.append(funcionario.getEndereco().getRua() + ", ");
			endereco.append(funcionario.getEndereco().getNumero() + ", ");
			if(funcionario.getEndereco().getComplemento() != null){
				endereco.append(funcionario.getEndereco().getComplemento() + ", ");
			}
			endereco.append(funcionario.getEndereco().getBairro());
			
			ps.setString(++i, endereco.toString());
			ps.setString(++i, funcionario.getTelefone());
			ps.setString(++i, funcionario.getCelular());
			ps.setString(++i, funcionario.getCargo().toUpperCase());
			ps.setDouble(++i, funcionario.getSalario());
			ps.setInt(++i, funcionario.getCidade().getId());
			ps.setInt(++i, funcionario.getUsuario().getId());
			ps.setInt(++i, funcionario.getId());

			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param funcionario Recebe um Funcionario e exclui do Banco de dados
	 *  localizando pelo seu Id
	 */
	public void excluir(Funcionario funcionario) {

		String sql = "delete from funcionarios where id = ?";

		Connection con = Conexao.getConnection();

		try {

			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, funcionario.getId());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

	}

	/**
	 * 
	 * @return Retorna todos os funcionarios do Banco de dados em forma de List<Funcionario>
	 */
	public List<Funcionario> listarTodos() {

		StringBuffer sql = new StringBuffer(); 
		sql.append("select funcionarios.id, funcionarios.nome, funcionarios.telefone, funcionarios.data_nasc, ");
		sql.append("funcionarios.endereco, funcionarios.id_cidade, cidades.id, cidades.nome, cidades.uf, ");
		sql.append("funcionarios.celular, funcionarios.sexo, funcionarios.cargo, funcionarios.salario, ");
		sql.append("funcionarios.id_usuario, usuarios.id, usuarios.login, usuarios.senha ");
		sql.append("from funcionarios inner join cidades on funcionarios.id_cidade = cidades.id ");
		sql.append("inner join usuarios on funcionarios.id_usuario = usuarios.id ");
		sql.append("order by funcionarios.nome asc");

		List<Funcionario> funcionarios = new ArrayList<Funcionario>();

		Connection con = Conexao.getConnection();

		try {
			PreparedStatement ps = con.prepareStatement(sql.toString());
			ResultSet rSet = ps.executeQuery();

			while (rSet.next()) {

				Cidade cidadeRetorno = new Cidade();
				cidadeRetorno.setId(rSet.getInt("cidades.id"));
				cidadeRetorno.setNome(rSet.getString("cidades.nome"));
				cidadeRetorno.setUf(rSet.getString("cidades.uf"));

				Funcionario funcionarioRetorno = new Funcionario();

				funcionarioRetorno.setId(rSet.getInt("funcionarios.id"));
				funcionarioRetorno.setNome(rSet.getString("funcionarios.nome"));

				// Alterando o formato de armazenamento da data para o Banco de
				// Dados Aceitar
				String[] dNasc = rSet.getString("funcionarios.data_nasc").split("-|/");
				String dia = dNasc[2];
				String mes = dNasc[1];
				String ano = dNasc[0];

				funcionarioRetorno.setDataNasc(dia + "-" + mes + "-" + ano);
				
				funcionarioRetorno.setSexo(rSet.getString("funcionarios.sexo"));
				funcionarioRetorno.setTelefone(rSet.getString("funcionarios.telefone"));
				
				Endereco end = new Endereco();
				// Alterando o formato de armazenamento da endereço para o Banco de
				// Dados Aceitar
				String[] endereco = rSet.getString("funcionarios.endereco").split(",");
				
				if(endereco.length == 4) {
					end.setRua(endereco[0].trim());
					end.setNumero(endereco[1].trim());
					end.setComplemento(endereco[2].trim());
					end.setBairro(endereco[3].trim());
				} else {
					end.setRua(endereco[0].trim());
					end.setNumero(endereco[1].trim());
					end.setBairro(endereco[2].trim());
				}
				
				funcionarioRetorno.setEndereco(end);				
				
				funcionarioRetorno.setCelular(rSet.getString("funcionarios.celular"));
				funcionarioRetorno.setCargo(rSet.getString("funcionarios.cargo"));
				funcionarioRetorno.setSalario(rSet.getDouble("funcionarios.salario"));
				
				Usuario usuario = new Usuario();
				usuario.setId(rSet.getInt("funcionarios.id_usuario"));
				usuario.setLogin(rSet.getString("usuarios.login"));
				usuario.setSenha(rSet.getString("usuarios.senha"));
				
				funcionarioRetorno.setCidade(cidadeRetorno);
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
	 * @param funcionario Recebe um funcionario e localiza pelo Id no Banco de Dados
	 * @return Retorna um Objeto de Funcionario
	 */
	public Funcionario pesquisarPorID(Funcionario funcionario) {

		StringBuffer sql = new StringBuffer(); 
		sql.append("select funcionarios.id, funcionarios.nome, funcionarios.telefone, funcionarios.data_nasc, ");
		sql.append("funcionarios.endereco, funcionarios.id_cidade, cidades.id, cidades.nome, cidades.uf, ");
		sql.append("funcionarios.celular, funcionarios.sexo, funcionarios.cargo, funcionarios.salario, ");
		sql.append("funcionarios.id_usuario, usuarios.id, usuarios.login, usuarios.senha ");
		sql.append("from funcionarios inner join cidades on funcionarios.id_cidade = cidades.id ");
		sql.append("inner join usuarios on funcionarios.id_usuario = usuarios.id ");
		sql.append("where funcionarios.id = ?");

		Funcionario funcionarioRetorno = null;

		Connection con = Conexao.getConnection();

		try {
			PreparedStatement ps = con.prepareStatement(sql.toString());
			ps.setInt(1, funcionario.getId());

			ResultSet rSet = ps.executeQuery();

			while (rSet.next()) {
				funcionarioRetorno = new Funcionario();

				funcionarioRetorno.setId(rSet.getInt("funcionarios.id"));
				funcionarioRetorno.setNome(rSet.getString("funcionarios.nome"));
				
				// Alterando o formato de armazenamento da data para o Banco de
				// Dados Aceitar
				String[] dNasc = rSet.getString("funcionarios.data_nasc").split("-|/");
				String dia = dNasc[2];
				String mes = dNasc[1];
				String ano = dNasc[0];
				
				funcionarioRetorno.setDataNasc(dia + "-" + mes + "-" + ano);
				
				funcionarioRetorno.setSexo(rSet.getString("funcionarios.sexo"));
				funcionarioRetorno.setTelefone(rSet.getString("funcionarios.telefone"));
				
				Endereco end = new Endereco();
				// Alterando o formato de armazenamento da endereço para o Banco de
				// Dados Aceitar
				String[] endereco = rSet.getString("funcionarios.endereco").split(",");
				
				if(endereco.length == 4) {
					end.setRua(endereco[0].trim());
					end.setNumero(endereco[1].trim());
					end.setComplemento(endereco[2].trim());
					end.setBairro(endereco[3].trim());
				} else {
					end.setRua(endereco[0].trim());
					end.setNumero(endereco[1].trim());
					end.setBairro(endereco[2].trim());
				}
				
				funcionarioRetorno.setEndereco(end);				

				funcionarioRetorno.setCelular(rSet.getString("funcionarios.celular"));
				funcionarioRetorno.setCargo(rSet.getString("funcionarios.cargo"));
				funcionarioRetorno.setSalario(rSet.getDouble("funcionarios.salario"));

				Cidade cidade = new Cidade();
				cidade.setId(rSet.getInt("funcionarios.id_cidade"));
				cidade.setNome(rSet.getString("cidades.nome"));
				cidade.setUf(rSet.getString("cidades.uf"));

				Usuario usuario = new Usuario();
				usuario.setId(rSet.getInt("funcionarios.id_usuario"));
				usuario.setLogin(rSet.getString("usuarios.login"));
				usuario.setSenha(rSet.getString("usuarios.senha"));
				
				funcionarioRetorno.setCidade(cidade);
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
	 * @param funcionarios Recebe um objeto do Tipo Funcionario e localiza no Banco pelo nome
	 * @return Retorna um Objeto do tipo Funcionario
	 */
	public List<Funcionario> pesquisarPorNome(Funcionario funcionario) {

		StringBuffer sql = new StringBuffer();
		sql.append("select distinct funcionarios.id, funcionarios.nome, funcionarios.telefone, funcionarios.data_nasc, ");
		sql.append("funcionarios.endereco, funcionarios.id_cidade, cidades.id, cidades.nome, cidades.uf, ");
		sql.append("funcionarios.celular, funcionarios.sexo, funcionarios.cargo, funcionarios.salario, ");
		sql.append("funcionarios.id_usuario, usuarios.id, usuarios.login, usuarios.senha ");
		sql.append("from funcionarios inner join cidades on funcionarios.id_cidade = cidades.id ");
		sql.append("inner join usuarios on funcionarios.id_usuario = usuarios.id ");
		sql.append("where funcionarios.nome like ? order by funcionarios.nome asc");

		List<Funcionario> lista = new ArrayList<Funcionario>();

		Connection con = Conexao.getConnection();

		try {

			PreparedStatement ps = con.prepareStatement(sql.toString());
			ps.setString(1, "%" + funcionario.getNome().toUpperCase() + "%");

			ResultSet rSet = ps.executeQuery();

			while (rSet.next()) {

				Funcionario funcionarioRetorno = new Funcionario();

				funcionarioRetorno.setId(rSet.getInt("funcionarios.id"));
				funcionarioRetorno.setNome(rSet.getString("funcionarios.nome"));
				
				// Alterando o formato de armazenamento da data para o Banco de
				// Dados Aceitar
				String[] dNasc = rSet.getString("funcionarios.data_nasc").split("-|/");
				String dia = dNasc[2];
				String mes = dNasc[1];
				String ano = dNasc[0];
				
				funcionarioRetorno.setDataNasc(dia + "-" + mes + "-" + ano);
				
				funcionarioRetorno.setSexo(rSet.getString("funcionarios.sexo"));
				funcionarioRetorno.setTelefone(rSet.getString("funcionarios.telefone"));
				
				Endereco end = new Endereco();
				// Alterando o formato de armazenamento da endereço para o Banco de
				// Dados Aceitar
				String[] endereco = rSet.getString("funcionarios.endereco").split(",");
				
					if(endereco.length == 4) {
						end.setRua(endereco[0].trim());
						end.setNumero(endereco[1].trim());
						end.setComplemento(endereco[2].trim());
						end.setBairro(endereco[3].trim());
					} else {
						end.setRua(endereco[0].trim());
						end.setNumero(endereco[1].trim());
						end.setBairro(endereco[2].trim());
					}
				
				funcionarioRetorno.setEndereco(end);				

				funcionarioRetorno.setCelular(rSet.getString("funcionarios.celular"));
				funcionarioRetorno.setCargo(rSet.getString("funcionarios.cargo"));
				funcionarioRetorno.setSalario(rSet.getDouble("funcionarios.salario"));

				Cidade cidade = new Cidade();
				cidade.setId(rSet.getInt("funcionarios.id_cidade"));
				cidade.setNome(rSet.getString("cidades.nome"));
				cidade.setUf(rSet.getString("cidades.uf"));

				Usuario usuario = new Usuario();
				usuario.setId(rSet.getInt("funcionarios.id_usuario"));
				usuario.setLogin(rSet.getString("usuarios.login"));
				usuario.setSenha(rSet.getString("usuarios.senha"));
				
				funcionarioRetorno.setCidade(cidade);
				funcionarioRetorno.setUsuario(usuario);

				lista.add(funcionarioRetorno);

			}

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

		return lista;
	}


}
