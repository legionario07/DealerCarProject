package br.com.dealercar.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.domain.Cliente;
import br.com.dealercar.domain.Funcionario;
import br.com.dealercar.domain.Reserva;
import br.com.dealercar.domain.automotivos.Modelo;
import br.com.dealercar.factory.Conexao;
import br.com.dealercar.util.JSFUtil;

public class ReservaDAO {


	/**
	 * 
	 * @param reserva Recebe um reserva e cadastra no Banco de Dados
	 * 
	 */
	public void cadastrar(Reserva reserva) {

		StringBuffer sql = new StringBuffer();
		sql.append("insert into reservas ");
		sql.append("(situacao, data_inicio, data_fim, id_modelo, id_cliente, id_funcionario) ");
		sql.append("values (?, ?, ?, ?, ?, ?)");

		Connection con = Conexao.getConnection();

		try {

			PreparedStatement ps = con.prepareStatement(sql.toString());
			int i = 0;
		
			ps.setString(++i, reserva.getSituacao().toString());

			/*
			// Alterando o formato de armazenamento da data para o Banco de
			// Dados Aceitar
			String[] dNasc = reserva.getDataNasc().split("-|/");
			String dia = dNasc[0];
			String mes = dNasc[1];
			String ano = dNasc[2];
			reserva.setDataNasc(ano + "-" + mes + "-" + dia);
			*/
			
			ps.setString(++i, reserva.getDataInicio().toString());
			ps.setString(++i, reserva.getDataFim().toString());
			ps.setInt(++i, reserva.getModelo().getId());
			ps.setInt(++i, reserva.getCliente().getId());
			ps.setInt(++i, reserva.getFuncionario().getId());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}

	/**
	 * 
	 * @param reserva Recebe uma Reserva e edita seus dados do Banco de Dados
	 * 
	 */
	public void editar(Reserva reserva) {

		StringBuffer sql = new StringBuffer();
		sql.append("update reservas set situacao = ?, data_inicio = ?, data_fim = ?, id_modelo = ?, ");
		sql.append("id_cliente = ?, id_funcionario = ? ");
		sql.append("where id = ?");
		
		Connection con = Conexao.getConnection();

		try {

			PreparedStatement ps = con.prepareStatement(sql.toString());

			int i = 0;
			
			ps.setString(++i, reserva.getSituacao().toString());

			/*
			// Alterando o formato de armazenamento da data para o Banco de
			// Dados Aceitar
			String[] dNasc = reserva.getDataNasc().split("-|/");
			String dia = dNasc[0];
			String mes = dNasc[1];
			String ano = dNasc[2];
			reserva.setDataNasc(ano + "-" + mes + "-" + dia);
			*/
			
			ps.setString(++i, reserva.getDataInicio().toString());
			ps.setString(++i, reserva.getDataFim().toString());
			ps.setInt(++i, reserva.getModelo().getId());
			ps.setInt(++i, reserva.getCliente().getId());
			ps.setInt(++i, reserva.getFuncionario().getId());
			ps.setInt(++i, reserva.getId());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param reserva Recebe um Reserva e exclui do Banco de dados
	 *  localizando pelo seu Id
	 */
	public void excluir(Reserva reserva) {

		String sql = "delete from reservas where id = ?";

		Connection con = Conexao.getConnection();

		try {

			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, reserva.getId());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

	}

	/**
	 * 
	 * @return Retorna todos as reservas do Banco de dados em forma de List<Reserva>
	 */
	public List<Reserva> listarTodos() {

		StringBuffer sql = new StringBuffer(); 
		sql.append("select reservas.id, reservas.situacao, reservas.data_inicio, ");
		sql.append("reservas.data_fim, reservas.id_modelo, reservas.id_cliente, ");
		sql.append("reservas.id_funcionario ");
		sql.append("from reservas inner join modelos on reservas.id_modelo = modelos.id ");
		sql.append("inner join clientes on reservas.id_cliente = clientes.id ");
		sql.append("inner join funcionarios on reservas.id_funcionario = funcionarios.id ");
		sql.append("order by reservas.situacao asc");

		List<Reserva> listaReservas = new ArrayList<Reserva>();

		Connection con = Conexao.getConnection();

		try {
			PreparedStatement ps = con.prepareStatement(sql.toString());
			ResultSet rSet = ps.executeQuery();

			while (rSet.next()) {

				Reserva reservaRetorno = new Reserva();

				reservaRetorno.setId(rSet.getInt("reservas.id"));
				reservaRetorno.setDataInicio(rSet.getDate("reservas.data_inicio"));

				/*
				String[] dNasc = rSet.getString("reservas.data_nasc").split("-|/");
				String dia = dNasc[2];
				String mes = dNasc[1];
				String ano = dNasc[0];

				reservaRetorno.setData(dia + "-" + mes + "-" + ano);
				
				*/
				reservaRetorno.setDataFim(rSet.getDate("reservas.data_fim"));
				
				Modelo modelo = new Modelo();
				modelo.setId(rSet.getInt("reservas.id_modelo"));
				modelo.setNome(rSet.getString("modelos.nome"));
				
				
				reservaRetorno.setModelo(modelo);
				
				Cliente cliente = new Cliente();
				cliente.setId(rSet.getInt("reservas.id_cliente"));
				
				reservaRetorno.setCliente(cliente);
				
				Funcionario funcionario = new Funcionario();
				funcionario.setId(rSet.getInt("reservas.id_funcionario"));
				
				reservaRetorno.setFuncionario(funcionario);
				
				listaReservas.add(reservaRetorno);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

		return listaReservas;

	}

	/**
	 * 
	 * @param reserva Recebe um reserva e localiza pelo Id no Banco de Dados
	 * @return Retorna um Objeto de Reserva
	 */
	public Reserva pesquisarPorID(Reserva reserva) {

		StringBuffer sql = new StringBuffer(); 
		sql.append("select reservas.id, reservas.situacao, reservas.data_inicio, ");
		sql.append("reservas.data_fim, reservas.id_modelo, reservas.id_cliente, ");
		sql.append("reservas.id_funcionario ");
		sql.append("from reservas inner join modelos on reservas.id_modelo = modelos.id ");
		sql.append("inner join clientes on reservas.id_cliente = clientes.id ");
		sql.append("inner join funcionarios on reservas.id_funcionario = funcionarios.id ");
		sql.append("where id = ?");

		Reserva reservaRetorno = null;

		Connection con = Conexao.getConnection();

		try {
			PreparedStatement ps = con.prepareStatement(sql.toString());
			ps.setInt(1, reserva.getId());

			ResultSet rSet = ps.executeQuery();

			while (rSet.next()) {
				reservaRetorno = new Reserva();

				reservaRetorno.setId(rSet.getInt("reservas.id"));
				reservaRetorno.setDataInicio(rSet.getDate("reservas.data_inicio"));

				/*
				String[] dNasc = rSet.getString("reservas.data_nasc").split("-|/");
				String dia = dNasc[2];
				String mes = dNasc[1];
				String ano = dNasc[0];

				reservaRetorno.setData(dia + "-" + mes + "-" + ano);
				
				*/
				reservaRetorno.setDataFim(rSet.getDate("reservas.data_fim"));
				
				Modelo modelo = new Modelo();
				modelo.setId(rSet.getInt("reservas.id_modelo"));
				modelo.setNome(rSet.getString("modelos.nome"));
				
				
				reservaRetorno.setModelo(modelo);
				
				Cliente cliente = new Cliente();
				cliente.setId(rSet.getInt("reservas.id_cliente"));
				
				reservaRetorno.setCliente(cliente);
				
				Funcionario funcionario = new Funcionario();
				funcionario.setId(rSet.getInt("reservas.id_funcionario"));
				
				reservaRetorno.setFuncionario(funcionario);

			}

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

		return reservaRetorno;

	}
	
	/**
	 * 
	 * @param reserva Recebe um objeto de Reserva e localiza no Banco de Dadoos
	 *  pelo modelo do carro
	 * @return Retorna uma lista de Objeto Reserva
	 */
	public List<Reserva> pesquisarPorModelo(Reserva reserva) {

		StringBuffer sql = new StringBuffer(); 
		sql.append("select reservas.id, reservas.situacao, reservas.data_inicio, ");
		sql.append("reservas.data_fim, reservas.id_modelo, modelos.nome, reservas.id_cliente, ");
		sql.append("reservas.id_funcionario MAX(reservas.id)");
		sql.append("from reservas inner join modelos on reservas.id_modelo = modelos.id ");
		sql.append("inner join clientes on reservas.id_cliente = clientes.id ");
		sql.append("inner join funcionarios on reservas.id_funcionario = funcionarios.id ");
		sql.append("where modelos.nome = ?");

		List<Reserva> listaReserva = new ArrayList<Reserva>();
		
		Connection con = Conexao.getConnection();

		try {
			PreparedStatement ps = con.prepareStatement(sql.toString());
			ps.setString(1, "%" + reserva.getModelo().getNome().toUpperCase() + "%");

			ResultSet rSet = ps.executeQuery();

			while (rSet.next()) {
				Reserva reservaRetorno = new Reserva();

				reservaRetorno.setId(rSet.getInt("reservas.id"));
				reservaRetorno.setDataInicio(rSet.getDate("reservas.data_inicio"));

				/*
				String[] dNasc = rSet.getString("reservas.data_nasc").split("-|/");
				String dia = dNasc[2];
				String mes = dNasc[1];
				String ano = dNasc[0];

				reservaRetorno.setData(dia + "-" + mes + "-" + ano);
				
				*/
				reservaRetorno.setDataFim(rSet.getDate("reservas.data_fim"));
				
				Modelo modelo = new Modelo();
				modelo.setId(rSet.getInt("reservas.id_modelo"));
				modelo.setNome(rSet.getString("modelos.nome"));
				
				
				reservaRetorno.setModelo(modelo);
				
				Cliente cliente = new Cliente();
				cliente.setId(rSet.getInt("reservas.id_cliente"));
				
				reservaRetorno.setCliente(cliente);
				
				Funcionario funcionario = new Funcionario();
				funcionario.setId(rSet.getInt("reservas.id_funcionario"));
				
				reservaRetorno.setFuncionario(funcionario);

				listaReserva.add(reservaRetorno);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

		return listaReserva;

	}

	/**
	 * 
	 * @param reserva Recebe um objeto de Reserva e localiza no Banco de Dadoos
	 *  pelo CPF do Cliente
	 * @return Retorna uma lista de Objeto Reserva
	 */
	public List<Reserva> pesquisarPorCPF(Reserva reserva) {

		StringBuffer sql = new StringBuffer(); 
		sql.append("select reservas.id, reservas.situacao, reservas.data_inicio, ");
		sql.append("reservas.data_fim, reservas.id_modelo, reservas.id_cliente, clientes.nome ");
		sql.append("reservas.id_funcionario MAX(reservas.id)");
		sql.append("from reservas inner join modelos on reservas.id_modelo = modelos.id ");
		sql.append("inner join clientes on reservas.id_cliente = clientes.id ");
		sql.append("inner join funcionarios on reservas.id_funcionario = funcionarios.id ");
		sql.append("where clientes.cpf = ?");

		List<Reserva> listaReserva = new ArrayList<Reserva>();
		
		Connection con = Conexao.getConnection();

		try {
			PreparedStatement ps = con.prepareStatement(sql.toString());
			ps.setString(1, reserva.getCliente().getCPF());

			ResultSet rSet = ps.executeQuery();

			while (rSet.next()) {
				Reserva reservaRetorno = new Reserva();

				reservaRetorno.setId(rSet.getInt("reservas.id"));
				reservaRetorno.setDataInicio(rSet.getDate("reservas.data_inicio"));

				/*
				String[] dNasc = rSet.getString("reservas.data_nasc").split("-|/");
				String dia = dNasc[2];
				String mes = dNasc[1];
				String ano = dNasc[0];

				reservaRetorno.setData(dia + "-" + mes + "-" + ano);
				
				*/
				reservaRetorno.setDataFim(rSet.getDate("reservas.data_fim"));
				
				Modelo modelo = new Modelo();
				modelo.setId(rSet.getInt("reservas.id_modelo"));
				modelo.setNome(rSet.getString("modelos.nome"));
				
				
				reservaRetorno.setModelo(modelo);
				
				Cliente cliente = new Cliente();
				cliente.setId(rSet.getInt("reservas.id_cliente"));
				
				reservaRetorno.setCliente(cliente);
				
				Funcionario funcionario = new Funcionario();
				funcionario.setId(rSet.getInt("reservas.id_funcionario"));
				
				reservaRetorno.setFuncionario(funcionario);

				listaReserva.add(reservaRetorno);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

		return listaReserva;

	}
	
}